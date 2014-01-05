package com.next.chatserver;

import java.util.HashMap;
import java.util.Map;

public class ChatManager
{
	private static final ChatManager instance = new ChatManager();
	
	public static ChatManager getInstance()
	{
		return instance;
	}

	private Map<String, ChatSubject> chatSubjects = new HashMap<String, ChatSubject>();

	public ChatSubject getRoom( String roomID )
	{
		ChatSubject chatSubject = chatSubjects.get( roomID );
		if (chatSubject == null)
		{
			chatSubject = new ChatSubject();
			chatSubjects.put(roomID, chatSubject);
		}

		return chatSubject;
	}
}
