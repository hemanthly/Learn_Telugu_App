package miwok.example.miwok;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miwok.R;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = new MediaPlayer();

    private AudioManager myaudioManager;

    private AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Pause playback immediately
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);  // because it's better here from start since pronunciation will be 1 or 2 sec.
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        //stop Playback and release resources.
                        releaseMediaPlayer();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Your app has been granted audio focus again
                        // Raise volume to normal, restart playback if necessary
                        //Resume Playback
                        mediaPlayer.start();
                    }
                }
            };

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            //Toast.makeText(NumbersActivity.this, "song finished.", Toast.LENGTH_SHORT).show();
            // Abandon audio focus when playback complete
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_xml_layout);

        //Word w = new Word("kikuchu","one");
        //words.add(w);
        myaudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Create an array of words
        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("okati", "one",R.drawable.number_one,R.raw.bhalegundi_baalaa));
        words.add(new Word("rendu","two",R.drawable.number_two,R.raw.saranga_dariya));
        words.add(new Word("moodu","three",R.drawable.number_three,R.raw.chuttu_chutti));
        words.add(new Word("naalugu","four",R.drawable.number_four,R.raw.egirey_mabbulalona));
        words.add(new Word("aidhu","five",R.drawable.number_five,R.raw.kanne_kanne));
        words.add(new Word("aaru","six",R.drawable.number_six,R.raw.motta_modatisaari));
        words.add(new Word("edu","seven",R.drawable.number_seven,R.raw.okey_oka_lokam));
        words.add(new Word("enimidhi","eight",R.drawable.number_eight,R.raw.violin_song));
        words.add(new Word("thommidhi","nine",R.drawable.number_nine,R.raw.yaa_yaa));
        words.add(new Word("padhi","ten",R.drawable.number_ten,R.raw.kanne_kanne));

        WordAdapter itemsAdapter = new WordAdapter(this, words,R.color.category_numbers);

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

                //Log.v("NumbersActivity", "Current word: " + word);
                releaseMediaPlayer();
                Word word = words.get(position);

                int result = myaudioManager.requestAudioFocus(afChangeListener,AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
               if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId() );
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
                else
                {
                    Intent onError = new Intent(NumbersActivity.this, error_activity.class);
                    startActivity(onError);
                }

            }
        });

/**
 * Clean up the media player by releasing its resources.
 */



       // Log.d("NumbersActivity", "Word at index 0: " + words.get(0));
        //Log.d("NumbersActivity", "Word at index 1: " + words.get(1));
        //Log.d("NumbersActivity", "Word at index 2: " + words.get(2));

    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.pause();
        //abandon audio focus when playback complete
        myaudioManager.abandonAudioFocus(afChangeListener);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        mediaPlayer.start();
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

            myaudioManager.abandonAudioFocus(afChangeListener);
        }
    }

}