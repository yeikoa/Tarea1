
package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloCliente extends Thread{
    private Socket cliente;
    private Buffer buffer;

    public HiloCliente(Socket cliente, Buffer buffer) {
        this.cliente = cliente;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

            String mensaje = "";
            while (!mensaje.equals("FIN")) {
                mensaje = entrada.readLine();
                if (mensaje.equals("NUEVO")) {
                    salida.println(buffer.get());
                }
            }

            cliente.close();
        } catch (IOException e) {
            System.out.println(cliente.getInetAddress().getHostAddress() + " Se Desconecto");
        }
    }
}
