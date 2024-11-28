/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package a5_multithreading;

import Thread.Thread1Bonus;
import Thread.Thread2Bonus;

public class A5_Bonus {
    public static void main(String[] args) {
        Object lock = new Object();

        // Twinkle Twinkle Little Star
        String[] song = {
            "do", "do", "sol", "sol", "la", "la", "sol",
            "fa", "fa", "mi", "mi", "re", "re", "do",
            "sol", "sol", "fa", "fa", "mi", "mi", "re",
            "sol", "sol", "fa", "fa", "mi", "mi", "re",
            "do", "do", "sol", "sol", "la", "la", "sol",
            "fa", "fa", "mi", "mi", "re", "re", "do"
        };

        int[] index = {0}; // Shared index as an array

        // Create and start threads
        Thread1Bonus thread1 = new Thread1Bonus(lock, song, index);
        Thread2Bonus thread2 = new Thread2Bonus(lock, song, index);

        thread1.start();
        thread2.start();
    }
}
