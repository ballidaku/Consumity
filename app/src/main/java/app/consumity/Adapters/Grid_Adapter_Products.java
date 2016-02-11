package app.consumity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.consumity.R;
import app.consumity.Utils.GlobalConstant;
import app.consumity.Utils.ImageViewSmoothScroll;

//import app.consumity.ProductFullDescription;
//import app.consumity.R;

/**
 * Created by ameba on 1/8/16.
 */
public class Grid_Adapter_Products extends BaseAdapter
{
    Context con;
    boolean isDelMode = false;

    GlobalConstant constant = new GlobalConstant();
    private LayoutInflater inflater;
    ArrayList<String> product_image_list = new ArrayList<>();

    public Grid_Adapter_Products(Context con, boolean isDelMode)
    {
        this.con = con;
        this.isDelMode = isDelMode;
        inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        product_image_list.add(R.mipmap.product_img1+"");
        product_image_list.add(R.mipmap.product_img2+"");
        product_image_list.add(R.mipmap.product_img3+"");
        product_image_list.add(R.mipmap.product_img4+"");
    }

    @Override
    public int getCount()
    {
        return 6;
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
            convertView = inflater.inflate(R.layout.custom_products_list_child, null);
            holder = new ViewHolder();
            holder.tv_first = (TextView) convertView.findViewById(R.id.tv_first);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holder.img_delete = (ImageView) convertView.findViewById(R.id.img_delete);
            holder.imgv_product = (ImageViewSmoothScroll) convertView.findViewById(R.id.imgv_product);
            holder.txt_vw_brand_name = (TextView) convertView.findViewById(R.id.txt_vw_brand_name);
            holder.txt_vw_online_store = (TextView) convertView.findViewById(R.id.txt_vw_online_store);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        constant.set_bold_font(con, convertView);

        if (isDelMode == false)
        {
            holder.img_delete.setVisibility(ViewGroup.GONE);
        }
        else
        {
            holder.img_delete.setVisibility(ViewGroup.VISIBLE);
        }

        holder.imgv_product.setImageResource(Integer.parseInt(product_image_list.get(position%4)));
        convertView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // con.startActivity(new Intent(con, ProductFullDescription.class));

            }
        });

        holder.txt_vw_brand_name.setSelected(true);
        holder.txt_vw_online_store.setSelected(true);

        return convertView;
    }

    public void show_del_icon(boolean isDelMode)
    {
        this.isDelMode = isDelMode;
    }

    class ViewHolder
    {
        ImageView img_delete ;

        ImageViewSmoothScroll imgv_product;
        TextView  tv_price, tv_first;
        TextView txt_vw_brand_name;
        TextView txt_vw_online_store;
    }
}
