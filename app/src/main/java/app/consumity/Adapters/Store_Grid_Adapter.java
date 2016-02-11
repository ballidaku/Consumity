package app.consumity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.consumity.Latest_Sales;
import app.consumity.R;
import app.consumity.Utils.GlobalConstant;
import app.consumity.Utils.ImageViewSmoothScroll;

/**
 * Created by ameba on 1/6/16.
 */
public class Store_Grid_Adapter extends BaseAdapter
{
    Context                             con;
    ArrayList<ArrayMap<String, String>> list;
    GlobalConstant constant = new GlobalConstant();
    private LayoutInflater inflater;

    public Store_Grid_Adapter(Context con, ArrayList<ArrayMap<String, String>> list)
    {
        inflater = LayoutInflater.from(con);
        this.con = con;
        this.list = list;
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final ViewHolder holder;
        if (convertView == null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.store_grid_child, null);
            holder.img_vw_store_grid = (ImageViewSmoothScroll) convertView.findViewById(R.id.img_vw_store_grid);
            holder.txt_vw_store_grid = (TextView) convertView.findViewById(R.id.txt_vw_store_grid);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        constant.set_bold_font(con, convertView);


//        holder.img_vw_store_grid.setImageResource(R.mipmap.store_grid_view1);
        holder.img_vw_store_grid.setImageResource(Integer.parseInt(list.get(position).get("image")));

        /*// Getting the height of image view.
        ViewTreeObserver vto = holder.img_vw_store_grid.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
        {
            public boolean onPreDraw()
            {
                int finalHeight = holder.img_vw_store_grid.getMeasuredHeight();
                int finalWidth = holder.img_vw_store_grid.getMeasuredWidth();
                return true;
            }
        });
*/

        holder.txt_vw_store_grid.setText(list.get(position).get("title"));
        convertView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                con.startActivity(new Intent(con, Latest_Sales.class));
            }
        });
        return convertView;
    }

    class ViewHolder
    {
        ImageViewSmoothScroll img_vw_store_grid;
        TextView              txt_vw_store_grid;
    }

}


/*public class Store_Grid_Adapter extends RecyclerView.Adapter<Store_Grid_Adapter.CustomViewHolder>
{

    Context                            con;
    ArrayList<HashMap<String, String>> list;
//    GlobalConstant constant = new GlobalConstant();

    public Store_Grid_Adapter(Context con, ArrayList<HashMap<String, String>> list)
    {
        this.con = con;
        this.list = list;

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View             layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_grid_child, null);
        CustomViewHolder rcv        = new CustomViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position)
    {
        holder.txt_vw_store_grid.setText(list.get(position).get("title"));
//        holder.img_vw_store_grid.setImageResource(Integer.parseInt(list.get(position).get("image")));
        holder.img_vw_store_grid.setImageResource(R.mipmap.store_grid_view1);
    }

    @Override
    public int getItemCount()
    {

        return this.list.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView  txt_vw_store_grid;
        public ImageView img_vw_store_grid;

        public CustomViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            txt_vw_store_grid = (TextView) itemView.findViewById(R.id.txt_vw_store_grid);
            img_vw_store_grid = (ImageView) itemView.findViewById(R.id.img_vw_store_grid);
        }

        @Override
        public void onClick(View view)
        {
            Toast.makeText(view.getContext(), "Clicked Position = " + getPosition(), Toast.LENGTH_SHORT).show();
        }
    }

    StaggeredGridLayoutManager mLayoutManager;


}*/
