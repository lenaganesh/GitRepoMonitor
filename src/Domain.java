import java.io.Serializable;

public class Domain implements Serializable {
	private String gitPathDir;
	private String gitRepoDir;
	private String password;
	
	public String getGitPathDir() {
		return gitPathDir;
	}
	public void setGitPathDir(String gitPathDir) {
		this.gitPathDir = gitPathDir;
	}
	public String getGitRepoDir() {
		return gitRepoDir;
	}
	public void setGitRepoDir(String gitRepoDir) {
		this.gitRepoDir = gitRepoDir;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
