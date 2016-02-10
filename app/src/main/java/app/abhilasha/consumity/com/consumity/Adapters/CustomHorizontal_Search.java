package app.abhilasha.consumity.com.consumity.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import app.abhilasha.consumity.com.consumity.R;
import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;

/**
 * Created by ameba on 11/1/16.
 */
public class CustomHorizontal_Search extends RecyclerView.Adapter<CustomHorizontal_Search.CustomViewHolder>
{

    private Context mContext;
    GlobalConstant    constant      = new GlobalConstant();
    ArrayList<String> searches_list = new ArrayList<>();

    public CustomHorizontal_Search(Context context)
    {
        this.mContext = context;
        searches_list.add(R.mipmap.searches_list1+"");
        searches_list.add(R.mipmap.searches_list2+"");
        searches_list.add(R.mipmap.searches_list3+"");
        searches_list.add(R.mipmap.searches_list4+"");
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_horizontal_recycle_search_fragment, null);
        constant.set_bold_font(mContext, view);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i)
    {
        customViewHolder.img_vw_horizontal.setImageResource(Integer.parseInt(searches_list.get(i%4)));
    }

    @Override
    public int getItemCount()
    {
        return 6;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder
    {
        protected ImageView img_vw_horizontal;

        public CustomViewHolder(View view)
        {
            super(view);
            this.img_vw_horizontal = (ImageView) view.findViewById(R.id.img_vw_horizontal);
        }
    }
}