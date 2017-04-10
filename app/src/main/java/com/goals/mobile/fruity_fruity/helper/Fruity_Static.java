package com.goals.mobile.fruity_fruity.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.goals.mobile.fruity_fruity.view.activity.Login;

/**
 * Created by Mobile on 11/9/2016.
 */

public class Fruity_Static {

    private static ProgressDialog progressDialog;

    public static void toastMessage(Activity activity, String message) {

        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();

    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    public static void hideSoftkeyboard(Activity activity)
    {

        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void startDialog(Activity activity) {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void stopDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
    public static void logOutPopup(final Activity activity ){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);

        // Setting Dialog Title
        alertDialog.setTitle("Alert!");

        // Setting Dialog Message
        alertDialog.setMessage("Do you want to Logout?");

        // Setting Icon to Dialog

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                try{
                    LoginManager.getInstance().logOut();
                    Intent intent = new Intent(activity,Login.class);
                    activity.startActivity(intent);
                }catch (Exception e){
                    Intent intent = new Intent(activity,Login.class);
                    activity.startActivity(intent);
                }

            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event

                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
