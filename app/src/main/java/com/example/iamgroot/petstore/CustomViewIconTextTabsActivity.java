package com.example.iamgroot.petstore;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class CustomViewIconTextTabsActivity extends AppCompatActivity {
    List<Holder> list;
    Button buybttn;
@Override
  protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_custom_view_icon_text_tabs);

    list = new ArrayList<>();

        list.add(new Holder("Puppy1", R.drawable.pupper1, "Golden Retriever",R.string.buy_pet));
        list.add(new Holder("Puppy2", R.drawable.pupper2, "Dachshund",R.string.buy_pet));
        list.add(new Holder("Puppy3", R.drawable.pupper3, "German shepherd ",R.string.buy_pet));
        list.add(new Holder("Puppy4",  R.drawable.pupper4, "Beagle",R.string.buy_pet));

        /*buybttn =(Button)findViewById(R.id.buybtn);

        buybttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomViewIconTextTabsActivity.this,BuyActivity.class);
                startActivity(intent);
            }
        });*/

    RecyclerView mRecyclerView = findViewById(R.id.recyclerview_id);
    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

    RecyclerView.Adapter mAdapter = new RecyclerViewAdapter(this, list);
    mRecyclerView.setAdapter(mAdapter);



}

}
