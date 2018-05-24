package model;


import interfaces.Buffer;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alann
 */
public class Consumidor implements Runnable {

    private static final Random SLEEP = new Random();
    private final Buffer bufferShared;

    public Consumidor(Buffer bufferShared) {
        this.bufferShared = bufferShared;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int k = 1; k <= 10; k++) {
            //Thread.sleep(timeSLEEP.nextInt(3000));
            sum += bufferShared.get();
        }
        
        System.out.println("Fim do Consumidor. Valor da soma: " + sum);
    }
}
