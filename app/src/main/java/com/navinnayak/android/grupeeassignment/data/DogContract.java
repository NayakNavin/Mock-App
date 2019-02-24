package com.navinnayak.android.grupeeassignment.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DogContract {
    public static final String CONTENT_AUTHORITY = "com.navinnayak.android.grupeeassignment";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_DOGS = "dog";

    private DogContract() {
    }

    public static final class DogEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_DOGS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DOGS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DOGS;

        public final static String TABLE_NAME = "dogs";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_DOG_BREED = "dog_breed";
        public final static String COLUMN_TIME_STAMP = "date_created";
    }
}