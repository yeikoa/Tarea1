package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        String servidor = "localhost";
        int puerto = 8000;
        
        try {
            Socket socket = new Socket(servidor, puerto);
            System.out.println("Conectado al servidor en " + servidor + ":" + puerto);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensaje;
            
            while ((mensaje = in.readLine()) != null) {
                System.out.println(mensaje);
            }
            
            socket.close();
        } catch (IOException e) {
            System.out.println("Error al conectarse al servidor: " + e.getMessage());
        }
    }
}