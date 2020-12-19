package com.androidlec.databasequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MemberUpdate extends AppCompatActivity {
    final static String TAG = "종찬";
    Memberinfo memberinfo;

    int sdNoImport;
    String sdNameImport, sdDeptImport, sdTelImport;
    String strsdNo,strsdName,strsdDept,strsdTel;

    EditText tvNo,tvName,tvDept,tvTel;


    SQLiteDatabase DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_update);


        memberinfo = new Memberinfo(MemberUpdate.this);

        Intent intent = getIntent();

        sdNoImport = intent.getIntExtra("sdNO", 0);
        sdNameImport = intent.getStringExtra("sdName");
        sdDeptImport = intent.getStringExtra("sdDept");
        sdTelImport = intent.getStringExtra("sdTel");


        //  EditText 호출하기
        tvNo = findViewById(R.id.sdNo_update);
        tvName = findViewById(R.id.sdName_update);
        tvDept = findViewById(R.id.sdDept_update);
        tvTel = findViewById(R.id.sdTel_update);

        // EditText 올리기
        tvNo.setText(Integer.toString(sdNoImport));
        tvName.setText(sdNameImport);
        tvDept.setText(sdDeptImport);
        tvTel.setText(sdTelImport);

        findViewById(R.id.buttonUpdate_update).setOnClickListener(new View.OnClickListener() {

            //성공


            @Override
            public void onClick(View v) {


                //쿼리 문에 넣을 구문 작성
                 strsdName = tvName.getText().toString();
                 strsdDept = tvDept.getText().toString();
                 strsdTel = tvTel.getText().toString();

                new AlertDialog.Builder(MemberUpdate.this)
                        .setTitle("입력 확인")
                        .setMessage("이름 : " + strsdName + "\n" + "전공 : " + strsdDept + "\n" + "전화번호 : " + strsdTel + "\n" + "입력하신 정보가 맞습니까?")
                        .setCancelable(false)//아무데나 눌렀을때 안꺼지게 하는거 (버튼을 통해서만 닫게)
                        .setPositiveButton("네", mClick)
                        .setNegativeButton("아니오", mClickNo)
                        .show();

            }



        });
    }

   DialogInterface.OnClickListener mClick = new DialogInterface.OnClickListener() {
       @Override
       public void onClick(DialogInterface dialogInterface, int i) {


               //입력 받은 값 받아오기
               strsdName = tvName.getText().toString();
               strsdDept = tvDept.getText().toString();
               strsdTel = tvTel.getText().toString();

               try {
                   DB = memberinfo.getWritableDatabase();
                   String query = "UPDATE MEMBER SET sdName = '" + strsdName + "' , sdDept = '" + strsdDept + "' ,sdTel = '" +
                           strsdTel + "' WHERE sdNo = '" + strsdNo + "';";
                   DB.execSQL(query);
                   memberinfo.close();

                   new AlertDialog.Builder(MemberUpdate.this) // 저장 후 입력 완료 되었다는 Alert 창, 확인 클릭 시 리스트 창으로 이동
                           .setTitle("")
                           .setMessage("입력이 완료 되었습니다.")
                           .setCancelable(false)//아무데나 눌렀을때 안꺼지게 하는거 (버튼을 통해서만 닫게)
                           .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) { // 리스트 창으로 이동해주는 메소드
                                   Intent intent = new Intent(MemberUpdate.this, MemberSelect.class);

                                   intent.putExtra("sdNO",strsdNo);
                                   intent.putExtra("sdName",strsdName);
                                   intent.putExtra("sdDept", strsdDept);
                                   intent.putExtra("sdTel",strsdTel);

                                   startActivity(intent);
                               }
                           })
                           .show();


               } catch (Exception e) { // 입력 실패시
                   e.printStackTrace();
                   new AlertDialog.Builder(MemberUpdate.this)
                           .setTitle("경고!")
                           .setMessage("입력에 실패 하였습니다.")
                           .setCancelable(false)//아무데나 눌렀을때 안꺼지게 하는거 (버튼을 통해서만 닫게)
                           .setPositiveButton("확인", null)
                           .show();
               }

           }

   };

    //아니오 눌렀을경우
    DialogInterface.OnClickListener mClickNo = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_NEGATIVE) {
                new AlertDialog.Builder(MemberUpdate.this)
                        .setTitle("")
                        .setMessage("다시 입력해 주세요.")
                        .setCancelable(false)//아무데나 눌렀을때 안꺼지게 하는거 (버튼을 통해서만 닫게)
                        .setPositiveButton("확인", null)
                        .show();
            }
        }
    };

//---------------------------------------------------------------------------------


    //메뉴바 생성 & 처음으로 돌아가기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_logout:
                Intent intent = new Intent(MemberUpdate.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }


}//------------------------





