/*
 * Created by JFormDesigner on Sun Nov 11 19:20:16 GMT+02:00 2012
 */

package megascripts.graphic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import megascripts.aiofishing.MegaFisher;
import megascripts.aiofishing.Variable;

/**
 * @author Mega Team
 */
public class FishingGUI extends JFrame {
	public FishingGUI() {
		initComponents();
	}

	private void button1ActionPerformed() {
         Variable.BANK_MODE = !checkBox1.isSelected();
         Variable.POWERFISHING = checkBox1.isSelected();
         String Chosen = comboBox2.getSelectedItem().toString();
         String Type = comboBox1.getSelectedItem().toString();
		if (Type.equals("Lure")) {
			Variable.FISH_IDS = Variable.FLYFISH_SPOT;
			Variable.Action = "Lure";
		}else if (Type.equals("Bait")) {
			Variable.FISH_IDS = Variable.BAITSPOT;
			Variable.Action = "Bait";
		}else if (Type.equals("Cage")) {
			Variable.FISH_IDS = Variable.CAGESPOT;
			Variable.Action = "Cage";
		}else if (Type.equals("Harpoon")) {
			Variable.FISH_IDS = Variable.HARPOONSPOT;
			Variable.Action = "Harpoon";
		}else if (Type.equals("Small Net")) {
			Variable.FISH_IDS = Variable.NETSPOT;
			Variable.Action = "Net";
		}
		if (Variable.BANK_MODE) {
			if (Chosen.equals("EdgeVillage")) {
				Variable.BANKAREA = Variable.BANKAREA_EDGEVILLAGE;
				Variable.FISHAREA = Variable.FISHAREA_EDGEVILLAGE;
				Variable.PATHTOBANK = Variable.EDGEVILLAGE_FISHTOBANK;
				Variable.PATHTOFISH = Variable.EDGEVILLAGE_BANKTOFISH;
			}else if (Chosen.equals("Catherby")) {
				Variable.BANKAREA = Variable.CATH_BANKAREA;
				Variable.FISHAREA = Variable.CATH_FISHAREA;
				Variable.PATHTOBANK = Variable.CATH_PATHTOBANK;
				Variable.PATHTOFISH = Variable.CATH_PATHTOFISH;
			}else if(Chosen.equals("Fishing Guild")){
				Variable.BANKAREA = Variable.FISHING_GUILD_BANKAREA;
				Variable.FISHAREA = Variable.FISHING_GUILD_FISHAREA;
				Variable.PATHTOBANK = Variable.FISHINg_GUILD_PATHTOBANK;
				Variable.PATHTOFISH = Variable.FISHINg_GUILD_PATHTOFISH;
			}else if(Chosen.equals("Karamja")){
				Variable.BANKAREA = Variable.KARMJA_BANKAREA;
				Variable.FISHAREA = Variable.KARMJA_FISHAREA;
				Variable.PATHTOBANK = Variable.KARMJA_PATHTOBANK;
				Variable.PATHTOFISH = Variable.KARMJA_PATHTOFISH;
				Variable.KARAMJA_BANK_MODE = true;
			}
		}
	
		 if (Chosen.equals("Barbarian Outpost") || Type.equals("Barbarian")) {
				Variable.FISH_IDS = Variable.BARBSPOT;
				Variable.Action = "Use-rod";
				Variable.POWERFISHING = true;
				Variable.BANK_MODE = false;
			}
		LogHandler.Print("You Have Chosen: " + Chosen + " Location");
		LogHandler.Print("You Have Chosen: " + Type +  " Fishing Type");
		LogHandler.Print("-----------> Setting Setup Done <-----------"); 
		MegaFisher.Setup();
		setVisible(false);
		dispose();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Mega Team
		comboBox1 = new JComboBox<>();
		label1 = new JLabel();
		comboBox2 = new JComboBox<>();
		label2 = new JLabel();
		checkBox1 = new JCheckBox();
		button1 = new JButton();

		//======== this ========
		setTitle("Mega Fishing");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- comboBox1 ----
		comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
			"Lure",
			"Bait",
			"Harpoon",
			"Cage",
			"Small Net",
			"Big Net",
			"Barbarian"
		}));
		contentPane.add(comboBox1);
		comboBox1.setBounds(85, 15, 235, comboBox1.getPreferredSize().height);

		//---- label1 ----
		label1.setText("Fish Type:");
		contentPane.add(label1);
		label1.setBounds(15, 15, 75, 19);

		//---- comboBox2 ----
		comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
			"Karamja",
			"EdgeVillage",
			"Catherby",
			"Barbarian Outpost",
			"Fishing Guild"
		}));
		contentPane.add(comboBox2);
		comboBox2.setBounds(85, 45, 235, 20);

		//---- label2 ----
		label2.setText("Fish Type:");
		contentPane.add(label2);
		label2.setBounds(15, 45, 75, 19);

		//---- checkBox1 ----
		checkBox1.setText("Activate PowerFishing");
		contentPane.add(checkBox1);
		checkBox1.setBounds(10, 75, 160, checkBox1.getPreferredSize().height);

		//---- button1 ----
		button1.setText("Start");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button1ActionPerformed();
			}
		});
		contentPane.add(button1);
		button1.setBounds(new Rectangle(new Point(325, 25), button1.getPreferredSize()));

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
	private JComboBox<String> comboBox1;
	private JLabel label1;
	private JComboBox<String> comboBox2;
	private JLabel label2;
	private JCheckBox checkBox1;
	private JButton button1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
