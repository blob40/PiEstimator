import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PiEstimator extends Thread {
	static Thread runner;
	static int Tottrials = 0;
	static int trials = 0;
	static double piNum;
	static JFrame f = new JFrame("Button Example");
	static	JButton P = new JButton();
	static	JLabel example = new JLabel("Actual Value " + Double.toString(Math.PI));
	static	JLabel ceL = new JLabel ();
	static	JLabel trialL = new JLabel();
	// the following code is just to jog your memory about how labels and buttons
	// work!
	// implement your Pi Estimator as described in the project. You may do it all in
	// main below or you
	// may implement additional functions if you feel it necessary.

	public PiEstimator (){


     
	}

	public static void main(String[] args) {
		PiEstimator pi = new PiEstimator();
		runner = pi;
		pi.start();

		P.setText("Pause");
		ceL.setText("Current Estimate " + piNum);
		trialL.setText("Total Trials " + 0);
		f.add(example);
		f.add(ceL);
		f.add(trialL);
		f.add(P);
		f.setSize(300, 300);
		f.setLayout(new GridLayout(4, 1));
		f.setVisible(true);


		
	}

	public void update (){
		ceL.setText("Current estimate " + piNum);
	}

	public synchronized void run() {
		
		Boolean running;
		
		synchronized (runner) {
			running = true;
			runner.notify();
		}

		while (true) {
			synchronized (this) {
				while (!running) { // wait for running to be true
					try {
						wait();
					} catch (InterruptedException e) {
					}
				}
			
			double x = Math.random();
			double y = Math.random();
			Tottrials++;
			//System.out.println("total + " + Tottrials);
			if((x*x+y*y) <= 1){
				trials++;
				piNum = 4.0*(trials)/Tottrials;
				
				//mod %mil
				if (Tottrials%100000 == 0){
					System.out.println("update");
				update();
			}

			trialL.setText("Total Trials " + Tottrials);
			//System.out.println("Not update");
				//System.out.println("tot mod = " + Tottrials%1000000);
				//System.out.println("Trials + " + trials + " pi num + " + piNum);
			}
			// rest of your code actually doing stuff here
		}
		}

	}
}
