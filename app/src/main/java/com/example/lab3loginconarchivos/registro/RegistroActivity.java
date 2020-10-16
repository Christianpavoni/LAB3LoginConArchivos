package com.example.lab3loginconarchivos.registro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab3loginconarchivos.R;
import com.example.lab3loginconarchivos.registro.RegistroActivityViewModel;
import com.example.lab3loginconarchivos.R;
import com.example.lab3loginconarchivos.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private RegistroActivityViewModel vm;
    private Button btGuardar;
    private EditText etDni,etNombre,etApellido,etEmail,etPassword;
    private TextView tvTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        inicializar();
    }

    public void inicializar() {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);

        btGuardar=findViewById(R.id.btGuardar);
        etDni=findViewById(R.id.etDni);
        etNombre=findViewById(R.id.etNombre);
        etApellido=findViewById(R.id.etApellido);
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        tvTitulo=findViewById(R.id.tvTitulo);

        if(getIntent().getBooleanExtra("perfil",false))
            tvTitulo.setText("MI PERFIL");


        vm.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

                if(s.equals("Guardado con exito")){

                    finish();

                }

            }
        });

        vm.getusuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {

                etDni.setText(usuario.getDni());
                etNombre.setText(usuario.getNombre());
                etApellido.setText(usuario.getApellido());
                etEmail.setText(usuario.getEmail());
                etPassword.setText(usuario.getPassword());

            }
        });

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dni= etDni.getText().toString();
                String nombre= etNombre.getText().toString();
                String apellido= etApellido.getText().toString();
                String email= etEmail.getText().toString();
                String password= etPassword.getText().toString();
                vm.guardar(dni,nombre,apellido,email,password);


            }
        });

        vm.leer();

    }
}