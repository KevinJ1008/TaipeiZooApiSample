package com.kevinj1008.taipeizooapisample;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

import com.kevinj1008.taipeizooapisample.base.BaseActivity;

import androidx.drawerlayout.widget.DrawerLayout;

public class TaipeiZooActivity extends BaseActivity implements TaipeiZooContract.View {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void init() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showMainUi() {

    }

    @Override
    public void setPresenter(TaipeiZooContract.Presenter presenter) {

    }

    private void setToolbar() {
        // Retrieve the AppCompact Toolbar
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // Set the padding to match the Status Bar height
        mToolbar.setPadding(0, getStatusBarHeight(), 0, 0);

//        mToolbarTitle = (TextView) mToolbar.findViewById(R.id.textview_toolbar_title);
//        mToolbarTitle.setText(getResources().getString(R.string.all_fitnessch));
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
}
