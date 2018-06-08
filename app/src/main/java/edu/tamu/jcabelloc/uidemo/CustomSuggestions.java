package edu.tamu.jcabelloc.uidemo;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.SearchRecentSuggestions;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.SearchView;


public class CustomSuggestions extends AppCompatActivity {

    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_suggestions);

        Toolbar myCartToolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(myCartToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) findViewById(R.id.customSearchView); //menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        handleIntent(getIntent());
    }


    @Override
    protected void onNewIntent(Intent intent) {
        searchView.setQuery("", false);
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            String intentDataString = intent.getDataString();
            Uri intentData = intent.getData();
            if (query != null) {
                Log.d("Query" ,query );
            }
            if (intentDataString != null) {
                Log.d("intentDataString" ,intentDataString);
            }
            if (intentData != null) {
                Log.d("intentData", intentData.toString());
            }
        }
    }

}
