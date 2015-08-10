package com.br.atps.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.br.atps.R;

import java.util.List;

import model.Desejo;

/**
 * Created by ismael on 26/06/2015.
 */
public class DesejoAdapter extends ArrayAdapter<Desejo> {
    private List<Desejo> values;
    private Context context;

    public DesejoAdapter(Context context, int resource, List<Desejo> values) {
        super(context, resource, values);
        this.context = context;
        this.values = values;
    }


    public int getCount(){
        return values.size();
    }

    public Desejo getItem(int position){
        return values.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.simple_text, parent, false);
        TextView tv = (TextView) rootView.findViewById(R.id.simple_item_text);
        tv.setText(values.get(position).getProduto());
        return rootView;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.simple_text, parent, false);
        TextView tv = (TextView) rootView.findViewById(R.id.simple_item_text);
        tv.setText(values.get(position).getProduto());

        return rootView;
    }

}
