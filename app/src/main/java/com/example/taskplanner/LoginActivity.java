package com.example.taskplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText email, password;
    TextView registry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email    = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        registry = (TextView) findViewById(R.id.registryView);
        login    = (Button) findViewById(R.id.login);

        registry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( view.getContext(), Register.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.length() == 0){
                    email.setError("Ingrese email");
                }
                else if (password.length() == 0 ){
                    password.setError("Ingrese contraseña");
                } else{
                    Intent intent = new Intent (v.getContext(), HomeActivity.class);
                    startActivityForResult(intent, 0);
                }
            }
        });
    }
}
