package com.musabcekerek.w01.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.musabcekerek.w01.R;
import com.musabcekerek.w01.base.BaseCars;

import java.util.ArrayList;
import java.util.List;

public class CarListAdapter extends ArrayAdapter<BaseCars> {


    private Context mContext;
    int mResource;


    public CarListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<BaseCars> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String whell = getItem(position).getProductionWhell();
        String name = getItem(position).getCarName();
        String door = getItem(position).getProductionDoor();
        String engine = getItem(position).getProductionEngine();


        BaseCars baseCars = new BaseCars(name,whell,door,engine);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tvCarName = (TextView) convertView.findViewById(R.id.textview1);
        TextView tvWhell = (TextView) convertView.findViewById(R.id.textview2);
        TextView tvDoor = (TextView) convertView.findViewById(R.id.textview3);
        TextView tvEngine = (TextView) convertView.findViewById(R.id.textview4);
        tvWhell.setText(whell);
        tvCarName.setText(name);
        tvDoor.setText(door);
        tvEngine.setText(engine);
        return convertView;
    }
}
