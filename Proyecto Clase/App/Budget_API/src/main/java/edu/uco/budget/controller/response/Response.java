package edu.uco.budget.controller.response;

import java.util.ArrayList;
import java.util.List;
import edu.uco.budget.crosscutting.helper.ObjectHelper;
import edu.uco.budget.crosscutting.messages.Message;

public class Response<T> {
	
	private List<Message> messages;
	private List<T> data;
	
	public Response() {
		setMessages(new ArrayList<>());
		setData(new ArrayList<>());
	}
	
	public Response(List<Message> messages, List<T> data) {
		super();
		setMessages(messages);
		setData(data);
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(final List<Message> messages) {
		this.messages = ObjectHelper.getDefaultIfNull(messages, new ArrayList<>());
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = ObjectHelper.getDefaultIfNull(data, new ArrayList<>());
	}
	
	public void addFatalMessage(final String content) {
		getMessages().add(Message.createFatalMessage(content));	
	}
	
	public void addErrorMessage(final String content) {
		getMessages().add(Message.createErrorMessage(content));	
	}
	
	public void addWarningMessage(final String content) {
		getMessages().add(Message.createWarningMessage(content));	
	}
	
	public void addInfoMessage(final String content) {
		getMessages().add(Message.createInfoMessage(content));	
	}
	
	public void addSuccessMessage(final String content) {
		getMessages().add(Message.createSuccessMessage(content));	
	}
	
}
