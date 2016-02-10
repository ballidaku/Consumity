package app.abhilasha.consumity.com.consumity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;
import app.abhilasha.consumity.com.consumity.tab.SalesTab;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener
{
    Context con;
    GlobalConstant constant = new GlobalConstant();
    TextView txtv_title;
    ArrayList<String> sub_category = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        con = this;
        constant.set_bold_font(con, findViewById(R.id.lay_font));
        findViewById();

        Intent intent = getIntent();
        txtv_title.setText(intent.getStringExtra("title").replaceAll("\n", " "));

        sub_category.add("Sub\nCategory");
        sub_category.add("Sub\nCategory");
        sub_category.add("Sub\nCategory");
        sub_category.add("Sub\nCategory");
        sub_category.add("Sub\nCategory");
        sub_category.add("Sub\nCategory");
        sub_category.add("Sub\nCategory");
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("sub_category", sub_category);

        FragmentManager           fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment =  new SalesTab();
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container_body, fragment, "NewFragmentTag");
        fragmentTransaction.commit();
    }

    private void findViewById()
    {
        txtv_title = (TextView) findViewById(R.id.txtv_title);
        findViewById(R.id.imgv_top_left).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.imgv_top_left:
                finish();
                break;

            default:
                break;
        }
    }

}
