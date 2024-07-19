import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args) throws IOException{
      System.out.println("Logs from your program will appear here!");

      int port = 6379;
      Socket clientSocket = null;
      ServerSocket serverSocket = null;
      try {
          serverSocket = new ServerSocket(port);
          serverSocket.setReuseAddress(true);
          clientSocket = serverSocket.accept();

          try (OutputStream outputStream = clientSocket.getOutputStream()) {
              // Since we're ignoring the actual input, we always respond with PONG
              while (true) {
                outputStream.write("+PONG\r\n".getBytes());
                outputStream.flush();
              }   
          } catch (IOException e) {
              System.out.println("IOException: " + e.getMessage());
          }
          
        } finally {
          try {
            if (clientSocket != null) {
              clientSocket.close();
              serverSocket.close();
            }
          } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
          }
      }
    }
  }
