package com.onedrinkaway.test;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.TableLayout;

import com.onedrinkaway.R;
import com.onedrinkaway.app.ResultsPage;
import com.onedrinkaway.app.SearchByFlavor;
import com.onedrinkaway.app.SearchByIngredient;
import com.onedrinkaway.db.DrinkData;
import com.onedrinkaway.db.DrinkDb;

public class SearchByFlavorTest extends
				ActivityInstrumentationTestCase2<SearchByFlavor> {
	private SearchByFlavor activity;
	
	public SearchByFlavorTest (){
		super(SearchByFlavor.class);
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
		
		TableLayout view = (TableLayout) activity.findViewById(R.id.flavors_scroll_view_table);
		assertNotNull(view);
		Button button = (Button) activity.findViewById(R.id.flavor_search_button);
		assertNotNull(button);
	}
	
	public void testGoToResultsView(){
		ActivityMonitor monitor = getInstrumentation().addMonitor(ResultsPage.class.getName(), null, false);
		getInstrumentation().waitForIdleSync();
		final SeekBar bar = (SeekBar)activity.findViewById(R.id.flavor_seek_bar);
		assertNotNull(bar);
		final Button button = (Button) activity.findViewById(R.id.flavor_search_button);
		assertNotNull(button);
		try {
			runTestOnUiThread( new Runnable(){
				public void run(){
					bar.incrementProgressBy(1);
					button.performClick();
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
