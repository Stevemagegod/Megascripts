/*
 * Created by JFormDesigner on Mon Nov 05 17:23:47 FET 2012
 */

package megascripts.graphic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import megascripts.aiofiremaking.MegaFiremaking;
import megascripts.aiofiremaking.Variable;

/**
 * @author Mega Team
 */
public class FM_GUI extends JFrame {
	public FM_GUI() {
		initComponents();
	}

	private void button1ActionPerformed() {
		String Location = comboBox1.getSelectedItem().toString();
		
		if(Location.equals("Edgevillage")){
		Variable.BANKAREA = Variable.EDGE_BANKAREA;
		Variable.FMAREA = Variable.EDGE_FMAREA;
		Variable.PathToFM = Variable.EDGE_PATHTOFM;
		Variable.PathToBank = Variable.EDGE_PATHTOBANK;
		} else if (Location.equals("Falador EastBank")) {
			Variable.BANKAREA = Variable.FALAEast_BANKAREA;
			Variable.FMAREA = Variable.FALAEast_FMAREA;
			Variable.PathToFM = Variable.FALAEast_PATHTOFM;
			Variable.PathToBank = Variable.FALAEast_PATHTOBANK;
		}else if (Location.equals("Falador WestBank")) {
			Variable.BANKAREA = Variable.FALAWest_BANKAREA;
			Variable.FMAREA = Variable.FALAWest_FMAREA;
			Variable.PathToFM = Variable.FALAWest_PATHTOFM;
			Variable.PathToBank = Variable.FALAWest_PATHTOBANK;
		}else if (Location.equals("Camelot")) {
			Variable.BANKAREA = Variable.Camelot_BANKAREA;
			Variable.FMAREA = Variable.Camelot_FMAREA;
			Variable.PathToFM = Variable.Camelot_PATHTOFM;
			Variable.PathToBank = Variable.Camelot_PATHTOBANK;
		}
		String Log = comboBox2.getSelectedItem().toString();
		 if (Log.equals("Oak")) {
			 Variable.LOG_ID = 1521;
		}else if (Log.equals("Willow")) {
			 Variable.LOG_ID = 1519;
		}else if (Log.equals("Normal Log")) {
			 Variable.LOG_ID = 1511;
		}else if (Log.equals("Maple")) {
			 Variable.LOG_ID = 1517;
		}else if (Log.equals("Yew")) {
			 Variable.LOG_ID = 1515;
		}else if (Log.equals("Magic")) {
			 Variable.LOG_ID = 1513;
		}
			LogHandler.Print("You Have Chosen: " + Location + " Location");
			LogHandler.Print("You Have Chosen: " + Log +  " As Log Type");
			LogHandler.Print("-----------> Setting Setup Done <-----------"); 
		MegaFiremaking.Setup();
		setVisible(false);
		dispose();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Mega Team
		label1 = new JLabel();
		comboBox1 = new JComboBox<>();
		label2 = new JLabel();
		comboBox2 = new JComboBox<>();
		button1 = new JButton();

		//======== this ========
		setTitle("Choose - Mega FireMaking");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- label1 ----
		label1.setText("BonFire Place:");
		contentPane.add(label1);
		label1.setBounds(5, 15, 110, 20);

		//---- comboBox1 ----
		comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
			"Edgevillage",
			"Falador EastBank",
			"Falador WestBank",
			"Camelot"
		}));
		contentPane.add(comboBox1);
		comboBox1.setBounds(115, 15, 210, 20);

		//---- label2 ----
		label2.setText("Log Type");
		contentPane.add(label2);
		label2.setBounds(5, 40, 105, 20);

		//---- comboBox2 ----
		comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
			"Normal Log",
			"Oak",
			"Willow",
			"Maple",
			"Yew",
			"Magic"
		}));
		contentPane.add(comboBox2);
		comboBox2.setBounds(115, 40, 210, comboBox2.getPreferredSize().height);

		//---- button1 ----
		button1.setText("Start");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button1ActionPerformed();
			}
		});
		contentPane.add(button1);
		button1.setBounds(new Rectangle(new Point(330, 25), button1.getPreferredSize()));

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
	private JLabel label2;
	private JComboBox<String> comboBox2;
	private JButton button1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
