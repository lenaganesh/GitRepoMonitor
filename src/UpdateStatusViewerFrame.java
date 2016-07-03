import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class UpdateStatusViewerFrame extends JInternalFrame {
	DefaultListModel model = new DefaultListModel();

	UpdateStatusViewerFrame() {
		super("Update Status Viewer", true, // resizable
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
		panel.add(new JLabel("Update Status Logs"), BorderLayout.NORTH);
		// JTextArea textArea = new JTextArea();
		// textArea.setEditable(false);
		// JScrollPane scrollPane = new JScrollPane(textArea);

		JList list = new JList(model);
		JScrollPane pane = new JScrollPane(list);
		panel.add(pane, BorderLayout.CENTER);
		setContentPane(panel);
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				ArrayList<String> listData = new ArrayList<String>();
				;
				while (true) {

					listData = new ArrayList<String>();
					;

					StringBuffer buffer = new StringBuffer();
					try (BufferedReader br = new BufferedReader(new FileReader("PollLog.txt"))) {
						for (String line; (line = br.readLine()) != null;) {
							// process the line.
							if (line.startsWith("Changes Found:")) {
								buffer.append(line + "\n");
								listData.add(line);

							}

						}
						// line is not visible here.
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					model.removeAllElements();
					for (String string : listData) {
						model.insertElementAt( string,0);
						//model.addElement(string);
					}

					// textArea.setText(buffer.toString() );
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
