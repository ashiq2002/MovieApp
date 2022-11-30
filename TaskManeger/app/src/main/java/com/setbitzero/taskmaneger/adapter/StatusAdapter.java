package com.setbitzero.taskmaneger.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.setbitzero.taskmaneger.R;


public class StatusAdapter extends BaseAdapter {
    Context context;
    String[] status;

    public StatusAdapter(Context context, String[] status) {
        this.context = context;
        this.status = status;
    }

    @Override
    public int getCount() {
        return status.length;
    }

    @Override
    public Object getItem(int position) {

        return status[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.status_item,null,false);
            TextView textView = convertView.findViewById(R.id.statusText);

            textView.setText(status[position]);
        }
        return convertView;
    }
}
