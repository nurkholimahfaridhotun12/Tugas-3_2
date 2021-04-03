package com.nur.shoppy;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.widget.TextView;

public class FragmentHome extends Fragment
{
	@Override   
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container, false);

		TextView tv = (TextView) rootView.findViewById(R.id.text_home);
		tv.setText("HOME");
		return rootView;
	}

}


