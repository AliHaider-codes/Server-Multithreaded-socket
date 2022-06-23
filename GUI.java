
import javax.swing.*;
import javax.swing.SpinnerNumberModel;
import java.awt.*;

//import javafx.scene.layout.Pane;

import java.util.Map;

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;
	JButton Prenota;
    JSpinner  NumPosti;
    private String NomeEventoScelto;
    private int PostiDaPrenotare;
    int counter;
    JPanel Panel;
    JPanel EventiPanel;
    
    public GUI() {
        super("Concurrent bookingPool");
        
        Panel = new JPanel();
        EventiPanel=new JPanel();
        JButton Sincronized=new JButton("Sincronizza");
        SyncronizedListener sync=new SyncronizedListener(this);
        Sincronized.addActionListener(sync);
        Panel.add(Sincronized);
        Panel.add(EventiPanel);

        
        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 10, 1);
        NumPosti=new JSpinner(model);
        Prenota=new JButton("Prenota Ora");
        Panel.add(NumPosti);
        Panel.add(Prenota);
        Prenota.addActionListener(new PrenotaFromGUI(this));
        NumPosti.setVisible(false);
        Prenota.setVisible(false);

        getContentPane().add(Panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }


    public void setNomeEventoScelto(String NomeEvento){
        this.NomeEventoScelto=NomeEvento;
    }

    public void setPostiDaPrenotare(int NumPosti){
        this.PostiDaPrenotare=NumPosti;
    }

    public String getNomeEventoScelto(){
        return this.NomeEventoScelto;
    }

    public int getPostiDaPrenotare(){
        return this.PostiDaPrenotare;
    }
    
}
