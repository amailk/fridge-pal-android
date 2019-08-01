package cp317.wlu.ca.fridgepal.fridge;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cp317.wlu.ca.fridgepal.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class AddFoodFragmentTest {

    @Rule
    public ActivityTestRule<AddFoodActivity> activityTestRule = new ActivityTestRule<>(AddFoodActivity.class);

    @Before
    public void setup() {
        activityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void test_checkAddButtonVisibleWithProperText() {
        onView(withId(R.id.added_food_add_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.added_food_add_button)).check(matches(withText("Add To Fridge")));
    }

    @Test
    public void test_checkTitleVisibleWithProperText() {
        onView(withId(R.id.textView4)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.textView4)).check(matches(withText("Add To Fridge")));
    }


    @Test
    public void test_checkExpiryDateButtonVisibleWithProperText() {
        onView(withId(R.id.date_picker_open)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.date_picker_open)).check(matches(withText("Expiry Date")));
    }

}