package monash.zi.fit3027_portfolio_andy;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class BooklistActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    FloatingActionButton addBookFAB;

    // Unique Identifier for receiving activity result
    public static final int ADD_BOOK_REQUEST = 1;
    public static final int UPDATE_BOOK_REQUEST = 2;

    private ListView listView;
    private BookAdapter adapter;
    private ArrayList<Book> bookArrayList;
    private SearchView searchView;
    private DatabaseHelper databaseHelper;

    private Book prevBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);

        // Get db Handler and values from database
        databaseHelper = new DatabaseHelper(getApplicationContext());
        bookArrayList = new ArrayList<>(databaseHelper.getAllBooks().values());

        System.out.println(bookArrayList.toString());

        // initialize FAB
        addBookFAB = findViewById(R.id.gotoAddBookFAB);
        addBookFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BooklistActivity.this, AddBookActivity.class);
                startActivityForResult(i, ADD_BOOK_REQUEST);
            }
        });

        // Initialize the Book List
//        bookArrayList = new ArrayList<>();
        listView = findViewById(R.id.bookListView);

        // Create Adapter and associate it with our BookList
        adapter = new BookAdapter(this, bookArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        updateListCount();
    }

    // Create our menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_booklist_actions, menu);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.searchBookAction);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    // For action menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.gotoAddBookFAB:
                Intent i = new Intent(this, AddBookActivity.class);
                startActivityForResult(i, ADD_BOOK_REQUEST);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Overridden method to get results from any activity launched from this activity previously
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // refresh from a book edit
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case ADD_BOOK_REQUEST : {
                if(resultCode == RESULT_OK) {
                    boolean canAddBook = true;
                    // Get the Book object from the intent and add it to our list
                    Book newBook = data.getParcelableExtra("result");

                    //check for existing book through ISBN
                    for (Book book : bookArrayList)
                    {
                        if (newBook.getBookISBN().equals(book.getBookISBN())) {
                            Toast.makeText(BooklistActivity.this,
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

                        adapter.notifyDataSetChanged();
                        updateListCount();

                        Snackbar.make(findViewById(R.id.bookListActivityCoordLayoutView), "Successfully created book.",
                                Snackbar.LENGTH_SHORT).show();
                    }
                }
            }

            case UPDATE_BOOK_REQUEST : {
                if(resultCode == RESULT_OK) {
                    // Get the Book object from the intent and add it to our list
                    Book newBook = data.getParcelableExtra("result");

                    // check for existing book through ISBN
                    for (Book book : bookArrayList)
                    {
                        if (prevBook.getBookISBN().equals(book.getBookISBN())) {

                            databaseHelper.updateBook(book, newBook);
                            bookArrayList.remove(book);
                            bookArrayList.add(newBook);

                            adapter.notifyDataSetChanged();
                            updateListCount();

                            Toast.makeText(BooklistActivity.this,
                                    "Book updated in database",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }


    }

    // Function to initialize default values for the Book list (Change this later)
    private void updateListCount() {
        // Get total size of Book list & set title
        int numPeople = bookArrayList.size();
        setTitle("Number of Books: " + numPeople);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.getFilter().filter(s);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
        // start a new activity,
        // parcelable pass into the new activity

        Intent newIntent = new Intent(BooklistActivity.this, ViewBookActivity.class);
        newIntent.putExtra("bookToView", bookArrayList.get(position));
        startActivity(newIntent);

        Toast.makeText(BooklistActivity.this, bookArrayList.get(position).getBookTitle(), Toast.LENGTH_SHORT).show();
    }

    // Currently set a Longclick to delete a book.
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int
            position, long l) {
        // Build a dialog to delete item
        AlertDialog.Builder builder =
                new AlertDialog.Builder(view.getContext());
        builder.setTitle("Edit or Remove Book");
        builder.setMessage("Do you wish to Edit or Remove this Book?");
        builder.setNeutralButton("Edit",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Remove Book from list and database
                        Book book = bookArrayList.get(position);

                        prevBook = bookArrayList.get(position);

                        // Start activity with edit intent
                        Intent editIntent = new Intent(BooklistActivity.this, AddBookActivity.class);
                        editIntent.putExtra("bookToEdit", book);
                        startActivityForResult(editIntent, UPDATE_BOOK_REQUEST);

                        // Update ListView
                        adapter.notifyDataSetChanged();
                        updateListCount();

                        Toast.makeText(getBaseContext(),
                                "Book has been updated.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setPositiveButton("Delete",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Remove Book from list and database
                        Book book = bookArrayList.remove(position);
                        databaseHelper.deleteBook(book);

                        // Update ListView
                        adapter.notifyDataSetChanged();
                        updateListCount();

                        Toast.makeText(getBaseContext(),
                                "Book has been deleted",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
        return false;
    }
}
