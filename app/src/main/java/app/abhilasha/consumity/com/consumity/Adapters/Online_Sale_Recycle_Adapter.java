package app.abhilasha.consumity.com.consumity.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.abhilasha.consumity.com.consumity.R;
import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;

/**
 * Created by ameba on 1/11/16.
 */
public class Online_Sale_Recycle_Adapter extends RecyclerView.Adapter<Online_Sale_Recycle_Adapter.CustomViewHolder>
{
    Context con;
    GlobalConstant constant = new GlobalConstant();

    public Online_Sale_Recycle_Adapter(Context con)
    {
        this.con = con;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_online_sale, parent, false);
        constant.set_bold_font(con, v);
        final CustomViewHolder viewholder = new CustomViewHolder(v);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int position)
    {

        SpannableString content = new SpannableString("Amazon");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        customViewHolder.textView.setText(content);
    }

    @Override
    public int getItemCount()
    {
        return 2;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder
    {
        protected TextView textView;

        public CustomViewHolder(View view)
        {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.txt_vw_store_name);

        }
    }
}
