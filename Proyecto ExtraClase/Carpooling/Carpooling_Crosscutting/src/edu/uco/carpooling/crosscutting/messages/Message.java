package edu.uco.carpooling.crosscutting.messages;

import edu.uco.carpooling.crosscutting.helper.ObjectHelper;
import edu.uco.carpooling.crosscutting.helper.StringHelper;
import edu.uco.carpooling.crosscutting.messages.enumeration.MessageLevel;

public class Message {
	
	private MessageLevel level;
	private String content;
	
	public Message() {
		setLevel(MessageLevel.FATAL);
		setContent(StringHelper.EMPTY);
	}
	
	public Message(MessageLevel level, String content) {
		super();
		setLevel(level);
		setContent(content);
	}

	public MessageLevel getLevel() {
		return level;
	}
	
	public static Message createFatalMessage(final String content) {
		return new Message(MessageLevel.FATAL, content);
	}
	
	public static Message createErrorMessage(final String content) {
		return new Message(MessageLevel.ERROR, content);
	}
	
	public static Message createWarningMessage(final String content) {
		return new Message(MessageLevel.WARNING, content);
	}
	
	public static Message createInfoMessage(final String content) {
		return new Message(MessageLevel.INFO, content);
	}
	
	public static Message createSuccessMessage(final String content) {
		return new Message(MessageLevel.SUCCESS, content);
	}

	public void setLevel(final MessageLevel level) {
		this.level = ObjectHelper.getDefaultIfNull(level, MessageLevel.FATAL);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = StringHelper.applyTrim(content);
	}
}
