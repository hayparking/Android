package com.example.taskplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.taskplanner.Model.User;
import com.example.taskplanner.Service.UserService;
import com.example.taskplanner.util.RetrofitHttp;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Response;

public class Register extends AppCompatActivity {

    Button registry;
    EditText name, last_name, email, password;

    private RetrofitHttp retrofitHttp;
    private static UserService userService;
    private final ExecutorService executorService =
            Executors.newFixedThreadPool(1);
    String nameT;
    String lastNameT;
    String emailT;
    String passwordT;
    String passwordCOnfirmT;
    String direccionT;
    Intent myIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userService = retrofitHttp.getRetrofit().create(UserService.class);

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
                else {
                    nameT = name.getText().toString();
                    lastNameT = last_name.getText().toString();
                    emailT = email.getText().toString();
                    passwordT = password.getText().toString();
                    passwordCOnfirmT = password.getText().toString();
                    direccionT = "calle 80";
                    register(v);
                    //startActivity(new Intent(v.getContext(), HomeActivity.class));
                    Intent myIntent = new Intent(v.getContext(), HomeActivity.class);
                }
            }
        });
    }

    public void register (final View view){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    User userNew = new User(nameT,lastNameT,direccionT,emailT,passwordT,passwordCOnfirmT);
                    Response<User> userResponse = userService.createUser(userNew).execute();
                    if (userResponse.isSuccessful()) {
                        startActivity(myIntent);
                    }
                    Log.d("holaaa usuario ", "onClick: "+ userResponse.body().getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
