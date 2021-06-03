package miwok.example.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miwok.R;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    private ArrayList<Word> words;
    private WordAdapter itemsAdapter;
    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            //Toast.makeText(PhrasesActivity.this, "song finished.", Toast.LENGTH_SHORT).show();
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
        words.add(new Word("susvaagatam", "Welcome ",R.raw.phrase_im_feeling_good));
        words.add(new Word("meeru aelaa unnaaru?","How are you?",R.raw.phrase_im_feeling_good));
        words.add(new Word("Long time no see","chaalaa kaalamaiṅdhi mimmalni choosi",R.raw.phrase_im_feeling_good));
        words.add(new Word("mimmalni kalavadaṅ chaalaa saṅthoashaṅgaa uṅdhi","Pleased to meet you",R.raw.phrase_im_feeling_good));
        words.add(new Word("shubha prayaanam","Bon voyage / Have a good journey",R.raw.phrase_im_feeling_good));
        words.add(new Word("nannu prashaaṅthathoa vadhili pettaṅdi","Leave me alone!",R.raw.phrase_im_feeling_good));

        words.add(new Word("mee aaroagyaṅ thvaraloa kudhuta padaalani koarukuṅtunnaanu","Get well soon",R.raw.phrase_im_feeling_good));
        words.add(new Word("meeru thelugu maatlaadathaaraa?","Do you speak Telugu?",R.raw.phrase_im_feeling_good));
        words.add(new Word("dhigulu chend(h)ad(h)(dh)u","Don't worry",R.raw.phrase_im_feeling_good,R.raw.phrase_im_feeling_good));

        words.add(new Word("dheeni dhara eṅtha?","How much is this?",R.raw.phrase_im_feeling_good));
        words.add(new Word("dhayachaesi mallee cheppaṅdi","Please say that again",R.raw.phrase_im_feeling_good));
        words.add(new Word("naenu ninnu praemisthunnaanu","I love you",R.raw.phrase_im_feeling_good));
        words.add(new Word("naathoa naatyaṅ chaesae kuthoohalaṅ unnadhaa?","Would you like to dance with me?",R.raw.phrase_im_feeling_good));




        itemsAdapter = new WordAdapter(this, words,R.color.category_phrases);

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
                mediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId() );
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