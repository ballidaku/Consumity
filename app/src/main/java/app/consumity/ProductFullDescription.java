package app.consumity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import app.consumity.Adapters.Latest_Sale_Grid_Adapter;
import app.consumity.Adapters.Online_Sale_Recycle_Adapter;
import app.consumity.Utils.GlobalConstant;

public class ProductFullDescription extends AppCompatActivity implements View.OnClickListener
{
    Context                     con;
    RecyclerView                recycler_vw_online_sales;
    Online_Sale_Recycle_Adapter online_sale_adapter;
    GridView                    grid_vw_latest_sale;
    GlobalConstant constant = new GlobalConstant();
    ImageView imgv_product_banner;
    ArrayList<String> product_banner_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_full_description);

        con = this;
        constant.set_bold_font(con, findViewById(R.id.lay_font));
        constant.set_actual_bold_font(con, findViewById(R.id.txt_vw_online_sales));
        constant.set_actual_bold_font(con, findViewById(R.id.txt_vw_related_products));

        setToolbar();

        product_banner_list.add(R.mipmap.product_banner1 + "");
        product_banner_list.add(R.mipmap.product_banner2 + "");
        product_banner_list.add(R.mipmap.product_banner3+"");
        product_banner_list.add(R.mipmap.product_banner4+"");
        Intent intent = getIntent();
//        intent.getIntExtra("position", 0);

        findIds();

        imgv_product_banner.setBackgroundResource(Integer.parseInt(product_banner_list.get(intent.getIntExtra("position", 0))));
    }

    void setToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_col);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setOverflowIcon(getResources().getDrawable(R.mipmap.my_overflow_menu_ic));
        toolbar.setNavigationIcon(R.mipmap.ic_back_btn);
    }
    public void findIds()
    {
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setExpandedTitleColor(R.color.black);
        collapsingToolbar.setTitle("Bring Dress");

        findViewById(R.id.lay_product_details).setOnClickListener(this);
        findViewById(R.id.lay_bargain_price).setOnClickListener(this);

        recycler_vw_online_sales = (RecyclerView) findViewById(R.id.recycler_vw_online_sales);
        GlobalConstant.MyLinearLayoutManager layoutManager = constant.new MyLinearLayoutManager(con, LinearLayoutManager.VERTICAL, false);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_vw_online_sales.setLayoutManager(layoutManager);
        recycler_vw_online_sales.setHasFixedSize(true);
        online_sale_adapter = new Online_Sale_Recycle_Adapter(con);
        recycler_vw_online_sales.setAdapter(online_sale_adapter);

        imgv_product_banner = (ImageView) findViewById(R.id.imgv_product_banner);

        grid_vw_latest_sale = (GridView) findViewById(R.id.grid_vw_latest_sale);
        grid_vw_latest_sale.setAdapter(new Latest_Sale_Grid_Adapter(con));
        GlobalConstant.setListViewHeightBasedOnItems(grid_vw_latest_sale, 0);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.lay_product_details:
                startActivity(new Intent(con, ViewProductDetails.class));
                break;

            case R.id.lay_bargain_price:
                Intent intent = new Intent(con, AddPriceSale.class);
                intent.putExtra("class", "price");
                startActivity(intent);
                break;

            default:
                break;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_items_product_full_desc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.add_new_sale:
                Intent intent = new Intent(con, AddPriceSale.class);
                intent.putExtra("class", "sale");
                startActivity(intent);
                break;

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
        }
        return true;
    }
}
