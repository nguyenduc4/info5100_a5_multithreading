package Thread;

import FilePlayer.FilePlayer;

public class Thread2 extends Thread {
    private final Object lock;

    public Thread2(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        String[] tones = {
            "src/Sounds/re.wav", 
            "src/Sounds/fa.wav",
            "src/Sounds/la.wav",
            "src/Sounds/do-octave.wav"
        };
        FilePlayer fp = new FilePlayer();

        synchronized (lock) {
            for (String filePath : tones) {
                fp.play(filePath); 
                try {
                    System.out.println("Thread 2: "  + filePath);
                    // Play then notify thread 1 and wait for that thread to complete
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
