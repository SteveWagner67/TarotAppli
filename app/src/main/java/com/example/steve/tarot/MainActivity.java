package com.example.steve.tarot;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import android.content.Intent;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /* Initialization of the variables */
    private TextView introTitle, joueurJeuTitle, joueurChoixTitle, nomNouvJoueurTitle;
    private ListView choixJoueurListView, jeuJoueurListView;
    private Button terminerBtn, ajoutJoueurBtn;

    private ArrayList<String> choixJoueurList, jeuJoueurList;
    private ArrayAdapter<String> adaptChoixJoueurList, adaptJeuJoueurList;

    private String joueurToAdd, joueurToRemove;
    int nbJoueur = 0, posDistrib;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if((keyCode == KeyEvent.KEYCODE_ENTER) || (keyCode == KeyEvent.KEYCODE_BACK))
        {
            introTitle.setVisibility(View.VISIBLE);
            joueurChoixTitle.setVisibility(View.VISIBLE);
            joueurJeuTitle.setVisibility(View.VISIBLE);
            choixJoueurListView.setVisibility(View.VISIBLE);
            jeuJoueurListView.setVisibility(View.VISIBLE);
            ajoutJoueurBtn.setVisibility(View.VISIBLE);
            terminerBtn.setVisibility(View.VISIBLE);

            nomNouvJoueurTitle.setVisibility(View.INVISIBLE);

            if(keyCode == KeyEvent.KEYCODE_ENTER)
            {
            }
        }


        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /* Reference of the variable */
        introTitle = (TextView)findViewById(R.id.intro);
        joueurJeuTitle = (TextView)findViewById(R.id.joueurJeuTitle);
        joueurChoixTitle = (TextView)findViewById(R.id.joueurChoixTitle);
        nomNouvJoueurTitle = (TextView)findViewById(R.id.nomNouvJoueurTitle);

        choixJoueurListView = (ListView)findViewById(R.id.joueurChoixList);
        jeuJoueurListView = (ListView)findViewById(R.id.joueurJeuList);

        terminerBtn = (Button)findViewById(R.id.terminerListBtn);
        ajoutJoueurBtn = (Button)findViewById(R.id.ajoutJoueurBtn);

        /* Initialization */
        choixJoueurList = new ArrayList<>();
        jeuJoueurList = new ArrayList<>();

        adaptChoixJoueurList = new ArrayAdapter<String>(MainActivity.this, R.layout.simple_list_item_1, choixJoueurList);
        adaptJeuJoueurList = new ArrayAdapter<String>(MainActivity.this, R.layout.simple_list_item_1, jeuJoueurList);

        choixJoueurList.add("André");
        choixJoueurList.add("Ben");
        choixJoueurList.add("Céline");
        choixJoueurList.add("Justine");
        choixJoueurList.add("Logan");
        choixJoueurList.add("Néna");
        choixJoueurList.add("Papé");
        choixJoueurList.add("Steve");
        choixJoueurList.add("Tony");


        choixJoueurListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (nbJoueur >= 6) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("Information");
                    adb.setMessage("Nombre de joueurs maximum atteint: 6");

                    adb.setPositiveButton("OK", null);
                    adb.show();
                } else {
                    joueurToAdd = choixJoueurList.get(position).toString();

                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("Information");
                    adb.setMessage("Ajouter " + joueurToAdd + " à la liste des joueurs en jeu ?");

                    adb.setNegativeButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adaptJeuJoueurList.add(joueurToAdd);
                            adaptChoixJoueurList.remove(joueurToAdd);
                            nbJoueur++;
                        }
                    });

                    adb.setPositiveButton("Non", null);
                    adb.show();
                }

            }
        });

        jeuJoueurListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                joueurToRemove = jeuJoueurList.get(position);

                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                adb.setTitle("Information");
                adb.setMessage("Supprimer " + joueurToRemove + " de la liste des joueurs en jeu ?");

                adb.setNegativeButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adaptJeuJoueurList.remove(joueurToRemove);
                        adaptChoixJoueurList.add(joueurToRemove);
                        nbJoueur--;
                    }
                });

                adb.setPositiveButton("Non", null);
                adb.show();
            }
        });

        terminerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nbJoueur < 3) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("Nombre de joueurs insuffisant");
                    adb.setMessage("Minimum: 3" + "\r\n" + "Actuellement: " + Integer.toString(nbJoueur));
                    adb.setPositiveButton("OK", null);
                    adb.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    // Set the dialog title
                    builder.setTitle("Premier distributeur");
                    builder.setSingleChoiceItems(jeuJoueurList.toArray(new String[jeuJoueurList.size()]), 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            posDistrib = which;

                        }
                    });

                    builder.setNegativeButton("Retour", null);
                    builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                            intent.putExtra("List", jeuJoueurList);
                            intent.putExtra("Distributor", posDistrib);
                            startActivity(intent);

                            finish();
                        }
                    });
                    builder.show();
                }

            }
        });

        ajoutJoueurBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_player);
                final EditText editTxt = (EditText) dialog.findViewById(R.id.addPlayerEdit);
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                dialog.show();

                editTxt.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if ((keyCode == KeyEvent.KEYCODE_ENTER) || (keyCode == KeyEvent.KEYCODE_BACK)) {

                            if (keyCode == KeyEvent.KEYCODE_ENTER) {

                                int i = 0;
                                boolean exist = false;
                                while (i < choixJoueurList.size()) {
                                    if (choixJoueurList.get(i).equals(editTxt.getText().toString())) {
                                        exist = true;
                                    }
                                    i++;
                                }

                                if (exist) {
                                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                                    adb.setTitle("Information");
                                    adb.setMessage("Joueur déjà existant");

                                    adb.setPositiveButton("OK", null);
                                    adb.show();
                                } else {
                                    choixJoueurList.add(editTxt.getText().toString());
                                }

                            }

                            dialog.dismiss();
                        }


                        return false;
                    }
                });

            }
        });

        choixJoueurListView.setAdapter(adaptChoixJoueurList);
        jeuJoueurListView.setAdapter(adaptJeuJoueurList);
    }

}



