import java.awt.TrayIcon;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.FetchResult;
import org.eclipse.jgit.transport.TrackingRefUpdate;

public class GitFetchExecuter {
	FileWriter fw;
	TrayIcon trayIcon;
	public GitFetchExecuter(TrayIcon trayIcon) {
		this.trayIcon = trayIcon;
		
	}
	public void executeCommand(String gitPath) {
		try {
			String filename = "PollLog.txt";
			fw = new FileWriter(filename, true); // the true will append the new
													// data

			System.out.println(HideToSystemTray.gitHome);
			File gitWorkDir = new File("C:/PROJECT/TEMP/GitRepoMonitor");
			Git git = null;
			fw.write("==========================\n");
			fw.write("Date:" + new Date() + "\n");
			fw.write("Checking Changes in :" + gitWorkDir.getAbsolutePath() + "\n");
			git = Git.open(gitWorkDir);

			org.eclipse.jgit.api.FetchCommand fetchCommand = git.fetch();
			
			// If TRUE==Next fetch time it will not notify for changess
			fetchCommand.setDryRun(false);// Otherwise it will Update... in
											// local..
			FetchResult fetchRes = fetchCommand.call();
			System.out.println(fetchRes.getMessages());
			Collection<TrackingRefUpdate> collection = fetchRes.getTrackingRefUpdates();
			if (collection != null && collection.size() > 0) {
				fw.write("Changes Found:" + new Date() + ".\n");
				trayIcon.displayMessage("GIT Repository Changed:"+gitWorkDir, "Repository Changed. Update your workspace", TrayIcon.MessageType.INFO);
			} else {
				fw.write("Changes NOT Found.\n");
			}
			for (TrackingRefUpdate trackingRefUpdate : collection) {
				System.out.println("Tracking Update:" + trackingRefUpdate);
				System.out.println(trackingRefUpdate.getRemoteName());
				fw.write("Changes Found :" + trackingRefUpdate + "\n");
				System.out.println(trackingRefUpdate.getNewObjectId().getName());
				System.out.println(trackingRefUpdate.getResult());
				System.out.println(trackingRefUpdate.getResult().getDeclaringClass());
			}

			System.out.println("Read Completed");
			fw.write("==========================\n");

			// }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fw != null) {
					fw.close();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
