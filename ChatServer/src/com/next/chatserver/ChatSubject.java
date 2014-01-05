package com.next.chatserver;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ChatSubject
{
	private final Set<ChatListener> listeners = new HashSet<ChatListener>();

	public void enter( String name, ChatListener listener ) throws IOException
	{
		if ( listeners.add( listener ) )
			message( "New user entered: " + name );
	}

	public void leave( String name, ChatListener listener ) throws IOException
	{
		if ( listeners.remove(listener) )
			message( name + " leaved." );
	}

	public void message( String message ) throws IOException
	{
		for ( ChatListener listener : listeners )
		{
			listener.message( message );
		}
	}

}
