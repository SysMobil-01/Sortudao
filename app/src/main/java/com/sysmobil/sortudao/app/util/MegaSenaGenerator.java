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
    private List<Integer> aposta;

    public void MegaSenaGenerator(){
//
//        this.dez1 = dez1;
//        this.dez2 = dez2;
//        this.dez3 = dez3;
//        this.dez4 = dez4;
//        this.dez5 = dez5;
//        this.dez6 = dez6;

        List<Integer> aposta = new ArrayList<Integer>();


    }

    public void geraAposta(int n) {
        for(int i=0; i<n;i++){
            int num = geraNum();
            aposta.add(num);
        }
        ordena();
    }

    private void ordena(){
        Collections.sort(aposta);
    }

    private int geraNum(){

        int n = (int)Math.random()*60;
        if(aposta.contains(n)){
            return geraNum();
        }else{
            return n;
        }
    }
    @Override
    public String toString() {
        String apostaString = " ";
        for (int n : aposta) {
            if(aposta.indexOf(n) == 5){
                apostaString = apostaString + n + " ";
                return apostaString;
            }
            apostaString = apostaString + n + ", ";
        }
        return apostaString;
    }

    public List<Integer> getAposta(){
        return this.aposta;
    }

}

