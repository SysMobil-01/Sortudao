package com.sysmobil.sortudao.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sysmobil.sortudao.app.util.Session;


/**
 * Created by peter on 15/12/15.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Firebase.setAndroidContext(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        final TextView txtInstructions = (TextView)findViewById(R.id.txtViewInstructions);
        final EditText txtEmail = (EditText)findViewById(R.id.txtEmail);
        final EditText txtPwd = (EditText)findViewById(R.id.txtPwd);
        final Button btnLogin = (Button)findViewById(R.id.btnLogin);
        Button btnCreate = (Button)findViewById(R.id.btnCreate);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                String email = txtEmail.getText().toString();
                String pwd = txtPwd.getText().toString();

                if(email != null && pwd != null){
                    txtInstructions.setText("Conectando...");
                    txtInstructions.setTextColor(Color.BLACK);
                    btnLogin.setText("Aguarde");
                    btnLogin.setClickable(false);

                    final Session session = new Session();
                    Firebase con = new Firebase("https://sortudao.firebaseio.com");
                    con.authWithPassword(email, pwd, new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            Toast.makeText(getApplicationContext(), "Usuário autenticado!", Toast.LENGTH_SHORT).show();
                            session.setActive(true);
                            session.setToken(authData.getToken());
                            Intent app = new Intent(view.getContext() , MainActivity.class);
                            app.putExtra("user", (String)authData.getUid());
                            startActivity(app);
                        }
                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {
                            txtInstructions.setText("Houve um erro, confira os dados e tente novamente");
                            txtInstructions.setTextColor(Color.RED);
                            btnLogin.setText("Login");
                            btnLogin.setClickable(true);
                            session.setActive(false);
                        }
                    });

                }else{
                    txtInstructions.setText("Preencha seus dados corretamente!");
                    txtInstructions.setTextColor(Color.RED);
                }

            }

        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createUser = new Intent(view.getContext() , CreateUserActivity.class);
                startActivity(createUser);
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

    }

}
