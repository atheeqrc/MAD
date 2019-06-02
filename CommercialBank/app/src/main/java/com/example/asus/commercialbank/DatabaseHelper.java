package com.example.asus.commercialbank;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "commercialBank.db";

    public static final String TABLE_NAME_USER = "user";
    public static final String USER_COL1 = "userId";
    public static final String USER_COL2 = "firstName";
    public static final String USER_COL3 = "lastName";
    public static final String USER_COL4 = "accountNumber";
    public static final String USER_COL5 = "securityCode";
    public static final String USER_COL6 = "email";
    public static final String USER_COL7 = "username";
    public static final String USER_COL8 = "password";
    public static final String USER_COL9 = "balance";

    public static final String TABLE_NAME_TRANSACTION = "transactiontable";
    public static final String TRANSACTION_COL1 = "transID";
    public static final String TRANSACTION_COL2 = "accno";
    public static final String TRANSACTION_COL3 = "taccno";
    public static final String TRANSACTION_COL4 = "date";
    public static final String TRANSACTION_COL5 = "amount";

    public static final String TABLE_NAME_COMPLAINT = "complaint";
    public static final String COMPLAINT_COL1 = "complaintId";
    public static final String COMPLAINT_COL2 = "username";
    public static final String COMPLAINT_COL3 = "type";
    public static final String COMPLAINT_COL4 = "complaint";


    public static final String TABLE_NAME_LOAN = "loan";
    public static final String LOAN_COL1 = "loanId";
    public static final String LOAN_COL2 = "username";
    public static final String LOAN_COL3 = "type";
    public static final String LOAN_COL4 = "comments";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME_USER + " (userId INTEGER PRIMARY KEY AUTOINCREMENT,firstName TEXT,lastName TEXT,accountNumber INTEGER,securityCode INTEGER,email TEXT,username TEXT,password TEXT,balance INTEGER)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_TRANSACTION + " (transID INTEGER PRIMARY KEY AUTOINCREMENT,accno INTEGER,taccno INTEGER,date TEXT,amount INTEGER)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_COMPLAINT + " (complaintId INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT,type TEXT,complaint TEXT) ");
        db.execSQL("CREATE TABLE " + TABLE_NAME_LOAN + " (loanId INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT,type TEXT,comments TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TRANSACTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_COMPLAINT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_LOAN);
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Atheeq - User Management

    public boolean insertNewUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL2, user.getFirstName());
        contentValues.put(USER_COL3, user.getLastName());
        contentValues.put(USER_COL4, user.getAccountNumber());
        contentValues.put(USER_COL5, user.getSecurityCode());
        contentValues.put(USER_COL6, user.getEmail());
        contentValues.put(USER_COL7, user.getUsername());
        contentValues.put(USER_COL8, user.getPassword());
        contentValues.put(USER_COL9, 100000);

        long res = db.insert(TABLE_NAME_USER, null, contentValues);
        if (res == -1) {
            return false;
        } else
            return true;
    }

    public boolean isValidUniqueUsername(String username) {
        Cursor cursor;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.rawQuery("SELECT * from " + TABLE_NAME_USER + " WHERE " + USER_COL7 + " Like " + " '" + username + "'", null);
        if (cursor.getCount() == 0) {
            return true;
        } else return false;

    }

    public boolean isValidUniqueAccountNumber(Integer accno) {
        Cursor cursor;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.rawQuery("SELECT * from " + TABLE_NAME_USER + " WHERE " + USER_COL4 + " = " + accno, null);
        if (cursor.getCount() == 0) {
            return true;
        } else return false;

    }

    public boolean validateLogin(User user) {

        Cursor cursor;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.rawQuery("SELECT * from " + TABLE_NAME_USER + " WHERE " + USER_COL7 + " Like " + " '" + user.getUsername() + "'" + " AND " + USER_COL8 + " LIKE " + "'" + user.getPassword() + "'", null);
        if (cursor.getCount() == 0) {
            return false;
        } else if (cursor.getCount() >= 1) {
            return true;
        }
        return false;

    }

    public Cursor getUserClass(String username) {
        Cursor cursor;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.rawQuery("SELECT * from " + TABLE_NAME_USER + " WHERE " + USER_COL7 + " Like " + " '" + username + "'", null);
        return cursor;
    }

    public boolean updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME_USER + " SET " + USER_COL2 + " = " + "'" + user.getFirstName() + "' , " + USER_COL3 + " = " + "'" + user.getLastName() + "' , " + USER_COL6 + " = " + "'" + user.getEmail() + "'" + " WHERE " + USER_COL7 + " = " + "'" + user.getUsername() + "'");

        return true;
    }

    public boolean deleteUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM  " + TABLE_NAME_USER + " WHERE " + USER_COL7 + " = " + "'" + username + "'");
        return true;
    }

    public boolean updatePin(String username, int pin) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME_USER + " SET " + USER_COL5 + " = " + pin + " WHERE " + USER_COL7 + " = " + "'" + username + "'");

        return true;
    }

    public boolean updatePass(String username, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME_USER + " SET " + USER_COL8 + " = " + "'" + pass + "'" + " WHERE " + USER_COL7 + " LIKE " + "'" + username + "'");
        return true;
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Vinu - Transaction Management

    public boolean insertNewTransaction(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TRANSACTION_COL2, transaction.getAccountNumber());
        contentValues.put(TRANSACTION_COL3, transaction.gettAccountNumber());
        contentValues.put(TRANSACTION_COL4, transaction.getDate());
        contentValues.put(TRANSACTION_COL5, transaction.getAmount());

        long res = db.insert(TABLE_NAME_TRANSACTION, null, contentValues);
        if (res == -1) {
            return false;
        } else {
            Cursor cursor1, cursor2;
            cursor1 = db.rawQuery("SELECT * FROM " + TABLE_NAME_USER + " WHERE " + USER_COL4 + " = " + transaction.getAccountNumber(), null);
            cursor2 = db.rawQuery("SELECT * FROM " + TABLE_NAME_USER + " WHERE " + USER_COL4 + " = " + transaction.gettAccountNumber(), null);
            int senderOld = 0;
            int receiverOld = 0;
            while (cursor1.moveToNext()) {
                senderOld = cursor1.getInt(8);
            }
            while (cursor2.moveToNext()) {
                receiverOld = cursor2.getInt(8);
            }
            int senderNew = senderOld - transaction.getAmount();
            int receiverNew = receiverOld + transaction.getAmount();

            db.execSQL("UPDATE " + TABLE_NAME_USER + " SET " + USER_COL9 + " = " + senderNew + " WHERE " + USER_COL4 + " = " + transaction.getAccountNumber());
            db.execSQL("UPDATE " + TABLE_NAME_USER + " SET " + USER_COL9 + " = " + receiverNew + " WHERE " + USER_COL4 + " = " + transaction.gettAccountNumber());
            return true;
        }


    }

    public boolean isValidAccountNumber(Long accno) {
        Cursor cursor;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.rawQuery("SELECT * from " + TABLE_NAME_USER + " WHERE " + USER_COL4 + " = " + accno, null);
        if (cursor.getCount() == 1) {
            return true;
        } else return false;

    }

    public boolean checkForTransactionAmount(int accountNo, int amount) {
        Cursor cursor1;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor1 = db.rawQuery("SELECT * FROM " + TABLE_NAME_USER + " WHERE " + USER_COL4 + " = " + accountNo, null);
        int balance = 0;
        while (cursor1.moveToNext()) {
            balance = cursor1.getInt(8);
        }
        if (amount > balance) {
            return false;
        }
        return true;
    }

    public boolean validateTransactionPin(String username, Integer pin) {

        Cursor cursor;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.rawQuery("SELECT * from " + TABLE_NAME_USER + " WHERE " + USER_COL7 + " = " + "'" + username + "'" + " AND " + USER_COL5 + " = " + pin, null);
        if (cursor.getCount() == 1) {
            return true;
        } else return false;

    }


    public Cursor retreiveSentTransaction(Long accno) {
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_TRANSACTION + " WHERE " + TRANSACTION_COL2 + " = " + accno, null);
        return cursor;
    }

    public Cursor retreiveReceivedTransaction(Long accno) {
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_TRANSACTION + " WHERE " + TRANSACTION_COL3 + " = " + accno, null);
        return cursor;
    }

    public Cursor retreiveAllTransactions(){
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_TRANSACTION , null);
        return cursor;
    }

    public boolean deleteTransaction(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM  " + TABLE_NAME_TRANSACTION + " WHERE " + TRANSACTION_COL1 + " = " +  id );
        return true;
    }

    public boolean isValidTransactionId(int id){
        Cursor cursor;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.rawQuery("SELECT * from " + TABLE_NAME_TRANSACTION + " WHERE " + TRANSACTION_COL1 + " = " + id, null);
        if (cursor.getCount() == 1) {
            return true;
        } else return false;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Thulshi - Fault Management


    public boolean insertNewComplaint(Complaint complaint) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COMPLAINT_COL2, complaint.getUsername());
        contentValues.put(COMPLAINT_COL3, complaint.getType());
        contentValues.put(COMPLAINT_COL4, complaint.getComplaint());


        long res = db.insert(TABLE_NAME_COMPLAINT, null, contentValues);
        if (res == -1) {
            return false;
        } else
            return true;
    }

    public Cursor retreiveUserComplaint(String username) {
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_COMPLAINT + " WHERE " + COMPLAINT_COL2 + " = " + "'" + username + "'", null);
        return cursor;
    }

    public boolean deleteComplaint(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM  " + TABLE_NAME_COMPLAINT + " WHERE " + COMPLAINT_COL1 + " = " + id);
        return true;
    }

    public boolean updateComplaint(Complaint complaint) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME_COMPLAINT + " SET " + COMPLAINT_COL3 + " = " + "'" + complaint.getType() + "', " + COMPLAINT_COL4 + " = " + "'" + complaint.getComplaint() + "'" + " WHERE " + COMPLAINT_COL1 + " = " + complaint.getComplaintId());
        return true;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public boolean insertNewLoan(Loan loan) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(LOAN_COL2, loan.getUsername());
    contentValues.put(LOAN_COL3, loan.getLoanType());
    contentValues.put(LOAN_COL4, loan.getComments());


    long res = db.insert(TABLE_NAME_LOAN, null, contentValues);
    if (res == -1) {
        return false;
    } else
        return true;
}

    public Cursor retreiveUserLoan(String username) {
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_LOAN+ " WHERE " + COMPLAINT_COL2 + " = " + "'" + username + "'", null);
        return cursor;
    }

    public boolean updateLoan(Loan loan) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME_LOAN + " SET " + LOAN_COL3 + " = " + "'" + loan.getLoanType() + "', " + LOAN_COL4 + " = " + "'" + loan.getComments() + "'" + " WHERE " + LOAN_COL1 + " = " + loan.getLoanId());
        return true;
    }

    public boolean deleteLoan(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM  " + TABLE_NAME_LOAN + " WHERE " + LOAN_COL1 + " = " + id);
        return true;
    }


}
