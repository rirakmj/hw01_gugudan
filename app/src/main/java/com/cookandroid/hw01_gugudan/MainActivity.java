package com.cookandroid.hw01_gugudan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("구구단 맞추기");

        final EditText et1 = findViewById(R.id.et1);
        final EditText et2 = findViewById(R.id.et2);
        final EditText etResult = findViewById(R.id.etResult);

        final Button btnRandom = findViewById(R.id.btnRandom);
        final Button btnResult = findViewById(R.id.btnResult);

        final ListView lv1 = findViewById(R.id.lv1);

        // 난수 생성
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rand1 = new Random().nextInt(8)+2;
                int rand2 = new Random().nextInt(8)+2;

                et1.setText(String.valueOf(rand1));
                et2.setText(String.valueOf(rand2));
            }
        });

        // 결과 확인
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 정답 확인 버튼 클릭 시 키보드가 사라지도록 하기
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);

                // 구구단 계산하기
                String str1 = et1.getText().toString();
                String str2 = et2.getText().toString();
                String strResult = etResult.getText().toString();

                if (str1.equals("") || str2.equals("") || strResult.equals("")) {
                    Toast.makeText(MainActivity.this, "값을 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    int int1 = Integer.parseInt(str1);
                    int int2 = Integer.parseInt(str2);
                    int intResult = Integer.parseInt(strResult);

                    int intAnswer = int1 * int2;

                    if (intResult == intAnswer) {
                        Toast.makeText(MainActivity.this, "정답입니다!", Toast.LENGTH_SHORT).show();
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, new String[0]);
                        lv1.setAdapter(adapter);
                    } else {
                        Toast.makeText(MainActivity.this, "틀렸습니다.", Toast.LENGTH_SHORT).show();

                        // 오답 입력 시 구구단 리스트 출력
                        String[] values = new String[9];
                        for (int i = 0; i < 9; ++i) {
                            values[i] = String.valueOf(int1) + "x" + (i + 1) + "=" + (int1 * (i + 1));
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, values);
                            lv1.setAdapter(adapter);
                        }
                    }
                }
            }
        });
    }
}