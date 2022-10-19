
public class Principal {
	public static void main(String[] args) {

		Simulador t1 = new Simulador(Cores.VERDE);
		
		for(int i=0; i < 15; i++) {
			System.out.println(t1.getCores());
			t1.waitForChange();
		}
		t1.cancel();
	

}
}
