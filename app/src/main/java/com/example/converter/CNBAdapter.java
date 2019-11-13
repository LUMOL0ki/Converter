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

// TODO 1. - Aktualne funguje aplikace tak, ze se nacte a zobrazi v listu kody men ze souboru http://www.cnb.cz/cs/financni_trhy/devizovy_trh/kurzy_devizoveho_trhu/denni_kurz.xml
// TODO 1. - Cilem je upravit zobrazovani tak aby se zobrazovalo: vlajka statu, nazev meny, kod statu, nazev statu a cena.
// TODO 1. - Kazdy radek listu je reprezentovan instancemi tridy Entry, v tento okamzik je ve tride Entry udaj pouze pro kod zeme
// TODO 1. - V nastaveni aplikace muzete nastavit jestli se maji data stahovat pouze pres wifi
//  |-----------|----------------------------------|
//  | ImageView |          NAZEV MENY              |  
//  |           |Kod                               | 
//  |  vlajka   |Stat                              |
//  |   statu   |Cena                              | 
//  |           |                                  |
//  |----------------------------------------------|
//

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
