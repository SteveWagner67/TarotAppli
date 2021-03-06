package com.example.steve.tarot;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Steve on 06/06/2016.
 */
public class Details implements Parcelable{

    private int numJeu;
    private String preneur;
    private String associe;
    private String enchere;
    private ArrayList<String> annonceList, joueurList;
    private String preneurForPersonne;
    private int nbBout;
    private String calculScoreDe;
    private double scoreCartes;
    private double finalValue;
    private int roundFinalValue;
    private boolean preneurWin;
    private String playerMort;
    private boolean petitAuBout;
    private boolean partieAnnule;

    private ArrayList<Player> classmntPlayersList;


    public Details()
    {
        numJeu = 0;
        preneur = "";
        associe = "";
        enchere = "";
        preneurForPersonne = "";
        nbBout = -1;
        calculScoreDe = "";
        scoreCartes = -1.0;
        finalValue = -1.0;
        roundFinalValue = -1;
        playerMort = "";
        classmntPlayersList = new ArrayList<>();
        partieAnnule = false;
    }

    public void setNumJeu(int numJeu)
    {
        this.numJeu = numJeu;
    }


    public int getNumJeu()
    {
        return numJeu;
    }

    public void setPreneur(String preneur)
    {
        this.preneur = preneur;
    }

    public String getPreneur()
    {
        return preneur;
    }

    public void setEnchere(String enchere)
    {
        this.enchere = enchere;
    }

    public String getEnchere()
    {
        return enchere;
    }

    public void setAssocie(String associe)
    {
        this.associe = associe;
    }

    public String getAssocie()
    {
        return associe;
    }

    public void setAnnonceList(ArrayList<String> annonceList)
    {
        this.annonceList = annonceList;
    }

    public ArrayList<String> getAnnonceList()
    {
        return annonceList;
    }

    public void setJoueurList(ArrayList<String> joueurList)
    {
        this.joueurList = joueurList;
    }

    public ArrayList<String> getJoueurList()
    {
        return joueurList;
    }

    public void setPreneurForPersonne(String preneurForPersonne)
    {
        this.preneurForPersonne = preneurForPersonne;
    }

    public String getPreneurForPersonne()
    {
        return preneurForPersonne;
    }

    public void setNbBout(int nbBout)
    {
        this.nbBout = nbBout;
    }

    public int getNbBout()
    {
        return nbBout;
    }

    public void setCalculScoreDe(String calculScoreDe)
    {
        this.calculScoreDe = calculScoreDe;
    }

    public String getCalculScoreDe()
    {
        return calculScoreDe;
    }

    public void setScoreCartes(double scoreCartes, double finalValue, int roundFinalValue)
    {
        this.scoreCartes = scoreCartes;
        this.finalValue = finalValue;
        this.roundFinalValue = roundFinalValue;
    }

    public double getScoreCartes()
    {
        return scoreCartes;
    }

    public double getFinalValue()
    {
        return finalValue;
    }

    public int getRoundFinalValue()
    {
        return roundFinalValue;
    }

    public void setResult(boolean preneurWin)
    {
        this.preneurWin = preneurWin;
    }

    public boolean getResult()
    {
        return preneurWin;
    }

    public void setPlayerMort(String playerMort)
    {
        this.playerMort = playerMort;
    }

    public String getPlayerMort()
    {
        return playerMort;
    }

    public void setClassmtPlayers(ArrayList<Player> classmntPlayersList) { this.classmntPlayersList = classmntPlayersList;}

    public ArrayList<Player> getClassmntPlayersList() {return classmntPlayersList;}

    public void setPetitAuBout(boolean petitAuBout) {this.petitAuBout = petitAuBout; }

    public boolean getPetitAuBout(){ return petitAuBout; }

    public void setPartieAnnule(boolean partieAnnule) {this.partieAnnule = partieAnnule; }

    public boolean getPartieAnnule() { return partieAnnule; }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(numJeu);
        dest.writeString(preneur);
        dest.writeString(associe);
        dest.writeString(enchere);
        dest.writeStringList(annonceList);
        dest.writeStringList(joueurList);
        dest.writeString(preneurForPersonne);
        dest.writeInt(nbBout);
        dest.writeString(calculScoreDe);
        dest.writeDouble(scoreCartes);
        dest.writeDouble(finalValue);
        dest.writeInt(roundFinalValue);
        dest.writeString(playerMort);
    }

    protected Details(Parcel in)
    {

    }

    public static final Parcelable.Creator<Details> CREATOR = new Parcelable.Creator<Details>() {
        public Details createFromParcel(Parcel source) {
            return new Details(source);
        }

        public Details[] newArray(int size) {
            return new Details[size];
        }
    };

}
