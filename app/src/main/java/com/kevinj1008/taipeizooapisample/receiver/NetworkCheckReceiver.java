package com.kevinj1008.taipeizooapisample.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.kevinj1008.taipeizooapisample.R;
import com.kevinj1008.taipeizooapisample.TaipeiZoo;
import com.kevinj1008.taipeizooapisample.util.Constants;
import com.kevinj1008.taipeizooapisample.util.NetworkUtil;

import androidx.appcompat.app.AlertDialog;

public class NetworkCheckReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String connectivityChange = Constants.CONNECTIVITY_CHANGE;
        int networkStatus = NetworkUtil.getConnectivityStatusString(context);
        if (connectivityChange.equals(intent.getAction())) {
            if (networkStatus == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED) {
                networkCheckDialog(context);
            }
        }
    }

    private void networkCheckDialog(final Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.AlertDialogCustom);
        alertDialog.setTitle(R.string.title_network_check_dialog);
        alertDialog.setPositiveButton("OK", null);
        final AlertDialog dialog = alertDialog.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkUtil networkUtils = new NetworkUtil(TaipeiZoo.getAppContext());
                if (networkUtils.isNetworkAvailable()) {
                    context.sendBroadcast(new Intent("restart_taipeizoosample"));
                    dialog.cancel();
                }
            }
        });
    }
}
