package com.example.healthcare.Adapter;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.healthcare.R;

public class LinksListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] titles;
    private final String[] descriptions;
    private final String[] links;

    public LinksListAdapter(Activity context, String[] titles, String[] descriptions, String[] links) {
        super(context, R.layout.linkslist, titles);

        this.context = context;
        this.titles = titles;
        this.descriptions = descriptions;
        this.links = links;

    }



    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.linkslist, null, true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView description = (TextView) rowView.findViewById(R.id.description);
        TextView link = (TextView) rowView.findViewById(R.id.link);

        titleText.setText(titles[position]);
        description.setText(descriptions[position]);
        link.setText(Html.fromHtml(links[position]));
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link.setLinkTextColor(Color.BLUE);


        return rowView;

    }
}
