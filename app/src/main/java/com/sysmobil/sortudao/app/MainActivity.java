package com.sysmobil.sortudao.app;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.sysmobil.sortudao.app.util.MegaSenaGenerator;

public class MainActivity extends AppCompatActivity {

    private MegaSenaGenerator apostaMega = new MegaSenaGenerator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);


        WebView view01 = (WebView)findViewById(R.id.web01);
        final Spinner numDezenasList = (Spinner)findViewById(R.id.numDezenasList);
        final EditText txt = (EditText)findViewById(R.id.txtAposta);

        txt.setFocusable(false);

        String[] itens = {"6 Dezenas","7 Dezenas","8 Dezenas","9 Dezenas","10 Dezenas","11 Dezenas","12 Dezenas","13 Dezenas","14 Dezenas","15 Dezenas"};

        numDezenasList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens));

        view01.setWebViewClient(new InternalBrowser());
        view01.getSettings().setJavaScriptEnabled(true);
        view01.loadUrl("http://loterias.caixa.gov.br/wps/portal/loterias/landing/megasena");

        Button bt = (Button) findViewById(R.id.btnGenerate);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt.setText("");

                int numDezenas = numDezenasList.getSelectedItemPosition() + 6;

                txt.setText(apostaMega.geraAposta(numDezenas).toString());

            }

        });
        Button cp = (Button) findViewById(R.id.btnCopy);
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
