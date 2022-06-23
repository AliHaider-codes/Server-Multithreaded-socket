import javax.swing.*;
import java.util.List;
import java.awt.GridBagLayout;
import java.net.*;
import java.io.*;
import java.awt.GridBagConstraints;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ExecutionException;

public class MySyncWorker extends SwingWorker<String, Integer> {
	
	private GUI gui;
    private String ListaEventi;
    public MySyncWorker(GUI gui) {
        this.gui=gui;
    }


    
    //eseguito quando facciamo execute() di un oggetto MyWorker eseguito da thread worker
    @Override
    protected String doInBackground() throws Exception {
        Thread.sleep(1000);
        InetAddress addr = InetAddress.getByName(null);

        Socket socket=new Socket(addr, 8087);

        try {
            System.out.println("socket per sync= " + socket);
            
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())),
                    true);


                out.println("SYNC");
                ListaEventi = in.readLine();
                System.out.println("CLIENT : (lista ricevuta)"+ListaEventi);
                out.println("END");
        } 
        finally{
            System.out.println("closing...sync");
            socket.close();
        }
        
        return "Done!";
    }
    
    @Override
    protected void process(List<Integer> chunks) {
    }

    
   //eseguito dopo la doInBackground() eseguito da "Event Dispatch Thread"
    //update del counter e della label va fatta qui
    @Override
    protected void done() {
        this.gui.EventiPanel.removeAll();
        String[] splitListaEventi=ListaEventi.split("-");
        int counter=0;
        for (String str:splitListaEventi) {
            
            System.out.println(str+"\n");
            if(counter%2!=0){
                JLabel NomeEvento=new JLabel(str);
                JButton PrenotaEvento=new JButton("Prenota");
                MyListener SbloccaPrenotazione  = new MyListener(this.gui,str);
                PrenotaEvento.addActionListener(SbloccaPrenotazione);
                this.gui.EventiPanel.add(PrenotaEvento);
                this.gui.EventiPanel.add(NomeEvento);
                
            }
            else if(counter!=0){
            JLabel PostiEvento=new JLabel(str+" Rimasti");
            this.gui.EventiPanel.add(PostiEvento);
            }

            counter++;
            
            
        }
        this.gui.setVisible(true);
        this.gui.pack();

    //	gui.statusLabel.setText(Integer.toString(gui.counter));
    //	gui.step.setEnabled(true);
    }
    

}
