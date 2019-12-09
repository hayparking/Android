package com.example.taskplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private RetrofitHttp retrofitHttp;
    private static UserService userService;
    private final ExecutorService executorService =
            Executors.newFixedThreadPool(1);
    Button login;
    EditText email, password;
    TextView registry;

    Intent intent;

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

                    //isEmailValid(email.getText().toString());
                }
                if (password.length() == 0){
                    password.setError("Ingrese contraseña");
                }
                if (email.length() != 0 && password.length() != 0) {
                    login(email.getText().toString(), password.getText().toString());

                    intent = new Intent(getApplicationContext(),MapsActivity.class);
                }
            }
        });


    }

    public void login (final String email, final String passwd){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    userService = retrofitHttp.getRetrofit().create(UserService.class);
                    Response<User> userResponse = userService.getUserByEmail(email).execute();
                    if (userResponse.isSuccessful()) {
                        User user = userResponse.body();
                        if (user.getPassword().equals(passwd)) {
                            startActivity(intent);
                        }
                        else {
                            password.setError("Contraseña o Usuario erroneo");
                        }
                        Log.d("nombre usuario",user.getName());
                    }
                    else {
                        password.setError("Contraseña o Usuario erroneo");
                    }
                    Log.d("holaaa usuario ", "onClick: "+ userResponse.body().getEmail());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
