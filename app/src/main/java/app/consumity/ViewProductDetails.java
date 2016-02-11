package app.consumity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import app.consumity.Utils.GlobalConstant;

public class ViewProductDetails extends AppCompatActivity implements View.OnClickListener
{
    Context con;
    GlobalConstant constant = new GlobalConstant();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product_details);
        con = this;
        constant.set_bold_font(con, findViewById(R.id.lay_font));
        findIds();
    }

    public void findIds()
    {
        findViewById(R.id.imgv_top_left).setOnClickListener(this);
        ((TextView) findViewById(R.id.txtv_title)).setText("Product Details");
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
