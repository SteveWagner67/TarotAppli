package com.example.steve.tarot;

import java.util.ArrayList;



/**
 * Created by Steve on 06/04/2016.
 */



public class Jeu extends Player {
    private int nbJeu;
    private ArrayList<String> listJoueur;
    private int nbJoueur;
    private String donneur;
    private String preneur;
    private String def1;
    private String def2;
    private String def3;
    private String enchere;
    private boolean chelemAnnonce;
    private String chelemDone;
    private int nbBout;
    private String petitAuBout;
    private String calculscoreDe;
    private double score;
    static private int petiteValue = 20, pouceValue = 40, gardeValue = 80, gardeSansValue = 160, gardeContreValue = 320;
    static private int value0Bout = 56, value1Bout = 51, value2Bout = 41, value3Bout = 36;

    private double valueDiff;

    private boolean neg = false;

    private Player player1, player2, player3, player4, player5, player6, player7;

    public Jeu(){

        player1 = new Player();
        player2 = new Player();
        player3 = new Player();
        player4 = new Player();
        player5 = new Player();
        player6 = new Player();
        player7 = new Player();
    }


    public void setPlayerList(ArrayList<String> list)
    {
        listJoueur = new ArrayList<String>();
        for(String item: list) listJoueur.add(new String(item));

        nbJoueur = listJoueur.size();

        switch(nbJoueur)
        {
            case 7:
                player7.setNomJoueur(listJoueur.get(6));

            case 6:
                player6.setNomJoueur(listJoueur.get(5));

            case 5:
                player5.setNomJoueur(listJoueur.get(4));

            case 4:
                player4.setNomJoueur(listJoueur.get(3));


            case 3:
                player3.setNomJoueur(listJoueur.get(2));
                player2.setNomJoueur(listJoueur.get(1));
                player1.setNomJoueur(listJoueur.get(0));
                break;
        }


    }

    public ArrayList<String> getPlayerList()
    {
        return listJoueur;
    }

    public int getPlayerListSize()
    {
        return listJoueur.size();
    }

    public String getPlayerName(int numPlayer)
    {
        return listJoueur.get(numPlayer);
    }

    public int getNextPlayer(int posActu) {
        int max = listJoueur.size();
        posActu++;

        // Remove 2 because we want to remove "Personne" and because we count by starting with 0
        if (posActu > (max - 2)) {
            posActu = 0;
        }

        return posActu;
    }

    public int getNbJoueur()
    {
        return nbJoueur;
    }

    public void setEnchere(String enchere)
    {
        this.enchere = enchere;
    }

    public void setPreneur(String preneur) {this.preneur = preneur; }

    public void setNbBout(int nbBout)
    {
        this.nbBout = nbBout;
    }

    public void setScore(double score)
    {
        this.score = score;
    }

    public double calculScore()
    {
        valueDiff = 0.0;
        int valueToDo = 0;

        switch (nbBout){
            case 0:
                valueToDo = value0Bout;
                break;

            case 1:
                valueToDo = value1Bout;
                break;

            case 2:
                valueToDo = value2Bout;
                break;

            case 3:
                valueToDo = value3Bout;
                break;
        }

        valueDiff = score - valueToDo;

        setPlayersScore();

        return valueDiff;

    }

    public int getArrondi(double score)
    {
        double modulus;
        int division;
        int result;

        if(score < 0.0)
        {
            score*=(-1);
            neg=true;
        }

        division = (int)score / 10;
        modulus = score % 10;

        if(modulus >= 5)
        {
            result = division*10+10;
        }

        else
        {
            result = division*10;
        }

 /*       if(true == neg)
        {
            result*=(-1);
        }
*/

        return result;
    }

    public void setCalculScoreDe(String calculscoreDe)
    {
        this.calculscoreDe = calculscoreDe;
    }

    /**
     *  true -> Preneur a gagné
     *  false -> Preneur a perdu
     */
    public boolean getResult()
    {
        boolean res = false;

        if(calculscoreDe.equals("Preneur"))
        {
            /**
             * Score négatif
             */
            if(true == neg)
            {
                res = false;
            }

            else
            {
                res = true;
            }
        }

        else if(calculscoreDe.equals("Défenseurs"))
        {
            /**
             * Score négatif
             */
            if(true == neg)
            {
                res = true;
            }

            else
            {
                res = false;
            }
        }

        return res;

    }

    private void setPlayersScore() {
        int scoreDonne = 0;

        switch (enchere) {
            case "Petite":
                scoreDonne += petiteValue;
                break;

            case "Pouce":
                scoreDonne += pouceValue;
                break;

            case "Garde":
                scoreDonne += gardeValue;
                break;

            case "Garde sans chien":
                scoreDonne += gardeSansValue;
                break;

            case "Garde contre chien":
                scoreDonne += gardeContreValue;
                break;

        }

        scoreDonne += getArrondi(valueDiff);

        // Preneur a Gagné
        if (getResult() == true) {
            boolean fourIsTrue = false;

            switch (nbJoueur) {
                case 7:

                case 6:

                case 5:

                case 4:
                    // Joueur 4 est preneur
                    if (preneur.equals(player4.getNomJoueur())) {
                        player4.setScore(3 * scoreDonne);
                        player3.setScore((-1) * scoreDonne);
                        player2.setScore((-1) * scoreDonne);
                        player1.setScore((-1) * scoreDonne);
                    }

                    fourIsTrue = true;

                case 3:
                    // Joueur 3 est preneur
                    if (preneur.equals(player3.getNomJoueur())) {
                        player3.setScore(3 * scoreDonne);
                        player2.setScore((-1) * scoreDonne);
                        player1.setScore((-1) * scoreDonne);

                        if (fourIsTrue) {
                            player4.setScore((-1) * scoreDonne);
                        }

                    }

                    // Joueur 2 est preneur
                    if (preneur.equals(player2.getNomJoueur())) {
                        player2.setScore(3 * scoreDonne);
                        player1.setScore((-1) * scoreDonne);
                        player3.setScore((-1) * scoreDonne);

                        if (fourIsTrue) {
                            player4.setScore((-1) * scoreDonne);
                        }
                    }

                    // Joueur 1 est preneur
                    if (preneur.equals(player1.getNomJoueur())) {
                        player1.setScore(3 * scoreDonne);
                        player2.setScore((-1) * scoreDonne);
                        player3.setScore((-1) * scoreDonne);

                        if (fourIsTrue) {
                            player4.setScore((-1) * scoreDonne);
                        }
                    }
                    break;
            }

        }

        // Preneur a perdu
        else {
            boolean fourIsTrue = false;

            switch (nbJoueur) {
                case 7:

                case 6:

                case 5:

                case 4:
                    // Joueur 4 est preneur
                    if (preneur.equals(player4.getNomJoueur())) {
                        player4.setScore((-3) * scoreDonne);
                        player3.setScore(scoreDonne);
                        player2.setScore(scoreDonne);
                        player1.setScore(scoreDonne);
                    }

                    fourIsTrue = true;

                case 3:
                    // Joueur 3 est preneur
                    if (preneur.equals(player3.getNomJoueur())) {
                        player3.setScore((-3) * scoreDonne);
                        player2.setScore(scoreDonne);
                        player1.setScore(scoreDonne);

                        if (fourIsTrue) {
                            player4.setScore(scoreDonne);
                        }

                    }

                    // Joueur 2 est preneur
                    if (preneur.equals(player2.getNomJoueur())) {
                        player2.setScore((-3) * scoreDonne);
                        player1.setScore(scoreDonne);
                        player3.setScore(scoreDonne);

                        if (fourIsTrue) {
                            player4.setScore(scoreDonne);
                        }
                    }

                    // Joueur 1 est preneur
                    if (preneur.equals(player1.getNomJoueur())) {
                        player1.setScore((-3) * scoreDonne);
                        player2.setScore(scoreDonne);
                        player3.setScore(scoreDonne);

                        if (fourIsTrue) {
                            player4.setScore(scoreDonne);
                        }
                    }
                    break;
            }
        }
    }

    public void setAnnonceToScore(ArrayList<String> annonceList, ArrayList<String> playerList)
    {
        int size=0, i=0, score=0;
        String annonce="";
        boolean fourIsTrue=false;

        size = annonceList.size();

        while(size > 0)
        {
            if(nbJoueur >= 5)
            {
                if(annonceList.get(i) == "Poignet (8)")
                {
                    annonce = "Poignet petite";
                }

                else if(annonceList.get(i) == "Poignet (10)")
                {
                    annonce = "Poignet moyenne";
                }

                else if(annonceList.get(i) == "Poignet (13)")
                {
                    annonce = "Poignet grande";
                }

                else
                {
                    annonce = annonceList.get(i);
                }
            }

            else if((nbJoueur == 4) || (nbJoueur == 3))
            {
                if(annonceList.get(i) == "Poignet (10)")
                {
                    annonce = "Poignet petite";
                }

                else if(annonceList.get(i) == "Poignet (13)")
                {
                    annonce = "Poignet moyenne";
                }

                else if(annonceList.get(i) == "Poignet (15)")
                {
                    annonce = "Poignet grande";
                }

                else
                {
                    annonce = annonceList.get(i);
                }
            }

            switch(annonce)
            {
                case "Simple misère":
                    score = 10;
                    break;

                case "Double misère":
                    score = 20;
                    break;

                case "Poignet petite":
                    score = 20;
                    break;

                case "Poignet moyenne":
                    score = 30;
                    break;

                case "Poignet grande":
                    score = 40;
                    break;
            }


            switch(nbJoueur)
            {
                case 7:
                case 6:
                case 5:
                case 4:
                    if(playerList.get(i) == player4.getNomJoueur())
                    {
                        player4.setScore(3*score);
                        player3.setScore((-1)*score);
                        player2.setScore((-1)*score);
                        player1.setScore((-1)*score);
                    }

                    fourIsTrue=true;

                case 3:

                    if(playerList.get(i) == player3.getNomJoueur())
                    {
                        player3.setScore(2*score);
                        player2.setScore((-1)*score);
                        player1.setScore((-1)*score);

                        if(fourIsTrue == true)
                        {
                            player3.setScore(score);
                            player4.setScore((-1)*score);
                        }
                    }

                    if(playerList.get(i) == player2.getNomJoueur())
                    {
                        player2.setScore(2*score);
                        player1.setScore((-1)*score);
                        player3.setScore((-1)*score);

                        if(fourIsTrue == true)
                        {
                            player2.setScore(score);
                            player4.setScore((-1)*score);
                        }
                    }


                    if(playerList.get(i) == player1.getNomJoueur())
                    {
                        player1.setScore(2*score);
                        player2.setScore((-1)*score);
                        player3.setScore((-1)*score);

                        if(fourIsTrue == true)
                        {
                            player1.setScore(score);
                            player4.setScore((-1)*score);
                        }
                    }

                    break;
            }

            size--;
            i++;
        }

    }

    public Player getPlayer1() { return player1; }
    public Player getPlayer2() { return player2; }
    public Player getPlayer3() { return player3; }
    public Player getPlayer4() { return player4; }
    public Player getPlayer5() { return player5; }
    public Player getPlayer6() { return player6; }
    public Player getPlayer7() { return player7; }

    public ArrayList<Player> setPlayerInOrder()
    {
        boolean playerToAdd = false;
        int i=0, j=0, placeListOut=0, indexPlayer=0;
        ArrayList<Player> playerListIn, playerListOut;


        playerListIn = new ArrayList<>();
        playerListOut = new ArrayList<>();

        playerListIn.add(0, player1);
        playerListIn.add(1, player2);
        playerListIn.add(2, player3);

        if(nbJoueur == 4)
        {
            playerListIn.add(3, player4);
        }

        placeListOut=nbJoueur;

        while(placeListOut > 0)
        {
            while(j<placeListOut)
            {
                if(playerListIn.get(j).getScore() > playerListIn.get(i).getScore())
                {
                    if(playerListIn.get(j).getScore() > playerListIn.get(indexPlayer).getScore())
                    {
                        indexPlayer=j;
                        playerToAdd = true;
                    }
                }
                j++;
            }
            if(playerToAdd == true)
            {
                playerListOut.add(i, playerListIn.get(indexPlayer));
                playerListIn.remove(indexPlayer);
            }

            else
            {
                playerListOut.add(i, playerListIn.get(i));
                playerListIn.remove(i);
            }


            placeListOut--;
            indexPlayer=0;
            playerToAdd = false;
            j=0;
        }

        playerListIn.addAll(playerListOut);
        i=0;
        j=playerListIn.size()-1;

        // Reset the playerListOut
        playerListOut = new ArrayList<>();

        while(i < playerListIn.size())
        {
            playerListOut.add(i, playerListIn.get(j));

            j--;
            i++;
        }


        return playerListOut;
    }
}
