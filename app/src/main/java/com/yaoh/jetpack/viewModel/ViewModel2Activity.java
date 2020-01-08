package com.yaoh.jetpack.viewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yaoh.jetpack.R;

public class ViewModel2Activity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ViewModel2Activity";

    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model2);

        btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
        subscribe();
    }

    private void subscribe() {
        LiveDataBus.get().with("message", String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Log.e(TAG, "onReveive onChanged222---> " + s);
                    }
                });
    }

    /**
     * 取消订阅
     */
    private void unSubscribe() {
        LiveDataBus.get().with("message").removeObservers(this);
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
        LiveDataBus.get().with("message")
                .setValue("hello------------->222");
    }

}
