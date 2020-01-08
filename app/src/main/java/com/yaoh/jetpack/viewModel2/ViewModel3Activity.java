package com.yaoh.jetpack.viewModel2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.yaoh.jetpack.R;
import com.yaoh.jetpack.log.LogTool;


/**
 * @author yaoh
 * @date Create in 2020-01-08 14:03
 * @description  由于LiveDataViewModel 是和当前的activity有关
 *                解决办法-> livaDataBus 或 static livedata
 */
public class ViewModel3Activity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ViewModel3Activity";

    private LiveDataViewModel mViewModel;

    private Button btn_subscribe;
    private Button btn_unsubscribe;
    private Button btn_sendMessage;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model3);

        btn_subscribe = findViewById(R.id.btn_subscribe);
        btn_subscribe.setOnClickListener(this);

        btn_unsubscribe = findViewById(R.id.btn_unsubscribe);
        btn_unsubscribe.setOnClickListener(this);

        btn_sendMessage = findViewById(R.id.btn_sendMessage);
        btn_sendMessage.setOnClickListener(this);

        mViewModel = ViewModelProviders.of(this).get(LiveDataViewModel.class);
        LogTool.LogE_DEBUG(TAG, " mViewModel111---> " + mViewModel);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_subscribe) {
            subscribe();
        } else if (id == R.id.btn_unsubscribe) {
            unsubscribe();
        } else if (id == R.id.btn_sendMessage) {
            sendMessage();
        }
    }

    private void subscribe() {
        LogTool.LogE_DEBUG(TAG, "subscribe--->");
        mViewModel.getMessageData().observe(this, new Observer<MessageInfo>() {
            @Override
            public void onChanged(MessageInfo messageInfo) {
                LogTool.LogE_DEBUG("ViewModel3Activity", "onChanged---> messageInfo: " + messageInfo);
            }
        });
    }

    private void unsubscribe() {
        LogTool.LogE_DEBUG(TAG, "unsubscribe--->");
        mViewModel.getMessageData().removeObservers(this);
    }

    private void sendMessage() {
        startActivity(new Intent(ViewModel3Activity.this, ViewModel4Activity.class));
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setMessageId(1);
                messageInfo.setContent("this is a message");
                mViewModel.getMessageData().setValue(messageInfo);

            }
        }, 2000);
    }

}
