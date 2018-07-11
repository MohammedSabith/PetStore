package com.example.iamgroot.petstore;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private Context context ;
    private List<Holder> mData;


    public RecyclerViewAdapter(Context context, List<Holder> mData)
    {
        this.context = context;
        this.mData = mData;
    }



    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.card_design,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
/*
        holder.profileImage.setImageResource(mData.get(position).getProfileImage());
*/
        holder.name.setText(mData.get(position).getName());
        holder.contentImage.setImageResource(mData.get(position).getContentImage());
        holder.caption.setText(mData.get(position).getCaption());
        holder.buyBtn.setText(mData.get(position).getBuyBtn());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, caption;
        ImageView profileImage, contentImage;
        CardView cardView ;
        Button buyBtn;

        public MyViewHolder(final View itemView) {
            super(itemView);
/*
            profileImage = itemView.findViewById(R.id.user_pic);
*/
            name = itemView.findViewById(R.id.user_name);
            contentImage = itemView.findViewById(R.id.content_image);
            caption = itemView.findViewById(R.id.caption);
            cardView =itemView.findViewById(R.id.cardview_id);

            buyBtn =itemView.findViewById(R.id.buybtn);
            buyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Intent intent = new Intent(context.getApplicationContext(),BuyActivity.class);
                   intent.putExtra("image",name.getText().toString());
                   context.startActivity(intent);

                }
            });

        }

    }
}