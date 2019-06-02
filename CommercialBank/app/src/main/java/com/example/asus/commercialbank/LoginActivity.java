package com.example.asus.commercialbank;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    //Declared Login Variables
    String  Username_login,Password_login;
    EditText username_login,password_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDb = new DatabaseHelper(this);
    }

    public void toRegister(View v) {
        Toast.makeText(this,"Directing to Register Page",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }



    public void Login (View v){

        //Capturing the Login fields using Ids
        username_login = findViewById(R.id.username_log);
        password_login = findViewById(R.id.password_log);

        Username_login= username_login.getText().toString();
        Password_login = password_login.getText().toString();

        if(validateLoginText()){ // checking for login field validations

            User user = new User(Username_login,Password_login); //creating an User object
            boolean res = myDb.validateLogin(user); //calling DB method
            if(res){
                Toast.makeText(this,"Logged In ",Toast.LENGTH_LONG).show();

                Cursor cursor = myDb.getUserClass(Username_login); // fetching the user class related to username
                User user1 = new User() ;

                while (cursor.moveToNext()){ //assigning the user details to user class (Acts as a session class)
                    user1.setUserId(cursor.getInt(0));
                    user1.setFirstName(cursor.getString(1));
                    user1.setLastName(cursor.getString(2));
                    user1.setAccountNumber(cursor.getInt(3));
                    user1.setSecurityCode(cursor.getInt(4));
                    user1.setEmail(cursor.getString(5));
                    user1.setUsername(cursor.getString(6));
                    user1.setBalance(cursor.getInt(8));

                }

                //successful login redirects to the new HomeActivity
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("user_session", user1);
                startActivity(intent);

            }
            else{
                Toast.makeText(this,"Login Failed, Try Again",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }

        }
        else {
            Toast.makeText(this,"Not Validated",Toast.LENGTH_LONG).show();
        }


    }

    public  boolean validateLoginText(){

        int white = ContextCompat.getColor(getApplicationContext(), R.color.white);
        String errorString = "This field cannot be empty";  // Your custom error message.

        String errorString3 = "Enter only Numbers";
        String errorString4 = "Enter only Letters";

        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(white);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);


        SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder(errorString3);
        spannableStringBuilder3.setSpan(foregroundColorSpan, 0, errorString3.length(), 0);

        SpannableStringBuilder spannableStringBuilder4 = new SpannableStringBuilder(errorString4);
        spannableStringBuilder4.setSpan(foregroundColorSpan, 0, errorString4.length(), 0);



        if(Username_login.isEmpty()){
            username_login.setError(spannableStringBuilder);
            return false;
        }
        if(Password_login.isEmpty()){
            password_login.setError(spannableStringBuilder);
            return false;
        }

        return true;
    }
}
