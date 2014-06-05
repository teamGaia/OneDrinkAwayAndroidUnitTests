package com.onedrinkaway.test;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import com.onedrinkaway.R;
import com.onedrinkaway.app.DrinkInfoPage;
import com.onedrinkaway.app.SearchByName;
import com.onedrinkaway.db.DrinkData;
import com.onedrinkaway.db.DrinkDb;

public class SearchByNameTest extends
		ActivityInstrumentationTestCase2<SearchByName> {
	private SearchByName activity;
	
	public SearchByNameTest(){
		super(SearchByName.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		try {
            DrinkData dd = DrinkData.getDrinkDataDB("test");
            DrinkDb.setDrinkData(dd);
        } catch (Exception e) {
            e.printStackTrace();
        }
		activity = getActivity();
	}
	
	public void testOnCreate(){
		assertNotNull(activity);
//		SearchView view = (SearchView) activity.findViewById(R.id.search_view);
//		assertTrue(view.getQueryHint().equals("Enter Name Here"));
//		assertFalse(view.isSubmitButtonEnabled());
//		assertFalse(view.isIconfiedByDefault());
//		assertNotNull(view);
	}
	
	public void testOnQueryTextChange(){
		ListView view = (ListView) activity.findViewById(R.id.list_view);
		assertNotNull(view);
		//activity.onQueryTextChange("hello");
		//assertTrue(view.getTextFilter().equals("hello"));
		//activity.onQueryTextChange("");
		//assertTrue(view.getTextFilter().equals(""));
	}
	
	public void testGoToDrinkInfoPage(){
		ActivityMonitor monitor = getInstrumentation().addMonitor(DrinkInfoPage.class.getName(), null, false);
		getInstrumentation().waitForIdleSync();
		final ListView view = (ListView) activity.findViewById(R.id.list_view);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertNotNull(view);
		try {
			runTestOnUiThread( new Runnable(){
				public void run(){
					view.performItemClick(view.getAdapter().getView(1, null, null), 1, view.getAdapter().getItemId(1));
				}
			});
		} catch (Throwable e) {
			e.printStackTrace();
		}
		Activity next = getInstrumentation().waitForMonitor(monitor);
		assertNotNull(next);
		next.finish();
	}
}
