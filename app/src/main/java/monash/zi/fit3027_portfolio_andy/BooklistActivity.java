package monash.zi.fit3027_portfolio_andy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class BooklistActivity extends AppCompatActivity {

    // Unique Identifier for receiving activity result
    public static final int ADD_BOOK_REQUEST = 1;
    private ListView listView;
    private BookAdapter adapter;
    private ArrayList<Book> bookArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);
        // Initialize the Person List
        bookArrayList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.bookListView);
        // Create Adapter and associate it with our PersonList
        adapter = new BookAdapter(this, bookArrayList);
        listView.setAdapter(adapter);
        updateListCount();
    }
    // Create our menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_booklist_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.gotoAddBookButton:
                Intent i = new Intent(this, AddBookActivity.class);
                startActivityForResult(i, ADD_BOOK_REQUEST);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Overridden method to get results from any activity launched from this activity pre-viously
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_BOOK_REQUEST) {
            if(resultCode == RESULT_OK) {
                // Get the person object from the intent and add it to our list
                Book newBook = data.getParcelableExtra("result");
                bookArrayList.add(newBook);
                adapter.notifyDataSetChanged();
                updateListCount();
            }
        }
    }
    // Function to initialize default values for the person list (Change this later)
    private void updateListCount() {
        // Get total size of person list & set title
        int numPeople = bookArrayList.size();
        setTitle("All Books: " + numPeople);
    }
}
