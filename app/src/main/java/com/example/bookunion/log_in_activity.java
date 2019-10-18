package com.example.bookunion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class log_in_activity extends AppCompatActivity implements View.OnClickListener {

    private EditText loginForEmail, loginForPassword;
    GoogleSignInClient mGoogleSignInClient;
    private Button loginForSuccess, loginForRegistration;
    private FirebaseAuth mAuth;
    SignInButton signInButton;
    TextView forgotPassword;
    ProgressDialog pd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        loginForEmail= findViewById(R.id.user_EmailLogin);
        loginForPassword = findViewById(R.id.user_PasswordLogin);

       loginForSuccess = findViewById(R.id.user_buttonLogin);
       loginForRegistration = findViewById(R.id.register_button);

       loginForRegistration.setOnClickListener( this);
       loginForSuccess.setOnClickListener(this);

       signInButton= findViewById(R.id.google_button);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        //Button button = (Button) findViewById(R.id.register_button);

       /** button.setOnClickListener(
            new Button.OnClickListener(){
                public void onClick(View view){
                    Intent intent = new Intent(log_in_activity.this, user_registration_activity.class);
                    startActivity(intent);
                }
        });*/

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        forgotPassword = findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecoverPasswordDialog();
            }
        });


    }
    //dialog for recovering password
    public void showRecoverPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Forgot Password");

        //set a linear layout for the dialog
        LinearLayout linearLayout = new LinearLayout(this);
        final EditText editText = new EditText(this);
        editText.setHint("Email");
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        editText.setMinEms(10);

        linearLayout.addView(editText);
        linearLayout.setPadding(10, 10, 10, 10);
        builder.setView(linearLayout);
        pd = new ProgressDialog(this);

        //buttons recover
        builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                String email = editText.getText().toString().trim();
                //progress box showing

                pd.setMessage("Sending Email...");
                pd.show();
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(log_in_activity.this, "Email Sent", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(log_in_activity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    //sign in for google client
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately

                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Login successful" ,Toast.LENGTH_SHORT).show();
                           // Intent intent = new Intent(log_in_activity.this, user_account_acitvity.class);
                            Intent intent = new Intent(log_in_activity.this, activity_user_profile.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Login failed" ,Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.user_buttonLogin:
                loginUpdate();
                break;

            case R.id.register_button:
                Intent intent = new Intent(log_in_activity.this, user_registration_activity.class);
                startActivity(intent);
                break;

        }
    }

    private void loginUpdate() {

        //for authentication
        String emailL = loginForEmail.getText().toString().trim();
        String passL = loginForPassword.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(emailL,passL).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(log_in_activity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                            //Intent intent = new Intent(log_in_activity.this, user_account_acitvity.class);
                            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            //startActivity(intent);
                            //finish();

                            Intent intent = new Intent(log_in_activity.this, activity_user_profile.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(),"Login failed", Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });

    }
}
