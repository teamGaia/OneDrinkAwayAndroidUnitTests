package com.onedrinkaway.test;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.onedrinkaway.R;
import com.onedrinkaway.app.DrinkInfoPage;
import com.onedrinkaway.db.DrinkData;
import com.onedrinkaway.db.DrinkDb;

public class DrinkInfoPageTest extends
		ActivityInstrumentationTestCase2<DrinkInfoPage> {
	private DrinkInfoPage activity;

	public DrinkInfoPageTest() {
		super(DrinkInfoPage.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent i = new Intent();
		i.putExtra("drink", "Bellini");
		setActivityIntent(i);
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
		LinearLayout view = (LinearLayout) activity.findViewById(R.id.drink_info_seek_bars_layout);
		assertNotNull(view);
		LinearLayout seekBar = (LinearLayout) activity.findViewById(R.id.drink_info_seek_bars_layout);
		assertNotNull(seekBar);
		assertTrue(activity.getTitle().equals("Bellini"));
		TextView ingredients = (TextView) activity.findViewById(R.id.drink_info_ingredients);
		assertNotNull(ingredients);
		ImageView glassImage = (ImageView) activity.findViewById(R.id.drink_info_glass_type);
		assertNotNull(glassImage);
		TextView instructions = (TextView) activity.findViewById(R.id.drink_info_instructions);
		assertNotNull(instructions);
		RatingBar userRating = (RatingBar) activity.findViewById(R.id.drink_info_user_rating_bar);
		assertNotNull(userRating);
		RatingBar predictedRating = (RatingBar) activity.findViewById(R.id.drink_info_predicted_rating_bar);
		assertNotNull(predictedRating);
	}

}
