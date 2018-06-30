package com.udacity.gradle.builditbigger;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.regex.Matcher;

@RunWith(AndroidJUnit4.class)
public class AsyncReturnTest {

    @Rule
    public final ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void getIdlingResource() {
        mIdlingResource = mainActivityTestRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @Test
    public void doTest(){
        // assert mainActivityTestRule.getActivity().getEndpointsReturn() != null;
        Espresso.onView(ViewMatchers.withId(R.id.joke_button)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.joke_text_view)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.withText(""))));
    }

    @After
    public void releaseIdle() {
        IdlingRegistry.getInstance().unregister(mIdlingResource);
    }
}
