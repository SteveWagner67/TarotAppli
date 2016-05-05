package com.example.steve.tarot;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements JeuFrag.setPreneur, JeuFrag.setEnchere, JeuFrag.setAnnonceList, JeuFrag.setJoueurList, JeuFrag.setPlayers {

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    ArrayList<String> list, listSelecAnnonce, listSelecJoueur;

    int pos;
    Bundle bundle;

    JeuFrag jeu;
    ClassementFrag classement;
    DetailsFrag details;

    String preneur, enchere;

    ArrayList<Player> playerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listSelecAnnonce = new ArrayList<>();
        listSelecJoueur = new ArrayList<>();
        playerList = new ArrayList<>();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Jeu"));
        tabLayout.addTab(tabLayout.newTab().setText("Classement"));
        tabLayout.addTab(tabLayout.newTab().setText("Details"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Intent intent = getIntent();
        list = new ArrayList<String>();

        list = intent.getStringArrayListExtra("List");
        pos = intent.getIntExtra("Distributor", 0);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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


    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {


            switch (position) {
                case 0:
                    jeu = new JeuFrag();
                    bundle = new Bundle();

                    bundle.putStringArrayList("List",list);
                    bundle.putInt("Distributeur", pos);
                    jeu.setArguments(bundle);
                    return jeu;
                case 1:
                    classement = new ClassementFrag();
                    bundle.putStringArrayList("List", list);
                    bundle.putInt("Distributeur", pos);
                    bundle.putParcelableArrayList("playerlist", playerList);
                    classement.setArguments(bundle);
                    return classement;
                case 2:
                    details = new DetailsFrag();
                    bundle.putStringArrayList("List",list);
                    bundle.putString("Preneur", preneur);
                    bundle.putString("Enchere", enchere);
                    bundle.putStringArrayList("List Annonce", listSelecAnnonce);
                    bundle.putStringArrayList("List Joueur", listSelecJoueur);
                    details.setArguments(bundle);
                    return details;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

    public void setPreneur(String preneur)
    {
        this.preneur = preneur;
    }

    public void setEnchere(String enchere)
    {
        this.enchere = enchere;
    }

    public void setAnnonceList(ArrayList<String> listAnnonce)
    {
        listSelecAnnonce.addAll(listAnnonce);
    }

    public void setJoueurList (ArrayList<String> listJoueur)
    {
        listSelecJoueur.addAll(listJoueur);
    }

    public void setPlayers(ArrayList<Player> listPlayer) {playerList.addAll(listPlayer); }


}