package me.branded.hossamhassan.retrofit_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import me.branded.hossamhassan.retrofit_test.API.HossRetroApi;
import me.branded.hossamhassan.retrofit_test.Models.Post;
import me.branded.hossamhassan.retrofit_test.Models.ResultPosts;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.tv1);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://developerhendy.16mb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        HossRetroApi hossRetroApi=retrofit.create(HossRetroApi.class);
        Call<ResultPosts> connection = hossRetroApi.getPosts();
        connection.enqueue(new Callback<ResultPosts>() {
            @Override
            public void onResponse(Call<ResultPosts> call, Response<ResultPosts> response) {
                List<Post>posts=response.body().getPosts();
                for (int i=0;i<posts.size();i++)
                {
                    String postsContent=posts.get(i).getPost_content();
                    textView.append("\n"+postsContent);

                }
                Toast.makeText(MainActivity.this, "Success ", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ResultPosts> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




        Retrofit retrofitForSend=new Retrofit.Builder()
                .baseUrl("http://developerhendy.16mb.com/")
                .build();

        Call<ResponseBody>addUserConnection=hossRetroApi.addUser(
                "Hoss",
                "123456789",
                "hoss1234@gmail.com",
                "cairo"
        );
        addUserConnection.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try
                {
                    textView.append("success"+response.body().string());
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                textView.append("success"+t.getMessage());

            }
        });



    }
}
