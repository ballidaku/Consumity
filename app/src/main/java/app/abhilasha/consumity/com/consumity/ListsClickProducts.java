package app.abhilasha.consumity.com.consumity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import app.abhilasha.consumity.com.consumity.Adapters.Grid_Adapter_Products;
import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;

public class ListsClickProducts extends AppCompatActivity
{
    Context  con;
    GridView grid_vw_products;
    GlobalConstant constant = new GlobalConstant();
    boolean isDelMode = false;
    Grid_Adapter_Products grid_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists_click_product);
        con = this;
        setToolbar();
        constant.set_bold_font(con, findViewById(R.id.lay_font));

        Intent intent = getIntent();
        String title  = intent.getStringExtra("list_name");
        ((TextView) findViewById(R.id.txt_vw_toolbar)).setText(title);
        findViewById(R.id.img_vw_toolbar_barcode).setVisibility(View.GONE);
        findViewById(R.id.img_vw_toolbar_sec_barcode).setVisibility(View.GONE);

        grid_vw_products = (GridView) findViewById(R.id.grid_vw_products);
        grid_adapter = new Grid_Adapter_Products(con, isDelMode);
        grid_vw_products.setAdapter(grid_adapter);
//        GlobalConstant.setListViewHeightBasedOnItems(grid_vw_products, 0);

    }

    void setToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setOverflowIcon(getResources().getDrawable(R.mipmap.my_overflow_menu_ic));
        toolbar.setNavigationIcon(R.mipmap.ic_back_btn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_items_lists_click_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:

                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN)
                {
                    supportFinishAfterTransition();
                }
                else
                {
                    finish();
                }
                break;

            case R.id.del_products:
                if (isDelMode == false)
                {
                    isDelMode = true;
                    grid_adapter.show_del_icon(isDelMode);
                    grid_adapter.notifyDataSetChanged();
                }
                else
                {
                    isDelMode = false;
                    grid_adapter.show_del_icon(isDelMode);
                    grid_adapter.notifyDataSetChanged();
                }
                break;
        }
        return true;
    }
}
