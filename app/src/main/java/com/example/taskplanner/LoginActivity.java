package com.example.taskplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.taskplanner.Model.User;
import com.example.taskplanner.Service.UserService;
import com.example.taskplanner.util.RetrofitHttp;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private RetrofitHttp retrofitHttp;
    private static UserService userService;
    private final ExecutorService executorService =
            Executors.newFixedThreadPool(1);
    Button login;
    EditText email, password;
    TextView registry;
    String Uemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email    = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        registry = (TextView) findViewById(R.id.registryView);
        login    = (Button) findViewById(R.id.login);

        retrofitHttp = new RetrofitHttp();

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
                    Uemail = email.getText().toString();

                    login(Uemail);
                    Intent intent = new Intent (v.getContext(), HomeActivity.class);
                    startActivityForResult(intent, 0);

                    Intent intentt = new Intent(getApplicationContext(),MapsActivity.class);
                    startActivity(intentt);
                }
            }
        });


    }

    public void login (String email){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    userService = retrofitHttp.getRetrofit().create(UserService.class);
                    Response<User> userResponse = userService.getUserByEmail(Uemail).execute();
                    if (userResponse.isSuccessful()) {
                        User user = userResponse.body();
                        Log.d("nombre usuario",user.getName());
                    }
                    Log.d("holaaa usuario ", "onClick: "+ userResponse.body().getEmail());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
