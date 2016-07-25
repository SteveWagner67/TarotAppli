package com.example.steve.tarot;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Steve on 18/04/2016.
 */
public class Player implements Parcelable{

    private int score;
    private ArrayList<Integer> scoreList;
    private int prevScore;
    private int actuPos;
    private ArrayList<Integer> placementList;
    private int prevPos;
    private String nom;
    private int nbPoignet;
    private int nbMisere;
    private int nbVictoire;
    private int nbDefaite;
    private int nbPetite;
    private int nbPouce;
    private int nbGarde;
    private int nbGardeContre;
    private int nbGardeSans;
    private int nbJeuPris;
    private int nbJeuAssocie;

    public Player ()
    {
        score = 0;
        prevScore = 0;
        actuPos = 0;
        prevPos = 0;
        nom = "";
        scoreList = new ArrayList<>();
        placementList = new ArrayList<>();
    }


    public void setNomJoueur(String nom)
    {
        this.nom = nom;
    }

    public String getNomJoueur()
    {
        return this.nom;
    }

    public void setScore(int score)
    {
        this.score += score;
        scoreList.add(this.score);
    }

    public int getScore(int pos)
    {
        return this.scoreList.get(pos);
    }

    private void setPrevScore(int prevScore) {this.prevScore = prevScore;}

    public int getPrevScore() {return prevScore;}

    private void setPrecPos(int prevPosition)
    {
        this.prevPos = prevPosition;
    }

    public int getPrecPos(int pos)
    {
        return placementList.get(pos-1);
    }

    public void setActuPos(int actuPosition)
    {
//        setPrecPos(this.actuPos);
        placementList.add(actuPosition);
//        this.actuPos = actuPosition;
    }

    public int getActuPos(int pos)
    {
        return placementList.get(pos);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeInt(score);
    }

    protected Player(Parcel in)
    {

    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

}
