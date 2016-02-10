package app.abhilasha.consumity.com.consumity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;
import app.abhilasha.consumity.com.consumity.Fragments.AboutFragment;
import app.abhilasha.consumity.com.consumity.Fragments.Help_Feedback_Fragment;
import app.abhilasha.consumity.com.consumity.Fragments.Home_fragment;
import app.abhilasha.consumity.com.consumity.Fragments.ListsFragment;
import app.abhilasha.consumity.com.consumity.Fragments.ProductsFragment;
import app.abhilasha.consumity.com.consumity.Fragments.Searches_Fragment;
import app.abhilasha.consumity.com.consumity.Fragments.Settings_Fragment;
import app.abhilasha.consumity.com.consumity.Fragments.Terms_Fragment;
import app.abhilasha.consumity.com.consumity.Fragments.UserProfileFragment;
import app.abhilasha.consumity.com.consumity.navitems.FragmentDrawer;

/**
 Created by ameba on 22/12/15. */
public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener, View.OnClickListener
{

    private static String TAG = MainActivity.class.getSimpleName();
    Context con;
    private Toolbar        mToolbar;
    private FragmentDrawer drawerFragment;
    TextView  txt_vw_toolbar;
    ImageView img_vw_toolbar_sec_barcode, img_vw_toolbar_barcode, imgv_user_profile;
    GlobalConstant constant = new GlobalConstant();
    Fragment     fragment;
    DrawerLayout mDrawerLayout;
    TextView     txtv_user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        con = this;

        findIds();

        displayView(0);
    }

    void findIds()
    {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        constant.set_bold_font(con, findViewById(R.id.drawer_layout));

        txt_vw_toolbar = (TextView) mToolbar.findViewById(R.id.txt_vw_toolbar);
        img_vw_toolbar_sec_barcode = (ImageView) mToolbar.findViewById(R.id.img_vw_toolbar_sec_barcode);
        img_vw_toolbar_barcode = (ImageView) mToolbar.findViewById(R.id.img_vw_toolbar_barcode);

        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, mDrawerLayout, mToolbar);
        drawerFragment.setDrawerListener(this);

        findViewById(R.id.llay_nav_header_container).setOnClickListener(this);
        txtv_user_name = (TextView) mDrawerLayout.findViewById(R.id.txtv_user_name);
        imgv_user_profile = (ImageView) mDrawerLayout.findViewById(R.id.imgv_user_profile);

        if (constant.getFromPreference(con, GlobalConstant.KeyValues_Names.UserData.toString()).trim().isEmpty())
        {
            txtv_user_name.setText("Log In or Sign Up");
        }
        else
        {
            String name = constant.getParticularValueFromPreference(con, GlobalConstant.KeyValues_Names.UserData.toString(), GlobalConstant.KeyValues_Names.FirstName.toString());
            String photo_url = constant.getParticularValueFromPreference(con, GlobalConstant.KeyValues_Names.UserData.toString(), GlobalConstant.KeyValues_Names.PhotoPath.toString());

            txtv_user_name.setText(name);

            constant.setRoundImage(con, imgv_user_profile, photo_url);
        }
    }

    Fragment home_fragment, searches_fragment, products_fragment, lists_fragment;

    @Override
    public void onDrawerItemSelected(View view, int position)
    {
        displayView(position);

    }

    private void displayView(final int position)
    {

        FragmentManager           fragmentManager     = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        img_vw_toolbar_sec_barcode.setVisibility(View.GONE);

        switch (position)
        {

            case 0:
                if (home_fragment == null)
                {
                    home_fragment = new Home_fragment();
                }
                img_vw_toolbar_barcode.setVisibility(View.VISIBLE);
                img_vw_toolbar_barcode.setBackgroundResource(R.mipmap.barcode_icon);
                txt_vw_toolbar.setText("Home");
                fragmentTransaction.replace(R.id.container_body, home_fragment, "NewFragmentTag");
                fragmentTransaction.commit();
                break;

            case 1:
                if (searches_fragment == null)
                {
                    searches_fragment = new Searches_Fragment();
                }
                img_vw_toolbar_barcode.setVisibility(View.VISIBLE);
                img_vw_toolbar_barcode.setBackgroundResource(R.mipmap.edit_icon);
                txt_vw_toolbar.setText("Searches");
                fragmentTransaction.replace(R.id.container_body, searches_fragment, "NewFragmentTag");
                fragmentTransaction.commit();
                break;

            case 2:
                if (products_fragment == null)
                {
                    products_fragment = new ProductsFragment();
                }
                img_vw_toolbar_barcode.setVisibility(View.VISIBLE);
                img_vw_toolbar_barcode.setBackgroundResource(R.mipmap.barcode_icon);
                txt_vw_toolbar.setText("Products");
                fragmentTransaction.replace(R.id.container_body, products_fragment, "NewFragmentTag");
                fragmentTransaction.commit();
                break;

            case 3:
                if (lists_fragment == null)
                {
                    lists_fragment = new ListsFragment();
                }
                img_vw_toolbar_barcode.setVisibility(View.VISIBLE);
                img_vw_toolbar_barcode.setBackgroundResource(R.mipmap.add_icon);
                txt_vw_toolbar.setText("Lists");
                fragmentTransaction.replace(R.id.container_body, lists_fragment, "NewFragmentTag");
                fragmentTransaction.commit();
                break;

            case 4:
                fragment = new Settings_Fragment();
                img_vw_toolbar_barcode.setVisibility(View.INVISIBLE);
                txt_vw_toolbar.setText("Settings");
                fragmentTransaction.replace(R.id.container_body, fragment, "NewFragmentTag");
                fragmentTransaction.commit();
                break;

            case 5:
                fragment = new AboutFragment();
                img_vw_toolbar_barcode.setVisibility(View.INVISIBLE);
                txt_vw_toolbar.setText("About");
                fragmentTransaction.replace(R.id.container_body, fragment, "NewFragmentTag");
                fragmentTransaction.commit();
                break;

            case 6:
                fragment = new Help_Feedback_Fragment();
                img_vw_toolbar_barcode.setVisibility(View.INVISIBLE);
                txt_vw_toolbar.setText("Help & Feedback");
                fragmentTransaction.replace(R.id.container_body, fragment, "NewFragmentTag");
                fragmentTransaction.commit();
                break;

            case 7:
                fragment = new Terms_Fragment();
                img_vw_toolbar_barcode.setVisibility(View.INVISIBLE);
                txt_vw_toolbar.setText("Terms of use");
                fragmentTransaction.replace(R.id.container_body, fragment, "NewFragmentTag");
                fragmentTransaction.commit();
                break;

            default:
                break;
        }

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {

            case R.id.llay_nav_header_container:

                Log.e("Click", "header");
                if (constant.getFromPreference(con, GlobalConstant.KeyValues_Names.UserData.toString()).trim().isEmpty())
                {
                    Intent i = new Intent(con, Login_RegisterActivity.class);
                    i.putExtra("login", "register");
                    startActivity(i);
                }
                else if (!constant.getFromPreference(con, GlobalConstant.KeyValues_Names.UserData.toString()).trim().isEmpty())
                {
                    txt_vw_toolbar.setText("User Profile");
                    img_vw_toolbar_sec_barcode.setVisibility(View.GONE);
                    img_vw_toolbar_barcode.setVisibility(View.INVISIBLE);
                    fragment = new UserProfileFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment, "NewFragmentTag");
                    fragmentTransaction.commit();
                    mDrawerLayout.closeDrawers();
                }

                break;

            default:
                break;
        }

    }
}
