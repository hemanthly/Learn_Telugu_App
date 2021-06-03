package miwok.example.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.miwok.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Find the View that shows the numbers category

       TextView numbers = (TextView)findViewById(R.id.numbers);
       numbers.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
               startActivity(numbersIntent);
           }
       });

        TextView family = (TextView)findViewById(R.id.family);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent FamilyIntent = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(FamilyIntent);
            }
        });

        TextView colors = (TextView)findViewById(R.id.colors);
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ColorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(ColorsIntent);
            }
        });

        TextView phrases = (TextView)findViewById(R.id.phrases);
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PhrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(PhrasesIntent);

            }
        });
       //Set a clicklistener on that View

    }

    public void ListNumbers(View view)
    {
        Intent intent = new Intent(this, NumbersActivity.class);
        startActivity(intent);
    }

    public void ListFamilyMembers(View view)
    {
        Intent intent = new Intent(this, FamilyActivity.class);
        startActivity(intent);
    }

    public void ListColors(View view)
    {
        Intent intent = new Intent(this, ColorsActivity.class);
        startActivity(intent);
    }

    public void ListPhrases(View view)
    {
        Intent intent = new Intent(this, PhrasesActivity.class);
        startActivity(intent);
    }

    private void onClick(View view) {
        Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
        startActivity(numbersIntent);
    }

}