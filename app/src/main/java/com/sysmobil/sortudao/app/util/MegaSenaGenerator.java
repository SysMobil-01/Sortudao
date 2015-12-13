package com.sysmobil.sortudao.app.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Wilson.Wistuba on 10/12/15.
 */
public class MegaSenaGenerator {

//    private int dez1;
//    private int dez2;
//    private int dez3;
//    private int dez4;
//    private int dez5;
//    private int dez6;

    public void MegaSenaGenerator(){

    }

    public Aposta geraAposta(int n) {
        Aposta aposta = new Aposta(n);
        for(int i=0; i<n;i++){
            int num = aposta.geraNum();
            aposta.addDezena(num);
        }
        return aposta;
    }


}

