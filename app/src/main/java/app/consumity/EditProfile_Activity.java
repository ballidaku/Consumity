package app.consumity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import app.consumity.Async_Thread.Super_AsyncTask;
import app.consumity.Async_Thread.Super_AsyncTask_Interface;
import app.consumity.Utils.Dialogs;
import app.consumity.Utils.GlobalConstant;
import eu.janmuller.android.simplecropimage.CropImage;

/**
 Created by ameba on 7/1/16. */
public class EditProfile_Activity extends AppCompatActivity implements View.OnClickListener
{
    TextView txtv_profile_cancel_pic, txtv_profile_change_pic;
    TextView txtv_title;

    EditText edtv_fullname, edtv_username, edtv_email, edtv_old_password, edtv_new_password,edtv_confirm_password;
    ImageView imgv_user_profile;
    CheckBox  chkb_change_password;

    LinearLayout llay_save_details, llay_isfacebook_manuall_login,llay_ischeckebox_checked;

    Context con;
    GlobalConstant constant = new GlobalConstant();
    Dialogs        dialogs  = new Dialogs();

    String base64_photoPath="";
    boolean isImageUpdated=false;
    String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        con = this;
        constant.set_bold_font(con, findViewById(R.id.lay_font));
        findViewById();

        setData();
    }

    private void findViewById()
    {
        txtv_title = (TextView) findViewById(R.id.txtv_title);
        txtv_title.setText("Edit Profile");
        findViewById(R.id.lay_edit_profile).setVisibility(View.VISIBLE);

        txtv_profile_cancel_pic = (TextView) findViewById(R.id.txtv_profile_cancel_pic);
        (txtv_profile_change_pic = (TextView) findViewById(R.id.txtv_profile_change_pic)).setOnClickListener(this);

        txtv_profile_cancel_pic.setPaintFlags(txtv_profile_cancel_pic.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtv_profile_change_pic.setPaintFlags(txtv_profile_change_pic.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtv_profile_change_pic.setVisibility(View.VISIBLE);

        imgv_user_profile = (ImageView) findViewById(R.id.imgv_user_profile);

        edtv_fullname = (EditText) findViewById(R.id.edtv_fullname);
        ( edtv_username = (EditText) findViewById(R.id.edtv_username)).setEnabled(false);
        edtv_email = (EditText) findViewById(R.id.edtv_email);


        edtv_old_password = (EditText) findViewById(R.id.edtv_old_password);
        edtv_new_password = (EditText) findViewById(R.id.edtv_new_password);
        edtv_confirm_password= (EditText) findViewById(R.id.edtv_confirm_password);


        chkb_change_password = (CheckBox) findViewById(R.id.chkb_change_password);

        (llay_save_details = (LinearLayout) findViewById(R.id.llay_save_details)).setOnClickListener(this);
        llay_isfacebook_manuall_login=(LinearLayout)findViewById(R.id.llay_isfacebook_manuall_login);
        llay_ischeckebox_checked=(LinearLayout)findViewById(R.id.llay_ischeckebox_checked);




        llay_ischeckebox_checked.setVisibility(View.GONE);

        chkb_change_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {

                int  view =b == true ? View.VISIBLE : View.GONE;
                llay_ischeckebox_checked.setVisibility(view);

                edtv_new_password.setText("");
                edtv_old_password.setText("");
                edtv_confirm_password.setText("");

            }
        });

        findViewById(R.id.imgv_top_left).setOnClickListener(this);
    }





    private void setData()
    {
        String full_name = constant.getParticularValueFromPreference(con, GlobalConstant.KeyValues_Names.UserData.toString(), GlobalConstant.KeyValues_Names.FirstName.toString());
        String user_name = constant.getParticularValueFromPreference(con, GlobalConstant.KeyValues_Names.UserData.toString(), GlobalConstant.KeyValues_Names.UserName.toString());
        String email     = constant.getParticularValueFromPreference(con, GlobalConstant.KeyValues_Names.UserData.toString(), GlobalConstant.KeyValues_Names.EmailID.toString());
        flag=constant.getParticularValueFromPreference(con, GlobalConstant.KeyValues_Names.UserData.toString(), GlobalConstant.KeyValues_Names.Flag.toString());

        String photo_url = constant.getParticularValueFromPreference(con, GlobalConstant.KeyValues_Names.UserData.toString(), GlobalConstant.KeyValues_Names.PhotoPath.toString());

        edtv_fullname.setText(full_name);
        edtv_username.setText(user_name);
        edtv_email.setText(email);

        constant.setRoundImage(con, imgv_user_profile, photo_url);


        if(flag.equalsIgnoreCase("facebook"))
        {
            String[] e=email.split("@");
            edtv_email.setText(e[0]);
            llay_isfacebook_manuall_login.setVisibility(View.GONE);
        }
        else
        {
            edtv_email.setText(email);
        }


    }



    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.imgv_top_left:
                finish();
                break;

            case R.id.llay_save_details:

//                saveData();
                VALIDATION_CHECK(v);
                break;


            case R.id.txtv_profile_change_pic:

                dialogs.selectImage(con);

                break;

            default:
                break;
        }
    }

   /* private void saveData()
    {
        Log.e("IScheck", "" + chkb_change_password.isChecked());
    }*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK)
        {
            if (requestCode == 1)
            {
                String filePath = null;
                filePath = Environment.getExternalStorageDirectory() + "/temp.jpg";
                if (filePath != null)
                {
                    constant.performCrop(filePath, con);
                }

            }
            else if (requestCode == 2)
            {
                try
                {
                    String filePathG = Environment.getExternalStorageDirectory() + "/temp.jpg";
                    InputStream inputStream;
                    inputStream = con.getContentResolver().openInputStream(data.getData());
                    FileOutputStream fileOutputStream = new FileOutputStream(filePathG);
                    constant.copyStream(inputStream, fileOutputStream);
                    fileOutputStream.close();
                    inputStream.close();
                    constant.performCrop(filePathG, con);
                }
                catch (IOException ex)
                {
                    Log.e("Exception is", ex.toString());
                }

            }
            else if (requestCode == 3)
            {
                if (resultCode == Activity.RESULT_OK)
                {
                    String path = data.getStringExtra(CropImage.IMAGE_PATH);
                    if (path != null)
                    {
                       setPhoto_Path(path);

                    }
                }
            }
        }

    }

    public void setPhoto_Path(String path)
    {
        try
        {
            base64_photoPath = constant.Get_pic(con, path, imgv_user_profile);
            isImageUpdated = true;
        }
        catch (IOException e)
        {
            Log.e("Input output", "Exception" + e.toString());
        }
    }



    void VALIDATION_CHECK(View v)
    {
        if (edtv_fullname.getText().toString().trim().isEmpty())
        {
            GlobalConstant.show_snackbar(v, "Please enter full name.");
        }
        else if (edtv_username.getText().toString().trim().isEmpty())
        {
            GlobalConstant.show_snackbar(v, "Please enter user name.");
        }
        else if (edtv_email.getText().toString().trim().isEmpty())
        {
            GlobalConstant.show_snackbar(v, "Please enter email id.");
        }
        else if (!(edtv_email.getText().toString().trim().matches(GlobalConstant.EmailPattern))
                    && !flag.equalsIgnoreCase("facebook"))
        {
            GlobalConstant.show_snackbar(v, "Please enter valid email id.");
        }
        else if (edtv_old_password.getText().toString().trim().isEmpty()
                  && !flag.equalsIgnoreCase("facebook")
                  && chkb_change_password.isChecked())
        {
            GlobalConstant.show_snackbar(v, "Please enter old password.");
        }
        else if ( edtv_old_password.getText().toString().trim().length()<5
                  && !flag.equalsIgnoreCase("facebook")
                  && chkb_change_password.isChecked())
        {
            GlobalConstant.show_snackbar(v, "Please enter old password minimum of 5 characters.");
        }
        else if (edtv_new_password.getText().toString().trim().isEmpty()
                  && !flag.equalsIgnoreCase("facebook")
                  && chkb_change_password.isChecked())
        {
            GlobalConstant.show_snackbar(v, "Please enter new password.");
        }
        else if (edtv_new_password.getText().toString().trim().length()<5
                  && !flag.equalsIgnoreCase("facebook")
                  && chkb_change_password.isChecked())
        {
            GlobalConstant.show_snackbar(v, "Please enter new password minimum of 5 characters.");
        }
        else if (edtv_confirm_password.getText().toString().trim().isEmpty()
                  && !flag.equalsIgnoreCase("facebook")
                  && chkb_change_password.isChecked())
        {
            GlobalConstant.show_snackbar(v, "Please enter confirm password.");
        }
        else if ( edtv_confirm_password.getText().toString().trim().length()<5
                  && !flag.equalsIgnoreCase("facebook")
                  && chkb_change_password.isChecked())
        {
            GlobalConstant.show_snackbar(v, "Please enter confirm password minimum of 5 characters.");
        }
        else if (!(edtv_new_password.getText().toString().equals(edtv_confirm_password.getText().toString()))
                     && !flag.equalsIgnoreCase("facebook")
                     && chkb_change_password.isChecked())
        {
            GlobalConstant.show_snackbar(v, "New and confirm password did not match.");
        }
        else
        {


            HashMap<String, String> UpdateProfile_Paramters = new HashMap<>();

            UpdateProfile_Paramters.put(GlobalConstant.KeyValues_Names.CustomerId.toString(), constant.getParticularValueFromPreference(con, GlobalConstant.KeyValues_Names.UserData.toString(), GlobalConstant.KeyValues_Names.CustomerId.toString()));
            UpdateProfile_Paramters.put(GlobalConstant.KeyValues_Names.EmailID.toString(),edtv_email.getText().toString().trim() );
            UpdateProfile_Paramters.put(GlobalConstant.KeyValues_Names.FirstName.toString(),edtv_fullname.getText().toString().trim() );
            UpdateProfile_Paramters.put(GlobalConstant.KeyValues_Names.UserName.toString(),edtv_username.getText().toString().trim() );

            if(flag.equalsIgnoreCase("facebook") || !chkb_change_password.isChecked())
            {
                UpdateProfile_Paramters.put(GlobalConstant.KeyValues_Names.oldpassword.toString(), "");
                UpdateProfile_Paramters.put(GlobalConstant.KeyValues_Names.Password.toString(), "");
            }
            else
            {
                UpdateProfile_Paramters.put(GlobalConstant.KeyValues_Names.oldpassword.toString(), edtv_old_password.getText().toString().trim());
                UpdateProfile_Paramters.put(GlobalConstant.KeyValues_Names.Password.toString(), edtv_new_password.getText().toString().trim());
            }


            if (isImageUpdated)
            {
                UpdateProfile_Paramters.put(GlobalConstant.KeyValues_Names.PhotoPath.toString(), base64_photoPath);
            }
            else
            {
                UpdateProfile_Paramters.put(GlobalConstant.KeyValues_Names.PhotoPath.toString(), "");
            }

          UPDATE_PROFILE_SERVICE(UpdateProfile_Paramters);

        }
    }

    //************************************************Update Profile*************************************************************************

    public void UPDATE_PROFILE_SERVICE(final HashMap<String, String> data)
    {

        Log.e("Hit",""+data);

        GlobalConstant.execute(new Super_AsyncTask(con, data, GlobalConstant.base_url + "Customer/UpdateProfile", new Super_AsyncTask_Interface()
        {
            @Override
            public void onTaskCompleted(String output)
            {
                try
                {
                    JSONObject object = new JSONObject(output);
                    if (object.getString("Status").equals("success"))
                    {
                        // Log.e("update response", object.toString());


                         constant.saveToPreference(con, object.getString("Message"), GlobalConstant.KeyValues_Names.UserData.toString());
//                        Constant_Class.SAVE_PERSONNEL_DATA(con, object.getString("Message"), Constant_Class.KeyValues_Names.Own_Data.toString());

                        GlobalConstant.show_Toast("Profile updated successfully.", con);

//                        details = Constant_Class.GET_PERSONNEL_DATA(con, Constant_Class.KeyValues_Names.Own_Data.toString());
//                        constant.setRoundImage(con, profile, details.optString(Constant_Class.Signup_Params.PhotoPath.toString()));

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
