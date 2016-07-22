package com.roomorama.caldroid;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Customize the weekday gridview
 */
public class WeekdayArrayAdapter extends ArrayAdapter<String> {
	public static int textColor = Color.LTGRAY;

	public WeekdayArrayAdapter(Context context, int TextViewResourceId,
			List<String> objects) {
		super(context, TextViewResourceId, objects);
	}

	// To prevent cell highlighted when clicked
	@Override
	public boolean areAllItemsEnabled() {
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		return false;
	}

	// Set color to gray and text size to 12sp
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// To customize text size and color
		TextView TextView = (TextView) super.getView(position, convertView,
				parent);

		// Set content
		String item = getItem(position);

		// Show smaller text if the size of the text is 4 or more in some
		// locale
		if (item.length() <= 3) {
			TextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
		} else {
			TextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
		}

		TextView.setTextColor(textColor);
		TextView.setGravity(Gravity.CENTER);
		return TextView;
	}

}
