package com.next.chatclient.ui;

import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.*;

public class ChatUI extends JPanel
{
	private BufferedWriter	bw		= null;

	private static final long	serialVersionUID	= 2059551023878276228L;
	private static final int	WIDTH				= 640;
	private static final int	HEIGHT				= 480;
	private static final String	SEND_BUTTON_TEXT	= "Send";
	
	JFrame		frame;
	JTextArea	textArea;
	JTextField	textField;
	JButton		sendButton;

	public ChatUI( Socket socket ) throws IOException
	{
		bw			= new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
		createLayout();
	}
	
	public void createLayout()
	{
		textArea	= new JTextArea( 20, 48 );
		textField	= new JTextField( 38 );
		sendButton	= new JButton( SEND_BUTTON_TEXT );

		ActionListener send = new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				send();
			}
		};

		textField.addActionListener( send );
		sendButton.addActionListener( send );
		
		add( textArea );
		add( textField );
		add( sendButton );
	}
	
	public void show()
	{
		frame = new JFrame();
		frame.setSize( WIDTH, HEIGHT );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		frame.getContentPane().add( this );
		frame.setVisible( true );
	}

	public void send()
	{
		String message = textField.getText();
		textField.setText( "" );

		try
		{
			bw.write( message + "\n" );
			bw.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void write( String message )
	{
		textArea.append( message + "\n" );
	}
	
	public void close()
	{
		frame.dispose();
	}
}
