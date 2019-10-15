package com.yaoh.jetpack.view_model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yaoh.jetpack.R;


public class ViewModelActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ViewModelActivity";

    private static MutableLiveData<String> mLivaData = new MutableLiveData<>();


    private LiveDataTimeViewModel mLiveDataTimeViewModel;

    private Button btn_subscribe;
    private Button btn_send;

    private int mCount;

    private Handler mHand = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);

        btn_subscribe = findViewById(R.id.btn_subscribe);
        btn_subscribe.setOnClickListener(this);
        btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);

        mLiveDataTimeViewModel = ViewModelProviders.of(this).get(LiveDataTimeViewModel.class);
        Log.e(TAG, "mLiveDataTimeViewModel111: " + mLiveDataTimeViewModel
                + " timeInfo: " + mLiveDataTimeViewModel.getTimeInfo().getValue());

        subscribe();
    }

    private void subscribe() {
//        mLiveDataTimeViewModel.getTimeInfo().observe(this, new Observer<TimeInfo>() {
//            @Override
//            public void onChanged(TimeInfo timeInfo) {
//                Log.e(TAG, "onChanged---> timeInfo: " + timeInfo);
//            }
//        });

        LiveDataBus.get().with("message", String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Log.e(TAG, "onChanged111---> " + s);
                    }
                });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_subscribe) {
            subscribe();
        } else if (id == R.id.btn_send) {
            send();

            mHand.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(ViewModelActivity.this, ViewModel2Activity.class);
                    startActivity(intent);
                }
            }, 1000);
        }
    }

    private void send() {
//        TimeInfo timeInfo = new TimeInfo();
//        timeInfo.setCount(mCount++);
//        mLiveDataTimeViewModel.setTimeInfo(timeInfo);

//        mLivaData.setValue("Hello");
        LiveDataBus.get().with("message").setValue("hello-------------->");
    }

}
