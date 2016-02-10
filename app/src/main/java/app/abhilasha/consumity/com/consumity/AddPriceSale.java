package app.abhilasha.consumity.com.consumity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.abhilasha.consumity.com.consumity.Adapters.CustomHorizontal_Recycle;
import app.abhilasha.consumity.com.consumity.Getter_Setter.Getter_setter_sale;
import app.abhilasha.consumity.com.consumity.Utils.Dialogs;
import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;

public class AddPriceSale extends Activity implements View.OnClickListener/*, AdapterView.OnItemSelectedListener*/
{
    private String[] saleType = {"Select the type of the sale", "Physical Coupon", "Coupon Code", "In-Store"};
    Spinner spinner;

    EditText ed_addprice, ed_date, ed_sale, ed_addphoto, ed_optional;
    TextView tv_saletype, tv_coupanphoto, tv_coupancode, tv_category;
    LinearLayout layout_addprice, layout_physicalcoupan, layout_coupan, layout, layout_instore, layout_category,
            layout_date, layout_sale, layout_addlocation, layout_addphoto, layout_optionalprice;
    AutoCompleteTextView autocomplete_addlocation;
    ImageView            img, img_coupan, imgv_top_right;
    Button btn_submit;
    boolean isFirstTime = false;
    RecyclerView recycler_categorytypes;
    Context      con;
    private List<Getter_setter_sale> feedsList;
    GlobalConstant constant = new GlobalConstant();
    Dialogs        dialogs  = new Dialogs();
    ArrayList<String> mobileArray = new ArrayList<String>(){
        {
            add("Electronics");
            add("Women's\nClothing");
            add("Men's\nClothing");
            add("Frozen");
            add("Simpsons");
            add("ipad");
            add("Fitbit");
            add("Keurig");
            add("Galaxy\nS6");
            add("Beats\nByDre");
            add("Xbox");
            add("Play\nStation");
            add("Kids &\nfamily");
            add("Home &\nGarden");
            add("Movies");
            add("Beauty");
            add("Sports\nOutdoor");
            add("Grocery");
        }};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_price_sale);

        con = this;
        constant.set_bold_font(con, findViewById(R.id.lay_font));
        findIds();

        Intent intent     = getIntent();
        String class_name = intent.getStringExtra("class");
        if (class_name.equals("price"))
        {
            show_new_price_data();
        }
        else if (class_name.equals("sale"))
        {
            show_new_sale_data();
        }

    }

    void findIds()
    {
        ((TextInputLayout) findViewById(R.id.input_layout_addprice)).setTypeface(constant.getAppTypeface(con));
        ((TextInputLayout) findViewById(R.id.input_layout_addlocation)).setTypeface(constant.getAppTypeface(con));
        ((TextInputLayout) findViewById(R.id.input_layout_sale)).setTypeface(constant.getAppTypeface(con));
        ((TextInputLayout) findViewById(R.id.input_layout_optional)).setTypeface(constant.getAppTypeface(con));
        ((TextInputLayout) findViewById(R.id.input_layout_date)).setTypeface(constant.getAppTypeface(con));
        ((TextInputLayout) findViewById(R.id.input_layout_addphoto)).setTypeface(constant.getAppTypeface(con));

        imgv_top_right = (ImageView) findViewById(R.id.imgv_top_left);
        imgv_top_right.setOnClickListener(this);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);

        //Add optional price
        layout_optionalprice = (LinearLayout) findViewById(R.id.layout_optionalprice);
        ed_optional = (EditText) findViewById(R.id.ed_optional);

        //Add photo
        layout_addphoto = (LinearLayout) findViewById(R.id.layout_addphoto);
        ed_addphoto = (EditText) findViewById(R.id.ed_addphoto);

        //Add a price
        layout_addprice = (LinearLayout) findViewById(R.id.layout_addprice);
        ed_addprice = (EditText) findViewById(R.id.ed_addprice);

        tv_saletype = (TextView) findViewById(R.id.tv_saletype);
//        tv_saletype.setOnClickListener(this);

        //====
        //Location layout
        layout_addlocation = (LinearLayout) findViewById(R.id.layout_addlocation);
        autocomplete_addlocation = (AutoCompleteTextView) findViewById(R.id.autocomplete_addlocation);

        //sale layout
        layout_sale = (LinearLayout) findViewById(R.id.layout_sale);
        ed_sale = (EditText) findViewById(R.id.ed_sale);

        //date optional
        layout_date = (LinearLayout) findViewById(R.id.layout_date);
        ed_date = (EditText) findViewById(R.id.ed_date);

        //select Category
        layout_category = (LinearLayout) findViewById(R.id.layout_category);
        tv_category = (TextView) findViewById(R.id.tv_category);

        //select sales type layout
        layout_coupan = (LinearLayout) findViewById(R.id.layout_coupane);
        layout_coupan.setOnClickListener(this);

        //Physical Code
        tv_coupanphoto = (TextView) findViewById(R.id.tv_coupanphoto);
        layout_physicalcoupan = (LinearLayout) findViewById(R.id.layout_physicalcoupan);
        layout = (LinearLayout) findViewById(R.id.layout);
        img = (ImageView) findViewById(R.id.img);
        img_coupan = (ImageView) findViewById(R.id.img_coupan);

//		Coupan Code
        tv_coupancode = (TextView) findViewById(R.id.tv_coupancode);

        //In-Store
        layout_instore = (LinearLayout) findViewById(R.id.layout_instore);

        //categories types
        recycler_categorytypes = (RecyclerView) findViewById(R.id.recyclerview_horizontal);
        feedsList = new ArrayList<>();
        Getter_setter_sale item = new Getter_setter_sale();
        for (int i = 0; i < 10; i++)
        {
            item.setBrand_name(("title"));
            feedsList.add(item);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(con, LinearLayoutManager.HORIZONTAL, false);
        recycler_categorytypes.setLayoutManager(layoutManager);
        recycler_categorytypes.setAdapter(new CustomHorizontal_Recycle(con, mobileArray));
        recycler_categorytypes.setVisibility(ViewGroup.GONE);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_submit:
                break;

            case R.id.imgv_top_left:
                finish();
                break;

            case R.id.layout_coupane:
//                spinner.performClick();

                View.OnClickListener Cancel = new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        dialogs.dialog.dismiss();

                    }
                };

                View.OnClickListener ok = new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {

                        int selected_item = dialogs.selected_radio_index + 1;
                        layout_select(selected_item);
                        dialogs.dialog.dismiss();

                    }
                };

                dialogs.SHOW_ALERT_DAILOG_TWO(con, ok, Cancel, "Sale Type", "Cancel", "Ok");

                break;

        }
    }

    void show_new_price_data()
    {
        ((TextView) findViewById(R.id.txtv_title)).setText("Add A New Price");

        layout_addprice.setVisibility(ViewGroup.VISIBLE);
        layout_addphoto.setVisibility(ViewGroup.VISIBLE);
        layout_optionalprice.setVisibility(ViewGroup.VISIBLE);

        layout_sale.setVisibility(ViewGroup.GONE);
        layout_date.setVisibility(ViewGroup.GONE);
        layout_coupan.setVisibility(ViewGroup.GONE);
        layout_physicalcoupan.setVisibility(ViewGroup.GONE);
        tv_coupancode.setVisibility(ViewGroup.GONE);
        layout_instore.setVisibility(ViewGroup.GONE);
        layout_category.setVisibility(ViewGroup.GONE);
        recycler_categorytypes.setVisibility(ViewGroup.GONE);

//        spinner.setSelection(0);
    }

    void show_new_sale_data()
    {
        ((TextView) findViewById(R.id.txtv_title)).setText("Add New Sale");
        //Sale types
        layout_sale.setVisibility(ViewGroup.VISIBLE);
        layout_date.setVisibility(ViewGroup.VISIBLE);
        layout_coupan.setVisibility(ViewGroup.VISIBLE);

        ed_sale.setEnabled(false);
        ed_date.setEnabled(false);
        autocomplete_addlocation.setEnabled(false);

        layout_addprice.setVisibility(ViewGroup.GONE);
        layout_addphoto.setVisibility(ViewGroup.GONE);
        layout_optionalprice.setVisibility(ViewGroup.GONE);


    }



    void layout_select(int selected_item)
    {
        ed_sale.setEnabled(true);
        ed_date.setEnabled(true);
        autocomplete_addlocation.setEnabled(true);
        switch (selected_item)
        {

            case 0:

                tv_saletype.setText("Select the type of the sale");
                layout_physicalcoupan.setVisibility(ViewGroup.GONE);
                layout_category.setVisibility(ViewGroup.GONE);
                recycler_categorytypes.setVisibility(ViewGroup.GONE);
                tv_coupancode.setVisibility(ViewGroup.GONE);
                layout_category.setVisibility(ViewGroup.GONE);
                recycler_categorytypes.setVisibility(ViewGroup.GONE);
                layout_instore.setVisibility(ViewGroup.GONE);
                layout_category.setVisibility(ViewGroup.GONE);
                recycler_categorytypes.setVisibility(ViewGroup.GONE);
                break;

            case 1:

                tv_saletype.setText("Physical Coupon");
                layout_physicalcoupan.setVisibility(ViewGroup.VISIBLE);
                tv_coupancode.setVisibility(ViewGroup.GONE);
                layout_instore.setVisibility(ViewGroup.GONE);
                layout_category.setVisibility(ViewGroup.VISIBLE);
                recycler_categorytypes.setVisibility(ViewGroup.VISIBLE);
                break;

            case 2:

                tv_saletype.setText("Coupon Code");
                tv_coupancode.setVisibility(ViewGroup.VISIBLE);
                layout_instore.setVisibility(ViewGroup.GONE);
                layout_physicalcoupan.setVisibility(ViewGroup.GONE);
                layout_category.setVisibility(ViewGroup.VISIBLE);
                recycler_categorytypes.setVisibility(ViewGroup.VISIBLE);
                break;

            case 3:

                tv_saletype.setText("In-store");
                layout_instore.setVisibility(ViewGroup.VISIBLE);
                tv_coupancode.setVisibility(ViewGroup.GONE);
                layout_physicalcoupan.setVisibility(ViewGroup.GONE);
                layout_category.setVisibility(ViewGroup.VISIBLE);
                recycler_categorytypes.setVisibility(ViewGroup.VISIBLE);
                break;
        }
    }


}
