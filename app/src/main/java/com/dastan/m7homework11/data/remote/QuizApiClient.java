package com.dastan.m7homework11.data.remote;

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

    public void getQuestions(final QuestionsCallback callback) {
        Call<QuizQuestionsResponse> call = client.getQuestions(
                10,
                null,
                "hard"
        );
        call.enqueue(new Callback<QuizQuestionsResponse>() {
            @Override
            public void onResponse(Call<QuizQuestionsResponse> call, Response<QuizQuestionsResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
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

    private interface QuizApi {
        @GET("api.php")
        Call<QuizQuestionsResponse> getQuestions(
                @Query("amount") int amount,
                @Query("category") String category,
                @Query("difficulty") String difficulty
        );
    }
}
