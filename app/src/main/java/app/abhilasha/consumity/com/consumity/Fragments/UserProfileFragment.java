package app.abhilasha.consumity.com.consumity.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import app.abhilasha.consumity.com.consumity.R;
import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;

/**
 Created by ameba on 1/14/16. */
public class UserProfileFragment extends Fragment
{
    Context con;
    GlobalConstant constant = new GlobalConstant();
    EditText edtv_fullname, edtv_username, edtv_email;
    ImageView imgv_user_profile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_profile, null);
        con = getActivity();
        constant.set_bold_font(con, view);

        findIds(view);

        setData();

        return view;
    }

    void findIds(View view)
    {
        view.findViewById(R.id.toolbar_my).setVisibility(View.GONE);
        view.findViewById(R.id.lay_edit_profile).setVisibility(View.GONE);
        view.findViewById(R.id.txtv_profile_change_pic).setVisibility(View.GONE);

        imgv_user_profile=(ImageView)view.findViewById(R.id.imgv_user_profile);

        edtv_fullname = (EditText) view.findViewById(R.id.edtv_fullname);
        edtv_username = (EditText) view.findViewById(R.id.edtv_username);
        edtv_email = (EditText) view.findViewById(R.id.edtv_email);

        edtv_fullname.setEnabled(false);
        edtv_username.setEnabled(false);
        edtv_email.setEnabled(false);
    }

    private void setData()
    {
        String full_name = constant.getParticularValueFromPreference(con, GlobalConstant.KeyValues_Names.UserData.toString(), GlobalConstant.KeyValues_Names.FirstName.toString());
        String user_name = constant.getParticularValueFromPreference(con, GlobalConstant.KeyValues_Names.UserData.toString(), GlobalConstant.KeyValues_Names.UserName.toString());
        String email = constant.getParticularValueFromPreference(con, GlobalConstant.KeyValues_Names.UserData.toString(), GlobalConstant.KeyValues_Names.EmailID.toString());
        String flag=constant.getParticularValueFromPreference(con, GlobalConstant.KeyValues_Names.UserData.toString(), GlobalConstant.KeyValues_Names.Flag.toString());

        String photo_url = constant.getParticularValueFromPreference(con, GlobalConstant.KeyValues_Names.UserData.toString(), GlobalConstant.KeyValues_Names.PhotoPath.toString());

        edtv_fullname.setText(full_name);
        edtv_username.setText(user_name);

        if(flag.equalsIgnoreCase("facebook"))
        {
            String[] e=email.split("@");


            edtv_email.setText(e[0]);
        }
        else
        {
            edtv_email.setText(email);
        }

        constant.setRoundImage(con, imgv_user_profile, photo_url);
    }
}
