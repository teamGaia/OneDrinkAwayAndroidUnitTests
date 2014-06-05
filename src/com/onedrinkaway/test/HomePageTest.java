package com.onedrinkaway.test;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;

import com.onedrinkaway.R;
import com.onedrinkaway.app.AdvancedSearch;
import com.onedrinkaway.app.FavoriteDrinks;
import com.onedrinkaway.app.HomePage;
import com.onedrinkaway.app.ResultsPage;
import com.onedrinkaway.app.SearchByCategory;
import com.onedrinkaway.app.SearchByFlavor;
import com.onedrinkaway.app.SearchByIngredient;
import com.onedrinkaway.app.SearchByName;

public class HomePageTest extends ActivityInstrumentationTestCase2<HomePage> {
	private HomePage activity;
	
	public HomePageTest(){
		super(HomePage.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
	}
	
	public void testOnCreate(){
		assertNotNull(activity);
		//assertTrue(activity.getSupportActionBar().getTitle().equals(R.string.app_name));
	}
	
	public void testTransitionToSearchByName(){
		testTransitionToPage(SearchByName.class, R.id.search_by_name);
	}
	
	public void testTransitionToSearchByCategory(){
		testTransitionToPage(SearchByCategory.class, R.id.search_by_category);
	}
	
	public void testTransitionToSearchByFlavor(){
		testTransitionToPage(SearchByFlavor.class, R.id.search_by_flavor);
	}
	
	public void testTransitionToSearchByIngredient(){
		testTransitionToPage(SearchByIngredient.class, R.id.search_by_ingredient);
	}
	
	public void testTransitionToFavoriteDrinks(){
		testTransitionToPage(FavoriteDrinks.class, R.id.favorites);
	}
	
	public void testTransitionToAdvancedSearch(){
		testTransitionToPage(AdvancedSearch.class, R.id.advanced_search);
	}
	
	public void testTransistionToTrySomethingNew(){
		testTransitionToPage(ResultsPage.class, R.id.try_something_new);
	}
	
	
	private void testTransitionToPage(Class nextPage, int id){
		ActivityMonitor monitor = getInstrumentation().addMonitor(nextPage.getName(), null, false);
		activity.goToActivity(activity.findViewById(id));
		getInstrumentation().waitForIdleSync();
		Activity next = getInstrumentation().waitForMonitor(monitor);
		assertNotNull(next);
		next.finish();
	}
}
