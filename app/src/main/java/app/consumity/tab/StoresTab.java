package app.consumity.tab;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;

import app.consumity.Adapters.Store_Expnd_List_Adapter;
import app.consumity.Getter_Setter.Expnd_List_Product_Item;
import app.consumity.R;
import app.consumity.Utils.GlobalConstant;

/**
 * Created by ameba on 22/12/15.
 */
public class StoresTab extends Fragment
{
    ArrayList<Expnd_List_Product_Item> product_list;
    ExpandableListView                 ExpLst_categories;
    Expnd_List_Product_Item            product_list_Item;
    Store_Expnd_List_Adapter           store_expnd_list_adapter;
    Context                            con;
    GlobalConstant constant = new GlobalConstant();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_stores_tab_layout, container, false);
        con = getActivity();
        constant.set_bold_font(con, view);
        findIds(view);
        Log.e("tab", "store");


        create_product_list();
        store_expnd_list_adapter = new Store_Expnd_List_Adapter(con, product_list);
        ExpLst_categories.setAdapter(store_expnd_list_adapter);
        ExpLst_categories.expandGroup(0);

        return view;
    }

    void findIds(View view)
    {
        ExpLst_categories = (ExpandableListView) view.findViewById(R.id.ExpLst_categories);
    }

    void create_product_list()
    {
        product_list = new ArrayList<>();

        ArrayMap<String, String> header = new ArrayMap<>();
        header.put("title", "Electronics");

        ArrayList<ArrayMap<String, String>> child = new ArrayList<>();

        ArrayMap<String, String> sub_child = new ArrayMap<>();
        sub_child.put("title", "Amazon");
        sub_child.put("image", R.mipmap.store_grid_view1 + "");
        child.add(sub_child);

        sub_child = new ArrayMap<>();
        sub_child.put("title", "Best Buy");
        sub_child.put("image", R.mipmap.store_grid_view2 + "");
        child.add(sub_child);

        sub_child = new ArrayMap<>();
        sub_child.put("title", "Brookstone");
        sub_child.put("image", R.mipmap.store_grid_view3 + "");
        child.add(sub_child);

        sub_child = new ArrayMap<>();
        sub_child.put("title", "Costco Wholesale");
        sub_child.put("image", R.mipmap.store_grid_view4 + "");
        child.add(sub_child);

        product_list_Item = new Expnd_List_Product_Item(header, child);
        product_list.add(product_list_Item);

        header = new ArrayMap<>();
        header.put("title", "Women's Clothing");
        product_list_Item = new Expnd_List_Product_Item(header, child);
        product_list.add(product_list_Item);

        header = new ArrayMap<>();
        header.put("title", "Men's Clothing");
        product_list_Item = new Expnd_List_Product_Item(header, child);
        product_list.add(product_list_Item);

        header = new ArrayMap<>();
        header.put("title", "Kids & Family");
        product_list_Item = new Expnd_List_Product_Item(header, child);
        product_list.add(product_list_Item);
    }
}
