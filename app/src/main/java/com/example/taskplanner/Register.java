package com.example.taskplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    Button registry;
    EditText name, last_name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.nameR);
        last_name = (EditText) findViewById(R.id.lastNameR);
        email = (EditText) findViewById(R.id.nameR);
        password = (EditText) findViewById(R.id.passwordR);
        registry = (Button) findViewById(R.id.registry);

        registry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.length() == 0){
                    name.setError("Ingrese un nombre");
                }
                else if( last_name.length() == 0) {
                    last_name.setError("Ingrese una contraseña");
                }
                else if (email.length() == 0){
                    email.setError("Ingrese un email");
                }
                else if (password.length() == 0 ){
                    password.setError("Ingrese una contraseña");
                }
                else if (password.length() < 8){
                    password.setError("contraseña muy corta");
                }
            }
        });
    }
}
