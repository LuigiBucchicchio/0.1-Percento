import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Game {
	
	
public static void main (String[] args) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException{
	ArrayList<String> incoraggiamenti=new ArrayList<String>();
	String stringa=null;
	int volte=1;
	File tentativi=new File("tentativi.txt");
	File personaggio= new File("personaggio.txt");
	File eroi= new File("eroi.txt");
	File coraggio= new File("incoraggiamenti.txt");
	Scanner s;
	String risposta;
	boolean ancora=false;
	InputStreamReader input=new InputStreamReader(System.in);
	Scanner inputScanner= new Scanner(input);
	do{
	
	if(!personaggio.exists()){
		FileWriter f= new FileWriter(personaggio);
		System.out.println("Benvenuto in 0.1%. Tu sei...");
		stringa=inputScanner.nextLine();
		f.write(stringa);
		f.close();
		System.out.println("In Questo gioco sfiderai la probabilità dello 0.1%. Ovvero 1 su 1000\n"
				+"Io, programmatore ti accompagnerò ad ogni tentativo =D Cominciamo? Premi invio per continuare...");
		inputScanner.nextLine(); //press any key
	}else{
		s=new Scanner(personaggio);
		stringa=s.nextLine();
		s.close();
	}
	if(tentativi.exists()){
		s=new Scanner(tentativi);
		volte=Integer.parseInt(s.nextLine());
		volte++;
		tentativi.delete();
		tentativi= new File("tentativi.txt");
		FileWriter f = new FileWriter(tentativi);
		f.write(Integer.toString(volte));
		s.close();
		f.close();
	}else{
		tentativi= new File("tentativi.txt");
		FileWriter f = new FileWriter(tentativi);
		f.write("1");
		f.close();
	}
	Probabilità p=new Probabilità(1, 1000);
	if(p.iterOnProb()){
		System.out.println(stringa+", Hai Vinto!\n");
		AudioInputStream audio = AudioSystem.getAudioInputStream(new File("Victory.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audio);
				clip.loop(0);
		Thread.sleep(3000);
		System.out.println("Sei stato bravo, non hai mollato, sei stato determinato!!\n");
		Thread.sleep(3000);
		System.out.println("hai impiegato "+volte+" tentativi\n");
		Thread.sleep(3000);
		System.out.println("hai affrontato una probabilità molto bassa. Secondo alcuni, hai vinto un tumore con la sola determinazione, per esempio\n");
		Thread.sleep(5000);
		System.out.println("hai appena preso l'unico numero vincente su 1000\n");
		Thread.sleep(3000);
		System.out.println("hai appena fatto testa 10 volte di fila (circa)\n");
		Thread.sleep(3000);
		System.out.println("hai appena catturato Darkrai in piena salute con una Pokèball\n");
		Thread.sleep(3000);
		System.out.println("hai appena fatto 1 oppure 20 con un dado a 20 facce per 3 volte di fila\n");
		Thread.sleep(4000);
		System.out.println("tu sei quel 1 su 1000 che ce la fa\n");
		Thread.sleep(3000);
		System.out.println("hai appena fatto ambo al lotto con due coppie distinte (circa)\n");
		Thread.sleep(3000);
		System.out.println("hai appena scommesso il primo dell'anno 2018 che la Fiorentina vinca il campionato di Serie A. Ed hai vinto. (OMG) \n");
		Thread.sleep(4000);
		System.out.println("Sei troppo forte!!!!!\n");
		Thread.sleep(3000);
		System.out.println("ci sono voluti diversi tentativi, certo, ma ci sono molte persone che si sono arrese senza neanche provare");
		Thread.sleep(3000);
		System.out.println("\n tu non sei tra queste. Non è questione di sola fortuna, è anche una questione di coraggio, forza, tenacia qualità importanti nella vita");
		Thread.sleep(4000);
		System.out.println("\n Sei un esempio da seguire, grazie per aver giocato. Sei stato aggiunto alla lista degli eroi di 0.1%\n");
		Thread.sleep(3000);
		s=new Scanner(eroi);
		s.useDelimiter("%5");
		ArrayList<String> heroes=new ArrayList<String>();
		while(s.hasNext()){
		heroes.add(s.next()+"%5");
		}
		s.close();
		FileWriter f=new FileWriter(eroi);
		while(!heroes.isEmpty()){
			f.write(heroes.remove(0));
		}
		f.write(stringa+"%5");
		f.write(""+volte+"%5");
     	f.close();
		personaggio.delete();
		tentativi.delete();
		ancora=false;
		System.out.println("LISTA DEGLI EROI\n");
		Thread.sleep(4000);
		s=new Scanner(eroi);
		s.useDelimiter("%5");
		while(s.hasNext()){
			System.out.println(s.next()+" "+s.next()+" tentativi\n");
			Thread.sleep(2000);
		}
		s.close();
	}
	else {
		System.out.println(stringa+", Hai Perso!");
		if(volte==1000){
			System.out.println("Sei stato particolarmente sfortunato fino ad ora, ma ormai manca davvero poco!!");
		}else{
			s=new Scanner(coraggio);
			while(s.hasNext()){
				incoraggiamenti.add(s.nextLine());
			}
			s.close();
			if(volte>=100){
				s=new Scanner(new File("super.txt"));
				while(s.hasNext())
					incoraggiamenti.add(s.nextLine());
				s.close();
			}
			if(volte>=500){
				s=new  Scanner(new File("secret.txt"));
				while(s.hasNext())
					incoraggiamenti.add(s.nextLine());
				s.close();
			}
			Collections.shuffle(incoraggiamenti);
			System.out.println(incoraggiamenti.get(0));
		}
		if(volte<10){
		System.out.println("Ri-esegui per continuare. (potresti sbloccare qualcosa più avanti...)");
		
		}else{
			
			if(volte==10){
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File("Unlocked.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audio);
				clip.loop(0);
				System.out.println("Hai sbloccato la Modalità Caparbio. Ora sei all'interno di un ciclo =D. Velocità di "+stringa+" aumenta!");
				
			}
			do{
				System.out.println("Riprovare? (si/no)");
				risposta=inputScanner.nextLine();
			}while(!risposta.equals("si")&&!risposta.equals("no"));
			if(risposta.equals("si"))
				ancora=true;
			else ancora=false;
			if(volte==100){
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File("Unlocked.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audio);
				clip.loop(0);
				System.out.println("Sei un tipo tosto, hai sbloccato altri incoraggiamenti! Premi invio per continuare...");
				inputScanner.nextLine();
			}
			if(volte==500){
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File("Unlocked.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audio);
				clip.loop(0);
				System.out.println("Sei da invidiare tanto quanto lui...\n"
						+ "hai sbloccato degli incoraggiamenti Segreti. Premi invio per continuare...");
				inputScanner.nextLine();
			}
			
		} //chiusura volte >= 10
	} // chiusura esito negativo
	}while(ancora); //chiusura esiti
	inputScanner.close();
	input.close();
} //chiusura main
}
