package com.example.administrator.hotfixdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        apkpatch.bat -f NoBug.apk -t Bug.apk -o Dennis -k guofeng.jks -p kui123456kui -a guofeng -e kui123456kui
        Toast.makeText(this, "Bug出现", Toast.LENGTH_SHORT).show();
    }

}
