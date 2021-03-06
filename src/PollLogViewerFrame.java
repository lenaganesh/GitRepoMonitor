import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class PollLogViewerFrame extends JInternalFrame {

	PollLogViewerFrame() {
		super("Poll Log Viewer", true, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable

		System.out.println("creating instance");
		try {
			System.out.println("setting look and feel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Unable to set LookAndFeel");
		}
		initializeComponents();
	}

	public void initializeComponents() {
		setVisible(true);
		setSize(500, 500);
		JPanel panel = new JPanel();
		BorderLayout borderLayout = new BorderLayout();
		panel.setLayout(borderLayout);
		panel.add(new JLabel("Poll Logs"), BorderLayout.NORTH);
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		panel.add(scrollPane, BorderLayout.CENTER);

		setContentPane(panel);
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				
				while (true) {
					StringBuffer buffer=new StringBuffer();
					try (BufferedReader br = new BufferedReader(new FileReader("PollLog.txt"))) {
						for (String line; (line = br.readLine()) != null;) {
							// process the line.
							buffer.append(line + "\n");
						
						}
						// line is not visible here.
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					textArea.setText(buffer.toString() );
					try {
						Thread.sleep(30000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} // While

			}
		});
		thread.start();
	}
}
