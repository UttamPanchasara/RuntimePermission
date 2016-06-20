package uttampanchasara.runtimepermission;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Uttam on 20-Jun-16.
 */
public class RuntimePermission extends AppCompatActivity {
    // variable declaration
    private AlertDialog mFirstAlertDialogWithoutCheck;
    private AlertDialog mFinalAlertDialog;
    private String TAG = getClass().getName();
    private final int PERMISSION_REQUEST_CODE = 0;
    private int REQUEST_PERMISSION_SETTING = 1;
    private String mPermission = null;
    private String mName = null;

    //** to set permission **//
    public void askPermissionFor(String mPermission, String mName) {
        this.mName = mName;
        this.mPermission = mPermission;
    }

    //** check permission is granted or not **//
    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(mPermission) == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }

    //** request permossion to user **//
    public void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{mPermission},
                PERMISSION_REQUEST_CODE);
    }


    //** get permission result **//
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted :)

                } else {
                    // permission was not granted
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, mPermission)) {
                        firstAlertDialogWithoutCheck();
                    } else {
                        finalalertDialog();
                    }
                }
                break;
        }
    }

    //** display dialog with warning **//
    public void firstAlertDialogWithoutCheck() {
        mFirstAlertDialogWithoutCheck = new AlertDialog.Builder(this)
                .setTitle("Permission denied")
                .setCancelable(false)
                .setMessage("Without " + mName + " Permission the app will not work. Are you sure you want to deny this permission?")
                .setPositiveButton("RE-TRY", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        requestPermission();
                    }
                })
                .setNegativeButton("I'M SURE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    //** Display alertDialog with setting option to user **//
//** Handle "Never ask Again" **//
    public void finalalertDialog() {
        if (mFirstAlertDialogWithoutCheck != null) {
            mFirstAlertDialogWithoutCheck.dismiss();
        }
        mFinalAlertDialog = new AlertDialog.Builder(this)
                .setTitle("Permission denied")
                .setCancelable(false)
                .setMessage("Without " + mName + " Permission the app will not work. Click Settings to go to App settings to let you do so.")
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finishAffinity();
                    }
                })
                .setNegativeButton("Setting", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
