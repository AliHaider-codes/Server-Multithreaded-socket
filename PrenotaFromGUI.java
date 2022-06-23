import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrenotaFromGUI implements ActionListener {

    private GUI gui;
    private MyWorker worker;

    public PrenotaFromGUI(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        
        worker = new MyWorker(gui);
        worker.execute();
       // SyncronizedListener sync=new SyncronizedListener(gui);
        //sync.actionPerformed(ev);
    }
}
