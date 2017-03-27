package com.love.lixinxin.pop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TicketVerificationDetailPop pop;

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(this);

        pop = new TicketVerificationDetailPop(this);

    }

    @Override
    public void onClick(View v) {
        pop.showPop(btn);
    }
}
