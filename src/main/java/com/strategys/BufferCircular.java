/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strategys;

import com.interfaces.Buffer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author alann
 */
public class BufferCircular implements Buffer {

    private final Lock accessLock = new ReentrantLock();
    private final Condition podeGravar = accessLock.newCondition();
    private final Condition podeLer = accessLock.newCondition();
    private final int[] buffer;
    private int buffersOcupados = 0;
    private int gravarIndice = 0;
    private int lerIndice = 0;

    public BufferCircular(int tamanhoBuffer) {

        int[] novoBuffer = new int[tamanhoBuffer];
        for (int k = 0; k < novoBuffer.length; k++) {
            novoBuffer[k] = -1;
        }
        this.buffer = novoBuffer;

    }

    @Override
    public int get() {

        int valorLido = 0;
        accessLock.lock();
        try {
            while (buffersOcupados == 0) {
                System.out.println("Buffers estão vazios.Consumidor aguarda.");
                podeLer.await();
            }
            
            valorLido = buffer[lerIndice];
            lerIndice = (lerIndice + 1) % buffer.length;
            buffersOcupados--;
            System.out.println("Consumidor lê: " + valorLido );
            podeGravar.signal();
            
        } catch (InterruptedException exception) {
            
        } finally {
            accessLock.unlock();
        }
        return valorLido;
    }

    @Override
    public void set(int value) {

        accessLock.lock();

        try {

            while (buffersOcupados == buffer.length) {
                System.out.println("Todos os buffers cheios.Produtor aguarda.");
                podeGravar.await();
            }

            buffer[gravarIndice] = value;
            gravarIndice = (gravarIndice + 1) % buffer.length;
            buffersOcupados++;
            System.out.println("Produtor grava: " + value);
            podeLer.signal();

        } catch (InterruptedException exception) {
            
        } finally {
            accessLock.unlock();
        }
    }

}
