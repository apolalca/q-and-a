package es.iesnervion.qa.ui.View;

import android.content.res.ColorStateList;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import es.iesnervion.qa.Model.Categoria;
import es.iesnervion.qa.R;
import es.iesnervion.qa.ui.Adapter.CategoriaAdapter;

public class GameList extends AppCompatActivity {

    private int iClicks = 0;
    private List<Categoria> categorias;
    private MediaPlayer mp;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        CategoriaAdapter mCategoryAdapter;
        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.rvCategorias);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorQuestions)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        TextView tx = (TextView) findViewById(R.id.backButton);
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mp = MediaPlayer.create(GameList.this, R.raw.music);
        mp.start();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView txtClicks = (TextView) findViewById(R.id.txtClicks);
                        // task to be done every 1000 milliseconds
                        iClicks = iClicks + 1;
                        txtClicks.setText(String.valueOf(iClicks));
                    }
                });
            }
        }, 0, 100);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        //TODO Dummy -> Replace that
        Categoria[] c = {
          new Categoria(1, "Ciencias")
        };

        mCategoryAdapter = new CategoriaAdapter(c);
        mRecyclerView.setAdapter(mCategoryAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mp.stop();
    }
}