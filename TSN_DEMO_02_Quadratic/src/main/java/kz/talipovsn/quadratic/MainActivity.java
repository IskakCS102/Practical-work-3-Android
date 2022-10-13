package kz.talipovsn.quadratic;


//import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity<savedInstanceState> extends AppCompatActivity {

    EditText editText_a; // Переменная для доступа к компоненту со значением "a"
    EditText editText_x; // Переменная для доступа к компоненту со значением "b"
    EditText editText_b; // Переменная для доступа к компоненту со значением "b"
    TextView textView_sum; // Переменная для доступа к компоненту со значением результата
    Button buttonSum; // Переменная для доступа к компоненту кнопки

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Доступ к компонентам окна
        editText_a = findViewById(R.id.editText_a);
        editText_x = findViewById(R.id.editText_x);
        editText_b = findViewById(R.id.editText_b);
        textView_sum = findViewById(R.id.textView_sum);
        buttonSum = findViewById(R.id.buttonSum);


        if (savedInstanceState != null) {
            textView_sum.setText(savedInstanceState.getString("y"));
            buttonSum.setEnabled(savedInstanceState.getBoolean("VB"));
            if (!buttonSum.isEnabled()){
                textView_sum.setText("");
            }
        }
        else {
            buttonSum.setEnabled(false); // Выключаем доступность нажатия у кнопки
            textView_sum.setText("");

        }

        View.OnKeyListener myKeyListener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // Проверка условия: если пусто в "a" или "b"
                if (editText_a.getText().toString().trim().equals("") ||
                        editText_b.getText().toString().trim().equals("") ||
                        editText_x.getText().toString().trim().equals("")) {
                    buttonSum.setEnabled(false); // Выключаем доступность нажатия у кнопки
                    textView_sum.setText("");
                } else {
                    buttonSum.setEnabled(true); // Включаем доступность нажатия у кнопки
                }
                return false;
            }
        };


        editText_a.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий
        editText_b.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий
        editText_x.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий



    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Сохранение нужных нам значений компонент при перевороте экрана
        outState.putString("y", textView_sum.getText().toString());
        outState.putBoolean("VB", buttonSum.isEnabled());
    }


    // МЕТОД КНОПКИ РАСЧЕТА
    public void onClick(View v) {
        // Объявление локальных переменных
        double a, b, y, x;

        try { // НАЧАЛО ЗАЩИЩЕННОГО БЛОКА

            // Чтение данных из компонент
            a = Double.parseDouble(editText_a.getText().toString().trim());
            b = Double.parseDouble(editText_b.getText().toString().trim());
            x = Double.parseDouble(editText_x.getText().toString().trim());

            // Основной алгоритм
            if (x >= 4) {
                y = (10 * (x + (a * a))) / (b + a);

            } else {
                y = 5 * (x + (a * a) + b);

            }
            NumberFormat formatter = new DecimalFormat("#0.00");
            textView_sum.setText(formatter.format(y));


        } catch (Exception e) { // ЧТО ДЕЛАТЬ ЕСЛИ ВОЗНИКНЕТ ОШИБКА В БЛОКЕ МЕЖДУ "TRY" И "CATCH":

            // Вывод сообщения об ошибке
            textView_sum.setText("Неверные входные данные для расчета!");

        }  // КОНЕЦ ЗАЩИЩЕННОГО БЛОКА

    }

}
