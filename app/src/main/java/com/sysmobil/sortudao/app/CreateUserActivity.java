package com.sysmobil.sortudao.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;


/**
 * Created by peter on 15/12/15.
 */
public class CreateUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Firebase.setAndroidContext(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user_main);

        final TextView txtInstructions = (TextView)findViewById(R.id.txtViewInstructions);
        final EditText txtEmail = (EditText)findViewById(R.id.txtEmail);
        final EditText txtName = (EditText)findViewById(R.id.txtName);
        final EditText txtPwd = (EditText)findViewById(R.id.txtPwd);
        final Button btnCreate = (Button)findViewById(R.id.btnCreate);
        final Button btnLogin = (Button)findViewById(R.id.btnLogin);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String name = txtName.getText().toString();
                final String email = txtEmail.getText().toString();
                String pwd = txtPwd.getText().toString();

                if(name != null && pwd != null && email != null){
                    txtInstructions.setText("Conectando...");
                    txtInstructions.setTextColor(Color.BLACK);
                    btnCreate.setText("Aguarde");
                    btnCreate.setClickable(false);

                    final Firebase con = new Firebase("https://sortudao.firebaseio.com");
                    con.createUser(email, pwd, new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {
                            Toast.makeText(getApplicationContext(), "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show();
                            Intent login = new Intent(view.getContext() , LoginActivity.class);
                            startActivity(login);
                        }
                        @Override
                        public void onError(FirebaseError firebaseError) {
                            txtInstructions.setText("Houve um erro, tente novamente");
                            txtInstructions.setTextColor(Color.RED);
                            btnCreate.setText("Criar");
                            btnCreate.setClickable(true);
                        }
                    });

                }else{
                    txtInstructions.setText("Preencha seus dados corretamente!");
                    txtInstructions.setTextColor(Color.RED);
                    btnCreate.setText("Criar");
                    btnCreate.setClickable(true);
                }

            }

        });

        txtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String content = txtEmail.getText().toString();
                if (hasFocus) {
                    if (content.equals("Email")) {
                        txtEmail.setText("");
                    }
                } else {
                    if (content.equals("") || content == null) {
                        txtEmail.setText("Email");
                    }
                }
            }
        });

        txtPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String content = txtPwd.getText().toString();
                if (hasFocus) {
                    if (content.equals("Senha")) {
                        txtPwd.setText("");
                    }
                } else {
                    if (content.equals("") || content == null) {
                        txtPwd.setText("Senha");
                    }
                }
            }
        });

        txtName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String content = txtName.getText().toString();
                if (hasFocus) {
                    if (content.equalsIgnoreCase("nome completo")) {
                        txtName.setText("");
                    }
                } else {
                    if (content.equals("") || content == null) {
                        txtName.setText("Nome completo");
                    }
                }
            }
        });

    }

}
