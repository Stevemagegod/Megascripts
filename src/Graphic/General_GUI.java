package megascripts.graphic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.powerbot.game.api.methods.Environment;
import org.powerbot.game.api.methods.input.Mouse.Speed;
/*
 * Created by JFormDesigner on Mon Nov 05 18:03:54 GMT+02:00 2012
 */

import megascripts.Variable;
import megascripts.aiofiremaking.MegaFiremaking;
import megascripts.aiofishing.MegaFisher;
import megascripts.aiofletcher.MegaFletcher;
import megascripts.aiofletcher.node.LogsToBows;
import megascripts.aioherblore.MegaHerblore;
import megascripts.dungoneering.MegaDungeon;

/**
 * @author Mega Team
 */
public class General_GUI extends JFrame {
	public General_GUI() {
		initComponents();
	}

	private void button1ActionPerformed() {
		String MouseSpeedString = comboBox2.getSelectedItem().toString();
		if (MouseSpeedString.equals("Very Fast")) {
			Variable.MouseSpeed = Speed.VERY_FAST;
		} else if (MouseSpeedString.equals("Fast")) {
			Variable.MouseSpeed = Speed.FAST;
		} else if (MouseSpeedString.equals("Normal")) {
			Variable.MouseSpeed = Speed.NORMAL;
		}	
		String skillType = comboBox1.getSelectedItem().toString();
		LogHandler.Print("Welcome To Mega Scripts V0.1 , " + Environment.getDisplayName() ,Color.green);
		LogHandler.Print("-----------> Setting <-----------");
		LogHandler.Print("You Have choose " + MouseSpeedString + " Mouse speed");
		LogHandler.Print("You Have choose " + skillType + " Skill");
		LogHandler.Print("-----------> Loading Next Gui <-----------");
		Variable.MEGA_FLETCHER = skillType.equals("Fletching");
		if (Variable.MEGA_FLETCHER) {
			Fletch_GUI g = new Fletch_GUI();
			g.setVisible(true);
		}
		Variable.MEGA_HERBLORE =  skillType.equals("Herblore");
        if(Variable.MEGA_HERBLORE){
        	Herblore_GUI g = new Herblore_GUI();
        	g.setVisible(true);
        }
    	Variable.MEGA_FISHER =  skillType.equals("Fishing");
        if(Variable.MEGA_FISHER){
        	FishingGUI g = new FishingGUI();
        	g.setVisible(true);
        }
    	Variable.MEGA_FIREMAKING =  skillType.equals("FireMaking");
        if(Variable.MEGA_FIREMAKING){
        	FM_GUI g = new FM_GUI();
        	g.setVisible(true);
        }
    	Variable.MEGA_DUNGEON =  skillType.equals("Dungeoneering");
        if(Variable.MEGA_DUNGEON){
        	MegaDungeon.Setup();
        }
		setVisible(false);
		dispose();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Mega Team
		label1 = new JLabel();
		comboBox1 = new JComboBox<>();
		button1 = new JButton();
		label2 = new JLabel();
		comboBox2 = new JComboBox<>();

		//======== this ========
		setTitle("Choose Skill");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- label1 ----
		label1.setText("Script skill Type:");
		contentPane.add(label1);
		label1.setBounds(10, 10, 95, 20);

		//---- comboBox1 ----
		comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
			"Herblore",
			"Fletching",
			"Fishing",
			"FireMaking",
			"Dungeoneering"
		}));
		contentPane.add(comboBox1);
		comboBox1.setBounds(105, 10, 130, comboBox1.getPreferredSize().height);

		//---- button1 ----
		button1.setText("Next");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button1ActionPerformed();
			}
		});
		contentPane.add(button1);
		button1.setBounds(245, 20, 60, button1.getPreferredSize().height);

		//---- label2 ----
		label2.setText("Mouse Speed:");
		contentPane.add(label2);
		label2.setBounds(10, 40, 80, 20);

		//---- comboBox2 ----
		comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
			"Very Fast",
			"Fast",
			"Normal"
		}));
		contentPane.add(comboBox2);
		comboBox2.setBounds(105, 40, 130, comboBox2.getPreferredSize().height);

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
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Mega Team
	private JLabel label1;
	private JComboBox<String> comboBox1;
	private JButton button1;
	private JLabel label2;
	private JComboBox<String> comboBox2;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
