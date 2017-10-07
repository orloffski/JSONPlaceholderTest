package by.development.madcat.jsonplaceholdertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.development.madcat.jsonplaceholdertest.models.Comment;
import by.development.madcat.jsonplaceholdertest.models.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardsActivity extends AppCompatActivity implements View.OnClickListener {

    private static JsonPlaceholderApi jsonPlaceholderApi;

    @BindView(R.id.post_title) TextView postTitle;
    @BindView(R.id.post_body) TextView postBody;
    @BindView(R.id.post_user_id) TextView postUserId;
    @BindView(R.id.post_id) TextView postId;
    @BindView(R.id.post_id_input) EditText postIdInput;

    @BindView(R.id.comment_name) TextView commentName;
    @BindView(R.id.comment_body) TextView commentBody;
    @BindView(R.id.comment_email) TextView commentEmail;
    @BindView(R.id.comment_post_id) TextView commentPostId;
    @BindView(R.id.comment_id) TextView commentId;
    @BindView(R.id.comment_id_input) EditText commentIdInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        ButterKnife.bind(this);

        init();
    }

    private void init(){
        postIdInput.setFilters(new InputFilter[]{new InputFilterMinMax(1, 100)});
        commentIdInput.setFilters(new InputFilter[]{new InputFilterMinMax(1, 500)});

        jsonPlaceholderApi = JsonPlaceholderApiController.getApi();
    }

    @OnClick({R.id.get_post_button, R.id.get_comment_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_post_button:
                loadPost();
                break;
            case R.id.get_comment_button:
                loadComment();
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
                postId.setText(String.valueOf(response.body().getId()));
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    private void loadComment(){
        if(commentIdInput.getText().toString().length() == 0)
            return;

        Integer number = Integer.parseInt(commentIdInput.getText().toString());
        jsonPlaceholderApi.getCommentById(String.valueOf(number)).enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                commentName.setText(response.body().getName());
                commentBody.setText(response.body().getBody());
                commentEmail.setText(response.body().getEmail());
                commentPostId.setText(String.valueOf(response.body().getPostId()));
                commentId.setText(String.valueOf(response.body().getId()));
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {

            }
        });
    }
}
