package monash.zi.fit3027_portfolio_andy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewBookActivity extends AppCompatActivity {

    private TextView bookTitleTextView;
    private TextView bookISBNTextView;
    private TextView bookAuthorTextView;
    private TextView bookPublisherTextView;
    private TextView bookEditionTextView;
    private TextView bookPublicationYearTextView;
    private TextView bookGenreTextView;
    private TextView bookDescriptionTextView;

    private Book bookToView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        //init Text Views
        bookTitleTextView = findViewById(R.id.titleTextView);
        bookISBNTextView = findViewById(R.id.isbnTextView);
        bookAuthorTextView = findViewById(R.id.authorTextView);
        bookPublisherTextView = findViewById(R.id.publisherTextView);
        bookEditionTextView = findViewById(R.id.editionTextView);
        bookPublicationYearTextView = findViewById(R.id.publishYearTextView);
        bookGenreTextView = findViewById(R.id.genreTextView);
        bookDescriptionTextView = findViewById(R.id.descriptionTextview);

        // Get Book Parcel
        bookToView = getIntent().getParcelableExtra("bookToView");

        // Set text view texts to reflect book contents
        bookTitleTextView.setText(bookToView.getBookTitle());
        bookISBNTextView.setText(String.format("ISBN: %s", bookToView.getBookISBN()));
        bookAuthorTextView.setText(String.format("By: %s", bookToView.getBookAuthor()));
        bookPublisherTextView.setText(String.format("Published by: %s", bookToView.getBookPublisher()));
        bookEditionTextView.setText(String.format("Edition: %s", bookToView.getBookEdition()));
        bookPublicationYearTextView.setText(String.format("Published in: %s", bookToView.getBookPublicationYear()));
        bookGenreTextView.setText(String.format("Genre: %s", bookToView.getBookGenre()));
        bookDescriptionTextView.setText(String.format("%s", bookToView.getBookDescription()));
    }
}
