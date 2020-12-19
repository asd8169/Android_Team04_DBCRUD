package com.androidlec.databasequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
    int strsdNo;
    String strsdName;
    String strsdDept;
    String strsdTel;




    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_update);

        //업데이트 버튼
        findViewById(R.id.buttonUpdate_update).setOnClickListener(onClickListener);

        //  EditText 호출하기
        TextView sdNo = findViewById(R.id.sdNo_update);
        EditText sdName = findViewById(R.id.sdName_update);
        EditText sdDept = findViewById(R.id.sdDept_update);
        EditText sdTel = findViewById(R.id.sdTel_update);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        strsdNo = bundle.getInt("sdNO");
        strsdName = bundle.getString("sdName");
        strsdDept = bundle.getString("sdDept");
        strsdTel = bundle.getString("sdTel");

        Log.d(TAG, "strsdNo: " + strsdNo);
        Log.d(TAG, "MembrerUpdate: " + strsdName);


     // EditText 올리기
        sdNo.setText(Integer.toString(strsdNo));
        sdName.setText(strsdName);
        sdDept.setText(strsdDept);
        sdTel.setText(strsdTel);




    }




    View.OnClickListener onClickListener = new View.OnClickListener() { //여기서 에러
        SQLiteDatabase DB; //데이타 베이스 가져오기

        @Override
        public void onClick(View v) {

        //  EditText 호출하기
//            TextView sdNo = findViewById(R.id.sdNo_update);// 여기서 에러
//            EditText sdName = findViewById(R.id.sdName_update);
//            EditText sdDept = findViewById(R.id.sdDept_update);
//            EditText sdTel = findViewById(R.id.sdTel_update);
//
//            //쿼리 문에 넣을 구문 작성
//            int strsdNo = Integer.parseInt((String)sdNo.getText());
//            String strsdName = sdName.getText().toString();
//            String strsdDept = sdDept.getText().toString();
//           String strsdTel = sdTel.getText().toString();



//        switch (v.getId()){
//            case R.id.buttonUpdate_update: //업데이트 버튼 누르기

                Intent intent = new Intent(MemberUpdate.this, MemberSelect.class);
                startActivity(intent);

                try {//정상적으로 구동 될 경우
                    DB = memberinfo.getWritableDatabase();
                    String query = "UPDATE member SET sdName = '"+ strsdName +"', sdDept = '"+strsdDept+"',sdTel ='"+strsdTel+"' WHERE sdNo = '9';";
                    DB.execSQL(query);
                    memberinfo.close();

//                    UPDATE member SET sdName = 'inwoo',  sdDept = '롤', sdTel = '0101111111' WHERE sdNo = 1

                    new AlertDialog.Builder(MemberUpdate.this)
                            .setTitle("알림")
                            .setMessage("수정 완료했습니다.")
                            .setNegativeButton("확인",null)
                            .setCancelable(false)
                            .show();
//                    Intent intent = new Intent(MemberUpdate.this,MainActivity.class);
//                    startActivity(intent);

                    finish();
                } catch (Exception e) {//에러 나는 경우

                    e.printStackTrace();
                    Toast.makeText(MemberUpdate.this, "Update Error", Toast.LENGTH_SHORT).show();
                }


//        }
        }
    };

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


}
