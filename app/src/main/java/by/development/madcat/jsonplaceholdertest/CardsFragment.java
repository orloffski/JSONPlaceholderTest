package by.development.madcat.jsonplaceholdertest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import by.development.madcat.jsonplaceholdertest.models.Comment;
import by.development.madcat.jsonplaceholdertest.models.Photo;
import by.development.madcat.jsonplaceholdertest.models.Post;
import by.development.madcat.jsonplaceholdertest.models.Todo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardsFragment extends Fragment implements View.OnClickListener{

    private static JsonPlaceholderApi jsonPlaceholderApi;

    @BindView(R.id.post_title) TextView postTitle;
    @BindView(R.id.post_body) TextView postBody;
    @BindView(R.id.post_user_id) TextView postUserId;
    @BindView(R.id.post_id_input) EditText postIdInput;

    @BindView(R.id.comment_name) TextView commentName;
    @BindView(R.id.comment_body) TextView commentBody;
    @BindView(R.id.comment_email) TextView commentEmail;
    @BindView(R.id.comment_post_id) TextView commentPostId;
    @BindView(R.id.comment_id_input) EditText commentIdInput;

    @BindView(R.id.photo_image) ImageView photoImage;
    @BindView(R.id.photo_title) TextView photoTitle;
    @BindView(R.id.photo_album_id) TextView photoAlbumId;
    @BindView(R.id.photo_id_input) EditText photoIdInput;

    @BindView(R.id.todo_title) TextView todoTitle;
    @BindView(R.id.todo_user_id) TextView todoUserId;
    @BindView(R.id.todo_completed) TextView todoCompleted;
    @BindView(R.id.todo_id_input) EditText todoIdInput;

    Unbinder unbinder;

    public CardsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cards, container, false);

        unbinder = ButterKnife.bind(this, view);

        init();

        loadTodo(true);

        return view;
    }

    private void init() {
        postIdInput.setFilters(new InputFilter[]{new InputFilterMinMax(1, 100)});
        commentIdInput.setFilters(new InputFilter[]{new InputFilterMinMax(1, 500)});
        photoIdInput.setFilters(new InputFilter[]{new InputFilterMinMax(1, 5000)});
        todoIdInput.setFilters(new InputFilter[]{new InputFilterMinMax(1, 200)});

        jsonPlaceholderApi = JsonPlaceholderApiController.getApi();
    }

    @OnClick({R.id.get_post_button, R.id.get_comment_button, R.id.get_photo_button, R.id.get_todo_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_post_button:
                loadPost();
                break;
            case R.id.get_comment_button:
                loadComment();
                break;
            case R.id.get_photo_button:
                loadPhoto();
                break;
            case R.id.get_todo_button:
                loadTodo(false);
                break;
        }
    }

    private void loadPost() {
        if (postIdInput.getText().toString().length() == 0)
            return;

        Integer number = Integer.parseInt(postIdInput.getText().toString());
        jsonPlaceholderApi.getPostById(String.valueOf(number)).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                postTitle.setText(response.body().getTitle());
                postBody.setText(response.body().getBody());
                postUserId.setText(String.valueOf(response.body().getUserId()));
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    private void loadComment() {
        if (commentIdInput.getText().toString().length() == 0)
            return;

        Integer number = Integer.parseInt(commentIdInput.getText().toString());
        jsonPlaceholderApi.getCommentById(String.valueOf(number)).enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                commentName.setText(response.body().getName());
                commentBody.setText(response.body().getBody());
                commentEmail.setText(response.body().getEmail());
                commentPostId.setText(String.valueOf(response.body().getPostId()));
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {

            }
        });
    }

    private void loadPhoto() {
        if (photoIdInput.getText().toString().length() == 0)
            return;

        Integer number = Integer.parseInt(photoIdInput.getText().toString());
        jsonPlaceholderApi.getPhotoById(String.valueOf(number)).enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                Picasso.with(getContext()).load(response.body().getUrl()).into(photoImage);
                photoTitle.setText(response.body().getTitle());
                photoAlbumId.setText(String.valueOf(response.body().getAlbumId()));
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {

            }
        });
    }

    private void loadTodo(final boolean random){
        if (todoIdInput.getText().toString().length() == 0 && !random)
            return;

        Integer number;

        if(!random)
            number = Integer.parseInt(todoIdInput.getText().toString());
        else
            number = new Random().nextInt(200) + 1;

        jsonPlaceholderApi.getTodoById(String.valueOf(number)).enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                todoTitle.setText(response.body().getTitle());
                todoUserId.setText(String.valueOf(response.body().getUserId()));
                if(isAdded())
                    todoCompleted.setText(
                            response.body().getCompleted()
                                    ?
                                    getActivity().getResources().getString(R.string.todo_completed_text)
                                    :
                                    getActivity().getResources().getString(R.string.todo_not_completed_text)
                    );

                if(random)
                    todoIdInput.setText(String.valueOf(response.body().getId()));
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
