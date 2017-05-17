package com.mmd.welcome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;

public class WelcomeActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tv=(TextView)findViewById(R.id.tv_welcome);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/login/LoginActivity").navigation();
            }
        });
    }

}
