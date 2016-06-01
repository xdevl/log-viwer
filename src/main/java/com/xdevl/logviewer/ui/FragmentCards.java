/*
 * Copyright (C) 2016 XdevL
 *
 * This file is part of Log viewer.

 * Log viewer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Log viewer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Log viewer. If not, see <http://www.gnu.org/licenses/>.
 */
package com.xdevl.logviewer.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xdevl.logviewer.R;
import com.xdevl.logviewer.bean.Card;

import java.util.Arrays;

public class FragmentCards extends Fragment implements AdapterCard.OnCardSelectedListener
{
	public static final int ID_LOGCAT=0 ;
	public static final int ID_DMESG=1 ;
	public static final int ID_ABOUT=2 ;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState)
	{
		View view=inflater.inflate(R.layout.fragment_cards,container,false) ;
		((Activity)getActivity()).setTitle(getString(R.string.app_name),false) ;
		RecyclerView recyclerView=findViewById(view,R.id.cards) ;
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())) ;
		recyclerView.setAdapter(new AdapterCard(getActivity(),this,Arrays.asList(
				new Card(ID_LOGCAT,R.drawable.ic_android,R.string.title_logcat,R.string.desc_logcat),
				new Card(ID_DMESG,R.drawable.ic_linux,R.string.title_dmesg,R.string.desc_dmesg),
				new Card(ID_ABOUT,R.drawable.ic_about,R.string.title_about,R.string.desc_about)
		))) ;

		return view ;
	}

	protected <T> T findViewById(View view, int id)
	{
		return (T)view.findViewById(id) ;
	}

	@Override
	public void onCardSelected(Card card)
	{
		if(card.mId==ID_LOGCAT)
			((Activity)getActivity()).displayFragment(FragmentLogs.createFragment(FragmentLogs.ADB_LOGS)) ;
		else if(card.mId==ID_DMESG)
			((Activity)getActivity()).displayFragment(FragmentLogs.createFragment(FragmentLogs.KERNEL_LOGS)) ;
		else if(card.mId==ID_ABOUT)
			((Activity)getActivity()).displayFragment(new FragmentAbout()) ;
	}
}