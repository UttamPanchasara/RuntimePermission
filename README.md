# AndroidRuntimePermission

This Library will help you to ask new Runtime Permissions introduced in Android M. This library allows you to check them in background and as well ask for a permission in easy way. just need to ADD THIS :)

**compatible with minSdkVersion 16**

![alt tag](http://utm1994.5gbfree.com/githubScreenshots/Screenshot_20160622-181823.jpg)
![alt tag](http://utm1994.5gbfree.com/githubScreenshots/Screenshot_20160622-181840.jpg)
![alt tag](http://utm1994.5gbfree.com/githubScreenshots/Screenshot_20160622-181903.jpg)
![alt tag](http://utm1994.5gbfree.com/githubScreenshots/Screenshot_20160622-181911.jpg)

#How to Ask Permission 

**To use First of all extends your ActivityClass from :** `RuntimePermission`

**// Define Permission**

    public final String mCalenderPermission = Manifest.permission.WRITE_CALENDAR;
  
**// To set permission use:**  `askPermissionFor(permission, "permissionName")`

    askPermissionFor(mCalenderPermission,"Calendar");     
    
**// To Check whether permission is granted or Not use:** `isPermissionGranted()` **if not then ask using:** `requestPermission();`

    if(!isPermissionGranted()){
        requestPermission();      // if not then ask Permission
    }else{
        // do your stuff here..
    }
    

#Easy to Ask Permission

it will automatically handle all things. if user `deny` permission or check `neverAskAgain` it will handle and also show the alert dialogs to allow permission.

#How to import

**As it is hosted at JCenter you can use it by adding this to your build.gradle:**

    dependencies {
          compile 'com.android.runtimepermission:runtimepermission:1.0.2'
    }


