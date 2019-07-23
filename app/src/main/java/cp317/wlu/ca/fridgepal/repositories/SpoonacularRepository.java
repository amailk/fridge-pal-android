package cp317.wlu.ca.fridgepal.repositories;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import cp317.wlu.ca.fridgepal.model.Food;
import cp317.wlu.ca.fridgepal.model.Instruction;
import cp317.wlu.ca.fridgepal.model.NutritionInfo;
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

    public void fetchRecipesByIngredient(List<String> ingredients, Consumer<List<Recipe>> result) {
        Call<List<Recipe>> recipesCall = spoonacularApi.getRecipesByIngredient(String.join(",", ingredients));
        recipesCall.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                Log.d(TAG, "Response received: " + response.body().size());

                for (Recipe recipe : response.body()) {
                    Log.d(TAG, recipe.getId() + " " + recipe.getTitle());
                }

                result.accept(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.e(TAG, "onFailure" + t.getMessage());
                result.accept(Collections.emptyList());
            }
        });
    }

    public void fetchRecipeInformation(String recipeId, Consumer<Recipe> result) {
        Call<Recipe> recipeCall = spoonacularApi.getRecipeInformation(recipeId);
        recipeCall.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                Recipe recipe = response.body();
                Log.d(TAG, "Response received: " + recipe.getId() + " " + recipe.getTitle());

                for (Food food: recipe.getExtendedIngredients()) {
                    food.setImage("https://spoonacular.com/cdn/ingredients_100x100/" + food.getImage());
                    Log.d(TAG, food.getName());
                }

                if (!recipe.getAnalyzedInstructions().isEmpty()) {
                    for (Instruction.Step step : recipe.getAnalyzedInstructions().get(0).getSteps()) {
                        Log.d(TAG, step.getNumber() + " " + step.getStep());
                    }
                }

                result.accept(recipe);
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                Log.e(TAG, "onFailure" + t.getMessage());
                result.accept(null);
            }
        });
    }

    public void fetchRelatedRecipesForRecipe(String recipeId, Consumer<List<Recipe>> result) {
        Call<List<Recipe>> recipesCall = spoonacularApi.getRelatedRecipesForRecipe(recipeId);
        recipesCall.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                Log.d(TAG, "Response recieved: " + response.body().size());

                result.accept(
                        response.body()
                                .stream()
                                .map(
                                        r -> {
                                            r.setImage("https://spoonacular.com/recipeImages/" + r.getImage());
                                            return r;
                                        }
                                )
                                .distinct()
                                .collect(Collectors.toList())
                );
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.e(TAG, "onFailure" + t.getMessage());
                result.accept(Collections.emptyList());
            }
        });
    }

    public void fetchFoodInformation(String foodName, Consumer<Food> result) {
        Call<List<Food>> foodCall = spoonacularApi.getFoodInformation(foodName);
        foodCall.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                Log.d(TAG, "Response received: " + response.body().size());

                Food food = response.body().get(0);
                food.setImage("https://spoonacular.com/cdn/ingredients_100x100/" + food.getImage());
                Log.d(TAG, "Food: " + food.getImage());

                result.accept(food);
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Log.d(TAG, "onFailure " + t.getMessage());
            }
        });
    }

    public void fetchNutritionInfo(String foodName, Consumer<NutritionInfo> result) {
        Call<NutritionInfo> nutritionInfoCall = spoonacularApi.getNutriotionInfo(foodName);
        nutritionInfoCall.enqueue(new Callback<NutritionInfo>() {
            @Override
            public void onResponse(Call<NutritionInfo> call, Response<NutritionInfo> response) {
                Log.d(TAG, "Response received: " + response.body().getCalories().getValue());
                result.accept(response.body());
            }

            @Override
            public void onFailure(Call<NutritionInfo> call, Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage());
            }
        });
    }

    interface SpoonacularApi {
        @GET("recipes/findByIngredients?number=5")
        @Headers(API_KEY_HEADER)
        Call<List<Recipe>> getRecipesByIngredient(@Query("ingredients") String ingredients);

        @GET("recipes/{id}/information")
        @Headers(API_KEY_HEADER)
        Call<Recipe> getRecipeInformation(@Path("id") String recipeId);

        @GET("recipes/{id}/similar")
        @Headers(API_KEY_HEADER)
        Call<List<Recipe>> getRelatedRecipesForRecipe(@Path("id") String recipeId);

        @GET("food/ingredients/autocomplete?number=1")
        @Headers(API_KEY_HEADER)
        Call<List<Food>> getFoodInformation(@Query("query") String foodName);

        @GET("recipes/guessNutrition")
        @Headers(API_KEY_HEADER)
        Call<NutritionInfo> getNutriotionInfo(@Query("title") String foodName);
    }
}
