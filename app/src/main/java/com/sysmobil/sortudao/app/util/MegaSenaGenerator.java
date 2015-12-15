package com.sysmobil.sortudao.app.util;

/**
 * Created by Wilson.Wistuba on 10/12/15.
 */
public class MegaSenaGenerator {


    public void MegaSenaGenerator(){

    }

    public double calcPreco(int numDez){
        switch(numDez){
            case 6: return 3.50;
            case 7: return 24.5;
            case 8: return 98.0;
            case 9: return 294.0;
            case 10: return 735.0;
            case 11: return 1617.0;
            case 12: return 3234.0;
            case 13: return 6006.0;
            case 14: return 10510.5;
            case 15: return 17517.5;
            default:return 0.0;
        }
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

