package io.github.kokoresh.timelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(30, 30, 30, 30);
        textView.setTextSize(17);
        textView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        return textView;
    }

    private View createLineView() {
        View view = new View(this);
        view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        return view;
    }

    private void writeLine(String line) {
        LinearLayout listView = findViewById(R.id.list_view);
        listView.addView(createTextView(line), new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        listView.addView(createLineView(), new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 5));
    }

    private void clearScreen() {
        ((LinearLayout)findViewById(R.id.list_view)).removeAllViews();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: add change
        Day currentDay = Parser.getCurrentDay(getResources().getXml(R.xml.b8102a));
        setTitle(currentDay.getName());
        for(Lesson i: currentDay.getLessons()) {
            writeLine(i.toString());
        }
    }
}
