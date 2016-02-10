package app.abhilasha.consumity.com.consumity.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import app.abhilasha.consumity.com.consumity.EditProfile_Activity;
import app.abhilasha.consumity.com.consumity.R;
import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;

/**
 * Created by ameba on 7/1/16.
 */
public class Settings_Fragment extends Fragment implements View.OnClickListener
{
    Context con;
    GlobalConstant constant = new GlobalConstant();


    TextView txtv_logout;

    SwitchCompat switch_notification;

    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_setting, null);
        con = getActivity();
        constant.set_bold_font(con, view);

        findViewById(view);




        return view;
    }

    private void findViewById(View view)
    {
        view.findViewById(R.id.txtv_edit_profile).setOnClickListener(this);
        view.findViewById(R.id.txtv_logout).setOnClickListener(this);

        switch_notification = (SwitchCompat) view.findViewById(R.id.switch_notification);


        preferences = con.getSharedPreferences("Notification", con.MODE_WORLD_READABLE);

        switch_notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {

                if (isChecked)
                {
                    preferences.edit().putBoolean("notification_on_off", true).apply();
                }
                else
                {
                    preferences.edit().putBoolean("notification_on_off", false).apply();
                }
            }
        });

        if (preferences.getBoolean("notification_on_off", true) == true)
        {
            switch_notification.setChecked(true);
        }
        else
        {
            switch_notification.setChecked(false);
        }

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.txtv_edit_profile:
                startActivity(new Intent(con, EditProfile_Activity.class));
                break;

            case R.id.txtv_logout:

                constant.logout_locally(con);
                break;
        }
    }





}
