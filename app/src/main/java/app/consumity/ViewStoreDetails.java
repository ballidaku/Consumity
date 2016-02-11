package app.consumity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import app.consumity.Adapters.Online_Sale_Recycle_Adapter;
import app.consumity.Adapters.Store_Details_Comments_Adapter;
import app.consumity.Utils.GlobalConstant;

public class ViewStoreDetails extends AppCompatActivity implements View.OnClickListener
{
    Context                        con;
    ListView                       list_vw_store_comments;
    Store_Details_Comments_Adapter store_details_comments_adapter;
    GlobalConstant constant = new GlobalConstant();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_store_details);
        con = this;
        constant.set_bold_font(con, findViewById(R.id.lay_font));
        findByIds();
//        store_details_comments_adapter = new Store_Details_Comments_Adapter(con);
//        list_vw_store_comments.setAdapter(store_details_comments_adapter);
//        GlobalConstant.setListViewHeightBasedOnItems_List(list_vw_store_comments, 0);

//        ListView lv = (ListView) findViewById(R.id.recycler_vw_store_comments);

        RecyclerView recycler_vw_store_comments = (RecyclerView) findViewById(R.id.recycler_vw_store_comments);
        GlobalConstant.MyLinearLayoutManager layoutManager = constant.new MyLinearLayoutManager(con, LinearLayoutManager.VERTICAL, false);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_vw_store_comments.setLayoutManager(layoutManager);
        recycler_vw_store_comments.setHasFixedSize(true);
        Store_Details_Comments_Adapter store_details_comments_adapter = new Store_Details_Comments_Adapter(con);
        recycler_vw_store_comments.setAdapter(store_details_comments_adapter);

        recycler_vw_store_comments.setOnTouchListener(new View.OnTouchListener()
        {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }


    void findByIds()
    {
        findViewById(R.id.imgv_top_left).setOnClickListener(this);
        ((TextView) findViewById(R.id.txtv_title)).setText("Amazon");

        findViewById(R.id.lay_leave_comment).setOnClickListener(this);
//        list_vw_store_comments = (ListView) findViewById(R.id.list_vw_store_comments);
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.imgv_top_left:
                finish();
                break;

            case R.id.lay_leave_comment:
                startActivity(new Intent(con, LeaveComment.class));

                break;

            default:
                break;
        }
    }
}
