/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategys;

import interfaces.Buffer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alann
 */
public class BufferBlocking implements Buffer {

    private final ArrayBlockingQueue<Integer> buffer;

    public BufferBlocking(int tamanhoBuffer) {
        
        buffer = new ArrayBlockingQueue<>(tamanhoBuffer);
    }

    @Override
    public int get() {

        int valorLido = 0;

        try {

            valorLido = buffer.take();
            System.out.println("Consumidor lê: " + valorLido);

        } catch (InterruptedException ex) {
            Logger.getLogger(BufferBlocking.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valorLido;
    }

    @Override
    public void set(int value) {

        try {

            buffer.put(value);
            System.out.println("Produtor grava: " + value);

        } catch (InterruptedException exeption) {

        }
    }

}
