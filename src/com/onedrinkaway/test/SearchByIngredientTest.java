package com.onedrinkaway.test;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;
import android.widget.SearchView;

import com.onedrinkaway.R;
import com.onedrinkaway.app.ResultsPage;
import com.onedrinkaway.app.SearchByIngredient;

public class SearchByIngredientTest extends
				ActivityInstrumentationTestCase2<SearchByIngredient> {
	private SearchByIngredient activity;
	
	public SearchByIngredientTest(){
		super(SearchByIngredient.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
	}
	
	public void testOnCreate(){
		assertNotNull(activity);
		SearchView view = (SearchView) activity.findViewById(R.id.ingredient_search_view);
		assertNotNull(view);
		assertTrue(view.getQueryHint().equals("Enter Ingredient Here"));
		assertFalse(view.isIconfiedByDefault());
	}
	
	public void testGoToResultsView(){
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
