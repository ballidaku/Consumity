package app.abhilasha.consumity.com.consumity.tab;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import app.abhilasha.consumity.com.consumity.Adapters.CustomHorizontal_Recycle;
import app.abhilasha.consumity.com.consumity.Adapters.Sale_Grid_Adapter;
import app.abhilasha.consumity.com.consumity.CategoryActivity;
import app.abhilasha.consumity.com.consumity.Getter_Setter.Getter_setter_sale;
import app.abhilasha.consumity.com.consumity.R;
import app.abhilasha.consumity.com.consumity.Utils.ExpandableHeightGridView;
import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;

/**
 * Created by ameba on 22/12/15.
 */
public class SalesTab extends Fragment
{

    ExpandableHeightGridView grid_vw_today_sale, grid_vw_yesterday_sale;
    //    RecyclerView.LayoutManager mLayoutManager;
    Sale_Grid_Adapter mAdapter;
    private List<Getter_setter_sale> feedsList;
    RecyclerView recyclerview_horizontal;
    GlobalConstant constant = new GlobalConstant();
    Context                    con;
    //    RecyclerView recyclerView;
//    CustomRecyclerView         recyclerView;
    StaggeredGridLayoutManager gaggeredGridLayoutManager;
    //    String[] mobileArray = {"Electronics", "Women's\nClothing", "Men's\nClothing",
    ArrayList<String> mobileArray = new ArrayList<String>()
    {
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
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_sales_tab_layout, container, false);

        con = getActivity();
        constant.set_bold_font(con, view);

        Bundle bundle = this.getArguments();
        if (bundle != null)
        {
            mobileArray = bundle.getStringArrayList("sub_category");
        }

        findViewById(view);
        Log.e("tab", "sale");


        Getter_setter_sale item = new Getter_setter_sale();
        for (int i = 0; i < 4; i++)
        {
            item.setBrand_name(("title"));
            feedsList.add(item);
        }
        mAdapter = new Sale_Grid_Adapter(getActivity(), feedsList);
        grid_vw_today_sale.setExpanded(true);
        grid_vw_today_sale.setAdapter(mAdapter);
//        GlobalConstant.setListViewHeightBasedOnItems(grid_vw_today_sale, feedsList.size());

        grid_vw_yesterday_sale.setExpanded(true);
        grid_vw_yesterday_sale.setAdapter(mAdapter);
//        GlobalConstant.setListViewHeightBasedOnItems(grid_vw_yesterday_sale, feedsList.size());

        return view;
    }

    private void findViewById(View view)
    {
//        recyclerView = (CustomRecyclerView) view.findViewById(R.id.recycler_view);
        grid_vw_today_sale = (ExpandableHeightGridView) view.findViewById(R.id.grid_vw_today_sale);
        grid_vw_yesterday_sale = (ExpandableHeightGridView) view.findViewById(R.id.grid_vw_yesterday_sale);
        recyclerview_horizontal = (RecyclerView) view.findViewById(R.id.recyclerview_horizontal);

        feedsList = new ArrayList<>();
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerview_horizontal.setLayoutManager(layoutManager);
        recyclerview_horizontal.setAdapter(new CustomHorizontal_Recycle(con, mobileArray));


//        recyclerView.setHasFixedSize(true);
//        StaggeredGridLayoutManager gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
//        gaggeredGridLayoutManager = constant.new MyStaggeredLayoutManager(2, 1);


    }


    @Override
    public void onResume()
    {
        super.onResume();

//        mAdapter.notifyDataSetChanged();

       /* gaggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        Sale_Grid_Adapter rcAdapter = new Sale_Grid_Adapter(con, feedsList);

        recyclerView.setAdapter(rcAdapter);*/
    }


}
