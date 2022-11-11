package com.example.datltph21763_mob2041_code;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ManChaoActivity extends AppCompatActivity {
    EditText edNhapMa;
    Button btnNhapMa;
    TextView txtBl;
    String strNhapMa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_chao);
        edNhapMa = findViewById(R.id.edNhapMa);
        btnNhapMa = findViewById(R.id.btnNhapMa);
        txtBl = findViewById(R.id.txtBl);
        btnNhapMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strNhapMa = edNhapMa.getText().toString();
                if (strNhapMa.equals("PH21763")) {
                    Toast.makeText(ManChaoActivity.this, "Chuyển thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ManChaoActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    txtBl.setText("Không đúng");
                    txtBl.setTextColor(Color.RED);
                }
            }
        });


    }
}