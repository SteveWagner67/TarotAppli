package com.example.steve.tarot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

    private Player player1, player2, player3, player4, player5, player6, player7;

    private boolean firstTime = true;

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

        playerArrayList = getArguments().getParcelableArrayList("playerlist");

        switch(playerArrayList.size())
        {
            case 7:
                player7 = playerArrayList.get(6);
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

            listScore.add(Integer.toString(player1.getScore()));
            listScore.add(Integer.toString(player2.getScore()));
            listScore.add(Integer.toString(player3.getScore()));

            if(playerArrayList.size() == 4)
            {
                listPlayerInOrder.add(player4.getNomJoueur());

                listScore.add(Integer.toString(player4.getScore()));
            }

            if(playerArrayList.size() == 5)
            {
                listPlayerInOrder.add(player4.getNomJoueur());
                listPlayerInOrder.add(player5.getNomJoueur());

                listScore.add(Integer.toString(player4.getScore()));
                listScore.add(Integer.toString(player5.getScore()));
            }

            if(playerArrayList.size() == 6)
            {
                listPlayerInOrder.add(player4.getNomJoueur());
                listPlayerInOrder.add(player5.getNomJoueur());
                listPlayerInOrder.add(player6.getNomJoueur());

                listScore.add(Integer.toString(player4.getScore()));
                listScore.add(Integer.toString(player5.getScore()));
                listScore.add(Integer.toString(player6.getScore()));
            }

            if(playerArrayList.size() == 7)
            {
                listPlayerInOrder.add(player4.getNomJoueur());
                listPlayerInOrder.add(player5.getNomJoueur());
                listPlayerInOrder.add(player6.getNomJoueur());
                listPlayerInOrder.add(player7.getNomJoueur());

                listScore.add(Integer.toString(player4.getScore()));
                listScore.add(Integer.toString(player5.getScore()));
                listScore.add(Integer.toString(player6.getScore()));
                listScore.add(Integer.toString(player7.getScore()));
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

        return view;
    }
}