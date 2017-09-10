package com.example.shobhitsagar.codingblocks101;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText firstnmbr;
    EditText scndnmbr;
    Button button;
    Button button2;
    String nmbrOne;
    String nmbrTwo;
    Integer one;
    Integer two;

    String TAG = "MainActivitySensor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager smgr = (SensorManager)getSystemService(SENSOR_SERVICE);

        final LinearLayout parent = (LinearLayout) findViewById(R.id.main_layout);

        for (Sensor sensor : smgr.getSensorList(Sensor.TYPE_ALL)){

            Log.d(TAG, sensor.getName());
            Log.d(TAG, String.valueOf(sensor.getVersion()));
            Log.d(TAG, sensor.getVendor());
            Log.d(TAG, String.valueOf(sensor.getType()));
            Log.d(TAG, "---------------------------------");
        }

        Sensor proximity = smgr.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        smgr.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float sensorVal = sensorEvent.values[0];
                Log.d(TAG, "onSensorChanged : " + sensorVal);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        }, proximity, 1000);

        Sensor accelerometer  = smgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        smgr.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float ax = sensorEvent.values[0];
                float ay = sensorEvent.values[1];
                float az = sensorEvent.values[2];

                Log.d(TAG, "onSensorChanged : " + ax);
                Log.d(TAG, "onSensorChanged : " + ay);
                Log.d(TAG, "onSensorChanged : " + az);

                parent.setBackgroundColor(
                        Color.rgb(getColor(ax),
                                getColor(ay),
                                getColor(az)));
            }
            int getColor(float ax) {
                return (int) (ax + 12 * 255) % 255;
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        }, accelerometer, 50000);

        textView = (TextView) findViewById(R.id.totalTextView);
        firstnmbr = (EditText) findViewById(R.id.firstNmbr);
        scndnmbr = (EditText) findViewById(R.id.SecondNmbr);
        button = (Button) findViewById(R.id.addBtn);
        button2 = (Button) findViewById(R.id.secondActivity);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nmbrOne = firstnmbr.getText().toString();
                nmbrTwo = scndnmbr.getText().toString();

                one = Integer.parseInt(nmbrOne);
                two = Integer.parseInt(nmbrTwo);

                String Total = String.valueOf(one + two);

                textView.setText(Total);

                Toast.makeText(MainActivity.this, Total, Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Total")
                        .setMessage("Sum = "+ Total)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                builder.create().show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);

                Intent serintent = new Intent(MainActivity.this, MyService.class);
                startService(serintent);
            }
        });

    }
}

