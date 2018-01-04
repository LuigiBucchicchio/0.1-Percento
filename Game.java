import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Game {


	public static void main (String[] args) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException{
		String incoraggiamentoPrecedente=null;
		String stringa=null;
		int volte=1;
		File tentativi=new File("tentativi.txt");
		File personaggio= new File("personaggio.txt");
		File eroi= new File("eroi.txt");
		File coraggio= new File("incoraggiamenti.txt");
		File achievement=new File("achievement.txt");
		Scanner s;
		String risposta;
		boolean ancora=false;
		boolean rush=false;
		InputStreamReader input=new InputStreamReader(System.in);
		Scanner inputScanner= new Scanner(input);
		do{
			ArrayList<String> incoraggiamenti=new ArrayList<String>();
			if(!personaggio.exists()){
				FileWriter f= new FileWriter(personaggio);
				System.out.println("Benvenuto in 0.1%. Tu sei...");
				do{
					stringa=inputScanner.nextLine();
					if(stringa.contains("*"))
						System.out.println("Non è ammesso il carattere speciale <*>");
				}while(stringa.contains("*"));
				f.write(stringa);
				f.close();
				System.out.println("In Questo gioco sfiderai la probabilità dello 0.1%. Ovvero 1 su 1000\n"
						+"Io, programmatore, ti accompagnerò ad ogni tentativo =D Cominciamo? Premi invio per continuare...");
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

				//aggiunta del secondo e terzo achievement
				if(achievement.exists()){
					Scanner scannerino=new Scanner(achievement);
					boolean secondoA=false;
					boolean terzoA=false;
					while(scannerino.hasNext()){
						int x= scannerino.nextInt();
						if(x==1)
							secondoA=true;
						else if(x==2)
							terzoA=true;
					}
					scannerino.close();
					if(secondoA){
						f.write("*Il Super Fortunato* ");
					}
					if(terzoA){
						f.write("*Vero Vincitore* ");
					}
				}

				f.write(stringa+"%5");
				f.write(""+volte+"%5");
				f.close();
				personaggio.delete();
				tentativi.delete();
				achievement.delete();
				achievement.createNewFile();
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

					Random numeroRandom= new Random();
					int index=numeroRandom.nextInt(incoraggiamenti.size());
					String temp=incoraggiamenti.get(index);
					System.out.println(temp);
					if(temp.equals(incoraggiamentoPrecedente)){
						if(volte<=100){

							ArrayList<Integer> premi=new ArrayList<Integer>(3);
							Scanner scannerini= new Scanner(achievement);
							while(scannerini.hasNext())
								premi.add(Integer.valueOf(scannerini.nextLine()));
							scannerini.close();

							if(!premi.contains(new Integer(0))){

								AudioInputStream audio = AudioSystem.getAudioInputStream(new File("Unlocked.wav"));
								Clip clip = AudioSystem.getClip();
								clip.open(audio);
								clip.loop(0);
								System.out.println("Hai appena battuto la probabilità di 0,025% (approssimativamente) ovvero ottenere lo stesso incoraggiamento (LVL1) due volte di fila");
								Thread.sleep(6000);
								System.out.println("Complimenti!! Aspetta... non ero preparato a questo...");
								Thread.sleep(4000);
								System.out.println("Facciamo che avrai un premio speciale.");
								Thread.sleep(3500);

								//scrittura achievement 1 sul file


								achievement.delete();
								achievement.createNewFile();
								FileOutputStream fs1 = new FileOutputStream(achievement);
								BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(fs1));
								if(!premi.contains(new Integer(0))){
									if(premi.isEmpty()){
										bw1.write("0");
										bw1.newLine();
										bw1.close();
										fs1.close();
									}else {
										bw1.write("0");
										bw1.newLine();
										while (!premi.isEmpty()) {
											bw1.write(""+premi.remove(0).toString());
											bw1.newLine();
										}
										bw1.close();
										fs1.close();
									}
								}else{
									while (!premi.isEmpty()) {
										bw1.write(""+premi.remove(0).toString());
										bw1.newLine();
									}
									bw1.close();
									fs1.close();
								}

								//aggiunta achievement 1

								ArrayList<String> temporaneo=new ArrayList<String>();
								Scanner sTemp=new Scanner(coraggio);
								while(sTemp.hasNext())
									temporaneo.add(sTemp.nextLine());
								sTemp.close();
								coraggio.delete();
								temporaneo.add(""+stringa+" Fa il tifo per te!");
								coraggio=new File("incoraggiamenti.txt");
								FileOutputStream fos = new FileOutputStream(coraggio);
								BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(fos));
								while(!temporaneo.isEmpty()){
									buffer.write(temporaneo.remove(0));
									buffer.newLine();
								}
								buffer.close();
								fos.close();

							}


						}else if(volte>100&&volte<=500){

							ArrayList<Integer> premi=new ArrayList<Integer>(3);
							Scanner scannerini= new Scanner(achievement);
							while(scannerini.hasNext())
								premi.add(Integer.valueOf(scannerini.nextLine()));
							scannerini.close();

							if(!premi.contains(new Integer(1))){

								AudioInputStream audio = AudioSystem.getAudioInputStream(new File("Unlocked.wav"));
								Clip clip = AudioSystem.getClip();
								clip.open(audio);
								clip.loop(0);
								System.out.println("Hai appena battuto la probabilità di 0,0125%  circa, ovvero ottenere lo stesso incoraggiamento (LVL2) due volte di fila");
								Thread.sleep(5000);
								System.out.println("Complimenti!! Aspetta... non ero preparato a questo...");
								Thread.sleep(4000);
								System.out.println("Facciamo che avrai un premio molto speciale.");
								Thread.sleep(3500);

								//aggiunta achievement 2 sul file

								achievement.delete();
								achievement.createNewFile();
								FileOutputStream fs2 = new FileOutputStream(achievement);
								BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(fs2));
								if(!premi.contains(new Integer(1))){
									if(premi.isEmpty()){
										bw2.write("1");
										bw2.newLine();
										bw2.close();
										fs2.close();
									}else if(premi.get(0).equals(new Integer(0))){
										bw2.write(""+premi.remove(0).toString());
										bw2.newLine();
										bw2.write("1");
										bw2.newLine();
										while (!premi.isEmpty()) {
											bw2.write(""+premi.remove(0).toString());
											bw2.newLine();
										}
										bw2.close();
										fs2.close();
									}else{
										bw2.write("1");
										bw2.newLine();
										while (!premi.isEmpty()) {
											bw2.write(""+premi.remove(0).toString());
											bw2.newLine();
										}
										bw2.close();
										fs2.close();
									}
								}else{
									while (!premi.isEmpty()) {
										bw2.write(""+premi.remove(0).toString());
										bw2.newLine();
									}
									bw2.close();
									fs2.close();
								}


							}

						}else if(volte>500){

							ArrayList<Integer> premi=new ArrayList<Integer>(3);
							Scanner scannerini= new Scanner(achievement);
							while(scannerini.hasNext())
								premi.add(Integer.valueOf(scannerini.nextLine()));
							scannerini.close();

							if(!premi.contains(new Integer(2))){

								AudioInputStream audio = AudioSystem.getAudioInputStream(new File("Unlocked.wav"));
								Clip clip = AudioSystem.getClip();
								clip.open(audio);
								clip.loop(0);
								System.out.println("Hai appena battuto la probabilità di 0.01% ovvero ottenere lo stesso incoraggiamento (LVL3) due volte di fila");
								Thread.sleep(5000);
								System.out.println("Complimenti!! Aspetta... non ero preparato a questo...");
								Thread.sleep(4000);
								System.out.println("Facciamo che avrai un premio molto, MOLTO speciale.");
								Thread.sleep(3500);


								achievement.delete();
								achievement.createNewFile();
								FileOutputStream fs3 = new FileOutputStream(achievement);
								BufferedWriter bw3 = new BufferedWriter(new OutputStreamWriter(fs3));
								if(!premi.contains(new Integer(2))){
									if(premi.isEmpty()){
										bw3.write("2");
										bw3.newLine();
										bw3.close();
										fs3.close();
									}else{
										while (!premi.isEmpty()) {
											bw3.write(""+premi.remove(0).toString());
											bw3.newLine();
										}
										bw3.write("2");
										bw3.newLine();
										bw3.close();
										fs3.close();
									}
								}else{
									while (!premi.isEmpty()) {
										bw3.write(""+premi.remove(0).toString());
										bw3.newLine();
									}
									bw3.close();
									fs3.close();
								}

							}

						}

					}// solo se l'incoraggiamento è uguale al precedente

					incoraggiamentoPrecedente=temp; // il nuovo diventa il prossimo precedente

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
					if(volte==325){
						AudioInputStream audio = AudioSystem.getAudioInputStream(new File("Unlocked.wav"));
						Clip clip = AudioSystem.getClip();
						clip.open(audio);
						clip.loop(0);
						System.out.println("Hai sbloccato la Modalità Super Caparbio."
								+ "\nOra puoi rispondere anche soltanto con <s> o <n>. Velocità di "+stringa+" aumenta!");
					}
					if(volte==750){
						AudioInputStream audio = AudioSystem.getAudioInputStream(new File("Unlocked.wav"));
						Clip clip = AudioSystem.getClip();
						clip.open(audio);
						clip.loop(0);
						System.out.println("Hai sbloccato la Modalità RUSH."
								+ "\nAdesso ti verrà chiesto (solo una volta ogni riavvio) se vuoi entrare in modalità Rush"
								+ "\nEssa consiste nel dover premere solo Invio, ma non può essere disattivata senza riavvio."
								+ "\nVelocità di "+stringa+" è al massimo!");
					}
					if(volte>=750){
						if(!rush&&!ancora){
							do{
								System.out.println("RUSH MODE? (si/no)");
								risposta=inputScanner.nextLine();
							}while(!risposta.equals("si")&&!risposta.equals("no"));
							if(risposta.equals("si"))
								rush=true;
						}
					}
					if(volte>=325&&!rush){
						do{
							System.out.println("Riprovare? (si/no) oppure (s/n)");
							risposta=inputScanner.nextLine();
						}while(!risposta.equals("si")&&!risposta.equals("no")&&!risposta.equals("s")&&!risposta.equals("n"));

						if(risposta.equals("si")||risposta.equals("s"))
							ancora=true;
						else ancora=false;
					}
					if(volte<325&&volte>=10){
						do{
							System.out.println("Riprovare? (si/no)");
							risposta=inputScanner.nextLine();
						}while(!risposta.equals("si")&&!risposta.equals("no"));

						if(risposta.equals("si"))
							ancora=true;
						else ancora=false;
					}

					if(rush){
						System.out.println("RUSH MODE. Premi invio per continuare...");
						inputScanner.nextLine();
						ancora=true;
					}

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