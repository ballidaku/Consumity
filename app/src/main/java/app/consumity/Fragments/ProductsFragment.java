package app.consumity.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;

import app.consumity.Adapters.Grid_Adapter_Products;
import app.consumity.R;
import app.consumity.Utils.GlobalConstant;

/**
 * Created by ameba on 7/1/16.
 */
public class ProductsFragment extends Fragment implements View.OnClickListener
{
    Context  con;
    GridView grid_vw_products;
    boolean isDelMode = false;
    ImageView             img_vw_toolbar_sec_barcode;
    Grid_Adapter_Products grid_adapter;
    GlobalConstant constant = new GlobalConstant();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_product_layout, null);
        con = getActivity();
        constant.set_bold_font(con, view);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        (toolbar.findViewById(R.id.img_vw_toolbar_barcode)).setOnClickListener(null);
        img_vw_toolbar_sec_barcode = (ImageView) toolbar.findViewById(R.id.img_vw_toolbar_sec_barcode);
        img_vw_toolbar_sec_barcode.setVisibility(View.VISIBLE);
        img_vw_toolbar_sec_barcode.setOnClickListener(this);


        toolbar.setOverflowIcon(getResources().getDrawable(R.mipmap.my_overflow_menu_ic));

        grid_vw_products = (GridView) view.findViewById(R.id.grid_vw_products);

                grid_vw_products.setFocusable(false);
                grid_adapter = new Grid_Adapter_Products(con, isDelMode);
                grid_vw_products.setAdapter(grid_adapter);


        return view;
    }



    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.img_vw_toolbar_sec_barcode:
                if (isDelMode == false)
                {
                    isDelMode = true;
                    grid_adapter.show_del_icon(isDelMode);
                    grid_adapter.notifyDataSetChanged();
                    img_vw_toolbar_sec_barcode.setImageResource(R.mipmap.done_icon);
                }
                else
                {
                    isDelMode = false;
                    grid_adapter.show_del_icon(isDelMode);
                    grid_adapter.notifyDataSetChanged();
                    img_vw_toolbar_sec_barcode.setImageResource(R.mipmap.edit_icon);
                }
                break;
        }

    }
}
