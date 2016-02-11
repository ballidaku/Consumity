package app.consumity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.consumity.ListsClickProducts;
import app.consumity.R;
import app.consumity.Utils.GlobalConstant;
import app.consumity.Utils.ImageViewSmoothScroll;

/**
 * Created by vanshika on 12/1/16.
 */
public class Grid_Adapter_Lists extends BaseAdapter
{

    Context con;
    GlobalConstant constant = new GlobalConstant();
    private LayoutInflater inflater;
    ArrayList<String> list_image = new ArrayList<>();
    ArrayList<String> list_txt = new ArrayList<>();

    public Grid_Adapter_Lists(Context con)
    {
        this.con = con;
        inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        list_image.add(R.mipmap.list1+"");
        list_image.add(R.mipmap.list2+"");
        list_image.add(R.mipmap.list3+"");

        list_txt.add("Samsung");
        list_txt.add("Puma");
        list_txt.add("Jacket");
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
            convertView = inflater.inflate(R.layout.lists_items_layout, null);

            holder = new ViewHolder();
            holder.img_lists_item = (ImageViewSmoothScroll) convertView.findViewById(R.id.img_lists_item);
            holder.tv_lists_item_name = (TextView) convertView.findViewById(R.id.tv_lists_item_name);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        constant.set_bold_font(con, convertView);
        holder.img_lists_item.setImageResource(Integer.parseInt(list_image.get(position%3)));
        holder.tv_lists_item_name.setText(list_txt.get(position%3));
        convertView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(con, ListsClickProducts.class);
                intent.putExtra("list_name", "List");
                con.startActivity(intent);
            }
        });


        return convertView;
    }

    class ViewHolder
    {
        ImageViewSmoothScroll img_lists_item;
        TextView              tv_lists_item_name;
    }
}
