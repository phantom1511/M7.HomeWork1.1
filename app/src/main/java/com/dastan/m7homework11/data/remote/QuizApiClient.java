package com.dastan.m7homework11.data.remote;

import com.dastan.m7homework11.core.CoreCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiClient implements IQuizApiClient {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    QuizApi client = retrofit.create(QuizApi.class);

    @Override
    public void getQuestions(int amount, Integer category, String difficulty, final QuestionsCallback callback) {
        Call<QuizQuestionsResponse> call = client.getQuestions(
                amount,
                category,
                difficulty
        );
        call.enqueue(new Callback<QuizQuestionsResponse>() {
            @Override
            public void onResponse(Call<QuizQuestionsResponse> call, Response<QuizQuestionsResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callback.onSuccess(response.body().getResults());
                    } else {
                        callback.onFailure(new Exception("Response body is empty: " + response.code()));
                    }
                } else {
                    callback.onFailure(new Exception("Request failed: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<QuizQuestionsResponse> call, Throwable t) {
                callback.onFailure(new Exception(t.getMessage()));
            }
        });
    }

    @Override
    public void getCategory(CategoryCallback categoryCallback) {
        Call<QuizCategoryResponse> call = client.getCategories();
        call.enqueue(new CoreCallback<QuizCategoryResponse>() {
            @Override
            public void onSuccess(QuizCategoryResponse result) {
                categoryCallback.onSuccess(result.getTriviaCategories());
            }

            @Override
            public void onFailure(Exception e) {
                categoryCallback.onFailure(e);
            }
        });
    }

    @Override
    public void getCount(Integer category, final CountCallback countCallback) {
        Call<QuizCountResponse> call = client.getQuestionsCount(category);
        call.enqueue(new CoreCallback<QuizCountResponse>() {
            @Override
            public void onSuccess(QuizCountResponse result) {
                countCallback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                countCallback.onFailure(e);
            }
        });
    }

    @Override
    public void getGlobal(GlobalCallback globalCallback) {
        Call<QuizGlobalResponse> call = client.getGlobal();
        call.enqueue(new CoreCallback<QuizGlobalResponse>() {
            @Override
            public void onSuccess(QuizGlobalResponse result) {
                globalCallback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                globalCallback.onFailure(e);
            }
        });
    }

    private interface QuizApi {
        @GET("api.php")
        Call<QuizQuestionsResponse> getQuestions(
                @Query("amount") int amount,
                @Query("category") Integer category,
                @Query("difficulty") String difficulty
        );

        @GET("api_category.php")
        Call<QuizCategoryResponse> getCategories(

        );

        @GET("api_count_global.php")
        Call<QuizGlobalResponse> getGlobal();

        @GET("api_count.php")
        Call<QuizCountResponse> getQuestionsCount(
                @Query("category") Integer category
        );
    }
}
