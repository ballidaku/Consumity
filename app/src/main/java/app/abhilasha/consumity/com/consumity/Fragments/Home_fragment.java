package app.abhilasha.consumity.com.consumity.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import app.abhilasha.consumity.com.consumity.R;
import app.abhilasha.consumity.com.consumity.Utils.GlobalConstant;
import app.abhilasha.consumity.com.consumity.tab.SalesTab;
import app.abhilasha.consumity.com.consumity.tab.StoresTab;

/**
 * Created by ameba on 22/12/15.
 */
public class Home_fragment extends Fragment
{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Context con;
    GlobalConstant constant = new GlobalConstant();
    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_home_layout, null);
        con = getActivity();
        constant.set_bold_font(con, view);
        Toolbar();

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);

//        dialog = ProgressDialog.show(con, "", "");
//        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        dialog.setContentView(R.layout.progress_dialog);
//        dialog.show();
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {

                setupViewPager(viewPager);
                tabLayout.setupWithViewPager(viewPager);
                viewPager.setOffscreenPageLimit(0);
//                dialog.dismiss();
            }
        }, 150);

        return view;
    }

    void Toolbar()
    {
        Toolbar   toolbar                = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView img_vw_toolbar_barcode = (ImageView) toolbar.findViewById(R.id.img_vw_toolbar_barcode);
        img_vw_toolbar_barcode.setOnClickListener(null);
    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new SalesTab(), "SALES");
        adapter.addFrag(new StoresTab(), "STORES");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter
    {
        private final List<Fragment> mFragmentList      = new ArrayList<>();
        private final List<String>   mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager)
        {
            super(manager);
        }

        @Override
        public Fragment getItem(int position)
        {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount()
        {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title)
        {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return mFragmentTitleList.get(position);
        }
    }


}