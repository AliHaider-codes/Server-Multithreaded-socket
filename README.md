# Server-Multithreaded-socket
Sistema di gestione di prenotazione di eventi in modo concorrente in Java.

Operazioni: 
• un metodo “Crea(Nome,Posti)” per aggiungere un nuovo evento e i relativi posti
disponibili solo se non esiste già un evento con lo stesso nome.<br>
• un metodo “Aggiungi(Nome,Posti)” per aggiungere nuovi posti ad un determinato evento<br>
• un metodo “Prenota(Nome,Posti)” per prenotare posti per un dato evento,
il metodo deve essere bloccante se non ci sono abbastanza posti<br>
• un metodo “ListaEventi” per visualizzare su console eventi e posti ancora disponibili<br>
• un metodo “Chiudi(Nome)” che cancella l’evento e sblocca tutti i clienti in attesa di posti<br>

la struttura dati concorrente è di tipo HashMap gestita in modo tale da parallelizzare se possibile operazioni su eventi indipendenti tra loro

I client usano comunicazione TCP per inviare le richieste
(prenotazione e lista eventi) al server.

Il server ha architettura multithreaded basata su ServerSocket su TCP e gestisce le richieste di prenotazione dei client confinando i socket generati da una richiesta in un task separato.

Al programma client è associata una GUI minimale per visualizzare la lista degli
eventi e inviare le richieste di prenotazione.
