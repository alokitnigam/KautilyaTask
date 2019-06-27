package com.example.kautilyatask.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kautilyatask.Model.ItemModel;
import com.example.kautilyatask.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private  ItemClickListener clicklistener;
    private ArrayList<ItemModel> list;
    private Context context;
    public interface ItemClickListener{
        void onEdit(int position);
        void onFav(int position);
    }
    public ItemAdapter(Context context,ArrayList<ItemModel> list,ItemClickListener clickListener){
        this.context=context;
        this.list=list;
        this.clicklistener=clickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemadapter, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.userId.setText("User id:"+list.get(i).getUserId());
        viewHolder.itemid.setText(list.get(i).getId());
        if(list.get(i).getCompleted()){
            viewHolder.card.setCardBackgroundColor(Color.BLUE);

        }else{
            viewHolder.card.setCardBackgroundColor(Color.RED);

        }
        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView fav,edit;
        TextView userId,itemid;
        CardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            fav=itemView.findViewById(R.id.fav);
            userId=itemView.findViewById(R.id.userId);
            itemid=itemView.findViewById(R.id.itemid);
            card=itemView.findViewById(R.id.card);
            edit=itemView.findViewById(R.id.edit);


        }
    }
}
