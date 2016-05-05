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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailsFrag extends Fragment {

    View view;
    TextView preneur;
    TextView enchere;

    ArrayList<String> listAnnonce, listJoueur;

    ArrayAdapter<String> adaptAnnonceList, adaptJoueurList;

    ListView annonceListView, joueurListView;

    ArrayList<Player> playerArrayList;

    Player player1, player2, player3, player4, player5, player6, player7;

    int nbJoueur;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_details, container, false);

        /* TODO
        Faire un check de combien de joueur il y a pour créer le nombre suffisant
        Ajouter le nom des joueurs dans la arraylist du noms des joueurs
        Ajouter le score des joueurs dans la arraylist du score des joueurs
        Faire un tri des joueurs+scores par rapport au score de chacun (le faire avant de l'envoyer via le bundle?)
         */

        annonceListView = (ListView) view.findViewById(R.id.selectedAnnonce);
        preneur = (TextView)view.findViewById(R.id.preneur);
        enchere = (TextView)view.findViewById(R.id.enchere);
        joueurListView = (ListView) view.findViewById(R.id.selectedJoueur);


        preneur.setText(getArguments().getString("Preneur"));
        enchere.setText(getArguments().getString("Enchere"));

        listAnnonce = getArguments().getStringArrayList("List Annonce");
        listJoueur = getArguments().getStringArrayList("List Joueur");

        nbJoueur = listJoueur.size();

        playerArrayList = getArguments().getParcelableArrayList("playerlist");


        player1 = playerArrayList.get(0);


        Toast.makeText(getContext(), "nom joueur 1: "+player1.getNomJoueur()+" Score: "+player1.getScore(), Toast.LENGTH_SHORT).show();


        adaptAnnonceList = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listAnnonce);
        adaptJoueurList = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listJoueur);

        annonceListView.setAdapter(adaptAnnonceList);
        joueurListView.setAdapter(adaptJoueurList);

        return view;

    }

    public void getPreneur(String preneur)
    {
        Toast.makeText(getContext(), "Preneur: "+preneur, Toast.LENGTH_SHORT).show();

    }
}