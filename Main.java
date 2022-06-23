import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        ThreadPooledServer server = new ThreadPooledServer(8087);
        new Thread(server).start();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI gui = new GUI();
                gui.setSize(1000, 1000);
            }
        });

        try {
            Thread.sleep(600 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stopping Server");
        server.stop();
    }
}
