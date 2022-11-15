package edu.uco.budget.crosscutting.messages;

import edu.uco.budget.crosscutting.helper.ObjectHelper;
import edu.uco.budget.crosscutting.helper.StringHelper;
import edu.uco.budget.crosscutting.messages.enumeration.MessageLevel;

public final class Message {
	private MessageLevel level;
	
	private String content;
	
	public Message() {
		setLevel(MessageLevel.FATAL);
		setContent(content);
	}
	
	public Message(MessageLevel level, String content) {
		super();
		this.level = level;
		this.content = content;
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
		return new Message(MessageLevel.SUCESS, content);
	}

	public MessageLevel getLevel() {
		return level;
	}

	public void setLevel(final  MessageLevel level) {
		this.level = ObjectHelper.getDefaultIfNull(level, MessageLevel.FATAL);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = StringHelper.applyTrim(content);
	}
}
