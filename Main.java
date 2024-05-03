import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class Main {
    public static void main(String[] args) throws IOException {
        String host = "accounts.spotify.com";
        int port = 57621;
        String path = "/api/token";
        String params = "grant_type=client_credentials&client_id=your-client-id&client_secret=your-client-secret"; // Parâmetros da requisição POST

        // Cria um socket e se conecta ao servidor
        Socket socket = new Socket(host, port);

        // Obtem o OutputStream do Socket
        OutputStream os = socket.getOutputStream();

        // Escreve os dados da requisição POST no OutputStream
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
        writer.write("POST " + path + " HTTP/1.1\r\n");
        writer.write("Host: " + host + "\r\n");
        writer.write("Content-Type: application/x-www-form-urlencoded\r\n");
        writer.write("Content-Length: " + params.length() + "\r\n");
        writer.write("\r\n");
        writer.write(params);
        writer.flush();

        // Obtem o InputStream do Socket
        InputStream is = socket.getInputStream();

        // Lê a resposta do servidor do InputStream
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // Fecha o InputStream, o OutputStream e o Socket
        reader.close();
        writer.close();
        socket.close();
    }
}

// public class Main {
//     public static void main(String[] args) {
//         String apiHost = "https://accounts.spotify.com/api/token";
//         int apiPort = 80; // Porta padrão para requisições HTTP

//         try (Socket socket = new Socket(apiHost, apiPort)) {
//             String request = "GET /endpoint HTTP/1.1\r\n" +
//                              "Host: " + apiHost + "\r\n" +
//                              "Connection: close\r\n\r\n";

//             OutputStream out = socket.getOutputStream();
//             out.write(request.getBytes());

//             InputStream in = socket.getInputStream();
//             BufferedReader reader = new BufferedReader(new InputStreamReader(in));

//             String line;
//             while ((line = reader.readLine()) != null) {
//                 System.out.println(line);
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }
