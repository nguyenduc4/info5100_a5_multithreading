package Thread;

import FilePlayer.FilePlayer;

public class Thread1 extends Thread {
    private final Object lock;

    public Thread1(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        String[] tones = {
            "src/Sounds/do.wav",      // do
            "src/Sounds/mi.wav",      // mi
            "src/Sounds/sol.wav",     // sol
            "src/Sounds/si.wav",      // si
            "src/Sounds/do-octave.wav" // do-octave
        };
        FilePlayer fp = new FilePlayer();

        synchronized (lock) {
            for (String filePath : tones) {
                fp.play(filePath);
                // First play the sound, then notify other thread and wait
                try {
                    System.out.println("Thread 1: "  + filePath);
                    if (!filePath.endsWith("do-octave.wav")) {
                        lock.notify();
                        lock.wait(); 
                    } else {
                        // Both do-octave should be played with 2 thread
                        lock.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
