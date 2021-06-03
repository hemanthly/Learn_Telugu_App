package miwok.example.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miwok.R;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    private ArrayList<Word> words;
    private WordAdapter itemsAdapter;
    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            //Toast.makeText(ColorsActivity.this, "song finished.", Toast.LENGTH_SHORT).show();
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_xml_layout);

        //Create an array of words
        words = new ArrayList<Word>();
        //Word w = new Word("kikuchu","one");
        //words.add(w);
        words.add(new Word("Nalupu rangu", "Black",R.drawable.color_black,R.raw.phrase_im_feeling_good));
        words.add(new Word("Blue","Bulugu or Neelamu",R.raw.phrase_im_feeling_good));
        words.add(new Word("Godhuma Rangu","Brown",R.drawable.color_brown,R.raw.phrase_im_feeling_good));
        words.add(new Word("Raktha Varnamu","Crimson Red",R.drawable.color_red,R.raw.phrase_im_feeling_good));
        words.add(new Word("Boodidha Rangu","Gray",R.drawable.color_gray,R.raw.phrase_im_feeling_good));
        words.add(new Word("Aakupaccha","Green",R.drawable.color_green,R.raw.phrase_im_feeling_good));
        words.add(new Word("Neelamu","Indigo",R.raw.phrase_im_feeling_good));
        words.add(new Word("Narinja rangu","Orange",R.raw.phrase_im_feeling_good));
        words.add(new Word("Erupu rangu","Red",R.drawable.color_red,R.raw.phrase_im_feeling_good));
        words.add(new Word("Etuka rangu","Scarlet",R.raw.phrase_im_feeling_good));
        words.add(new Word("Oodhaa rangu","Violet",R.raw.phrase_im_feeling_good));
        words.add(new Word("Thelupu","White",R.drawable.color_white,R.raw.phrase_im_feeling_good));
        words.add(new Word("Pasupu","Yellow",R.drawable.color_mustard_yellow,R.raw.phrase_im_feeling_good));




        itemsAdapter = new WordAdapter(this, words, R.color.category_colors);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list_view);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudioResourceId() );
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(mOnCompletionListener);
            }
        });


        // Log.d("NumbersActivity", "Word at index 0: " + words.get(0));
        //Log.d("NumbersActivity", "Word at index 1: " + words.get(1));
        //Log.d("NumbersActivity", "Word at index 2: " + words.get(2));

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }
}