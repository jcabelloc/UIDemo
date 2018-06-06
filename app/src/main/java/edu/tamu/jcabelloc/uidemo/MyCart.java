package edu.tamu.jcabelloc.uidemo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MyCart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        // my_cart_toolbar is defined in the layout file
        Toolbar myCartToolbar = (Toolbar) findViewById(R.id.my_cart_toolbar);
        setSupportActionBar(myCartToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        onSearchRequested();
    }

    @Override
    public boolean onSearchRequested() {
        return super.onSearchRequested();
    }
}
