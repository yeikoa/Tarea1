package servidor;

public class Buffer {

    private int[] numeros;
    private int contador;
    private int maxSize;

    public Buffer(int maxSize) {
        numeros = new int[maxSize];
        contador = 0;
        this.maxSize = maxSize;
    }

    public synchronized void put(int num) {
        // Esperar si el buffer está lleno
        while (contador == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Error al esperar: " + e.getMessage());
            }
        }

        // Insertar número y actualizar contador
        numeros[contador++] = num;
        System.out.println("Se produjo el número " + num);
        

        // Notificar a los hilos en espera
        notifyAll();
    }

    public synchronized int get() {
        // Esperar si el buffer está vacío
        while (contador == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Error al esperar: " + e.getMessage());
            }
        }

        // Obtener el último número producido y actualizar contador
        int num = numeros[--contador];
        System.out.println("Se consumió el número " + num);
        // Notificar a los hilos en espera
        notifyAll();

        return num;
    }

    public synchronized int getContador() {
        return contador;
    }

    int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
