import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.FetchResult;
import org.eclipse.jgit.transport.TrackingRefUpdate;

public class GitFetchExecuter {

	public void executeCommand(String gitPath) {
		try {
			
			System.out.println(HideToSystemTray.gitHome);
			File gitWorkDir = new File("C:/PROJECT/TEMP/GitRepoMonitor");
			Git git = null;
			
			git = Git.open(gitWorkDir);
			
			org.eclipse.jgit.api.FetchCommand fetchCommand = git.fetch();
			
			fetchCommand.setDryRun(true);
			FetchResult fetchRes = fetchCommand.call();
			System.out.println(fetchRes.getMessages());
			Collection<TrackingRefUpdate> collection = fetchRes.getTrackingRefUpdates();
			for (TrackingRefUpdate trackingRefUpdate : collection) {
				System.out.println("Tracking Update:"+trackingRefUpdate);
				System.out.println(trackingRefUpdate.getRemoteName());
			}

			System.out.println("After Read:");
			

			// }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
