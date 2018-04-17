package monash.zi.fit3027_portfolio_andy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Zi C on 09-Mar-18.
 */

public class BookAdapter extends BaseAdapter {
    private Context currentContext;
    private ArrayList<Book> bookArrayList;

    public BookAdapter(Context con, ArrayList<Book> book) {
        currentContext = con;
        bookArrayList = book;
    }
    @Override
    public int getCount() { return bookArrayList.size(); }

    @Override
    public Object getItem(int i) { return bookArrayList.get(i); }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Check if view already exists. If not inflate it
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) currentContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // Create a list item based off layout definition
            view = inflater.inflate(R.layout.list_book_item, null);
        }
        // Assign values to the TextViews using Person Object
        TextView bookItemView = view.findViewById(R.id.bookItemTitleTextView);
        TextView bookAuthorView = view.findViewById(R.id.bookItemAuthorTextView);
        bookItemView.setText(bookArrayList.get(i).getBookTitle());
        bookAuthorView.setText(bookArrayList.get(i).getBookAuthor());

        return view;
    }
}
