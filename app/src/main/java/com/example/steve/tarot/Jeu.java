package com.example.steve.tarot;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;



/**
 * Created by Steve on 06/04/2016.
 */



public class Jeu extends Player implements Parcelable {
    private ArrayList<String> listJoueur;
    private ArrayList<Player> playersListInOrder;
    private int nbJoueur;
    private String donneur;
    private String preneur;
    private String associe;
    private String enchere;
    private boolean chelemAnnonce;
    private String chelemDone;
    private int nbBout;
    private boolean petitAuBout;
    private String calculscoreDe;
    private double score;
    static private int petiteValue = 20, pouceValue = 40, gardeValue = 80, gardeSansValue = 160, gardeContreValue = 320;
    static private int value0Bout = 56, value1Bout = 51, value2Bout = 41, value3Bout = 36;
    static private int valuePetitAuBout = 10;

    private double valueDiff;

    private boolean neg = false;

    private Player player1, player2, player3, player4, player5, player6;

    private ArrayList<Player> classementListJoueur;

    private int scorePlayer6, scorePlayer5, scorePlayer4, scorePlayer3, scorePlayer2, scorePlayer1;

    private int playerWinner, playerLooser, playerAssocie, playerAnnonce;

    private boolean jeuALEnvers;

    public Jeu(){

        player1 = new Player();
        player2 = new Player();
        player3 = new Player();
        player4 = new Player();
        player5 = new Player();
        player6 = new Player();

        scorePlayer6 = 0;
        scorePlayer5 = 0;
        scorePlayer4 = 0;
        scorePlayer3 = 0;
        scorePlayer2 = 0;
        scorePlayer1 = 0;
    }

    public void setJeuALenvers(boolean jeuALEnvers)
    {
        this.jeuALEnvers = jeuALEnvers;
    }

    public void setPlayerList(ArrayList<String> list)
    {
        listJoueur = new ArrayList<String>();
        for(String item: list) listJoueur.add(new String(item));

        nbJoueur = listJoueur.size();

        switch(nbJoueur)
        {
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

    public void addPlayerAtPosition(int position, String player)
    {
        listJoueur.add(position, player);
    }

    public void setDonneur(String donneur)
    {
        this.donneur = donneur;
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

    public void setAssocie(String associe)
    {
        this.associe = associe;
    }

    public void setPetitAuBout (boolean petitAuBout) { this.petitAuBout = petitAuBout; }

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

        else
        {
            neg = false;
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

    public void setPlayersScore(int numJeu) {
        int scoreDonne = 0;

        switch (enchere)
        {
            case "Petite (20pts)":
                scoreDonne += petiteValue;
                break;

            case "Pouce (40pts)":
                scoreDonne += pouceValue;
                break;

            case "Garde (80pts)":
                scoreDonne += gardeValue;
                break;

            case "Garde sans chien (160pts)":
                scoreDonne += gardeSansValue;
                break;

            case "Garde contre chien (320pts)":
                scoreDonne += gardeContreValue;
                break;

        }

        scorePlayer6 = 0;
        scorePlayer5 = 0;
        scorePlayer4 = 0;
        scorePlayer3 = 0;
        scorePlayer2 = 0;
        scorePlayer1 = 0;

        playerWinner = 0;
        playerLooser = 0;
        playerAssocie = 0;

        scoreDonne += getArrondi(valueDiff);

        // Preneur a Gagné
        if (getResult() == true)
        {
            boolean fourIsTrue = false, fiveIsTrue = false, sixIsTrue = false;

            switch (nbJoueur) {

                case 6:

                    // Joueur 6 est preneur
                    if(preneur.equals(player6.getNomJoueur()))
                    {
                        playerWinner = 6;
                        setScorePlayer6Winner(scoreDonne);
                    }

                    // Don't change the score for the donneur = "Mort" (which can only be one who loose)
                    if(donneur.equals(player6.getNomJoueur()))
                    {
                        scorePlayer6 = scoreDonne;

                        if(petitAuBout)
                        {
                            scorePlayer6 += valuePetitAuBout; // Player6 doesn't give point because is "Mort"
                        }
                    }

                    if(donneur.equals(player5.getNomJoueur()))
                    {
                        scorePlayer5 = scoreDonne;

                        if(petitAuBout)
                        {
                            scorePlayer5 += valuePetitAuBout; // Player5 doesn't give point because is "Mort"
                        }
                    }

                    if(donneur.equals(player4.getNomJoueur()))
                    {
                        scorePlayer4 = scoreDonne;

                        if(petitAuBout)
                        {
                            scorePlayer4 += valuePetitAuBout; // Player4 doesn't give point because is "Mort"
                        }
                    }

                    if(donneur.equals(player3.getNomJoueur()))
                    {
                        scorePlayer3 = scoreDonne;

                        if(petitAuBout)
                        {
                            scorePlayer3 += valuePetitAuBout; // Player3 doesn't give point because is "Mort"
                        }
                    }

                    if(donneur.equals(player2.getNomJoueur()))
                    {
                        scorePlayer2 = scoreDonne;

                        if(petitAuBout)
                        {
                            scorePlayer2 += valuePetitAuBout; // Player2 doesn't give point because is "Mort"
                        }
                    }

                    if(donneur.equals(player1.getNomJoueur()))
                    {
                        scorePlayer1 = scoreDonne;

                        if(petitAuBout)
                        {
                            scorePlayer1 += valuePetitAuBout; // Player1 doesn't give point because is "Mort"
                        }
                    }


                    sixIsTrue = true;

                case 5:

                    // Joueur 5 est preneur
                    if(preneur.equals(player5.getNomJoueur()))
                    {
                        playerWinner = 5;
                        setScorePlayer5Winner(sixIsTrue, scoreDonne);
                    }

                    fiveIsTrue = true;

                case 4:

                    // Joueur 4 est preneur
                    if (preneur.equals(player4.getNomJoueur()))
                    {
                        playerWinner = 4;
                        setScorePlayer4Winner(scoreDonne, sixIsTrue, fiveIsTrue);
                    }

                    fourIsTrue = true;

                case 3:
                    // Joueur 3 est preneur
                    if (preneur.equals(player3.getNomJoueur()))
                    {
                        playerWinner = 3;
                        setScorePlayer3Winner(scoreDonne, sixIsTrue, fiveIsTrue, fourIsTrue);
                    }

                    // Joueur 2 est preneur
                    if (preneur.equals(player2.getNomJoueur()))
                    {
                        playerWinner = 2;
                        setScorePlayer2Winner(scoreDonne, sixIsTrue, fiveIsTrue, fourIsTrue);
                    }

                    // Joueur 1 est preneur
                    if (preneur.equals(player1.getNomJoueur()))
                    {
                        playerWinner = 1;
                        setScorePlayer1Winner(scoreDonne, sixIsTrue, fiveIsTrue, fourIsTrue);
                    }
                    break;
            }
        }

        // Preneur a perdu
        else {
            boolean fourIsTrue = false, fiveIsTrue = false, sixIsTrue = false;

            if(!petitAuBout)
            {
                switch (nbJoueur) {

                    case 6:

                        if (preneur.equals(player6.getNomJoueur())) {
                            playerLooser = 6;
                            setScorePlayer6Looser(scoreDonne);
                        }

                        // Don't change the score for the donneur (which can only be one who win)
                        if (donneur.equals(player6.getNomJoueur())) {
                            scorePlayer6 += (-1) * scoreDonne;
                        }

                        if (donneur.equals(player5.getNomJoueur())) {
                            scorePlayer5 += (-1) * scoreDonne;
                        }

                        if (donneur.equals(player4.getNomJoueur())) {
                            scorePlayer4 += (-1) * scoreDonne;
                        }

                        if (donneur.equals(player3.getNomJoueur())) {
                            scorePlayer3 += (-1) * scoreDonne;
                        }

                        if (donneur.equals(player2.getNomJoueur())) {
                            scorePlayer2 += (-1) * scoreDonne;
                        }

                        if (donneur.equals(player1.getNomJoueur())) {
                            scorePlayer1 += (-1) * scoreDonne;
                        }

                        sixIsTrue = true;

                    case 5:

                        if (preneur.equals(player5.getNomJoueur())) {
                            playerLooser = 5;
                            setScorePlayer5Looser(scoreDonne, sixIsTrue);
                        }

                        fiveIsTrue = true;

                    case 4:
                        // Joueur 4 est preneur
                        if (preneur.equals(player4.getNomJoueur())) {
                            playerLooser = 4;
                            setScorePlayer4Looser(scoreDonne, sixIsTrue, fiveIsTrue);
                        }

                        fourIsTrue = true;

                    case 3:
                        // Joueur 3 est preneur
                        if (preneur.equals(player3.getNomJoueur())) {
                            playerLooser = 3;
                            setScorePlayer3Looser(scoreDonne, sixIsTrue, fiveIsTrue, fourIsTrue);
                        }

                        // Joueur 2 est preneur
                        if (preneur.equals(player2.getNomJoueur())) {
                            playerLooser = 2;
                            setScorePlayer2Looser(scoreDonne, sixIsTrue, fiveIsTrue, fourIsTrue);
                        }

                        // Joueur 1 est preneur
                        if (preneur.equals(player1.getNomJoueur())) {
                            playerLooser = 1;
                            setScorePlayer1Looser(scoreDonne, sixIsTrue, fiveIsTrue, fourIsTrue);
                        }
                        break;
                }
            }

            // Partie annulé
            else
            {

            }

        }

        switch (nbJoueur)
        {
            case 6:
                player6.setScore(scorePlayer6);
            case 5:
                player5.setScore(scorePlayer5);
            case 4:
                player4.setScore(scorePlayer4);
            case 3:
                player3.setScore(scorePlayer3);
                player2.setScore(scorePlayer2);
                player1.setScore(scorePlayer1);
                break;
        }

        setPartPlayerDetail(numJeu);

        setAssociePlayerDetail(numJeu);


    }

    public void setAnnonceToScore(int numJeu, ArrayList<String> annonceList, ArrayList<String> playerList)
    {
        int size=0, i=0, score=0;
        String annonce="";
        boolean fourIsTrue=false, fiveIsTrue=false, sixIsTrue=false;

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

            playerAnnonce = 0;

            switch(nbJoueur)
            {
                case 6:

                    if(playerList.get(i).equals(player6.getNomJoueur()))
                    {
                        scorePlayer6 = 4 * score;
                        scorePlayer5 = (-1) * score;
                        scorePlayer4 = (-1) * score;
                        scorePlayer3 = (-1) * score;
                        scorePlayer2 = (-1) * score;
                        scorePlayer1 = (-1) * score;

                        playerAnnonce = 6;
                    }

                    //Remove the change for the donneur
                    if(donneur.equals(player6.getNomJoueur()))
                    {
                        scorePlayer6 = score;
                    }

                    if(donneur.equals(player5.getNomJoueur()))
                    {
                        scorePlayer5 = score;
                    }

                    if(donneur.equals(player4.getNomJoueur()))
                    {
                        scorePlayer4 = score;
                    }

                    if(donneur.equals(player3.getNomJoueur()))
                    {
                        scorePlayer3 = score;
                    }

                    if(donneur.equals(player2.getNomJoueur()))
                    {
                        scorePlayer2 = score;
                    }

                    if(donneur.equals(player1.getNomJoueur()))
                    {
                        scorePlayer1 = score;
                    }

                    sixIsTrue = true;

                case 5:

                    if(playerList.get(i).equals(player5.getNomJoueur()))
                    {
                        scorePlayer5 = 4 * score;
                        scorePlayer4 = (-1) * score;
                        scorePlayer3 = (-1) * score;
                        scorePlayer2 = (-1) * score;
                        scorePlayer1 = (-1) * score;

                        if(sixIsTrue)
                        {
                            scorePlayer6 = (-1) * score;
                        }

                        playerAnnonce = 5;
                    }


                    fiveIsTrue = true;

                case 4:
                    if(playerList.get(i).equals(player4.getNomJoueur()))
                    {
                        if(sixIsTrue)
                        {
                            scorePlayer6 = (-1) * score;
                        }

                        if(fiveIsTrue)
                        {
                            scorePlayer4 = 4 * score;
                            scorePlayer5 = (-1) * score;
                            scorePlayer3 = (-1) * score;
                            scorePlayer2 = (-1) * score;
                            scorePlayer1 = (-1) * score;
                        }

                        else
                        {
                            scorePlayer4 = 3 * score;
                            scorePlayer3 = (-1) * score;
                            scorePlayer2 = (-1) * score;
                            scorePlayer1 = (-1) * score;
                        }

                        playerAnnonce = 4;
                    }

                    fourIsTrue=true;

                case 3:

                    if(playerList.get(i).equals(player3.getNomJoueur()))
                    {
                        if(sixIsTrue)
                        {
                            scorePlayer6 = (-1) * score;
                        }

                        if(fiveIsTrue)
                        {
                            scorePlayer3 = score;
                            scorePlayer5 = (-1) * score;
                        }

                        if(fourIsTrue == true)
                        {
                            scorePlayer3 = score;
                            scorePlayer4 = (-1) * score;
                        }

                        scorePlayer3 = 2 * score;
                        scorePlayer2 = (-1) * score;
                        scorePlayer1 = (-1) * score;

                        playerAnnonce = 3;
                    }

                    if(playerList.get(i).equals(player2.getNomJoueur()))
                    {
                        scorePlayer2 = 2 * score;
                        scorePlayer1 = (-1) * score;
                        scorePlayer3 = (-1) * score;

                        if(fourIsTrue == true)
                        {
                            scorePlayer2 = score;
                            scorePlayer4 = (-1) * score;
                        }

                        if(fiveIsTrue == true)
                        {
                            scorePlayer2 = score;
                            scorePlayer5 = (-1) * score;
                        }

                        if(sixIsTrue)
                        {
                            scorePlayer6 = (-1) * score;
                        }

                        playerAnnonce = 2;
                    }


                    if(playerList.get(i).equals(player1.getNomJoueur()))
                    {
                        if(sixIsTrue)
                        {
                            scorePlayer6 = (-1) * score;
                        }

                        if(fiveIsTrue == true)
                        {
                            scorePlayer1 = score;
                            scorePlayer5 = (-1) * score;
                        }

                        if(fourIsTrue == true)
                        {
                            scorePlayer1 = score;
                            scorePlayer4 = (-1) * score;
                        }

                        scorePlayer1 = 2 * score;
                        scorePlayer2 = (-1) * score;
                        scorePlayer3 = (-1) * score;

                        playerAnnonce = 1;
                    }

                    break;
            }

            size--;
            i++;

            if(!petitAuBout)
            {
                setAnnoncePlayerDetail(numJeu, annonce);
            }

        }
    }

    public Player getPlayer1() { return player1; }
    public Player getPlayer2() { return player2; }
    public Player getPlayer3() { return player3; }
    public Player getPlayer4() { return player4; }
    public Player getPlayer5() { return player5; }
    public Player getPlayer6() { return player6; }

    public void setPlayersInOrder(int numJeu)
    {
        boolean playerToAdd = false;
        int i=0, j=0, placeListOut=0, indexPlayer=0;
        ArrayList<Player> playerListIn;
        int index, placement;


        playerListIn = new ArrayList<>();
        playersListInOrder = new ArrayList<>();

        playerListIn.add(0, player1);
        playerListIn.add(1, player2);
        playerListIn.add(2, player3);

        if(nbJoueur == 4)
        {
            playerListIn.add(3, player4);
        }

        if(nbJoueur == 5)
        {
            playerListIn.add(3, player4);
            playerListIn.add(4, player5);
        }

        if(nbJoueur == 6)
        {
            playerListIn.add(3, player4);
            playerListIn.add(4, player5);
            playerListIn.add(5, player6);
        }

        placeListOut=nbJoueur;

        while(placeListOut > 0)
        {
            while(j<placeListOut)
            {
                if(playerListIn.get(j).getScore(numJeu) > playerListIn.get(i).getScore(numJeu))
                {
                    if(playerListIn.get(j).getScore(numJeu) > playerListIn.get(indexPlayer).getScore(numJeu))
                    {
                        indexPlayer=j;
                        playerToAdd = true;
                    }
                }
                j++;
            }
            if(playerToAdd == true)
            {
                playersListInOrder.add(i, playerListIn.get(indexPlayer));
                playerListIn.remove(indexPlayer);
            }

            else
            {
                playersListInOrder.add(i, playerListIn.get(i));
                playerListIn.remove(i);
            }


            placeListOut--;
            indexPlayer=0;
            playerToAdd = false;
            j=0;
        }

        playerListIn.addAll(playersListInOrder);
        i=0;
        j=playerListIn.size()-1;

        // Reset the playerListOut
        playersListInOrder = new ArrayList<>();

        while(i < playerListIn.size())
        {
            playersListInOrder.add(i, playerListIn.get(j));

            j--;
            i++;
        }

        index = nbJoueur - 1;
        placement = nbJoueur;

        while(placement > 0)
        {
            playersListInOrder.get(index).setActuPos(placement);
            index--;
            placement--;

        }

    }

    public ArrayList<Player> getPlayersInOrder()
    {
        return playersListInOrder;
    }

    private void setScorePlayer6Winner(int scoreDonne)
    {
        if(petitAuBout)
        {
            scorePlayer6 += valuePetitAuBout * (nbJoueur - 2); // Player6 doesn't give point to himself + the "mort" neither
            scorePlayer5 += (-1) * valuePetitAuBout;
            scorePlayer4 += (-1) * valuePetitAuBout;
            scorePlayer3 += (-1) * valuePetitAuBout;
            scorePlayer2 += (-1) * valuePetitAuBout;
            scorePlayer1 += (-1) * valuePetitAuBout;
        }
        // Joueur 6 est associé (joue seul)
        if(associe.equals(player6.getNomJoueur()))
        {
            scorePlayer6 += 4 * scoreDonne;
            scorePlayer5 += (-1) * scoreDonne;
            scorePlayer4 += (-1) * scoreDonne;
            scorePlayer3 += (-1) * scoreDonne;
            scorePlayer2 += (-1) * scoreDonne;
            scorePlayer1 += (-1) * scoreDonne;
        }

        // Joueur 5 est associé
        if(associe.equals(player5.getNomJoueur()))
        {
            scorePlayer6 += 2 * scoreDonne;
            scorePlayer5 += scoreDonne;
            scorePlayer4 += (-1) * scoreDonne;
            scorePlayer3 += (-1) * scoreDonne;
            scorePlayer2 += (-1) * scoreDonne;
            scorePlayer1 += (-1) * scoreDonne;

            playerAssocie = 5;
        }

        // Joueur 4 est associé
        if(associe.equals(player4.getNomJoueur()))
        {
            scorePlayer6 += 2 * scoreDonne;
            scorePlayer4 += scoreDonne;
            scorePlayer5 += (-1) * scoreDonne;
            scorePlayer3 += (-1) * scoreDonne;
            scorePlayer2 += (-1) * scoreDonne;
            scorePlayer1 += (-1) * scoreDonne;

            playerAssocie = 4;
        }

        // Joueur 3 est associé
        if(associe.equals(player3.getNomJoueur()))
        {
            scorePlayer6 += 2 * scoreDonne;
            scorePlayer3 += scoreDonne;
            scorePlayer5 += (-1) * scoreDonne;
            scorePlayer4 += (-1) * scoreDonne;
            scorePlayer2 += (-1) * scoreDonne;
            scorePlayer1 += (-1) * scoreDonne;

            playerAssocie = 3;
        }

        // Joueur 2 est associé
        if(associe.equals(player2.getNomJoueur()))
        {
            scorePlayer6 += 2 * scoreDonne;
            scorePlayer2 += scoreDonne;
            scorePlayer5 += (-1) * scoreDonne;
            scorePlayer4 += (-1) * scoreDonne;
            scorePlayer3 += (-1) * scoreDonne;
            scorePlayer1 += (-1) * scoreDonne;

            playerAssocie = 2;
        }

        // Joueur 1 est associé
        if(associe.equals(player1.getNomJoueur()))
        {
            scorePlayer6 += 2 * scoreDonne;
            scorePlayer1 += scoreDonne;
            scorePlayer5 += (-1) * scoreDonne;
            scorePlayer4 += (-1) * scoreDonne;
            scorePlayer3 += (-1) * scoreDonne;
            scorePlayer2 += (-1) * scoreDonne;

            playerAssocie = 1;
        }



    }

    private void setScorePlayer5Winner(boolean sixIsTrue, int scoreDonne)
    {
        if(petitAuBout)
        {
            scorePlayer5 += valuePetitAuBout * (nbJoueur - 1); // Player6 doesn't give point to himself
            scorePlayer4 += (-1) * valuePetitAuBout;
            scorePlayer3 += (-1) * valuePetitAuBout;
            scorePlayer2 += (-1) * valuePetitAuBout;
            scorePlayer1 += (-1) * valuePetitAuBout;
        }

        // Joueur 5 est associé (joue seul)
        if(associe.equals(player5.getNomJoueur()))
        {
            scorePlayer5 += 4 * scoreDonne;
            scorePlayer4 += (-1) * scoreDonne;
            scorePlayer3 += (-1) * scoreDonne;
            scorePlayer2 += (-1) * scoreDonne;
            scorePlayer1 += (-1) * scoreDonne;
        }

        // Joueur 4 est associé
        if(associe.equals(player4.getNomJoueur()))
        {
            scorePlayer5 += 2 * scoreDonne;
            scorePlayer4 += scoreDonne;
            scorePlayer3 += (-1) * scoreDonne;
            scorePlayer2 += (-1) * scoreDonne;
            scorePlayer1 += (-1) * scoreDonne;

            playerAssocie = 4;
        }

        // Joueur 3 est associé
        if(associe.equals(player3.getNomJoueur()))
        {
            scorePlayer5 += 2 * scoreDonne;
            scorePlayer3 += scoreDonne;
            scorePlayer4 += (-1) * scoreDonne;
            scorePlayer2 += (-1) * scoreDonne;
            scorePlayer1 += (-1) * scoreDonne;

            playerAssocie = 3;
        }

        // Joueur 2 est associé
        if(associe.equals(player2.getNomJoueur()))
        {
            scorePlayer5 += 2 * scoreDonne;
            scorePlayer2 += scoreDonne;
            scorePlayer4 += (-1) * scoreDonne;
            scorePlayer3 += (-1) * scoreDonne;
            scorePlayer1 += (-1) * scoreDonne;

            playerAssocie = 2;
        }

        // Joueur 1 est associé
        if(associe.equals(player1.getNomJoueur()))
        {
            scorePlayer5 += 2 * scoreDonne;
            scorePlayer1 += scoreDonne;
            scorePlayer4 += (-1) * scoreDonne;
            scorePlayer3 += (-1) * scoreDonne;
            scorePlayer2 += (-1) * scoreDonne;

            playerAssocie = 1;
        }

        // Joueur 6 est associé
        if(sixIsTrue)
        {
            if(petitAuBout)
            {
                scorePlayer5 += (-1) * valuePetitAuBout; // The "Mort" doesn't give point
                scorePlayer6 += (-1) * valuePetitAuBout;
            }

            if(associe.equals(player6.getNomJoueur()))
            {
                scorePlayer5 += 2 * scoreDonne;
                scorePlayer6 += scoreDonne;
                scorePlayer3 += (-1) * scoreDonne;
                scorePlayer3 += (-1) * scoreDonne;
                scorePlayer2 += (-1) * scoreDonne;
                scorePlayer1 += (-1) * scoreDonne;

                playerAssocie = 6;
            }

            else
            {
                scorePlayer6 += (-1) * scoreDonne;
            }
        }
    }

    private void setScorePlayer4Winner(int scoreDonne, boolean sixIsTrue, boolean fiveIsTrue)
    {
            if(fiveIsTrue)
            {
                if(petitAuBout)
                {
                    scorePlayer4 += valuePetitAuBout * (nbJoueur - 1); // Player5 doesn't give point to himself
                    scorePlayer5 += (-1) * valuePetitAuBout;
                    scorePlayer3 += (-1) * valuePetitAuBout;
                    scorePlayer2 += (-1) * valuePetitAuBout;
                    scorePlayer1 += (-1) * valuePetitAuBout;
                }
                // Joueur 5 associé
                if(associe.equals(player5.getNomJoueur()))
                {
                    scorePlayer4 += 2 * scoreDonne;
                    scorePlayer5 += scoreDonne;
                    scorePlayer3 += (-1) * scoreDonne;
                    scorePlayer2 += (-1) * scoreDonne;
                    scorePlayer1 += (-1) * scoreDonne;

                    playerAssocie = 5;
                }

                // Joueur 4 associé (joue seul)
                if(associe.equals(player4.getNomJoueur()))
                {
                    scorePlayer4 += 4 * scoreDonne;
                    scorePlayer5 += (-1) * scoreDonne;
                    scorePlayer3 += (-1) * scoreDonne;
                    scorePlayer2 += (-1) * scoreDonne;
                    scorePlayer1 += (-1) * scoreDonne;
                }

                // Joueur 3 associé
                if(associe.equals(player3.getNomJoueur()))
                {
                    scorePlayer4 += 2 * scoreDonne;
                    scorePlayer3 += scoreDonne;
                    scorePlayer5 += (-1) * scoreDonne;
                    scorePlayer2 += (-1) * scoreDonne;
                    scorePlayer1 += (-1) * scoreDonne;

                    playerAssocie = 3;
                }

                // Joueur 2 associé
                if(associe.equals(player2.getNomJoueur()))
                {
                    scorePlayer4 += 2 * scoreDonne;
                    scorePlayer2 += scoreDonne;
                    scorePlayer3 += (-1) * scoreDonne;
                    scorePlayer5 += (-1) * scoreDonne;
                    scorePlayer1 += (-1) * scoreDonne;

                    playerAssocie = 2;
                }

                // Joueur 1 associé
                if(associe.equals(player1.getNomJoueur()))
                {
                    scorePlayer4 += 2 * scoreDonne;
                    scorePlayer1 += scoreDonne;
                    scorePlayer3 += (-1) * scoreDonne;
                    scorePlayer2 += (-1) * scoreDonne;
                    scorePlayer5 += (-1) * scoreDonne;

                    playerAssocie = 1;
                }

                // Joueur 6 est associé
                if(sixIsTrue)
                {
                    if(petitAuBout)
                    {
                        scorePlayer4 += (-1) * valuePetitAuBout; // The "Mort" doesn't give point
                        scorePlayer6 += (-1) * valuePetitAuBout;
                    }

                    if(associe.equals(player6.getNomJoueur()))
                    {
                        scorePlayer4 += 2 * scoreDonne;
                        scorePlayer6 += scoreDonne;
                        scorePlayer5 += (-1) * scoreDonne;
                        scorePlayer3 += (-1) * scoreDonne;
                        scorePlayer2 += (-1) * scoreDonne;
                        scorePlayer1 += (-1) * scoreDonne;

                        playerAssocie = 6;
                    }

                    else
                    {
                        scorePlayer6 += (-1) * scoreDonne;
                    }
                }
            }

            else
            {
                if(petitAuBout)
                {
                    scorePlayer4 += valuePetitAuBout * (nbJoueur - 1); // Player4 doesn't give point to himself
                    scorePlayer3 += (-1) * valuePetitAuBout;
                    scorePlayer2 += (-1) * valuePetitAuBout;
                    scorePlayer1 += (-1) * valuePetitAuBout;
                }

                scorePlayer4 += 3 * scoreDonne;
                scorePlayer3 += (-1) * scoreDonne;
                scorePlayer2 += (-1) * scoreDonne;
                scorePlayer1 += (-1) * scoreDonne;
            }

    }

    private void setScorePlayer3Winner(int scoreDonne, boolean sixIsTrue, boolean fiveIsTrue, boolean fourIsTrue)
    {
        if(fiveIsTrue)
        {
            if(petitAuBout)
            {
                scorePlayer3 += valuePetitAuBout * (nbJoueur - 1); // Player3 doesn't give point to himself
                scorePlayer5 += (-1) * valuePetitAuBout;
                scorePlayer4 += (-1) * valuePetitAuBout;
                scorePlayer2 += (-1) * valuePetitAuBout;
                scorePlayer1 += (-1) * valuePetitAuBout;
            }

            // Joueur 5 associé
            if(associe.equals(player5.getNomJoueur()))
            {
                scorePlayer3 += 2 * scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer4 += (-1) * scoreDonne;
                scorePlayer2 += (-1) * scoreDonne;
                scorePlayer1 += (-1) * scoreDonne;

                playerAssocie = 5;
            }

            // Joueur 4 associé
            if(associe.equals(player4.getNomJoueur()))
            {
                scorePlayer3 += 2 * scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer5 += (-1) * scoreDonne;
                scorePlayer2 += (-1) * scoreDonne;
                scorePlayer1 += (-1) * scoreDonne;

                playerAssocie = 4;
            }

            // Joueur 3 associé (joue seul)
            if(associe.equals(player3.getNomJoueur()))
            {
                scorePlayer3 += 4 * scoreDonne;
                scorePlayer5 += (-1) * scoreDonne;
                scorePlayer4 += (-1) * scoreDonne;
                scorePlayer2 += (-1) * scoreDonne;
                scorePlayer1 += (-1) * scoreDonne;
            }

            // Joueur 2 associé
            if(associe.equals(player2.getNomJoueur()))
            {
                scorePlayer3 += 2 * scoreDonne;
                scorePlayer2 += scoreDonne;
                scorePlayer5 += (-1) * scoreDonne;
                scorePlayer4 += (-1) * scoreDonne;
                scorePlayer1 += (-1) * scoreDonne;

                playerAssocie = 2;
            }

            // Joueur 1 associé
            if(associe.equals(player1.getNomJoueur()))
            {
                scorePlayer3 += 2 * scoreDonne;
                scorePlayer1 += scoreDonne;
                scorePlayer5 += (-1) * scoreDonne;
                scorePlayer4 += (-1) * scoreDonne;
                scorePlayer2 += (-1) * scoreDonne;

                playerAssocie = 1;
            }

            // Joueur 6 est associé
            if(sixIsTrue)
            {
                if(petitAuBout)
                {
                    scorePlayer3 += (-1) * valuePetitAuBout; // The "Mort" doesn't give point
                    scorePlayer6 += (-1) * valuePetitAuBout;
                }

                if(associe.equals(player6.getNomJoueur()))
                {
                    scorePlayer3 += 2 * scoreDonne;
                    scorePlayer6 += scoreDonne;
                    scorePlayer5 += (-1) * scoreDonne;
                    scorePlayer4 += (-1) * scoreDonne;
                    scorePlayer2 += (-1) * scoreDonne;
                    scorePlayer1 += (-1) * scoreDonne;

                    playerAssocie = 6;
                }

                else
                {
                    scorePlayer6 += (-1) * scoreDonne;
                }
            }
        }

        else
        {
            if(petitAuBout)
            {
                scorePlayer3 += valuePetitAuBout * (nbJoueur - 1); // Player3 doesn't give point to himself
                scorePlayer2 += (-1) * valuePetitAuBout;
                scorePlayer1 += (-1) * valuePetitAuBout;
            }

            scorePlayer3 += 2 * scoreDonne;
            scorePlayer2 += (-1) * scoreDonne;
            scorePlayer1 += (-1) * scoreDonne;

            if (fourIsTrue)
            {
                if(petitAuBout)
                {
                    scorePlayer4 += (-1) * valuePetitAuBout;
                }

                scorePlayer3 += scoreDonne;
                scorePlayer4 += (-1) * scoreDonne;
            }


        }
    }

    private void setScorePlayer2Winner(int scoreDonne, boolean sixIsTrue, boolean fiveIsTrue, boolean fourIsTrue)
    {
        if(fiveIsTrue)
        {
            if(petitAuBout)
            {
                scorePlayer2 += valuePetitAuBout * (nbJoueur - 1); // Player2 doesn't give point to himself
                scorePlayer5 += (-1) * valuePetitAuBout;
                scorePlayer4 += (-1) * valuePetitAuBout;
                scorePlayer3 += (-1) * valuePetitAuBout;
                scorePlayer1 += (-1) * valuePetitAuBout;
            }

            // Joueur 5 associé
            if(associe.equals(player5.getNomJoueur()))
            {
                scorePlayer2 += 2 * scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer4 += (-1) * scoreDonne;
                scorePlayer3 += (-1) * scoreDonne;
                scorePlayer1 += (-1) * scoreDonne;

                playerAssocie = 5;
            }

            // Joueur 4 associé
            if(associe.equals(player4.getNomJoueur()))
            {
                scorePlayer2 += 2 * scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer5 += (-1) * scoreDonne;
                scorePlayer3 += (-1) * scoreDonne;
                scorePlayer1 += (-1) * scoreDonne;

                playerAssocie = 4;
            }

            // Joueur 3 associé
            if(associe.equals(player5.getNomJoueur()))
            {
                scorePlayer2 += 2 * scoreDonne;
                scorePlayer3 += scoreDonne;
                scorePlayer5 += (-1) * scoreDonne;
                scorePlayer4 += (-1) * scoreDonne;
                scorePlayer1 += (-1) * scoreDonne;

                playerAssocie = 3;
            }

            // Joueur 2 associé (joue seul)
            if(associe.equals(player2.getNomJoueur()))
            {
                scorePlayer2 += 4 * scoreDonne;
                scorePlayer5 += (-1) * scoreDonne;
                scorePlayer4 += (-1) * scoreDonne;
                scorePlayer3 += (-1) * scoreDonne;
                scorePlayer1 += (-1) * scoreDonne;
            }

            // Joueur 1 associé
            if(associe.equals(player1.getNomJoueur()))
            {
                scorePlayer2 += 2 * scoreDonne;
                scorePlayer1 += scoreDonne;
                scorePlayer5 += (-1) * scoreDonne;
                scorePlayer4 += (-1) * scoreDonne;
                scorePlayer3 += (-1) * scoreDonne;

                playerAssocie = 1;
            }

            // Joueur 6 est associé
            if(sixIsTrue)
            {
                if(petitAuBout)
                {
                    scorePlayer2 += (-1) * valuePetitAuBout; // The "Mort" doesn't give point
                    scorePlayer6 += (-1) * valuePetitAuBout;
                }

                if(associe.equals(player6.getNomJoueur()))
                {
                    scorePlayer2 += 2 * scoreDonne;
                    scorePlayer6 += scoreDonne;
                    scorePlayer5 += (-1) * scoreDonne;
                    scorePlayer4 += (-1) * scoreDonne;
                    scorePlayer3 += (-1) * scoreDonne;
                    scorePlayer1 += (-1) * scoreDonne;

                    playerAssocie = 6;
                }

                else
                {
                    scorePlayer6 += (-1) * scoreDonne;
                }
            }
        }

        else
        {
            if(petitAuBout)
            {
                scorePlayer2 += valuePetitAuBout * (nbJoueur - 1); // Player2 doesn't give point to himself
                scorePlayer3 += (-1) * valuePetitAuBout;
                scorePlayer1 += (-1) * valuePetitAuBout;
            }

            scorePlayer2 += 2 * scoreDonne;
            scorePlayer1 += (-1) * scoreDonne;
            scorePlayer3 += (-1) * scoreDonne;

            if (fourIsTrue)
            {
                if(petitAuBout)
                {
                    scorePlayer4 += (-1) * valuePetitAuBout;
                }

                scorePlayer2 += scoreDonne;
                scorePlayer4 += (-1) * scoreDonne;
            }
        }
    }

    private void setScorePlayer1Winner(int scoreDonne, boolean sixIsTrue, boolean fiveIsTrue, boolean fourIsTrue)
    {
        if(fiveIsTrue)
        {
            if(petitAuBout)
            {
                scorePlayer1 += valuePetitAuBout * (nbJoueur - 1); // Player1 doesn't give point to himself
                scorePlayer5 += (-1) * valuePetitAuBout;
                scorePlayer4 += (-1) * valuePetitAuBout;
                scorePlayer3 += (-1) * valuePetitAuBout;
                scorePlayer2 += (-1) * valuePetitAuBout;
            }

            // Joueur 5 associé
            if(associe.equals(player5.getNomJoueur()))
            {
                scorePlayer1 += 2 * scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer4 += (-1) * scoreDonne;
                scorePlayer3 += (-1) * scoreDonne;
                scorePlayer2 += (-1) * scoreDonne;

                playerAssocie = 5;
            }

            // Joueur 4 associé
            if(associe.equals(player4.getNomJoueur()))
            {
                scorePlayer1 += 2 * scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer5 += (-1) * scoreDonne;
                scorePlayer3 += (-1) * scoreDonne;
                scorePlayer2 += (-1) * scoreDonne;

                playerAssocie = 4;
            }

            // Joueur 3 associé
            if(associe.equals(player3.getNomJoueur()))
            {
                scorePlayer1 += 2 * scoreDonne;
                scorePlayer3 += scoreDonne;
                scorePlayer4 += (-1) * scoreDonne;
                scorePlayer5 += (-1) * scoreDonne;
                scorePlayer2 += (-1) * scoreDonne;

                playerAssocie = 3;
            }

            // Joueur 2 associé
            if(associe.equals(player2.getNomJoueur()))
            {
                scorePlayer1 += 2 * scoreDonne;
                scorePlayer2 += scoreDonne;
                scorePlayer3 += (-1) * scoreDonne;
                scorePlayer4 += (-1) * scoreDonne;
                scorePlayer5 += (-1) * scoreDonne;

                playerAssocie = 2;
            }

            // Joueur 1 associé (joue seul)
            if(associe.equals(player1.getNomJoueur()))
            {
                scorePlayer1 += 4 * scoreDonne;
                scorePlayer2 += (-1) * scoreDonne;
                scorePlayer3 += (-1) * scoreDonne;
                scorePlayer4 += (-1) * scoreDonne;
                scorePlayer5 += (-1) * scoreDonne;
            }

            // Joueur 6 est associé
            if(sixIsTrue)
            {
                if(petitAuBout)
                {
                    scorePlayer1 += (-1) * valuePetitAuBout; // The "Mort" doesn't give point
                    scorePlayer6 += (-1) * valuePetitAuBout;
                }

                if(associe.equals(player6.getNomJoueur()))
                {
                    scorePlayer1 += 2 * scoreDonne;
                    scorePlayer6 += scoreDonne;
                    scorePlayer5 += (-1) * scoreDonne;
                    scorePlayer4 += (-1) * scoreDonne;
                    scorePlayer3 += (-1) * scoreDonne;
                    scorePlayer2 += (-1) * scoreDonne;

                    playerAssocie = 6;
                }

                else
                {
                    scorePlayer6 += (-1) * scoreDonne;
                }
            }

        }

        else
        {
            if(petitAuBout)
            {
                scorePlayer1 += valuePetitAuBout * (nbJoueur - 1); // Player1 doesn't give point to himself
                scorePlayer3 += (-1) * valuePetitAuBout;
                scorePlayer2 += (-1) * valuePetitAuBout;
            }

            scorePlayer1 += 2 * scoreDonne;
            scorePlayer2 += (-1) * scoreDonne;
            scorePlayer3 += (-1) * scoreDonne;

            if (fourIsTrue)
            {
                if(petitAuBout)
                {
                    scorePlayer4 += (-1) * valuePetitAuBout;
                }

                scorePlayer1 += scoreDonne;
                scorePlayer4 += (-1) * scoreDonne;
            }
        }
    }

    private void setScorePlayer6Looser(int scoreDonne)
    {
        // Joueur 6 est associé (joue seul)
        if(associe.equals(player6.getNomJoueur()))
        {
            scorePlayer6 += (-4) * scoreDonne;
            scorePlayer5 += scoreDonne;
            scorePlayer4 += scoreDonne;
            scorePlayer3 += scoreDonne;
            scorePlayer2 += scoreDonne;
            scorePlayer1 += scoreDonne;
        }

        // Joueur 5 est associé
        if(associe.equals(player5.getNomJoueur()))
        {
            scorePlayer6 += (-2) * scoreDonne;
            scorePlayer5 += (-1) * scoreDonne;
            scorePlayer4 += scoreDonne;
            scorePlayer3 += scoreDonne;
            scorePlayer2 += scoreDonne;
            scorePlayer1 += scoreDonne;

            playerAssocie = 5;

        }

        // Joueur 4 est associé
        if(associe.equals(player4.getNomJoueur()))
        {
            scorePlayer6 += (-2) * scoreDonne;
            scorePlayer4 += (-1) * scoreDonne;
            scorePlayer5 += scoreDonne;
            scorePlayer3 += scoreDonne;
            scorePlayer2 += scoreDonne;
            scorePlayer1 += scoreDonne;

            playerAssocie = 4;
        }

        // Joueur 3 est associé
        if(associe.equals(player3.getNomJoueur()))
        {
            scorePlayer6 += (-2) * scoreDonne;
            scorePlayer3 += (-1) * scoreDonne;
            scorePlayer4 += scoreDonne;
            scorePlayer5 += scoreDonne;
            scorePlayer2 += scoreDonne;
            scorePlayer1 += scoreDonne;

            playerAssocie = 3;
        }

        // Joueur 2 est associé
        if(associe.equals(player2.getNomJoueur()))
        {
            scorePlayer6 += (-2) * scoreDonne;
            scorePlayer2 += (-1) * scoreDonne;
            scorePlayer4 += scoreDonne;
            scorePlayer3 += scoreDonne;
            scorePlayer5 += scoreDonne;
            scorePlayer1 += scoreDonne;

            playerAssocie = 2;
        }

        // Joueur 1 est associé
        if(associe.equals(player1.getNomJoueur()))
        {
            scorePlayer6 += (-2) * scoreDonne;
            scorePlayer1 += (-1) * scoreDonne;
            scorePlayer4 += scoreDonne;
            scorePlayer3 += scoreDonne;
            scorePlayer2 += scoreDonne;
            scorePlayer5 += scoreDonne;

            playerAssocie = 1;
        }
    }


    private void setScorePlayer5Looser(int scoreDonne, boolean sixIsTrue)
    {

        // Joueur 5 est associé (joue seul)
        if(associe.equals(player5.getNomJoueur()))
        {
            scorePlayer5 += (-4) * scoreDonne;
            scorePlayer4 += scoreDonne;
            scorePlayer3 += scoreDonne;
            scorePlayer2 += scoreDonne;
            scorePlayer1 += scoreDonne;
        }

        // Joueur 4 est associé
        if(associe.equals(player4.getNomJoueur()))
        {
            scorePlayer5 += (-2) * scoreDonne;
            scorePlayer4 += (-1) * scoreDonne;
            scorePlayer3 += scoreDonne;
            scorePlayer2 += scoreDonne;
            scorePlayer1 += scoreDonne;

            playerAssocie = 4;
        }

        // Joueur 3 est associé
        if(associe.equals(player3.getNomJoueur()))
        {
            scorePlayer5 += (-2) * scoreDonne;
            scorePlayer3 += (-1) * scoreDonne;
            scorePlayer4 += scoreDonne;
            scorePlayer2 += scoreDonne;
            scorePlayer1 += scoreDonne;

            playerAssocie = 3;
        }

        // Joueur 2 est associé
        if(associe.equals(player2.getNomJoueur()))
        {
            scorePlayer5 += (-2) * scoreDonne;
            scorePlayer2 += (-1) * scoreDonne;
            scorePlayer4 += scoreDonne;
            scorePlayer3 += scoreDonne;
            scorePlayer1 += scoreDonne;

            playerAssocie = 2;
        }

        // Joueur 1 est associé
        if(associe.equals(player1.getNomJoueur()))
        {
            scorePlayer5 += (-2) * scoreDonne;
            scorePlayer1 += (-1) * scoreDonne;
            scorePlayer4 += scoreDonne;
            scorePlayer3 += scoreDonne;
            scorePlayer2 += scoreDonne;

            playerAssocie = 1;
        }

        if(sixIsTrue)
        {
            // Joueur 6 est associé
            if(associe.equals(player6.getNomJoueur()))
            {
                scorePlayer5 += (-2) * scoreDonne;
                scorePlayer6 += (-1) * scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer3 += scoreDonne;
                scorePlayer2 += scoreDonne;
                scorePlayer1 += scoreDonne;

                playerAssocie = 6;
            }

            else
            {
                scorePlayer6 += scoreDonne;
            }
        }
    }


    private void setScorePlayer4Looser(int scoreDonne, boolean sixIsTrue, boolean fiveIsTrue)
    {
        if(fiveIsTrue)
        {
            // Joueur 5 associé
            if(associe.equals(player5.getNomJoueur()))
            {
                scorePlayer4 += (-2) * scoreDonne;
                scorePlayer5 += (-1) * scoreDonne;
                scorePlayer3 += scoreDonne;
                scorePlayer2 += scoreDonne;
                scorePlayer1 += scoreDonne;

                playerAssocie = 5;
            }

            // Joueur 4 associé (joue seul)
            if(associe.equals(player4.getNomJoueur()))
            {
                scorePlayer4 += (-4) * scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer3 += scoreDonne;
                scorePlayer2 += scoreDonne;
                scorePlayer1 += scoreDonne;
            }

            // Joueur 3 associé
            if(associe.equals(player3.getNomJoueur()))
            {
                scorePlayer4 += (-2) * scoreDonne;
                scorePlayer3 += (-1) * scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer2 += scoreDonne;
                scorePlayer1 += scoreDonne;

                playerAssocie = 3;
            }

            // Joueur 2 associé
            if(associe.equals(player2.getNomJoueur()))
            {
                scorePlayer4 += (-2) * scoreDonne;
                scorePlayer2 += (-1) * scoreDonne;
                scorePlayer3 += scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer1 += scoreDonne;

                playerAssocie = 2;
            }

            // Joueur 1 associé
            if(associe.equals(player1.getNomJoueur()))
            {
                scorePlayer4 += (-2) * scoreDonne;
                scorePlayer1 += (-1) * scoreDonne;
                scorePlayer3 += scoreDonne;
                scorePlayer2 += scoreDonne;
                scorePlayer5 += scoreDonne;

                playerAssocie = 1;
            }
        }

        else
        {
            scorePlayer4 += ((-3) * scoreDonne);
            scorePlayer3 += scoreDonne;
            scorePlayer2 += scoreDonne;
            scorePlayer1 += scoreDonne;
        }

        if(sixIsTrue)
        {
            // Joueur 6 est associé
            if(associe.equals(player6.getNomJoueur()))
            {
                scorePlayer4 += (-2) * scoreDonne;
                scorePlayer6 += (-1) * scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer3 += scoreDonne;
                scorePlayer2 += scoreDonne;
                scorePlayer1 += scoreDonne;

                playerAssocie = 6;
            }

            else
            {
                scorePlayer6 += scoreDonne;
            }
        }

    }

    private void setScorePlayer3Looser(int scoreDonne, boolean sixIsTrue, boolean fiveIsTrue, boolean fourIsTrue)
    {
        if(fiveIsTrue)
        {
            // Joueur 5 associé
            if(associe.equals(player5.getNomJoueur()))
            {
                scorePlayer3 += (-2) * scoreDonne;
                scorePlayer5 += (-1) * scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer2 += scoreDonne;
                scorePlayer1 += scoreDonne;

                playerAssocie = 5;
            }

            // Joueur 4 associé
            if(associe.equals(player4.getNomJoueur()))
            {
                scorePlayer3 += (-2) * scoreDonne;
                scorePlayer4 += (-1) * scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer2 += scoreDonne;
                scorePlayer1 += scoreDonne;

                playerAssocie = 4;
            }

            // Joueur 3 associé (joue seul)
            if(associe.equals(player3.getNomJoueur()))
            {
                scorePlayer3 += (-4) * scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer2 += scoreDonne;
                scorePlayer1 += scoreDonne;
            }

            // Joueur 2 associé
            if(associe.equals(player2.getNomJoueur()))
            {
                scorePlayer3 += (-2) * scoreDonne;
                scorePlayer2 += (-1) * scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer1 += scoreDonne;

                playerAssocie = 2;
            }

            // Joueur 1 associé
            if(associe.equals(player1.getNomJoueur()))
            {
                scorePlayer3 += (-2) * scoreDonne;
                scorePlayer1 += (-1) * scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer2 += scoreDonne;

                playerAssocie = 1;
            }
        }

        else
        {
            scorePlayer3 += (-2) * scoreDonne;
            scorePlayer2 += scoreDonne;
            scorePlayer1 += scoreDonne;

            if (fourIsTrue)
            {
                scorePlayer3 += (-1) * scoreDonne;
                scorePlayer4 += scoreDonne;
            }
        }

        if(sixIsTrue)
        {
            // Joueur 6 est associé
            if(associe.equals(player6.getNomJoueur()))
            {
                scorePlayer3 += (-2) * scoreDonne;
                scorePlayer6 += (-1) * scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer2 += scoreDonne;
                scorePlayer1 += scoreDonne;

                playerAssocie = 6;
            }

            else
            {
                scorePlayer6 += scoreDonne;
            }
        }
    }

    private void setScorePlayer2Looser(int scoreDonne, boolean sixIsTrue, boolean fiveIsTrue, boolean fourIsTrue)
    {
        if(fiveIsTrue)
        {
            // Joueur 5 associé
            if(associe.equals(player5.getNomJoueur()))
            {
                scorePlayer2 += (-2) * scoreDonne;
                scorePlayer5 += (-1) * scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer3 += scoreDonne;
                scorePlayer1 += scoreDonne;

                playerAssocie = 5;
            }

            // Joueur 4 associé
            if(associe.equals(player4.getNomJoueur()))
            {
                scorePlayer2 += (-2) * scoreDonne;
                scorePlayer4 += (-1) * scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer3 += scoreDonne;
                scorePlayer1 += scoreDonne;

                playerAssocie = 4;
            }

            // Joueur 3 associé
            if(associe.equals(player5.getNomJoueur()))
            {
                scorePlayer2 += (-2) * scoreDonne;
                scorePlayer3 += (-1) * scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer1 += scoreDonne;

                playerAssocie = 3;
            }

            // Joueur 2 associé (joue seul)
            if(associe.equals(player2.getNomJoueur()))
            {
                scorePlayer2 += (-4) * scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer3 += scoreDonne;
                scorePlayer1 += scoreDonne;
            }

            // Joueur 1 associé
            if(associe.equals(player1.getNomJoueur()))
            {
                scorePlayer2 += (-2) * scoreDonne;
                scorePlayer1 += (-1) * scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer3 += scoreDonne;

                playerAssocie = 1;
            }
        }

        else
        {
            scorePlayer2 += (-2) * scoreDonne;
            scorePlayer1 += scoreDonne;
            scorePlayer3 += scoreDonne;

            if (fourIsTrue)
            {
                scorePlayer2 += (-1) * scoreDonne;
                scorePlayer4 += scoreDonne;
            }
        }

        if(sixIsTrue)
        {
            // Joueur 6 est associé
            if(associe.equals(player6.getNomJoueur()))
            {
                scorePlayer2 += (-2) * scoreDonne;
                scorePlayer6 += (-1) * scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer3 += scoreDonne;
                scorePlayer1 += scoreDonne;

                playerAssocie = 6;
            }

            else
            {
                scorePlayer6 += scoreDonne;
            }
        }
    }

    private void setScorePlayer1Looser(int scoreDonne, boolean sixIsTrue, boolean fiveIsTrue, boolean fourIsTrue)
    {
        if(fiveIsTrue)
        {
            // Joueur 5 associé
            if(associe.equals(player5.getNomJoueur()))
            {
                scorePlayer1 += (-2) * scoreDonne;
                scorePlayer5 += (-1) * scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer3 += scoreDonne;
                scorePlayer2 += scoreDonne;

                playerAssocie = 5;
            }

            // Joueur 4 associé
            if(associe.equals(player4.getNomJoueur()))
            {
                scorePlayer1 += (-2) * scoreDonne;
                scorePlayer4 += (-1) * scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer3 += scoreDonne;
                scorePlayer2 += scoreDonne;

                playerAssocie = 4;
            }

            // Joueur 3 associé
            if(associe.equals(player3.getNomJoueur()))
            {
                scorePlayer1 += (-2) * scoreDonne;
                scorePlayer3 += (-1) * scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer2 += scoreDonne;

                playerAssocie = 3;
            }

            // Joueur 2 associé
            if(associe.equals(player2.getNomJoueur()))
            {
                scorePlayer1 += (-2) * scoreDonne;
                scorePlayer2 += (-1) * scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer3 += scoreDonne;
                scorePlayer5 += scoreDonne;

                playerAssocie = 2;
            }

            // Joueur 1 associé (joue seul)
            if(associe.equals(player1.getNomJoueur()))
            {
                scorePlayer1 += (-4) * scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer3 += scoreDonne;
                scorePlayer2 += scoreDonne;

                playerAssocie = 1;
            }

        }

        else
        {
            scorePlayer1 += (-2) * scoreDonne;
            scorePlayer2 += scoreDonne;
            scorePlayer3 += scoreDonne;

            if (fourIsTrue)
            {
                scorePlayer1 += (-1) * scoreDonne;
                scorePlayer4 += scoreDonne;
            }
        }

        if(sixIsTrue)
        {
            // Joueur 6 est associé
            if(associe.equals(player6.getNomJoueur()))
            {
                scorePlayer1 += (-2) * scoreDonne;
                scorePlayer6 += (-1) * scoreDonne;
                scorePlayer5 += scoreDonne;
                scorePlayer4 += scoreDonne;
                scorePlayer3 += scoreDonne;
                scorePlayer2 += scoreDonne;

                playerAssocie = 6;
            }

            else
            {
                scorePlayer6 += scoreDonne;
            }
        }
    }

    private void setPartPlayerDetail(int numJeu)
    {
        switch(playerWinner)
        {
            case 6:
                player6.addJeuPris(numJeu);
                player6.addVictoire(numJeu);
                switch (enchere)
                {
                    case "Petite (20pts)":
                        player6.addPetite(numJeu);
                        break;

                    case "Pouce (40pts)":
                        player6.addPouce(numJeu);
                        break;

                    case "Garde (80pts)":
                        player6.addGarde(numJeu);
                        break;

                    case "Garde sans chien (160pts)":
                        player6.addGardeSans(numJeu);
                        break;

                    case "Garde contre chien (320pts)":
                        player6.addGardeContre(numJeu);
                        break;
                }

                break;
            case 5:
                player5.addJeuPris(numJeu);
                player5.addVictoire(numJeu);
                switch (enchere)
                {
                    case "Petite (20pts)":
                        player5.addPetite(numJeu);
                        break;

                    case "Pouce (40pts)":
                        player5.addPouce(numJeu);
                        break;

                    case "Garde (80pts)":
                        player5.addGarde(numJeu);
                        break;

                    case "Garde sans chien (160pts)":
                        player5.addGardeSans(numJeu);
                        break;

                    case "Garde contre chien (320pts)":
                        player5.addGardeContre(numJeu);
                        break;
                }

                break;

            case 4:
                player4.addJeuPris(numJeu);
                player4.addVictoire(numJeu);
                switch (enchere)
                {
                    case "Petite (20pts)":
                        player4.addPetite(numJeu);
                        break;

                    case "Pouce (40pts)":
                        player4.addPouce(numJeu);
                        break;

                    case "Garde (80pts)":
                        player4.addGarde(numJeu);
                        break;

                    case "Garde sans chien (160pts)":
                        player4.addGardeSans(numJeu);
                        break;

                    case "Garde contre chien (320pts)":
                        player4.addGardeContre(numJeu);
                        break;
                }

                break;

            case 3:
                player3.addJeuPris(numJeu);
                player3.addVictoire(numJeu);
                switch (enchere)
                {
                    case "Petite (20pts)":
                        player3.addPetite(numJeu);
                        break;

                    case "Pouce (40pts)":
                        player3.addPouce(numJeu);
                        break;

                    case "Garde (80pts)":
                        player3.addGarde(numJeu);
                        break;

                    case "Garde sans chien (160pts)":
                        player3.addGardeSans(numJeu);
                        break;

                    case "Garde contre chien (320pts)":
                        player3.addGardeContre(numJeu);
                        break;
                }

                break;

            case 2:
                player2.addJeuPris(numJeu);
                player2.addVictoire(numJeu);
                switch (enchere)
                {
                    case "Petite (20pts)":
                        player2.addPetite(numJeu);
                        break;

                    case "Pouce (40pts)":
                        player2.addPouce(numJeu);
                        break;

                    case "Garde (80pts)":
                        player2.addGarde(numJeu);
                        break;

                    case "Garde sans chien (160pts)":
                        player2.addGardeSans(numJeu);
                        break;

                    case "Garde contre chien (320pts)":
                        player2.addGardeContre(numJeu);
                        break;
                }

                break;

            case 1:
                player1.addJeuPris(numJeu);
                player1.addVictoire(numJeu);
                switch (enchere)
                {
                    case "Petite (20pts)":
                        player1.addPetite(numJeu);
                        break;

                    case "Pouce (40pts)":
                        player1.addPouce(numJeu);
                        break;

                    case "Garde (80pts)":
                        player1.addGarde(numJeu);
                        break;

                    case "Garde sans chien (160pts)":
                        player1.addGardeSans(numJeu);
                        break;

                    case "Garde contre chien (320pts)":
                        player1.addGardeContre(numJeu);
                        break;
                }

                break;

            default:
                break;
        }

        switch(playerLooser)
        {
            case 6:

                player6.addDefaite(numJeu);

                if(jeuALEnvers)
                {
                    player6.addJeuALEnvers(numJeu);
                    break;
                }

                player6.addJeuPris(numJeu);

                switch (enchere)
                {
                    case "Petite (20pts)":
                        player6.addPetite(numJeu);
                        break;

                    case "Pouce (40pts)":
                        player6.addPouce(numJeu);
                        break;

                    case "Garde (80pts)":
                        player6.addGarde(numJeu);
                        break;

                    case "Garde sans chien (160pts)":
                        player6.addGardeSans(numJeu);
                        break;

                    case "Garde contre chien (320pts)":
                        player6.addGardeContre(numJeu);
                        break;
                }

                break;

            case 5:

                player5.addDefaite(numJeu);

                if(jeuALEnvers)
                {
                    player5.addJeuALEnvers(numJeu);
                    break;
                }

                player5.addJeuPris(numJeu);

                switch (enchere)
                {
                    case "Petite (20pts)":
                        player5.addPetite(numJeu);
                        break;

                    case "Pouce (40pts)":
                        player5.addPouce(numJeu);
                        break;

                    case "Garde (80pts)":
                        player5.addGarde(numJeu);
                        break;

                    case "Garde sans chien (160pts)":
                        player5.addGardeSans(numJeu);
                        break;

                    case "Garde contre chien (320pts)":
                        player5.addGardeContre(numJeu);
                        break;
                }

                break;

            case 4:

                player4.addDefaite(numJeu);

                if(jeuALEnvers)
                {
                    player4.addJeuALEnvers(numJeu);
                    break;
                }

                player4.addJeuPris(numJeu);

                switch (enchere)
                {
                    case "Petite (20pts)":
                        player4.addPetite(numJeu);
                        break;

                    case "Pouce (40pts)":
                        player4.addPouce(numJeu);
                        break;

                    case "Garde (80pts)":
                        player4.addGarde(numJeu);
                        break;

                    case "Garde sans chien (160pts)":
                        player4.addGardeSans(numJeu);
                        break;

                    case "Garde contre chien (320pts)":
                        player4.addGardeContre(numJeu);
                        break;
                }

                break;

            case 3:

                player3.addDefaite(numJeu);

                if(jeuALEnvers)
                {
                    player3.addJeuALEnvers(numJeu);
                    break;
                }

                player3.addJeuPris(numJeu);

                switch (enchere)
                {
                    case "Petite (20pts)":
                        player3.addPetite(numJeu);
                        break;

                    case "Pouce (40pts)":
                        player3.addPouce(numJeu);
                        break;

                    case "Garde (80pts)":
                        player3.addGarde(numJeu);
                        break;

                    case "Garde sans chien (160pts)":
                        player3.addGardeSans(numJeu);
                        break;

                    case "Garde contre chien (320pts)":
                        player3.addGardeContre(numJeu);
                        break;
                }
                break;

            case 2:

                player2.addDefaite(numJeu);

                if(jeuALEnvers)
                {
                    player2.addJeuALEnvers(numJeu);
                    break;
                }

                player2.addJeuPris(numJeu);

                switch (enchere)
                {
                    case "Petite (20pts)":
                        player2.addPetite(numJeu);
                        break;

                    case "Pouce (40pts)":
                        player2.addPouce(numJeu);
                        break;

                    case "Garde (80pts)":
                        player2.addGarde(numJeu);
                        break;

                    case "Garde sans chien (160pts)":
                        player2.addGardeSans(numJeu);
                        break;

                    case "Garde contre chien (320pts)":
                        player2.addGardeContre(numJeu);
                        break;
                }
                break;

            case 1:

                player1.addDefaite(numJeu);

                if(jeuALEnvers)
                {
                    player1.addJeuALEnvers(numJeu);
                    break;
                }

                player1.addJeuPris(numJeu);
                switch (enchere)
                {
                    case "Petite (20pts)":
                        player1.addPetite(numJeu);
                        break;

                    case "Pouce (40pts)":
                        player1.addPouce(numJeu);
                        break;

                    case "Garde (80pts)":
                        player1.addGarde(numJeu);
                        break;

                    case "Garde sans chien (160pts)":
                        player1.addGardeSans(numJeu);
                        break;

                    case "Garde contre chien (320pts)":
                        player1.addGardeContre(numJeu);
                        break;
                }
                break;

            default:
                break;
        }
    }

    private void setAssociePlayerDetail(int numJeu)
    {
        switch(playerAssocie)
        {
            case 6:
                player6.add1FoisAssocie(numJeu);
                break;

            case 5:
                player5.add1FoisAssocie(numJeu);
                break;

            case 4:
                player4.add1FoisAssocie(numJeu);
                break;

            case 3:
                player3.add1FoisAssocie(numJeu);
                break;

            case 2:
                player2.add1FoisAssocie(numJeu);
                break;

            case 1:
                player1.add1FoisAssocie(numJeu);
                break;
        }
    }

    private void setAnnoncePlayerDetail(int numJeu, String annonce)
    {
        switch(playerAnnonce)
        {
            case 6:
                switch(annonce)
                {
                    case "Simple misère":
                        player6.addMisere(numJeu);
                        break;

                    case "Double misère":
                        player6.addMisere(numJeu);
                        break;

                    case "Poignet petite":
                        player6.addPoignet(numJeu);
                        break;

                    case "Poignet moyenne":
                        player6.addPoignet(numJeu);
                        break;

                    case "Poignet grande":
                        player6.addPoignet(numJeu);
                        break;
                }
                break;

            case 5:
                switch(annonce)
                {
                    case "Simple misère":
                        player5.addMisere(numJeu);
                        break;

                    case "Double misère":
                        player5.addMisere(numJeu);
                        break;

                    case "Poignet petite":
                        player5.addPoignet(numJeu);
                        break;

                    case "Poignet moyenne":
                        player5.addPoignet(numJeu);
                        break;

                    case "Poignet grande":
                        player5.addPoignet(numJeu);
                        break;
                }
                break;

            case 4:
                switch(annonce)
                {
                    case "Simple misère":
                        player4.addMisere(numJeu);
                        break;

                    case "Double misère":
                        player4.addMisere(numJeu);
                        break;

                    case "Poignet petite":
                        player4.addPoignet(numJeu);
                        break;

                    case "Poignet moyenne":
                        player4.addPoignet(numJeu);
                        break;

                    case "Poignet grande":
                        player4.addPoignet(numJeu);
                        break;
                }

                break;

            case 3:
                switch(annonce)
                {
                    case "Simple misère":
                        player3.addMisere(numJeu);
                        break;

                    case "Double misère":
                        player3.addMisere(numJeu);
                        break;

                    case "Poignet petite":
                        player3.addPoignet(numJeu);
                        break;

                    case "Poignet moyenne":
                        player3.addPoignet(numJeu);
                        break;

                    case "Poignet grande":
                        player3.addPoignet(numJeu);
                        break;
                }
                break;

            case 2:
                switch(annonce)
                {
                    case "Simple misère":
                        player2.addMisere(numJeu);
                        break;

                    case "Double misère":
                        player2.addMisere(numJeu);
                        break;

                    case "Poignet petite":
                        player2.addPoignet(numJeu);
                        break;

                    case "Poignet moyenne":
                        player2.addPoignet(numJeu);
                        break;

                    case "Poignet grande":
                        player2.addPoignet(numJeu);
                        break;
                }
                break;

            case 1:
                switch(annonce)
                {
                    case "Simple misère":
                        player1.addMisere(numJeu);
                        break;

                    case "Double misère":
                        player1.addMisere(numJeu);
                        break;

                    case "Poignet petite":
                        player1.addPoignet(numJeu);
                        break;

                    case "Poignet moyenne":
                        player1.addPoignet(numJeu);
                        break;

                    case "Poignet grande":
                        player1.addPoignet(numJeu);
                        break;
                }
                break;

            default:
                break;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(nbJoueur);
        dest.writeString(donneur);
        dest.writeString(preneur);
        dest.writeString(associe);
        dest.writeString(enchere);
        dest.writeInt(nbBout);
        dest.writeString(calculscoreDe);
        dest.writeDouble(score);
    }

    protected Jeu(Parcel in)
    {

    }

    public static final Parcelable.Creator<Jeu> CREATOR = new Parcelable.Creator<Jeu>() {
        public Jeu createFromParcel(Parcel source) {
            return new Jeu(source);
        }

        public Jeu[] newArray(int size) {
            return new Jeu[size];
        }
    };
}
