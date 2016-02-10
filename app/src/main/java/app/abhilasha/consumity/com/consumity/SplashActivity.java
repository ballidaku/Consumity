package app.abhilasha.consumity.com.consumity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;

public class SplashActivity extends AppCompatActivity
{

    Context con;
    GlobalConstant constant = new GlobalConstant();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        con = this;

        splashmethod();
    }

    private void splashmethod()
    {
        android.os.Handler h = new android.os.Handler();
        h.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {

                if (!constant.getFromPreference(con, GlobalConstant.KeyValues_Names.UserData.toString()).trim().isEmpty())
                {
                    Intent i = new Intent(con, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
                else
                {
                    startActivity(new Intent(SplashActivity.this, SplashViewPagerActivity.class));
                    finish();
                }

            }
        }, 2000);
    }

}
