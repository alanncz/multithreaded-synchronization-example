package multithreaded.synchronization.example;


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
public class Consumer implements Runnable {

    private static Random timeSleep = new Random();
    private Buffer bufferShared;

    public Consumer(Buffer bufferShared) {
        this.bufferShared = bufferShared;
    }

    public void run() {
        int sum = 0;
        for (int k = 1; k <= 10; k++) {
            try {
                Thread.sleep(timeSleep.nextInt(3000));
                sum += bufferShared.get();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        
        System.out.println("Fim do Consumidor. Valor da soma: " + sum);
    }
}
