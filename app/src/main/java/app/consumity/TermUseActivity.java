package app.consumity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class TermUseActivity extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_term_of_use);

        findViewById(R.id.toolbar_my).setVisibility(View.VISIBLE);
        findViewById(R.id.imgv_top_left).setOnClickListener(this);
        ((TextView)findViewById(R.id.txtv_title)).setText("Terms of use");
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
