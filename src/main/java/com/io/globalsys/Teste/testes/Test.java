package com.io.globalsys.Teste.testes;

import java.util.concurrent.atomic.AtomicBoolean;

public class Test {
    public static void main(String[] args) {

        AtomicBoolean ok = new AtomicBoolean(false);
        int []nums1 = new int[3];
        int []num2 = new int[3];

        for (int i = 1; i < num2.length; i++) {
            nums1[0] = 4;
            num2[0] = 3;

            nums1[i] = i;
            num2[i] = i;



        }

        for(int i = 0; i < nums1.length; i++){
            ok.set(nums1[i] == num2[i]);
            System.out.println(ok);
        }

    }
}
