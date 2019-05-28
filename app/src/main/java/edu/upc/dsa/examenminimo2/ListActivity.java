package edu.upc.dsa.examenminimo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {

    private MuseusApi museusApi;
    private RecyclerView recyclerView;
    private MyAdapter recyclerAdapter;
    private Museums museumsList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Progress Bar
        progressBar = findViewById(R.id.indeterminateBar);

        //Recycle View
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new MyAdapter(this, museumsList);
        recyclerView.setAdapter(recyclerAdapter);

        //Api connection
        Gson gson = new GsonBuilder().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://do.diba.cat/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        museusApi = retrofit.create(MuseusApi.class);

        getMuseums();
    }

    private void getMuseums() {

        Call<Museums> call = museusApi.getMuseums();

        call.enqueue(new Callback<Museums>() {
            @Override
            public void onResponse(Call<Museums> call, Response<Museums> response) {
                if (response.isSuccessful()) {
                    museumsList = response.body();
                    recyclerAdapter.setMuseumsList(museumsList);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Museums> call, Throwable t) {

            }
        });
    }
}
