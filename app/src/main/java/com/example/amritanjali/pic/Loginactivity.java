package com.example.amritanjali.pic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Loginactivity extends AppCompatActivity {
    Button button1,button2;
    //TextView text4;
    static EditText e;
    static  EditText e1;
    String s,s1;
   // private Button getButton2;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        e=(EditText)findViewById(R.id.e);
        e1=(EditText)findViewById(R.id.e1);
        //text4=(TextView)findViewById(R.id.text4);

        mAuth = FirebaseAuth.getInstance();
//        text4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //password change link send to mail
//            }
//        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s=String.valueOf(e.getText());
                s1=String.valueOf(e1.getText());


                mAuth.signInWithEmailAndPassword(e.getText().toString(), e1.getText().toString())
                        .addOnCompleteListener(Loginactivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("123", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                  sendtomain();
                                    //   updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("123", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(Loginactivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });


            }
        });




        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent regIntent = new Intent(Loginactivity.this, RegisterActivity.class);
                startActivity(regIntent);

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {

            sendtomain();
        }
        // updateUI(currentUser);
    }

    private void sendtomain() {

        Intent intent= new Intent(Loginactivity.this,MainActivity.class);
        startActivity(intent);
        //finish();
    }
}
