package com.yaoh.jetpack.viewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yaoh.jetpack.R;


public class ViewModelActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ViewModelActivity";

    private Button btn_subscribe;
    private Button btn_send;
    private Button btn_unSubscribe;

    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);

        btn_subscribe = findViewById(R.id.btn_subscribe);
        btn_subscribe.setOnClickListener(this);

        btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);

        btn_unSubscribe = findViewById(R.id.btn_unSubscribe);
        btn_unSubscribe.setOnClickListener(this);

        subscribe();
    }

    private void subscribe() {
        LiveDataBus.get().with("message", String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Log.e(TAG, "onReceive ===> " + s);
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
//            mMainHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    send();
////                    Intent intent = new Intent(ViewModelActivity.this, ViewModel2Activity.class);
////                    startActivity(intent);
//                }
//            }, 1000);
        } else if (id == R.id.btn_unSubscribe) {
            unSubscribe();
        }
    }

    private void send() {
        LiveDataBus.get().with("message").setValue("this is a message ===>");
    }

    /**
     * 取消订阅
     */
    private void unSubscribe() {
        LiveDataBus.get().with("message").removeObservers(this);
    }

}
