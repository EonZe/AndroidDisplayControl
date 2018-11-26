package eonzombie.net.androiddisplaycontrol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<BluetoothProfile>
{
    private ArrayList<BluetoothProfile> dataSet;
    Activity activity;


    public CustomAdapter(Activity activity, ArrayList<BluetoothProfile> data, Context context)
    {
        super(context,R.layout.row_item, data);
        dataSet = data;
        this.activity = activity;
    }

    public View getView(int position,View view,ViewGroup parent)
    {
        LayoutInflater inflater= activity.getLayoutInflater();

        View rowView=inflater.inflate(R.layout.row_item, null,true);

        TextView id = rowView.findViewById(R.id.devid);
        TextView mac = rowView.findViewById(R.id.MACaddr);
        BluetoothProfile bp = dataSet.get(position);
        id.setText(bp.id);
        mac.setText(bp.MACaddress);

        return rowView;
    }

}
