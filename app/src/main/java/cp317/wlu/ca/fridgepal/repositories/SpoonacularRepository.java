package cp317.wlu.ca.fridgepal.repositories;


import android.util.Log;

import com.google.android.gms.common.util.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import cp317.wlu.ca.fridgepal.model.IngredientRecipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public class SpoonacularRepository {

    private static final String TAG = SpoonacularApi.class.getSimpleName();
    private static final String BASE_URL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/";
    private final SpoonacularApi spoonacularApi;

    public SpoonacularRepository() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        spoonacularApi = retrofit.create(SpoonacularApi.class);
    }

    public void fetchRecipesByIngredient(List<String> ingredients, Consumer<List<IngredientRecipe>> result) {
        Call<List<IngredientRecipe>> recipesCall = spoonacularApi.getRecipesByIngredient(String.join(",", ingredients));
        recipesCall.enqueue(new Callback<List<IngredientRecipe>>() {
            @Override
            public void onResponse(Call<List<IngredientRecipe>> call, Response<List<IngredientRecipe>> response) {
                Log.d(TAG, "Response received: " + response.body().size());

                for (IngredientRecipe ingredientRecipe : response.body()) {
                    Log.d(TAG, ingredientRecipe.getId() + " " + ingredientRecipe.getTitle());
                }

                result.accept(response.body());
            }

            @Override
            public void onFailure(Call<List<IngredientRecipe>> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());
                result.accept(Collections.emptyList());
            }
        });
    }


    interface SpoonacularApi {
        @GET("recipes/findByIngredients?number=5")
        @Headers("X-RapidAPI-Key: bb3f801f97mshb27f34a96f064c1p11f15ajsn1931856cc647")
        Call<List<IngredientRecipe>> getRecipesByIngredient(@Query("ingredients") String ingredients);
    }
}
