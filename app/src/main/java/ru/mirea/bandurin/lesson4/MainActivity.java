package ru.mirea.bandurin.lesson4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.mirea.bandurin.lesson4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ImageView img_song;
    private ImageView prev;
    private ImageView pause;
    private ImageView next;
    private TextView song_name;
    private TextView singer;
    private int count;
    private boolean p_i;
    private String[] song_names;
    private String[] singers;
    private String[] s_img;
    private int id;
    final static String countVariableKey = "COUNT_VARIABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pause=binding.pause;
        next=binding.next;
        prev=binding.prev;
        song_name=binding.tvSong;
        singer=binding.tvSinger;
        img_song=binding.imageView;
        pause.setMaxWidth(50);
        pause.setMaxWidth(50);
        next.setImageResource(R.drawable.next);
        next.setMaxHeight(50);
        next.setMaxWidth(50);
        prev.setImageResource(R.drawable.prev);
        prev.setMaxHeight(50);
        prev.setMaxWidth(50);
        song_name.setGravity(Gravity.CENTER);
        singer.setGravity(Gravity.CENTER);
        song_names = new String[3];
        song_names[0]="Californication";
        song_names[1]="Большие города";
        song_names[2]="Smells Like Teen Spirit";
        singers = new String[3];
        singers[0]="Red Hot Chili Peppers";
        singers[1]="Би-2";
        singers[2]="Nirvana";
        s_img = new String[3];
        s_img[0]="cali";
        s_img[1]="bi2";
        s_img[2]="smel";
        count=1;
        song_name.setText(song_names[count]);
        singer.setText(singers[count]);
        id = getResources().getIdentifier(s_img[count], "drawable", getPackageName());
        img_song.setImageResource(id);
        p_i=false;
        pause.setImageResource(R.drawable.pause);
        //pause.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.play));
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p_i){
                    pause.setImageResource(R.drawable.pause);
                    p_i=false;
                }
                else{
                    pause.setImageResource(R.drawable.play);
                    p_i=true;
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count!=2){
                    count+=1;

                }
                else{
                    count=0;
                }
                song_name.setText(song_names[count]);
                singer.setText(singers[count]);
                id = getResources().getIdentifier(s_img[count], "drawable", getPackageName());
                img_song.setImageResource(id);
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count!=0){
                    count-=1;
                }
                else{
                    count=2;
                }
                song_name.setText(song_names[count]);
                singer.setText(singers[count]);
                id = getResources().getIdentifier(s_img[count], "drawable", getPackageName());
                img_song.setImageResource(id);
            }
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        
        outState.putInt(countVariableKey, count);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        count = savedInstanceState.getInt(countVariableKey);
        song_name.setText(song_names[count]);
        singer.setText(singers[count]);
        id = getResources().getIdentifier(s_img[count], "drawable", getPackageName());
        img_song.setImageResource(id);
    }
}