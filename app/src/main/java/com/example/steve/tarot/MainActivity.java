package com.example.steve.tarot;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
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
    Button valid;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    EditText enter;
    private ListView playerList;
    int nbPlayer =0;
    int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Reference of the variable */
        valid = (Button) findViewById(R.id.button2);
        playerList = (ListView) findViewById(R.id.listView);
        enter = (EditText) findViewById(R.id.editText);

        list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.simple_list_item_1, list);


        enter.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                valid.setVisibility(View.INVISIBLE);

                return false;
            }
        });

        /* Key action for the edittext*/
        enter.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
                {

                    if (enter.getText().toString().equals(""))
                    {
                        // Do nothing
                    }

                    else
                    {
                        /* Be sure the number of maximum player isn't attempt */
                        if(nbPlayer > 6)
                        {
                            AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                            adb.setTitle("Information");
                            adb.setMessage("Nombre de joueurs max atteint: 6");
                            adb.setPositiveButton("OK", null);
                            adb.show();
                        }

                        else
                        {

                            // Be sure the name doesn't already exist
                            int i;
                            boolean exist = false;
                            for(i=0; i<nbPlayer; i++)
                            {
                                if(list.get(i).toString().equals(enter.getText().toString()))
                                {
                                    exist = true;
                                }
                            }

                            if(exist)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                // Set the dialog title
                                builder.setTitle("Information");
                                builder.setMessage(enter.getText().toString() + " est déjà dans la liste");
                                builder.setPositiveButton("OK", null);
                                builder.show();
                            }

                            else
                            {
                                list.add(enter.getText().toString());
                                playerList.setAdapter(adapter);

                                /* Add a new player */
                                nbPlayer++;
                            }

                            // Check if no view has focus:
                            View view = getCurrentFocus();
                            if (view != null)
                            {
                                // Close the keyboard
                                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            }

                        }

                        // Clear the written text
                        enter.setText("");

                        valid.setVisibility(View.VISIBLE);

                    }

                    return true;
                }

                return false;
            }
        });

        /* Button action */
        valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "Nb of player = " + nbPlayer, Toast.LENGTH_SHORT).show();
                if(nbPlayer < 3)
                {
                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("Nombre de joueurs insuffisant");
                    adb.setMessage("Minimum: 3" + "\r\n" +"Actuellement: " +nbPlayer);
                    adb.setPositiveButton("OK", null);
                    adb.show();
                }

                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    // Set the dialog title
                    builder.setTitle("Premier distributeur");
                    builder.setSingleChoiceItems(list.toArray(new String[list.size()]), 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            pos=which;

                        }
                    });

                    builder.setNegativeButton("Retour", null);
                    builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                            intent.putExtra("List", list);
                            intent.putExtra("Distributor", pos);
                            startActivity(intent);

                            finish();
                        }
                    });
                    builder.show();
                }

            }
        });

        /* Action long click on the list of item */
        playerList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final String rmvStr = ((TextView) view).getText().toString();
                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                adb.setTitle("Liste des joueurs");
                adb.setMessage("Êtes vous sûre de vouloir supprimer " + rmvStr + " ?");
                adb.setNegativeButton("Annuler", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.remove(rmvStr);
                        adapter.notifyDataSetChanged();
                        nbPlayer--;
                    }
                });
                adb.show();

                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
