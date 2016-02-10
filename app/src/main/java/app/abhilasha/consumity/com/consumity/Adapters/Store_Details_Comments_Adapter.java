package app.abhilasha.consumity.com.consumity.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import app.abhilasha.consumity.com.consumity.R;
import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;

/**
 * Created by ameba on 1/11/16.
 */
/*public class Store_Details_Comments_Adapter extends BaseAdapter
{
    Context con;
    GlobalConstant constant = new GlobalConstant();

    public Store_Details_Comments_Adapter(Context con)
    {
        this.con = con;
    }

    @Override
    public int getCount()
    {
        return 3;
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.store_details_comments_child, null);
        constant.set_bold_font(con, convertView);
        TextView txt_vw_username = (TextView) convertView.findViewById(R.id.txt_vw_username);
        SpannableString content = new SpannableString("@abhishekgarg39:");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        txt_vw_username.setText(content);

        return convertView;
    }
}*/

public class Store_Details_Comments_Adapter extends RecyclerView.Adapter<Store_Details_Comments_Adapter.CustomViewHolder>
{
    Context con;
    GlobalConstant constant = new GlobalConstant();

    public Store_Details_Comments_Adapter(Context con)
    {
        this.con = con;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_details_comments_child, parent, false);
        constant.set_bold_font(con, v);
        final CustomViewHolder viewholder = new CustomViewHolder(v);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int position)
    {

        SpannableString content = new SpannableString("@abhishekgarg39:");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        customViewHolder.txt_vw_username.setText(content);
        customViewHolder.txt_vw_comment.setText("Lorem ipsum is simply a dummy text. Lorem ipsum is simply a dummy text. Lorem ipsum is simply a dummy text.");
    }

    @Override
    public int getItemCount()
    {
        return 2;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder
    {
        protected TextView txt_vw_username;
        protected TextView txt_vw_comment;

        public CustomViewHolder(View view)
        {
            super(view);
            this.txt_vw_username = (TextView) view.findViewById(R.id.txt_vw_username);
            this.txt_vw_comment = (TextView) view.findViewById(R.id.txt_vw_comment);

        }
    }
}
