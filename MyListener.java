import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MyListener implements ActionListener {

    private GUI gui;
    private String NomeEvento;

    public MyListener(GUI gui, String NomeEventoDaPrenotare) {
        this.gui = gui;
        this.NomeEvento=NomeEventoDaPrenotare;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        gui.Prenota.setVisible(true);
        gui.NumPosti.setVisible(true);
        gui.setNomeEventoScelto(NomeEvento);
        gui.pack();
    }
}