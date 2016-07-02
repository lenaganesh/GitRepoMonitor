import java.awt.TrayIcon;

public class MessageNotifier implements Runnable {
	TrayIcon trayIcon;

	public MessageNotifier(TrayIcon trayIcon) {
		this.trayIcon = trayIcon;
	}

	@Override
	public void run() {
		while (true) {
			try {
				GitFetchExecuter executer=new GitFetchExecuter(this.trayIcon);
				executer.executeCommand("");
				Thread.sleep(30000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//trayIcon.displayMessage("GIT Repository Changed", "Repository Changed. Update your workspace", TrayIcon.MessageType.INFO);
		}

	}
}