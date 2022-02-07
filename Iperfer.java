import java.io.*;
import java.net.*;

public class Iperfer{
	private static String errorMsg = "Error: missing or additional arguments";
	private static String portErrorMsg = "Error: port number must be in the range 1024 to 65535";

	static class Client{
		String host;
		int port;
		int time;
	
		public Client(String host, int port, int time){
			this.host = host;
			this.port = port;
			this.time = time;
			StartClient();
		}

		private void StartClient(){
			try{
				Socket echoSocket = new Socket(this.host, this.port);
				PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				
				String userInput = "0";
				long start = System.currentTimeMillis();
				long end = start;
				long numBytesSent = 0;
				while((end / 1000) - (start / 1000) < this.time){
					out.println(userInput);
					numBytesSent = numBytesSent + userInput.getBytes().length;
					end = System.currentTimeMillis();
				}

				numBytesSent = numBytesSent / 1000;
				System.out.println("sent=" + numBytesSent + " KB");

				in.close();
				out.close();
				stdIn.close();
				echoSocket.close();

			}catch(Exception e){
				System.out.println("Cannot connect to server");
				e.printStackTrace();
			}
		}
	}

	static class Server{
		int port;

		public Server(int port){
			this.port = port;
			StartServer();
		}

		private void StartServer(){
			try{
				ServerSocket serverSocket = new ServerSocket(this.port);
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				String inputLine, outputLine;
				long numBytesRecieved = 0;
				while((inputLine = in.readLine()) != null){
					// do server work here
					// get size of data sent to server
					numBytesRecieved = numBytesRecieved + inputLine.getBytes().length;
				}

				numBytesRecieved = numBytesRecieved / 1000;
				System.out.println("recieved=" + numBytesRecieved + " KB");

				serverSocket.close();
				in.close();
				out.close();
			}catch(Exception e){
				System.out.println("Cannot start server");
				e.printStackTrace();
			}
			
		}
	}	

	public static void main(String[] args){
		if (args.length <= 0){
			printError(errorMsg);
		}

		String type = args[0];

		if (type.equals("-c")){
			// client	//
			if (args.length != 7){
				printError(errorMsg);
			}
			checkPortNumber(args[4]);
			InitClient(args);
		}else if (type.equals("-s")){
			// server
			if (args.length != 3){
				printError(errorMsg);
			}
			checkPortNumber(args[2]);
			InitServer(args);
		}else{
			// error
			printError(errorMsg);
		}
	}

	private static void checkPortNumber(String portNumber){
		int port = 0;
		try{
			port = Integer.parseInt(portNumber);
		}catch(Exception e){
			printError(portErrorMsg);
		}

		if (port < 1024 || port > 65535){
			printError(portErrorMsg);
		}
	}

	private static void printError(String msg){
		System.out.println(msg);
		System.exit(0);
	}

	private static void InitServer(String[] args){
		int port = 0;
		
		try{
			port = Integer.parseInt(args[2]);
		}catch(Exception e){}

		Server server = new Server(port);
	}

	private static void InitClient(String[] args){
		int port = 0;
		String host = args[2];
		int time = 0;

		try{
			port = Integer.parseInt(args[4]);
			time = Integer.parseInt(args[6]);
		}catch(Exception e){}

		Client client = new Client(host, port, time);
	}
}
