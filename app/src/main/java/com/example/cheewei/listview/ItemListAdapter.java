package com.example.cheewei.listview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemListAdapter extends ArrayAdapter<Item> {

    private Context mContext;
    int mResource;

    public ItemListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Item> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    public View getView (int position, View convertView, ViewGroup parent){
        String itemname = getItem(position).getItemname();
        double price = getItem(position).getPrice();

        Item item  =new Item(itemname,price);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tvItem = (TextView) convertView.findViewById(R.id.txtItem);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.txtPrice);

        tvItem.setText(itemname);
        tvPrice.setText(String.valueOf(price));

        return convertView;

    }
}
