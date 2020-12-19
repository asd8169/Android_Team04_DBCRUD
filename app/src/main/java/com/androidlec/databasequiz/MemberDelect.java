package com.androidlec.databasequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MemberDelect extends AppCompatActivity {

    //---field
    final static String TAG = "status";
    TextView tvNo, tvName, tvDept, tvTel;
    Button btnDelete;
    Memberinfo memberinfo;
    SQLiteDatabase DB;
    String Name;
    int sdNo;

    //----시동
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_delect);


        //-----리스트에서 값 받아오기
        Intent intent = getIntent();
        sdNo = intent.getIntExtra("sdNO",0); //받아오는 sdNo값이 defaultValue값으로 들어옴
        String sdName = intent.getStringExtra("sdName");
        String sdDept = intent.getStringExtra("sdDept");
        String sdTel = intent.getStringExtra("sdTel");
        //---------------

        //-------xml 연결
        tvNo = findViewById(R.id.tv_sdNo_deletePage);
        tvName = findViewById(R.id.tv_sdName_deletePage);
        tvDept = findViewById(R.id.tv_sdDept_deletePage);
        tvTel = findViewById(R.id.tv_sdTel_deletePage);
        //--------------


        //--------xml에 값 세팅
        tvNo.setText(Integer.toString(sdNo));//학생 정보 목록에서 롱클릭시 sdNo값 다르게 뜸!
        tvName.setText(sdName);
        tvDept.setText(sdDept);
        tvTel.setText(sdTel);
        //----------------


        Name = sdName;

        memberinfo = new Memberinfo(MemberDelect.this);


        btnDelete = findViewById(R.id.btn_delete_deletePage);

        //---삭제버튼 onclick
        View.OnClickListener onClickListener = new View.OnClickListener() {
            SQLiteDatabase DB;

            @Override
            public void onClick(View v) {
                sdNo = getIntent().getIntExtra("sdNO", 0);
                new AlertDialog.Builder(MemberDelect.this)
                        .setTitle("학생 정보 삭제")
                        .setMessage(Name + "님의 정보를 완전히 삭제합니다. \n삭제하신 정보는 복구되지 않습니다. \n정말로 삭제하시겠습니까?")
                        .setCancelable(false)

                        //---네 버튼
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(MemberDelect.this, MemberSelect.class);
                                startActivity(intent);

                                try {
                                    //----삭제 성공
                                    DB = memberinfo.getWritableDatabase();
                                    String query = "DELETE FROM member WHERE sdNo='" + sdNo + "';";
                                    DB.execSQL(query);


                                    memberinfo.close();
                                    Toast.makeText(MemberDelect.this, "delete OK!", Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    //----삭제 실패
                                    e.printStackTrace();
                                    Toast.makeText(MemberDelect.this, "delete Error", Toast.LENGTH_SHORT).show();
                                }

                            }
                        })

                        //---아니오 버튼
                        .setNegativeButton("아니오", null)
                        .show();

            }
        };
    }























    //-------------------------------------------------Default end
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
                Intent intent = new Intent(MemberDelect.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}