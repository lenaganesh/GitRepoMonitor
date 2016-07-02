import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.UIManager;


public class HideToSystemTray extends JFrame {
	TrayIcon trayIcon;
	SystemTray tray;
	JDesktopPane desktop;
	public static JPasswordField password = new JPasswordField();
	public static Domain domain=new Domain();
	public static String gitHome;
	HideToSystemTray() {
		super("SystemTray test");
		desktop= new JDesktopPane();
		System.out.println("creating instance");
		try {
			System.out.println("setting look and feel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Unable to set LookAndFeel");
		}
		if (SystemTray.isSupported()) {
			System.out.println("system tray supported");
			tray = SystemTray.getSystemTray();

			Image image = Toolkit.getDefaultToolkit().getImage("12.png");
			ActionListener exitListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Exiting....");
					System.exit(0);
				}
			};
			PopupMenu popup = new PopupMenu();
			MenuItem defaultItem = new MenuItem("Exit");
			defaultItem.addActionListener(exitListener);
			popup.add(defaultItem);
			defaultItem = new MenuItem("Open");
			defaultItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(true);
					setExtendedState(JFrame.NORMAL);
				}
			});
			MenuItem startMonitorItem = new MenuItem("Start Monitor");
			MenuItem stopMonitorItem = new MenuItem("Stop Monitor");
			popup.add(defaultItem);
			popup.add(startMonitorItem);
			popup.add(stopMonitorItem);
			trayIcon = new TrayIcon(image, "SystemTray Demo", popup);
			trayIcon.setImageAutoSize(true);
			Thread t = new Thread(new MessageNotifier(trayIcon));
			t.start();
		} else {
			System.out.println("system tray not supported");
		}
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent e) {
				if (e.getNewState() == ICONIFIED) {
					try {
						tray.add(trayIcon);
						setVisible(false);
						System.out.println("added to SystemTray");
					} catch (AWTException ex) {
						System.out.println("unable to add to tray");
					}
				}
				if (e.getNewState() == 7) {
					try {
						tray.add(trayIcon);
						setVisible(false);
						System.out.println("added to SystemTray");
					} catch (AWTException ex) {
						System.out.println("unable to add to system tray");
					}
				}
				if (e.getNewState() == MAXIMIZED_BOTH) {
					tray.remove(trayIcon);
					setVisible(true);
					System.out.println("Tray icon removed");
				}
				if (e.getNewState() == NORMAL) {
					tray.remove(trayIcon);
					setVisible(true);
					System.out.println("Tray icon removed");
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage("Duke256.png"));

		setVisible(true);
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(desktop);
	  
	    //Make dragging a little faster but perhaps uglier.
	    desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	    initializeComponents();
	}

	public static void main(String[] args) {
		new HideToSystemTray();
	}
	public void initializeComponents(){
		SettingsFrame settingsFrame=new SettingsFrame();
		desktop.add(settingsFrame);
		PollLogViewerFrame PollLogViewerFrame=new PollLogViewerFrame();
		desktop.add(PollLogViewerFrame);
		UpdateStatusViewerFrame UpdateStatusViewerFrame=new UpdateStatusViewerFrame();
		desktop.add(UpdateStatusViewerFrame);
		try {
			//settingsFrame.setSelected(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

