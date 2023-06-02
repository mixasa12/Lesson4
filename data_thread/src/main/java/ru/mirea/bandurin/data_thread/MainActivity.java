package ru.mirea.bandurin.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import ru.mirea.bandurin.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding	= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final Runnable runn1 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("runn1");
            }
        };
        final Runnable runn2 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("runn2");
            }
        };
        final Runnable runn3 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("runn3");
            }
        };
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.tvInfo.postDelayed(runn3, 2000);
                    binding.tvInfo.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        binding.textView.setText("runOnUiThread - выполняет указанное действие в потоке пользовательского интерфейса. Если текущий поток является потоком пользовательского интерфейса, то действие выполняется немедленно.Если текущий поток не является потоком пользовательского интерфейса, действие отправляется в очередь событий потока пользовательского интерфейса.." +
                "\n post - Добавляет действие в очередь сообщений. Действие будет запущено в потоке пользовательского интерфейса" +
                "\n postDelayed - Вызывает добавление действия в очередь сообщений для запуска по истечении указанного времени. Действие будет запущено в потоке пользовательского интерфейса.");
    }
}