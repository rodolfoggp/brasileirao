package com.example.testing_library.instrumented

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

fun onView(@IdRes id: Int): ViewInteraction = onView(withId(id))

fun checkViewIsDisplayed(@IdRes id: Int): ViewInteraction =
    onView(withId(id)).check(matches(isDisplayed()))

fun checkViewContainsText(@IdRes id: Int, text: String): ViewInteraction =
    onView(withId(id)).check(matches(withText(text)))

fun swipeDownOn(@IdRes id: Int): ViewInteraction =
    onView(withId(id)).perform(swipeDown())

fun dialogIsDisplayedWithText(text: String): ViewInteraction =
    onView(withText(text)).check(matches(isDisplayed()))

fun <T> ViewInteraction.checkItContains(items: List<T>, itemMatcher: (T) -> Matcher<View?>) {
    check(
        matches(
            allOf(
                items.mapIndexed { position, item ->
                    atPosition(position, itemMatcher(item))
                }
            )
        )
    )
}