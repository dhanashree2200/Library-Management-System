package com.example.librarymanagementsystem;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final int DB_VERSION=2;
    public static final String DB_NAME="library_mgmt";

    public static final String BOOKS_TABLE="book_details";
    public static final String BOOK_ID="book_id";
    public static final String BOOK_TITLE="book_title";
    public static final String BOOK_AUTH="book_auth";
    public static final String BOOK_PUB="book_pub";
    public static final String BOOK_STATUS="status";

    public static final String STUD_TABLE="stud_details";
    public static final String STUD_ID="stud_id";
    public static final String STUD_NAME="stud_name";
    public static final String STUD_PASS="stud_pass";
    public static final String STUD_MAIL="stude_mail";
    public static final String STUD_PH="stud_ph";
    public static final String BOOK="book";

    public static final String ISSUED_TABLE="issue_table";
    public static final String I_BOOK_ID="book_id";
    public static final String I_STUD_ID="stud_id";
    public static final String I_DATE="i_date";
    public static final String R_DATE="r_date";

    public DatabaseHandler(Context context) {
        super(context, DB_NAME,null,DB_VERSION);
        Log.d("TAG", "DbHandler: ");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableBook(db);
        createTableStud(db);
        createIssueBook(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    void createTableBook(SQLiteDatabase db){
        String createQuery="CREATE TABLE " + BOOKS_TABLE + "(" +
                BOOK_ID + " TEXT PRIMARY KEY, " +
                BOOK_TITLE + " TEXT , " +
                BOOK_AUTH + " TEXT , " +
                BOOK_PUB + " TEXT , " +
                BOOK_STATUS + " INTEGER)" ;
        Log.d("TAG", "Books table: created");
        db.execSQL(createQuery);
    }
    void createTableStud(SQLiteDatabase db){
        String createQuery="CREATE TABLE " + STUD_TABLE + "(" +
                STUD_ID + " TEXT PRIMARY KEY, " +
                STUD_NAME + " TEXT , " +
                STUD_PASS + " TEXT , " +
                STUD_PH + " TEXT , " +
                STUD_MAIL + " TEXT , " +
                BOOK + " INTEGER)" ;
        Log.d("TAG", "Student table: created");
        db.execSQL(createQuery);
    }
    void createIssueBook(SQLiteDatabase db){
        String createQuery="CREATE TABLE " + ISSUED_TABLE + "(" +
                I_BOOK_ID + " TEXT , " +
                I_STUD_ID + " TEXT , " +
                I_DATE + " DATE , " +
                R_DATE + " DATE)" ;
        Log.d("TAG", "Issue table: created");
        db.execSQL(createQuery);
    }
    public void addBook(DbModel dbModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(BOOK_ID,dbModel.getBook_id());
        values.put(BOOK_TITLE,dbModel.getBook_title());
        values.put(BOOK_AUTH,dbModel.getBook_auth());
        values.put(BOOK_PUB,dbModel.getBook_pub());
        values.put(BOOK_STATUS,dbModel.getStatus());
        db.insert(BOOKS_TABLE,null,values);
        Log.d("TAG", "Book table inserted... ");
        db.close();
    }
    public void addStud(DbModel dbModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(STUD_ID,dbModel.getStud_id());
        values.put(STUD_NAME,dbModel.getStud_name());
        values.put(STUD_PASS,dbModel.getStud_pass());
        values.put(STUD_MAIL,dbModel.getStud_mail());
        values.put(STUD_PH,dbModel.getStud_ph());
        values.put(BOOK,dbModel.getBook());
        db.insert(STUD_TABLE,null,values);
        Log.d("TAG", "STUD table inserted... ");
        db.close();
    }
    public void addIssueBook(DbModel dbModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        values.put(I_BOOK_ID,dbModel.getI_book_id());
        values.put(I_STUD_ID,dbModel.getI_stud_id());
        values.put(I_DATE,dbModel.getI_date());
        values.put(R_DATE,dbModel.getR_date());
        db.insert(ISSUED_TABLE,null,values);
        Log.d("TAG", "ISSUE table inserted... ");
        db.close();
    }

    public List<DbModel> getAllBooks(){
        List<DbModel> list=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String select="SELECT * FROM "+BOOKS_TABLE;
        Cursor cursor=db.rawQuery(select,null);
        if(cursor.moveToFirst()){
            do {
                DbModel dbModel=new DbModel();
                dbModel.setBook_id(cursor.getString(0));
                dbModel.setBook_title(cursor.getString(1));
                dbModel.setBook_auth(cursor.getString(2));
                dbModel.setBook_pub(cursor.getString(3));
                dbModel.setStatus(Integer.parseInt(cursor.getString(4)));
                list.add(dbModel);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public List<DbModel> getAllStud(){
        List<DbModel> list=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String select="SELECT * FROM "+STUD_TABLE;
        Cursor cursor=db.rawQuery(select,null);
        if(cursor.moveToFirst()){
            do {
                DbModel dbModel=new DbModel();
                dbModel.setStud_id(cursor.getString(0));
                dbModel.setStud_name(cursor.getString(1));
                dbModel.setStud_pass(cursor.getString(2));
                dbModel.setStud_ph(cursor.getString(3));
                dbModel.setStud_mail(cursor.getString(4));
                dbModel.setBook(Integer.parseInt(cursor.getString(5)));
                list.add(dbModel);
            }while (cursor.moveToNext());
        }
        return list;
    }
//    public List<DbModel> getIdPassOfStud(){
//        List<DbModel> list=new ArrayList<>();
//        SQLiteDatabase db=this.getReadableDatabase();
//        String select="SELECT * FROM "+STUD_TABLE;
//        Cursor cursor=db.rawQuery(select,null);
//        if(cursor.moveToFirst()){
//            do {
//                DbModel dbModel=new DbModel();
//                dbModel.setStud_id(cursor.getString(0));
//                dbModel.setStud_name(cursor.getString(1));
//                dbModel.setStud_pass(cursor.getString(2));
//                dbModel.setStud_ph(cursor.getString(3));
//                dbModel.setStud_mail(cursor.getString(4));
//                dbModel.setBook(Integer.parseInt(cursor.getString(5)));
//                list.add(dbModel);
//            }while (cursor.moveToNext());
//        }
//        return list;
//    }


    public List<DbModel> getAllIssueBook()  {
        List<DbModel> list=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String select="SELECT * FROM "+ISSUED_TABLE;
        Cursor cursor=db.rawQuery(select,null);
        if(cursor.moveToFirst()){
            do {
                DbModel dbModel=new DbModel();
                dbModel.setI_book_id(cursor.getString(0));
                dbModel.setI_stud_id(cursor.getString(1));
                dbModel.setI_date(cursor.getString(2));
                dbModel.setR_date(cursor.getString(3));
                list.add(dbModel);
            }while (cursor.moveToNext());
        }
        db.close();
        return list;
    }

    public int updateBook(DbModel dbModel){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(BOOK_TITLE,dbModel.getBook_title());
        values.put(BOOK_AUTH,dbModel.getBook_auth());
        values.put(BOOK_PUB,dbModel.getBook_pub());
        return db.update(BOOKS_TABLE,values,BOOK_ID+"=?",new String[]{String.valueOf(dbModel.getBook_id())});
    }


//    public void updateBookStatus(String ID, int status) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues values =  new ContentValues();
//        values.put(BOOK_STATUS, status);
//        sqLiteDatabase.update(BOOKS_TABLE, values, BOOK_ID+" = '" + ID+"'", null);
//        sqLiteDatabase.close();
//    }
    public void updateBookStatus(String ID, int status) {
        String query="update "+BOOKS_TABLE+" set "+BOOK_STATUS+" = "+status+" where "+BOOK_ID+" = '"+ID+"';";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.close();
    }

    public void updateBookReturn(String ID, String rDate) {
        String query="update "+ISSUED_TABLE+" set "+R_DATE+" = '"+rDate+"' where "+BOOK_ID+" = '"+ID+"';";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.close();
    }

//    public int updateStudStatus(DbModel dbModel){
//        SQLiteDatabase db=this.getReadableDatabase();
//        ContentValues values=new ContentValues();
//        values.put(BOOK,dbModel.getBook());
//        return db.update(STUD_TABLE,values,STUD_ID+"=?",new String[]{String.valueOf(dbModel.getStud_id())});
//    }
//    public void updateStudStatus(String ID, int status) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values =  new ContentValues();
//        values.put(BOOK, status);
//        db.update(STUD_TABLE, values, STUD_ID+" = '"+ID +"'", null);
//        db.close();
//    }
    public void updateStudStatus(String ID, int status) {
        String query="update "+STUD_TABLE+" set "+BOOK+" = "+status+" where "+STUD_ID+" = '"+ID+"';";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.close();
    }

    public int getStudCount(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+STUD_TABLE;
        Cursor cursor=db.rawQuery(query,null);
        return cursor.getCount();
    }
    public int getIssueCount(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+ISSUED_TABLE;
        Cursor cursor=db.rawQuery(query,null);
        return cursor.getCount();
    }
    public int getBooksCount(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+BOOKS_TABLE;
        Cursor cursor=db.rawQuery(query,null);
        return cursor.getCount();
    }

    public void deleteIssueBook(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(ISSUED_TABLE,BOOK_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public DbModel getSingleBook(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + BOOKS_TABLE + " WHERE " + BOOK_ID + " = '" + id +"'",null);
//        Cursor cursor = database.rawQuery("SELECT * FROM " + BOOKS_TABLE + " WHERE " + BOOK_ID + "= 'B12'" ,null);
        DbModel dbModel=new DbModel();
        Log.d("TAG", "getSingleBook: "+cursor.moveToFirst());
         if(cursor.moveToFirst()){
            Log.d("TAG", "getSingleBook: "+cursor.getString(0)+" dha "+id);
            if(cursor.getString(0).equals(id)) {
                do {
                    dbModel.setBook_id(cursor.getString(0));
                    dbModel.setBook_title(cursor.getString(1));
                    dbModel.setBook_auth(cursor.getString(2));
                    dbModel.setBook_pub(cursor.getString(3));
                    dbModel.setStatus(Integer.parseInt(cursor.getString(4)));
                } while (cursor.moveToNext());
                return dbModel;
            }
        }
        cursor.close();
        database.close();
        return dbModel;
    }

    public DbModel getSingleStud(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + STUD_TABLE + " WHERE " + STUD_ID + " = '" + id+"'",null);
        DbModel dbModel=new DbModel();
        Log.d("TAG", "getSingleBook: "+cursor.moveToFirst());
        if(cursor.moveToFirst()){
            Log.d("TAG", "getSingleBook: "+cursor.getString(0)+" dha "+id);
            if(cursor.getString(0).equals(id)) {
                do {
                    dbModel.setStud_id(cursor.getString(0));
                    dbModel.setStud_name(cursor.getString(1));
                    dbModel.setStud_pass(cursor.getString(2));
                    dbModel.setStud_ph(cursor.getString(3));
                    dbModel.setStud_mail(cursor.getString(4));
                    dbModel.setBook(Integer.parseInt(cursor.getString(5)));
                } while (cursor.moveToNext());
                return dbModel;
            }
        }
        cursor.close();
        database.close();
        return dbModel;
    }
}
