package com.kevinj1008.taipeizooapisample.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.kevinj1008.taipeizooapisample.TaipeiZooActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    private final String IS_RELAUNCH = "is_relaunch";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.containsKey(IS_RELAUNCH)) {
            if (savedInstanceState.getBoolean(IS_RELAUNCH, false)) restartApplication();
        }

        this.mContext = this;

        setStatusBar();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(IS_RELAUNCH, true);
        super.onSaveInstanceState(outState);
    }

    private void restartApplication() {
        // Intent to start launcher activity and closing all previous ones
        Intent restartIntent = new Intent(getApplicationContext(), TaipeiZooActivity.class);
        restartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(restartIntent);

        // Kill Current Process
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    private void setStatusBar() {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);//calculateStatusColor(Color.WHITE, (int) alphaValue)
    }
}
