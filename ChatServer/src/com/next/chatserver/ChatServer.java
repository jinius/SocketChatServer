package com.next.chatserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer
{
	public static void main(String[] args) throws IOException
	{
		ServerSocket serverSocket = new ServerSocket( 8000 );
		
		try
		{
			while ( true )
			{
				Socket			socket			= serverSocket.accept();
				ServerThread	serverThread	= new ServerThread( socket );
				serverThread.start();
			}
		}
		catch ( IOException e )
		{
			throw e;
		}
		finally
		{
			serverSocket.close();
		}
	}
}
