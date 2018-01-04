import java.util.Random;

public class Probabilità{
public long CasiFav=0;
public long CasiTot=0;
/**
 * costruzione di una probabilità
 * @param CasiFavorevoli
 * @param CasiTotali
 */
public Probabilità(long CasiFav, long CasiTot){
	this.CasiFav=CasiFav;
	this.CasiTot=CasiTot;
}
public String getProb(){
	return ""+(double)CasiFav*100/CasiTot+"%, ovvero "+CasiFav+"/"+CasiTot;
}
public boolean iterOnProb(){
	Random r= new Random();
	int x=r.nextInt((int)(CasiTot));
	if(x<=(((int)CasiFav)-1)) 
		return true;
	return false;
}
}
