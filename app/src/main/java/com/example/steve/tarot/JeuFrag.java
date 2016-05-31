package com.example.steve.tarot;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class JeuFrag extends Fragment {

    private View view;
    private Spinner preneurSpinn, enchereSpinn, annonceSpinn, joueurSpinn, annonce2Spinn, joueur2Spinn, calculScoreSpinn, nbBoutSpinn, preneurForPersonneSpinn, associeSpinn;
    private TextView donneurTitle, donneurTxt, preneurTxt, enchereTxt, annonceTitle, joueurTitle, annonceSelected, joueurSelected, annonce2Selected, joueur2Selected, calculScoreTitle, nbBoutTitle, scoreTitle, nbBoutTxt, calculScoreTxt, scoreTxt, finishTxt, preneurForPersonneTitle, preneurForPersonneTxt, associeTitle, associeTxt;
    private ArrayAdapter<String> adaptPlayerList, adaptEnchere, adaptAnnonce, adaptJoueur, adaptAnnoncList, adapt2Annonce, adapt2Joueur, adaptCalculScore, adaptnbBout, adaptPreneurForPersonne, adaptAssocie;
    private int pos, firstPos, nbJoueur;
    private Button valideFirstPartBtn, ajouter, ajouter2, terminer, terminer2, calculerBtn, finishBtn, valideAssocieBtn;

    private EditText scoreEdit;

    private Jeu jeu;

    private int part;

    private int nbBout;
    private double score;

    private boolean preneurPersonne;

    private String enchereTab[] = {"Petite", "Pouce", "Garde", "Garde sans chien", "Garde contre chien"};
    private String annonce4Tab[] = {"-", "Simple misère", "Double misère", "Poignet (10)", "Poignet (13)", "Poignet (15)"};
    private String annonce5Tab[] = {"-", "Simple misère", "Double misère", "Poignet (8)", "Poignet (10)", "Poignet (13)"};
    private String scorePartieTab[] = {"Preneur", "Défenseurs"};
    private String nbBoutTab[] = {"   0    ", "   1   ", "   2   ", "   3  "};

    private String annonceTxt, joueurTxt, annonce2Txt, joueur2Txt;

    private setPreneur mCallbackPreneur;
    private setEnchere mCallbackEnchere;
    private setAnnonceList mCallBackAnnonceList;
    private setJoueurList mCallBackJoueurList;
    private setPlayers mCallbackPlayers;

    private ArrayList<String> annonceArrayList, joueurArrayList, joueurForScoreArrayList;

    private ListView annonceListView;

    private Player player1, player2, player3, player4, player5, player6, player7;

    private ArrayList<Player> playersArrayList;

    private String preneurName;

    private ArrayList<Player> playerList;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore last state for checked position.

            switch (savedInstanceState.getInt("Part")) {
                case 0:
                    preneurSpinn.setVisibility(View.VISIBLE);
                    preneurTxt.setVisibility(View.INVISIBLE);

                    enchereSpinn.setVisibility(View.VISIBLE);
                    enchereTxt.setVisibility(View.INVISIBLE);

                    valideFirstPartBtn.setVisibility(View.VISIBLE);

                    annonceTitle.setVisibility(View.INVISIBLE);
                    annonceSpinn.setVisibility(View.INVISIBLE);

                    joueurTitle.setVisibility(View.INVISIBLE);
                    joueurSpinn.setVisibility(View.INVISIBLE);

                    ajouter.setVisibility(View.INVISIBLE);
                    terminer.setVisibility(View.INVISIBLE);

                    annonceSelected.setVisibility(View.INVISIBLE);


                    joueurSelected.setVisibility(View.INVISIBLE);

                    annonce2Spinn.setVisibility(View.INVISIBLE);
                    joueur2Spinn.setVisibility(View.INVISIBLE);

                    ajouter2.setVisibility(View.INVISIBLE);
                    terminer2.setVisibility(View.INVISIBLE);

                    annonce2Selected.setVisibility(View.INVISIBLE);
                    joueur2Selected.setVisibility(View.INVISIBLE);

                    calculScoreTitle.setVisibility(View.INVISIBLE);
                    calculScoreSpinn.setVisibility(View.INVISIBLE);
                    calculScoreTxt.setVisibility(View.INVISIBLE);
                    nbBoutTitle.setVisibility(View.INVISIBLE);
                    nbBoutSpinn.setVisibility(View.INVISIBLE);
                    nbBoutTxt.setVisibility(View.INVISIBLE);
                    scoreTitle.setVisibility(View.INVISIBLE);
                    scoreEdit.setVisibility(View.INVISIBLE);
                    scoreTxt.setVisibility(View.INVISIBLE);
                    calculerBtn.setVisibility(View.INVISIBLE);

                    finishTxt.setVisibility(View.INVISIBLE);
                    finishBtn.setVisibility(View.INVISIBLE);


                    part = 0;

                    break;

                case 1:

                    preneurSpinn.setVisibility(View.INVISIBLE);
                    preneurTxt.setVisibility(View.VISIBLE);

                    enchereSpinn.setVisibility(View.INVISIBLE);
                    enchereTxt.setVisibility(View.VISIBLE);

                    valideFirstPartBtn.setVisibility(View.INVISIBLE);

                    annonceTitle.setVisibility(View.VISIBLE);
                    annonceSpinn.setVisibility(View.VISIBLE);
                    joueurTitle.setVisibility(View.VISIBLE);
                    joueurSpinn.setVisibility(View.VISIBLE);
                    ajouter.setVisibility(View.VISIBLE);
                    terminer.setVisibility(View.VISIBLE);

                    annonceSelected.setVisibility(View.INVISIBLE);
                    annonce2Spinn.setVisibility(View.INVISIBLE);

                    joueurSelected.setVisibility(View.INVISIBLE);
                    joueur2Spinn.setVisibility(View.INVISIBLE);

                    ajouter2.setVisibility(View.INVISIBLE);
                    terminer2.setVisibility(View.INVISIBLE);

                    annonce2Selected.setVisibility(View.INVISIBLE);
                    joueur2Selected.setVisibility(View.INVISIBLE);

                    calculScoreTitle.setVisibility(View.INVISIBLE);
                    calculScoreSpinn.setVisibility(View.INVISIBLE);
                    nbBoutTitle.setVisibility(View.INVISIBLE);
                    nbBoutSpinn.setVisibility(View.INVISIBLE);
                    scoreTitle.setVisibility(View.INVISIBLE);
                    scoreEdit.setVisibility(View.INVISIBLE);
                    calculerBtn.setVisibility(View.INVISIBLE);

                    finishTxt.setVisibility(View.INVISIBLE);
                    finishBtn.setVisibility(View.INVISIBLE);

                    part = 1;

                    break;

                case 2:

                    preneurSpinn.setVisibility(View.INVISIBLE);
                    preneurTxt.setVisibility(View.VISIBLE);

                    enchereSpinn.setVisibility(View.INVISIBLE);
                    enchereTxt.setVisibility(View.VISIBLE);

                    valideFirstPartBtn.setVisibility(View.INVISIBLE);

                    annonceTitle.setVisibility(View.VISIBLE);
                    joueurTitle.setVisibility(View.VISIBLE);

                    annonceSelected.setVisibility(View.INVISIBLE);
                    annonce2Spinn.setVisibility(View.INVISIBLE);


                    annonceSelected.setVisibility(View.VISIBLE);
                    annonceSpinn.setVisibility(View.INVISIBLE);
                    annonce2Spinn.setVisibility(View.VISIBLE);

                    joueurSelected.setVisibility(View.VISIBLE);
                    joueurSpinn.setVisibility(View.INVISIBLE);
                    joueur2Spinn.setVisibility(View.VISIBLE);

                    ajouter.setVisibility(View.INVISIBLE);
                    terminer.setVisibility(View.INVISIBLE);

                    ajouter2.setVisibility(View.VISIBLE);
                    terminer2.setVisibility(View.VISIBLE);

                    annonce2Selected.setVisibility(View.INVISIBLE);
                    joueur2Selected.setVisibility(View.INVISIBLE);

                    calculScoreTitle.setVisibility(View.INVISIBLE);
                    calculScoreSpinn.setVisibility(View.INVISIBLE);
                    nbBoutTitle.setVisibility(View.INVISIBLE);
                    nbBoutSpinn.setVisibility(View.INVISIBLE);
                    scoreTitle.setVisibility(View.INVISIBLE);
                    scoreEdit.setVisibility(View.INVISIBLE);
                    calculerBtn.setVisibility(View.INVISIBLE);

                    finishTxt.setVisibility(View.INVISIBLE);
                    finishBtn.setVisibility(View.INVISIBLE);

                    part = 2;

                    break;

                case 3:

                    preneurSpinn.setVisibility(View.INVISIBLE);
                    preneurTxt.setVisibility(View.VISIBLE);

                    enchereSpinn.setVisibility(View.INVISIBLE);
                    enchereTxt.setVisibility(View.VISIBLE);

                    valideFirstPartBtn.setVisibility(View.INVISIBLE);

                    annonceTitle.setVisibility(View.VISIBLE);
                    joueurTitle.setVisibility(View.VISIBLE);

                    annonceSelected.setVisibility(View.INVISIBLE);
                    annonce2Spinn.setVisibility(View.INVISIBLE);


                    annonceSelected.setVisibility(View.VISIBLE);
                    annonceSpinn.setVisibility(View.INVISIBLE);
                    annonce2Spinn.setVisibility(View.INVISIBLE);

                    joueurSelected.setVisibility(View.VISIBLE);
                    joueurSpinn.setVisibility(View.INVISIBLE);
                    joueur2Spinn.setVisibility(View.INVISIBLE);

                    ajouter.setVisibility(View.INVISIBLE);
                    terminer.setVisibility(View.INVISIBLE);

                    ajouter2.setVisibility(View.INVISIBLE);
                    terminer2.setVisibility(View.INVISIBLE);

                    annonce2Selected.setVisibility(View.INVISIBLE);
                    joueur2Selected.setVisibility(View.INVISIBLE);

                    calculScoreTitle.setVisibility(View.VISIBLE);
                    calculScoreSpinn.setVisibility(View.VISIBLE);
                    nbBoutTitle.setVisibility(View.VISIBLE);
                    nbBoutSpinn.setVisibility(View.VISIBLE);
                    scoreTitle.setVisibility(View.VISIBLE);
                    scoreEdit.setVisibility(View.VISIBLE);
                    calculerBtn.setVisibility(View.VISIBLE);

                    finishTxt.setVisibility(View.INVISIBLE);
                    finishBtn.setVisibility(View.INVISIBLE);

                    part = 3;

                    break;


                case 5:

                    preneurSpinn.setVisibility(View.INVISIBLE);
                    preneurTxt.setVisibility(View.VISIBLE);

                    enchereSpinn.setVisibility(View.INVISIBLE);
                    enchereTxt.setVisibility(View.VISIBLE);

                    valideFirstPartBtn.setVisibility(View.INVISIBLE);

                    annonceTitle.setVisibility(View.VISIBLE);
                    joueurTitle.setVisibility(View.VISIBLE);

                    annonceSelected.setVisibility(View.INVISIBLE);
                    annonce2Spinn.setVisibility(View.INVISIBLE);


                    annonceSelected.setVisibility(View.VISIBLE);
                    annonceSpinn.setVisibility(View.INVISIBLE);
                    annonce2Spinn.setVisibility(View.INVISIBLE);

                    joueurSelected.setVisibility(View.VISIBLE);
                    joueurSpinn.setVisibility(View.INVISIBLE);
                    joueur2Spinn.setVisibility(View.INVISIBLE);

                    ajouter.setVisibility(View.INVISIBLE);
                    terminer.setVisibility(View.INVISIBLE);

                    ajouter2.setVisibility(View.INVISIBLE);
                    terminer2.setVisibility(View.INVISIBLE);

                    annonce2Selected.setVisibility(View.VISIBLE);
                    joueur2Selected.setVisibility(View.VISIBLE);

                    calculScoreTitle.setVisibility(View.VISIBLE);
                    calculScoreSpinn.setVisibility(View.VISIBLE);
                    nbBoutTitle.setVisibility(View.VISIBLE);
                    nbBoutSpinn.setVisibility(View.VISIBLE);
                    scoreTitle.setVisibility(View.VISIBLE);
                    scoreEdit.setVisibility(View.VISIBLE);
                    calculerBtn.setVisibility(View.VISIBLE);

                    finishTxt.setVisibility(View.INVISIBLE);
                    finishBtn.setVisibility(View.INVISIBLE);

                    part = 5;

                    break;
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_jeu4, container, false);

        // Attribution
        preneurSpinn = (Spinner) view.findViewById(R.id.preneurSpinn);
        preneurTxt = (TextView) view.findViewById(R.id.preneurText);

        donneurTitle = (TextView) view.findViewById(R.id.donneurTitle);
        donneurTxt = (TextView) view.findViewById(R.id.donneurSpinn);
        enchereSpinn = (Spinner) view.findViewById(R.id.enchereSpinn);
        enchereTxt = (TextView) view.findViewById(R.id.enchereText);

        annonceTitle = (TextView) view.findViewById(R.id.annonceTxt);
        annonceSpinn = (Spinner) view.findViewById(R.id.annonceSpinn);
        joueurTitle = (TextView) view.findViewById(R.id.joueurTxt);
        joueurSpinn = (Spinner) view.findViewById(R.id.joueurSpinn);

        annonce2Spinn = (Spinner) view.findViewById(R.id.annonceSpinn2);
        joueur2Spinn = (Spinner) view.findViewById(R.id.joueurSpinn2);

        annonce2Selected = (TextView) view.findViewById(R.id.selectedAnnonce2);
        joueur2Selected = (TextView) view.findViewById(R.id.selectedJoueur2);


        valideFirstPartBtn = (Button) view.findViewById(R.id.valid);
        ajouter = (Button) view.findViewById(R.id.ajouter);
        ajouter2 = (Button) view.findViewById(R.id.ajouter2);
        terminer = (Button) view.findViewById(R.id.terminer);
        terminer2 = (Button) view.findViewById(R.id.terminer2);

        annonceListView = (ListView) view.findViewById(R.id.annonceList);
        annonceSelected = (TextView) view.findViewById(R.id.annonceSelected);

        joueurSelected = (TextView) view.findViewById(R.id.joueurSelected);

        associeTitle = (TextView) view.findViewById(R.id.associeTitle);
        associeSpinn = (Spinner) view.findViewById(R.id.associeSpinn);
        associeTxt = (TextView) view.findViewById(R.id.associeTxt);

        valideAssocieBtn = (Button) view.findViewById(R.id.valideAssocie);

        preneurForPersonneTitle = (TextView) view.findViewById(R.id.preneurForPersonneTitle);
        preneurForPersonneSpinn = (Spinner) view.findViewById(R.id.preneurForPersonneSpinn);
        preneurForPersonneTxt = (TextView) view.findViewById(R.id.preneurForPersonneTxt);

        calculScoreTitle = (TextView) view.findViewById(R.id.calculScoreTitle);
        calculScoreSpinn = (Spinner) view.findViewById(R.id.calculScoreSpinn);
        calculScoreTxt = (TextView) view.findViewById(R.id.calculScoreTxt);

        nbBoutTitle = (TextView) view.findViewById(R.id.nbBoutTitle);
        nbBoutSpinn = (Spinner) view.findViewById(R.id.nbBoutSpinn);
        nbBoutTxt = (TextView) view.findViewById(R.id.nbBoutTxt);

        scoreTitle = (TextView) view.findViewById(R.id.scoreTitle);
        scoreEdit = (EditText) view.findViewById(R.id.scoreEdit);
        scoreTxt = (TextView) view.findViewById(R.id.scoreTxt);

        calculerBtn = (Button) view.findViewById(R.id.calcul);

        finishTxt = (TextView) view.findViewById(R.id.finishTxt);
        finishBtn = (Button) view.findViewById(R.id.finishBtn);

        annonceArrayList = new ArrayList<>();
        joueurArrayList = new ArrayList<>();
        //joueurForScoreArraylist = new ArrayList<>();

        playersArrayList = new ArrayList<>();

        pos = getArguments().getInt("Distributeur");

        jeu = new Jeu();

        //joueurForScoreArraylist = getArguments().getStringArrayList("List");

        jeu.setPlayerList(getArguments().getStringArrayList("List"));

        nbJoueur = jeu.getNbJoueur();

        switch (nbJoueur) {
            case 7:
                player7 = new Player();

            case 6:
                player6 = new Player();

            case 5:
                player5 = new Player();

            case 4:
                player4 = new Player();

            case 3:
                player3 = new Player();
                player2 = new Player();
                player1 = new Player();

                break;
        }

        if(nbJoueur == 6)
        {
            donneurTitle.setText("Donneur / Mort: ");
        }

        donneurTxt.setText(jeu.getPlayerName(pos));
        jeu.setDonneur(jeu.getPlayerName(pos));

        adaptPlayerList = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, jeu.getPlayerList());
        adaptEnchere = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, enchereTab);

        if(nbJoueur >= 5)
        {
            adaptAnnonce = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, annonce5Tab);
        }

        else
        {
            adaptAnnonce = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, annonce4Tab);
        }

        adaptJoueur = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, jeu.getPlayerList());
        adaptAnnoncList = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, annonceArrayList);

        if(nbJoueur >= 5)
        {
            adapt2Annonce = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, annonce5Tab);
        }

        else
        {
            adapt2Annonce = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, annonce4Tab);
        }

        adapt2Joueur = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, jeu.getPlayerList());

        //adaptPreneurForPersonne = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, joueurForScoreArraylist);
        adaptPreneurForPersonne = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, jeu.getPlayerList());

        //adaptAssocie = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, joueurForScoreArraylist);
        adaptAssocie = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, jeu.getPlayerList());

        adaptCalculScore = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, scorePartieTab);
        adaptnbBout = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, nbBoutTab);

        adaptPlayerList.add("Personne");
        //joueurForScoreArraylist.add("NTM");

        if(nbJoueur == 6)
        {
            // Remove the donneur / mort
            adaptPlayerList.remove(adaptPlayerList.getItem(pos).toString());
            /*boolean donneurRemoved = false;
            int i = 0;
            while(donneurRemoved == false)
            {
                if(jeu.getPlayerName(pos).equals(adaptPlayerList.getItem(i).toString()))
                {
                    adaptPlayerList.remove(adaptPlayerList.getItem(i).toString());
                    joueurForScoreArraylist.remove(i);

                    donneurRemoved = true;
                }

                i++;
            }*/

    }




        adaptPlayerList.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        adaptEnchere.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        adaptAnnonce.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        adaptJoueur.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        adapt2Annonce.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        adapt2Joueur.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        adaptAssocie.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        adaptPreneurForPersonne.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        adaptCalculScore.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        adaptnbBout.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        preneurSpinn.setAdapter(adaptPlayerList);
        preneurSpinn.setSelection(jeu.getPlayerListSize() - 1);

        enchereSpinn.setAdapter(adaptEnchere);

        annonceSpinn.setAdapter(adaptAnnonce);
        joueurSpinn.setAdapter(adaptJoueur);
        joueurSpinn.setSelection(jeu.getPlayerListSize() - 1);

        annonce2Spinn.setAdapter(adapt2Annonce);
        joueur2Spinn.setAdapter(adapt2Joueur);
        joueur2Spinn.setSelection(jeu.getPlayerListSize() - 1);

        preneurForPersonneSpinn.setAdapter(adaptPreneurForPersonne);
        preneurForPersonneSpinn.setSelection(jeu.getPlayerListSize() - 1);

        associeSpinn.setAdapter(adaptAssocie);
        associeSpinn.setSelection(jeu.getPlayerListSize() - 1);

        calculScoreSpinn.setAdapter(adaptCalculScore);
        nbBoutSpinn.setAdapter(adaptnbBout);

        preneurSpinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                preneurTxt.setText(parentView.getItemAtPosition(position).toString());

                if (parentView.getItemAtPosition(position).toString().equals("Personne")) {
                    enchereSpinn.setSelection(2);
                    enchereSpinn.setVisibility(View.INVISIBLE);
                    enchereTxt.setVisibility(View.VISIBLE);
                    preneurPersonne = true;
                } else {
                    // First part of the game
                    if (part == 0) {
                        enchereSpinn.setSelection(0);
                        enchereSpinn.setVisibility(View.VISIBLE);
                        enchereTxt.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        enchereSpinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                enchereTxt.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        valideFirstPartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                preneurSpinn.setVisibility(View.INVISIBLE);
                preneurTxt.setVisibility(View.VISIBLE);

                enchereSpinn.setVisibility(View.INVISIBLE);
                enchereTxt.setVisibility(View.VISIBLE);

                valideFirstPartBtn.setVisibility(View.INVISIBLE);

                annonceTitle.setVisibility(View.VISIBLE);
                annonceSpinn.setVisibility(View.VISIBLE);
                joueurTitle.setVisibility(View.VISIBLE);
                joueurSpinn.setVisibility(View.VISIBLE);
                ajouter.setVisibility(View.VISIBLE);
                terminer.setVisibility(View.VISIBLE);

                part = 1;
                mCallbackPreneur.setPreneur(preneurTxt.getText().toString());

                if (preneurTxt.getText().toString().equals("Personne")) {
                    mCallbackEnchere.setEnchere("Garde");
                } else {
                    mCallbackEnchere.setEnchere(enchereTxt.getText().toString());
                }

            }
        });


        joueurSpinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                joueurTxt = parent.getItemAtPosition(position).toString();
                joueurSelected.setText(joueurTxt);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        annonceSpinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                annonceTxt = parent.getItemAtPosition(position).toString();
                annonceSelected.setText(annonceTxt);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (joueurTxt.equals("Personne")) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                    adb.setTitle("Information");
                    adb.setMessage("Aucun joueur sélectionné");
                    adb.setPositiveButton("OK", null);
                    adb.show();
                } else {
                    if (annonceTxt.equals("-")) {
                        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                        adb.setTitle("Information");
                        adb.setMessage("Aucune annonce sélectionnée");
                        adb.setPositiveButton("OK", null);
                        adb.show();
                    } else {

                        // Reset the annonce joueur Arraylist
                        annonceArrayList = new ArrayList<String>();
                        joueurArrayList = new ArrayList<String>();

                        annonceArrayList.add(0, annonceTxt);
                        joueurArrayList.add(0, joueurTxt);

                        annonceSelected.setVisibility(View.VISIBLE);
                        annonceSpinn.setVisibility(View.INVISIBLE);
                        annonce2Spinn.setVisibility(View.VISIBLE);

                        joueurSelected.setVisibility(View.VISIBLE);
                        joueurSpinn.setVisibility(View.INVISIBLE);
                        joueur2Spinn.setVisibility(View.VISIBLE);

                        ajouter.setVisibility(View.INVISIBLE);
                        terminer.setVisibility(View.INVISIBLE);

                        ajouter2.setVisibility(View.VISIBLE);
                        terminer2.setVisibility(View.VISIBLE);

                        part = 2;

                    }
                }

            }
        });


        terminer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (annonceArrayList.size() == 0) {
                    annonceArrayList.add(0, "-");
                }

                if (joueurArrayList.size() == 0) {
                    joueurArrayList.add(0, "-");
                }

                annonceSelected.setText("-");
                joueurSelected.setText("-");

                mCallBackAnnonceList.setAnnonceList(annonceArrayList);
                mCallBackJoueurList.setJoueurList(joueurArrayList);

                annonceSelected.setVisibility(View.VISIBLE);
                annonceSpinn.setVisibility(View.INVISIBLE);

                joueurSelected.setVisibility(View.VISIBLE);
                joueurSpinn.setVisibility(View.INVISIBLE);

                ajouter.setVisibility(View.INVISIBLE);
                terminer.setVisibility(View.INVISIBLE);


                if((nbJoueur >= 5) && (!preneurTxt.getText().toString().equals("Personne")))
                {
                    associeTitle.setVisibility(View.VISIBLE);
                    associeSpinn.setVisibility(View.VISIBLE);
                    valideAssocieBtn.setVisibility(View.VISIBLE);
                }

                else if(preneurTxt.getText().toString().equals("Personne"))
                {
                    preneurForPersonneTitle.setVisibility(View.VISIBLE);
                    preneurForPersonneSpinn.setVisibility(View.VISIBLE);
                    calculerBtn.setVisibility(View.VISIBLE);
                }
                
                else
                {
                    calculScoreTitle.setVisibility(View.VISIBLE);
                    calculScoreSpinn.setVisibility(View.VISIBLE);
                    nbBoutTitle.setVisibility(View.VISIBLE);
                    nbBoutSpinn.setVisibility(View.VISIBLE);
                    scoreTitle.setVisibility(View.VISIBLE);
                    scoreEdit.setVisibility(View.VISIBLE);
                    calculerBtn.setVisibility(View.VISIBLE);
                }

                
                part = 3;
            }
        });

        annonce2Spinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                annonce2Txt = parent.getItemAtPosition(position).toString();
                annonce2Selected.setText(annonce2Txt);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        joueur2Spinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                joueur2Txt = parent.getItemAtPosition(position).toString();
                joueur2Selected.setText(joueur2Txt);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ajouter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (joueur2Txt.equals("Personne")) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                    adb.setTitle("Information");
                    adb.setMessage("Aucun joueur sélectionné");
                    adb.setPositiveButton("OK", null);
                    adb.show();
                } else {
                    if (annonce2Txt.equals("-")) {
                        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                        adb.setTitle("Information");
                        adb.setMessage("Aucune annonce sélectionnée");
                        adb.setPositiveButton("OK", null);
                        adb.show();
                    } else {
                        annonceArrayList.add(1, annonce2Txt);
                        joueurArrayList.add(1, joueur2Txt);

                        mCallBackAnnonceList.setAnnonceList(annonceArrayList);
                        mCallBackJoueurList.setJoueurList(joueurArrayList);

                        annonce2Selected.setVisibility(View.VISIBLE);
                        annonce2Spinn.setVisibility(View.INVISIBLE);

                        joueur2Selected.setVisibility(View.VISIBLE);
                        joueur2Spinn.setVisibility(View.INVISIBLE);

                        ajouter2.setVisibility(View.INVISIBLE);
                        terminer2.setVisibility(View.INVISIBLE);

                        if((nbJoueur >= 5) && (!preneurTxt.getText().toString().equals("Personne")))
                        {
                            associeTitle.setVisibility(View.VISIBLE);
                            associeSpinn.setVisibility(View.VISIBLE);
                            valideAssocieBtn.setVisibility(View.VISIBLE);
                        }

                        else if(preneurTxt.getText().toString().equals("Personne"))
                        {
                            preneurForPersonneTitle.setVisibility(View.VISIBLE);
                            preneurForPersonneSpinn.setVisibility(View.VISIBLE);
                            calculerBtn.setVisibility(View.VISIBLE);
                        }

                        else
                        {
                            calculScoreTitle.setVisibility(View.VISIBLE);
                            calculScoreSpinn.setVisibility(View.VISIBLE);
                            nbBoutTitle.setVisibility(View.VISIBLE);
                            nbBoutSpinn.setVisibility(View.VISIBLE);
                            scoreTitle.setVisibility(View.VISIBLE);
                            scoreEdit.setVisibility(View.VISIBLE);
                            calculerBtn.setVisibility(View.VISIBLE);
                        }

                        part = 5;
                    }

                }
            }
        });


        terminer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCallBackAnnonceList.setAnnonceList(annonceArrayList);
                mCallBackJoueurList.setJoueurList(joueurArrayList);

                annonceSelected.setVisibility(View.VISIBLE);
                annonce2Spinn.setVisibility(View.INVISIBLE);

                joueurSelected.setVisibility(View.VISIBLE);
                joueur2Spinn.setVisibility(View.INVISIBLE);

                ajouter2.setVisibility(View.INVISIBLE);
                terminer2.setVisibility(View.INVISIBLE);

                if((nbJoueur >= 5) && (!preneurTxt.getText().toString().equals("Personne")))
                {
                    associeTitle.setVisibility(View.VISIBLE);
                    associeSpinn.setVisibility(View.VISIBLE);
                    valideAssocieBtn.setVisibility(View.VISIBLE);
                }

                else if(preneurTxt.getText().toString().equals("Personne"))
                {
                    preneurForPersonneTitle.setVisibility(View.VISIBLE);
                    preneurForPersonneSpinn.setVisibility(View.VISIBLE);
                    calculerBtn.setVisibility(View.VISIBLE);
                }

                else
                {
                    calculScoreTitle.setVisibility(View.VISIBLE);
                    calculScoreSpinn.setVisibility(View.VISIBLE);
                    nbBoutTitle.setVisibility(View.VISIBLE);
                    nbBoutSpinn.setVisibility(View.VISIBLE);
                    scoreTitle.setVisibility(View.VISIBLE);
                    scoreEdit.setVisibility(View.VISIBLE);
                    calculerBtn.setVisibility(View.VISIBLE);
                }

                part = 3;


            }
        });
        
        associeSpinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                associeTxt.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        valideAssocieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (associeTxt.getText().toString().equals("Personne"))
                {
                    AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                    adb.setTitle("Information");
                    adb.setMessage("Veuillez choisir l'associé(e) (Autre que personne)");
                    adb.setPositiveButton("OK", null);
                    adb.show();
                }

                else
                {
                    associeSpinn.setVisibility(View.INVISIBLE);
                    valideAssocieBtn.setVisibility(View.INVISIBLE);

                    associeTxt.setVisibility(View.VISIBLE);
                    calculScoreTitle.setVisibility(View.VISIBLE);
                    calculScoreSpinn.setVisibility(View.VISIBLE);
                    nbBoutTitle.setVisibility(View.VISIBLE);
                    nbBoutSpinn.setVisibility(View.VISIBLE);
                    scoreTitle.setVisibility(View.VISIBLE);
                    scoreEdit.setVisibility(View.VISIBLE);
                    calculerBtn.setVisibility(View.VISIBLE);
                }

            }
        });


        preneurForPersonneSpinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                preneurForPersonneTxt.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calculScoreSpinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calculScoreTxt.setText(parent.getItemAtPosition(position).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        nbBoutSpinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                switch(parent.getItemAtPosition(position).toString())
                {
                    case "   0    ":
                        nbBoutTxt.setText("0");
                        break;

                    case "   1   ":
                        nbBoutTxt.setText("1");
                        break;

                    case "   2   ":
                        nbBoutTxt.setText("2");
                        break;

                    case "   3  ":
                        nbBoutTxt.setText("3");
                        break;
                }

                try {
                    nbBout = Integer.valueOf(nbBoutTxt.getText().toString());
                } catch (NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        scoreEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                calculScoreTitle.setVisibility(View.INVISIBLE);
                calculScoreSpinn.setVisibility(View.INVISIBLE);
                nbBoutTitle.setVisibility(View.INVISIBLE);
                nbBoutSpinn.setVisibility(View.INVISIBLE);
                calculerBtn.setVisibility(View.INVISIBLE);

                scoreTxt.setVisibility(View.VISIBLE);

                scoreTxt.setText("0");
                scoreEdit.setText("");

                return false;
            }
        });

        scoreEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    double i = 0;
                    boolean isEqual = false;

                    if ((scoreEdit.getText().length() == 2) || scoreEdit.getText().length() == 1) {
                        if ((scoreEdit.getText().toString() == "-") ||
                                (scoreEdit.getText().toString() == ",") ||
                                (scoreEdit.getText().toString() == "_") ||
                                (scoreEdit.getText().toString() == ".")
                                ) {
                            /* Do nothing */
                        } else {
                            scoreEdit.setText(scoreEdit.getText().toString() + ".0");
                        }

                    }
                    while (i <= 91) {

                        if (scoreEdit.getText().toString().equals(Double.toString(i))) {
                            isEqual = true;
                            scoreTxt.setText(scoreEdit.getText().toString());
                        }

                        i += 0.5;
                    }

                    if (isEqual == false) {
                        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                        adb.setTitle("Information");
                        adb.setMessage("Les valeurs peuvent être des demi" + "\r\n" + "Exemple: 10.5" + "\r\n\r\n" + "Les valeurs entières doivent être écrites de la manière suivante:" + "\r\n" + "26.0 pour 26" + "\r\n\r\n" + "Les valeurs doivent être comprises entre 0 et 91" + "\r\n\r\n" + "Les autres valeurs ne sont pas autorisées" + "\r\n" + "Exemple: 20.1, 20.2, 20.3, 20.4, 20.6, 20.7, 20.8, 20.9");
                        adb.setPositiveButton("OK", null);
                        adb.show();
                    } else {
                        try {
                            score = Double.parseDouble(scoreTxt.getText().toString());
                        } catch (NumberFormatException nfe) {
                            System.out.println("Could not parse " + nfe);
                        }

                    }

                    calculScoreTitle.setVisibility(View.VISIBLE);
                    calculScoreSpinn.setVisibility(View.VISIBLE);
                    nbBoutTitle.setVisibility(View.VISIBLE);
                    nbBoutSpinn.setVisibility(View.VISIBLE);
                    calculerBtn.setVisibility(View.VISIBLE);

                    scoreTxt.setVisibility(View.INVISIBLE);

                    return true;
                }

                scoreTxt.setText(scoreEdit.getText().toString());

                return false;
            }
        });


        calculerBtn.setOnClickListener(new View.OnClickListener() {

            boolean calculer = false;

            @Override
            public void onClick(View v) {

                if(preneurTxt.getText().toString().equals("Personne"))
                {
                    if (preneurForPersonneTxt.getText().toString().equals("Personne"))
                    {
                        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                        adb.setTitle("Information");
                        adb.setMessage("Veuillez choisir la personne ayant le score le plus élevé (Autre que personne)");
                        adb.setPositiveButton("OK", null);
                        adb.show();
                    }

                    else
                    {
                        jeu.setPreneur(preneurForPersonneTxt.getText().toString());

                        if(nbJoueur >= 5)
                        {
                            jeu.setAssocie(preneurForPersonneTxt.getText().toString());
                        }

                        //nbBout isn't set (default: 3) so we set the score to have something loose to 0
                        score=55.0;

                        calculer = true;
                    }

                }

                else
                {
                    jeu.setPreneur(preneurTxt.getText().toString());

                    if(nbJoueur >= 5)
                    {
                        jeu.setAssocie(associeTxt.getText().toString());
                    }

                    calculer = true;
                }

                if(calculer == true)
                {
                    jeu.setEnchere(enchereTxt.getText().toString());
                    jeu.setNbBout(nbBout);
                    jeu.setScore(score);

                    double value;
                    int roundValue;

                    jeu.setCalculScoreDe(calculScoreTxt.getText().toString());

                    value = jeu.calculScore();
                    roundValue = jeu.getArrondi(value);

                    playersArrayList.clear();

                    switch (jeu.getNbJoueur()) {
                        case 7:

                            player7 = jeu.getPlayer7();
                            if (player7.getNomJoueur().equals(preneurTxt.getText().toString())) {
                                preneurName = player7.getNomJoueur();
                            }

                        case 6:

                            player6 = jeu.getPlayer6();
                            if (player6.getNomJoueur().equals(preneurTxt.getText().toString())) {
                                preneurName = player6.getNomJoueur();
                            }

                        case 5:
                            player5 = jeu.getPlayer5();
                            if (player5.getNomJoueur().equals(preneurTxt.getText().toString())) {
                                preneurName = player5.getNomJoueur();
                            }

                        case 4:
                            player4 = jeu.getPlayer4();
                            if (player4.getNomJoueur().equals(preneurTxt.getText().toString())) {
                                preneurName = player4.getNomJoueur();
                            }

                        case 3:

                            player3 = jeu.getPlayer3();
                            if (player3.getNomJoueur().equals(preneurTxt.getText().toString())) {
                                preneurName = player3.getNomJoueur();
                            }

                            player2 = jeu.getPlayer2();
                            if (player2.getNomJoueur().equals(preneurTxt.getText().toString())) {
                                preneurName = player2.getNomJoueur();
                            }

                            player1 = jeu.getPlayer1();
                            if (player1.getNomJoueur().equals(preneurTxt.getText().toString())) {
                                preneurName = player1.getNomJoueur();
                            }

                            break;

                        default:
                            preneurName = "";
                    }

                    if(preneurTxt.getText().toString().equals("Personne"))
                    {
                        preneurName = preneurForPersonneTxt.getText().toString();
                        value = -0.0;
                        roundValue = 0;
                    }

                    if (jeu.getResult() == true) {
                        finishTxt.setText(preneurName + " a gagné de " + Double.toString(value) + " donc de " + Integer.toString(roundValue));
                    } else {
                        finishTxt.setText(preneurName + " a perdu de " + Double.toString((-1) * value) + " donc de " + Integer.toString(roundValue));
                    }


                    playerList = new ArrayList<Player>();


                    if((annonceArrayList.size() != 0) && (joueurArrayList.size() != 0))
                    {
                        jeu.setAnnonceToScore(annonceArrayList, joueurArrayList);
                    }

                    playerList = jeu.setPlayerInOrder();

                    mCallbackPlayers.setPlayers(playerList);

                    if(preneurTxt.getText().toString().equals("Personne"))
                    {
                        preneurForPersonneSpinn.setVisibility(View.INVISIBLE);
                        preneurForPersonneTxt.setVisibility(View.VISIBLE);
                    }

                    else
                    {
                        calculScoreSpinn.setVisibility(View.INVISIBLE);
                        calculScoreTxt.setVisibility(View.VISIBLE);

                        nbBoutSpinn.setVisibility(View.INVISIBLE);
                        nbBoutTxt.setVisibility(View.VISIBLE);

                        scoreEdit.setVisibility(View.INVISIBLE);
                        scoreTxt.setVisibility(View.VISIBLE);
                    }



                    calculerBtn.setVisibility(View.INVISIBLE);

                    finishTxt.setVisibility(View.VISIBLE);
                    finishBtn.setVisibility(View.VISIBLE);

                    part = 6;

                    calculer = false;
                }
            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                preneurSpinn.setVisibility(View.VISIBLE);
                preneurTxt.setVisibility(View.INVISIBLE);

                enchereSpinn.setVisibility(View.VISIBLE);
                enchereTxt.setVisibility(View.INVISIBLE);

                valideFirstPartBtn.setVisibility(View.VISIBLE);

                annonceTitle.setVisibility(View.INVISIBLE);
                annonceSpinn.setVisibility(View.INVISIBLE);

                joueurTitle.setVisibility(View.INVISIBLE);
                joueurSpinn.setVisibility(View.INVISIBLE);

                ajouter.setVisibility(View.INVISIBLE);
                terminer.setVisibility(View.INVISIBLE);

                annonceSelected.setVisibility(View.INVISIBLE);


                joueurSelected.setVisibility(View.INVISIBLE);

                annonce2Spinn.setVisibility(View.INVISIBLE);
                joueur2Spinn.setVisibility(View.INVISIBLE);

                ajouter2.setVisibility(View.INVISIBLE);
                terminer2.setVisibility(View.INVISIBLE);

                annonce2Selected.setVisibility(View.INVISIBLE);
                joueur2Selected.setVisibility(View.INVISIBLE);

                associeTitle.setVisibility(View.INVISIBLE);
                associeSpinn.setSelection(0);
                associeTxt.setVisibility(View.INVISIBLE);

                preneurForPersonneTitle.setVisibility(View.INVISIBLE);
                preneurForPersonneSpinn.setVisibility(View.INVISIBLE);
                preneurForPersonneTxt.setVisibility(View.INVISIBLE);

                calculScoreTitle.setVisibility(View.INVISIBLE);
                calculScoreSpinn.setVisibility(View.INVISIBLE);
                calculScoreTxt.setVisibility(View.INVISIBLE);
                nbBoutTitle.setVisibility(View.INVISIBLE);
                nbBoutSpinn.setVisibility(View.INVISIBLE);
                nbBoutTxt.setVisibility(View.INVISIBLE);
                scoreTitle.setVisibility(View.INVISIBLE);
                scoreEdit.setVisibility(View.INVISIBLE);
                scoreTxt.setVisibility(View.INVISIBLE);
                calculerBtn.setVisibility(View.INVISIBLE);

                finishTxt.setVisibility(View.INVISIBLE);
                finishBtn.setVisibility(View.INVISIBLE);

                // Reset all

                if(nbJoueur == 6)
                {
                    // Add the previous reseted donneur / mort
                    //joueurForScoreArraylist.add(pos, donneurTxt.getText().toString());
                    jeu.addPlayerAtPosition(pos, donneurTxt.getText().toString());

                }

                // Set the order with the distributor as player1
                pos = jeu.getNextPlayer(pos);

                ArrayList<String> list = new ArrayList<String>();
                list = getArguments().getStringArrayList("List");

                donneurTxt.setText(list.get(pos));
                jeu.setDonneur(jeu.getPlayerName(pos));

                if(nbJoueur == 6)
                {
                    // Remove the donneur / mort
                    adaptPlayerList.remove(adaptPlayerList.getItem(pos).toString());
                    /*boolean donneurRemoved = false;
                    int i = 0;
                    while(donneurRemoved == false)
                    {

                        if(jeu.getPlayerName(pos).equals(adaptPlayerList.getItem(i).toString()))
                        {
                            adaptPlayerList.remove(adaptPlayerList.getItem(i).toString());
                            donneurRemoved = true;
                        }

                        i++;
                    }*/
                }

                preneurSpinn.setSelection(jeu.getPlayerListSize() - 1);
                enchereSpinn.setSelection(2);
                enchereSpinn.setVisibility(View.INVISIBLE);
                enchereTxt.setVisibility(View.VISIBLE);
                annonceSpinn.setSelection(0);
                joueurSpinn.setSelection(jeu.getPlayerListSize() - 1);
                annonce2Spinn.setSelection(0);
                joueur2Spinn.setSelection(jeu.getPlayerListSize() - 1);
                preneurForPersonneSpinn.setSelection(jeu.getPlayerListSize() - 1);
                associeSpinn.setSelection(jeu.getPlayerListSize() - 1);

                calculScoreSpinn.setSelection(0);
                nbBoutSpinn.setSelection(0);
                scoreEdit.setText("");

                part=0;
            }
        });

        return view;
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("Part", part);
        outState.putBoolean("Preneur Personne", preneurPersonne);
    }


    public interface setPreneur {
        public void setPreneur(String preneur);
    }

    public interface setEnchere {
        public void setEnchere(String enchere);
    }

    public interface setAnnonceList {
        public void setAnnonceList(ArrayList<String> listAnnonce);
    }

    public interface setJoueurList {
        public void setJoueurList(ArrayList<String> listJoueur);
    }

    public interface setPlayers {
        public void setPlayers(ArrayList<Player> listPlayer);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallbackPreneur = (setPreneur) activity;
            mCallbackEnchere = (setEnchere) activity;
            mCallBackAnnonceList = (setAnnonceList) activity;
            mCallBackJoueurList = (setJoueurList) activity;
            mCallbackPlayers = (setPlayers) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement setPreneur, setEnchere, setAnnonceList and/or setJoueurList");
        }
    }


    @Override
    public void onDetach() {
        mCallbackPreneur = null; // => avoid leaking, thanks @Deepscorn
        mCallbackEnchere = null;
        mCallBackAnnonceList = null;
        mCallBackJoueurList = null;
        mCallbackPlayers = null;
        super.onDetach();
    }
}

