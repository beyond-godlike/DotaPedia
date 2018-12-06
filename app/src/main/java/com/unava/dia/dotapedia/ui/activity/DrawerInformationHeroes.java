package com.unava.dia.dotapedia.ui.activity;

import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.utils.RecyclerViewClickListener;
import com.unava.dia.dotapedia.data.model.DotaHero;
import com.unava.dia.dotapedia.data.model.Hero;
import com.unava.dia.dotapedia.ui.adapters.NavigatorAdapter;
import com.unava.dia.dotapedia.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class DrawerInformationHeroes extends AppCompatActivity
        implements RecyclerViewClickListener, NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Hero> heroes = new ArrayList<>();
    private RealmResults<DotaHero> listHeroImages;

    @BindView(R.id.hero_name)
    public TextView hName;

    @BindView(R.id.hero_strength)
    TextView strength;
    @BindView(R.id.hero_agility)
    TextView agility;
    @BindView(R.id.hero_intelligence)
    TextView intelligence;

    @BindView(R.id.hero_damage)
    TextView damage;
    @BindView(R.id.hero_speed)
    TextView speed;
    @BindView(R.id.hero_armor)
    TextView armor;

    @BindView(R.id.hero_history)
    TextView history;

    @BindView(R.id.heroImage)
    ImageView heroImage;
    @BindView(R.id.skill1)
    ImageButton skillImage1;
    @BindView(R.id.skill2)
    ImageButton skillImage2;
    @BindView(R.id.skill3)
    ImageButton skillImage3;
    @BindView(R.id.skill4)
    ImageButton skillImage4;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_information_heroes);
        ButterKnife.bind(this);

        listHeroImages = Utils.getHeroPediaList(getApplicationContext());
        heroes = Utils.getHeroList(this);

        //SET UP RECYCLERVIEW
        RecyclerView rv = (RecyclerView) findViewById(R.id.rvChooseHeroDrawer);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        //ADAPTER
        NavigatorAdapter adapter = new NavigatorAdapter(getApplicationContext(), heroes, this);
        rv.setAdapter(adapter);

        history.setMovementMethod(new ScrollingMovementMethod());
        setHero(0);


    }

    public void setHero(int i) {

        String path = listHeroImages.get(i).getIcon();
        String pathSkill1 = listHeroImages.get(i).getSkill1();
        String pathSkill2 = listHeroImages.get(i).getSkill2();
        String pathSkill3 = listHeroImages.get(i).getSkill3();
        String pathSkill6 = listHeroImages.get(i).getSkill6();

        heroImage.setImageDrawable(Drawable.createFromStream(Utils.openImage(path, getApplicationContext()), null));
        skillImage1.setImageDrawable(Drawable.createFromStream(Utils.openImage(pathSkill1, getApplicationContext()), null));
        skillImage2.setImageDrawable(Drawable.createFromStream(Utils.openImage(pathSkill2, getApplicationContext()), null));
        skillImage3.setImageDrawable(Drawable.createFromStream(Utils.openImage(pathSkill3, getApplicationContext()), null));
        skillImage4.setImageDrawable(Drawable.createFromStream(Utils.openImage(pathSkill6, getApplicationContext()), null));

        Log.d("imii", new Integer(listHeroImages.size()).toString()); // 21

        hName.setText(heroes.get(i).name);
        strength.setText(heroes.get(i).strength);
        agility.setText(heroes.get(i).agility);
        intelligence.setText(heroes.get(i).intelligence);

        damage.setText("base damage: " + heroes.get(i).baseDamage);
        speed.setText("speed: " + heroes.get(i).speed);
        armor.setText("base armor: " + heroes.get(i).armor);

        history.setText(heroes.get(i).history);
    }
// Drawer

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // RecyclerViewClickListener

    @Override
    public void onItemClick(int position) {
        setHero(position);
    }
}
