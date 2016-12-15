package trinadhkoya.gihub.io.gettingstartedfcm_chat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String LOG_TAG = MainActivity.class.getCanonicalName();

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @InjectView(R.id.email_edit_text)
    EditText emailEditText;
    @InjectView(R.id.password_edit_text)
    EditText passwordEditText;

    @InjectView(R.id.login_button)
    Button loginButton;
    @InjectView(R.id.register_button)
    Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.inject(this);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                //TODO this
                if (validate()) {
                    letUserLogin();
                }
                break;
            case R.id.register_button:
                //TODO this
                if (validate()) {
                    letUserRegister();
                }
                break;
            default:
                break;
        }

    }

    private void letUserRegister() {

        progressDialog.setMessage("Processing...");
        progressDialog.show();

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));

                } else {
                    Logs.i(LOG_TAG, task.getException().getMessage());
                }

            }
        });


    }


    private void letUserLogin() {
        progressDialog.setMessage("Authenicating");
        progressDialog.show();

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();


        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else {
                    Logs.i(LOG_TAG, task.getException().getMessage());
                    Toast.makeText(getApplicationContext(), "Something went wrong", LENGTH_LONG).show();
                }
            }
        });


    }


    private boolean validate() {
        boolean valid = true;

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Invalid Email");
            valid = false;
        }

        if (password.isEmpty() || password.length() < 6) {
            passwordEditText.setError("Invalid Password");
            valid = false;
        }

        return valid;
    }


}
