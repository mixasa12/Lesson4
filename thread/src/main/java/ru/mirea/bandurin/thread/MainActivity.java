package ru.mirea.bandurin.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ru.mirea.bandurin.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
private Button sub;
private EditText les;
private EditText days;
private TextView lesson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding	= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sub=binding.button;
        les=binding.lesson;
        days=binding.days;
        lesson=binding.textView;
        sub.setOnClickListener(new View.OnClickListener(){
            @Override
            public	void	onClick(View	v)	{
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        float lesson1 = Float.parseFloat(les.getText().toString())/Float.parseFloat(days.getText().toString());
                        lesson.post(new Runnable() {
                            @Override
                            public void run() {
                                lesson.setText("Среднее кол-во пар в день " +Float.toString(lesson1));
                            }
                        });
                    }
                };
                Thread thread  = new Thread(runnable);
                thread.start();
        }});

}
}