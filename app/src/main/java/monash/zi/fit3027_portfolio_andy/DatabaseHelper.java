package monash.zi.fit3027_portfolio_andy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by unkno on 09-Mar-18.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    // Set database and table properties
    public static final String DATABASE_NAME = "books.sqlite";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Book.CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Book.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addBook(Book book) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        // Form data/values to add
        ContentValues values = new ContentValues();
        values.put(Book.COLUMN_TITLE, book.getBookTitle());
        values.put(Book.COLUMN_ISBN, book.getBookISBN());
        values.put(Book.COLUMN_AUTHOR, book.getBookAuthor());
        values.put(Book.COLUMN_PUBLISHER, book.getBookPublisher());
        values.put(Book.COLUMN_EDITION, book.getBookEdition());
        values.put(Book.COLUMN_PUBLICATIONYEAR, book.getBookPublicationYear());
        values.put(Book.COLUMN_GENRE, book.getBookGenre());
        values.put(Book.COLUMN_DESCRIPTION, book.getBookDescription());

        // Add row
        sqLiteDatabase.insert(Book.TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }

//    public Book getBook(String title) {
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//
//        Cursor cursor = sqLiteDatabase.query();
//    }

    public HashMap<Long, Book> getAllBooks() {
        HashMap<Long, Book> books = new LinkedHashMap<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + Book.TABLE_NAME, null);

        while (cursor.moveToNext()) {
            // Getting the different attributes of a book
            Book book = new Book(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );
            books.put(Long.parseLong(book.getBookISBN()),book);
        }

        cursor.close();
        sqLiteDatabase.close();

        return books;
    }

    public int getBookCount() {
        return 1;
    }

    public int updateBook(Book book) {
        return 1;
    }

    public void deleteBook(Book book) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(Book.TABLE_NAME, Book.COLUMN_ISBN + " = ?",
                new String[] {String.valueOf(book.getBookISBN())});
    }
}
