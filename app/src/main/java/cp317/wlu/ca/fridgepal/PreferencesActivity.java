package com.alvinalexander.preferencestestapp;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cp317.wlu.ca.fridgepal.fridge.FridgeFragment;
import cp317.wlu.ca.fridgepal.grocerylist.GroceryListFragment;
import cp317.wlu.ca.fridgepal.recipes.RecipesFragment;

import cp317.wlu.ca.fridgepal.preferences.MyPreferencesFragment;
import cp317.wlu.ca.fridgepal.R;

public class PreferencesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.preferences, new MyPreferencesFragment())
                .commit();
    }
}

}

