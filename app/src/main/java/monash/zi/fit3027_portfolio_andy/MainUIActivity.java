package monash.zi.fit3027_portfolio_andy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainUIActivity extends AppCompatActivity {
    // This activity is the main screen to contain the buttons navigate between add a book screen, or
    // the main book list.

    // Unique Identifier for receiving activity result
    public static final int ADD_BOOK_REQUEST = 1;

    Button addBook;
    Button bookList;

    private ArrayList<Book> bookArrayList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        bookArrayList = new ArrayList<>(databaseHelper.getAllBooks().values());

        addBook = findViewById(R.id.gotoAddBookButton);
        bookList = findViewById(R.id.gotoBookListButton);

        // Initialize add book button
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainUIActivity.this, AddBookActivity.class);
                startActivityForResult(i, ADD_BOOK_REQUEST);
            }
        });

        bookList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainUIActivity.this, BooklistActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // refresh from a book edit
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_BOOK_REQUEST) {
            if(resultCode == RESULT_OK) {
                boolean canAddBook = true;
                // Get the Book object from the intent and add it to our list
                Book newBook = data.getParcelableExtra("result");

                //check for existing book through ISBN
                for (Book book : bookArrayList)
                {
                    if (newBook.getBookISBN().equals(book.getBookISBN())) {
                        Toast.makeText(MainUIActivity.this,
                                "Book already exists in database",
                                Toast.LENGTH_SHORT).show();
                        canAddBook = false;
                    }
                }

                // If book doesn't exist (id'd by ISBN) then add it
                if (canAddBook)
                {
                    databaseHelper.addBook(newBook);
                    bookArrayList.add(newBook);

                    Toast.makeText(MainUIActivity.this,
                            "Successfully add book to database",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
