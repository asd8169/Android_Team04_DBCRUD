package com.androidlec.databasequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.regex.Pattern;


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


        //editText 아이디 연결해주기

        insert_sdDept = findViewById(R.id.insert_sdDept);
        insert_sdName = findViewById(R.id.insert_sdName);
        insert_sdTel = findViewById(R.id.insert_sdTel);


        // 입력 버튼 클릭 시, 입력한 값이 맞는지 확인 Alert 띄워주기
        findViewById(R.id.buttonInsert_Insert).setOnClickListener(new View.OnClickListener() {
            SQLiteDatabase DB;


            @Override
            public void onClick(View v) {

                // 입력 받은 값 받아오기
                String major = insert_sdDept.getText().toString();
                String name = insert_sdName.getText().toString();
                String tel = insert_sdTel.getText().toString();

                //빈칸인데 입력버튼 눌렀을 경우, 경고창 띄워주기
                if (insert_sdName.getText().length() == 0 || insert_sdDept.getText().length() == 0 || insert_sdTel.getText().length() == 0) {

                    new AlertDialog.Builder(MemberInsert.this)
                            .setTitle("경고!")
                            .setMessage("정보를 입력해주세요!")
                            .setCancelable(false)//아무데나 눌렀을때 안꺼지게 하는거 (버튼을 통해서만 닫게)
                            .setPositiveButton("확인", null)
                            .show();
                } else {

                    //빈칸이 아닐경우 확인 Alert 창띄우기
                    new AlertDialog.Builder(MemberInsert.this)
                            .setTitle("입력 확인")
                            .setMessage("이름 : " + name + "\n" + "전공 : " + major + "\n" + "전화번호 : " + tel + "\n" + "입력하신 정보가 맞습니까?")
                            .setCancelable(false)//아무데나 눌렀을때 안꺼지게 하는거 (버튼을 통해서만 닫게)
                            .setPositiveButton("네", mClick) // 맞을땐 mClick Dialog 창 띄우기
                            .setNegativeButton("아니오", mClickNo) // 아닐때는 mClickNO Dialog 창 띄우기
                            .show();
                }

            }

            // Alert 창에서 네를 눌렀을 경우 띄워지는 Dialog
            DialogInterface.OnClickListener mClick = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == DialogInterface.BUTTON_POSITIVE) {

                        //입력 받은 값 받아오기
                        String major = insert_sdDept.getText().toString();
                        String name = insert_sdName.getText().toString();
                        String tel = insert_sdTel.getText().toString();

                        try {
                            DB = memberinfo.getWritableDatabase();
                            String query = "INSERT INTO MEMBER (sdName, sdDept, sdTel) VALUES ('" + name + "' , '" + major + "', '" + tel + "');";
                            DB.execSQL(query);
                            memberinfo.close();
                            new AlertDialog.Builder(MemberInsert.this) // 저장 후 입력 완료 되었다는 Alert 창, 확인 클릭 시 리스트 창으로 이동
                                    .setTitle("")
                                    .setMessage("입력이 완료 되었습니다.")
                                    .setCancelable(false)//아무데나 눌렀을때 안꺼지게 하는거 (버튼을 통해서만 닫게)
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) { // 리스트 창으로 이동해주는 메소드
                                            Intent intent = new Intent(MemberInsert.this, MemberSelect.class);
                                            startActivity(intent);
                                        }
                                    })
                                    .show();


                        } catch (Exception e) { // 입력 실패시
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

            //아니오 버튼 눌렀을 때, Dialog 띄우기
            DialogInterface.OnClickListener mClickNo = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == DialogInterface.BUTTON_NEGATIVE) {
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

    //메뉴바 생성

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    //메뉴바 - 처음으로 이동하기
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_logout:
                Intent intent = new Intent(MemberInsert.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }


    //배경 터치 시 키보드 사라지게
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        InputMethodManager imm;
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }


}//------------------------





