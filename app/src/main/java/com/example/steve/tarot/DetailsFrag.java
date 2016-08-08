package com.example.steve.tarot;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailsFrag extends Fragment {

    View view;

    ArrayList<String> detailsNumArraylist;
    ArrayList<Details> detailsArrayList;
    ArrayList<Jeu> jeuArrayList;

    ArrayAdapter<String> adaptDetailsList;

    ListView detailsListView;

    int pos;

    boolean dialogClosed = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_details, container, false);

        detailsNumArraylist = new ArrayList<>();
        detailsNumArraylist = getArguments().getStringArrayList("detailsNumList");

        detailsArrayList = new ArrayList<>();
        detailsArrayList = getArguments().getParcelableArrayList("detailsList");

        jeuArrayList = new ArrayList<>();
        jeuArrayList = getArguments().getParcelableArrayList("jeuList");

        detailsListView = (ListView) view.findViewById(R.id.detailsList);
        adaptDetailsList = new ArrayAdapter<String>(getActivity(), R.layout.mylist, detailsNumArraylist);

        detailsListView.setAdapter(adaptDetailsList);

        detailsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());


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

                final TextView diffTitle = (TextView) dialogView.findViewById(R.id.diffTitle);
                final TextView diffTxt = (TextView) dialogView.findViewById(R.id.diffTxt);

                final TextView resTxt = (TextView) dialogView.findViewById(R.id.resTxt);

                final Button classBtn = (Button) dialogView.findViewById(R.id.classBtn);

                pos = position;

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
                    diffTitle.setVisibility(View.INVISIBLE);
                    diffTxt.setVisibility(View.INVISIBLE);
                    resTxt.setVisibility(View.INVISIBLE);

                } else {

                    preneurTitle.setText("Preneur: ");

                    scoreQuiTitle.setVisibility(View.VISIBLE);
                    scoreQuiTxt.setVisibility(View.VISIBLE);
                    nbBoutTitle.setVisibility(View.VISIBLE);
                    nbBoutTxt.setVisibility(View.VISIBLE);
                    scoreAttTitle.setVisibility(View.VISIBLE);
                    scoreAttTxt.setVisibility(View.VISIBLE);
                    diffTitle.setVisibility(View.VISIBLE);
                    diffTxt.setVisibility(View.VISIBLE);
                    resTxt.setVisibility(View.VISIBLE);



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

                    diffTxt.setText(Double.toString(detailsArrayList.get(position).getFinalValue()));

                    if ((detailsArrayList.get(position).getResult() == true) && (detailsArrayList.get(position).getCalculScoreDe().equals("Preneur"))) {
                        resTxt.setText("Victoire de ");
                    } else if ((detailsArrayList.get(position).getResult() == true) && (detailsArrayList.get(position).getCalculScoreDe().equals("Défenseurs"))) {
                        resTxt.setText("Victoire de ");
                    } else if ((detailsArrayList.get(position).getResult() == false) && (detailsArrayList.get(position).getCalculScoreDe().equals("Preneur"))) {
                        resTxt.setText("Défaite de ");
                    } else if ((detailsArrayList.get(position).getResult() == false) && (detailsArrayList.get(position).getCalculScoreDe().equals("Défenseurs"))) {
                        resTxt.setText("Défaite de ");
                    }


                    resTxt.setText(resTxt.getText() + Integer.toString(detailsArrayList.get(position).getRoundFinalValue()) + " points");

                    if(detailsArrayList.get(position).getPetitAuBout())
                    {
                        resTxt.setText(resTxt.getText() + "\r\n" + "-- Petit au bout --");
                    }

                    if(detailsArrayList.get(position).getPartieAnnule())
                    {
                        preneurTitle.setPaintFlags(preneurTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        preneurTxt.setPaintFlags(preneurTxt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        enchereTitle.setPaintFlags(enchereTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        enchereTxt.setPaintFlags(enchereTxt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        enchereTitle2.setPaintFlags(enchereTitle2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        enchereTxt2.setPaintFlags(enchereTxt2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        associeTitle.setPaintFlags(associeTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        associeTxt.setPaintFlags(associeTxt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        annonce1Txt.setPaintFlags(annonce1Txt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        joueur1Txt.setPaintFlags(joueur1Txt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        annonce2Txt.setPaintFlags(annonce2Txt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        joueur2Txt.setPaintFlags(joueur2Txt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        scoreQuiTitle.setPaintFlags(scoreQuiTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        scoreQuiTxt.setPaintFlags(scoreQuiTxt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        nbBoutTitle.setPaintFlags(nbBoutTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        nbBoutTxt.setPaintFlags(nbBoutTxt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        scoreAttTitle.setPaintFlags(scoreAttTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        scoreAttTxt.setPaintFlags(scoreAttTxt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        diffTitle.setPaintFlags(diffTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        diffTxt.setPaintFlags(diffTxt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        resTxt.setPaintFlags(resTxt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                }

                // Set the layout for the dialog
                adb.setView(dialogView);

                String title = "";

                if (detailsArrayList.get(position).getPreneur().equals("Personne")) {
                    title = "Jeu n°" + Integer.toString(detailsArrayList.get(position).getNumJeu()) + " (à l'envers)";

                    // Means there is at least 6 players (displays the playerMort)
                    if (!detailsArrayList.get(position).getPlayerMort().equals(""))
                    {
                        title += "\r\n" + "Mort: " + detailsArrayList.get(position).getPlayerMort();
                    }

                }

                else
                {

                    title = "Jeu n°" + Integer.toString(detailsArrayList.get(position).getNumJeu());

                    if(detailsArrayList.get(position).getPartieAnnule())
                    {
                        title += " - Annulé ";
                    }

                    // Means there is at least 6 players (displays the playerMort)
                    if (!detailsArrayList.get(position).getPlayerMort().equals(""))
                    {
                        title += "                       " + "\r\n" + "Mort: " + detailsArrayList.get(position).getPlayerMort();
                    }
                }

                adb.setTitle(title);

                adb.setPositiveButton("Fermer", null);

                adb.show();

                classBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

                        // Get the layout inflater
                        LayoutInflater inflater = getActivity().getLayoutInflater();

                        // Inflate the layout for the dialog
                        // Pass null as the parent view because its going in the dialog layout
                        final View dialogView2 = inflater.inflate(R.layout.details_classement, null);

                        final TextView class1Txt = (TextView) dialogView2.findViewById(R.id.classP1);
                        final TextView class2Txt = (TextView) dialogView2.findViewById(R.id.classP2);
                        final TextView class3Txt = (TextView) dialogView2.findViewById(R.id.classP3);
                        final TextView class4Txt = (TextView) dialogView2.findViewById(R.id.classP4);
                        final TextView class5Txt = (TextView) dialogView2.findViewById(R.id.classP5);
                        final TextView class6Txt = (TextView) dialogView2.findViewById(R.id.classP6);


                        switch (jeuArrayList.get(pos).getNbJoueur()) {

                            case 6:
                                class6Txt.setVisibility(View.VISIBLE);
                                class6Txt.setText(setClassement(6));
                            case 5:
                                class5Txt.setVisibility(View.VISIBLE);
                                class5Txt.setText(setClassement(5));
                            case 4:
                                class4Txt.setVisibility(View.VISIBLE);
                                class4Txt.setText(setClassement(4));
                            case 3:
                                class3Txt.setVisibility(View.VISIBLE);
                                class3Txt.setText(setClassement(3));

                                class2Txt.setVisibility(View.VISIBLE);
                                class2Txt.setText(setClassement(2));

                                class1Txt.setVisibility(View.VISIBLE);
                                class1Txt.setText(setClassement(1));
                                break;
                        }

                        dialog.setView(dialogView2);

                        dialog.setTitle("Classement jeu n°" + Integer.toString(detailsArrayList.get(pos).getNumJeu()));

                        dialog.setPositiveButton("Fermer", null);

                        dialog.show();
                    }
                });
            }
        });


        return view;

    }


    private String setClassement(int placement)
    {
        String text = "";

        text = Integer.toString(placement)
                + "-  "
                + detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getNomJoueur()
                + ":          "
                + Integer.toString(detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getScore(pos))
                + " Pts";

        if (pos != 0)
        {
            text = text + "   ->   ";
            if ((detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getActuPos(pos) - detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getPrecPos(pos)) > 0)
            {
                text = text     +  "-"
                                + Integer.toString(detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getActuPos(pos) - detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getPrecPos(pos))
                                + " Pl.";
            }

            else if ((detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getActuPos(pos) - detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getPrecPos(pos)) < 0)
            {
                text = text     + "+"
                                + Integer.toString((-1)*(detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getActuPos(pos) - detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getPrecPos(pos)))
                                + " Pl.";
            }

            else
            {
                text = text     + " = Pl.";
            }

        }

        return text;
    }
}