package com.androidlec.databasequiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Select_list_Adapter  extends BaseAdapter {

    private Context mContext = null;
    private  int layout = 0;
    private ArrayList<Select_list> data = null;
    private LayoutInflater inflater = null;

    public Select_list_Adapter(Context mContext, int layout, ArrayList<Select_list> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(this.layout, parent,false);

        }
        TextView tv_sdNo = convertView.findViewById(R.id.tv_sdNo);
        TextView tv_sdName = convertView.findViewById(R.id.tv_sdName);
        TextView tv_sdDept = convertView.findViewById(R.id.tv_sdDept);
        TextView tv_sdTel = convertView.findViewById(R.id.tv_sdTel);


        tv_sdNo.setText(data.get(position).getSdNo() + " ");
        tv_sdName.setText(data.get(position).getSdName() + " ");
        tv_sdDept.setText(data.get(position).getSdDept() + " ");
        tv_sdTel.setText(data.get(position).getSdTel() + " ");


        if (position % 2 == 1){
            convertView.setBackgroundColor(0xdcdcdc);
        }else {
            convertView.setBackgroundColor(0xbebebe);
        }
        return convertView;



    }
}//=====
