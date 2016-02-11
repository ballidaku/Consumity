package app.consumity.Adapters;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import app.consumity.Getter_Setter.Expnd_List_Product_Item;
import app.consumity.R;
import app.consumity.Utils.ExpandableHeightGridView;
import app.consumity.Utils.GlobalConstant;

/**
 * Created by ameba on 1/6/16.
 */
public class Store_Expnd_List_Adapter extends BaseExpandableListAdapter
{
    ArrayList<Expnd_List_Product_Item> product_list;
    Context                            con;

    GlobalConstant constant = new GlobalConstant();
    private LayoutInflater inflater;

    public Store_Expnd_List_Adapter(Context con, ArrayList<Expnd_List_Product_Item> product_list)
    {
        this.con = con;
        this.product_list = product_list;
        inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount()
    {
        return product_list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return product_list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        return product_list.get(groupPosition).getChild();
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return 0;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent)
    {
        GroupViewHolder groupHolder = null;

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.store_expnd_header, null);
            groupHolder = new GroupViewHolder();
            groupHolder.txt_vw_header_name = (TextView) convertView.findViewById(R.id.txt_vw_header_name);
            convertView.setTag(groupHolder);
        }
        else
        {
            groupHolder = (GroupViewHolder) convertView.getTag();
        }


        constant.set_bold_font(con, convertView);
        Expnd_List_Product_Item expnd_list_product_item = (Expnd_List_Product_Item) getGroup(groupPosition);

        SpannableString content = new SpannableString(expnd_list_product_item.getHeader().get("title"));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        groupHolder.txt_vw_header_name.setText(content);

               return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        ChildViewHolder childHolder = null;

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.store_expnd_child, null);
            childHolder = new ChildViewHolder();
//            childHolder.grid_vw_store = (GridView) convertView.findViewById(R.id.grid_vw_store);
            childHolder.grid_vw_store = (ExpandableHeightGridView) convertView.findViewById(R.id.grid_vw_store);
            convertView.setTag(childHolder);
        }
        else
        {
            childHolder = (ChildViewHolder) convertView.getTag();
        }

        constant.set_bold_font(con, convertView);
        final ArrayList<ArrayMap<String, String>> list               = (ArrayList) getChild(groupPosition, childPosition);
        Store_Grid_Adapter                       store_grid_adapter = new Store_Grid_Adapter(con, list);

        childHolder.grid_vw_store.setExpanded(true);
        childHolder.grid_vw_store.setAdapter(store_grid_adapter);

//        GlobalConstant.setListViewHeightBasedOnItems(childHolder.grid_vw_store, list.size());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return false;
    }

    static class ChildViewHolder
    {
//        GridView                 grid_vw_store;
        ExpandableHeightGridView grid_vw_store;
    }

    static class GroupViewHolder
    {
        TextView txt_vw_header_name;
    }
}
