
package servidor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

   public static int numero = 0;
    public static void main(String[] args) throws IOException {
        int puerto = 8000;
        ServerSocket servidor = new ServerSocket(puerto);

        System.out.println("Servidor iniciado en el puerto " + puerto);

        int maxSize = 75;
        Buffer buffer = new Buffer(maxSize);
        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);

        productor.start();
        consumidor.start();

        while (true) {
            Socket cliente = servidor.accept();
            System.out.println("Nuevo cliente conectado: " + cliente.getInetAddress().getHostAddress());
            HiloCliente hilo = new HiloCliente(cliente, buffer);
            hilo.start();
        }
    }
}
