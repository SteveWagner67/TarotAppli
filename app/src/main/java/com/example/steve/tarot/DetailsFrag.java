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

                        final TextView pos1Txt = (TextView) dialogView2.findViewById(R.id.pos1);
                        final TextView name1Txt = (TextView) dialogView2.findViewById(R.id.name1);
                        final TextView score1Txt = (TextView) dialogView2.findViewById(R.id.score1);
                        final TextView f1Txt = (TextView) dialogView2.findViewById(R.id.f1);
                        final TextView place1Txt = (TextView) dialogView2.findViewById(R.id.place1);

                        final TextView pos2Txt = (TextView) dialogView2.findViewById(R.id.pos2);
                        final TextView name2Txt = (TextView) dialogView2.findViewById(R.id.name2);
                        final TextView score2Txt = (TextView) dialogView2.findViewById(R.id.score2);
                        final TextView f2Txt = (TextView) dialogView2.findViewById(R.id.f2);
                        final TextView place2Txt = (TextView) dialogView2.findViewById(R.id.place2);

                        final TextView pos3Txt = (TextView) dialogView2.findViewById(R.id.pos3);
                        final TextView name3Txt = (TextView) dialogView2.findViewById(R.id.name3);
                        final TextView score3Txt = (TextView) dialogView2.findViewById(R.id.score3);
                        final TextView f3Txt = (TextView) dialogView2.findViewById(R.id.f3);
                        final TextView place3Txt = (TextView) dialogView2.findViewById(R.id.place3);

                        final TextView pos4Txt = (TextView) dialogView2.findViewById(R.id.pos4);
                        final TextView name4Txt = (TextView) dialogView2.findViewById(R.id.name4);
                        final TextView score4Txt = (TextView) dialogView2.findViewById(R.id.score4);
                        final TextView f4Txt = (TextView) dialogView2.findViewById(R.id.f4);
                        final TextView place4Txt = (TextView) dialogView2.findViewById(R.id.place4);

                        final TextView pos5Txt = (TextView) dialogView2.findViewById(R.id.pos5);
                        final TextView name5Txt = (TextView) dialogView2.findViewById(R.id.name5);
                        final TextView score5Txt = (TextView) dialogView2.findViewById(R.id.score5);
                        final TextView f5Txt = (TextView) dialogView2.findViewById(R.id.f5);
                        final TextView place5Txt = (TextView) dialogView2.findViewById(R.id.place5);

                        final TextView pos6Txt = (TextView) dialogView2.findViewById(R.id.pos6);
                        final TextView name6Txt = (TextView) dialogView2.findViewById(R.id.name6);
                        final TextView score6Txt = (TextView) dialogView2.findViewById(R.id.score6);
                        final TextView f6Txt = (TextView) dialogView2.findViewById(R.id.f6);
                        final TextView place6Txt = (TextView) dialogView2.findViewById(R.id.place6);


                        switch (jeuArrayList.get(pos).getNbJoueur()) {

                            case 6:
                                pos6Txt.setVisibility(View.VISIBLE);
                                pos6Txt.setText(setPosition(6));

                                name6Txt.setVisibility(View.VISIBLE);
                                name6Txt.setText(setName(6));

                                score6Txt.setVisibility(View.VISIBLE);
                                score6Txt.setText(setScore(6));

                                if(pos != 0)
                                {
                                    f6Txt.setVisibility(View.VISIBLE);
                                }

                                place6Txt.setVisibility(View.VISIBLE);
                                place6Txt.setText(setPlacement(6));

                            case 5:
                                pos5Txt.setVisibility(View.VISIBLE);
                                pos5Txt.setText(setPosition(5));

                                name5Txt.setVisibility(View.VISIBLE);
                                name5Txt.setText(setName(5));

                                score5Txt.setVisibility(View.VISIBLE);
                                score5Txt.setText(setScore(5));

                                if(pos != 0)
                                {
                                    f5Txt.setVisibility(View.VISIBLE);
                                }

                                place5Txt.setVisibility(View.VISIBLE);
                                place5Txt.setText(setPlacement(5));

                            case 4:
                                pos4Txt.setVisibility(View.VISIBLE);
                                pos4Txt.setText(setPosition(4));

                                name4Txt.setVisibility(View.VISIBLE);
                                name4Txt.setText(setName(4));

                                score4Txt.setVisibility(View.VISIBLE);
                                score4Txt.setText(setScore(4));

                                if(pos != 0)
                                {
                                    f4Txt.setVisibility(View.VISIBLE);
                                }

                                place4Txt.setVisibility(View.VISIBLE);
                                place4Txt.setText(setPlacement(4));

                            case 3:
                                pos3Txt.setVisibility(View.VISIBLE);
                                pos3Txt.setText(setPosition(3));

                                name3Txt.setVisibility(View.VISIBLE);
                                name3Txt.setText(setName(3));

                                score3Txt.setVisibility(View.VISIBLE);
                                score3Txt.setText(setScore(3));

                                if(pos != 0)
                                {
                                    f3Txt.setVisibility(View.VISIBLE);
                                }

                                place3Txt.setVisibility(View.VISIBLE);
                                place3Txt.setText(setPlacement(3));


                                pos2Txt.setVisibility(View.VISIBLE);
                                pos2Txt.setText(setPosition(2));

                                name2Txt.setVisibility(View.VISIBLE);
                                name2Txt.setText(setName(2));

                                score2Txt.setVisibility(View.VISIBLE);
                                score2Txt.setText(setScore(2));

                                if(pos != 0)
                                {
                                    f2Txt.setVisibility(View.VISIBLE);
                                }

                                place2Txt.setVisibility(View.VISIBLE);
                                place2Txt.setText(setPlacement(2));


                                pos1Txt.setVisibility(View.VISIBLE);
                                pos1Txt.setText(setPosition(1));

                                name1Txt.setVisibility(View.VISIBLE);
                                name1Txt.setText(setName(1));

                                score1Txt.setVisibility(View.VISIBLE);
                                score1Txt.setText(setScore(1));

                                if(pos != 0)
                                {
                                    f1Txt.setVisibility(View.VISIBLE);
                                }

                                place1Txt.setVisibility(View.VISIBLE);
                                place1Txt.setText(setPlacement(1));
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


    private String setPosition(int placement)
    {
        String text = "";

        text = Integer.toString(placement)
                + "-";

        return text;
    }

    private String setName(int placement)
    {
        String text = "";

        text = detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getNomJoueur()
                + ":";

        return text;
    }

    private String setScore(int placement)
    {
        String text = "";

        text = Integer.toString(detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getScore(pos))
                + " Pts";

        return text;
    }

    private String setPlacement(int placement)
    {
        String text = "";

        if (pos != 0)
        {
            if ((detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getActuPos(pos) - detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getPrecPos(pos)) > 0)
            {
                text = "-"
                        + Integer.toString(detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getActuPos(pos) - detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getPrecPos(pos))
                        + " Pl.";
            }

            else if ((detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getActuPos(pos) - detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getPrecPos(pos)) < 0)
            {
                text = "+"
                        + Integer.toString((-1)*(detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getActuPos(pos) - detailsArrayList.get(pos).getClassmntPlayersList().get(placement-1).getPrecPos(pos)))
                        + " Pl.";
            }

            else
            {
                text = "= Pl.";
            }

        }

        return text;
    }
}