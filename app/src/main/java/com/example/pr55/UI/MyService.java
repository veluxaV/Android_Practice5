package com.example.pr55.UI;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.pr55.MainActivity;
import com.example.pr55.R;

public class MyService extends Service {
    private WindowManager windowManager;
    private FrameLayout bannerView;
    private static final int NOTIFICATION_ID = 1;

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            String channelId = "my_channel_id";
            CharSequence channelName = "My Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //createNotificationChannel();
        // Создаем и настраиваем визуальное содержимое баннера из макета
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View bannerLayout = layoutInflater.inflate(R.layout.banner_layout, null);

        ImageView bannerImageView = bannerLayout.findViewById(R.id.imageView);
        bannerImageView.setImageResource(R.drawable.barcode);

        // Создание PendingIntent'a для перехода в приложение
        Intent intent= new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Button bannerButton = bannerLayout.findViewById(R.id.button_to_app);
        bannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException e) {//если попытка отправки отмененного PendingIntent'a
                    e.printStackTrace();
                }
            }
        });
        Button bannerImageButton = bannerLayout.findViewById(R.id.close_banner);
        bannerImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Закрытие сервиса при нажатии на кнопку
                stopSelf();
            }
        });

        // Устанавливаем параметры для системного окна
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, // параметр, который позволяет отображать
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE        // окно поверх всех других окон
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT);

        // Создаем новое системное окно
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        // Добавление всплывающего окна на экран
        windowManager.addView(bannerLayout, layoutParams);
        // Задание гравитации окна (центрирование)
        layoutParams.gravity = Gravity.CENTER;
        // Обновление параметров окна
        windowManager.updateViewLayout(bannerLayout, layoutParams);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    // Остановка сервиса и удаление всплывающего окна при уничтожении сервиса
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (windowManager != null && bannerView != null) {
            windowManager.removeView(bannerView);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void showBanner() {
    }

    private void hideBanner() {
        windowManager.removeView(bannerView);
    }

    public MyService() {
    }

}