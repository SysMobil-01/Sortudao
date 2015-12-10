package com.sysmobil.sortudao.app;

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
import android.widget.Button;
import android.widget.EditText;

import com.sysmobil.sortudao.app.util.MegaSenaGenerator;

public class MainActivity extends AppCompatActivity {

    private MegaSenaGenerator apostaMega = new MegaSenaGenerator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView view01 = (WebView)findViewById(R.id.web01);
        view01.setWebViewClient(new InternalBrowser());
        view01.getSettings().setJavaScriptEnabled(true);
        view01.loadUrl("http://loterias.caixa.gov.br/wps/portal/loterias/landing/megasena");

        Button bt = (Button) findViewById(R.id.bt01);
        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                EditText txt = (EditText)findViewById(R.id.txtAposta);

                apostaMega.geraAposta();
                txt.setText(apostaMega.getAposta());

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

    private class InternalBrowser extends WebViewClient {




    }
}
