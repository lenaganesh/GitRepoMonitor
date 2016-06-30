import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class SettingsFrame extends JInternalFrame {
	
	SettingsFrame() {
		super("Settings",
		          true, //resizable
		          true, //closable
		          true, //maximizable
		          true);//iconifiable
		
		System.out.println("creating instance");
		try {
			System.out.println("setting look and feel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Unable to set LookAndFeel");
		}
		initializeComponents();
	}

	
	public void initializeComponents(){
		setVisible(true);
		setSize(300, 200);
		
		GridBagLayout GridBagLayout=new GridBagLayout();
		
		GridBagConstraints c = new GridBagConstraints();
		JPanel panel =new JPanel();
		panel.setLayout(GridBagLayout);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		
		panel.add(new JLabel("GIT Executable Path"),c);
		c.gridx = 1;
		c.gridy = 0;
		JTextField gitPath=new JTextField();
		panel.add(gitPath,c);
		c.gridx = 2;
		c.gridy = 0;
		JButton choose1=new JButton("SELECT");
		panel.add(choose1,c);
		gitPath.setText(HideToSystemTray.domain.getGitPathDir());
		choose1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showDialog(SettingsFrame.this, "Attach");
				gitPath.setText(fc.getSelectedFile().toString());
				HideToSystemTray.domain.setGitPathDir(fc.getSelectedFile().toString());
			}
		});
		
		
		c.gridx = 0;
		c.gridy = 1;
		
		
		panel.add(new JLabel("GIT Repository Home (To Monitor)"),c);
		JTextField gitRepoPath=new JTextField("",20);
		c.gridx = 1;
		c.gridy = 1;
		panel.add(gitRepoPath,c);
		JButton choose2=new JButton("SELECT");
		c.gridx = 2;
		c.gridy = 1;
		panel.add(choose2,c);
		gitRepoPath.setText(HideToSystemTray.domain.getGitRepoDir());
		choose2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showDialog(SettingsFrame.this, "Attach");
				gitRepoPath.setText(fc.getSelectedFile().toString());
				HideToSystemTray.domain.setGitRepoDir(fc.getSelectedFile().toString());
			}
		});
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		
		panel.add(new JLabel("Refresh Interval Pattern"),c);
		c.gridx = 1;
		c.gridy = 2;
		JTextField refreshInterval=new JTextField();
		panel.add(refreshInterval,c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;
		
		
		JButton save=new JButton("SAVE");
		panel.add(save,c);
		JPanel panel1 =new JPanel(new GridLayout());
		panel1.add(panel);
		setContentPane(panel1);
	}
}

