package com.onedrinkaway.test;

import android.test.ActivityInstrumentationTestCase2;
import com.onedrinkaway.app.*;
import com.onedrinkaway.R;

public class HomePageTest extends ActivityInstrumentationTestCase2<HomePage> {
	private HomePage activity;
	//private View myView;
	
	public HomePageTest(){
		super(HomePage.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		
		//myView = activity.findViewById(R.id.home);
	}
}
