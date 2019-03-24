package com.kevinj1008.taipeizooapisample;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.kevinj1008.taipeizooapisample.base.BaseActivity;
import com.kevinj1008.taipeizooapisample.model.Plant;
import com.kevinj1008.taipeizooapisample.model.Zoo;
import com.kevinj1008.taipeizooapisample.util.Constants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import static androidx.core.util.Preconditions.checkNotNull;

public class TaipeiZooActivity extends BaseActivity implements TaipeiZooContract.View,
        NavigationView.OnNavigationItemSelectedListener {

    private TaipeiZooContract.Presenter mPresenter;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private TextView mToolbarTitle;

    private Zoo mZoo;
    private Plant mPlant;

    private boolean isToolBarNavListenerReg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new TaipeiZooPresenter(this, getSupportFragmentManager());

        init();
    }

    private void init() {
        setContentView(R.layout.activity_main);

        setToolbar();
        setDrawerLayout();

        mPresenter.start();

    }

    @Override
    public void onBackPressed() {
        ConstraintLayout zooDetailPage = findViewById(R.id.zoo_detail_page);
        ConstraintLayout plantDetailPage = findViewById(R.id.plant_detail_page);
        ConstraintLayout plantContainer = findViewById(R.id.plant_container);
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (zooDetailPage != null && zooDetailPage.getVisibility() == View.VISIBLE) showMainUi();
            if (plantContainer != null && plantContainer.getVisibility() == View.VISIBLE) showMainUi();
            if (plantDetailPage != null && plantDetailPage.getVisibility() == View.VISIBLE) showZooDetailUi();
            super.onBackPressed();
        }
    }

    @Override
    public void showMainUi() {
        enableDrawerBackKey(false);
        mToolbarTitle.setText(getString(R.string.taipei_zoo_title));
    }

    @Override
    public void showZooDetailUi() {
        enableDrawerBackKey(true);
        mToolbarTitle.setText(mZoo.getName());
    }

    @Override
    public void showPlantDetailUi() {
        enableDrawerBackKey(true);
        if (!"".equals(mPlant.getNameCh())) {
            mToolbarTitle.setText(mPlant.getNameCh());
        } else {
            mToolbarTitle.setText(R.string.no_plant_name_ch);
        }
    }

    public void transToZooDetail(Zoo zoo) {
        this.mZoo = zoo;
        mPresenter.transToZooDetail(zoo);
    }

    public void transToPlantDetail(Plant plant) {
        this.mPlant = plant;
        mPresenter.transToPlantDetail(plant);
    }

    @Override
    public void setPresenter(TaipeiZooContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void setToolbar() {
        // Retrieve the AppCompact Toolbar
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        // Set the padding to match the Status Bar height
        mToolbar.setPadding(-12, getStatusBarHeight(), 0, 0);

        // Set title of toolbar
        mToolbarTitle = findViewById(R.id.toolbar_title);
    }

    /**
     * Set Drawer
     */
    private void setDrawerLayout() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.setFitsSystemWindows(true);
        mDrawerLayout.setClipToPadding(false);

        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void enableDrawerBackKey(boolean enable) {
        if (enable) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            if (!isToolBarNavListenerReg) {
                mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });
                isToolBarNavListenerReg = true;
            }
        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerToggle.setToolbarNavigationClickListener(null);
            mDrawerLayout.addDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();
            isToolBarNavListenerReg = false;
        }
    }

    /**
     * @return height of status bar
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false; //TODO: turn to true if set menu
    }
}
