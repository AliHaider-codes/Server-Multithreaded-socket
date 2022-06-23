import javax.swing.*;
import java.util.List;
import java.awt.GridBagLayout;
import java.net.*;
import java.io.*;
import java.awt.GridBagConstraints;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ExecutionException;

public class MyWorker extends SwingWorker<String, Integer> {
	
	private GUI gui;
    private String str;

    public MyWorker(GUI gui) {
        this.gui=gui;
    }


    
    //eseguito quando facciamo execute() di un oggetto MyWorker eseguito da thread worker
    @Override
    protected String doInBackground() throws Exception {
        Thread.sleep(1000);
        int NumPosti=(int)gui.NumPosti.getValue();
        String RichiestaPerServer=gui.getNomeEventoScelto() + "-" + NumPosti;
        InetAddress addr = InetAddress.getByName(null);

        Socket socket=new Socket(addr, 8087);

        try {
            System.out.println("socket = " + socket);
            
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())),
                    true);


                out.println(RichiestaPerServer);
                str = in.readLine();
                //JOptionPane.showMessageDialog(null, str);
                System.out.println("CLIENT: "+str);
            out.println("END");
        } 
        finally{
            socket.close();
            System.out.println("CLIENT: chiusura connessione");
        }
        
        return "Done!";
    }
    
    @Override
    protected void process(List<Integer> chunks) {
    }

    
   //eseguito dopo la doInBackground() eseguito da "Event Dispatch Thread"
    @Override
    protected void done() {
        JOptionPane.showMessageDialog(null, str);
    }
    

}
