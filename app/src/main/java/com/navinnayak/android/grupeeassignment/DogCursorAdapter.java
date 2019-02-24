package com.navinnayak.android.grupeeassignment;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.navinnayak.android.grupeeassignment.data.DogContract;
import com.squareup.picasso.Picasso;

public class DogCursorAdapter extends CursorAdapter {

    public DogCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Get the current position of the cursor in order to set a TAG with it on the sell button
        final int position = cursor.getPosition();

        // Get the views to set the content in
        TextView dateTextView = view.findViewById(R.id.dateTime);
        ImageView dogImgView = view.findViewById(R.id.liked_image);

        // Get the column indexes
        int dateColumnIndex = cursor.getColumnIndex(DogContract.DogEntry.COLUMN_TIME_STAMP);
        int imageUrlColumnIndex = cursor.getColumnIndex(DogContract.DogEntry.COLUMN_DOG_BREED);

        // Set the content
        dateTextView.setText(cursor.getString(dateColumnIndex));
        Picasso.get().load(cursor.getString(imageUrlColumnIndex)).into(dogImgView);

    }
}