
package multithreaded.synchronization.example;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alann
 */

public class BufferImpl implements Buffer {

    private int buffer = -1;
    private Lock lockDeAcesso = new ReentrantLock();
    private Condition podeGravar = lockDeAcesso.newCondition();
    private Condition podeLer = lockDeAcesso.newCondition();
    private boolean ocupado = false;

    @Override
    public int get() {

        int valorLido = 0;
        lockDeAcesso.lock();
        try {
            while (!ocupado) {
                System.out.println("Buffer vazio, Comsumidor aguarda");
                podeLer.await();
            }
            ocupado = false;
            valorLido = buffer;
            System.out.println("Consumidor lê: " + valorLido );
            podeGravar.signal();
            
        } catch (InterruptedException exception) {
            exception.printStackTrace();
            
        } finally {
            lockDeAcesso.unlock();
        }
        return valorLido;
    }

    @Override
        public void set(int value) {

        lockDeAcesso.lock();
        try {
            while (ocupado) {
                System.out.println("Buffer cheio, produtor aguarda");
                podeGravar.await();
            }
            buffer = value;
            ocupado = true;
            System.out.println("Produtor grava: " + buffer );
            podeLer.signal();
            
        } catch (InterruptedException exception) {
            exception.printStackTrace();
            
        } finally {
            lockDeAcesso.unlock();
        }

    }

}
