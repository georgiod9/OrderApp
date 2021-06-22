package com.example.orderapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.orderapp.R;

import org.w3c.dom.Text;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    Context context;
    List<String> list;
    LayoutInflater inflater;

    public GridViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_gridview, null);
        TextView entry = (TextView) convertView.findViewById(R.id.entryTextView);
        String text = new String();

        text = list.get(position);
        entry.setText(text);
        return convertView;
    }
}












