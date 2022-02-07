
public class Iperfer{
	private static String errorMsg = "Error: missing or additional arguments";
	private static String portErrorMsg = "Error: port number must be in the range 1024 to 65535";

	public static void main(String[] args){
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
		
	}

	private static void InitClient(String[] args){
		
	}

	public class Server {
		public Server(long port){

		}
	}

	public class Client {
		public Client(String host, long port){

		}
	}
}
