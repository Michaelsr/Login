package com.upeu.msr.examen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText e1,e2;
    Button bbt;

    DatabaseHelper db;

    TextView sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        e1 = (EditText)findViewById(R.id.user);
        e2 = (EditText)findViewById(R.id.password_login);
        bbt = (Button)findViewById(R.id.SignIn);
        bbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String password = e2.getText().toString();
                Boolean emailpassword = db.emailpassword(email,password);
                if (emailpassword == true){
                    Toast.makeText(getApplicationContext(),"Login", Toast.LENGTH_LONG).show();
                    Intent menu = new Intent(Login.this, MenuUser.class);
                    startActivity(menu);

                }else{
                    Toast.makeText(getApplicationContext(),"EROR email or password", Toast.LENGTH_LONG).show();
                }
            }
        });

        sign=(TextView) findViewById(R.id.SignUp);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regis = new Intent(Login.this, Registro.class);
                startActivity(regis);
            }
        });

    }
}
