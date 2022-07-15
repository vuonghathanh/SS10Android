package com.example.ss10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ss10.adapter.UserAdapter;
import com.example.ss10.database.AppDatabase;
import com.example.ss10.database.User;

import java.util.List;

public class ListUserActivity extends AppCompatActivity {

    RecyclerView rvUser;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        db = AppDatabase.getAppDatabase(this);
        List<User> list = db.userDao().getAll();

        UserAdapter adapter = new UserAdapter(this, list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvUser = findViewById(R.id.rvUser);
        rvUser.setLayoutManager(layoutManager);
        rvUser.setAdapter(adapter);
    }
}