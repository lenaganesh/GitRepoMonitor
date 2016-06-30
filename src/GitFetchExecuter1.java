import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GitFetchExecuter1 {

	public void executeCommand(String gitPath) {
		try {
			System.out.println(HideToSystemTray.gitHome);
			
			//String[] commands={"cmd "," cd " + "C:/PROJECT/TEMP/","dir"};
			//for (String string : commands) {
				
			//Process p = Runtime.getRuntime().exec("cmd /C dir ",null,new File("C:/PROJECT/TEMP/GitRepoMonitor"));
			Process p = Runtime.getRuntime().exec("cmd /C C:/Users/IBM_ADMIN/AppData/Local/GitHub/PortableGit_d76a6a98c9315931ec4927243517bc09e9b731a0/cmd/git.exe fetch",null,new File("C:/PROJECT/TEMP/GitRepoMonitor/"));
			//Process p = Runtime.getRuntime().exec(new String[]{"cmd /c C:/Users/IBM_ADMIN/AppData/Local/GitHub/PortableGit_d76a6a98c9315931ec4927243517bc09e9b731a0/cmd/git.exe "},null,new File("C:/PROJECT/TEMP/GitRepoMonitor/"));//new String[] {"cmd ","/K", " cd " + "C:/PROJECT/TEMP/", "cmd  dir" });
			InputStream br = p.getInputStream();
			//BufferedReader br = new BufferedReader(new InputStreamReader(is));
			File file = new File("");
			String curDir = file.getAbsolutePath().toString();
			System.out.println("FFF:"+curDir);
			String input;
			StringBuffer buffer = new StringBuffer();
			int n;
			while ((n = br.read()) != -1) {

				char input1 = (char) n;
				//System.out.println(n + "\t" + input1);
				//System.out.println(input1);
				buffer.append(input1);
				//System.out.println(buffer);
				if (buffer.toString().toLowerCase().contains(curDir.toLowerCase())) {
					break;
				}
				if (buffer.toString().toLowerCase().contains("password")) {
					System.out.println(HideToSystemTray.password.getPassword() + "XXX");
					if (HideToSystemTray.password.getPassword() == null
							|| HideToSystemTray.password.getPassword().length == 0) {
						JPanel panel = new JPanel(new BorderLayout());
						JLabel lab = new JLabel("Password to GIT Repository:");
						panel.add(lab, BorderLayout.NORTH);

						panel.add(HideToSystemTray.password, BorderLayout.SOUTH);
						JOptionPane.showMessageDialog(null, panel);
					}
					byte[] passwd = (new String(HideToSystemTray.password.getPassword() + "\r\n")).getBytes();
					p.getOutputStream().write(passwd);
					p.getOutputStream().flush();
					p.getOutputStream().close();
				}
			}
			// String text = new String(buff, 0, n);
			// System.out.println(text);

			System.out.println("After Read:");
			System.out.println("OP:"+buffer);
			//}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
