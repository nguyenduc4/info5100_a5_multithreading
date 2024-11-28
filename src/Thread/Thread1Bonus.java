package Thread;

import FilePlayer.FilePlayer;

public class Thread1Bonus extends Thread {
    private final Object lock;
    private final String[] song;
    private final int[] index; // index to indicate which note has bene play

    public Thread1Bonus(Object lock, String[] song, int[] index) {
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
                // Thread 1 play only do , mi, sol, si, do-octave note
                if (note.equals("do") || note.equals("mi") || note.equals("sol") || note.equals("si") || note.equals("do-octave")) {
                    fp.play("src/Sounds/" + note + ".wav");
                    System.out.print(note + " ");
                    // If note currently at 14 then go to new line
                    if ((index[0] + 1) % 14== 0 ) {
                        System.out.println();
                    }
                    // index to keep track of which note in the song
                    index[0]++; 
                    lock.notify(); 
                } else { // if not is not do, mi ,sol, si, do-octave, notify thread 1
                    lock.notify();
                }
                try {
                    lock.wait(); // Wait for Thread1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.notify(); 
        }
    }
}
