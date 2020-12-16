package org.latihan.retrofitlatihan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.latihan.retrofitlatihan.databinding.ActivityMainBinding;
import org.latihan.retrofitlatihan.models.Repo;
import org.latihan.retrofitlatihan.services.GithubService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etInputGithub;
    Button btnInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        etInputGithub = findViewById(R.id.input_username_github);
        btnInput = findViewById(R.id.btn_input);

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.github.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                GithubService service = retrofit.create(GithubService.class);

                Call<List<Repo>> repos = service.listRepos(etInputGithub.getText().toString());

                repos.enqueue(new Callback<List<Repo>>() {
                    @Override
                    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                        binding.setRepo(response.body().get(0));
                    }

                    @Override
                    public void onFailure(Call<List<Repo>> call, Throwable t) {
                        Log.e("Error",t.getMessage());
                    }
                });
            }
        });



    }
}