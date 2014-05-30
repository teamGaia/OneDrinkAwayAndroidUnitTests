package com.onedrinkaway.test;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.onedrinkaway.R;
import com.onedrinkaway.app.ResultsPage;
import com.onedrinkaway.app.SearchByCategory;
import com.onedrinkaway.app.SearchByIngredient;
import com.onedrinkaway.db.DrinkData;
import com.onedrinkaway.db.DrinkDb;

public class SearchByCategoryTest extends
		ActivityInstrumentationTestCase2<SearchByCategory> {
	private SearchByCategory activity;

	public SearchByCategoryTest() {
		super(SearchByCategory.class);
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
		LinearLayout view = (LinearLayout) activity.findViewById(R.id.category_space);
		assertNotNull(view);
	}
	
	public void testCategorySelected(){
		ActivityMonitor monitor = getInstrumentation().addMonitor(ResultsPage.class.getName(), null, false);
		getInstrumentation().waitForIdleSync();
		final TextView view = (TextView) LayoutInflater.from(activity).inflate(R.layout.category_text_area, null);
		view.setText("Classic");
		try {
			runTestOnUiThread( new Runnable(){
				public void run(){
					activity.categorySelected(view);
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
