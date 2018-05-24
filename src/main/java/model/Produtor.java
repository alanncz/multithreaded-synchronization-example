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
public class Produtor implements Runnable {

    private static final Random SLEEP = new Random();
    private final Buffer bufferShared;

    public Produtor(Buffer bufferShared) {
        this.bufferShared = bufferShared;
    }

    @Override
    public void run() {
        for (int k = 1; k <= 10; k++) {
            //Thread.sleep(SLEEP.nextInt(3000));
            this.bufferShared.set(k);
        }
        
        System.out.println("Fim do Produtor.");

    }
}
