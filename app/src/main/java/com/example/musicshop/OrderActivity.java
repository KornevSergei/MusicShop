package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {


    //переменные для передачи в другое приложение
    String[] addresses = {"4Kornev@gmail.com"};
    String subject = "Order from MusicShop";
    String emailText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //устанавливаем в шапку новое название
        setTitle("Ваш заказ:");

        //получаем информацию из первого активити

        Intent receivedOrderIntent = getIntent();
        //выводим текст в новую активити по ключу
        String userName = receivedOrderIntent.getStringExtra("userNameForIntent");
        String goodsName = receivedOrderIntent.getStringExtra("goodsNameForIntent");
        int quantity = receivedOrderIntent.getIntExtra("quantityForIntent", 0);
        double price = receivedOrderIntent.getDoubleExtra("priceForIntent", 0.0);
        double orderPrice = receivedOrderIntent.getDoubleExtra("orderPriceForIntent", 0.0);

        //рисваиваем для вставки в письмо
        emailText = "Заказчик: " + userName +
                "\n" + "Инструмент: " + goodsName +
                "\n" + "Колличество: " + quantity +
                "\n" + "Цена за шт: " + price +
                "\n" + "Сумма заказа: " + orderPrice;

        //связываем для отображения
        TextView orderTextView = findViewById(R.id.orderTextView);


        //устанавливаем текст и переносим на новую строку
//        orderTextView.setText("Заказчик: " + userName +
//                "\n" + "Инструмент: " + goodsName +
//                "\n" + "Колличество: " + quantity +
//                "\n" + "Цена за шт: " + price +
//                "\n" + "Сумма заказа: " + orderPrice);


        //или просто устанавливаем переменную
        orderTextView.setText(emailText);

    }


    //передаем информацию заказа и запускаем другое приложение
    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        //адресс
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        //тема письма
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        //текст пиьсма
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        //если нет емейла - то не запускаем
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);


        }

    }
}