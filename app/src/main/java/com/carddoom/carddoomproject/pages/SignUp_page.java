package com.carddoom.carddoomproject.pages;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.carddoom.carddoomproject.R;
import com.carddoom.carddoomproject.databinding.ActivitySignUpPageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUp_page extends AppCompatActivity {
    ActivitySignUpPageBinding b;
   private String phone;
   private String verificationCodeBySystem;
   private String codeByUser;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivitySignUpPageBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

          // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();







        b.textSpacerNoTitle.setOnClickListener(view -> {
            phone = b.etxtPhone.getEditText().getText().toString().trim();

            checkInputs(phone);
            if (phone != null ){

                sendVerificationCodeToUser(phone);

            }
        });

        b.btnSignUp.setOnClickListener(view -> {
            codeByUser = b.etxtCode.getEditText().getText().toString().trim();
            checkInputs(codeByUser);
            if (codeByUser != null ){
                verifyCode(codeByUser);

            }
        });




    }



    private String checkInputs(String input){


        if (!input.matches("")) {
            return input;
        }
        Toast.makeText(this, "هناك حقول ناقصة", Toast.LENGTH_SHORT).show();
        return null;
    }

    private void sendVerificationCodeToUser(String phoneNo) {


        PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationCodeBySystem = s;
                    }
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        String code = phoneAuthCredential.getSmsCode();
                        if (code != null) {
                        //    progressBar.setVisibility(View.VISIBLE);
                            verifyCode(code);
                        }
                    }
                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(SignUp_page.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                };

        // [START start_phone_auth]
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+967" + phoneNo)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        // [END start_phone_auth]



    }

    private void verifyCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, code);
        signInTheUserByCredentials(credential);
    }

    private void signInTheUserByCredentials(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(SignUp_page.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(SignUp_page.this, "Your Account has been created successfully!", Toast.LENGTH_SHORT).show();

                            //Perform Your required action here to either let the user sign In or do something required
                            Intent intent = new Intent(getApplicationContext(), Home_page.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUp_page.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }








}