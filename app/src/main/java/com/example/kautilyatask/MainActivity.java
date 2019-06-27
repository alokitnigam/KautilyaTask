package com.example.kautilyatask;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kautilyatask.Adapter.ItemAdapter;
import com.example.kautilyatask.Database.DBTools;
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
        itemAdapter=new ItemAdapter(this, itemModels, new ItemAdapter.ItemClickListener() {
            @Override
            public void onEdit(int position) {
                openEditDialog(position);
            }

            @Override
            public void onFav(int position) {

            }
        });
        todorv.setAdapter(itemAdapter);

        getDataFromServer();

    }

    private void openEditDialog(final int position) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button save = dialog.findViewById(R.id.save);
        final EditText title = dialog.findViewById(R.id.titleedit);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(title.getText().length() == 0){
                    Toast.makeText(MainActivity.this,"Length must be gretaer than 0",Toast.LENGTH_SHORT).show();
                }else{
                    DBTools.getDatabase(MainActivity.this).feedDao().setTitle(title.getText().toString(),position+"");
                    itemAdapter.notifyItemChanged(position);
                }
            }
        });



        dialog.show();
    }

    void getDataFromServer(){
        RetrofitClientInstance.getRetrofitInstance().getTasksList().enqueue(new Callback<List<ItemModel>>() {
            @Override
            public void onResponse(Call<List<ItemModel>> call, Response<List<ItemModel>> response) {
                Log.i("", "onResponse: "+response.body());
                ArrayList<ItemModel> localitemModels = (ArrayList<ItemModel>) response.body();

                itemModels.addAll(localitemModels);
                DBTools.getDatabase(MainActivity.this).feedDao().upateItems(itemModels);
                itemAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<ItemModel>> call, Throwable t) {
                Log.i("", "onfaliure: ");

            }
        });
    }
}
