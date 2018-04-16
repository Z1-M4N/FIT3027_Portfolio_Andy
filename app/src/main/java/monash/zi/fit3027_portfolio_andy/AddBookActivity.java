package monash.zi.fit3027_portfolio_andy;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBookActivity extends AppCompatActivity implements View.OnClickListener{

    EditText bookTitleText;
    EditText bookISBNText;
    EditText bookAuthorText;
    EditText bookPublisherText;
    EditText bookEditionText;
    EditText bookPublicationYearText;
    EditText bookGenreText;
    EditText bookDescriptionText;

    FloatingActionButton createBookButton;

    Book newBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // New default Book
        newBook = new Book();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        //Instantiate UI elements
        bookTitleText = findViewById(R.id.bookTitleInput);
        bookISBNText = findViewById(R.id.bookISBNInput);
        bookAuthorText = findViewById(R.id.bookAuthorInput);
        bookPublisherText = findViewById(R.id.bookPublisherInput);
        bookEditionText = findViewById(R.id.bookEditionInput);
        bookPublicationYearText = findViewById(R.id.bookPublicationYearInput);
        bookGenreText = findViewById(R.id.bookGenreInput);
        bookDescriptionText = findViewById(R.id.bookDescriptionInput);
    }

    @Override
    public void onClick(View view) {
        System.out.println("Logging");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_add_book_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.confirmBookAction:
                // get user input
                String bookTitle = bookTitleText.getText().toString();
                String bookISBN = bookISBNText.getText().toString();
                String bookAuthor = bookAuthorText.getText().toString();
                String bookPublisher = bookPublisherText.getText().toString();
                String bookEdition = bookEditionText.getText().toString();
                String bookPublicationYear = bookPublicationYearText.getText().toString();
                String bookGenre = bookGenreText.getText().toString();
                String bookDescription = bookDescriptionText.getText().toString();

                // run validation

                // set Book to user input
                newBook = new Book();
                newBook.setBookTitle(bookTitle);
                newBook.setBookISBN(bookISBN);
                newBook.setBookAuthor(bookAuthor);
                newBook.setBookPublisher(bookPublisher);
                newBook.setBookEdition(bookEdition);
                newBook.setBookPublicationYear(bookPublicationYear);
                newBook.setBookGenre(bookGenre);
                newBook.setBookDescription(bookDescription);

                // create new intent and put in book object
                Intent newIntent = new Intent(this, AddBookActivity.class);
                newIntent.putExtra("Book", newBook);

                startActivity(newIntent);
                finish();

                System.out.println("Pressed it!");
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
