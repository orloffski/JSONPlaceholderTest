package by.development.madcat.jsonplaceholdertest.JsonPlaceholderApi;

import java.util.List;

import by.development.madcat.jsonplaceholdertest.models.Comment;
import by.development.madcat.jsonplaceholdertest.models.Photo;
import by.development.madcat.jsonplaceholdertest.models.Post;
import by.development.madcat.jsonplaceholdertest.models.Todo;
import by.development.madcat.jsonplaceholdertest.models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceholderApi {

    @GET("/posts/{id}")
    Call<Post> getPostById(@Path("id") String id);

    @GET("/comments/{id}")
    Call<Comment> getCommentById(@Path("id") String id);

    @GET("/photos/{id}")
    Call<Photo> getPhotoById(@Path("id") String id);

    @GET("/todos/{id}")
    Call<Todo> getTodoById(@Path("id") String id);

    @GET("/users")
    Call<List<User>> getUsersByIds(
            @Query("id") String id1,
            @Query("id") String id2,
            @Query("id") String id3,
            @Query("id") String id4,
            @Query("id") String id5);
}
