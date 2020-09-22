package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    //создаем переменные
    int quantity = 0;

    Spinner spinner;

    ArrayList spinnerArrayList;

    //переменная для заполения спинера
    ArrayAdapter spinnerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //связываем по id
        spinner = findViewById(R.id.spinner);

        //Определеяем и добавляем элементы в массив;
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("keyboard");

        //передаем в адаптер элементы
        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAdapter);
    }

    //Добавляем количество
    public void increaseQuantity(View view) {

//        quantity = quantity + 1;
        quantity++;

        //связываем по id
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + quantity);
    }

    //уменьшаем количество
    public void decreaseQuantity(View view) {
        if (quantity > 0) {
            quantity--;

            //связываем по id
            TextView quantityTextView = findViewById(R.id.quantityTextView);
            quantityTextView.setText("" + quantity);
        }
    }
}