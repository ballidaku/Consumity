package app.consumity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import app.consumity.Adapters.StickyAdapter;
import app.consumity.Utils.GlobalConstant;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class Latest_Sales extends AppCompatActivity implements View.OnClickListener
{

    Context                   con;
    ImageView                 imgv_top_left;
    StickyListHeadersListView stickyList;
    GlobalConstant constant = new GlobalConstant();
    FrameLayout myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest__sales);

        con = this;
        constant.set_bold_font(con, findViewById(R.id.lay_font));
        findByIds();
    }

    void findByIds()
    {
        myToolbar = (FrameLayout) findViewById(R.id.toolbar);
        myToolbar.findViewById(R.id.imgv_top_left).setOnClickListener(this);
        ((TextView) findViewById(R.id.txtv_title)).setText("Latest Sales");
        findViewById(R.id.lay_store_details).setOnClickListener(this);

        stickyList = (StickyListHeadersListView) findViewById(R.id.sticky_header_list_view);
        StickyAdapter adapter = new StickyAdapter(Latest_Sales.this);
        stickyList.setFastScrollEnabled(true);
        stickyList.setEmptyView(findViewById(R.id.txtv_empty));
        stickyList.setDrawingListUnderStickyHeader(true);
        stickyList.setAdapter(adapter);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.imgv_top_left:
                finish();
                break;

            case R.id.lay_store_details:
                startActivity(new Intent(con, ViewStoreDetails.class));
                break;

            default:
                break;
        }
    }
}
