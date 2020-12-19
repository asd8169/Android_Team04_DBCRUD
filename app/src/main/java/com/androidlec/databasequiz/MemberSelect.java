package com.androidlec.databasequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MemberSelect extends AppCompatActivity {

    private ArrayList<Select_list> data = null;
    private Select_list_Adapter adapter = null;
    private ListView listView = null;

    int sdNO;
    String sdName,sdDept,sdTel;

    Memberinfo studentInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_select);

       selectall();

        listView.setOnItemClickListener(click);
        listView.setOnItemLongClickListener(longclick);

    }//oncreate

    public void selectall(){
        studentInfo = new Memberinfo(MemberSelect.this);
        data = new ArrayList<Select_list>();
        try {


            SQLiteDatabase DB;
            DB = studentInfo.getReadableDatabase();
            String query = "SELECT * FROM member;";
            Cursor cursor = DB.rawQuery(query, null);

            StringBuffer stringBuffer = new StringBuffer();

            while (cursor.moveToNext()) {
                sdNO = cursor.getInt(0);
                sdName = cursor.getString(1);
                sdDept = cursor.getString(2);
                sdTel = cursor.getString(3);

                data.add(new Select_list(sdNO, sdName, sdDept, sdTel));
            }

            adapter = new Select_list_Adapter(MemberSelect.this, R.layout.custom_layout,data);
            listView = findViewById(R.id.lv_select);
            listView.setAdapter(adapter);
            cursor.close();
            studentInfo.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //리스트 짧게 클릭
    AdapterView.OnItemClickListener click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(), MemberUpdate.class);

            //데이터 전달 putExtra
            intent.putExtra("sdNO", data.get(position).getSdNo());
            intent.putExtra("sdName",data.get(position).getSdName());
            intent.putExtra("sdDept", data.get(position).getSdDept());
            intent.putExtra("sdTel",data.get(position).getSdTel());

            startActivity(intent);

        }
    };

    AdapterView.OnItemLongClickListener longclick = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            Intent intent = new Intent(getApplicationContext(), MemberDelect.class);

            //데이터 전달 putExtra
            intent.putExtra("sdNO", data.get(position).getSdNo());
            intent.putExtra("sdName", data.get(position).getSdName());
            intent.putExtra("sdDept", data.get(position).getSdDept());
            intent.putExtra("sdTel", data.get(position).getSdTel());

            startActivity(intent);
            return false;
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
                Intent intent = new Intent(MemberSelect.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }



}