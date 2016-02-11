package app.consumity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_about);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        findViewById(R.id.toolbar_my).setVisibility(View.VISIBLE);
        findViewById(R.id.imgv_top_left).setOnClickListener(this);
        ((TextView)findViewById(R.id.txtv_title)).setText("About");
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.imgv_top_left:
                finish();
                break;
        }
    }
}
