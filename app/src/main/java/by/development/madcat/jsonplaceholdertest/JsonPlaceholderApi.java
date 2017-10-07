package by.development.madcat.jsonplaceholdertest;

import by.development.madcat.jsonplaceholdertest.models.Comment;
import by.development.madcat.jsonplaceholdertest.models.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceholderApi {

    @GET("/posts/{id}")
    Call<Post> getPostById(@Path("id") String id);

    @GET("/comments/{id}")
    Call<Comment> getCommentById(@Path("id") String id);
}
