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

                if (detailsArrayList.get(position).getCalculScoreDe().equals("Preneur"))
                {
                    detailsScore.setText("Le preneur a un score de ");
                }

                else if (detailsArrayList.get(position).getCalculScoreDe().equals("Défenseurs"))
                {
                    detailsScore.setText("Les défenseurs ont un score de ");
                }

                detailsScore.setText(detailsScore.getText()

                        + Double.toString(detailsArrayList.get(position).getScoreCartes())
                        + " avec " + Integer.toString(detailsArrayList.get(position).getNbBout())
                        + " bouts, donc"
                        + "\r\n\r\n");

                if ((detailsArrayList.get(position).getResult() == true) && (detailsArrayList.get(position).getCalculScoreDe().equals("Preneur"))) {
                    detailsScore.setText(detailsScore.getText() + "Le preneur a gagné ");
                } else if ((detailsArrayList.get(position).getResult() == true) && (detailsArrayList.get(position).getCalculScoreDe().equals("Défenseurs"))) {
                    detailsScore.setText(detailsScore.getText() + "Le preneur a gagné ");
                } else if ((detailsArrayList.get(position).getResult() == false) && (detailsArrayList.get(position).getCalculScoreDe().equals("Preneur"))) {
                    detailsScore.setText(detailsScore.getText() + "Le preneur a perdu ");
                } else if ((detailsArrayList.get(position).getResult() == false) && (detailsArrayList.get(position).getCalculScoreDe().equals("Défenseurs"))) {
                    detailsScore.setText(detailsScore.getText() + "Le preneur a perdu ");
                }

                detailsScore.setText(detailsScore.getText() +
                        "de "
                        + Double.toString(detailsArrayList.get(position).getFinalValue())
                        + " donc de "
                        + Integer.toString(detailsArrayList.get(position).getRoundFinalValue()));

                // Set the layout for the dialog
                adb.setView(dialogView);

                if (detailsArrayList.get(position).getPreneur().equals("Personne")) {
                    adb.setTitle("Jeu n°" + Integer.toString(detailsArrayList.get(position).getNumJeu()) + " (à l'envers)");
                } else {
                    adb.setTitle("Jeu n°" + Integer.toString(detailsArrayList.get(position).getNumJeu()));
                }


                adb.setPositiveButton("Fermer", null);

                adb.show();

            }
        });


        return view;

    }


}