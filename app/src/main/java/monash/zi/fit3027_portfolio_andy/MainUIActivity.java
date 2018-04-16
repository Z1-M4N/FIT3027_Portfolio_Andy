package monash.zi.fit3027_portfolio_andy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainUIActivity extends AppCompatActivity {
    // This activity is the main screen to contain the buttons navigate between adda book screen, or
    // the main book list.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
    }
}
