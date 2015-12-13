package com.sysmobil.sortudao.app.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by peter on 13/12/15.
 */
public class Aposta {

    List<Integer> dezenas;
    int tamanho, tMax;

    public Aposta(int tamMax) {
        dezenas = new ArrayList<Integer>();
        tamanho = 0;
        tMax = tamMax;
    }

    public void apostaCheia(List<Integer> dez){
        for(int n : dez){
            addDezena(n);
        }
    }

    public boolean addDezena(int d){
        if(tamanho >= tMax){
            return false;
        }
        if (!dezenas.contains(d) && d > 0 ){
            dezenas.add(d);
            tamanho = dezenas.size();
            ordena();
            return true;
        }else{
            return false;
        }
    }

    private void ordena(){
        Collections.sort(dezenas);
    }

    public int geraNum(){
        int n = (int) (Math.random()*60);
        if (!dezenas.contains(n) && n != 0) {
            return n;
        } else {
            return geraNum();
        }
    }

    @Override
    public String toString() {
        if(dezenas.isEmpty()){
            return "Vazia";
        }
        String apostaString = " ";
        for (int n : dezenas) {
            if(dezenas.indexOf(n) == (dezenas.size()-1)){
                apostaString = apostaString + n + " ";
                return apostaString;
            }
            apostaString = apostaString + n + ", ";
        }
        return apostaString;
    }

    public int getTamanho(){
        return tamanho;
    }

    public List<Integer> getDezenas() {
        return dezenas;
    }

}
