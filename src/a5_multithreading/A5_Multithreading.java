/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package a5_multithreading;

import FilePlayer.FilePlayer;
import Thread.Thread1;
import Thread.Thread2;

/**
 *
 * @author danie
 */
public class A5_Multithreading {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Object lock = new Object();
        Thread1 thread1 = new Thread1(lock);
        Thread2 thread2 = new Thread2(lock);
        
        // Override method run in thread 1 + 2 then start 2 thread
        thread1.start();
        thread2.start();
    }
    
}
