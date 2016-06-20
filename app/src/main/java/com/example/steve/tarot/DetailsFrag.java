package com.example.steve.tarot;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailsFrag extends Fragment {

    View view;

    ArrayList<String> detailsNumArraylist;
    ArrayList<Details> detailsArrayList;

    ArrayAdapter<String> adaptDetailsList;

    ListView detailsListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_details, container, false);

        detailsNumArraylist = new ArrayList<>();
        detailsNumArraylist = getArguments().getStringArrayList("detailsNumList");

        detailsArrayList = new ArrayList<>();
        detailsArrayList = getArguments().getParcelableArrayList("detailsList");

        detailsListView = (ListView) view.findViewById(R.id.detailsList);
        adaptDetailsList = new ArrayAdapter<String>(getActivity(), R.layout.mylist, detailsNumArraylist);

        detailsListView.setAdapter(adaptDetailsList);

        detailsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());


                // Get the layout inflater
                LayoutInflater inflater = getActivity().getLayoutInflater();

                // Inflate the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                final View dialogView = inflater.inflate(R.layout.details, null);

                final TextView preneurTxt = (TextView) dialogView.findViewById(R.id.preneurDetailsTxt);
                final TextView preneurTitle = (TextView) dialogView.findViewById(R.id.preneurDetailsTitle);

                final TextView enchereTitle = (TextView) dialogView.findViewById(R.id.enchereDetailsTitle);
                final TextView enchereTxt = (TextView) dialogView.findViewById(R.id.enchereDetailsTxt);

                final TextView enchereTitle2 = (TextView) dialogView.findViewById(R.id.enchereDetailsTitle2);
                final TextView enchereTxt2 = (TextView) dialogView.findViewById(R.id.enchereDetailsTxt2);

                final TextView associeTitle = (TextView) dialogView.findViewById(R.id.associeDetailsTitle);
                final TextView associeTxt = (TextView) dialogView.findViewById(R.id.associeDetailsTxt);

                final TextView annonce1Txt = (TextView) dialogView.findViewById(R.id.annonce1Details);
                final TextView joueur1Txt = (TextView) dialogView.findViewById(R.id.joueur1Details);

                final TextView annonce2Txt = (TextView) dialogView.findViewById(R.id.annonce2Details);
                final TextView joueur2Txt = (TextView) dialogView.findViewById(R.id.joueur2Details);

                final TextView scoreQuiTitle = (TextView) dialogView.findViewById(R.id.scoreQuiDetailsTitle);
                final TextView scoreQuiTxt = (TextView) dialogView.findViewById(R.id.scoreQuiDetailsTxt);

                final TextView nbBoutTitle = (TextView) dialogView.findViewById(R.id.nbBoutDetailsTitle);
                final TextView nbBoutTxt = (TextView) dialogView.findViewById(R.id.nbBoutDetailsTxt);

                final TextView scoreAttTitle = (TextView) dialogView.findViewById(R.id.scoreAttDetailsTitle);
                final TextView scoreAttTxt = (TextView) dialogView.findViewById(R.id.scoreAttDetailsTxt);

                final TextView detailsScore = (TextView) dialogView.findViewById(R.id.detailsScore);


                if (detailsArrayList.get(position).getPreneur().equals("Personne")) {
                    preneurTxt.setText(detailsArrayList.get(position).getPreneurForPersonne());
                } else {
                    preneurTxt.setText(detailsArrayList.get(position).getPreneur());
                }


                if (detailsArrayList.get(position).getAssocie().equals("")) {
                    enchereTitle.setVisibility(dialogView.VISIBLE);
                    enchereTxt.setVisibility(dialogView.VISIBLE);

                    enchereTxt.setText(detailsArrayList.get(position).getEnchere());

                    associeTitle.setVisibility(dialogView.INVISIBLE);
                    associeTxt.setVisibility(dialogView.INVISIBLE);

                    enchereTitle2.setVisibility(dialogView.INVISIBLE);
                    enchereTxt2.setVisibility(dialogView.INVISIBLE);
                } else {
                    enchereTitle.setVisibility(dialogView.INVISIBLE);
                    enchereTxt.setVisibility(dialogView.INVISIBLE);

                    associeTitle.setVisibility(dialogView.VISIBLE);
                    associeTxt.setVisibility(dialogView.VISIBLE);

                    enchereTitle2.setVisibility(dialogView.VISIBLE);
                    enchereTxt2.setVisibility(dialogView.VISIBLE);

                    enchereTxt2.setText(detailsArrayList.get(position).getEnchere());
                    associeTxt.setText(detailsArrayList.get(position).getAssocie());
                }

                annonce1Txt.setText(detailsArrayList.get(position).getAnnonceList().get(0));
                joueur1Txt.setText(detailsArrayList.get(position).getJoueurList().get(0));

                if (detailsArrayList.get(position).getAnnonceList().size() > 1) {
                    annonce2Txt.setVisibility(dialogView.VISIBLE);
                    annonce2Txt.setText(detailsArrayList.get(position).getAnnonceList().get(1));

                    joueur2Txt.setVisibility(dialogView.VISIBLE);
                    joueur2Txt.setText(detailsArrayList.get(position).getJoueurList().get(1));
                } else {
                    annonce2Txt.setVisibility(dialogView.INVISIBLE);
                    joueur2Txt.setVisibility(dialogView.INVISIBLE);
                }


                if (detailsArrayList.get(position).getPreneur().equals("Personne")) {
                    preneurTitle.setText("Perdant: ");
                    scoreQuiTitle.setVisibility(View.INVISIBLE);
                    scoreQuiTxt.setVisibility(View.INVISIBLE);
                    nbBoutTitle.setVisibility(View.INVISIBLE);
                    nbBoutTxt.setVisibility(View.INVISIBLE);
                    scoreAttTitle.setVisibility(View.INVISIBLE);
                    scoreAttTxt.setVisibility(View.INVISIBLE);
                    detailsScore.setVisibility(View.INVISIBLE);

                } else {

                    preneurTitle.setText("Preneur: ");

                    scoreQuiTitle.setVisibility(View.VISIBLE);
                    scoreQuiTxt.setVisibility(View.VISIBLE);
                    nbBoutTitle.setVisibility(View.VISIBLE);
                    nbBoutTxt.setVisibility(View.VISIBLE);
                    scoreAttTitle.setVisibility(View.VISIBLE);
                    scoreAttTxt.setVisibility(View.VISIBLE);
                    detailsScore.setVisibility(View.VISIBLE);

                    if (detailsArrayList.get(position).getCalculScoreDe().equals("Preneur")) {
                        scoreQuiTitle.setText("Score preneur: ");
                    } else if (detailsArrayList.get(position).getCalculScoreDe().equals("Défenseurs")) {
                        scoreQuiTitle.setText("Score défenseurs: ");
                    }

                    scoreQuiTxt.setText(Double.toString(detailsArrayList.get(position).getScoreCartes()));

                    nbBoutTxt.setText(Integer.toString(detailsArrayList.get(position).getNbBout()));


                    switch (detailsArrayList.get(position).getNbBout()) {
                        case 0:
                            scoreAttTxt.setText("56 points");
                            break;

                        case 1:
                            scoreAttTxt.setText("51 points");
                            break;

                        case 2:
                            scoreAttTxt.setText("41 points");
                            break;

                        case 3:
                            scoreAttTxt.setText("36 points");
                            break;
                    }

                    if ((detailsArrayList.get(position).getResult() == true) && (detailsArrayList.get(position).getCalculScoreDe().equals("Preneur"))) {
                        detailsScore.setText("Le preneur a gagné ");
                    } else if ((detailsArrayList.get(position).getResult() == true) && (detailsArrayList.get(position).getCalculScoreDe().equals("Défenseurs"))) {
                        detailsScore.setText("Le preneur a gagné ");
                    } else if ((detailsArrayList.get(position).getResult() == false) && (detailsArrayList.get(position).getCalculScoreDe().equals("Preneur"))) {
                        detailsScore.setText("Le preneur a perdu ");
                    } else if ((detailsArrayList.get(position).getResult() == false) && (detailsArrayList.get(position).getCalculScoreDe().equals("Défenseurs"))) {
                        detailsScore.setText("Le preneur a perdu ");
                    }


                    detailsScore.setText(detailsScore.getText() +
                            "de "
                            + Integer.toString(detailsArrayList.get(position).getRoundFinalValue())
                            + " (arrondi de "
                            + Double.toString(detailsArrayList.get(position).getFinalValue())
                            + ")");
                }

                // Set the layout for the dialog
                adb.setView(dialogView);

                if (detailsArrayList.get(position).getPreneur().equals("Personne"))
                {
                    // Means there is at least 6 players (displays the playerMort)
                    if (!detailsArrayList.get(position).getPlayerMort().equals(""))
                    {
                        adb.setTitle("Jeu n°" + Integer.toString(detailsArrayList.get(position).getNumJeu()) + " (à l'envers)" + "\r\n" + "Mort: " + detailsArrayList.get(position).getPlayerMort());
                    }

                    else
                    {
                        adb.setTitle("Jeu n°" + Integer.toString(detailsArrayList.get(position).getNumJeu()) + " (à l'envers)");
                    }

                }

                else
                {
                    // Means there is at least 6 players (displays the playerMort)
                    if (!detailsArrayList.get(position).getPlayerMort().equals(""))
                    {
                        adb.setTitle("Jeu n°" + Integer.toString(detailsArrayList.get(position).getNumJeu()) + "                       " + "\r\n" + "Mort: " + detailsArrayList.get(position).getPlayerMort());
                    }

                    else
                    {
                        adb.setTitle("Jeu n°" + Integer.toString(detailsArrayList.get(position).getNumJeu()));
                    }
                }


                adb.setPositiveButton("Fermer", null);

                adb.show();

            }
        });


        return view;

    }


}