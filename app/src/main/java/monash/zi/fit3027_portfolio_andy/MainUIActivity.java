package monash.zi.fit3027_portfolio_andy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainUIActivity extends AppCompatActivity {
    // This activity is the main screen to contain the buttons navigate between add a book screen, or
    // the main book list.

    Button addBook;
    Button bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);

        addBook = findViewById(R.id.gotoAddBookButton);
        bookList = findViewById(R.id.gotoBookListButton);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainUIActivity.this, AddBookActivity.class));
            }
        });
        bookList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainUIActivity.this, BooklistActivity.class));
            }
        });
    }
}
