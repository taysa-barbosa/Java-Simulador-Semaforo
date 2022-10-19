

public class Simulador implements Runnable {
	private Thread thrd; // thread que executa a simulação
	private Cores tlc; // mantém a cor do semáforo atual
	boolean stop = false; // define como true para interromper a simulação
	
	Simulador(Cores init) {
		tlc = init;
		
		thrd = new Thread(this);
		thrd.start();
	}
	
	Simulador() {
		tlc =Cores.VERMELHO;
		
		thrd = new Thread(this);
		thrd.start();
	}
	
	// Liga a luz
	public void run() {
		while(!stop) {
			try {
				switch(tlc) {
					case VERDE:
						Thread.sleep(1000); // green for 0.1 seconds
						break;
					case AMARELO:
						Thread.sleep(200); // yellow for 0.02 seconds
						break;
					case VERMELHO:
						Thread.sleep(1200); // red for 0.12 seconds
						break;
				}
			} catch(InterruptedException exc) {
				System.out.println(exc);
			}
			changeColor();
		}
	}
	
	// muda a cor
	synchronized void changeColor() {
		switch(tlc) {
			case VERMELHO:
				tlc = Cores.VERDE;
				break;
			case AMARELO:
				tlc = Cores.VERMELHO;
				break;
			case VERDE:
				tlc = Cores.AMARELO;
		}
		
		notify(); // sinal de que a luz mudou
	}
	
	// Aguarde até que ocorra uma mudança de luz.
	synchronized void waitForChange() {
		try {
			wait(); //espere a luz mudar
		} catch(InterruptedException exc) {
			System.out.println(exc);
		}
	}
	
	// Retorna a cor atual.

	Cores getCores() {
		return tlc;
	}
	
	//Pare o semáforo.
	void cancel() {
		stop = true;
	}

}
