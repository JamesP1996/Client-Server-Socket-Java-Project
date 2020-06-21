import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import java.util.Arrays;
//test
public class Server{
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		ServerSocket listener;
		int clientid=0;
		try 
		{
			 listener = new ServerSocket(10000,10);
			 
			 while(true)
			 {
				System.out.println("Main thread listening for incoming new connections");
				Socket newconnection = listener.accept();
				
				System.out.println("New connection received and spanning a thread");
				Connecthandler t = new Connecthandler(newconnection, clientid);
				clientid++;
				t.start();
			 }
			
		} 
		
		catch (IOException e) 
		{
			System.out.println("Socket not opened");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


class Connecthandler extends Thread
{

	Socket individualconnection;
	int socketid;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	int result;
	
	public Connecthandler(Socket s, int i)
	{
		individualconnection = s;
		socketid = i;
	}
	
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("client>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	
	public void run()
	{
		
		try 
		{
			
			out = new ObjectOutputStream(individualconnection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(individualconnection.getInputStream());
			System.out.println("Connection"+ socketid+" from IP address "+individualconnection.getInetAddress());
			SharedClass sharedObject = new SharedClass();
		
		 do {
			
		
			//Commence
				do
				{
					sendMessage("Please Enter 1 to Login or 2 to Register");
					message = (String)in.readObject();
				}while(!message.equals("1")&&!message.equals("2")); 
					
					//If Client Chooses to Login //
				
					if(message.equals("1")) {
						sendMessage("Would you like to log in as\n1.Agent\n2.Club");
						message = (String)in.readObject();
						
						//Try Login as Agent
						if(message.equals("1")) {
							
							sendMessage("Enter Agent Name");
							String AName = (String)in.readObject();
							sendMessage("Enter Agent ID");
							int AID = Integer.parseInt((String)in.readObject());
							boolean validate = sharedObject.validateAgentLogin(AID, AName);
							
							if(validate == true ) {
								//Bring up Agent Menu
								sendMessage("Would you like to\n1.Add a Player\n2.Change a Players Valuation\n3.Change a Players Status");
								message = (String)in.readObject();
								
								//Add Player
								if(message.equals("1")) {
									Player p = new Player();
									
									sendMessage("Enter Player Name");
									message = (String)in.readObject();
									p.setName(message);
									
									sendMessage("Enter Player Age");
									message = (String)in.readObject();
									p.setAge(Integer.parseInt(message));
									
									String clubID;
									do {
										sendMessage("Enter ClubID of Player");
										clubID = (String)in.readObject();
									}while(sharedObject.validateClubIDExists(Integer.parseInt(clubID)) != true);
									if(sharedObject.validateClubIDExists(Integer.parseInt(clubID)) == true) {
										p.setClubID(Integer.parseInt(clubID));
									}
									
									sendMessage("Enter Player Valuation");
									message = (String)in.readObject();
									p.setValuation(Integer.parseInt(message));
									
									sendMessage("Enter Player Status ->\n1.For Sale\n2.Sold\n3.Sale Suspended");
									message = (String)in.readObject();
									if(message.equals("1")) {
										p.setStatus("For Sale");
									}
									else if(message.equals("2")) {
										p.setStatus("Sold");
									}
									else if(message.equals("3")) {
										p.setStatus("Sale Suspended");
									}
									sendMessage("Enter Player Position -> \n1.GoalKeeper\n2.Defender\n3.MidField\n4.Attacker");
									message = (String)in.readObject();
									if(message.equals("1")) {
										p.setPosition("GoalKeeper");
									}
									else if(message.equals("2")) {
										p.setPosition("Defender");
									}
									else if(message.equals("3")) {
										p.setPosition("MidField");
									}
									else if(message.equals("4")) {
										p.setPosition("Attacker");
									}
									
									p.setAgentID(AID);
									p.setPlayerID(sharedObject.createPlayerID());
									
									sharedObject.addPlayer(p);
									
									
									
								}
								
								// Change Valuation
								else if(message.equals("2")) {
									sendMessage("Enter Player ID");
									message = (String)in.readObject();
									int pid = Integer.parseInt(message);
									
									sendMessage("Enter Valuation");
									message = (String)in.readObject();
									float valuation = Integer.parseInt(message);

									sharedObject.changePlayerValuation(pid, valuation, AID);
								}
								
								//Change Status
								else if(message.equals("3")) {
									sendMessage("Enter Player ID");
									message = (String)in.readObject();
									int pid = Integer.parseInt(message);
									
									sendMessage("Enter Player Status ->\n1.For Sale\n2.Sold\n3.Sale Suspended");
									message = (String)in.readObject();
									if(message.equals("1")) {
										sharedObject.changePlayerStatus(pid, "For Sale", AID);
										message = "0";
									}
									else if(message.equals("2")) {
										sharedObject.changePlayerStatus(pid, "Sold", AID);
										message = "0";
									}
									else if(message.equals("3")) {
										sharedObject.changePlayerStatus(pid, "Sale Suspended", AID);
										message = "0";
									}
								}
							}
							
						}
						
						//Try Login as Club
						else if (message.equals("2")) {
							sendMessage("Enter Club Name");
							String CName = (String)in.readObject();
							sendMessage("Enter Club ID");
							int CID = Integer.parseInt((String)in.readObject());
						
							boolean validate = sharedObject.validateClubLogin(CID, CName);
							if(validate == true ) {
								
								//Club Logged in Menu
								sendMessage("Would you like to "
										+ "\n1.Search for all players in a Position"
										+ "\n2.Search for all players for sale in your Club"
										+ "\n3.Suspend/Resume Sale of Player in your Club"
										+ "\n4.Purchase a Player from another Club");
								
								message = (String)in.readObject();
								
								//Search for players in position
								if(message.equals("1")){

									
									sendMessage("Please Enter in Position You Wish to Search for "
											+ "\nGoalkeeper"
											+ "\nDefender"
											+ "\nMidField"
											+ "\nAttacker");
									message = (String)in.readObject();
									
									sendMessage("Here is the list of " + message.toUpperCase() + "\n" + Arrays.toString(sharedObject.searchPlayersPos(message)) );
									
									//BUG CLIENT DOES NOT RECIEVE RESPONSES AND PROGRAM LOCKS.
									// HAVE TO END PROGRAM TO CONTINUE
									
									// ONLY FIX IS TO RE-OPEN CLIENT APP
									
									
								}
								
								//Search for all players for sale in club
								else if (message.equals("2")) {
									String[] list = sharedObject.searchForPlayersOnSale(CID);
									 sendMessage(Arrays.toString(list));
									 
										//BUG CLIENT DOES NOT RECIEVE RESPONSES AND PROGRAM LOCKS.
										// HAVE TO END PROGRAM TO CONTINUE
									 
									 	//BUG: Server skips to Register Menu Prematurely.
									 
									 // ONLY FIX IS TO RE-OPEN CLIENT APP
								}
								
								//Suspend/Resume Sale of Player in Club
								else if (message.equals("3")) {
									int PlayerID = 0;
									sendMessage("Enter PlayerID of player you wish to Suspend/Resume sale of");
									message = (String)in.readObject();
									
									sendMessage("Enter Status You wish to set player to\n1.Suspend Sale\n2.Resume Sale");
									if(message.equals("1")) {
										sharedObject.changeClubPlayerSaleStatus(PlayerID, CID, 1);
									}
									else if (message.equals("2")) {
										sharedObject.changeClubPlayerSaleStatus(PlayerID, CID, 2);
									}
									
									//Server and Client lose synchronize but Status does change
									// ONLY FIX IS TO RE-OPEN CLIENT APP
								}
								
								//Purchase a Player
								else if (message.equals("4")) {
									sendMessage("Enter ID of Player You wish to purchase");
									message = (String)in.readObject();
									String ans = sharedObject.purchasePlayer(Integer.parseInt(message), CID);
									sendMessage(ans);
									
									//BUGGED. Must Exit Application on client side to continue
									//Player Purchase Does Work
								}
							}
						}
					}
					
					// If Client Choose to Register //
					if(message.equals("2")) {
						sendMessage("Would you like to register as a Club? or a Agent");
						message = (String)in.readObject();
						
						//Registering as a Club
						if(message.equalsIgnoreCase("Club")) {
							Club c = new Club();
							sendMessage("Enter a Unique Name for the Club");
							c.setName((String)in.readObject());
							sendMessage("Enter a Unique Club ID");
							c.setClubID(Integer.parseInt((String)in.readObject()));
							sendMessage("Enter a Email for the Club");
							c.setEmail((String)in.readObject());
							sendMessage("Enter the funds you have available in this club");
							c.setFundsAvailable(Float.parseFloat((String)in.readObject()));
						    sharedObject.addClub(c);
							
							
							
							//sharedObject.printClubs();
							
						}
						
						//Registering as a Agent
						else if(message.equalsIgnoreCase("Agent")) {
							Agent a = new Agent();
							sendMessage("Enter your Name");
							a.setAgentName((String)in.readObject());
							sendMessage("Enter Your Agent ID");
							a.setAgentID(Integer.parseInt((String)in.readObject()));
							sendMessage("Enter your Email");
							a.setEmail((String)in.readObject());
							sharedObject.addAgent(a);
							
							//sharedObject.printAgents();
							
						}
					
					}
					
			sendMessage("Would you like to Continue 'Y' or Terminate 'N");
			message = (String)in.readObject();
			
					
		 } while (!message.equalsIgnoreCase("N"));	
		
		} catch (EOFException e) {
			   e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		finally
		{
			try 
			{
				out.close();
				in.close();
				individualconnection.close();
			}
			
	
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	
	
	
}

