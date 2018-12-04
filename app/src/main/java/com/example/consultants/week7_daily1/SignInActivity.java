package com.example.consultants.week7_daily1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    public static final String TAG = SignInActivity.class.getSimpleName() + "_TAG";
    public static final String PrefKey = "MySharedPref";

    PrefManager prefManager;

    private EditText etPassword;
    private EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Log.d(TAG, "onCreate: ");
        onBindView();

        prefManager = new PrefManager(getApplicationContext());

//        Check the intent -> receive signup success email
        Intent receiveSignUp = getIntent();
        if (receiveSignUp.getExtras() != null) {
            etEmail.setText(receiveSignUp.getStringExtra("Email"));
        }
    }

    public void onBindView() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignIn:
                attemptLogin();
                break;

            case R.id.btnSignUp:
                Log.d(TAG, "onClick: ");
                Intent intentSignUp = new Intent(getApplicationContext(), SignUpActivity.class);
                intentSignUp.putExtra("Email", etEmail.getText().toString());
                startActivity(intentSignUp);
                break;
        }
    }

    public void attemptLogin() {
        if (!etEmail.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()) {
            if (prefManager.checkLogin(etEmail.getText().toString(), etPassword.getText().toString())) {
                Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
                intentLogin.putExtra("Email", etEmail.getText().toString());
                startActivity(intentLogin);
            } else {
                Toast.makeText(this, "Fail to Login", Toast.LENGTH_LONG).show();
                etPassword.setText("");
            }
        }
    }
}
