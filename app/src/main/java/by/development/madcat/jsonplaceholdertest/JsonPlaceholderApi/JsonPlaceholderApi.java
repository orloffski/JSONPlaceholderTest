package by.development.madcat.jsonplaceholdertest.JsonPlaceholderApi;

import by.development.madcat.jsonplaceholdertest.models.Comment;
import by.development.madcat.jsonplaceholdertest.models.Photo;
import by.development.madcat.jsonplaceholdertest.models.Post;
import by.development.madcat.jsonplaceholdertest.models.Todo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceholderApi {

    @GET("/posts/{id}")
    Call<Post> getPostById(@Path("id") String id);

    @GET("/comments/{id}")
    Call<Comment> getCommentById(@Path("id") String id);

    @GET("/photos/{id}")
    Call<Photo> getPhotoById(@Path("id") String id);

    @GET("/todos/{id}")
    Call<Todo> getTodoById(@Path("id") String id);
}