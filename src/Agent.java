
public class Agent {

	private String agentName;
	private int agentID;
	private String email;
	
	
	
	public Agent(String agentName, int agentID, String email) {
		this.agentName = agentName;
		this.agentID = agentID;
		this.email = email;
	}
	
	public Agent() {
		// TODO Auto-generated constructor stub
	}
	
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public int getAgentID() {
		return agentID;
	}
	public void setAgentID(int agentID) {
		this.agentID = agentID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
