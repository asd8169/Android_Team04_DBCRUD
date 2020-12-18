package com.androidlec.databasequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_insert).setOnClickListener(mClickListener);
        findViewById(R.id.btn_update).setOnClickListener(mClickListener);
        findViewById(R.id.btn_delete).setOnClickListener(mClickListener);
        findViewById(R.id.btn_select).setOnClickListener(mClickListener);
    }


    //컨트롤러
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                //insert로 이동
                case R.id.btn_insert:
                    Intent intent1 = new Intent(MainActivity.this,MemberInsert.class);
                    startActivity(intent1);
                    break;

                //update 이동
                case R.id.btn_update:
                    Intent intent2 = new Intent(MainActivity.this,MemberUpdate.class);
                    startActivity(intent2);
                    break;

                //delete로 이동
                case R.id.btn_delete:
                    Intent intent3 = new Intent(MainActivity.this,MemberDelect.class);
                    startActivity(intent3);
                    break;

                //select로 이동
                case R.id.btn_select:
                    Intent intent4 = new Intent(MainActivity.this,MemberSelect.class);
                    startActivity(intent4);
                    break;
            }
        }
    };






}