package com.yaoh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yaoh.jetpack.R;
import com.yaoh.jetpack.room.RoomActivity;
import com.yaoh.jetpack.view_model.ViewModelActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_test_viewModel;
    private Button btn_test_room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_test_viewModel = findViewById(R.id.btn_test_viewModel);
        btn_test_viewModel.setOnClickListener(this);

        btn_test_room = findViewById(R.id.btn_test_room);
        btn_test_room.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_test_viewModel) {
            startActivity(new Intent(this, ViewModelActivity.class));
        } else if (id == R.id.btn_test_room) {
            startActivity(new Intent(this, RoomActivity.class));
        }
    }


}
