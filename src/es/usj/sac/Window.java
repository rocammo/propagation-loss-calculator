package es.usj.sac;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import es.usj.sac.dialog.Cost231HataModel;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	private final static Logger LOGGER = Logger.getLogger("es.usj.sac.Window");

	private JFrame frame = this;

	private JPanel panel;

	private JLabel heading;

	private JButton cost231Hata;
	private JButton okumuraHata;
	private JButton cost231HataD;
	private JButton okumuraHataD;

	public Window() {
		super();

		init();
		setup();
	}

	/**
	 * Window configuration.
	 */
	private void init() {
		this.setTitle("FSPL");
		this.setSize(310, 210);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Window components configuration.
	 */
	private void setup() {
		panel = new JPanel(new GridLayout(0, 1, 10, 10));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		heading = new JLabel("Free Space Path Loss", SwingConstants.CENTER);

		// COST231-Hata model
		cost231Hata = new JButton("COST231-Hata model");
		cost231Hata.setFocusable(false);
		cost231Hata.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, cost231Hata.getText() + " clicked!");
				Cost231HataModel cost231HataModel = new Cost231HataModel(frame);
				cost231HataModel.setVisible(true);
			}
		});

		// Okumura-Hata model
		okumuraHata = new JButton("Okumura-Hata model");
		okumuraHata.setFocusable(false);
		okumuraHata.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});

		// COST231-Hata model (distance)
		cost231HataD = new JButton("COST231-Hata model (distance)");
		cost231HataD.setFocusable(false);
		cost231HataD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});

		// Okumura-Hata model (distance)
		okumuraHataD = new JButton("Okumura-Hata model (distance)");
		okumuraHataD.setFocusable(false);
		okumuraHataD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});

		panel.add(heading);
		panel.add(cost231Hata);
		panel.add(okumuraHata);
		panel.add(cost231HataD);
		panel.add(okumuraHataD);

		this.add(panel);
	}

	public static void main(String[] args) {
		Window window = new Window();
		window.setVisible(true);
	}
}
