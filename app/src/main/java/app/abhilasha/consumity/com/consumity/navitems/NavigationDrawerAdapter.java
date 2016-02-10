package app.abhilasha.consumity.com.consumity.navitems;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import app.abhilasha.consumity.com.consumity.R;
import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;

/**
 Created by ameba on 9/12/15. */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder>
{
    List<NavDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context        context;
    GlobalConstant constant = new GlobalConstant();

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position)
    {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        constant.set_bold_font(context, view);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        NavDrawerItem current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.image.setBackgroundResource(current.getImage());
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView  title;
        ImageView image;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.txt_vw_drawer_title);
            image = (ImageView) itemView.findViewById(R.id.img_vw_drawer);
        }
    }
}
