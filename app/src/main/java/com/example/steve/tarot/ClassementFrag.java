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

    View view;
/*    TextView player1, player2, player3, player4;

    ArrayList<String> playerList, playerName;
*/
    int pos, nbJoueur, posActu;

    ArrayList<String> listPosition, listPlayer, listPlayerInOrder, listScore;

    ArrayList<Player> playerArrayList;

    ArrayAdapter<String> adaptPlayer, adaptPosition, adaptScore;

    int nbPlayer = 0;

    ListView listViewPlayer, listViewPosition, listViewScore;

    Player player1, player2, player3, player4, player5, player6, player7;

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

        // Set the adapter of the listview to update it
        listViewPlayer.setAdapter(adaptPlayer);

        for(int i=1; i<(nbJoueur+1); i++)
        {
            listPosition.add(i-1, Integer.toString(i));
        }

        // Set the adapter of the listview to update it
        listViewPosition.setAdapter(adaptPosition);


        for(int i=1; i<(nbJoueur+1); i++)
        {
            listScore.add(i-1, Integer.toString(0));
        }

        listViewScore.setAdapter(adaptScore);

        return view;
    }
}