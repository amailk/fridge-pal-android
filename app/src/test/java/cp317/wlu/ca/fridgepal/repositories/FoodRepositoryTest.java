package cp317.wlu.ca.fridgepal.repositories;

import com.google.firebase.database.DatabaseReference;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import cp317.wlu.ca.fridgepal.model.Food;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FoodRepositoryTest {

    public static final String UUID = "UUID";
    private FoodRepository foodRepository;
    @Mock DatabaseReference databaseReference;
    @Mock Food food;


    @Before
    public void setUp() {
        foodRepository = new FoodRepository(databaseReference);
        when(food.getUuid()).thenReturn(UUID);
    }

    @Test
    public void addFood_whenNotAtCapacity_shouldPushDataToFirebase() {

        for (int i = 0; i < 99; i++) {
            foodRepository.fridge.get("Meat").addFood(food);
        }

        DatabaseReference pushReference = mock(DatabaseReference.class);
        when(databaseReference.push()).thenReturn(pushReference);

        foodRepository.addFood(food);

        verify(databaseReference).push();
        verify(pushReference).setValue(food);
    }

    @Test
    public void addFood_whenAtCapacity_shouldNotPushDataToFirebase() {
        for (int i = 0; i < 100; i++) {
            foodRepository.fridge.get("Meat").addFood(food);
        }

        foodRepository.addFood(food);
        verify(databaseReference, never()).push();
    }

    @Test
    public void removeFood_withFood_callsRemoveOnFirebase() {
        DatabaseReference foodReference = mock(DatabaseReference.class);
        when(databaseReference.child(UUID)).thenReturn(foodReference);

        foodRepository.removeFood(food);

        verify(foodReference).removeValue();
    }

    @Test
    public void addDataLoadedListener_whenAdded_addedToList() {
        foodRepository.addDataLoadedListener(mock(FoodRepository.DataLoadedListener.class));
        assertThat(foodRepository.dataLoadedListeners).hasSize(1);
    }

    @Test
    public void getFridgeSize_whenEmpty_returnsZero() {
        assertThat(foodRepository.getFridgeSize()).isEqualTo(0);
    }

    @Test
    public void getFridgeSize_whenTwentyEntries_returnsTwenty() {
        for (int i = 0; i < 20; i++) {
            foodRepository.fridge.get("Meat").addFood(food);
        }
        assertThat(foodRepository.getFridgeSize()).isEqualTo(20);
    }

    @Test
    public void getCategories_returnsDefaultCategories() {
        assertThat(foodRepository.getCategories()).hasSize(4);
    }
}