package com.onedrinkaway.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.LinearLayout;

import com.onedrinkaway.R;
import com.onedrinkaway.app.ResultsPage;
import com.onedrinkaway.db.DrinkData;
import com.onedrinkaway.db.DrinkDb;
import com.onedrinkaway.model.DrinkModel;

public class ResultsPageTest extends
		ActivityInstrumentationTestCase2<ResultsPage> {
	private ResultsPage activity;

	public ResultsPageTest() {
		super(ResultsPage.class);
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
		DrinkModel.findTrySomethingNewDrinks();
		activity = getActivity();
	}
	
	public void testOnCreate(){
		assertNotNull(activity);
		LinearLayout view = (LinearLayout) activity.findViewById(R.id.results_container);
		assertNotNull(view);
	}
}
