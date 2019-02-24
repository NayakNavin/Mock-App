package com.navinnayak.android.grupeeassignment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.navinnayak.android.grupeeassignment.data.DogContract.DogEntry;

public class LikedActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int DOG_LOADER = 0;
    DogCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked);

        ListView dogtListView = findViewById(R.id.list);
        View emptyView = findViewById(R.id.empty_view);
        dogtListView.setEmptyView(emptyView);

        mCursorAdapter = new DogCursorAdapter(this, null);
        dogtListView.setAdapter(mCursorAdapter);

        getSupportLoaderManager().initLoader(DOG_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                DogEntry._ID,
                DogEntry.COLUMN_DOG_BREED,
                DogEntry.COLUMN_TIME_STAMP

        };
        return new CursorLoader(this,
                DogEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }

}