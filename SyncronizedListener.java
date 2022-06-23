import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class SyncronizedListener implements ActionListener {

    private GUI gui;

    public SyncronizedListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        MySyncWorker mySyncWorker=new MySyncWorker(gui);
        mySyncWorker.execute();
    }
}