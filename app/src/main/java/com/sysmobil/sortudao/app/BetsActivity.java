package com.sysmobil.sortudao.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * Created by peter on 15/12/15.
 */
public class BetsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Firebase.setAndroidContext(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.bets_main);

        final String userID = getIntent().getExtras().getString("user");

        final Firebase con = new Firebase("https://sortudao.firebaseio.com");

        final ListView listBets = (ListView)findViewById(R.id.listBets);

        con.child("userbets").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                HashMap<String, String> userbets = (HashMap<String, String>)snapshot.getValue();

                HashMapAdapter adapter = new HashMapAdapter(userbets);

                listBets.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

}

class HashMapAdapter extends BaseAdapter {
    private HashMap<String, String> mData = new HashMap<String, String>();
    private String[] mKeys;
    public HashMapAdapter(HashMap<String, String> data){
        mData  = data;
        mKeys = mData.keySet().toArray(new String[data.size()]);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(mKeys[position]);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        String key = mKeys[pos];
        String Value = getItem(pos).toString();

        //do your view stuff here

        return convertView;
    }
}
