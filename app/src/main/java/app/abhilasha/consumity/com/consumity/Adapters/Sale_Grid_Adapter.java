package app.abhilasha.consumity.com.consumity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.abhilasha.consumity.com.consumity.Getter_Setter.Getter_setter_sale;
import app.abhilasha.consumity.com.consumity.Latest_Sales;
import app.abhilasha.consumity.com.consumity.R;
import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;
import app.abhilasha.consumity.com.consumity.Utils.ImageViewSmoothScroll;
import app.abhilasha.consumity.com.consumity.ViewStoreDetails;

/**
 * Created by ameba on 22/12/15.
 */
public class Sale_Grid_Adapter extends BaseAdapter
{
    private List<Getter_setter_sale> feedItemList;
    private Context                  con;
    GlobalConstant constant = new GlobalConstant();
    private LayoutInflater inflater;
    ArrayList<String> sale_banner_list = new ArrayList<>();

    public Sale_Grid_Adapter(Context con, List<Getter_setter_sale> feedItemList)
    {
        inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.feedItemList = feedItemList;
        this.con = con;
        sale_banner_list.add(R.mipmap.sale_banner1 + "");
        sale_banner_list.add(R.mipmap.sale_banner2 + "");
        sale_banner_list.add(R.mipmap.sale_banner3 + "");
        sale_banner_list.add(R.mipmap.sale_banner4 + "");
    }


    @Override
    public int getCount()
    {
        return feedItemList.size();
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
        View       view   = convertView;
        ViewHolder holder = null;

        if (view == null)
        {

//            Resources r = Resources.getSystem();
//            float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, r.getDisplayMetrics());
//            view = new View(mContext);
            view = inflater.inflate(R.layout.custom_sale_grid, null);
            holder = new ViewHolder();
            holder.txt_vw_brand_name = (TextView) view.findViewById(R.id.txt_vw_brand_name);
            holder.txt_vw_online_store = (TextView) view.findViewById(R.id.txt_vw_online_store);
            holder.imgv_sale_banner = (ImageViewSmoothScroll) view.findViewById(R.id.imgv_sale_banner);
//            RelativeLayout imageView = (RelativeLayout) grid.findViewById(R.id.rel_vw_grid_sale_bg);
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        holder.imgv_sale_banner.setImageResource(Integer.parseInt(sale_banner_list.get(position)));
        Getter_setter_sale feedItem = feedItemList.get(position);
        constant.set_bold_font(con, view);
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                con.startActivity(new Intent(con, Latest_Sales.class));
            }
        });
        holder.txt_vw_brand_name.setSelected(true);
        holder.txt_vw_online_store.setSelected(true);
        return view;

    }

    static class ViewHolder
    {
        TextView              txt_vw_brand_name;
        TextView              txt_vw_online_store;
        ImageViewSmoothScroll imgv_sale_banner;
    }

}


/*
public class Sale_Grid_Adapter extends RecyclerView.Adapter<Sale_Grid_Adapter.CustomViewHolder> implements View.OnClickListener
{

    private        List<Getter_setter_sale> feedItemList;
    private static Context                  con;
    GlobalConstant constant = new GlobalConstant();

    public Sale_Grid_Adapter(Context con, List<Getter_setter_sale> feedItemList)
    {
        this.feedItemList = feedItemList;
        this.con = con;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View             layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_sale_grid, null);
        CustomViewHolder rcv        = new CustomViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position)
    {
        constant.set_bold_font(con, holder.mainView);
        holder.mainView.setOnClickListener(this);
        holder.txt_vw_brand_name.setSelected(true);
        holder.txt_vw_online_store.setSelected(true);
    }

    @Override
    public int getItemCount()
    {
        return 10;
    }

    @Override
    public void onClick(View v)
    {
        con.startActivity(new Intent(con, Latest_Sales.class));
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder
    {

        protected TextView     txt_vw_brand_name;
        protected TextView     txt_vw_online_store;
        protected View         mainView;

        public CustomViewHolder(View view)
        {
            super(view);
            mainView = view;

            this.txt_vw_brand_name = (TextView) view.findViewById(R.id.txt_vw_brand_name);
            this.txt_vw_online_store = (TextView) view.findViewById(R.id.txt_vw_online_store);
        }


    }

}*/
