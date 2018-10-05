package com.example.jose.miprimeraaplicacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mCalculo;
    Button mBotonDuplicar;
    Button mBotonDividir;
    Button mbotonLimpiar;
    EditText mNumeroEntrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        // Creación de referencias a los views

        mCalculo = findViewById(R.id.txvValorCalculo);
        mBotonDuplicar = findViewById(R.id.btMultiplica);
        mBotonDividir = findViewById(R.id.btDivide);
        mbotonLimpiar = findViewById(R.id.btLimpiar);
        mNumeroEntrada = findViewById(R.id.numeroEntrada);


        // Implementación de listerners para recoger eventos sobre los
        // views anteriores

        mBotonDuplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duplicar();
            }
        });
        mBotonDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dividir();
            }
        });
        mbotonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetear();
            }
        });
    }

    private void dividir() {
        int valor, numeroEntrada;
        try {
            numeroEntrada = Integer.parseInt(mNumeroEntrada.getText().toString());
            valor = Integer.parseInt(mCalculo.getText().toString());
            // Si valor es 1 lo deja tal cual
            valor = valor == 1 ? valor : valor / numeroEntrada;
            mCalculo.setText(String.valueOf(valor));
            mBotonDuplicar.setEnabled(valor*numeroEntrada <= Integer.MAX_VALUE);
            mBotonDividir.setEnabled(valor > 1);

        } catch (NumberFormatException e) {
            Log.e("MiAplicacion", "Error al convertir el valor en un entero", e);
        }
    }

    void duplicar() {
        int valor,numeroEntrada;
        try {
            numeroEntrada = Integer.parseInt(mNumeroEntrada.getText().toString());
            valor = Integer.parseInt(mCalculo.getText().toString());
            valor *= numeroEntrada;
            mCalculo.setText(String.valueOf(valor));

            // Si el proximo duplicado excede en rango de int deshabilita el boton duplicar
            mBotonDuplicar.setEnabled(valor*(float)numeroEntrada <= Integer.MAX_VALUE);

            // Si el valor actual es mayor que uno habilita boton dividir
            mBotonDividir.setEnabled(valor > 1);



        } catch (NumberFormatException e) {
            Log.e("MiAplicacion", "Error al convertir el valor en un entero", e);
        }
    }

    void resetear() {
        mCalculo.setText("1");
        mNumeroEntrada.setText("");
        mBotonDuplicar.setEnabled(true);
        mBotonDividir.setEnabled(false);
    }
}
