package app.abhilasha.consumity.com.consumity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


import app.abhilasha.consumity.com.consumity.R;
import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;
import app.abhilasha.consumity.com.consumity.Utils.ImageViewSmoothScroll;

/**
 * Created by ameba on 1/8/16.
 */
public class Latest_Sale_Grid_Adapter extends BaseAdapter
{
    Context con;
    GlobalConstant constant = new GlobalConstant();
    private LayoutInflater inflater;
    ArrayList<String> related_product_list = new ArrayList<>();

    public Latest_Sale_Grid_Adapter(Context con)
    {
        this.con = con;
        inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        related_product_list.add(R.mipmap.related_product1+"");
        related_product_list.add(R.mipmap.related_product2+"");
        related_product_list.add(R.mipmap.related_product3+"");
        related_product_list.add(R.mipmap.related_product4+"");
    }

    @Override
    public int getCount()
    {
        return related_product_list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.latest_sale_grid_child, null);
            holder = new ViewHolder();
            holder.imgv_product = (ImageViewSmoothScroll) convertView.findViewById(R.id.imgv_product);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imgv_product.setImageResource(Integer.parseInt(related_product_list.get(position)));
        constant.set_bold_font(con, convertView);

        return convertView;
    }

    class ViewHolder
    {
        ImageViewSmoothScroll imgv_product;
    }
}