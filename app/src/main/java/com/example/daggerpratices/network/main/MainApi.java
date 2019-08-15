package com.example.daggerpratices.network.main;

import com.example.daggerpratices.models.Post;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface MainApi {

    // /posts?userId=1/
    @GET("posts")
    Flowable<List<Post>> getPostsFromUser(
            @Query("userId") int id
    );
}
