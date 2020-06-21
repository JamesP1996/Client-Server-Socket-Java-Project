import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import java.util.Scanner;
//test
public class Client 
{
	
	private Socket connection;
	private String message;
	private  Scanner console;
	private  String ipaddress;
	private  int portaddress;
	private ObjectOutputStream out;
	private ObjectInputStream in;


	public Client()
	{
		console = new Scanner(System.in);
		
		System.out.println("Enter the IP Address of the server");
		ipaddress = console.nextLine();
		
		System.out.println("Enter the TCP Port");
		portaddress  = console.nextInt();
		
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
	
	
	public static void main(String[] args) 
	{
			Client temp = new Client();
			temp.clientapp();
	}

	public void clientapp()
	{
		
		try 
		{
			connection = new Socket(ipaddress,portaddress);
		
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			System.out.println("Client Side ready to communicate");
			SharedClass sharedObject = new SharedClass();
		
		do {
			
		
		    /// Client App
				do
				{
					//Asking if User Wants to Login In Or Register
					message = (String)in.readObject();
					System.out.println(message);
					//Handle User Response
					message = console.next();
					sendMessage(message);
				}while(!message.equalsIgnoreCase("1") && !message.equalsIgnoreCase("2"));
					
					//Login Menu
					if(message.equals("1")) {
						
						//Server asks if user is Agent/Club
						message = (String)in.readObject();
						System.out.println(message);
						
						//User Responds
						message = console.next();
						sendMessage(message);
						
						//Agent
						if(message.equals("1")) {
						
						//Server asks for Agent Name
						message = (String)in.readObject();
						System.out.println(message);
						message = console.next();
						String Name = message;
						sendMessage(message);
						
						// Server Asks for AgentID
						message = (String)in.readObject();
						System.out.println(message);
						message = console.next();
						int AID = Integer.parseInt(message);
						sendMessage(message);
						System.out.println("If Validation Fails User Will Return to Terminate Y/N Menu");
						
						if(sharedObject.validateAgentLogin(AID, Name) == true) {
							//Agent Login Menu Appears
							message = (String)in.readObject();
							System.out.println(message);
							
							//User Picks Option
							message = console.next();
							sendMessage(message);
							
							//Add Player
							if(message.equals("1")) {
								//Asked to Enter Player Name
								message = (String)in.readObject();
								System.out.println(message);
								message = console.next();
								sendMessage(message);
								
								//Asked to Enter Player Age
								message = (String)in.readObject();
								System.out.println(message);
								message = console.next();
								sendMessage(message);
								
							
								//Asked to Enter Player Club ID
								int clubID;
								do {
									message = (String)in.readObject();
									System.out.println(message);
									//Enter Club ID
									message = console.next();
									clubID = Integer.parseInt(message);
									sendMessage(message);
									
								}while(sharedObject.validateClubIDExists(clubID) != true);
								
								//Asked to Enter Valuation
								message = (String)in.readObject();
								System.out.println(message);
								message = console.next();
								sendMessage(message);
								
								//Asked to Enter Player Status
								int status;
								do {
								message = (String)in.readObject();
								System.out.println(message);
								message = console.next();
								status = Integer.parseInt(message);
								}while(!(status == 1 || status == 2 || status == 3));
								
								//Asked for Position
								int position;
								do {
								message = (String)in.readObject();
								System.out.println(message);
								message = console.next();
								position = Integer.parseInt(message);
								}while(!(position == 1 || position == 2 || position == 3 || position == 4));
								
								System.out.println("Player Added");
							
								
							}
							
							// Change Valuation
							else if(message.equals("2")) {
								
								System.out.println("Warning Valuation Will Not Change if you do not own player or playerID does not exist");
								
								//Asks for Player ID
								message = (String)in.readObject();
								System.out.println(message);
								message = console.next();
								sendMessage(message);
								
								//Asks for Valuation
								message = (String)in.readObject();
								System.out.println(message);
								message = console.next();
								sendMessage(message);
								
								
							}
							
							//Change Status
							else if(message.equals("3")) {
								
								System.out.println("Warning Status Will Not Change if you do not own player or playerID does not exist");
								
								//Asks for Player ID
								message = (String)in.readObject();
								System.out.println(message);
								message = console.next();
								sendMessage(message);
								
								//Asks for Status
								int status;
								do {
								message = (String)in.readObject();
								System.out.println(message);
								message = console.next();
								sendMessage(message);
								status = Integer.parseInt(message);
								}while(!(status == 1 || status == 2 || status == 3));
								
								
							}
							
						}

						}
						//Club
						else if (message.equals("2")) {
							//Server asks for Agent Name
							message = (String)in.readObject();
							System.out.println(message);
							message = console.next();
							String CName = message;
							sendMessage(message);
							
							// Server Asks for AgentID
							message = (String)in.readObject();
							System.out.println(message);
							message = console.next();
							int CID = Integer.parseInt(message);
							sendMessage(message);
							System.out.println("If Validation Fails User Will Return to Terminate Y/N Menu");
							
							if(sharedObject.validateClubLogin(CID, CName) == true) {
								
								//Show Club Menu
								System.out.println("Enter a Number of one of these following options or \n"
										+ "Enter something else to Return to Y/N Terminate Menu");
								message = (String)in.readObject();
								System.out.println(message);
								//User Sends Option
								message = console.next();
								sendMessage(message);
								
								//Search Players In a Position
								if(message.equals("1")) {

									//Asks for Position
									message = (String)in.readObject();
									System.out.println(message);
									message = console.next();
									sendMessage(message);
									
									//Receives Response
									message = (String)in.readObject();
									System.out.println(message);
									
									
									//BUG CLIENT DOES NOT RECIEVE RESPONSES AND PROGRAM LOCKS.
									// HAVE TO END PROGRAM TO CONTINUE
									
									// ONLY FIX IS TO RE-OPEN CLIENT APP
										
									}
								 }
								
								}
								
								//Search for all players in club for sale
								else if (message.equals("2")) {
									message = (String)in.readObject();
									System.out.println(message);
									
									//BUG CLIENT DOES NOT RECIEVE RESPONSES AND PROGRAM LOCKS.
									// HAVE TO END PROGRAM TO CONTINUE
									// ONLY FIX IS TO RE-OPEN CLIENT APP
									
								} 
								
								//Suspend/Resume Sale of a Player
								else if (message.equals("3")) {
									//Asks User for Player ID
									message = (String)in.readObject();
									System.out.println(message);
									message = console.next();
									sendMessage(message);
									
									//Asks for Status Wanted
									message = (String)in.readObject();
									System.out.println(message);
									message = console.next();
									sendMessage(message);
									
									System.out.println("Player Status Changed");
									
									//Server and Client lose synchronize but Status does change
									// ONLY FIX IS TO RE-OPEN CLIENT APP
									
								}
								
								//Purchase a Player
								else if (message.equals("4")) {
									//"Enter ID of Player You wish to purchase"
									message = (String)in.readObject();
									System.out.println(message);
									
									//Send ID
									message = console.next();
									sendMessage(message);
									
									//Receive Message
									message = (String)in.readObject();
									System.out.println(message);
									
									//BUGGED. Must Exit Application on client side to continue
									//Player Purchase Does Work
									
								}
								
							}
							
						
						
					
					
					
					else if(message.equals("2")) {
						//Server Asks User If They are a Club or a Agent
						message = (String)in.readObject();
						System.out.println(message);
						
						//User Responds With "Club" or "Agent"
						message = console.next();
						sendMessage(message);
						
						if(message.equalsIgnoreCase("Club")) {
							
							//"Enter a Name for the Club"
							message = (String)in.readObject();
							System.out.println(message);
							message = console.next();
							sendMessage(message);
							
							//"Enter a Club ID"
							message = (String)in.readObject();
							System.out.println(message);
							message = console.next();
							sendMessage(message);
							
							//"Enter a Email for the Club"
							message = (String)in.readObject();
							System.out.println(message);
							message = console.next();
							sendMessage(message);
							
							//"Enter Funds Available to Club"
							message = (String)in.readObject();
							System.out.println(message);
							message = console.next();
							sendMessage(message);
							
							
							
						
						}
						else if (message.equalsIgnoreCase("Agent")) {
							
							//"Enter Your Agent Name"
							message = (String)in.readObject();
							System.out.println(message);
							message = console.next();
							sendMessage(message);
							
							//"Enter Your Agent ID"
							message = (String)in.readObject();
							System.out.println(message);
							message = console.next();
							sendMessage(message);
							
							//"Enter Your Agent Email"
							message = (String)in.readObject();
							System.out.println(message);
							message = console.next();
							sendMessage(message);
						}
				
						
							
					}
					
		//Continue or Terminate
		message = (String)in.readObject();
		System.out.println(message);
		message = console.next();
		sendMessage(message);
		
		} while (!message.equalsIgnoreCase("N"));
			
			out.close();
			in.close();
			connection.close();
		} 
		catch (EOFException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
