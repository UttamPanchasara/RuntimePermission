package uttampanchasara.librarydemo;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import uttampanchasara.runtimepermission.RuntimePermission;

public class MainActivity extends RuntimePermission {

    String contact = Manifest.permission.READ_CONTACTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        askPermissionFor(contact, "Contact");

        if (!isPermissionGranted()) {
            requestPermission();
        } else {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
    }
}
