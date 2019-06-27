package com.example.kautilyatask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.kautilyatask.Adapter.ItemAdapter;
import com.example.kautilyatask.Model.ItemModel;
import com.example.kautilyatask.Network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView todorv;
    ItemAdapter itemAdapter;
    ArrayList<ItemModel> itemModels=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todorv=findViewById(R.id.todorv);
        todorv.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter=new ItemAdapter(this,itemModels);
        todorv.setAdapter(itemAdapter);

        getDataFromServer();

    }

    void getDataFromServer(){
        RetrofitClientInstance.getRetrofitInstance().getTasksList().enqueue(new Callback<List<ItemModel>>() {
            @Override
            public void onResponse(Call<List<ItemModel>> call, Response<List<ItemModel>> response) {
                Log.i("", "onResponse: "+response.body());
                ArrayList<ItemModel> localitemModels = (ArrayList<ItemModel>) response.body();

                itemModels.addAll(localitemModels);
                itemAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<ItemModel>> call, Throwable t) {
                Log.i("", "onfaliure: ");

            }
        });
    }
}
