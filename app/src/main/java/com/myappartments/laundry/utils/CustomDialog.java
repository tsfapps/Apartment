package com.myappartments.laundry.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

import com.myappartments.laundry.R;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class CustomDialog {

    public static void showDialogPositive(String strTitle, String strMessage, Context tContext){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(tContext);
        alertDialog.setTitle(strTitle);
        alertDialog.setMessage(strMessage);
        alertDialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              dialog.dismiss();
            }
        });
//        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });

        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    public static void showLoginCustomAlertDialog(String strPos, String strNeg, final Context tContext, String strTitle, String strMsg, int dialogType) {
        SweetAlertDialog alertDialog = new SweetAlertDialog(tContext, dialogType);
        alertDialog.setTitleText(strTitle);
        alertDialog.setCancelText(strNeg);
        alertDialog.setCancelClickListener( new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                Config.moveTo(context, LoginActivity.class);

            }
        });
        alertDialog.setConfirmText(strPos);
        alertDialog.setConfirmClickListener( new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                Config.moveTo(context, SignUp.class);

            }
        });
        if (strMsg.length() > 0)
            alertDialog.setContentText(strMsg);
        alertDialog.show();
        Button btn = (Button) alertDialog.findViewById(R.id.confirm_button);
        btn.setBackgroundColor(ContextCompat.getColor(tContext, R.color.colorPrimary));
        Button btn1 = (Button) alertDialog.findViewById(R.id.cancel_button);
        btn1.setBackgroundColor(ContextCompat.getColor(tContext, R.color.colorPrimary));

    }
    public static void customDialogPos( Context tContext, String strMsg) {
        final SweetAlertDialog alertDialog = new SweetAlertDialog(tContext,  SweetAlertDialog.WARNING_TYPE);
        alertDialog.setTitleText("Service will start soon...");
        alertDialog.setConfirmText("Okay");
        alertDialog.setConfirmClickListener( new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {

                alertDialog.dismiss();
            }
        });
        alertDialog.setContentText(strMsg);
        alertDialog.show();
        Button btn = alertDialog.findViewById(R.id.confirm_button);
        btn.setBackgroundColor(ContextCompat.getColor(tContext, R.color.colorPrimary));

    }


}
