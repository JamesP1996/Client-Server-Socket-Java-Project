
public class Player {
	
	private String name;
	private int age;
	private int playerID;
	private int clubID;
	private int agentID;
	private float valuation;
	
	private String Status;
	private String Position;
	
	public Player(String name, int age, int playerID, int clubID, int agentID, float valuation, String status,
			String position) {
		super();
		this.name = name;
		this.age = age;
		this.playerID = playerID;
		this.clubID = clubID;
		this.agentID = agentID;
		this.valuation = valuation;
		Status = status;
		Position = position;
	}
	public Player() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public int getClubID() {
		return clubID;
	}
	public void setClubID(int clubID) {
		this.clubID = clubID;
	}
	public int getAgentID() {
		return agentID;
	}
	public void setAgentID(int agentID) {
		this.agentID = agentID;
	}
	public float getValuation() {
		return valuation;
	}
	public void setValuation(float valuation) {
		this.valuation = valuation;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	
	

}
