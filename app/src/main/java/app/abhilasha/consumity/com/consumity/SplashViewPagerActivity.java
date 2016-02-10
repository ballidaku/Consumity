package app.abhilasha.consumity.com.consumity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;

/**
 Created by ameba on 21/12/15. */
public class SplashViewPagerActivity extends AppCompatActivity implements View.OnClickListener
{

    int gallery_grid_Images[] = {R.mipmap.flip_mobile_one, R.mipmap.flip_mobile_two, R.mipmap.flip_mobile_three, R.mipmap.flip_mobile_four, R.mipmap.flip_mobile_five};
    ImageView imgv_mobile, imgv_dot_one, imgv_dot_two, imgv_dot_three, imgv_dot_four, imgv_dot_five;
    private static final long GET_DATA_INTERVAL = 2000;
    int     index = 0;
    Handler hand  = new Handler();
    LinearLayout lnrvw_login, lnrvw_register, lnr_vw_fb, lin_lay_direct_enter;
    Context con;
    GlobalConstant constant = new GlobalConstant();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_view_pager);
        con = this;
        constant.set_bold_font(con, findViewById(R.id.lay_font));

        findViewById();

        hand.postDelayed(run, GET_DATA_INTERVAL);

    }

    private void findViewById()
    {
        lin_lay_direct_enter = (LinearLayout) findViewById(R.id.lin_lay_direct_enter);
        imgv_mobile = (ImageView) findViewById(R.id.imgv_mobile);
        imgv_dot_one = (ImageView) findViewById(R.id.imgv_dot_one);
        imgv_dot_two = (ImageView) findViewById(R.id.imgv_dot_two);
        imgv_dot_three = (ImageView) findViewById(R.id.imgv_dot_three);
        imgv_dot_four = (ImageView) findViewById(R.id.imgv_dot_four);
        imgv_dot_five = (ImageView) findViewById(R.id.imgv_dot_five);
        lnrvw_login = (LinearLayout) findViewById(R.id.lnrv_login);
        lnrvw_register = (LinearLayout) findViewById(R.id.lnrv_register);
        findViewById(R.id.lay_about).setOnClickListener(this);
        findViewById(R.id.lay_terms_use).setOnClickListener(this);

        lnrvw_login.setOnClickListener(this);
        lnrvw_register.setOnClickListener(this);
        lin_lay_direct_enter.setOnClickListener(this);
    }

    Runnable run = new Runnable()
    {
        @Override
        public void run()
        {
            imgv_mobile.setBackgroundDrawable(getResources().getDrawable(gallery_grid_Images[index++]));
            if (index == 0)
            {
                imgv_dot_one.setBackgroundResource(R.mipmap.full_dot_gray);
                imgv_dot_two.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_three.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_four.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_five.setBackgroundResource(R.mipmap.dim_dot_gray);
            }
            if (index == 1)
            {
                imgv_dot_one.setBackgroundResource(R.mipmap.full_dot_gray);
                imgv_dot_two.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_three.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_four.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_five.setBackgroundResource(R.mipmap.dim_dot_gray);
            }
            if (index == 2)
            {
                imgv_dot_one.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_two.setBackgroundResource(R.mipmap.full_dot_gray);
                imgv_dot_three.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_four.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_five.setBackgroundResource(R.mipmap.dim_dot_gray);
            }
            if (index == 3)
            {
                imgv_dot_one.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_two.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_three.setBackgroundResource(R.mipmap.full_dot_gray);
                imgv_dot_four.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_five.setBackgroundResource(R.mipmap.dim_dot_gray);
            }
            if (index == 4)
            {
                imgv_dot_one.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_two.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_three.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_four.setBackgroundResource(R.mipmap.full_dot_gray);
                imgv_dot_five.setBackgroundResource(R.mipmap.dim_dot_gray);
            }
            if (index == 5)
            {
                imgv_dot_one.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_two.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_three.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_four.setBackgroundResource(R.mipmap.dim_dot_gray);
                imgv_dot_five.setBackgroundResource(R.mipmap.full_dot_gray);
            }

            if (index == gallery_grid_Images.length)
                index = 0;
            hand.postDelayed(run, GET_DATA_INTERVAL);
        }
    };

    @Override
    public void onClick(View v)
    {
        Intent i = new Intent(SplashViewPagerActivity.this, Login_RegisterActivity.class);
        switch (v.getId())
        {
            case R.id.lnrv_login:
                i.putExtra("login", "login");
                startActivity(i);
                break;

            case R.id.lnrv_register:
                i.putExtra("login", "register");
                startActivity(i);
                break;

            case R.id.lin_lay_direct_enter:
                calMainActivity();
                break;

            case R.id.lay_about:
                startActivity(new Intent(con, AboutActivity.class));
                break;

            case R.id.lay_terms_use:
                startActivity(new Intent(con, TermUseActivity.class));
                break;

            default:
                break;

        }

    }

    void calMainActivity()
    {

        Intent i = new Intent(con, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

    }

}
