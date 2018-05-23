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

        System.out.println("Consumidor lê : " + buffer);
        return buffer;
    }

    @Override
    public void set(int value) {

        System.out.println("Produtor grava :" + value);
        buffer = value;

    }

}
