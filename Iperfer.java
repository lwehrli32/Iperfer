public class Iperfer{
	private static String errorMsg = "Error: missing or additional arguments";
	private static String portErrorMsg = "Error: port number must be in the range 1024 to 65535";

	static class Client{
		String host;
		long port;
		String time;
	
		public Client(String host, long port, String time){
			System.out.println("Starting client. . .");
			this.host = host;
			this.port = port;
			this.time = time;
		}
	}

	static class Server{
		long port;

		public Server(long port){
			System.out.println("Starting server. . .");
			this.port = port;
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
		long port = 0;
		try{
			port = Long.parseLong(portNumber);
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
		long port = 0;
		
		try{
			port = Long.parseLong(args[2]);
		}catch(Exception e){}

		Server server = new Server(port);
	}

	private static void InitClient(String[] args){
		long port = 0;
		String host = args[2];
		String time = args[6];

		try{
			port = Long.parseLong(args[4]);
		}catch(Exception e){}

		Client client = new Client(host, port, time);
	}
}
