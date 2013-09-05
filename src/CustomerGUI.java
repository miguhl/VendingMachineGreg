import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CustomerGUI extends JFrame {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new CustomerGUI().setVisible(true);
			}
		});

	}

	public CustomerGUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Vending Machines");

		primaryWindow pan = new primaryWindow();
		add(pan.panel);
		pack();
		setVisible(true);
	}
}



//Each window defined down here

class primaryWindow implements ActionListener {

	private JButton btn1 = new JButton("Purchase");
	private JButton btn2 = new JButton("Quit");
	private JTextField txt1 = new JTextField(2);
	private JTextField txt2 = new JTextField(4);
	private JLabel lbl1 = new JLabel("Dispenser: ");
	private JLabel lbl2 = new JLabel("Money: ");
	JPanel panel;

	public primaryWindow() {
		panel = new JPanel();
		panel.add(btn1);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(txt1.getText());
			}
		});
		panel.add(lbl1);
		panel.add(txt1);
		panel.add(lbl2);
		panel.add(txt2);
		panel.add(btn2);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NewFrame1();
				// System.exit(0);
			}
		});
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		//Because it makes the compiler happy
	}
}

class NewFrame1 extends JFrame implements ActionListener {
	// Initializes the frame and opens it
	public NewFrame1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Vending Machines");
		JButton open = new JButton("New Window");
		open.addActionListener(this);
		add(open);
		pack();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		// code for the new frame
	}
}