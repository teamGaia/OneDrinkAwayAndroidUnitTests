package com.onedrinkaway.test;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;
import android.widget.SearchView;

import com.onedrinkaway.R;
import com.onedrinkaway.app.ResultsPage;
import com.onedrinkaway.app.SearchByIngredient;
import com.onedrinkaway.db.DrinkData;
import com.onedrinkaway.db.DrinkDb;

public class SearchByIngredientTest extends
				ActivityInstrumentationTestCase2<SearchByIngredient> {
	private SearchByIngredient activity;
	
	public SearchByIngredientTest(){
		super(SearchByIngredient.class);
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
		ListView view = (ListView) activity.findViewById(R.id.ingredient_list_view);
		assertNotNull(view);
	}
	
	public void testGoToResultsView(){
		ActivityMonitor monitor = getInstrumentation().addMonitor(ResultsPage.class.getName(), null, false);
		getInstrumentation().waitForIdleSync();
		final ListView view = (ListView) activity.findViewById(R.id.ingredient_list_view);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertNotNull(view);
		try {
			runTestOnUiThread( new Runnable(){
				public void run(){
					view.performItemClick(view.getAdapter().getView(1, null, null), 1, view.getAdapter().getItemId(1));
					activity.goToResults(view);
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
