package es.usj.sac.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import es.usj.sac.controller.OkumuraHataController;
import es.usj.sac.util.SpringUtilities;

public class OkumuraHataModel extends JDialog {
	private static final long serialVersionUID = 1L;

	private JPanel panel;

	private JLabel label;

	private JTextField carrierFrequency;
	private JTextField distance;
	private JTextField txAntHeight;
	private JTextField rxAntHeight;
	private JLabel result;

	private JComboBox<String> environment;

	public OkumuraHataModel(JFrame parent) {
		super(parent, "Okumura-Hata model", true);
		this.setSize(310, 210);
		this.setLocation(parent.getX() + 300, parent.getY());

		setup();
	}

	private void setup() {
		panel = new JPanel(new SpringLayout());

		// Carrier Frequency
		label = new JLabel("Carrier Frequency: ", JLabel.TRAILING);
		panel.add(label);
		carrierFrequency = new JTextField(10);
		carrierFrequency.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				result.setText(predict());
			}
		});
		label.setLabelFor(carrierFrequency);
		panel.add(carrierFrequency);

		// Distance
		label = new JLabel("Distance: ", JLabel.TRAILING);
		panel.add(label);
		distance = new JTextField(10);
		distance.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				result.setText(predict());
			}
		});
		label.setLabelFor(distance);
		panel.add(distance);

		// Tx ant. height
		label = new JLabel("Tx ant. height: ", JLabel.TRAILING);
		panel.add(label);
		txAntHeight = new JTextField(10);
		txAntHeight.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				result.setText(predict());
			}
		});
		label.setLabelFor(txAntHeight);
		panel.add(txAntHeight);

		// Rx ant. height
		label = new JLabel("Rx ant. height: ", JLabel.TRAILING);
		panel.add(label);
		rxAntHeight = new JTextField(10);
		rxAntHeight.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				result.setText(predict());
			}
		});
		label.setLabelFor(rxAntHeight);
		panel.add(rxAntHeight);

		// Environment
		label = new JLabel("Environment: ", JLabel.TRAILING);
		panel.add(label);
		environment = new JComboBox<String>(new String[] { "Rural", "Suburban", "Medium-small city", "Big city" });
		environment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				result.setText(predict());
			}
		});
		label.setLabelFor(environment);
		panel.add(environment);

		// Result
		label = new JLabel("Attenuation: ", JLabel.TRAILING);
		panel.add(label);
		result = new JLabel(predict());
		label.setLabelFor(result);
		panel.add(result);

		SpringUtilities.makeCompactGrid(panel, 6, 2, 6, 6, 6, 6);
		this.add(panel);
	}

	private String predict() {
		if (carrierFrequency.getText().isEmpty() || distance.getText().isEmpty() || txAntHeight.getText().isEmpty()
				|| rxAntHeight.getText().isEmpty()) {
			return "";
		}

		double f = Double.parseDouble(carrierFrequency.getText());
		double d = Double.parseDouble(distance.getText());
		double hb = Double.parseDouble(txAntHeight.getText());
		double hm = Double.parseDouble(rxAntHeight.getText());

		String area = (String) environment.getSelectedItem();

		String result = OkumuraHataController.predict(f, d, hb, hm, area).replace(',', '.');

		return (Double.parseDouble(result) == 0) ? "Out of range" : result;
	}
}
