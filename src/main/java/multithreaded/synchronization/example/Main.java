package multithreaded.synchronization.example;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alann
 */
public class Main {

    public static void main(String[] args) {

        ExecutorService test = Executors.newFixedThreadPool(2);
        Buffer bufferShared = new BufferImpl();
        
        try {
            test.execute(new Producer(bufferShared));
            test.execute(new Consumer(bufferShared));
            
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        test.shutdown();
    }

}
