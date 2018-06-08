package edu.tamu.jcabelloc.uidemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.util.Arrays;

public class MyCustomSuggestionProvider extends ContentProvider {

    // Creates a UriMatcher object.
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        /*
         * The calls to addURI() go here, for all of the content URI patterns that the provider
         * should recognize. For this snippet, only the calls for table 3 are shown.
         */

        /*
         * Sets the integer value for multiple rows in table 3 to 1. Notice that no wildcard is used
         * in the path
         */
        uriMatcher.addURI("edu.tamu.jcabelloc.uidemo.provider.MyCustomSuggestionProvider", "product", 1);

        /*
         * Sets the code for a single row to 2. In this case, the "#" wildcard is
         * used. "content://com.example.app.provider/table3/3" matches, but
         * "content://com.example.app.provider/table3 doesn't.
         */
        uriMatcher.addURI("edu.tamu.jcabelloc.uidemo.provider.MyCustomSuggestionProvider", "product/#", 2);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        /*
         * Choose the table to query and a sort order based on the code returned for the incoming
         * URI. Here, too, only the statements for table 3 are shown.
         */
        Log.d("URI", uri.toString());
        Log.d("projection: ", Arrays.toString(projection));
        Log.d("selection: ", selection);
        Log.d("selectionArgs: ", Arrays.toString(selectionArgs));


        switch (uriMatcher.match(uri)) {
            // If the incoming URI was for all of table3
            case 1:

                if (TextUtils.isEmpty(sortOrder)) sortOrder = "_ID ASC";
                break;

            // If the incoming URI was for a single row
            case 2:

                /*
                 * Because this URI was for a single row, the _ID value part is
                 * present. Get the last path segment from the URI; this is the _ID value.
                 * Then, append the value to the WHERE clause for the query
                 */
                selection = selection + "_ID = " + uri.getLastPathSegment();
                break;

            default:

                // If the URI is not recognized, you should do some error handling here.
        }
        // call the code to actually do the query
        String[] columNames = {"_id", "suggest_text_1", "suggest_text_2", /*"suggest_intent_data",*/ "suggest_intent_data_id"};
        MatrixCursor cursor = new MatrixCursor(columNames);
        cursor.addRow(new String[]{"1", "product 1", "Product Description 1", /*"01",*/ "0001"});
        cursor.addRow(new String[]{"2", "product 2", "Product Description 2", /*"02",*/ "0002"});
        cursor.addRow(new String[]{"3", "product 3", "Product Description 3", /*"03",*/ "0003"});
        cursor.addRow(new String[]{"4", "product 4", "Product Description 4", /*"04",*/ "0004"});

        return cursor;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
