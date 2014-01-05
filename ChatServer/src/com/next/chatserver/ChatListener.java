package com.next.chatserver;

import java.io.IOException;

public interface ChatListener
{
	public String	getName();
	public void		message( String message ) throws IOException;
}
