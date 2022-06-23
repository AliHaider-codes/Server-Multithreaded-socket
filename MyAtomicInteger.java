public class MyAtomicInteger {
    private int posti;

    public MyAtomicInteger (int posti){
        this.posti=posti;
    }

    public synchronized int aggiungi(int posti){
        this.posti=this.posti+posti;
        return this.posti;
    }

    public synchronized boolean prenota(int posti){
        if(this.posti<posti)
            return false;
        this.posti=this.posti-posti;
        return true;
    }
    public synchronized int getPosti(){
        return this.posti;
    }

        
}
