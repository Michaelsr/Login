package com.upeu.msr.examen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


public class Login extends AppCompatActivity{private SignInButton mGoogleSignInButton;
    private GoogleApiClient mGoogleApiClient;

    EditText e1,e2;
    Button bbt;

    DatabaseHelper db;

    TextView sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*LOGIN NORMAL*/
        db = new DatabaseHelper(this);
        e1 = (EditText) findViewById(R.id.user);
        e2 = (EditText) findViewById(R.id.password_login);
        bbt = (Button) findViewById(R.id.SignIn);
        bbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String password = e2.getText().toString();
                Boolean emailpassword = db.emailpassword(email, password);
                if (emailpassword == true) {
                    Toast.makeText(getApplicationContext(), "Login", Toast.LENGTH_LONG).show();
                    Intent menu = new Intent(Login.this, MenuUser.class);
                    startActivity(menu);

                } else {
                    Toast.makeText(getApplicationContext(), "EROR email or password", Toast.LENGTH_LONG).show();
                }
            }
        });

        sign = (TextView) findViewById(R.id.SignUp);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regis = new Intent(Login.this, Registro.class);
                startActivity(regis);
            }
        });
            /**Login GOOGLE**/
        mGoogleSignInButton = (SignInButton) findViewById(R.id.google_sign_in_button);
        mGoogleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();

            }

        });

    }

    private void checkInitialized() {
    }

    private void handleSignInResult(Object o) {
    }


    private static final int RC_SIGN_IN = 9001;

    private void signInWithGoogle() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        final Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                    }
                });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(result.isSuccess()) {
                final GoogleApiClient client = mGoogleApiClient;
                result.getSignInAccount();
            } else {

                startActivity(new Intent(this,MenuUser.class));
                //handleSignInResult(...);
            }
        } else {
            // Handle other values for requestCode
        }
    }









}
