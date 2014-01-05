package com.next.chatclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.next.chatclient.ui.ChatUI;

public class ChatThread extends Thread
{
	private Socket			socket	= null;
	private ChatUI			chatUI	= null;
	private BufferedReader	br		= null;

	public ChatThread( Socket socket ) throws IOException
	{
		this.socket	= socket;
		chatUI		= new ChatUI( socket );
		br			= new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
		
		chatUI.show();
	}

	@Override
	public void run()
	{
		String	message	= null;

		try
		{
			while ( ( message = br.readLine() ) != null )
				chatUI.write( message );
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			chatUI.close();
			try
			{
				socket.close();
			}
			catch ( IOException e )
			{
				e.printStackTrace();
			}
		}
	}
}
