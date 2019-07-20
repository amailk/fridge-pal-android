package cp317.wlu.ca.fridgepal.repositories;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import cp317.wlu.ca.fridgepal.model.IngredientRecipe;
import cp317.wlu.ca.fridgepal.model.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class SpoonacularRepository {

    private static final String TAG = SpoonacularApi.class.getSimpleName();
    private static final String BASE_URL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/";
    private static final String API_KEY_HEADER = "X-RapidAPI-Key: bb3f801f97mshb27f34a96f064c1p11f15ajsn1931856cc647";
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
                Log.d(TAG, "onFailure" + t.getMessage() );
                result.accept(Collections.emptyList());
            }
        });
    }

    public void fetchRecipeInformation(String recipeId, Consumer<Recipe> result) {
        Call<Recipe> recipeCall = spoonacularApi.getRecipeInformation(recipeId);
        recipeCall.enqueue(new Callback<Recipe>() {

            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                Log.d(TAG, "Response received: " + response.body().getId() + " " + response.body().getTitle());
                result.accept(response.body());
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());
                result.accept(null);
            }
        });
    }


    interface SpoonacularApi {
        @GET("recipes/findByIngredients?number=5")
        @Headers(API_KEY_HEADER)
        Call<List<IngredientRecipe>> getRecipesByIngredient(@Query("ingredients") String ingredients);

        @GET("recipes/{id}/information")
        @Headers(API_KEY_HEADER)
        Call<Recipe> getRecipeInformation(@Path("id") String recipeId);
    }
}
