package miwok.example.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.miwok.R;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mcolorResourceId;
    private MediaPlayer mediaPlayer;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        super(context,0, words);
        mcolorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // return super.getView(position, convertView, parent);

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_layout, parent, false);
        }
// Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWord = getItem(position);
// Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.telugu_one);
        // Get the version name from the current Word object and
        // set this text on the name TextView
        nameTextView.setText(currentWord.getTeluguTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.english_one);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText(currentWord.getEnglishTranslation());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        // Get the image resource ID from the current Word object and
        // set the image to iconView
        if(currentWord.hasImage())
        {
            iconView.setImageResource(currentWord.getImageResourceId());
            //making sure even if it's accidentally made hidden , make the image visible
            iconView.setVisibility(View.VISIBLE);
        }
        else
        {
            iconView.setVisibility(View.INVISIBLE);
        }

        View textContainer = listItemView.findViewById(R.id.text_container);

        int color = ContextCompat.getColor(getContext(), mcolorResourceId );

        textContainer.setBackgroundColor(color);


        return listItemView;


    }
}
