package app.abhilasha.consumity.com.consumity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.abhilasha.consumity.com.consumity.CategoryActivity;
import app.abhilasha.consumity.com.consumity.R;
import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;

/**
 * Created by ameba on 5/1/16.
 */
public class CustomHorizontal_Recycle extends RecyclerView.Adapter<CustomHorizontal_Recycle.CustomViewHolder>
{
    ArrayList<String> mobileArray /*= {"Electronics", "Women's\nClothing", "Men's\nClothing",
            "Frozen", "Simpsons", "ipad", "Fitbit", "Keurig", "Galaxy\nS6", "Beats\nByDre", "Xbox", "Play\nStation", "Kids &\nfamily", "Home &\nGarden", "Movies", "Beauty", "Sports\nOutdoor",
            "Grocery"}*/;
    private Context con;
    GlobalConstant constant = new GlobalConstant();

    public CustomHorizontal_Recycle(Context con, ArrayList<String> mobileArray)
    {
        this.con = con;
        this.mobileArray = mobileArray;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_custom_layout, null);
        constant.set_bold_font(con, view);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, final int i)
    {
        //Setting text view title
        customViewHolder.textView.setText(mobileArray.get(i));
        customViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!(con instanceof CategoryActivity))
                {
                    Intent intent = new Intent(con, CategoryActivity.class);
                    intent.putExtra("title", mobileArray.get(i));
                    con.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mobileArray.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder
    {
        protected TextView textView;
        protected View view;

        public CustomViewHolder(View view)
        {
            super(view);
            this.view = view;
            this.textView = (TextView) view.findViewById(R.id.txt_vw_horizontal);
        }
    }
}
