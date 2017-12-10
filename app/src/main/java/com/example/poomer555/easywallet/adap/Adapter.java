package com.example.poomer555.easywallet.adap;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poomer555.easywallet.Model.Item;
import com.example.poomer555.easywallet.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.poomer555.easywallet.R.drawable.ic_income;

/**
 * Created by poome on 12/10/2017.
 */

public class Adapter extends ArrayAdapter<Item> {
    private Context mContext;
    private int mResID;
    private ArrayList<Item> mItem;
    private int fTotal;

    public Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Item> objects) {
        super(context, resource, objects);
        mContext =context;
        mResID = resource;
        mItem = objects;
    }
     public int cal() {
         for (Item m : mItem) {
             fTotal = fTotal + m.Much;


         }
         return fTotal;
     }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View ItemLayout = inflater.inflate(mResID,null);

        Item item = mItem.get(position);


        ImageView ItemPic = ItemLayout.findViewById(R.id.PicList);
        TextView ItemName = ItemLayout.findViewById(R.id.NameList);
        TextView ItemValue = ItemLayout.findViewById(R.id.ValueList);
        String showmuch =String.valueOf(item.Much);
        ItemName.setText(item.Name);
        ItemValue.setText(showmuch);
        if (item.Type.equals("0")) {

            ItemPic.setImageResource(R.drawable.ic_income);
        }

        else{
            ItemPic.setImageResource(R.drawable.ic_expense);
        }

return ItemLayout;
    }

}
