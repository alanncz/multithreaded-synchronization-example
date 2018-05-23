package com.model;

import com.interfaces.Buffer;
import com.strategys.BufferBlocking;
import com.strategys.BufferCircular;
import com.strategys.BufferSincronizado;
import java.util.Scanner;
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

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        ExecutorService test = Executors.newFixedThreadPool(2);

        System.out.println("Informe o tamanho do buffer desejado");
        int tamanho = in.nextInt();

        Buffer bufferShared = tipoBuffer(tamanho);

        try {

            if (bufferShared != null) {
                test.execute(new Produtor(bufferShared));
                test.execute(new Consumidor(bufferShared));
            }
            else System.out.println("Buffer não existe");
            
        } catch (Exception exception) {
        }

        test.shutdown();
    }

    public static Buffer tipoBuffer(int tamanho) {

        System.out.println("Selecione o tipo de buffer ");
        System.out.println("Digite 0 para Buffer Sincronizado");
        System.out.println("Digite 1 para Buffer Sincronizado Circular");
        System.out.println("Digite 2 para Buffer Sincronizado Circular com BufferBlocking");
        int opcao = in.nextInt();

        switch (opcao) {

            case 0:
                return new BufferSincronizado();
            case 1:
                return new BufferCircular(tamanho);
            case 2:
                return new BufferBlocking(tamanho);
            default:
                return null;
        }

    }

}
