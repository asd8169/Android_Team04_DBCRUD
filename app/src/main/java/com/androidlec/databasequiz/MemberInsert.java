package com.androidlec.databasequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MemberInsert extends AppCompatActivity {

    //field
    Memberinfo memberinfo;
    EditText insert_sdName;
    EditText insert_sdDept;
    EditText insert_sdTel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_insert);



        memberinfo = new Memberinfo(MemberInsert.this);


        insert_sdDept = findViewById(R.id.insert_sdDept);
        insert_sdName = findViewById(R.id.insert_sdName);
        insert_sdTel = findViewById(R.id.insert_sdTel);



        findViewById(R.id.buttonInsert_Insert).setOnClickListener(new View.OnClickListener() {
            SQLiteDatabase DB;

            @Override
            public void onClick(View v) {

                String major = insert_sdDept.getText().toString();
                String name = insert_sdName.getText().toString();
                String tel = insert_sdTel.getText().toString();


                new AlertDialog.Builder(MemberInsert.this)
                        .setTitle("입력 확인")
                        .setMessage("이름 : " + name + "\n" + "전공 : " + major + "\n" + "전화번호 : " + tel+"\n"+"입력하신 정보가 맞습니까?")
                        .setCancelable(false)//아무데나 눌렀을때 안꺼지게 하는거 (버튼을 통해서만 닫게)
                        .setPositiveButton("네", mClick)
                        .setNegativeButton("아니오", mClickNo)
                        .show();


            }


            DialogInterface.OnClickListener mClick = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == DialogInterface.BUTTON_POSITIVE) {

                        String major = insert_sdDept.getText().toString();
                        String name = insert_sdName.getText().toString();
                        String tel = insert_sdTel.getText().toString();

                                try {
                                    DB = memberinfo.getWritableDatabase();
                                    String query = "INSERT INTO MEMBER (sdName, sdDept, sdTel) VALUES ('" + name + "' , '" + major + "', '" + tel + "');";
                                    DB.execSQL(query);
                                    memberinfo.close();
                                    new AlertDialog.Builder(MemberInsert.this)
                                            .setTitle("")
                                            .setMessage("입력이 완료 되었습니다.")
                                            .setCancelable(false)//아무데나 눌렀을때 안꺼지게 하는거 (버튼을 통해서만 닫게)
                                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent(MemberInsert.this,MemberSelect.class);
                                                    startActivity(intent);
                                                }
                                            })
                                            .show();




                                } catch (Exception e) {
                                    e.printStackTrace();
                                    new AlertDialog.Builder(MemberInsert.this)
                                            .setTitle("경고!")
                                            .setMessage("입력에 실패 하였습니다.")
                                            .setCancelable(false)//아무데나 눌렀을때 안꺼지게 하는거 (버튼을 통해서만 닫게)
                                            .setPositiveButton("확인", null)
                                            .show();
                                }

                        }

                }
            };
          DialogInterface.OnClickListener mClickNo = new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                  if(which == DialogInterface.BUTTON_NEGATIVE) {
                      new AlertDialog.Builder(MemberInsert.this)
                              .setTitle("")
                              .setMessage("다시 입력해 주세요.")
                              .setCancelable(false)//아무데나 눌렀을때 안꺼지게 하는거 (버튼을 통해서만 닫게)
                              .setPositiveButton("확인", null)
                              .show();
                  }

              }
          };



        });


    }

            //메뉴바 생성 & 처음으로 돌아가기

            public boolean onCreateOptionsMenu(Menu menu) {

                getMenuInflater().inflate(R.menu.menu_main, menu);


                return true;
            }


            public boolean onOptionsItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.action_logout:
                        Intent intent = new Intent(MemberInsert.this, MainActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }


    }//------------------------





