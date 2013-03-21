/*
 * Created by JFormDesigner on Mon Nov 05 20:11:29 GMT+02:00 2012
 */

package megascripts.graphic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import megascripts.aiofletcher.MegaFletcher;
import megascripts.aiofletcher.Variable;

/**
 * @author Mega Team
 */
public class Fletch_GUI extends JFrame {
	public Fletch_GUI() {
		initComponents();
	}

	private void button1ActionPerformed() {
		String Chosen = comboBox1.getSelectedItem().toString();
		if(Chosen.equals("Craft Logs Into LongBow (u) Start With Logs")){
			LogHandler.Print("You Have Choose Mode: LogsToBow"); 
			Variable.MODE_LOGTOBOW = true;
			Variable.OPTION_LONGBOW = true;
		}else if(Chosen.equals("Craft Logs Into ShortBow (u) Start With Logs")){
			LogHandler.Print("You Have Choose Mode: LogsToBow"); 
			Variable.MODE_LOGTOBOW = true;
			Variable.OPTION_LONGBOW = false;
		}else if(Chosen.equals("Craft Logs Into Shafts Just Start...")){
			LogHandler.Print("You Have Choose Mode: LogsToShafts"); 
			Variable.MODE_LOGTOSHAFT= true;
		}else if(Chosen.equals("Combine Bow(U) With Bow String start with 14 each item")){
			LogHandler.Print("You Have Choose Mode: BowString"); 
			Variable.MODE_BOWSTRING = true;
		}
		LogHandler.Print("-----------> Setting Setup Done <-----------"); 
		MegaFletcher.Setup();
		setVisible(false);
		dispose();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Mega Team
		label1 = new JLabel();
		comboBox1 = new JComboBox<>();
		button1 = new JButton();

		//======== this ========
		setTitle("Choose Mode - Fletching");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- label1 ----
		label1.setText("Mode Type:");
		contentPane.add(label1);
		label1.setBounds(10, 10, 100, 20);

		//---- comboBox1 ----
		comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
			"Craft Logs Into LongBow (u) Start With Logs",
			"Craft Logs Into ShortBow (u) Start With Logs",
			"Craft Logs Into Shafts Just Start...",
			"Combine Bow(U) With Bow String start with 14 each item"
		}));
		contentPane.add(comboBox1);
		comboBox1.setBounds(110, 10, 250, comboBox1.getPreferredSize().height);

		//---- button1 ----
		button1.setText("Start");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button1ActionPerformed();
			}
		});
		contentPane.add(button1);
		button1.setBounds(115, 45, 115, button1.getPreferredSize().height);

		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for(int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Mega Team
	private JLabel label1;
	private JComboBox<String> comboBox1;
	private JButton button1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
