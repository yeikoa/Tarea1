
package servidor;

import java.util.HashSet;
import java.util.Set;


public class Productor extends Thread{
    private Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        Set<Integer> generatedNumbers = new HashSet<>();
        while (true) {
            
            try {
                int num;
                do {
                    num = (int) (Math.random() * 75) + 1;
                } while (!generatedNumbers.add(num));
                buffer.put(num);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Error al producir número: " + e.getMessage());
            }
        }
    }

}
