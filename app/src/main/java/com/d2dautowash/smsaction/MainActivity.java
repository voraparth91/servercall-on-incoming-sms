package com.d2dautowash.smsaction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView tvRules = findViewById(R.id.tv_rules);
        if(tvRules!=null){
            StringBuilder sb = new StringBuilder();
            int i=0;
            for (Map.Entry<String, String> entry : Config.SMS_RULES.entrySet()) {
                i++;
                String key = entry.getKey();
                String value = entry.getValue();
                sb.append(i + ") SMS starting with '"+ key + "' sending at " + value + "\n");
            }
            tvRules.setText(sb.toString());
        }
    }
}
