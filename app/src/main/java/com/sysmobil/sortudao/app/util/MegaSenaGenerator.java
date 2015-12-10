package com.sysmobil.sortudao.app.util;

import java.util.ArrayList;

/**
 * Created by Wilson.Wistuba on 10/12/15.
 */
public class MegaSenaGenerator {

    private int dez1;
    private int dez2;
    private int dez3;
    private int dez4;
    private int dez5;
    private int dez6;
    private String aposta;

    public void MegaSenaGenerator(int dez1, int dez2, int dez3, int dez4, int dez5, int dez6){

        this.dez1 = dez1;
        this.dez2 = dez2;
        this.dez3 = dez3;
        this.dez4 = dez4;
        this.dez5 = dez5;
        this.dez6 = dez6;
    }

    public void geraAposta() {
        this.dez1 = (int) (Math.random()*60);
        this.dez2 = (int) (Math.random()*60);
        this.dez3 = (int) (Math.random()*60);
        this.dez4 = (int) (Math.random()*60);
        this.dez5 = (int) (Math.random()*60);
        this.dez6 = (int) (Math.random()*60);

        switch (this.aposta = this.dez1 + " - " + this.dez2 + " - " + this.dez3 + " - " + this.dez4 + " - " + this.dez5 + " - " + this.dez6) {
        }
    }


    public String getAposta(){
        return this.aposta;
    }
}
