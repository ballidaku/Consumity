package app.consumity.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import app.consumity.Adapters.Grid_Adapter_Lists;
import app.consumity.R;
import app.consumity.Utils.GlobalConstant;

/**
 * Created by vanshika on 12/1/16.
 */
public class ListsFragment extends Fragment {

	Context  con;
	GridView grid_vw_lists;
	GlobalConstant constant = new GlobalConstant();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		con = getActivity();

		View view = inflater.inflate(R.layout.fragment_lists_layout, container, false);
		constant.set_bold_font(con, view);

		Toolbar();

		grid_vw_lists = (GridView) view.findViewById(R.id.grid_vw_lists);
		grid_vw_lists.setAdapter(new Grid_Adapter_Lists(con));
//		GlobalConstant.setListViewHeightBasedOnItems(grid_vw_lists, 0);

		return view;
	}

	void Toolbar()
	{
		Toolbar   toolbar                = (Toolbar) getActivity().findViewById(R.id.toolbar);
		ImageView img_vw_toolbar_barcode = (ImageView) toolbar.findViewById(R.id.img_vw_toolbar_barcode);
		img_vw_toolbar_barcode.setOnClickListener(null);
	}

}
