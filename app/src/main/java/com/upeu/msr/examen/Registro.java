package com.upeu.msr.examen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
    DatabaseHelper db;
    EditText e1,e2,e3,e4;
    Button btnn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        db = new DatabaseHelper(this);
        e1 = (EditText)findViewById(R.id.name);
        e2 = (EditText)findViewById(R.id.email);
        e3 = (EditText)findViewById(R.id.password);
        e4 = (EditText)findViewById(R.id.confirm);

        btnn = (Button)findViewById(R.id.resgister);
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                if (s1.equals("") ||s2.equals("") ||s3.equals("") ||s4.equals("")){
                    Toast.makeText(getApplicationContext(),"los campos están vacios", Toast.LENGTH_LONG).show();

                }else{
                    if (s3.equals(s4)){
                        Boolean chkeemail = db.chkeemail(s2);
                        if (chkeemail==true){
                            Boolean insert = db.insert(s1,s2,s3);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(),"registrado",Toast.LENGTH_LONG).show();
                                Intent regreso = new Intent(Registro.this, Login.class);
                                startActivity(regreso);
                            }
                        }else {
                            Toast.makeText(getApplicationContext(),"Email existe",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });


    }
}
