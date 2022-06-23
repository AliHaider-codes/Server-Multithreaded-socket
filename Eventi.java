import java.util.*;

public class Eventi{

    /* Con questa struttura dati i lock sono sui singoli eventi (più precisamente sul numero di posti disponibili), in 
    questo modo è garantito che solo un thread alla volta può leggere e/o modificare il dato relativo al numero di posti
    di un certo evento, inoltre è garantito il parallelismo perche è possibile effettuare prenotazioni in parallelo purchè queste 
    siano riferite ad eventi diversi.
     */
    private Map<String, MyAtomicInteger> EventList;

    public Eventi() {
        this.EventList = new HashMap<String, MyAtomicInteger>();
    }

    public Map<String, MyAtomicInteger> getEventList(){
        return this.EventList;
    }

    public synchronized void Crea(String Nome, int Posti) {
        if(this.EventList.putIfAbsent(Nome, new MyAtomicInteger(Posti))==null)
            System.out.println("Creato il nuovo evento : " + Nome + " di posti :"+ Posti + ";\n");
        else
            System.out.println("Evento già presente : " + Nome+";\n");
    }

    public void Aggiungi(String Nome, int Posti) {
        if( this.EventList.get(Nome) == null){//evento non esiste
            System.out.println("Prenotazione fallita, Evento : " + Nome + " non esiste;\n");
            return;
        }
        this.EventList.get(Nome).aggiungi(Posti);
        System.out.println("Aggiunti : " + Posti + " ad evento :"+ Nome + ";\n");
    }

    public  boolean Prenota(String Nome, int Posti) {
        if(this.EventList.get(Nome) == null){//evento non esiste
            System.out.println("Prenotazione fallita, Evento : " + Nome + " non esiste;\n");
            return false;
        }
      return this.EventList.get(Nome).prenota(Posti);//se prenotazione fallisce allora si mette in wait per aspettare aggiunta nuovi posti
    
    }

    public String ListEventi() {
        String ListaEventi="";
        for (Map.Entry<String, MyAtomicInteger> entry : this.EventList.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().getPosti());
            ListaEventi=ListaEventi+"-"+entry.getKey() + "-" + entry.getValue().getPosti();
        }
        return ListaEventi;
    }

    public synchronized void Chiudi(String Nome) {
        this.EventList.remove(Nome);
        System.out.println(" Evento : " + Nome + " chiuso\n");
    }
}