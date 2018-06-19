package com.example.lukaszwachowski.bakingapp;

import android.graphics.drawable.ColorDrawable;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.lukaszwachowski.bakingapp.ui.main.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkVisibilityTest() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition(2, click()));
        onView(withId(R.id.steps_recycler_view)).perform(actionOnItemAtPosition(1, click()));
        onView(isRoot()).perform(pressBack());
    }

    @Test
    public void toolbarTest() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withText(R.string.app_name)).check(matches(withParent(withId(R.id.toolbar))));
        onView(withId(R.id.toolbar)).check(matches(withToolbarBackGroundColor()));
    }

    private Matcher<? super View> withToolbarBackGroundColor() {
        return new BoundedMatcher<View, View>(View.class) {
            @Override
            public boolean matchesSafely(View view) {
                final ColorDrawable color = (ColorDrawable) view.getBackground();

                return ContextCompat
                        .getColor(mActivityRule.getActivity(), R.color.colorPrimary) ==
                        color.getColor();
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}
