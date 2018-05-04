package com.upeu.msr.examen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Escaner extends AppCompatActivity {
    EditText codigo;
    private ZXingScannerView vistaescaner;


    DatabaseHelper db;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaner);

        db = new DatabaseHelper(this);
        codigo=(EditText)findViewById(R.id.ecaner_text);
        button = (Button)findViewById(R.id.saveCode);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = codigo.getText().toString();
                if (s1.equals("")){
                    Toast.makeText(getApplicationContext(),"los campos están vacios", Toast.LENGTH_LONG).show();

                }else{
                    Boolean insert = db.insertAsistencia(s1);
                    if (insert==true){
                        Toast.makeText(getApplicationContext(),"Insertado", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"ERROR", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });



    }
    public void Escaner(View view){
        vistaescaner = new ZXingScannerView(this);
        vistaescaner.setResultHandler(new zxingscanner());
        setContentView(vistaescaner);
        vistaescaner.startCamera();
    }

    class zxingscanner implements ZXingScannerView.ResultHandler{

        @Override
        public void handleResult(Result result) {
            String dato = result.getText();
            setContentView(R.layout.activity_escaner);
            vistaescaner.stopCamera();
            codigo = (EditText)findViewById(R.id.ecaner_text);
            codigo.setText(dato);
        }
    }

}
