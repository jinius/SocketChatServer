package com.next.chatserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread implements ChatListener
{
	private Socket			socket	= null;
	private BufferedReader	br		= null;
	private BufferedWriter	bw		= null;
	private ChatSubject		chatSubject	= null;

	public ServerThread( Socket socket )
	{
		this.socket		= socket;
		chatSubject		= ChatManager.getInstance().getRoom( "" );
	}
	
	@Override
	public void message( String message ) throws IOException
	{
		synchronized( this )
		{
			bw.write( message + "\n" );
			bw.flush();
		}
	}

	@Override
	public void run()
	{
		String name = "User" + getName().substring( 6 );

		try
		{
			br		= new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
			bw		= new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );

			chatSubject.enter( name, this );
			message( "enter 'quit' for quit.\n" );

			String	message	= null;
			while ( ( message = br.readLine() ) != null )
			{
				if ( message.equals("quit") )
					break;

				chatSubject.message( name + ": " + message );
			}

			chatSubject.leave( name, this );
			socket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
