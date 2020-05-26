package com.yahorb.mvvm.view.search

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.yahorb.mvvm.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SearchActivityTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<SearchActivity>()

    @Test
    fun signInTest() {
        onView(withId(R.id.search))
            .perform(
                ViewActions.typeText("STRING_TO_BE_TYPED"),
                ViewActions.closeSoftKeyboard()
            )

        //activityScenarioRule.
    }
}