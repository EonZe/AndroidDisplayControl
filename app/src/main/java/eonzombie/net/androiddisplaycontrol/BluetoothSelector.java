package eonzombie.net.androiddisplaycontrol;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Set;

public class BluetoothSelector extends AppCompatActivity {

    Set<BluetoothDevice> pairedDevices;
    ArrayList<BluetoothProfile> bdevices;
    BluetoothAdapter ad = BluetoothAdapter.getDefaultAdapter();
    ListView listView;
    ToggleButton toggleButton;

    private static CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_selector);

        pairedDevices = ad.getBondedDevices();
        bdevices = new ArrayList<>();
        listView = findViewById(R.id.devices);
        toggleButton = findViewById(R.id.scanButton);

        for (BluetoothDevice d: pairedDevices)
        {
            bdevices.add(new BluetoothProfile(d.getName(),d.getAddress()));
        }
        refreshList();

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (!isChecked)
               {
                   try {unregisterReceiver(mReceiver);}
                   catch (IllegalArgumentException e) {}
               }
               else
               {
                   IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                   try {registerReceiver(mReceiver,filter);}
                   catch (IllegalArgumentException e) {}
               }
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BluetoothProfile bdevice= bdevices.get(position);

                String mac = bdevice.MACaddress;
                MainActivity.mac = mac;

                Intent intent = new Intent(MainActivity.context, MainActivity.class);
                MainActivity.context.startActivity(intent);
            }
        });
    }

    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver mReceiver = new BroadcastReceiver()
    {
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action))
            {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress();
                BluetoothProfile bp = new BluetoothProfile(deviceName,deviceHardwareAddress);
                bdevices.add(bp);
                customAdapter.notifyDataSetChanged();
            }
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {unregisterReceiver(mReceiver);}
        catch (IllegalArgumentException e) {}
    }

    void refreshList()
    {
        customAdapter = new CustomAdapter(this ,bdevices, MainActivity.context);
        listView.setAdapter(customAdapter);
    }

}
