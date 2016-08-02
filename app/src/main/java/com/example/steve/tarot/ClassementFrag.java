package com.example.steve.tarot;

import android.app.AlertDialog;
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

import java.util.ArrayList;

public class ClassementFrag extends Fragment {

    private View view;

    private ArrayList<String> playerList, playerName;

    private int pos, nbJoueur, posActu;

    private ArrayList<String> listPosition, listPlayer, listPlayerInOrder, listScore;

    private ArrayList<Player> playerArrayList;

    private ArrayAdapter<String> adaptPlayer, adaptPosition, adaptScore;

    private int nbPlayer = 0;

    private ListView listViewPlayer, listViewPosition, listViewScore;

    private Player player1, player2, player3, player4, player5, player6;

    private boolean firstTime = true;

    private int numJeu;

    private int posit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_classement, container, false);

        listViewPlayer = (ListView) view.findViewById(R.id.name);
        listViewPosition = (ListView) view.findViewById(R.id.position);
        listViewScore = (ListView) view.findViewById(R.id.listScore);

        listPosition = new ArrayList<>();
        listPlayer = new ArrayList<>();
        listPlayerInOrder = new ArrayList<>();
        listScore = new ArrayList<>();
        playerArrayList = new ArrayList<>();

        adaptPlayer = new ArrayAdapter<String>(getActivity(), R.layout.simple_list_item_1, listPlayerInOrder);
        adaptPosition = new ArrayAdapter<String>(getActivity(), R.layout.simple_list_item_1, listPosition);
        adaptScore = new ArrayAdapter<String>(getActivity(), R.layout.simple_list_item_1, listScore);

        listPlayer = getArguments().getStringArrayList("List");
        pos = getArguments().getInt("Distributeur");
        nbJoueur = listPlayer.size();

        playerArrayList = new ArrayList<>();

        playerArrayList = getArguments().getParcelableArrayList("playerlist");

        numJeu = getArguments().getInt("NumJeu");

        switch(playerArrayList.size())
        {
            case 6:
                player6 = playerArrayList.get(5);
            case 5:
                player5 = playerArrayList.get(4);
            case 4:
                player4 = playerArrayList.get(3);
            case 3:
                player3 = playerArrayList.get(2);
                player2 = playerArrayList.get(1);
                player1 = playerArrayList.get(0);

                firstTime = false;
                break;
        }


        if(firstTime)
        {
            // Set the order with the distributor as player1
            posActu = pos;
            do
            {
                listPlayerInOrder.add(nbPlayer, listPlayer.get(posActu).toString());

                posActu++;

                if(posActu > (nbJoueur-1))
                {
                    posActu=0;
                }
                nbPlayer++;

            }while(posActu != pos);

            for(int i=1; i<(nbJoueur+1); i++)
            {
                listScore.add(i-1, Integer.toString(0));
            }
        }

        else
        {
            listPlayerInOrder.add(player1.getNomJoueur());
            listPlayerInOrder.add(player2.getNomJoueur());
            listPlayerInOrder.add(player3.getNomJoueur());

            listScore.add(Integer.toString(player1.getScore(numJeu-1)));
            listScore.add(Integer.toString(player2.getScore(numJeu-1)));
            listScore.add(Integer.toString(player3.getScore(numJeu-1)));

            if(playerArrayList.size() == 4)
            {
                listPlayerInOrder.add(player4.getNomJoueur());

                listScore.add(Integer.toString(player4.getScore(numJeu-1)));
            }

            if(playerArrayList.size() == 5)
            {
                listPlayerInOrder.add(player4.getNomJoueur());
                listPlayerInOrder.add(player5.getNomJoueur());

                listScore.add(Integer.toString(player4.getScore(numJeu-1)));
                listScore.add(Integer.toString(player5.getScore(numJeu-1)));
            }

            if(playerArrayList.size() == 6)
            {
                listPlayerInOrder.add(player4.getNomJoueur());
                listPlayerInOrder.add(player5.getNomJoueur());
                listPlayerInOrder.add(player6.getNomJoueur());

                listScore.add(Integer.toString(player4.getScore(numJeu-1)));
                listScore.add(Integer.toString(player5.getScore(numJeu-1)));
                listScore.add(Integer.toString(player6.getScore(numJeu-1)));
            }

        }


        // Set the adapter of the listview to update it
        listViewPlayer.setAdapter(adaptPlayer);
        listViewScore.setAdapter(adaptScore);

        for(int i=1; i<(nbJoueur+1); i++)
        {
            listPosition.add(i-1, Integer.toString(i));
        }

        // Set the adapter of the listview to update it
        listViewPosition.setAdapter(adaptPosition);


            listViewPlayer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    posit = position;

                    if (playerArrayList.size() > 0) {
                        final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());


                        // Get the layout inflater
                        LayoutInflater inflater = getActivity().getLayoutInflater();

                        // Inflate the layout for the dialog
                        // Pass null as the parent view because its going in the dialog layout
                        final View dialogView = inflater.inflate(R.layout.players_details, null);
                        final TextView nbJeuPrisTxt = (TextView) dialogView.findViewById(R.id.nbJeuPrisTxt);
                        final TextView nbVictoireTxt = (TextView) dialogView.findViewById(R.id.nbVictoireTxt);
                        final TextView nbDefaiteTxt = (TextView) dialogView.findViewById(R.id.nbDefaiteTxt);
                        final TextView nbJeuALEnversTxt = (TextView) dialogView.findViewById(R.id.nbJeuALEnversTxt);
                        final TextView nbJeuAssocieTxt = (TextView) dialogView.findViewById(R.id.nbJeuAssocieTxt);
                        final TextView nbPoignetTxt = (TextView) dialogView.findViewById(R.id.nbPoignetTxt);
                        final TextView nbMisereTxt = (TextView) dialogView.findViewById(R.id.nbMisereTxt);
                        final TextView nbPetiteTxt = (TextView) dialogView.findViewById(R.id.nbPetiteTxt);
                        final TextView nbPouceTxt = (TextView) dialogView.findViewById(R.id.nbPouceTxt);
                        final TextView nbGardeTxt = (TextView) dialogView.findViewById(R.id.nbGardeTxt);
                        final TextView nbGardeSansTxt = (TextView) dialogView.findViewById(R.id.nbGardeSansTxt);
                        final TextView nbGardeContreTxt = (TextView) dialogView.findViewById(R.id.nbGardeContreTxt);

                        final Button nbJeuPrisButton = (Button) dialogView.findViewById(R.id.nbJeuPrisButton);
                        final Button nbVictoireButton = (Button) dialogView.findViewById(R.id.nbVictoireButton);
                        final Button nbDefaiteButton = (Button) dialogView.findViewById(R.id.nbDefaiteButton);
                        final Button nbJeuALEnversButton = (Button) dialogView.findViewById(R.id.nbJeuALEnversButton);
                        final Button nbJeuAssocieButton = (Button) dialogView.findViewById(R.id.nbJeuAssocieButton);
                        final Button nbPoignetButton = (Button) dialogView.findViewById(R.id.nbPoignetButton);
                        final Button nbMisereButton = (Button) dialogView.findViewById(R.id.nbMisereButton);
                        final Button nbPetiteButton = (Button) dialogView.findViewById(R.id.nbPetiteButton);
                        final Button nbPouceButton = (Button) dialogView.findViewById(R.id.nbPouceButton);
                        final Button nbGardeButton = (Button) dialogView.findViewById(R.id.nbGardeButton);
                        final Button nbGardeSansButton = (Button) dialogView.findViewById(R.id.nbGardeSansButton);
                        final Button nbGardeContreButton = (Button) dialogView.findViewById(R.id.nbGardeContreButton);

                        nbJeuPrisTxt.setText(Integer.toString(playerArrayList.get(position).getNbJeuPris()));
                        nbVictoireTxt.setText(Integer.toString(playerArrayList.get(position).getNbVictoire()));
                        nbDefaiteTxt.setText(Integer.toString(playerArrayList.get(position).getNbDefaite()));
                        nbJeuALEnversTxt.setText(Integer.toString(playerArrayList.get(position).getNbJeuALEnvers()));
                        nbJeuAssocieTxt.setText(Integer.toString(playerArrayList.get(position).getNbFoisAssocie()));
                        nbPoignetTxt.setText(Integer.toString(playerArrayList.get(position).getNbPoignet()));
                        nbMisereTxt.setText(Integer.toString(playerArrayList.get(position).getNbMisere()));
                        nbPetiteTxt.setText(Integer.toString(playerArrayList.get(position).getNbPetite()));
                        nbPouceTxt.setText(Integer.toString(playerArrayList.get(position).getNbPouce()));
                        nbGardeTxt.setText(Integer.toString(playerArrayList.get(position).getNbGarde()));
                        nbGardeSansTxt.setText(Integer.toString(playerArrayList.get(position).getNbGardeSans()));
                        nbGardeContreTxt.setText(Integer.toString(playerArrayList.get(position).getNbGardeContre()));

                        // Set the layout for the dialog
                        adb.setView(dialogView);

                        adb.setTitle("Détails " + playerArrayList.get(position).getNomJoueur());

                        adb.setPositiveButton("Fermer", null);

                        adb.show();


                        nbJeuPrisButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

                                // Get the layout inflater
                                LayoutInflater inflater = getActivity().getLayoutInflater();

                                final View dialogView = inflater.inflate(R.layout.button_players_details, null);

                                final ListView numJeuListView = (ListView) dialogView.findViewById(R.id.numJeuListView);

                                final ArrayList<Integer> numJeuJeuPrisArraylist = playerArrayList.get(posit).getArrayListNumJeuJeuPris();

                                final ArrayAdapter adaptnumJeu = new ArrayAdapter<Integer>(getActivity(), R.layout.mylist, numJeuJeuPrisArraylist);

                                numJeuListView.setAdapter(adaptnumJeu);

                                adb.setView(dialogView);

                                adb.setTitle("Jeu pris");

                                adb.setPositiveButton("Fermer", null);

                                adb.show();
                            }
                        });

                        nbVictoireButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

                                // Get the layout inflater
                                LayoutInflater inflater = getActivity().getLayoutInflater();

                                final View dialogView = inflater.inflate(R.layout.button_players_details, null);

                                final ListView numJeuListView = (ListView) dialogView.findViewById(R.id.numJeuListView);

                                final ArrayList<Integer> numJeuVictoireArraylist = playerArrayList.get(posit).getArrayListNumJeuVictoire();

                                final ArrayAdapter adaptnumJeu = new ArrayAdapter<Integer>(getActivity(), R.layout.mylist, numJeuVictoireArraylist);

                                numJeuListView.setAdapter(adaptnumJeu);

                                adb.setView(dialogView);

                                adb.setTitle("Victoire");

                                adb.setPositiveButton("Fermer", null);

                                adb.show();
                            }
                        });

                        nbDefaiteButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

                                // Get the layout inflater
                                LayoutInflater inflater = getActivity().getLayoutInflater();

                                final View dialogView = inflater.inflate(R.layout.button_players_details, null);

                                final ListView numJeuListView = (ListView) dialogView.findViewById(R.id.numJeuListView);

                                final ArrayList<Integer> numJeuDefaiteArraylist = playerArrayList.get(posit).getArrayListNumJeuDefaite();

                                final ArrayAdapter adaptnumJeu = new ArrayAdapter<Integer>(getActivity(), R.layout.mylist, numJeuDefaiteArraylist);

                                numJeuListView.setAdapter(adaptnumJeu);

                                adb.setView(dialogView);

                                adb.setTitle("Defaite");

                                adb.setPositiveButton("Fermer", null);

                                adb.show();
                            }
                        });

                        nbJeuALEnversButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

                                // Get the layout inflater
                                LayoutInflater inflater = getActivity().getLayoutInflater();

                                final View dialogView = inflater.inflate(R.layout.button_players_details, null);

                                final ListView numJeuListView = (ListView) dialogView.findViewById(R.id.numJeuListView);

                                final ArrayList<Integer> numJeuALEnversArraylist = playerArrayList.get(posit).getArrayListNumJeuALEnvers();

                                final ArrayAdapter adaptnumJeu = new ArrayAdapter<Integer>(getActivity(), R.layout.mylist, numJeuALEnversArraylist);

                                numJeuListView.setAdapter(adaptnumJeu);

                                adb.setView(dialogView);

                                adb.setTitle("A l'envers");

                                adb.setPositiveButton("Fermer", null);

                                adb.show();
                            }
                        });

                        nbJeuAssocieButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

                                // Get the layout inflater
                                LayoutInflater inflater = getActivity().getLayoutInflater();

                                final View dialogView = inflater.inflate(R.layout.button_players_details, null);

                                final ListView numJeuListView = (ListView) dialogView.findViewById(R.id.numJeuListView);

                                final ArrayList<Integer> numJeuAssocieArraylist = playerArrayList.get(posit).getArrayListNumJeuAssocie();

                                final ArrayAdapter adaptnumJeu = new ArrayAdapter<Integer>(getActivity(), R.layout.mylist, numJeuAssocieArraylist);

                                numJeuListView.setAdapter(adaptnumJeu);

                                adb.setView(dialogView);

                                adb.setTitle("Associé");

                                adb.setPositiveButton("Fermer", null);

                                adb.show();
                            }
                        });

                        nbPoignetButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

                                // Get the layout inflater
                                LayoutInflater inflater = getActivity().getLayoutInflater();

                                final View dialogView = inflater.inflate(R.layout.button_players_details, null);

                                final ListView numJeuListView = (ListView) dialogView.findViewById(R.id.numJeuListView);

                                final ArrayList<Integer> numJeuPoignetArraylist = playerArrayList.get(posit).getArrayListNumJeuPoignet();

                                final ArrayAdapter adaptnumJeu = new ArrayAdapter<Integer>(getActivity(), R.layout.mylist, numJeuPoignetArraylist);

                                numJeuListView.setAdapter(adaptnumJeu);

                                adb.setView(dialogView);

                                adb.setTitle("Poignet");

                                adb.setPositiveButton("Fermer", null);

                                adb.show();
                            }
                        });

                        nbMisereButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

                                // Get the layout inflater
                                LayoutInflater inflater = getActivity().getLayoutInflater();

                                final View dialogView = inflater.inflate(R.layout.button_players_details, null);

                                final ListView numJeuListView = (ListView) dialogView.findViewById(R.id.numJeuListView);

                                final ArrayList<Integer> numJeuMisereArraylist = playerArrayList.get(posit).getArrayListNumJeuMisere();

                                final ArrayAdapter adaptnumJeu = new ArrayAdapter<Integer>(getActivity(), R.layout.mylist, numJeuMisereArraylist);

                                numJeuListView.setAdapter(adaptnumJeu);

                                adb.setView(dialogView);

                                adb.setTitle("Misère");

                                adb.setPositiveButton("Fermer", null);

                                adb.show();
                            }
                        });

                        nbPetiteButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

                                // Get the layout inflater
                                LayoutInflater inflater = getActivity().getLayoutInflater();

                                final View dialogView = inflater.inflate(R.layout.button_players_details, null);

                                final ListView numJeuListView = (ListView) dialogView.findViewById(R.id.numJeuListView);

                                final ArrayList<Integer> numJeuPetiteArraylist = playerArrayList.get(posit).getArrayListNumJeuPetite();

                                final ArrayAdapter adaptnumJeu = new ArrayAdapter<Integer>(getActivity(), R.layout.mylist, numJeuPetiteArraylist);

                                numJeuListView.setAdapter(adaptnumJeu);

                                adb.setView(dialogView);

                                adb.setTitle("Petite");

                                adb.setPositiveButton("Fermer", null);

                                adb.show();
                            }
                        });

                        nbPouceButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

                                // Get the layout inflater
                                LayoutInflater inflater = getActivity().getLayoutInflater();

                                final View dialogView = inflater.inflate(R.layout.button_players_details, null);

                                final ListView numJeuListView = (ListView) dialogView.findViewById(R.id.numJeuListView);

                                final ArrayList<Integer> numJeuPouceArraylist = playerArrayList.get(posit).getArrayListNumJeuPouce();

                                final ArrayAdapter adaptnumJeu = new ArrayAdapter<Integer>(getActivity(), R.layout.mylist, numJeuPouceArraylist);

                                numJeuListView.setAdapter(adaptnumJeu);

                                adb.setView(dialogView);

                                adb.setTitle("Pouce");

                                adb.setPositiveButton("Fermer", null);

                                adb.show();
                            }
                        });

                        nbGardeButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

                                // Get the layout inflater
                                LayoutInflater inflater = getActivity().getLayoutInflater();

                                final View dialogView = inflater.inflate(R.layout.button_players_details, null);

                                final ListView numJeuListView = (ListView) dialogView.findViewById(R.id.numJeuListView);

                                final ArrayList<Integer> numJeuGardeArraylist = playerArrayList.get(posit).getArrayListNumJeuGarde();

                                final ArrayAdapter adaptnumJeu = new ArrayAdapter<Integer>(getActivity(), R.layout.mylist, numJeuGardeArraylist);

                                numJeuListView.setAdapter(adaptnumJeu);

                                adb.setView(dialogView);

                                adb.setTitle("Garde");

                                adb.setPositiveButton("Fermer", null);

                                adb.show();
                            }
                        });

                        nbGardeSansButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

                                // Get the layout inflater
                                LayoutInflater inflater = getActivity().getLayoutInflater();

                                final View dialogView = inflater.inflate(R.layout.button_players_details, null);

                                final ListView numJeuListView = (ListView) dialogView.findViewById(R.id.numJeuListView);

                                final ArrayList<Integer> numJeuGardeSansArraylist = playerArrayList.get(posit).getArrayListNumJeuGardeSans();

                                final ArrayAdapter adaptnumJeu = new ArrayAdapter<Integer>(getActivity(), R.layout.mylist, numJeuGardeSansArraylist);

                                numJeuListView.setAdapter(adaptnumJeu);

                                adb.setView(dialogView);

                                adb.setTitle("Garde Sans");

                                adb.setPositiveButton("Fermer", null);

                                adb.show();
                            }
                        });

                        nbGardeContreButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

                                // Get the layout inflater
                                LayoutInflater inflater = getActivity().getLayoutInflater();

                                final View dialogView = inflater.inflate(R.layout.button_players_details, null);

                                final ListView numJeuListView = (ListView) dialogView.findViewById(R.id.numJeuListView);

                                final ArrayList<Integer> numJeuGardeContreArraylist = playerArrayList.get(posit).getArrayListNumJeuGardeContre();

                                final ArrayAdapter adaptnumJeu = new ArrayAdapter<Integer>(getActivity(), R.layout.mylist, numJeuGardeContreArraylist);

                                numJeuListView.setAdapter(adaptnumJeu);

                                adb.setView(dialogView);

                                adb.setTitle("Garde Contre");

                                adb.setPositiveButton("Fermer", null);

                                adb.show();
                            }
                        });

                    } else {
                        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                        adb.setTitle("Information");
                        adb.setMessage("Aucun jeu réalisé");
                        adb.setPositiveButton("OK", null);
                        adb.show();
                    }

                }
            });

        return view;

    }



}