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
    private ArrayList<Integer> placementList;
    private String nom;
    private int nbPoignet;
    private int nbMisere;
    private int nbPetite;
    private int nbPouce;
    private int nbGarde;
    private int nbGardeContre;
    private int nbGardeSans;
    private int nbJeuPris;
    private int nbVictoire;
    private int nbDefaite;
    private int nbJeuALEnvers;
    private int nbFoisAssocie;

    private ArrayList<Integer> numJeuJeuPrisArrayList;
    private ArrayList<Integer> numJeuVictoireArrayList;
    private ArrayList<Integer> numJeuDefaiteArrayList;
    private ArrayList<Integer> numJeuALEnversArrayList;
    private ArrayList<Integer> numJeuAssocieArrayList;
    private ArrayList<Integer> numJeuPoignetArrayList;
    private ArrayList<Integer> numJeuMisereArrayList;
    private ArrayList<Integer> numJeuPetiteArrayList;
    private ArrayList<Integer> numJeuPouceArrayList;
    private ArrayList<Integer> numJeuGardeArrayList;
    private ArrayList<Integer> numJeuGardeSansArrayList;
    private ArrayList<Integer> numJeuGardeContreArrayList;

    public Player ()
    {
        score = 0;
        nom = "";
        scoreList = new ArrayList<>();
        placementList = new ArrayList<>();
        nbPoignet = 0;
        nbMisere = 0;
        nbPetite = 0;
        nbPouce = 0;
        nbGarde = 0;
        nbGardeContre = 0;
        nbGardeSans = 0;
        nbJeuPris = 0;
        nbVictoire = 0;
        nbDefaite = 0;
        nbJeuALEnvers = 0;
        nbFoisAssocie = 0;

        numJeuJeuPrisArrayList = new ArrayList<>();
        numJeuVictoireArrayList = new ArrayList<>();
        numJeuDefaiteArrayList = new ArrayList<>();
        numJeuALEnversArrayList = new ArrayList<>();
        numJeuAssocieArrayList = new ArrayList<>();
        numJeuPoignetArrayList = new ArrayList<>();
        numJeuMisereArrayList = new ArrayList<>();
        numJeuPetiteArrayList = new ArrayList<>();
        numJeuPouceArrayList = new ArrayList<>();
        numJeuGardeArrayList = new ArrayList<>();
        numJeuGardeSansArrayList = new ArrayList<>();
        numJeuGardeContreArrayList = new ArrayList<>();
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

    public int getPrecPos(int pos)
    {
        return placementList.get(pos-1);
    }

    public void setActuPos(int actuPosition)
    {
        placementList.add(actuPosition);
    }

    public int getActuPos(int pos)
    {
        return placementList.get(pos);
    }

    public void addJeuPris(int numJeu)
    {
        this.nbJeuPris += 1;
        numJeuJeuPrisArrayList.add(numJeu);
    }

    public int getNbJeuPris()
    {
        return nbJeuPris;
    }

    public ArrayList<Integer> getArrayListNumJeuJeuPris()
    {
        return numJeuJeuPrisArrayList;
    }

    public void addVictoire(int numJeu)
    {
        this.nbVictoire += 1;
        numJeuVictoireArrayList.add(numJeu);
    }

    public int getNbVictoire()
    {
        return nbVictoire;
    }

    public ArrayList<Integer> getArrayListNumJeuVictoire()
    {
        return numJeuVictoireArrayList;
    }

    public void addDefaite(int numJeu)
    {
        this.nbDefaite += 1;
        numJeuDefaiteArrayList.add(numJeu);
    }

    public int getNbDefaite()
    {
        return nbDefaite;
    }

    public ArrayList<Integer> getArrayListNumJeuDefaite()
    {
        return numJeuDefaiteArrayList;
    }

    public void addJeuALEnvers(int numJeu)
    {
        nbJeuALEnvers += 1;
        numJeuALEnversArrayList.add(numJeu);
    }

    public int getNbJeuALEnvers()
    {
        return nbJeuALEnvers;
    }

    public ArrayList<Integer> getArrayListNumJeuALEnvers()
    {
        return numJeuALEnversArrayList;
    }

    public void add1FoisAssocie(int numJeu)
    {
        this.nbFoisAssocie += 1;
        numJeuAssocieArrayList.add(numJeu);
    }

    public ArrayList<Integer> getArrayListNumJeuAssocie()
    {
        return numJeuAssocieArrayList;
    }

    public int getNbFoisAssocie()
    {
        return nbFoisAssocie;
    }
    public void addPoignet(int numJeu)
    {
        this.nbPoignet += 1;
        numJeuPoignetArrayList.add(numJeu);
    }

    public int getNbPoignet()
    {
        return nbPoignet;
    }

    public ArrayList<Integer> getArrayListNumJeuPoignet()
    {
        return numJeuPoignetArrayList;
    }

    public void addMisere(int numJeu)
    {
        this.nbMisere += 1;
        numJeuMisereArrayList.add(numJeu);
    }

    public int getNbMisere()
    {
        return nbMisere;
    }

    public ArrayList<Integer> getArrayListNumJeuMisere()
    {
        return numJeuMisereArrayList;
    }

    public void addPetite(int numJeu)
    {
        this.nbPetite += 1;
        numJeuPetiteArrayList.add(numJeu);
    }

    public int getNbPetite()
    {
        return nbPetite;
    }

    public ArrayList<Integer> getArrayListNumJeuPetite()
    {
        return numJeuPetiteArrayList;
    }

    public void addPouce(int numJeu)
    {
        this.nbPouce += 1;
        numJeuPouceArrayList.add(numJeu);
    }

    public int getNbPouce()
    {
        return nbPouce;
    }

    public ArrayList<Integer> getArrayListNumJeuPouce()
    {
        return numJeuPouceArrayList;
    }

    public void addGarde(int numJeu)
    {
        this.nbGarde += 1;
        numJeuGardeArrayList.add(numJeu);
    }

    public int getNbGarde()
    {
        return nbGarde;
    }

    public ArrayList<Integer> getArrayListNumJeuGarde()
    {
        return numJeuGardeArrayList;
    }

    public void addGardeSans(int numJeu)
    {
        this.nbGardeSans += 1;
        numJeuGardeSansArrayList.add(numJeu);
    }

    public int getNbGardeSans()
    {
        return nbGardeSans;
    }

    public ArrayList<Integer> getArrayListNumJeuGardeSans()
    {
        return numJeuGardeSansArrayList;
    }

    public void addGardeContre(int numJeu)
    {
        this.nbGardeContre += 1;
        numJeuGardeContreArrayList.add(numJeu);
    }

    public int getNbGardeContre()
    {
        return nbGardeContre;
    }

    public ArrayList<Integer> getArrayListNumJeuGardeContre()
    {
        return numJeuGardeContreArrayList;
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
