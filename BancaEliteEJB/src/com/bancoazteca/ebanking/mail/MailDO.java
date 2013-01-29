package com.bancoazteca.ebanking.mail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MailDO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*Modificado por Miguel Troncoso M. emerk2*/
	private Object content = null;
	private String subject = null;
	private List<String> bccRecipients = null;
	private List<String> ccRecipients = null;
	private List<String> toRecipients = null;
	private String replyTo = null;
	private String from = null;

	public MailDO() {
		super();
		bccRecipients = new ArrayList<String>();
		ccRecipients = new ArrayList<String>();
		toRecipients = new ArrayList<String>();
	}

	public Object getContent() {
		return content;
	}

	public String getSubject() {
		if( subject == null ) return "";
		return subject;
	}

	public void setContent( Object content ) {
		this.content = content;
	}

	public void setSubject( String subject ) {
		if( subject != null ) {
			subject = subject.trim();
			if( subject.length() == 0 ) subject = null;
		}
		this.subject = subject;
	}

	public List<String> getBCCRecipients() {
		return bccRecipients;
	}

	public List<String> getCCRecipients() {
		return ccRecipients;
	}

	public List<String> getTORecipients() {
		return toRecipients;
	}

	public boolean addBCCRecipient( String recipient ) {
		if( recipient != null ) {
			recipient = recipient.trim();
			if( recipient.length() != 0 )
				return bccRecipients.add( recipient );
		}
		return false;
	}

	public boolean addCCRecipient( String recipient ) {
		if( recipient != null ) {
			recipient = recipient.trim();
			if( recipient.length() != 0 )
				return ccRecipients.add( recipient );
		}
		return false;
	}

	public boolean addTORecipient( String recipient ) {
		if( recipient != null ) {
			recipient = recipient.trim();
			if( recipient.length() != 0 )
				return toRecipients.add( recipient );
		}
		return false;
	}
	/**
	 * Returns the replyTo.
	 * @return String
	 */
	public String getReplyTo() {
		return replyTo;
	}

	/**
	 * Sets the replyTo.
	 * @param replyTo The replyTo to set
	 */
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
