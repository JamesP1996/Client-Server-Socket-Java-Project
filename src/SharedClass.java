
import java.util.ArrayList;


public class SharedClass {

	private static ArrayList<Club> clubList = new ArrayList<>();
	private static ArrayList<Agent> agentList = new ArrayList<>();
	private static ArrayList<Player> playerList = new ArrayList<>();
	
	
	//Print Clubs
	public void printClubs()
	{
		for (Club club : clubList) {
			System.out.println(club.getName());
		}
		
	}
	
	//Print Agents
	public void printAgents()
	{
		for (Agent agent : agentList) {
			System.out.println(agent.getAgentID()+""+agent.getAgentName());
		}
			
	}
	
	//Print Players
		public void printPlayers()
		{
			for (Player player : playerList) {
				System.out.println(player.getPlayerID() + " " + player.getName());
			}
				
		}
	
	//Add a New Club to the Shared Club List
	public void addClub(Club c) {
	     clubList.add(c);
		
	}
	
	//Add a New Agent to the Shared Agent List
	public void addAgent(Agent a) {
		agentList.add(a);
	}
	
	
	
	//Add a New Player to the Shared Player List
	public void addPlayer(Player p) {
		playerList.add(p);
	}
	
	//Update a Players Value
	public void updateValuation(int playerID,float valuation,int AgentID) {
		for (int i = 0; i < playerList.size(); i++) {
			if (playerList.get(i).getPlayerID() == playerID) {
				playerList.get(i).setValuation(valuation);
				break;
			}
			
		}
	}
	
	//Update a Players Status
	public void updateStatus(int playerID,String status) {
		for (int i = 0; i < playerList.size(); i++) {
			if (playerList.get(i).getPlayerID() == playerID) {
				playerList.get(i).setStatus(status);
				break;
			}
			
		}
	}
	
	public String[] searchPlayersPos(String position) {
		ArrayList<String> tempList = new ArrayList<>();
		
		for (Player player : playerList) {
			if(player.getPosition().equalsIgnoreCase(position)) {
				tempList.add("ID: " + player.getPlayerID() + " Name : " + player.getName() + " ClubID : " + player.getClubID() + "\n");
			}
		}
		String[] temp = new String[tempList.size()];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = tempList.get(i).toString();
		}
		
		
		return temp;
		
		
		
	}
	
	public String[] searchForPlayersOnSale(int ClubID) {
		ArrayList<String> tempList = new ArrayList<>();
		for (Player player : playerList) {
			if(player.getStatus().equals("For Sale") && player.getClubID() == ClubID) {
				tempList.add("ID: " + player.getPlayerID() + " Name : " + player.getName() + " ClubID : " + player.getClubID() + "\n");
			}
		}
		String[] temp = new String[tempList.size()];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = tempList.get(i).toString();
		}
		return temp;
	}
	
	public void changePlayerStatus(int playerID,String Status,int AgentID) {

		for (int i = 0; i < playerList.size(); i++) {
			if(playerList.get(i).getPlayerID() == playerID && AgentID == playerList.get(i).getAgentID()) {
				playerList.get(i).setStatus(Status);
		}	
	}
		
}
	
	public void changePlayerValuation(int playerID,float valuation,int AgentID) {

		for (int i = 0; i < playerList.size(); i++) {
			if(playerList.get(i).getPlayerID() == playerID && AgentID == playerList.get(i).getAgentID()) {
				playerList.get(i).setValuation(valuation);
		}	
	}
		
}
	
	public void changeClubPlayerSaleStatus(int playerID,int ClubID,int status) {
		for (Player player : playerList) {
			//Suspend
			if(player.getPlayerID() == playerID && player.getClubID() == ClubID && status == 1) {
				player.setStatus("Suspended");
			}
			//Resume
			else if(player.getPlayerID() == playerID && player.getClubID() == ClubID && status == 2) {
				player.setStatus("For Sale");
			}
		}
	}
	
	public boolean validateClubLogin(int clubID,String name) {
		
		int loginValidated = 0;

		for (Club club: clubList) {
			if (club.getName().equals(name) && club.getClubID() == clubID) {
				loginValidated = 1;
			}
		}
		if(loginValidated == 1) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
	public boolean validateAgentLogin(int agentID,String agentName) {
		
		int loginValidated = 0;

		for (Agent agent: agentList) {
			if (agent.getAgentName().equals(agentName) && agent.getAgentID() == agentID) {
				loginValidated = 1;
			}
		}
		if(loginValidated == 1) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
	public boolean validateClubIDExists(int clubID) {
		int loginValidated = 0;

		for (Club club: clubList) {
			if (club.getClubID() == clubID) {
				loginValidated = 1;
			}
		}
		if(loginValidated == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	@SuppressWarnings("unused")
	public int createPlayerID() {
		
		int playerID =1;
		for (Player player : playerList) {
			playerID++;
		}
		return playerID;
		
	}
	
	public String purchasePlayer(int PlayerID,int ClubID) {
		
		
		Club myClub = new Club();
		Player p = new Player();
		Club xClub = new Club();
		
		for (Player player : playerList) {
			if(player.getPlayerID() == PlayerID) {
				p = player;
			}
		}
		
		for (Club club : clubList) {
			if(club.getClubID() == ClubID) {
				myClub = club;
			}
			if(club.getClubID() == p.getClubID()) {
				xClub = club;
			}
		}

		
		if(p.getStatus().equalsIgnoreCase("For Sale") && myClub.getFundsAvailable() > p.getValuation()) {
			p.setClubID(ClubID);
			p.setStatus("Sold");
			xClub.setFundsAvailable(xClub.getFundsAvailable() + p.getValuation());
			myClub.setFundsAvailable(myClub.getFundsAvailable() - p.getValuation());
			return "Player Purchased";
		}
		else {
		return "Player Unable to be Purchased";
		
		}
	}
	

	
	
	
	
}
