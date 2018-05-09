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
                String newEntry = codigo.getText().toString();
                if(codigo.length()!= 0){
                    AddData(newEntry);
                    codigo.setText("");
                }else{
                    Toast.makeText(Escaner.this, "You must put something in the text field!", Toast.LENGTH_LONG).show();
                }


            }

        });



    }

    private void AddData(String newEntry) {
        boolean insertData = db.addData(newEntry);

        if(insertData==true){
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }

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
