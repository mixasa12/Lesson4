package ru.mirea.bandurin.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import ru.mirea.bandurin.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Handler mainThreadHandler	=	new	Handler(Looper.getMainLooper())	{
            @Override
            public	void	handleMessage(Message msg)	{
                Log.d(MainActivity.class.getSimpleName(),  msg.getData().getString("message"));
            }
        };
        MyLooper	myLooper	=	new	MyLooper(mainThreadHandler);
        myLooper.start();

        binding.textView.setText("Мой номер по списку №4");
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message	msg	=	Message.obtain();
                Bundle	bundle	=	new	Bundle();
                String job = binding.eTextWork.getText().toString();
                int age = Integer.parseInt(binding.eTextAge.getText().toString());
                bundle.putString("JOB", job);
                bundle.putInt("AGE", age);
                msg.setData(bundle);
                myLooper.mHandler.sendMessage(msg);
            }
        });
    }
}