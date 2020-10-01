package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

//Имплементируем слушатель событий
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //создаем переменные
    int quantity = 0;

    Spinner spinner;
    ArrayList spinnerArrayList;
    HashMap goodsMap;
    String goodsName;
    Double price;


    //переменная для заполения спинера
    ArrayAdapter spinnerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSpinner();
        createMap();
    }


    void createSpinner() {
        //связываем по id
        spinner = findViewById(R.id.spinner);
        //станавливаем слушатель событий для отображения цены
        spinner.setOnItemClickListener(this);


        //Определеяем и добавляем элементы в массив;
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("keyboard");

        //передаем в адаптер элементы
        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    void createMap() {
        //даём цену при выборе
        goodsMap = new HashMap();
        goodsMap.put("guitar", 500.0);
        goodsMap.put("drums", 1500.0);
        goodsMap.put("keyboard", 1000.0);
    }


    //Добавляем количество
    public void increaseQuantity(View view) {
//        quantity = quantity + 1;
        quantity++;

        //связываем по id
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + quantity);

        //Для изменения цены по клику
        TextView priceTextView = findViewById(R.id.priseTextView);
        priceTextView.setText("" + quantity * price);

    }

    //уменьшаем количество
    public void decreaseQuantity(View view) {
        if (quantity > 0) {
            quantity--;

            //связываем по id
            TextView quantityTextView = findViewById(R.id.quantityTextView);
            quantityTextView.setText("" + quantity);

            //Для изменения цены по клику
            TextView priceTextView = findViewById(R.id.priseTextView);
            priceTextView.setText("" + quantity * price);
        }
    }


    //описываем метод отображение цены выбора спинера
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priseTextView);
        priceTextView.setText("" + quantity * price);

        //меняем картинку в зависимости от выбора
        ImageView goodsImageView = findViewById(R.id.goodsImageView);

//        if (goodsName.equals("guitar")) {
//            goodsImageView.setImageResource(R.drawable.guitar);
//        } else if (goodsName.equals("drums")) {
//            goodsImageView.setImageResource(R.drawable.drums);
//        } else if (goodsName.equals("keyboard")) {
//            goodsImageView.setImageResource(R.drawable.keyboard);
//        }

        //вариант со свичкейс
        switch (goodsName) {
            case "guitar":
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
            case "drums":
                goodsImageView.setImageResource(R.drawable.drums);
                break;
            case "keyboard":
                goodsImageView.setImageResource(R.drawable.keyboard);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.guitar);
                break;

        }
    }
}