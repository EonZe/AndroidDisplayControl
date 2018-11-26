package eonzombie.net.androiddisplaycontrol;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
{

    EditText a;
    EditText b;
    EditText c;
    EditText d;
    EditText e;
    EditText f;
    EditText g;
    EditText h;
    Button button;
    FloatingActionButton fab;
    BluetoothAdapter ad;
    BluetoothSocket mmSocket;
    public static String mac = "";
    String UUID = "00001101-0000-1000-8000-00805F9B34FB";
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        a = findViewById(R.id.A);
        b = findViewById(R.id.B);
        c = findViewById(R.id.C);
        d = findViewById(R.id.D);
        e = findViewById(R.id.E);
        f = findViewById(R.id.F);
        g = findViewById(R.id.G);
        h = findViewById(R.id.H);
        button = findViewById(R.id.send);
        fab = findViewById(R.id.fab);
        context = this;

        ad = BluetoothAdapter.getDefaultAdapter();

        if (ad == null)
        {

        }
        if (!ad.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, BluetoothSelector.class);
                startActivity(intent);
            }
        });




        button.setOnClickListener(
                new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {

                        if (a.getText().toString().equalsIgnoreCase("")) a.setText("A");
                        if (b.getText().toString().equalsIgnoreCase("")) b.setText("B");
                        if (c.getText().toString().equalsIgnoreCase("")) c.setText("C");
                        if (d.getText().toString().equalsIgnoreCase("")) d.setText("D");
                        if (e.getText().toString().equalsIgnoreCase("")) e.setText("E");
                        if (f.getText().toString().equalsIgnoreCase("")) f.setText("F");
                        if (g.getText().toString().equalsIgnoreCase("")) g.setText("G");
                        if (h.getText().toString().equalsIgnoreCase("")) h.setText("H");

                        String one = ""+a.getText().charAt(0)+b.getText().charAt(0)+c.getText().charAt(0)+d.getText().charAt(0);
                        String two = ""+e.getText().charAt(0)+f.getText().charAt(0)+g.getText().charAt(0)+h.getText().charAt(0);

                        sendData(one,two);
                    }
                }
        );

        TextWatcher twa = new TextWatcher()
        {
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void afterTextChanged(Editable s) {}
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(s.toString().length() >1) setText(a,""+s.toString().charAt(start));
            }
        };
        TextWatcher twb = new TextWatcher()
        {
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void afterTextChanged(Editable s) {}
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(s.toString().length() >1) setText(b,""+s.toString().charAt(start));
            }
        };
        TextWatcher twc = new TextWatcher()
        {
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void afterTextChanged(Editable s) {}
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(s.toString().length() >1) setText(c,""+s.toString().charAt(start));
            }
        };
        TextWatcher twd = new TextWatcher()
        {
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void afterTextChanged(Editable s) {}
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(s.toString().length() >1) setText(d,""+s.toString().charAt(start));
            }
        };
        TextWatcher twe = new TextWatcher()
        {
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void afterTextChanged(Editable s) {}
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(s.toString().length() >1) setText(e,""+s.toString().charAt(start));
            }
        };
        TextWatcher twf = new TextWatcher()
        {
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void afterTextChanged(Editable s) {}
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(s.toString().length() >1) setText(f,""+s.toString().charAt(start));
            }
        };
        TextWatcher twg = new TextWatcher()
        {
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void afterTextChanged(Editable s) {}
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(s.toString().length() >1) setText(g,""+s.toString().charAt(start));
            }
        };
        TextWatcher twh = new TextWatcher()
        {
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void afterTextChanged(Editable s) {}
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(s.toString().length() >1) setText(h,""+s.toString().charAt(start));
            }
        };

        a.addTextChangedListener(twa);
        b.addTextChangedListener(twb);
        c.addTextChangedListener(twc);
        d.addTextChangedListener(twd);
        e.addTextChangedListener(twe);
        f.addTextChangedListener(twf);
        g.addTextChangedListener(twg);
        h.addTextChangedListener(twh);

    }

    void setText(EditText et, String text)
    {
        et.setText(text);
    }




    void sendData(String one, String two)
    {
        if (mac.equalsIgnoreCase(""))
        {
            Toast.makeText(context,"Select a device by clicking bluetooth button!",Toast.LENGTH_LONG).show();
            return;
        }
        BluetoothDevice bluetoothDevice = ad.getRemoteDevice(mac);
        try
        {
            mmSocket = bluetoothDevice.createRfcommSocketToServiceRecord(java.util.UUID.fromString(UUID));
            mmSocket.connect();

            mmSocket.getOutputStream().write(one.getBytes());
            mmSocket.close();

            mmSocket.connect();
            mmSocket.getOutputStream().write(two.getBytes());
            mmSocket.close();
        } catch (IOException e)
        {
            Toast.makeText(context,"Could not send data! "+e.getMessage(),Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(context, "There's no way going back!", Toast.LENGTH_LONG).show();
    }
}
