package com.example.android.theshelf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class  Databasehelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "library.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "SQLiteAppLog";




    public Databasehelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "DatabaseHelper: database created");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: inside oncreate");
        String CREATE_ADMIN_TABLE = "CREATE TABLE Admin ( " +
                "Admin_ID INTEGER PRIMARY KEY not null , " +
                "Password TEXT not null, " +
                "Admin_Name TEXT not null)" ;
        db.execSQL(CREATE_ADMIN_TABLE);
        String CREATE_BOOK_TABLE = "CREATE TABLE book (" +
                "book_ID INTEGER PRIMARY KEY not null, " +
                "Admin_ID INTEGER  not null, " +
                "Title TEXT not null, " +
                "Author TEXT not null, " +
                "FOREIGN KEY(admin_id) REFERENCES admin(admin_id))";
        db.execSQL(CREATE_BOOK_TABLE);

        String CREATE_STUDENT_TABLE = "CREATE TABLE student(" +
                "student_ID INTEGER PRIMARY KEY not null, " +
                "Student_name TEXT not null, " +
                "Admin_ID INTEGER  not null,FOREIGN KEY(admin_id) REFERENCES admin(admin_id))";
        db.execSQL(CREATE_STUDENT_TABLE);
        String CREATE_DATE_TABLE ="CREATE TABLE date (" +
                "student_ID INTEGER not null, " +
                "Admin_ID INTEGER  not null," +
                "book_ID INTEGER  UNIQUE not null, " +
                "Issue_Date DATE not null, " +
                "Return_Date DATE not null,FOREIGN KEY(admin_id) REFERENCES admin(admin_id),FOREIGN KEY(book_id) REFERENCES book(book_id),FOREIGN KEY(student_id) REFERENCES student(student_id))";
        db.execSQL(CREATE_DATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Admin");
        this.onCreate(db);
    }
    public String getPassword(int id)
    {
        String password = " ";
        String selectQuery = "SELECT password FROM Admin WHERE Admin_ID = "+id ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            password = cursor.getString(0);
        }else {
            return "Wrong User ID";
        }
        cursor.close();
        db.close();
        // return user
        return password;
    }
    public void addUser(int id,String pass,String name){

        SQLiteDatabase db = this.getWritableDatabase();

        //  create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("Admin_ID", id); // get title
        values.put("PASSWORD", pass);
        values.put("Admin_Name",name);// get author

        //  insert to table
        db.insert("Admin", // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        //  close - release the reference of writable DB
        db.close();
    }
    public long addbook(int bid, int id, String name,String aname){

        SQLiteDatabase db = this.getWritableDatabase();

        //  create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("book_ID", bid); // get title
        values.put("Admin_ID", id);
        values.put("Title",name);// get author
        values.put("Author",aname);
        //  insert to table
        long insid = db.insert("Book", // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        //  close - release the reference of writable DB
        db.close();
        return insid;
    }
    public long addstudent(int sid,int aid, String stdname){

        SQLiteDatabase db = this.getWritableDatabase();

        //  create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("student_ID", sid); // get title
        values.put("Admin_ID", aid);
        values.put("Student_name",stdname);// get author


        //  insert to table
        long insid = db.insert("student", // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        //  close - release the reference of writable DB
        db.close();
        return insid;
    }

    public ArrayList<Admin>  getAllUsers() {
        ArrayList<Admin> users = new ArrayList<Admin>();

        // build the query
        String query = "SELECT  * FROM " + "Admin";

        //  get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // go over each row, build book and add it to list
        Admin user = null;
        if (cursor.moveToFirst()) {
            do {
                user= new Admin();
                user.setAdmin_Name(cursor.getString(2));
                //System.out.println("TESTid: " + Integer.parseInt(cursor.getString(0)));
                user.setAdmin_ID(cursor.getInt(0));
                user.setPassword(cursor.getString(1));

                // Add book to books
                users.add(user);
            } while (cursor.moveToNext());
        }

        Log.d(TAG, "getAllUsers() - " + users.toString());

        // return books
        return users;
    }
    public long issuebook(int sid, int bid,int A_ID,String Issue,String return_Date)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        //  create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("student_ID", sid); // get title
        values.put("book_ID",bid);
        values.put( "Admin_ID",A_ID );
        values.put( "Issue_Date",Issue );
        values.put( "Return_date",return_Date );
        //  insert to table
        long insid = db.insert("date", // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        //  close - release the reference of writable DB
        db.close();
        return insid;
    }

    public ArrayList<Book> getAllBookList() {

        ArrayList<Book> bookList;
        String sql = "SELECT book_ID,Title,Author FROM book";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery( sql, null );
        bookList = new ArrayList<Book>();
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setTitle( cursor.getString( cursor.getColumnIndex( "Title" ) ) );
                book.setBookId( Integer.parseInt( cursor.getString( cursor.getColumnIndex( "book_ID" ) ) ) );
                book.setAuthor_Name( cursor.getString( cursor.getColumnIndex( "Author" ) ) );
                bookList.add(book);
                // do what ever you want here
            } while (cursor.moveToNext());
        }


        cursor.close();
        db.close();
        return bookList;
    }
    /*  public ArrayList<Add_Student> getAllStudents() {

          ArrayList<Add_Student> studentlist;
          String sql = "SELECT student_ID,student_name,Admin_ID  FROM student";

          SQLiteDatabase db = getReadableDatabase();
          Cursor cursor = db.rawQuery( sql, null );
          studentlist = new ArrayList<Add_Student>();
          if (cursor.moveToFirst()) {
              do {
                  Add_Student stu = new Add_Student();

                  studentlist.add( stu );
                  // do what ever you want here
              } while (cursor.moveToNext());
          }

          cursor.close();
          db.close();
          return studentlist;
      } */
    public int Icheckbook(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT book_ID FROM date WHERE book_ID = "+id;


        int B_ID = 0;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            B_ID = cursor.getInt(0);

        }else {
            return 0;
        }
        cursor.close();
        db.close();
        // return user
        return B_ID;
    }
    public int checkReaderLogin(int id) {

        int reader_id = 0;
        String selectQuery = "SELECT student_ID FROM student WHERE student_ID = "+id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            reader_id = cursor.getInt(0);
            Log.e("ReaderID",reader_id+"");
        }else {
            return 0;
        }
        cursor.close();
        db.close();
        // return user
        return reader_id;
    }


    public int getBookID(int val)
    {
        int book_id =0 ;
        String selectQuery = "SELECT book_ID FROM book WHERE book_ID = "+ val ;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            book_id = cursor.getInt(0);
        }else
        {
            return 0;
        }
        cursor.close();
        db.close();
        // return user
        return book_id;
    }

    public String getReturnDate(int b_id)
    {
        String b_name = " " ;
        String selectQuery = "SELECT Return_Date FROM date WHERE book_ID = " + b_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            b_name = cursor.getString(0);
        }else
        {
            return null;
        }
        cursor.close();
        db.close();
        // return user
        return b_name;
    }
    public boolean deleteTitle(int name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("date", "book_ID" + "=" + name, null) > 0;
    }

}
