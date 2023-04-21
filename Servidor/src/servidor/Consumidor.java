
package servidor;


public class Consumidor extends Thread {
    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                System.out.println("Error al esperar tiempo: " + e.getMessage());
            }

            if (buffer.getContador() >= 75) {
                System.out.println("Bingo completado!");
                System.exit(0);
            }
        }
    }
}