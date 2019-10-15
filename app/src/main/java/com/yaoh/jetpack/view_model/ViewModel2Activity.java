package com.yaoh.jetpack.view_model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yaoh.jetpack.R;

public class ViewModel2Activity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ViewModel2Activity";

    private LiveDataTimeViewModel mLiveDataTimeViewModel;

    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model2);

        btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);

        mLiveDataTimeViewModel = ViewModelProviders.of(this).get(LiveDataTimeViewModel.class);
        Log.e(TAG, "mLiveDataTimeViewModel222---> " + mLiveDataTimeViewModel
                + " timeInfo: " + mLiveDataTimeViewModel.getTimeInfo().getValue());

        subscribe();
    }

    private void subscribe() {
//        mLiveDataTimeViewModel.getTimeInfo().observe(this, new Observer<TimeInfo>() {
//            @Override
//            public void onChanged(TimeInfo timeInfo) {
//                Log.e("ViewModel2Activity", "onChanged---> timeInfo: " + timeInfo);
//            }
//        });

        LiveDataBus.get().with("message", String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Log.e(TAG, "onChanged222---> " + s);
                    }
                });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_send) {
            send();
        }
    }

    private void send() {
//        TimeInfo timeInfo = new TimeInfo();
//        timeInfo.setCount(100);
//        mLiveDataTimeViewModel.setTimeInfo(timeInfo);
        LiveDataBus.get().with("message").setValue("hello------------->222");
    }

}
