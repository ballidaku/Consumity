package app.abhilasha.consumity.com.consumity.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.abhilasha.consumity.com.consumity.ProductFullDescription;
import app.abhilasha.consumity.com.consumity.R;
import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;
import app.abhilasha.consumity.com.consumity.Utils.ImageViewSmoothScroll;
import app.abhilasha.consumity.com.consumity.ViewStoreDetails;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by ameba on 11/1/16.
 */
public class StickyAdapter extends BaseAdapter implements StickyListHeadersAdapter
{

    private LayoutInflater inflater;
    Context con;
    GlobalConstant    constant         = new GlobalConstant();
    ArrayList<String> sale_banner_list = new ArrayList<>();

    public StickyAdapter(Context con)
    {
        inflater = LayoutInflater.from(con);
        this.con = con;
        sale_banner_list.add(R.mipmap.sale_banner1 + "");
        sale_banner_list.add(R.mipmap.sale_banner2 + "");
        sale_banner_list.add(R.mipmap.sale_banner3 + "");
        sale_banner_list.add(R.mipmap.sale_banner4 + "");
    }

    @Override
    public int getCount()
    {
        return sale_banner_list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return 5;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;

        if (convertView == null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.sticky_adapter_view, parent, false);
            holder.grid_vw_latest_sale = (GridView) convertView.findViewById(R.id.grid_vw_latest_sale);
            holder.imgv_sale_banner = (ImageViewSmoothScroll) convertView.findViewById(R.id.imgv_sale_banner);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        constant.set_bold_font(con, convertView);


//    constant.setImageAccAspectRatio(con, holder.imgv_sale_banner, Integer.parseInt(sale_banner_list.get(position)));
        holder.imgv_sale_banner.setImageResource(Integer.parseInt(sale_banner_list.get(position)));

        holder.grid_vw_latest_sale.setAdapter(new Latest_Sale_Grid_Adapter(con));
        GlobalConstant.setListViewHeightBasedOnItems(holder.grid_vw_latest_sale, 4);
        holder.grid_vw_latest_sale.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(con, ProductFullDescription.class);
                intent.putExtra("position", position);
                con.startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent)
    {
        HeaderViewHolder holder;
        if (convertView == null)
        {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.sticky_header_layout, parent, false);
            holder.imgv_store_details = (ImageView) convertView.findViewById(R.id.imgv_store_details);
            convertView.setTag(holder);
        }
        else
        {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        constant.set_bold_font(con, convertView);
        holder.imgv_store_details.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                con.startActivity(new Intent(con, ViewStoreDetails.class));
            }
        });

        return convertView;
    }

    @Override
    public long getHeaderId(int position)
    {
        //return the first character of the country as ID because this is what headers are based upon
//        return countries[position].subSequence(0, 1).charAt(0);
        return position;
    }

    class HeaderViewHolder
    {
        //        TextView text;
        ImageView imgv_store_details;
    }

    class ViewHolder
    {
        TextView              text;
        GridView              grid_vw_latest_sale;
        ImageViewSmoothScroll imgv_sale_banner;
    }

}