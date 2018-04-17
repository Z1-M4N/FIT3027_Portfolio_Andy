package monash.zi.fit3027_portfolio_andy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Zi C on 09-Mar-18.
 */

public class BookAdapter extends BaseAdapter implements Filterable{
    private Context currentContext;
    private ArrayList<Book> bookArrayList;
    private ArrayList<Book> filteredBookList;
    private BookFilter filter;

    public BookAdapter(Context con, ArrayList<Book> book) {
        currentContext = con;
        bookArrayList = book;
        filteredBookList = bookArrayList;

    }
    @Override
    public int getCount() { return filteredBookList.size(); }

    @Override
    public Object getItem(int i) { return filteredBookList.get(i); }

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
        bookItemView.setText(filteredBookList.get(i).getBookTitle());
        bookAuthorView.setText(filteredBookList.get(i).getBookAuthor());

        return view;
    }

    @Override
    public Filter getFilter() {
        if(filter == null) {
            filter = new BookFilter();
        }
        return filter;
    }

    private class BookFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint != null && constraint.length() > 0) {
                ArrayList<Book> tempList = new ArrayList<>();
                for(Book book : bookArrayList) {
                    if(book.getBookTitle().toLowerCase().
                            contains(constraint.toString().
                                    toLowerCase()))
                        tempList.add(book);
                }
                results.count = tempList.size();
                results.values = tempList;
            }
            else {
                results.count = bookArrayList.size();
                results.values = bookArrayList;
            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredBookList = (ArrayList<Book>) results.values;
            notifyDataSetChanged();
        }
    }
}
