/*
 * Created by JFormDesigner on Mon Nov 05 16:06:46 GMT+02:00 2012
 */

package megascripts.graphic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import megascripts.aioherblore.MegaHerblore;
import megascripts.aioherblore.Variable;

/**
 * @author Mega Team
 */
public class Herblore_GUI extends JFrame {
	public Herblore_GUI() {
		initComponents();
	}

	private void button1ActionPerformed() {
		String Chosen = comboBox1.getSelectedItem().toString();
		if(Chosen.equals("Make Potion - Start with 14 each item")){
			LogHandler.Print("You Have Choose Mode: Potion Making"); 
			Variable.MODE_UNFPOTION = true;
		}else if(Chosen.equals("Make (Unf) Potion - Start With 14 each item")){
			LogHandler.Print("You Have Choose Mode: UnfMaking"); 
			Variable.MODE_UNFPOTION = true;
		}else if(Chosen.equals("Clean Herblore - Start with 28 Herb")){
			LogHandler.Print("You Have Choose Mode: Cleaning Herbs"); 
			Variable.MODE_CLEANHERBS= true;
		}else if(Chosen.equals("Make Potion - Extreme Range Just Start...")){
			LogHandler.Print("You Have Choose Mode: Range Extreme"); 
			Variable.MODE_RANGEEXT = true;
		}else if(Chosen.equals("Make Potion - OverLoad Just Start...")){
			LogHandler.Print("You Have Choose Mode: OverLoad"); 
			Variable.MODE_OVERLOAD = true;
		}
		LogHandler.Print("-----------> Setting Setup Done <-----------"); 
		MegaHerblore.Setup();
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
		setTitle("Choose Mode - Herblore");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- label1 ----
		label1.setText("Mode Type:");
		contentPane.add(label1);
		label1.setBounds(10, 10, 100, 20);

		//---- comboBox1 ----
		comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
			"Make Potion - Start with 14 each item",
			"Make (Unf) Potion - Start With 14 each item",
			"Make Potion - Extreme Range Just Start...",
			"Make Potion - OverLoad Just Start...",
			"Clean Herblore - Start with 28 Herb"
		}));
		contentPane.add(comboBox1);
		comboBox1.setBounds(110, 10, 250, 20);

		//---- button1 ----
		button1.setText("Start");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button1ActionPerformed();
			}
		});
		contentPane.add(button1);
		button1.setBounds(120, 40, 115, 23);

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
