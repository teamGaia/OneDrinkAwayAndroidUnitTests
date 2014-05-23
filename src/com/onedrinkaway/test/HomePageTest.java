package com.onedrinkaway.test;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.onedrinkaway.app.*;
import com.onedrinkaway.R;

public class HomePageTest extends ActivityInstrumentationTestCase2<HomePage> {
	private HomePage activity;
	private View myView;
	
	public HomePageTest(){
		super(HomePage.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		myView = activity.findViewById(R.layout.activity_home_page);
		

	}
	
	public void testSimple(){
		ActivityMonitor monitor = getInstrumentation().addMonitor(SearchByName.class.getName(), null, false);
		assertTrue(activity.getSupportActionBar().getTitle().equals(R.string.app_name));
		activity.goToActivity(activity.findViewById(R.id.search_by_name));
		getInstrumentation().waitForIdleSync();
		SearchByName nextPage = (SearchByName) getInstrumentation().waitForMonitor(monitor);
		assertNotNull(nextPage);
	}
}
