package ac.za.cput.forgetpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class ForgotPassword extends AppCompatActivity {
    private EditText mforgotpassword;
    private Button mpasswordrecoverbutton;
    private TextView mgobacktologin;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        getSupportActionBar().hide();

        mforgotpassword=findViewById(R.id.forgotpassword);
        mpasswordrecoverbutton=findViewById(R.id.passwordrecoverbutton);
        mgobacktologin=findViewById(R.id.gobacktologin);

        firebaseAuth= FirebaseAuth.getInstance();


        mgobacktologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ForgotPassword.this,MainActivity.class);
                startActivity(intent);
            }
        });

        mpasswordrecoverbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=mforgotpassword.getText().toString().trim();
                if(mail.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter your mail first",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //we have to send password recover email

                    firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),
                                        "Mail Sent , You can recover your password using mail",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ForgotPassword.this,MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),
                                        "Email is Wrong or Account Not Exist",Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

                }
            }
        });


    }
}

forgotpassword.xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".ForgotPassword">

<RelativeLayout
        android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:id="@+id/centerline3"
                android:layout_centerInParent="true">

</RelativeLayout>
<LinearLayout
        android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginLeft="30dp"
                android:layout_marginRight="30dp"
                android:layout_above="@id/centerline3"
                android:layout_marginBottom="120dp"
                android:orientation="vertical">

<TextView
            android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:text="Forgot Password"
                    android:textSize="35sp"
                    android:textColor="@color/black"
                    android:textStyle="bold"
                    ></TextView>
<TextView
            android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="Dont worry we are here"
                    android:textSize="23sp"
                    android:textColor="@color/black">

</TextView>
</LinearLayout>
<EditText
        android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginLeft="30dp"
                android:layout_marginRight="30dp"
                android:layout_above="@id/centerline3"
                android:id="@+id/forgotpassword"
                android:hint="Enter your registered mail"
                android:layout_marginBottom="10dp">

</EditText>
<Button
        android:layout_width="match_parent"
                android:layout_height="50dp"
                android:text="Click here to recover"
                android:id="@+id/passwordrecoverbutton"
                android:layout_below="@id/centerline3"
                android:layout_marginTop="10dp"
                android:layout_marginLeft="30dp"
                android:layout_marginRight="30dp">

</Button>

<TextView
        android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_alignParentBottom="true"
                android:text="Go Back to Login Screen"
                android:id="@+id/gobacktologin"
                android:layout_marginBottom="30dp"
                android:textSize="20sp"
                android:textColor="@color/black"
                android:textAlignment="center">

</TextView>
</RelativeLayout>

