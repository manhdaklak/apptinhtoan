package com.example.chuongtrinhtinhtoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    ImageView img_icon_1, img_icon_2;
    Animation animation, animation_1;
    Button btnCong, btnTru, btnNhan, btnChia;
    EditText edtA, edtB;
    TextView txtResult;

    private ServiceConnection serviceConnection;

    private boolean isConnected;
    private MyService myService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        connectService();
        img_icon_1 = findViewById(R.id.img_icon_1);
        img_icon_2 = findViewById(R.id.img_icon_2);


        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        animation_1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotate);
        img_icon_1.startAnimation(animation);
        img_icon_2.startAnimation(animation_1);
    }
    private void connectService() {

        Intent intent = new Intent(this, MyService.class);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyService.MyBinder myBinder = (MyService.MyBinder) service;

                myService = myBinder.getService();
                isConnected = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isConnected = false;
                myService = null;
            }
        };
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    void initView()
    {

        btnCong = findViewById(R.id.btnAdd);
        btnTru = findViewById(R.id.btnTru);
        btnNhan = findViewById(R.id.btnNhan);
        btnChia = findViewById(R.id.btnChia);
        edtA = (EditText) findViewById(R.id.edtA);
        edtB = (EditText) findViewById(R.id.edtB);
        txtResult = findViewById(R.id.txtKetqua);
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected){
                    return;
                }

                int result = myService.add(
                        Integer.parseInt(edtA.getText().toString()),
                        Integer.parseInt(edtB.getText().toString()));

                //txtResult.setText(result);
                Toast.makeText(myService, "Result:" + result, Toast.LENGTH_SHORT).show();

            }
        });
        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected){
                    return;
                }
                if(edtB.getText() == null ||Integer.parseInt(edtA.getText().toString())==0){
                    Toast.makeText(myService, "Error", Toast.LENGTH_SHORT).show();
                }
                int result = myService.chia(
                        Integer.parseInt(edtA.getText().toString()),

                        Integer.parseInt(edtB.getText().toString()));

                Toast.makeText(myService, "Result:" + result, Toast.LENGTH_SHORT).show();

            }
        });
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected){
                    return;
                }

                int result = myService.tru(
                        Integer.parseInt(edtA.getText().toString()),
                        Integer.parseInt(edtB.getText().toString()));

                Toast.makeText(myService, "Result:" + result, Toast.LENGTH_SHORT).show();

            }
        });
        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected){
                    return;
                }

                int result = myService.nhan(
                        Integer.parseInt(edtA.getText().toString()),
                        Integer.parseInt(edtB.getText().toString()));

                Toast.makeText(myService, "Result:" + result, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }
}