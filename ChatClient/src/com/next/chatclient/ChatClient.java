package com.next.chatclient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ChatClient
{
	public static void main( String[] args ) throws IOException
	{
		String hostName;

		if ( args.length < 1 )
			hostName = "localhost";
		else
			hostName = args[0];

		Socket socket = new Socket();
		
		try
		{
			socket.connect( new InetSocketAddress( hostName , 8000 ) );
			
			ChatThread	chatThread	= new ChatThread( socket );
			chatThread.start();
		}
		catch ( IOException e )
		{
			throw e;
		}
	}
}
