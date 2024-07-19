import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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

          multiablePingResponse(clientSocket);
          
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

    
    public static void multiablePingResponse(Socket clientSocket){
      try(BufferedReader reader =new BufferedReader(
              new InputStreamReader(clientSocket.getInputStream()));
          BufferedWriter writer = new BufferedWriter(
              new OutputStreamWriter(clientSocket.getOutputStream()));) {

                String clientData;
                while ((clientData = reader.readLine()) != null) {
                  if("ping".equalsIgnoreCase(clientData)){
                    writer.write("PONG");
                    writer.flush();
                    
                  }
                  
                }
        
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

    }
  }
