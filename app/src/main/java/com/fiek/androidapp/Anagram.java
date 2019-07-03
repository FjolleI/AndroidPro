package com.fiek.androidapp;
import java.util.Random;

public class Anagram {

    public static final Random RANDOM = new Random();
    public static final String[] FJALET = {"ARRITJE", "AUDICION", "BARI", "DJE", "KAFSHE", "KIMIKE",
            "MESIM", "MOTI", "NDERMJET", "NESER", "NERVOZ", "PAGESE", "POLITIKE",
            "QEVERI", "SPITAL", "SOT", "SUBSTANCA",
            "SHPEJTE", "TELEFON", "TOKA", "ZEZE",  };


    public static String randomFjala() {
        return FJALET[RANDOM.nextInt(FJALET.length)];
    }

    public static String shuffleFjala(String fjala) {
        if (fjala!=null && !"".equals(fjala)) {
            char a[] = fjala.toCharArray();

            for (int i=0; i<a.length ;i++) {
                int j = RANDOM.nextInt(a.length);
                char tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }

            return new String(a);
        }

        return fjala;
    }
}
