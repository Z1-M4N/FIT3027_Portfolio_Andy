package monash.zi.fit3027_portfolio_andy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zi on 01-Mar-18.
 */

public class Book implements Parcelable{
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

    public String getBookSummary() {
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
}
