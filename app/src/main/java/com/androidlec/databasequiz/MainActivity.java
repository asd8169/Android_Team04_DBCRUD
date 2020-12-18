package com.androidlec.databasequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("학생 정보 수정")
                            .setMessage("목록에서 접근해 주세요!")
                            .setCancelable(false)
                            .setPositiveButton("확인", null)
                            .show();

                    break;

                //delete로 이동
                case R.id.btn_delete:
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("학생 정보 삭제")
                            .setMessage("목록에서 접근해 주세요!")
                            .setCancelable(false)
                            .setPositiveButton("확인", null)
                            .show();

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