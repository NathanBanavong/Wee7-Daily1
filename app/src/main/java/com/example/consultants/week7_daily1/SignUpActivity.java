package com.example.consultants.week7_daily1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO need to 'implement view.onClickListener'
public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = SignUpActivity.class.getSimpleName() + "_TAG";
    //    Checks the input of the password pattern
    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

//    private onClickListener listener;

    private Pattern pattern;
    private Matcher matcher;

    private String userEmail;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        onBindViews();

//        Pass the email used, if inputted any
        Intent intentSignUp = getIntent();
        userEmail = intentSignUp.getStringExtra("Email");
        etEmail.setText(userEmail);
        Log.d(TAG, "onCreate: " + etEmail.getText().toString());
    }

    public void onBindViews() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
    }

    public SignUpActivity() {
        Log.d(TAG, "SignUpActivity: ");
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    public boolean validate(final String password) {
        Log.d(TAG, "validate: " + password);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    //    TODO Need to make this function an 'onClick' -> via listener
    public void onSignUp(View view) {

        Log.d(TAG, "onSignUp: ");
//        Check input is not null
        if (etPassword.getText().toString() != null && etEmail.getText().toString() != null) {
//            make sure password is valid -> return true
            if (validate(etPassword.getText().toString())) {
                Toast.makeText(this, "Password Success!", Toast.LENGTH_LONG).show();

//              TODO need to also reference a sharedpreference to insert input
                new PrefManager(this).saveLoginDetails(etEmail.getText().toString(), etPassword.getText().toString());
                Log.d(TAG, "onSignUp: complete PrefManager");

//                send the user back to the sign in page
                Intent intentSignIn = new Intent(getApplicationContext(), SignInActivity.class);
                intentSignIn.putExtra("Email", etEmail.getText().toString());
                startActivity(intentSignIn);

            } else {
                Toast.makeText(this, "Issue with Password", Toast.LENGTH_LONG).show();
                etPassword.setText("");
            }
        }
    }


    //    checks the clicked button for the SignUp -> return to SignIn
//    @Override
//    public void onClick(View v) {
//        Log.d(TAG, "onClick: ");
//
////        listener.signUpClicked(etPassword.getText().toString());
//
////        Check input is not null
//        if(etPassword.getText().toString() != null && etEmail.getText().toString() != null){
////            make sure password is valid -> return true
//            if(validate(etPassword.getText().toString())){
//                Toast.makeText(this, "Password Success!",Toast.LENGTH_LONG).show();
//
////              TODO need to also reference a sharedpreference to insert input
//
//            }
//            else {
//                Toast.makeText(this, "Issue with Password", Toast.LENGTH_LONG).show();
//                etPassword.setText("");
//            }
//        }
//
//    }

//    public interface onClickListener{
//        void signUpClicked(String password);
//    }
}
