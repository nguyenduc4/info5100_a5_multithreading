package Thread;

import FilePlayer.FilePlayer;

public class Thread2Bonus extends Thread {
    private final Object lock;
    private final String[] song;
    private final int[] index; // Shared integer array to act as a mutable int

    public Thread2Bonus(Object lock, String[] song, int[] index) {
        this.lock = lock;
        this.song = song;
        this.index = index;
    }

    @Override
    public void run() {
        FilePlayer fp = new FilePlayer();
        synchronized (lock) {
            while (index[0] < song.length) {
                String note = song[index[0]];
                // Thread 2 plays only re, fa, la , do-octave note
                if (note.equals("re") || note.equals("fa") || note.equals("la") || note.equals("do-octave")) {
                    fp.play("src/Sounds/" + note + ".wav");
                    // If note currently at 14 then go to new line
                    System.out.print(note + " ");
                    if ((index[0] + 1) % 14 == 0 ) {
                        System.out.println();
                    }
                    // index to keep track of which note in the song
                    index[0]++;
                    lock.notify(); 
                } else { // if not is not re, fa, la, do-octavae, notify thread 1
                    lock.notify();
                }
                try {
                    lock.wait(); // Wait for Thread1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.notify(); // Ensure Thread1to exit after done 
        }
    }
}
