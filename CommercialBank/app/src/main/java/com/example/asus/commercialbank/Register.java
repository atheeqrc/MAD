package com.example.asus.commercialbank;

import android.app.AlertDialog;
import android.app.LauncherActivity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DatabaseHelper myDb;

    //Declared Register Variables
   private EditText firstName_reg,lastName_reg,accountNumber_reg,securityCode_reg,email_reg,username_reg,password_reg,confPassword_reg;
   private String Firstname_reg,Lastname_reg,AccountNumber_reg,SecurityCode_reg,Email_reg,Username_reg,Password_reg,ConfPassword_reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        myDb = new DatabaseHelper(this);
    }

    public void register (View v){
        //Capturing the register fields using Ids
         firstName_reg = findViewById(R.id.firstName_reg);
         lastName_reg = findViewById(R.id.lastName_reg);
         accountNumber_reg = findViewById(R.id.accountNumber_reg);
         securityCode_reg = findViewById(R.id.securityCode_reg);
         email_reg = findViewById(R.id.email_reg);
         username_reg = findViewById(R.id.username_reg);
         password_reg = findViewById(R.id.pass_reg);
         confPassword_reg = findViewById(R.id.passConf_reg);


         Firstname_reg = firstName_reg.getText().toString();
         Lastname_reg = lastName_reg.getText().toString();
         AccountNumber_reg = accountNumber_reg.getText().toString();
         SecurityCode_reg = securityCode_reg.getText().toString();
         Email_reg = email_reg.getText().toString();
         Username_reg = username_reg.getText().toString();
         Password_reg = password_reg.getText().toString();
         ConfPassword_reg = confPassword_reg.getText().toString();

        if(validateRegister()){ //validates the credential
            //true
            Toast.makeText(this,"Validated Successfully",Toast.LENGTH_LONG).show();
            User user = new User(Firstname_reg,Lastname_reg,Integer.parseInt(AccountNumber_reg),Integer.parseInt(SecurityCode_reg),Email_reg,Username_reg,Password_reg); //encapsulating as an object

            boolean res = myDb.insertNewUser(user); // calling DB function
            if(res){
                //true
                Toast.makeText(this,"Registered Successfully",Toast.LENGTH_LONG).show();
                //redirect to New activity "Login"
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);

            }
            else{
                //false
                Toast.makeText(this,"Registration Failed",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, Register.class);
                startActivity(intent);
            }

        }
        else {
            //false
            Toast.makeText(this,"Not Validated",Toast.LENGTH_LONG).show();
        }

    }

    public boolean  validateRegister(){

        int white = ContextCompat.getColor(getApplicationContext(), R.color.white);
        String errorString = "This field cannot be empty";  // Your custom error message.
        String errorString1 = "Confirm Correctly";
        String errorString2 = "Enter a valid Email";
        String errorString3 = "Enter only Numbers";
        String errorString4 = "Enter only Letters";
        String errorString5 = "Username already exists, Enter another ";
        String errorString6 = "Account Number already exists, Enter another ";
        String errorString7 = "Please Enter 8 digit number ";
        String errorString8 = "Please Enter 4 digit number";

        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(white);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);

        SpannableStringBuilder spannableStringBuilder1 = new SpannableStringBuilder(errorString1);
        spannableStringBuilder1.setSpan(foregroundColorSpan, 0, errorString1.length(), 0);

        SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(errorString2);
        spannableStringBuilder2.setSpan(foregroundColorSpan, 0, errorString2.length(), 0);

        SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder(errorString3);
        spannableStringBuilder3.setSpan(foregroundColorSpan, 0, errorString3.length(), 0);

        SpannableStringBuilder spannableStringBuilder4 = new SpannableStringBuilder(errorString4);
        spannableStringBuilder4.setSpan(foregroundColorSpan, 0, errorString4.length(), 0);

        SpannableStringBuilder spannableStringBuilder5 = new SpannableStringBuilder(errorString5);
        spannableStringBuilder5.setSpan(foregroundColorSpan, 0, errorString5.length(), 0);

        SpannableStringBuilder spannableStringBuilder6 = new SpannableStringBuilder(errorString6);
        spannableStringBuilder6.setSpan(foregroundColorSpan, 0, errorString6.length(), 0);

        SpannableStringBuilder spannableStringBuilder7 = new SpannableStringBuilder(errorString7);
        spannableStringBuilder7.setSpan(foregroundColorSpan, 0, errorString7.length(), 0);

        SpannableStringBuilder spannableStringBuilder8 = new SpannableStringBuilder(errorString8);
        spannableStringBuilder8.setSpan(foregroundColorSpan, 0, errorString8.length(), 0);



        if(Firstname_reg.isEmpty()){
            firstName_reg.setError(spannableStringBuilder);

            return false;
        }
        if(Lastname_reg.isEmpty()){
            lastName_reg.setError(spannableStringBuilder);
            return false;
        }
        if(AccountNumber_reg.isEmpty()){
            accountNumber_reg.setError(spannableStringBuilder);
            return false;
        }
        if(SecurityCode_reg.isEmpty()){
            securityCode_reg.setError(spannableStringBuilder);
            return false;
        }
        if(Username_reg.isEmpty()){
            username_reg.setError(spannableStringBuilder);
            return false;
        }
        if(Email_reg.isEmpty()){
            email_reg.setError(spannableStringBuilder);
            return false;
        }

        if(Password_reg.isEmpty()){
            password_reg.setError(spannableStringBuilder);
            return false;
        }
        if(ConfPassword_reg.isEmpty()){
            confPassword_reg.setError(spannableStringBuilder);
            return false;
        }
        if(!Password_reg.isEmpty() && !ConfPassword_reg.isEmpty()){

            if(!(Password_reg.equals(ConfPassword_reg))){
                Toast.makeText(this,"Please Confirm the password correctly",Toast.LENGTH_LONG).show();
                password_reg.setError(spannableStringBuilder1);
                confPassword_reg.setError(spannableStringBuilder1);
                return false;
            }

        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Email_reg).matches()){
            email_reg.setError(spannableStringBuilder2);
            return false;
        }


        if(!Firstname_reg.matches("[a-zA-Z]+")){
            firstName_reg.setError(spannableStringBuilder4);
            return false;
        }

        if(!Lastname_reg.matches("[a-zA-Z]+")){
            lastName_reg.setError(spannableStringBuilder4);
            return false;
        }

        if(!AccountNumber_reg.matches("[0-9]+")){
            accountNumber_reg.setError(spannableStringBuilder4);
            return false;
        }
        if(!SecurityCode_reg.matches("[0-9]+")){
            securityCode_reg.setError(spannableStringBuilder4);
            return false;
        }
        if(!myDb.isValidUniqueUsername(Username_reg)){
            username_reg.setError(spannableStringBuilder5);
            return false;
        }

        if(!myDb.isValidUniqueAccountNumber(Integer.parseInt(AccountNumber_reg))){
            accountNumber_reg.setError(spannableStringBuilder6);
            return false;
        }

        if (!AccountNumber_reg.matches("\\d{8,8}")){
            accountNumber_reg.setError(spannableStringBuilder7);
            return false;
        }

        if (!SecurityCode_reg.matches("\\d{4,4}")) {
            securityCode_reg.setError(spannableStringBuilder8);
            return false;
        }
        return true;
    }




}
