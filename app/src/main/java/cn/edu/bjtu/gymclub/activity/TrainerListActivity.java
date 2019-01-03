package cn.edu.bjtu.gymclub.activity;

import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.bjtu.gymclub.R;
import cn.edu.bjtu.gymclub.adapter.TrainerAdapter;
import cn.edu.bjtu.gymclub.model.News;
import cn.edu.bjtu.gymclub.model.Trainer;
import cn.edu.bjtu.gymclub.provider.TrainerContentProvider;

public class TrainerListActivity extends AppCompatActivity {
    private static final int LOADER_TRAINERS = 1;
    private RecyclerView rv;
    private TrainerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<News> mList=new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_list);
        rv = findViewById(R.id.recycler_view_trainers);
        mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        mAdapter=new TrainerAdapter();
        rv.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(LOADER_TRAINERS, null, mLoaderCallbacks);
    }

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks =
            new LoaderManager.LoaderCallbacks<Cursor>() {

                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                    switch (id) {
                        case LOADER_TRAINERS:
                            CursorLoader c =  new CursorLoader(getApplicationContext(),
                                    TrainerContentProvider.URI_CHEESE,
                                    new String[]{Trainer.COLUMN_NAME},
                                    null, null, null);
                            return c;
                        default:
                            throw new IllegalArgumentException();
                    }
                }

                @Override
                public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                    switch (loader.getId()) {
                        case LOADER_TRAINERS:
                            mAdapter.setTrainers(data);
//                            mAdapter.notifyDataSetChanged();
                            break;
                    }
                }

                @Override
                public void onLoaderReset(Loader<Cursor> loader) {
                    switch (loader.getId()) {
                        case LOADER_TRAINERS:
                            mAdapter.setTrainers(null);
//                            mAdapter.notifyDataSetChanged();
                            break;
                    }
                }
            };
}
