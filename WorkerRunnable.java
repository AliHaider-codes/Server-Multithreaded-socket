
import java.io.*;

import java.net.Socket;


public class WorkerRunnable implements Runnable{

    protected Socket clientSocket = null;
    protected String serverText   = null;
    private Eventi e=null;

    public WorkerRunnable(Socket clientSocket, String serverText,Eventi e) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
        this.e=e;
    }

    public void run() {
        try {

            BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        clientSocket.getOutputStream())),
                        true);
                while (true) {
                    String str = in.readLine();
                    if (str.equals("END"))
                        break;
                    if(str.equals("SYNC")){
                        out.println(e.ListEventi());
                        System.out.println("SERVER: invio lista eventi aggiornata");
                        
                        break;
                    }
                   
                    String[] strsplit=str.split("-");
                    if(e.Prenota(strsplit[0], Integer.parseInt(strsplit[1]))){
                        out.println("PRENOTAZIONE EFFETTUATA");
                        System.out.println("SERVER: Prenotazione effettuata: " +strsplit[1]+" Posti per "+strsplit[0]);
                    }
                    else{
                        out.println("PRENOTAZIONE FALLITA");
                        System.out.println("SERVER: Prenotazione fallita:"+strsplit[0]);

                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}