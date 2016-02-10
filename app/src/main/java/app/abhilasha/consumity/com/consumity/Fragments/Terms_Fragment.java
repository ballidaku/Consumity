package app.abhilasha.consumity.com.consumity.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.abhilasha.consumity.com.consumity.R;
import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;

/**
 * Created by ameba on 7/1/16.
 */
public class Terms_Fragment extends Fragment
{
    Context con;
    GlobalConstant constant = new GlobalConstant();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_term_of_use, null);
        con = getActivity();
        constant.set_bold_font(con, view);
        view.findViewById(R.id.toolbar_my).setVisibility(View.GONE);
        return view;
    }
}