package com.onedrinkaway.test;

import com.onedrinkaway.app.ResultsPage;
import com.onedrinkaway.app.SearchByName;
import com.onedrinkaway.R;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

public class SearchByNameTest extends
		ActivityInstrumentationTestCase2<SearchByName> {
	private SearchByName activity;
	
	public SearchByNameTest(){
		super(SearchByName.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
	}
	
	public void testOnCreate(){
		assertNotNull(activity);
	}
	
	public void testGoToDrinkInfoPage(){
		ActivityMonitor monitor = getInstrumentation().addMonitor(ResultsPage.class.getName(), null, false);
		getInstrumentation().waitForIdleSync();
		ListView view = (ListView) activity.findViewById(R.id.list_view);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertNotNull(view);
		view.performItemClick(view, 2, R.id.list_item);
		Activity next = getInstrumentation().waitForMonitor(monitor);
		assertNotNull(next);
		next.finish();
	}
}
