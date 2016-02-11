package app.consumity.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.consumity.Adapters.StickyAdapter_Search;
import app.consumity.R;
import app.consumity.Utils.GlobalConstant;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by ameba on 7/1/16.
 */
public class Searches_Fragment extends Fragment implements View.OnClickListener
{
    ImageView img_toolbar_left, img_toolbar_right, img_vw_toolbar_barcode;
    TextView                  txt_toolbar;
    StickyListHeadersListView sticky_list_vw;
    Context                   con;
    GlobalConstant constant  = new GlobalConstant();
    Boolean        isDelMode = false;
    StickyAdapter_Search adapter_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        /**
         *Inflate fragment_home_layout and setup Views.
         */
        View view = inflater.inflate(R.layout.fragment_searches_layout, null);
        con = getActivity();
        constant.set_bold_font(con, view);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        img_vw_toolbar_barcode = (ImageView) toolbar.findViewById(R.id.img_vw_toolbar_barcode);
        img_vw_toolbar_barcode.setVisibility(View.VISIBLE);
        img_vw_toolbar_barcode.setOnClickListener(this);

        findByIds(view);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                adapter_search = new StickyAdapter_Search(getActivity(), isDelMode);
                sticky_list_vw.setAdapter(adapter_search);

            }
        }, 150);

        return view;
    }

    void findByIds(View view)
    {
        img_toolbar_left = (ImageView) view.findViewById(R.id.imgv_top_left);
        img_toolbar_right = (ImageView) view.findViewById(R.id.imgv_top_right);
        txt_toolbar = (TextView) view.findViewById(R.id.txtv_title);
        sticky_list_vw = (StickyListHeadersListView) view.findViewById(R.id.sticky_list_view_search);



//        txt_toolbar.setText("Searches");

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.img_vw_toolbar_barcode:
                if (isDelMode == false)
                {
                    isDelMode = true;
                    adapter_search.show_del_icon(isDelMode);
                    adapter_search.notifyDataSetChanged();
                    img_vw_toolbar_barcode.setBackgroundResource(R.mipmap.done_icon);
                }
                else
                {
                    isDelMode = false;
                    adapter_search.show_del_icon(isDelMode);
                    adapter_search.notifyDataSetChanged();
                    img_vw_toolbar_barcode.setBackgroundResource(R.mipmap.edit_icon);
                }
                break;
        }
    }
}
