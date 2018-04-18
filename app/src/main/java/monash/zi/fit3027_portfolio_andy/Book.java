package monash.zi.fit3027_portfolio_andy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zi on 01-Mar-18.
 */

public class Book implements Parcelable{

    // Database Constants
    public static final String TABLE_NAME = "Books";
    public static final String COLUMN_TITLE = "Title";
    public static final String COLUMN_ISBN = "ISBN";
    public static final String COLUMN_AUTHOR = "Author";
    public static final String COLUMN_PUBLISHER = "Publisher";
    public static final String COLUMN_EDITION = "Edition";
    public static final String COLUMN_PUBLICATIONYEAR = "Publication_Year";
    public static final String COLUMN_GENRE = "Genre";
    public static final String COLUMN_DESCRIPTION = "Description";

    // Table create statement
    public static final String CREATE_STATEMENT =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ISBN + " INTEGER PRIMARY KEY NOT NULL, " +
                    COLUMN_TITLE + " TEXT NOT NULL, " +
                    COLUMN_AUTHOR + " TEXT NOT NULL, " +
                    COLUMN_PUBLISHER + " TEXT NOT NULL, " +
                    COLUMN_EDITION + " TEXT NOT NULL, " +
                    COLUMN_PUBLICATIONYEAR + " TEXT NOT NULL, " +
                    COLUMN_GENRE + " TEXT NOT NULL, " +
                    COLUMN_DESCRIPTION + " TEXT NOT NULL " +
                    ");";

    private String bookTitle;
    private String bookISBN;
    private String bookAuthor;
    private String bookPublisher;
    private String bookEdition;
    private String bookPublicationYear;
    private String bookGenre;
    private String bookDescription;

    public Book(Parcel in) {
        this.bookTitle = in.readString();
        this.bookISBN = in.readString();
        this.bookAuthor = in.readString();
        this.bookPublisher = in.readString();
        this.bookEdition = in.readString();
        this.bookPublicationYear = in.readString();
        this.bookGenre = in.readString();
        this.bookDescription = in.readString();
    }

    public Book(String bookTitle, String bookISBN, String bookAuthor, String bookPublisher, String bookEdition, String bookPublicationYear, String bookGenre, String bookDescription) {
        this.bookTitle = bookTitle;
        this.bookISBN = bookISBN;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.bookEdition = bookEdition;
        this.bookPublicationYear = bookPublicationYear;
        this.bookGenre = bookGenre;
        this.bookDescription = bookDescription;
    }

    // An example book
    public Book() {
        this.bookTitle = "";
        this.bookISBN = "";
        this.bookAuthor = "";
        this.bookPublisher = "";
        this.bookEdition = "";
        this.bookPublicationYear = "";
        this.bookGenre = "";
        this.bookDescription = "";
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) { return new Book(in); }
        @Override
        public Book[] newArray(int size) { return new Book[size]; }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(bookTitle);
        parcel.writeString(bookISBN);
        parcel.writeString(bookAuthor);
        parcel.writeString(bookPublisher);
        parcel.writeString(bookEdition);
        parcel.writeString(bookPublicationYear);
        parcel.writeString(bookGenre);
        parcel.writeString(bookDescription);
    }

    // Book summary
    @Override
    public String toString() {
        // Formatting the ISBN was considered, but not included for the sake of simplicity,
        // and assumption of user input of a 13-digit number.

        return String.format(
                "Name: %s\n" +
                        "ISBN: %s\n" +
                        "Author: %s\n" +
                        "Publisher: %s\n" +
                        "Book Edition: %s\n" +
                        "Publication Year: %s\n" +
                        "Genre: %s\n\n" +
                        "Description:\n%s\n",
                getBookTitle(),getBookISBN(),getBookAuthor(),getBookPublisher(),
                getBookEdition(),getBookPublicationYear(),getBookGenre(),getBookDescription());
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public String getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(String bookEdition) {
        this.bookEdition = bookEdition;
    }

    public String getBookPublicationYear() {
        return bookPublicationYear;
    }

    public void setBookPublicationYear(String bookPublicationYear) {
        this.bookPublicationYear = bookPublicationYear;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    // id is set via ISBN
    public long get_id() {
        return Long.parseLong(this.getBookISBN());
    }

    public String getBookSummary() {
        // Formatting the ISBN was considered, but not included for the sake of simplicity,
        // and assumption of user input of a 10-digit number.

        return String.format(
                "Name: %s\n" +
                "ISBN: %s\n" +
                "Author: %s\n" +
                "Publisher: %s\n" +
                "Book Edition: %s\n" +
                "Publication Year: %s\n" +
                "Genre: %s\n\n" +
                "Description:\n%s\n",
                getBookTitle(),getBookISBN(),getBookAuthor(),getBookPublisher(),
                getBookEdition(),getBookPublicationYear(),getBookGenre(),getBookDescription());
    }
}
