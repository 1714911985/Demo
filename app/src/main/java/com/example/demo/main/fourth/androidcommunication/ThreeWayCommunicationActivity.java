package com.example.demo.main.fourth.androidcommunication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demo.R;
import com.example.demo.main.fourth.androidcommunication.utils.AnotherActivity;
import com.example.demo.main.fourth.androidcommunication.utils.EventBusUtil;
import com.example.demo.main.fourth.androidcommunication.utils.MessageEvent;
import com.example.demo.main.fourth.androidcommunication.utils.RxBus;
import com.example.demo.main.fourth.androidcommunication.utils.WeatherEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class ThreeWayCommunicationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnEventBus;
    private Button btnRxBus;
    private TextView tvEventBus;
    private TextView tvRxBus;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_way_communication);
        initView();

        Toolbar toolbar = findViewById(R.id.tb_three_way);
        //toolbar的返回按钮
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        EventBusUtil.register(this);
        initRxBus();
    }

    private void initView() {
        btnEventBus = findViewById(R.id.btn_event_bus);
        btnEventBus.setOnClickListener(this);
        btnRxBus = findViewById(R.id.btn_rx_bus);
        btnRxBus.setOnClickListener(this);
        tvEventBus = findViewById(R.id.tv_event_bus);
        tvRxBus = findViewById(R.id.tv_rx_bus);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_event_bus) {
            startActivity(new Intent(ThreeWayCommunicationActivity.this, AnotherActivity.class));
        } else if (v.getId() == R.id.btn_rx_bus) {
            startActivity(new Intent(ThreeWayCommunicationActivity.this, AnotherActivity.class));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(MessageEvent messageEvent) {
        String message = messageEvent.getMessage();
        tvEventBus.setText(message);
        tvRxBus.setText("");
    }

    public void initRxBus() {
        compositeDisposable = new CompositeDisposable();
        RxBus.getInstance().toObservable(WeatherEvent.class).subscribe(new Observer<WeatherEvent>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull WeatherEvent weatherEvent) {
                tvRxBus.setText(weatherEvent.toString());
                tvEventBus.setText("");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.e("initRxBus", "onComplete: ");
            }
        });
    }
}