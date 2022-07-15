package com.example.ss10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ss10.database.AppDatabase;
import com.example.ss10.database.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edUser, edDes;
    Spinner spinner;
    CheckBox ckAgree;
    Button btRegister;
    String gender = "Male";
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getAppDatabase(this);
        ckAgree = findViewById(R.id.ck);
        edUser = findViewById(R.id.edUser);
        edDes = findViewById(R.id.edDes);
        spinner = findViewById(R.id.spinner);
        btRegister = findViewById(R.id.btRegister);
        btRegister.setOnClickListener(this);

        String[] listGender = {"Male", "Female", "Unknow"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, listGender);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TAG", "onItemSelected: " + listGender[i]);
                gender = listGender[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void onRegister() {
        if (validate()) {
            User user = new User();
            user.setUsername(edUser.getText().toString());
            user.setDes(edDes.getText().toString());
            user.setGender(gender);
            long id = db.userDao().insertUser(user);
            if(id > 0) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            }
            goToListUser();
        }
    }

    private void goToListUser() {
        Intent intent = new Intent(this, ListUserActivity.class);
        startActivity(intent);
    }

    private boolean validate() {
        String mes = null;
        if (edUser.getText().toString().trim().isEmpty()) {
            mes = "Chua nhap Username";
        } else if (edDes.getText().toString().trim().isEmpty()) {
            mes = "Chua nhap gioi thieu";
        } else if (!ckAgree.isChecked()) {
            mes = "Ban phai dong y dieu khoan su dung";
        }
        if (mes != null) {
            Toast.makeText(this, mes, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btRegister) {
            onRegister();
        }
    }
}