package app.consumity.Adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import app.consumity.R;
import app.consumity.Utils.GlobalConstant;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by ameba on 11/1/16.
 */
public class StickyAdapter_Search extends BaseAdapter implements StickyListHeadersAdapter
{

    private LayoutInflater inflater;
    Context context;
    boolean isDelMode;
    GlobalConstant constant = new GlobalConstant();

    public StickyAdapter_Search(Context context, boolean isDelMode)
    {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.isDelMode = isDelMode;
    }

    @Override
    public int getCount()
    {
        return 3;
    }

    @Override
    public Object getItem(int position)
    {
        return 3;
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
            convertView = inflater.inflate(R.layout.custom_search_list_horizontalscroll, parent, false);
            holder.recycle_horizontal = (RecyclerView) convertView.findViewById(R.id.recyclerview_search_horizontal);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        constant.set_bold_font(context, convertView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.recycle_horizontal.setLayoutManager(layoutManager);
        holder.recycle_horizontal.setAdapter(new CustomHorizontal_Search(context));
//        GlobalConstant.setListViewHeightBasedOnItems(holder.recycle_horizontal, 10);
        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent)
    {
        HeaderViewHolder holder;
        if (convertView == null)
        {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.sticky_header_search, parent, false);
            holder.img_delete = (ImageView) convertView.findViewById(R.id.img_delete);
            convertView.setTag(holder);
        }
        else
        {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        if (isDelMode)
        {
            holder.img_delete.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.img_delete.setVisibility(View.GONE);
        }
        constant.set_bold_font(context, convertView);
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
        ImageView img_delete;
    }

    class ViewHolder
    {
        TextView     text;
        RecyclerView recycle_horizontal;
    }

    public void show_del_icon(boolean isDelMode)
    {
        this.isDelMode = isDelMode;
    }
}