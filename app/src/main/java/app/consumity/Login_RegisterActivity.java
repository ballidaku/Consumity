package app.consumity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

import app.consumity.Async_Thread.Super_AsyncTask;
import app.consumity.Async_Thread.Super_AsyncTask_Interface;
import app.consumity.Utils.Dialogs;
import app.consumity.Utils.GlobalConstant;
import app.consumity.Utils.LocationFinder;
import app.consumity.Utils.LocationNotifier;

/**
 Created by ameba on 22/12/15. */
public class Login_RegisterActivity extends AppCompatActivity implements View.OnClickListener, LocationNotifier
{

    RelativeLayout relvw_login, rel_vw_register;
    View view_login_line, view_register_line;
    String get_intnt_login;

    CallbackManager callbackManager;
    LoginButton     facebook;
    ProfileTracker  profileTracker;

    GoogleCloudMessaging gcm = null;

    LinearLayout lnrvw_register_full_name, llay_user_id, llay_cnfrm_pas, /*llay_fb,*/
              llay_btn_login_registr;
    EditText edtv_full_name, edtv_user_id, edtv_email_id, edtv_paswrd, edtv_cnfrm_pas;
    TextView txtv_login_registr, txtv_fb, txtv_forgot_password;
    ImageView imgv_login_register;
    Boolean isLoginScreen = true;

    LocationFinder google_location;
    Location location = null;

    SharedPreferences sp;
    GlobalConstant constant = new GlobalConstant();
    Dialogs        dialogs  = new Dialogs();
    Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //************************************************Facebook**************************************

        try
        {
            FacebookSdk.sdkInitialize(Login_RegisterActivity.this);
            callbackManager = CallbackManager.Factory.create();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //**********************************************************************************************

        setContentView(R.layout.login_register);

        con = this;
        constant.set_bold_font(con, findViewById(R.id.lay_font));
        findViewById();

        //************************************************Facebook**************************************

        FacebookSdk.setApplicationId(GlobalConstant.FB_APP_ID);
        isAlreadyLogin(isLoggedIn());

        //**********************************************************************************************

        // GlobalConstant.get_Hash_key(con);

        //************************************************GCM**************************************

        if (sp.getString("GCM_Reg_id", "").isEmpty())
        {
            registerInBackground();
        }

        //********************************************************************************************

        get_intnt_login = getIntent().getStringExtra("login");

        if (get_intnt_login != null)
        {
            if (get_intnt_login.equals("login"))
            {
                show_login_data();
            }
            else
            {
                show_register_data();
            }
        }

        relvw_login.setOnClickListener(this);
        rel_vw_register.setOnClickListener(this);
    }

    public boolean isLoggedIn()
    {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    private void findViewById()
    {
        relvw_login = (RelativeLayout) findViewById(R.id.relvw_login);
        rel_vw_register = (RelativeLayout) findViewById(R.id.rel_vw_register);

        view_login_line = findViewById(R.id.view_login_line);
        view_register_line = findViewById(R.id.view_register_line);
        lnrvw_register_full_name = (LinearLayout) findViewById(R.id.lnrvw_register_full_name);
        llay_user_id = (LinearLayout) findViewById(R.id.llay_user_id);
        llay_cnfrm_pas = (LinearLayout) findViewById(R.id.llay_cnfrm_pas);

        (txtv_forgot_password = (TextView) findViewById(R.id.txtv_forgot_password)).setOnClickListener(this);

        (txtv_fb = (TextView) findViewById(R.id.txtv_fb)).setOnClickListener(this);
        (llay_btn_login_registr = (LinearLayout) findViewById(R.id.llay_btn_login_registr)).setOnClickListener(this);

        constant.spaceNotAllowed_at_starting(edtv_full_name = (EditText) findViewById(R.id.edtv_full_name));
        constant.spaceNotAllowed_at_starting(edtv_user_id = (EditText) findViewById(R.id.edtv_user_id));
        constant.spaceNotAllowed_at_starting(edtv_email_id = (EditText) findViewById(R.id.edtv_email_id));

        edtv_paswrd = (EditText) findViewById(R.id.edtv_paswrd);
        edtv_cnfrm_pas = (EditText) findViewById(R.id.edtv_cnfrm_pas);
        txtv_login_registr = (TextView) findViewById(R.id.txtv_login_registr);
        facebook = (LoginButton) findViewById(R.id.facebook);
        imgv_login_register = (ImageView) findViewById(R.id.imgv_login_register);

        findViewById(R.id.txtv_back).setOnClickListener(this);

        google_location = new LocationFinder(this);
        google_location.setOnResultsListener(this);

        //************************************************SharedPreference**************************************

        sp = constant.get_SharedPreferences(con);

        //************************************************Facebook**************************************

        facebook.setReadPermissions("user_friends");
        facebook.setReadPermissions(Arrays.asList("email"));

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                get_fb_data();
            }

            @Override
            public void onCancel()
            {

            }

            @Override
            public void onError(FacebookException exception)
            {

            }
        });

        //**********************************************************************************************
    }

    //************************************************Facebook***************************************************************

    public void get_fb_data()
    {
        try
        {
            final Profile profile = Profile.getCurrentProfile();

            Log.e("onSuccess", "4" + profile.getName());
            Log.e("onSuccess", "5" + profile.getId());
            Log.e("onSuccess", "6" + profile.getProfilePictureUri(500, 500));

            URL img_value = new URL(profile.getProfilePictureUri(500, 500).toString());

           /* HashMap<String, String> map = new HashMap<>();
            map.put("id", Profile.getCurrentProfile().getId());
            map.put("image", constant.get_Base64_from_url(img_value));
            map.put("name", Profile.getCurrentProfile().getName());*/

            login_register_through_fb(profile);

        }
        catch (Exception e)
        {
            e.printStackTrace();

            profileTracker = new ProfileTracker()
            {
                @Override
                protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile)
                {
                    Log.e("AAAAAAAAAAAAAA", "Hello");

                    if (Profile.getCurrentProfile() != null)
                    {
                        // Log.e("currentProfile", "Hello" + currentProfile.getName() + "...." + currentProfile.getProfilePictureUri(100, 100) + "...." + currentProfile.getId());

                        Log.e("onSuccess", "7" + currentProfile.getName());
                        Log.e("onSuccess", "8" + currentProfile.getId());
                        Log.e("onSuccess", "9" + currentProfile.getProfilePictureUri(500, 500).toString());

                        login_register_through_fb(currentProfile);
/*
                        URL img_value = null;
                        try
                        {
                            img_value = new URL(currentProfile.getProfilePictureUri(500, 500).toString());
                        }
                        catch (MalformedURLException e1)
                        {
                            e1.printStackTrace();
                        }

                        HashMap<String, String> map = new HashMap<>();
                        map.put("id", currentProfile.getId());
                        map.put("image", constant.get_Base64_from_url(img_value));
                        map.put("name", currentProfile.getName());*/

                    }
                    else
                    {
                        stop_fb();
                    }
                }
            };

            profileTracker.startTracking();
        }
    }

    private void login_register_through_fb(Profile profile)
    {
        HashMap<String, String> map = new HashMap<>();

        // Log.e("location.getLatitude()",""+location.getLatitude());
        //Log.e("ocation.getLongitude()",""+location.getLongitude());

        map.put(GlobalConstant.KeyValues_Names.EmailID.toString(), profile.getId());
        map.put(GlobalConstant.KeyValues_Names.Password.toString(), "");
        map.put(GlobalConstant.KeyValues_Names.ApplicationID.toString(), sp.getString("GCM_Reg_id", ""));
        map.put(GlobalConstant.KeyValues_Names.Latitude.toString(), "" + location.getLatitude());
        map.put(GlobalConstant.KeyValues_Names.Longitude.toString(), "" + location.getLongitude());
        map.put(GlobalConstant.KeyValues_Names.DeviceSerialNo.toString(), constant.get_Mac_Address(con));
        map.put(GlobalConstant.KeyValues_Names.DeviceType.toString(), "android");
        map.put(GlobalConstant.KeyValues_Names.Flag.toString(), "facebook");

        if (isLoginScreen)
        {
            LOGIN_SERVICE(map, view);
        }
        else
        {
            map.put(GlobalConstant.KeyValues_Names.FirstName.toString(), profile.getName());
            map.put(GlobalConstant.KeyValues_Names.UserName.toString(), profile.getId());

            SIGNUP_SERVICE(map, view);
        }
    }

    /**
     @FbAcessToken
     */
    private void isAlreadyLogin(boolean currentAccessToken)
    {
        if (currentAccessToken)
        {
            stop_fb();
        }

    }

    public void stop_fb()
    {
        Log.e("Logout", "Logout");

        try
        {
            if (profileTracker.isTracking())
            {
                profileTracker.stopTracking();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            AccessToken.setCurrentAccessToken(null);
            Profile.setCurrentProfile(null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        Log.e("sharan :" + requestCode, "" + resultCode);

    }

    //****************************************************************************************************************************************

    View view;

    @Override
    public void onClick(View v)
    {
        constant.hide_keyboard(con);

        view = v;

        switch (v.getId())
        {
            case R.id.txtv_fb:

                if (location == null)
                {
                    if (!constant.check_GPS(con))
                    {
                        Show_GPS_Dailog();
                    }
                    else
                    {

                        GlobalConstant.show_snackbar(v, "Please try again, we are fetching your location.");
                        getLocation();
                    }
                }
                else
                {
                    facebook.performClick();
                }

                break;

            case R.id.llay_btn_login_registr:

                Validation_Check(v);
                // calMainActivity();

                break;

            case R.id.relvw_login:
                show_login_data();
                break;

            case R.id.rel_vw_register:
                show_register_data();
                break;

            case R.id.txtv_back:
                finish();
                break;

            case R.id.txtv_forgot_password:

                forgotPassword();

                break;

        }

    }

    void show_login_data()
    {
        isLoginScreen = true;
        view_login_line.setVisibility(View.VISIBLE);
        view_register_line.setVisibility(View.INVISIBLE);

        txtv_forgot_password.setVisibility(View.VISIBLE);
        txtv_login_registr.setText("Login with Consumity");
        txtv_fb.setText("Login with Facebook");
        imgv_login_register.setImageResource(R.mipmap.ic_login_man);
        lnrvw_register_full_name.setVisibility(View.GONE);
        llay_user_id.setVisibility(View.GONE);
        llay_cnfrm_pas.setVisibility(View.GONE);

    }

    void show_register_data()
    {
        isLoginScreen = false;
        edtv_full_name.requestFocus();
        edtv_paswrd.setText("");
        txtv_forgot_password.setVisibility(View.INVISIBLE);
        view_register_line.setVisibility(View.VISIBLE);
        view_login_line.setVisibility(View.INVISIBLE);
        lnrvw_register_full_name.setVisibility(View.VISIBLE);
        llay_user_id.setVisibility(View.VISIBLE);
        llay_cnfrm_pas.setVisibility(View.VISIBLE);
        txtv_login_registr.setText("Register with Consumity");
        txtv_fb.setText("Register with Facebook");
        imgv_login_register.setImageResource(R.mipmap.register_icon_man);
    }

    public void Validation_Check(View v)
    {
        if (isLoginScreen)
        {
            if (edtv_email_id.getText().toString().trim().isEmpty())
            {
                GlobalConstant.show_snackbar(v, "Please enter email id.");
            }
            else if (!(edtv_email_id.getText().toString().trim().matches(GlobalConstant.EmailPattern)))
            {
                GlobalConstant.show_snackbar(v, "Please enter valid email id.");
            }
            else if (edtv_paswrd.getText().toString().trim().isEmpty())
            {
                GlobalConstant.show_snackbar(v, "Please enter password.");
            }
            else if (sp.getString("GCM_Reg_id", "").isEmpty())
            {
                GlobalConstant.show_snackbar(v, "GCM Id not found. Please try after sometime..");
                registerInBackground();
            }
            else if (location == null)
            {
                if (!constant.check_GPS(con))
                {
                    Show_GPS_Dailog();
                }
                else
                {

                    GlobalConstant.show_snackbar(v, "Please try again, we are fetching your location.");
                    getLocation();
                }
            }
            else
            {
                HashMap<String, String> map = new HashMap<>();

                map.put(GlobalConstant.KeyValues_Names.EmailID.toString(), edtv_email_id.getText().toString().trim());
                map.put(GlobalConstant.KeyValues_Names.Password.toString(), edtv_paswrd.getText().toString().trim());
                map.put(GlobalConstant.KeyValues_Names.ApplicationID.toString(), sp.getString("GCM_Reg_id", ""));
                map.put(GlobalConstant.KeyValues_Names.Latitude.toString(), "" + location.getLatitude());
                map.put(GlobalConstant.KeyValues_Names.Longitude.toString(), "" + location.getLongitude());
                map.put(GlobalConstant.KeyValues_Names.DeviceSerialNo.toString(), constant.get_Mac_Address(con));
                map.put(GlobalConstant.KeyValues_Names.DeviceType.toString(), "android");


                if(edtv_email_id.getText().toString().trim().contains("@"))
                {
                    map.put(GlobalConstant.KeyValues_Names.Flag.toString(), "");
                }
                else
                {
                    map.put(GlobalConstant.KeyValues_Names.Flag.toString(), "username");
                }


                LOGIN_SERVICE(map, v);

            }
        }
        else
        {
            if (edtv_full_name.getText().toString().trim().isEmpty())
            {
                GlobalConstant.show_snackbar(v, "Please enter full name.");
            }
            else if (edtv_user_id.getText().toString().trim().isEmpty())
            {
                GlobalConstant.show_snackbar(v, "Please enter user name.");
            }
            else if (edtv_email_id.getText().toString().trim().isEmpty())
            {
                GlobalConstant.show_snackbar(v, "Please enter email id.");
            }
            else if (!(edtv_email_id.getText().toString().trim().matches(GlobalConstant.EmailPattern)))
            {
                GlobalConstant.show_snackbar(v, "Please enter valid email id.");
            }
            else if (edtv_paswrd.getText().toString().trim().isEmpty())
            {
                GlobalConstant.show_snackbar(v, "Please enter password.");
            }
            else if (edtv_paswrd.getText().toString().trim().length() < 5)
            {
                GlobalConstant.show_snackbar(v, "Please enter password minimum of 5 characters.");
            }
            else if (edtv_paswrd.getText().toString().trim().length() > 15)
            {
                GlobalConstant.show_snackbar(v, "Please enter password maximum of 15 characters.");
            }
            else if (edtv_cnfrm_pas.getText().toString().trim().isEmpty())
            {
                GlobalConstant.show_snackbar(v, "Please enter confirm password.");
            }
            else if (edtv_cnfrm_pas.getText().toString().trim().length() < 5)
            {
                GlobalConstant.show_snackbar(v, "Please enter confirm password minimum of 5 characters.");
            }
            else if (edtv_cnfrm_pas.getText().toString().trim().length() > 15)
            {
                GlobalConstant.show_snackbar(v, "Please enter confirm password maximum of 15 characters.");
            }
            else if (!edtv_cnfrm_pas.getText().toString().equals(edtv_paswrd.getText().toString()))
            {
                GlobalConstant.show_snackbar(v, "Password and confirm password did not match.");
            }
            else if (sp.getString("GCM_Reg_id", "").isEmpty())
            {
                GlobalConstant.show_snackbar(v, "GCM Id not found. Please try after sometime..");
                registerInBackground();
            }
            else if (location == null)
            {
                if (!constant.check_GPS(con))
                {
                    Show_GPS_Dailog();
                }
                else
                {

                    GlobalConstant.show_snackbar(v, "Please try again, we are fetching your location.");
                    getLocation();
                }
            }
            else
            {
                HashMap<String, String> map = new HashMap<>();

                map.put(GlobalConstant.KeyValues_Names.FirstName.toString(), edtv_full_name.getText().toString().trim());
                map.put(GlobalConstant.KeyValues_Names.UserName.toString(), edtv_user_id.getText().toString().trim());
                map.put(GlobalConstant.KeyValues_Names.EmailID.toString(), edtv_email_id.getText().toString().trim());
                map.put(GlobalConstant.KeyValues_Names.Password.toString(), edtv_paswrd.getText().toString().trim());
                map.put(GlobalConstant.KeyValues_Names.ApplicationID.toString(), sp.getString("GCM_Reg_id", ""));
                map.put(GlobalConstant.KeyValues_Names.Latitude.toString(), "" + location.getLatitude());
                map.put(GlobalConstant.KeyValues_Names.Longitude.toString(), "" + location.getLongitude());
                map.put(GlobalConstant.KeyValues_Names.DeviceSerialNo.toString(), constant.get_Mac_Address(con));
                map.put(GlobalConstant.KeyValues_Names.DeviceType.toString(), "android");
                map.put(GlobalConstant.KeyValues_Names.Flag.toString(), "");

                SIGNUP_SERVICE(map, v);

            }
        }
    }



    //************************************************************GCM*****************************************************************************
    private void registerInBackground()
    {
        new AsyncTask()
        {
            @Override
            protected Void doInBackground(Object... params)
            {
                String msg = "";
                try
                {
                    if (gcm == null)
                    {
                        gcm = GoogleCloudMessaging.getInstance(con);
                    }
                    String GCM_Reg_id = gcm.register(GlobalConstant.SENDER_ID);
                    sp.edit().putString("GCM_Reg_id", GCM_Reg_id).apply();
                    //					System.out
                    // .println("GCM_Reg_id===" +
                    // GCM_Reg_id);

                    Log.e("GCM_Reg_id", ".." + GCM_Reg_id);
                }
                catch (IOException ex)
                {
                    registerInBackground();
                    msg = "Error :" + ex.getMessage();
                }
                return null;
            }
        }.execute(null, null, null);
    }
    //*******************************************************************************************************************************************

    //************************************************************ Forgot Password Dialog*****************************************************************************

    EditText edtv_forgot_email = null;

    private void forgotPassword()
    {

        View.OnClickListener send = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edtv_forgot_email.getWindowToken(), 0);

                String email = edtv_forgot_email.getText().toString().trim();

                if (email.isEmpty())
                {
                    GlobalConstant.show_snackbar(view, "Please enter email id.");
                }
                else if (!(email.matches(GlobalConstant.EmailPattern)))
                {
                    GlobalConstant.show_snackbar(view, "Please enter valid email id.");
                }
                else
                {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("EmailId", email);

                     FORGOT_PASSWORD_SERVICE(map);
                }

            }
        };

        edtv_forgot_email = dialogs.show_forgot_dialog(con, send);
    }

    //*******************************************************************************************************************************************

    @Override
    protected void onResume()
    {
        super.onResume();
        getLocation();
    }

    @Override
    public void onPause()
    {
        super.onPause();

        if (google_location.mGoogleApiClient.isConnected())
        {
            google_location.stopLocationUpdates();
        }

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if (google_location.mGoogleApiClient != null)
        {
            google_location.mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        if (google_location.mGoogleApiClient.isConnected())
        {
            google_location.mGoogleApiClient.disconnect();
        }

    }

    @Override
    public void LOCATION_NOTIFIER(Location latlng)
    {
        location = latlng;

        if (latlng != null)
        {
            Log.e("Hello", "" + latlng.getLatitude() + "   " + latlng.getLongitude());
        }
    }

    void getLocation()
    {
        try
        {
            google_location.checkPlayServices();
            // Resuming the periodic location updates
            if (google_location.mGoogleApiClient.isConnected())
            {
                google_location.startLocationUpdates();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void Show_GPS_Dailog()
    {
        final Dialogs dialogs = new Dialogs();

        View.OnClickListener Cancel = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialogs.dialog.dismiss();
                finish();
            }
        };

        View.OnClickListener enable = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialogs.dialog.dismiss();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                con.startActivity(intent);
            }
        };

        dialogs.SHOW_ALERT_DAILOG(con, enable, Cancel, "Location service seems to be disable!.. Please enable location service", "EXIT", "ENABLE");
    }





    //*********************************************************Go inside app**************************************************************


    void calMainActivity(String data)
    {

        constant.saveToPreference(con, data, GlobalConstant.KeyValues_Names.UserData.toString());


        Intent i = new Intent(Login_RegisterActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }


    //*********************************************************FORGOT_PASSWORD_SERVICE**************************************************************

    public void FORGOT_PASSWORD_SERVICE(final HashMap<String, String> data)
    {
        dialogs.dialog.dismiss();

        GlobalConstant.execute(new Super_AsyncTask(con, data, GlobalConstant.base_url + "Customer/ForgotPassword", new Super_AsyncTask_Interface()
        {

            @Override
            public void onTaskCompleted(String output)
            {


                try
                {
                    JSONObject object = new JSONObject(output);
                    if (object.getString("Status").equalsIgnoreCase("success"))
                    {

                        GlobalConstant.show_snackbar(view, object.getString("Message"));

                    }
                    else
                    {
                        GlobalConstant.show_snackbar(view, object.getString("Message"));
                    }
                }
                catch (Exception ex)
                {
                    Log.e("Exception is", ex.toString());
                }

            }
        }, true));
    }

    //*********************************************************SignUp User here**************************************************************

    public void SIGNUP_SERVICE(final HashMap<String, String> data, final View v)
    {

        Log.e("SignUp Map", "" + data);

        stop_fb();

        GlobalConstant.execute(new Super_AsyncTask(con, data, GlobalConstant.base_url + "Customer/SaveCustomer", new Super_AsyncTask_Interface()
        {
            @Override
            public void onTaskCompleted(String output)
            {
                try
                {

                    JSONObject object = new JSONObject(output);
                    if (object.getString("Status").equalsIgnoreCase("success"))
                    {

                        GlobalConstant.show_Toast("You are successfully registered.", con);
                        calMainActivity(object.getString("Message"));

                    }
                    else
                    {
                        GlobalConstant.show_snackbar(v, object.getString("Message"));
                    }
                }
                catch (Exception ex)
                {
                    Log.e("Exception is", ex.toString());
                }

            }
        }, true));

    }

    //*********************************************************Login User here**************************************************************

    public void LOGIN_SERVICE(final HashMap<String, String> data, final View v)
    {
        Log.e("Login Map", "" + data);

        stop_fb();

        GlobalConstant.execute(new Super_AsyncTask(con, data, GlobalConstant.base_url + "Customer/ValidateUserCustomer", new Super_AsyncTask_Interface()
        {
            @Override
            public void onTaskCompleted(String output)
            {
                try
                {

                    JSONObject object = new JSONObject(output);
                    if (object.getString("Status").equalsIgnoreCase("success"))
                    {
                        calMainActivity(object.getString("Message"));

                    }
                    else
                    {
                        GlobalConstant.show_snackbar(v, object.getString("Message"));
                    }
                }
                catch (Exception ex)
                {
                    Log.e("Exception is", ex.toString());
                }

            }
        }, true));

    }
}
