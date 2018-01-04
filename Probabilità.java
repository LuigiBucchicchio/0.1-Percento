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
	int i=(int)(Math.random()*(CasiTot-1)); // 0...N 4
	i++;
	if(i<=CasiFav&&i>=1) // i (1...5) <= 1
		return true;
	return false;
}
}
