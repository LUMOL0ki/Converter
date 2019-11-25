package com.example.converter;

import java.util.List;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CNBAdapter extends ArrayAdapter<Entry>{

    Context context;
    int layoutResourceId;   
    List<Entry> data = null;
    
   
    public CNBAdapter(Context context, int layoutResourceId, List<Entry> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EntryHolder holder = null;
       
        if(row == null)
        {
        	LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );      	
            row = inflater.inflate(layoutResourceId, parent, false);
           
            holder = new EntryHolder();
            holder.txtCode = (TextView)row.findViewById(R.id.txtCode);
            holder.txtCountry = row.findViewById(R.id.txtCountry);
            holder.txtRate = row.findViewById(R.id.txtRate);
            holder.imgFlag = row.findViewById(R.id.imgFlag);
            row.setTag(holder);
        }
        else
        {
            holder = (EntryHolder)row.getTag();
        }
       
        Entry entry = data.get(position);

        holder.txtCode.setText(entry.getCode());
        holder.txtCountry.setText(entry.getCountry());
        holder.txtRate.setText(String.valueOf(entry.getRate()));
        String imgName = "flag_" + entry.getCode().toLowerCase();
        Resources res = context.getResources();
        int imgId = res.getIdentifier(imgName, "drawable", context.getPackageName());
        holder.imgFlag.setImageResource(imgId);
        // to do: add country name
        // add current rate
        // add image of country flag
        return row;
    }
   
    static class EntryHolder
    {
        TextView txtCode;
        TextView txtCountry;
        TextView txtRate;
        ImageView imgFlag;
    }
}
