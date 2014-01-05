package com.next.chatclient.ui;

public interface ChatWriter
{
	public void write( String message );
	public void close();
}
