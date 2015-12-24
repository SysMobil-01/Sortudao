package com.sysmobil.sortudao.app;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.ads.AdView;
import com.sysmobil.sortudao.app.util.MegaSenaGenerator;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private MegaSenaGenerator apostaMega = new MegaSenaGenerator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Firebase.setAndroidContext(this);

        final String userID = getIntent().getExtras().getString("user");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        final Firebase con = new Firebase("https://sortudao.firebaseio.com");

        WebView view01 = (WebView)findViewById(R.id.web01);
        final Spinner numDezenasList = (Spinner)findViewById(R.id.numDezenasList);
        final EditText txt = (EditText)findViewById(R.id.txtAposta);
        final TextView txtPreco = (TextView) findViewById(R.id.txtPreco);
        final Button btnBets = (Button)findViewById(R.id.btnLastBets);

        txt.setFocusable(false);

        String[] itens = {"6 Dezenas","7 Dezenas","8 Dezenas","9 Dezenas","10 Dezenas","11 Dezenas","12 Dezenas","13 Dezenas","14 Dezenas","15 Dezenas"};

        numDezenasList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens));

        view01.setWebViewClient(new InternalBrowser());
        view01.getSettings().setJavaScriptEnabled(true);
        view01.loadUrl("http://loterias.caixa.gov.br/wps/portal/loterias/landing/megasena");

        numDezenasList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int numDezenas = position + 6;
                NumberFormat df = NumberFormat.getCurrencyInstance();
                txtPreco.setText(df.format(apostaMega.calcPreco(numDezenas)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // do nothing
            }

        });

        btnBets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent bets = new Intent(view.getContext() , BetsActivity.class);
                bets.putExtra("user" , userID);
                startActivity(bets);

            }

        });

        ImageButton bt = (ImageButton) findViewById(R.id.btnGenerate);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt.setText("");

                int numDezenas = numDezenasList.getSelectedItemPosition() + 6;

                String aposta = apostaMega.geraAposta(numDezenas).toString();

                txt.setText(aposta);

                con.child("userbets").child(userID).push().setValue(aposta);;

            }

        });
        ImageButton cp = (ImageButton) findViewById(R.id.btnCopy);
        cp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                String ap = txt.getText().toString();

                ClipData clip = ClipData.newPlainText("Aposta", ap);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getApplicationContext(), "Aposta copiada", Toast.LENGTH_SHORT).show();

            }

        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class InternalBrowser extends WebViewClient {}
}
