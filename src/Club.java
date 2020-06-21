
public class Club {

	private String Name;
	private int ClubID;
	private String Email;
	private float fundsAvailable;
	
	
	public Club(String name, int clubID, String email, float fundsAvailable) {
		Name = name;
		ClubID = clubID;
		Email = email;
		this.fundsAvailable = fundsAvailable;
	}
	
	public Club() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getClubID() {
		return ClubID;
	}
	public void setClubID(int clubID) {
		ClubID = clubID;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public float getFundsAvailable() {
		return fundsAvailable;
	}
	public void setFundsAvailable(float fundsAvailable) {
		this.fundsAvailable = fundsAvailable;
	}
	
	
}
