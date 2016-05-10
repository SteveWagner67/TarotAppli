package com.example.steve.tarot;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Steve on 18/04/2016.
 */
public class Player implements Parcelable{

    private int score;
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
        nom = "";
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
    }

    public int getScore()
    {
        return this.score;
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
