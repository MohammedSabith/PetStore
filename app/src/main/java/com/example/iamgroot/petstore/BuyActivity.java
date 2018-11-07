package com.example.iamgroot.petstore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.util.Scanner;

public class BuyActivity extends AppCompatActivity {
    String path;
    Button cnf_btn;
TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        cnf_btn = findViewById(R.id.confirm_btn);

        txt = findViewById(R.id.textView);

        VideoView videoView = findViewById(R.id.videoView);
        path = "android.resource://" + getPackageName() + "/" + R.raw.test;
        videoView.setVideoURI(Uri.parse(path));
        videoView.start();


        String data = getIntent().getExtras().getString("image");

        txt.setText("This Golden Retriever puppy just wants your boops 24x7. 13/10 good boi.\n" +
                "Just look at him hooman, he is infinite times cuter than your girl/guy, and if you're single\n" +
                "why are you even waiting? buy this lovely fur ball asap.\n" +
                "\n" +
                "price: ₹3000\n");

        cnf_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             
                        new.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                                Intent intents = new Intent(BuyActivity.this,LoginActivity.class);
                                finish();
                                startActivity(intent);
                            }
                        });
                AlertDialog alertDialog = mbuilder.create();
                alertDialog.setTitle("");
            }
        });


    }
}
