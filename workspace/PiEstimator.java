import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PiEstimator extends Thread {
	static Thread runner;
	static Boolean running;
	static int Tottrials = 0;
	static int trials = 0;
	static double piNum;
	static JFrame f = new JFrame("Button Example");
	static JButton P = new JButton();
	static JLabel example = new JLabel("Actual Value " + Double.toString(Math.PI));
	static JLabel ceL = new JLabel();
	static JLabel trialL = new JLabel();
	// the following code is just to jog your memory about how labels and buttons
	// work!
	// implement your Pi Estimator as described in the project. You may do it all in
	// main below or you
	// may implement additional functions if you feel it necessary.

	public static void main(String[] args) {
		PiEstimator pi = new PiEstimator();
		runner = pi;
		pi.start();

		P.setText("Pause");
		ceL.setText("Current Estimate " + 0);
		trialL.setText("Total Trials " + 0);
		f.add(example);
		f.add(ceL);
		f.add(trialL);
		f.add(P);
		f.setSize(300, 300);
		f.setLayout(new GridLayout(4, 1));
		f.setVisible(true);

		P.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			if (P.getText() == "Pause"){
				running = false;
				P.setText("Run");
				//notify();
			} else if (P.getText() == "Run"){
				running = true;
				P.setText("Pause");
				//notify();
			}

				synchronized(pi) {
					pi.notify();
				}
				//if (running = true){
				//	P.setText("Run");
				//} else {
				//	P.setText("Pause");
				//}
				System.out.println("Shouldve stopped" + running);
			}
		});

	}

	public synchronized void run() {

		

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
				trialL.setText("Total Trials " + Tottrials);
				System.out.println("total + " + Tottrials);
				if ((x * x + y * y) <= 1) {
					trials++;
					piNum = 4.0 * (trials) / Tottrials;

				}

				if (Tottrials % 50000 == 0) {
						ceL.setText("Current estimate " + piNum);
					}
				// rest of your code actually doing stuff here
			}
		}

	}
}
