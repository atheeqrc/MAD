package com.example.asus.commercialbank;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseHelper myDb;
    private User user_session; // user class which acts as the session

    EditText taccno_trans, amount_trans ;                           //fragment_transaction.xml
    EditText pin_pin;                                               //fragment_transaction_pin.xml
    EditText transId_delete;                                    //fragment_transaction_employee.xml
    EditText firstName_update, lastName_update, email_update;       //fragment_profile_update.xml
    EditText oldPin_update, newPin_update, confNewPin_update;       //fragment_profile_update_pin.xml
    EditText oldPass_update, newPass_update, confNewPass_update;    //fragment_profile_update_password.xml
    EditText type_complaint, complaint_complaint;                   //fragment_complaint.xml
    EditText complintId_delete;                                     //fragment_complaint_update.xml
    EditText complaintId_update, type_update, complaint_update;     //fragment_complaint_update.xml
    EditText amount_loan,interest_loan,months_loan;                 //fragment_loancalculator.xml
    EditText type_loan,comments_loan;                               //fragment_loancalculator_apply.xml
    EditText loanid_loan_update,type_loan_update,comments_loan_update;  //fragment_loancalculator_update.xml

    TextView accountNumber_update, accountBalance_update;           //fragment_profile_update.xml
    TextView accno_trans, date_trans;                               //fragment_transaction.xml
    TextView displayName;                                           //nav_header
    TextView username_complaint;                                    //fragment_complaint.xml
    TextView monthly_loan,total_loan;                               //fragment_loancalculator.xml
    TextView username_loan;                                         //fragment_loancalculator_apply.xml

    String Accno_trans, Taccno_trans, Date_trans, Amount_trans = null; //fragment_transaction.xml
    String Pin_pin;                                                    //fragment_transaction_pin.xml
    String TransId_delete;                                       //fragment_transaction_employee.xml
    String FirstName_update, LastName_update, Email_update;            //fragment_profile_update.xml
    String OldPin_update, NewPin_update, ConfNewPin_update;            //fragment_profile_update_pin.xml
    String OldPass_update, NewPass_update, ConfNewPass_update;         //fragment_profile_update_password.xml
    String Type_complaint, Complaint_complaint, Username_complaint;    //fragment_complaint.xml
    String ComplintId_delete;                                          //fragment_profile_update.xml
    String ComplaintId_update, Type_update, Complaint_update;          //fragment_profile_update.xml
    String Amount_loan,Interest_loan,Months_loan,Monthly_loan,Total_loan; //fragment_loancalculator.xml
    String Username_loan,Type_loan,Comments_loan;                         //fragment_loancalculator_apply.xml
    String Loanid_loan_update,Type_loan_update,Comments_loan_update;   //fragment_loancalculator_update.xml

    Bundle bundle = new Bundle(); // bundle used to send data from activity to fragment


    private DrawerLayout drawer;  // to set the navigation drawer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user_session = (User) getIntent().getSerializableExtra("user_session"); // getting the user class from Login activity
        myDb = new DatabaseHelper(this); // instance of DatabaseHelper class
        bundle.putSerializable("user_session", user_session); // putting the user class to bundle


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout); // setting drawer


        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0); // injecting header to the navigation view
        displayName = headerView.findViewById(R.id.fullname_session);
        displayName.setText(user_session.getFirstName() + " " + user_session.getLastName());
        navigationView.setNavigationItemSelectedListener(this); // configuring the redirection for each item listed in the menu

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home); // the initial page would be Home
        }




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home://  inflates the Home fragment

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.nav_profile: // sets the bundle and inflates the profile fragment
                ProfileFragment pf = new ProfileFragment();
                pf.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pf).commit();

                break;
            case R.id.nav_transaction: // sets the bundle and inflates the transaction fragment

                TransactionFragment tf = new TransactionFragment();
                tf.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, tf).commit();
                break;

            case R.id.nav_calculator:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoanCalculatorFragment()).commit();
                break;
            case R.id.nav_complaint: // sets the bundle and inflates the Complaint fragment
                ComplaintFragment cf = new ComplaintFragment();
                cf.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, cf).commit();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Atheeq - User Management
    public void directToUpdateProfilePage(View v) {
        ProfileUpdateFragment puf = new ProfileUpdateFragment();
        puf.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, puf).commit();

    }

    public void directToChangePinPage(View v) {
        ProfileUpdatePinFragment pupf = new ProfileUpdatePinFragment();
        pupf.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pupf).commit();
    }

    public void directToChangePassPage(View v) {
        ProfileUpdatePasswordFragment pupaf = new ProfileUpdatePasswordFragment();
        pupaf.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pupaf).commit();

    }

    public void updateProfile(View v) {
        firstName_update = findViewById(R.id.firstName_profile_update);
        lastName_update = findViewById(R.id.lastName_profile_update);
        accountNumber_update = findViewById(R.id.accountNumber_profile_update);
        accountBalance_update = findViewById(R.id.accountBalance_profile_update);
        email_update = findViewById(R.id.email_profile_update);

        if (validateUpdateProfile()) {
            Toast.makeText(this, "Validated Successfully", Toast.LENGTH_LONG).show();

            User user = new User(FirstName_update, LastName_update, Email_update, user_session.getUsername());

            myDb.updateUser(user);
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Please Re Login", Toast.LENGTH_LONG).show();

            updateUserSession();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        } else {
            Toast.makeText(this, "Not Validated ", Toast.LENGTH_LONG).show();
        }
    }

    public boolean validateUpdateProfile() {

        FirstName_update = firstName_update.getText().toString();
        LastName_update = lastName_update.getText().toString();
        Email_update = email_update.getText().toString();

        int white = ContextCompat.getColor(getApplicationContext(), R.color.white);
        String errorString = "This field cannot be empty";  // Your custom error message.

        String errorString2 = "Enter a valid Email";
        String errorString3 = "Enter only Numbers";
        String errorString4 = "Enter only Letters";


        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(white);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);


        SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(errorString2);
        spannableStringBuilder2.setSpan(foregroundColorSpan, 0, errorString2.length(), 0);

        SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder(errorString3);
        spannableStringBuilder3.setSpan(foregroundColorSpan, 0, errorString3.length(), 0);

        SpannableStringBuilder spannableStringBuilder4 = new SpannableStringBuilder(errorString4);
        spannableStringBuilder4.setSpan(foregroundColorSpan, 0, errorString4.length(), 0);

        if (FirstName_update.isEmpty()) {
            firstName_update.setError(spannableStringBuilder);
            return false;
        }
        if (FirstName_update.isEmpty()) {
            firstName_update.setError(spannableStringBuilder);
            return false;
        }
        if (FirstName_update.isEmpty()) {
            firstName_update.setError(spannableStringBuilder);
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email_update).matches()) {
            email_update.setError(spannableStringBuilder2);
            return false;
        }
        if (!FirstName_update.matches("[a-zA-Z]+")) {
            firstName_update.setError(spannableStringBuilder4);
            return false;
        }

        if (!LastName_update.matches("[a-zA-Z]+")) {
            lastName_update.setError(spannableStringBuilder4);
            return false;
        }

        return true;
    }

    public void deleteUser(View v) {
        myDb.deleteUser(user_session.getUsername());
        Toast.makeText(this, "Account Deleted", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Create a new account to proceed", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

    }

    public void changePin(View v) {
        oldPin_update = findViewById(R.id.oldpin_profile_change_pin);
        newPin_update = findViewById(R.id.newpin_profile_change_pin);
        confNewPin_update = findViewById(R.id.confirmpin_profile_change_pin);

        OldPin_update = oldPin_update.getText().toString();
        NewPin_update = newPin_update.getText().toString();
        ConfNewPin_update = confNewPin_update.getText().toString();

        if (validateChangePin()) {
            Toast.makeText(this, "Validated Successfully", Toast.LENGTH_LONG).show();
            myDb.updatePin(user_session.getUsername(), Integer.parseInt(NewPin_update));

            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Please Re Login", Toast.LENGTH_LONG).show();

            updateUserSession();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        } else {
            Toast.makeText(this, " Not Validated", Toast.LENGTH_LONG).show();
        }

    }

    public void changePassword(View v) {
        oldPass_update = findViewById(R.id.oldpass_profile_change_password);
        newPass_update = findViewById(R.id.newpass_profile_change_password);
        confNewPass_update = findViewById(R.id.confirmpass_profile_change_pass);

        OldPass_update = oldPass_update.getText().toString();
        NewPass_update = newPass_update.getText().toString();
        ConfNewPass_update = confNewPass_update.getText().toString();

        if (validateChangePass()) {
            Toast.makeText(this, "Validated Successfully", Toast.LENGTH_LONG).show();
            myDb.updatePass(user_session.getUsername(), NewPass_update);

            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Please Re Login", Toast.LENGTH_LONG).show();

            updateUserSession();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();


        } else {
            Toast.makeText(this, " Not Validated", Toast.LENGTH_LONG).show();
        }


    }

    public boolean validateChangePin() {
        int white = ContextCompat.getColor(getApplicationContext(), R.color.white);
        String errorString = "This field cannot be empty";  // Your custom error message.
        String errorString1 = "Confirm Correctly";
        String errorString3 = "Enter only Numbers";
        String errorString8 = "Please Enter 4 digit number";
        String errorString9 = "Incorrect Pin";

        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(white);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);

        SpannableStringBuilder spannableStringBuilder1 = new SpannableStringBuilder(errorString1);
        spannableStringBuilder1.setSpan(foregroundColorSpan, 0, errorString1.length(), 0);

        SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder(errorString3);
        spannableStringBuilder3.setSpan(foregroundColorSpan, 0, errorString3.length(), 0);

        SpannableStringBuilder spannableStringBuilder8 = new SpannableStringBuilder(errorString8);
        spannableStringBuilder8.setSpan(foregroundColorSpan, 0, errorString8.length(), 0);

        SpannableStringBuilder spannableStringBuilder9 = new SpannableStringBuilder(errorString9);
        spannableStringBuilder9.setSpan(foregroundColorSpan, 0, errorString9.length(), 0);

        if (OldPin_update.isEmpty()) {
            oldPin_update.setError(spannableStringBuilder);
            return false;

        }

        if (NewPin_update.isEmpty()) {
            newPin_update.setError(spannableStringBuilder);
            return false;

        }

        if (ConfNewPin_update.isEmpty()) {
            confNewPin_update.setError(spannableStringBuilder);
            return false;

        }

        if (!OldPin_update.matches("\\d{4,4}")) {
            oldPin_update.setError(spannableStringBuilder8);
            return false;

        }

        if (!NewPin_update.matches("\\d{4,4}")) {
            newPin_update.setError(spannableStringBuilder8);
            return false;

        }

        if (!ConfNewPin_update.matches("\\d{4,4}")) {
            confNewPin_update.setError(spannableStringBuilder8);
            return false;

        }
        if (!myDb.validateTransactionPin(user_session.getUsername(), Integer.parseInt(OldPin_update))) {
            oldPin_update.setError(spannableStringBuilder9);
            return false;
        }

        if (!(NewPin_update.equals(ConfNewPin_update))) {
            Toast.makeText(this, "Please Confirm the pin correctly", Toast.LENGTH_LONG).show();
            newPin_update.setError(spannableStringBuilder1);
            confNewPin_update.setError(spannableStringBuilder1);
            return false;
        }

        return true;
    }

    public boolean validateChangePass() {
        int white = ContextCompat.getColor(getApplicationContext(), R.color.white);
        String errorString = "This field cannot be empty";  // Your custom error message.
        String errorString1 = "Confirm Correctly";
        String errorString9 = "Incorrect Pass";


        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(white);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);

        SpannableStringBuilder spannableStringBuilder1 = new SpannableStringBuilder(errorString1);
        spannableStringBuilder1.setSpan(foregroundColorSpan, 0, errorString1.length(), 0);

        SpannableStringBuilder spannableStringBuilder9 = new SpannableStringBuilder(errorString9);
        spannableStringBuilder9.setSpan(foregroundColorSpan, 0, errorString9.length(), 0);


        if (OldPass_update.isEmpty()) {
            oldPass_update.setError(spannableStringBuilder);
            return false;

        }

        if (NewPass_update.isEmpty()) {
            newPass_update.setError(spannableStringBuilder);
            return false;

        }

        if (ConfNewPass_update.isEmpty()) {
            confNewPass_update.setError(spannableStringBuilder);
            return false;

        }
        User u1 = new User(user_session.getUsername(), OldPass_update);
        if (!myDb.validateLogin(u1)) {
            oldPass_update.setError(spannableStringBuilder9);
            return false;
        }

        if (!(NewPass_update.equals(ConfNewPass_update))) {
            Toast.makeText(this, "Please Confirm the password correctly", Toast.LENGTH_LONG).show();
            newPass_update.setError(spannableStringBuilder1);
            confNewPass_update.setError(spannableStringBuilder1);
            return false;
        }

        return true;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Vinu - Transaction Management
    public void insertTransaction(View v) {
        accno_trans = findViewById(R.id.accno_session_trans);
        taccno_trans = findViewById(R.id.taccno_trans);
        date_trans = findViewById(R.id.date_trans);
        amount_trans = findViewById(R.id.amount_trans);

        Accno_trans = accno_trans.getText().toString();
        Taccno_trans = taccno_trans.getText().toString();
        Date_trans = date_trans.getText().toString();
        Amount_trans = amount_trans.getText().toString();

        boolean lowerAmount = myDb.checkForTransactionAmount(Integer.parseInt(Accno_trans), Integer.parseInt(Amount_trans)); //returns true when amount is sufficent to do the transaction
        if (lowerAmount) {
            if (valditateTransaction()) {
                Toast.makeText(this, "Validated Successfully", Toast.LENGTH_LONG).show();

                TransactionPinFragement tpf = new TransactionPinFragement();

                tpf.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, tpf).commit();

            } else {
                Toast.makeText(this, "Not Validated", Toast.LENGTH_LONG).show();
            }
        } else {
            setLessBalanceError(); // sets low balance error
        }

    }

    public boolean valditateTransaction() {
        int white = ContextCompat.getColor(getApplicationContext(), R.color.white);
        String errorString = "This field cannot be empty";  // Your custom error message.

        String errorString3 = "Enter only Numbers";
        String errorString4 = "Enter only Letters";
        String errorString6 = "Enter a valid Account Number ";
        String errorString7 = "Please Enter 8 digit number ";
        String errorString8 = "Please Enter 4 digit number";

        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(white);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);


        SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder(errorString3);
        spannableStringBuilder3.setSpan(foregroundColorSpan, 0, errorString3.length(), 0);

        SpannableStringBuilder spannableStringBuilder4 = new SpannableStringBuilder(errorString4);
        spannableStringBuilder4.setSpan(foregroundColorSpan, 0, errorString4.length(), 0);

        SpannableStringBuilder spannableStringBuilder6 = new SpannableStringBuilder(errorString6);
        spannableStringBuilder6.setSpan(foregroundColorSpan, 0, errorString6.length(), 0);

        SpannableStringBuilder spannableStringBuilder7 = new SpannableStringBuilder(errorString7);
        spannableStringBuilder7.setSpan(foregroundColorSpan, 0, errorString7.length(), 0);

        SpannableStringBuilder spannableStringBuilder8 = new SpannableStringBuilder(errorString8);
        spannableStringBuilder8.setSpan(foregroundColorSpan, 0, errorString8.length(), 0);


        if (Accno_trans.isEmpty()) {
            accno_trans.setError(spannableStringBuilder);
            return false;
        }
        if (Taccno_trans.isEmpty()) {
            taccno_trans.setError(spannableStringBuilder);
            return false;
        }
        if (Date_trans.isEmpty()) {
            date_trans.setError(spannableStringBuilder);
            return false;
        }
        if (Amount_trans.isEmpty()) {
            amount_trans.setError(spannableStringBuilder);
            return false;
        }

        if (!Accno_trans.matches("[0-9]+")) {
            amount_trans.setError(spannableStringBuilder3);
            return false;
        }

        if (!Taccno_trans.matches("[0-9]+")) {
            taccno_trans.setError(spannableStringBuilder3);
            return false;
        }

        if (!Amount_trans.matches("[0-9]+")) {
            amount_trans.setError(spannableStringBuilder3);
            return false;
        }

        if (Date_trans.matches("[a-zA-Z]+")) {
            date_trans.setError(spannableStringBuilder4);
            return false;
        }

        if (!myDb.isValidAccountNumber(Long.parseLong(Taccno_trans))) {
            taccno_trans.setError(spannableStringBuilder6);
            return false;
        }

        if (!Taccno_trans.matches("\\d{8,8}")) {
            taccno_trans.setError(spannableStringBuilder7);
            return false;
        }


        return true;
    }

    public void setPinError() {
        int white = ContextCompat.getColor(getApplicationContext(), R.color.white);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(white);

        String errorString7 = "Incorrect Pin, Try Again ";
        SpannableStringBuilder spannableStringBuilder7 = new SpannableStringBuilder(errorString7);
        spannableStringBuilder7.setSpan(foregroundColorSpan, 0, errorString7.length(), 0);

        pin_pin = findViewById(R.id.pin_pin);
        pin_pin.setError(spannableStringBuilder7);

        validatePinText();

    }

    public void setLessBalanceError() {
        int white = ContextCompat.getColor(getApplicationContext(), R.color.white);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(white);

        String errorString7 = "Sorry, Your Balance is Less than Transfer Amount";
        SpannableStringBuilder spannableStringBuilder7 = new SpannableStringBuilder(errorString7);
        spannableStringBuilder7.setSpan(foregroundColorSpan, 0, errorString7.length(), 0);

        amount_trans.setError(spannableStringBuilder7);
    }

    public void validatePin(View v) { // validating whether pin is matching or not
        pin_pin = findViewById(R.id.pin_pin);
        Pin_pin = pin_pin.getText().toString();
        boolean pinRes = myDb.validateTransactionPin(user_session.getUsername(), Integer.parseInt(Pin_pin));
        if (pinRes) {
            Transaction transaction = new Transaction(Integer.parseInt(Accno_trans), Integer.parseInt(Taccno_trans), Date_trans, Integer.parseInt(Amount_trans));
            boolean res = myDb.insertNewTransaction(transaction);

            if (res) {
                Toast.makeText(this, "Transaction Made Successfully", Toast.LENGTH_LONG).show();
                updateUserSession();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            } else {
                Toast.makeText(this, "Transaction Failed", Toast.LENGTH_LONG).show();

            }
        } else {
            setPinError(); // sets the error in pin field if the pin is incorrect
        }
    }



    public void updateUserSession() { // called to update the user session class
        User user1 = new User();
        Cursor cursor1 = myDb.getUserClass(user_session.getUsername());
        while (cursor1.moveToNext()) {
            user1.setUserId(cursor1.getInt(0));
            user1.setFirstName(cursor1.getString(1));
            user1.setLastName(cursor1.getString(2));
            user1.setAccountNumber(cursor1.getInt(3));
            user1.setSecurityCode(cursor1.getInt(4));
            user1.setEmail(cursor1.getString(5));
            user1.setUsername(cursor1.getString(6));
            user1.setBalance(cursor1.getInt(8));
        }
        user_session = user1;
        bundle.putSerializable("user_session", user_session);
    }

    public void validatePinText() {

        int white = ContextCompat.getColor(getApplicationContext(), R.color.white);
        String errorString = "This field cannot be empty";  // Your custom error message.
        String errorString3 = "Enter only Numbers";
        String errorString8 = "Please Enter 4 digit number";

        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(white);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);


        SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder(errorString3);
        spannableStringBuilder3.setSpan(foregroundColorSpan, 0, errorString3.length(), 0);


        SpannableStringBuilder spannableStringBuilder8 = new SpannableStringBuilder(errorString8);
        spannableStringBuilder8.setSpan(foregroundColorSpan, 0, errorString8.length(), 0);

        if (Pin_pin.isEmpty()) {
            pin_pin.setError(spannableStringBuilder);

        }
        if (!Pin_pin.matches("[0-9]+")) {
            pin_pin.setError(spannableStringBuilder3);

        }
        if (!Pin_pin.matches("\\d{4,4}")) {
            pin_pin.setError(spannableStringBuilder8);
        }


    }

    public void displayTransactionHistory(View v) {
        StringBuffer buffer = new StringBuffer();
        Cursor res1 = myDb.retreiveSentTransaction(user_session.getAccountNumber());
        Cursor res2 = myDb.retreiveReceivedTransaction(user_session.getAccountNumber());
        buffer.append("Transaction Sent \n \n");
        if (res1.getCount() == 0) {
            buffer.append("No Transactions were Sent \n\n");
        } else {
            while (res1.moveToNext()) {
                buffer.append("Transaction ID : " + res1.getInt(0) + "\n");
                buffer.append("Target Account : " + res1.getLong(2) + "\n");
                buffer.append("Date : " + res1.getString(3) + "\n");
                buffer.append("Amount : " + res1.getInt(4) + "\n\n");
            }

        }

        buffer.append("Transaction Received \n \n");

        if (res2.getCount() == 0) {
            buffer.append("No Transactions were Received \n \n ");
        } else {
            while (res2.moveToNext()) {
                buffer.append("Transaction ID : " + res2.getInt(0) + "\n");
                buffer.append("Source Account : " + res2.getLong(1) + "\n");
                buffer.append("Date : " + res2.getString(3) + "\n");
                buffer.append("Amount : " + res2.getInt(4) + "\n\n");
            }


        }
        buffer.append("\n\n \n Current Balance  : " + user_session.getBalance());
        showTransactionHistoryMessage("Transaction History", buffer.toString());

    }

    public void showTransactionHistoryMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void directToEmployeeTransactionMode(View v){
        TransactionEmployeeFragment tef = new TransactionEmployeeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, tef).commit();
    }

    public void displayAllTransactions( View v){
        StringBuffer buffer = new StringBuffer();
        Cursor res = myDb.retreiveAllTransactions();

        if (res.getCount() == 0) {
            buffer.append("No Transactions Were Made  \n\n");
        } else {
            while (res.moveToNext()) {
                buffer.append("Transaction ID : " + res.getInt(0) + "\n");
                buffer.append("Source Account : " + res.getInt(1) + "\n");
                buffer.append("Target Account : " + res.getLong(2) + "\n");
                buffer.append("Date : " + res.getString(3) + "\n");
                buffer.append("Amount : " + res.getInt(4) + "\n\n");
            }

        }
        showAllTransactionMessage("Full Transaction History",buffer.toString());
    }

    public void showAllTransactionMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void deleteTransaction(View v){
        transId_delete = findViewById(R.id.complaintid_trans_delete);
        TransId_delete = transId_delete.getText().toString();



        if (validateDeleteTransaction()) {
            Toast.makeText(this, "Validated Successfully", Toast.LENGTH_LONG).show();

           myDb.deleteTransaction(Integer.parseInt(TransId_delete));
            Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Not Validated", Toast.LENGTH_LONG).show();
        }
    }

    public boolean validateDeleteTransaction(){

        int white = ContextCompat.getColor(getApplicationContext(), R.color.white);
        String errorString = "This field cannot be empty";  // Your custom error message.
        String errorString3 = "Enter only Numbers";
        String errorString8 = "Please Enter a valid Transaction Id";

        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(white);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);


        SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder(errorString3);
        spannableStringBuilder3.setSpan(foregroundColorSpan, 0, errorString3.length(), 0);


        SpannableStringBuilder spannableStringBuilder8 = new SpannableStringBuilder(errorString8);
        spannableStringBuilder8.setSpan(foregroundColorSpan, 0, errorString8.length(), 0);

        if(TransId_delete.isEmpty()){
            transId_delete.setError(spannableStringBuilder);
            return false;
        }

        if(!TransId_delete.matches("[0-9]+")){
            transId_delete.setError(spannableStringBuilder3);
            return false;
        }

        if(!(myDb.isValidTransactionId(Integer.parseInt(TransId_delete)))){
            transId_delete.setError(spannableStringBuilder8);
            return false;
        }

        return  true;

    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Thulshi - Fault Management
    public void insertNewComplaint(View v) {
        username_complaint = findViewById(R.id.username_session_complaint);
        type_complaint = findViewById(R.id.complaintType_complaint);
        complaint_complaint = findViewById(R.id.complaint_complaint);

        Username_complaint = username_complaint.getText().toString();
        Type_complaint = type_complaint.getText().toString();
        Complaint_complaint = complaint_complaint.getText().toString();

        if(validateInsertComplaint()) {
            Complaint complaint = new Complaint(Username_complaint, Type_complaint, Complaint_complaint);

            boolean res = myDb.insertNewComplaint(complaint);
            if (res) {
                Toast.makeText(this, "Complaint Added Successfully", Toast.LENGTH_LONG).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

            } else {
                Toast.makeText(this, "Unsuccessful", Toast.LENGTH_LONG).show();

            }
        }
        else{
            Toast.makeText(this, "Not Validated", Toast.LENGTH_LONG).show();
        }

    }

    public boolean validateInsertComplaint(){

        int white = ContextCompat.getColor(getApplicationContext(), R.color.white);
        String errorString = "This field cannot be empty";  // Your custom error message.


        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(white);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);

        if (Type_complaint.isEmpty()){
            type_complaint.setError(spannableStringBuilder);
            return false;
        }

        if (Complaint_complaint.isEmpty()){
            complaint_complaint.setError(spannableStringBuilder);
            return false;
        }
        return  true;

    }

    public void viewAllComplaint(View v) {
        StringBuffer buffer = new StringBuffer();
        Cursor res = myDb.retreiveUserComplaint(user_session.getUsername());


        if (res.getCount() == 0) {
            buffer.append("No Complaints Lodged");

        } else {

            while (res.moveToNext()) {
                buffer.append("Complaint ID : " + res.getInt(0) + "\n");
                buffer.append("Complaint Type :" + res.getString(2) + "\n");
                buffer.append("Complaint : " + res.getString(3) + "\n\n");

            }
            showComplaintMessage("Complaint History", buffer.toString());

        }
    }

    public void showComplaintMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void directToUpdateComplaintPage(View v) {
        ComplaintUpdateFragment cuf = new ComplaintUpdateFragment();
        cuf.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, cuf).commit();

    }


    public void deleteComplaint(View v) {
        complintId_delete = findViewById(R.id.complaintId_complaint_update);
        ComplintId_delete = complintId_delete.getText().toString();
        myDb.deleteComplaint(Integer.parseInt(ComplintId_delete));

        Toast.makeText(this, "Complaint Deleted Successfully", Toast.LENGTH_LONG).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

    }

    public void updateComplaint(View v) {
        complaintId_update = findViewById(R.id.complaintId_complaint_update);
        type_update = findViewById(R.id.complaintType_complaint);
        complaint_update = findViewById(R.id.complaint_complaint);

        ComplaintId_update = complaintId_update.getText().toString();
        Type_update = type_update.getText().toString();
        Complaint_update = complaint_update.getText().toString();

        Complaint complaint = new Complaint(Integer.parseInt(ComplaintId_update),Type_complaint,Complaint_update);
        myDb.updateComplaint(complaint);
        Toast.makeText(this, "Complaint Updated Successfully", Toast.LENGTH_LONG).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void calculateLoan(View v){
        amount_loan= findViewById(R.id.amount_loan);
        interest_loan = findViewById(R.id.interest_loan);
        months_loan=findViewById(R.id.months_loan);

        monthly_loan = findViewById(R.id.monthly_loan);
        total_loan = findViewById(R.id.total_loan);

        Amount_loan = amount_loan.getText().toString();
        Interest_loan = interest_loan.getText().toString();
        Months_loan = months_loan.getText().toString();

        //Toast.makeText(this, "" + Amount_loan + Interest_loan+Months_loan, Toast.LENGTH_LONG).show();
         DecimalFormat df2 = new DecimalFormat(".##");
        double amount = Double.parseDouble(Amount_loan);
        double interest = Double.parseDouble(Interest_loan);
        interest = interest/100.00;
        double months = Double.parseDouble(Months_loan);

        total_loan.setText(df2.format(amount*(1+interest) )+ "");

        monthly_loan.setText(df2.format((amount*(1+interest))/months) + "");


    }

    public void directToUpdateLoanPage(View v){

        LoanCalculatorUpdateFragment lcuf = new LoanCalculatorUpdateFragment();
        lcuf.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, lcuf).commit();


    }

    public void directToApplyLoanPage(View v){

        LoanCalculatorApplyFragment lcaf = new LoanCalculatorApplyFragment();
        lcaf.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, lcaf).commit();


    }

    public void insertNewLoan(View v){
        username_loan = findViewById(R.id.username_session_loan);
        type_loan = findViewById(R.id.loanType_loan);
        comments_loan = findViewById(R.id.comment_loan);

        Username_loan = username_loan.getText().toString();
        Type_loan = type_loan.getText().toString();
        Comments_loan = comments_loan.getText().toString();

       if( validateInsertLoan()){
         Loan loan = new Loan(Username_loan,Type_loan,Comments_loan);
         boolean res = myDb.insertNewLoan(loan);
         if(res){
             Toast.makeText(this, "Inserted Successfully", Toast.LENGTH_LONG).show();
             getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
         }
         else{
             Toast.makeText(this, "Insertion Failed", Toast.LENGTH_LONG).show();
         }

        }

        else{
           Toast.makeText(this, "Not Validated", Toast.LENGTH_LONG).show();
       }
    }

    public boolean validateInsertLoan(){

        int white = ContextCompat.getColor(getApplicationContext(), R.color.white);
        String errorString = "This field cannot be empty";  // Your custom error message.


        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(white);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);


        if (Type_loan.isEmpty()){
            type_loan.setError(spannableStringBuilder);
            return false;
        }

        if (Comments_loan.isEmpty()){
            comments_loan.setError(spannableStringBuilder);
            return false;
        }

        return true;
    }

    public  void viewAllLoan(View v){
        StringBuffer buffer = new StringBuffer();
        Cursor res = myDb.retreiveUserLoan(user_session.getUsername());

        if (res.getCount() == 0) {
            buffer.append("No Loans Applied");

        } else {

            while (res.moveToNext()) {
                buffer.append("Loan ID : " + res.getInt(0) + "\n");
                buffer.append("Loan Type :" + res.getString(2) + "\n");
                buffer.append("Comments : " + res.getString(3) + "\n\n");

            }
            showLoanMessage("Loan History", buffer.toString());

        }

    }

    public void showLoanMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void updateLoan(View v){

        loanid_loan_update=findViewById(R.id.loanId_loan_update);
        type_loan_update=findViewById(R.id.loanType_loan_update);
        comments_loan_update=findViewById(R.id.comments_loan_update);

        Loanid_loan_update = loanid_loan_update.getText().toString();
        Type_loan_update = type_loan_update.getText().toString();
        Comments_loan_update = comments_loan_update.getText().toString();

        Loan loan = new Loan(Integer.parseInt(Loanid_loan_update),Type_loan_update,Comments_loan_update);
        myDb.updateLoan(loan);

        Toast.makeText(this, "Updated Successfully", Toast.LENGTH_LONG).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();


    }

    public void deleteLoan(View v){
        loanid_loan_update=findViewById(R.id.loanId_loan_update);
        Loanid_loan_update = loanid_loan_update.getText().toString();

        myDb.deleteLoan(Integer.parseInt(Loanid_loan_update));

        Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_LONG).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();



    }
}
