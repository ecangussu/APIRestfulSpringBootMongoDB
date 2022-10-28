package com.ecangussu.springmongo.dto;

import java.io.Serializable;
import java.util.Date;

public class CommentDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String comment;
	private Date date;
	private AuthorDTO authorDTO;

	public CommentDTO() {
	}

	public CommentDTO(String comment, Date date, AuthorDTO authorDTO) {
		this.comment = comment;
		this.date = date;
		this.authorDTO = authorDTO;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AuthorDTO getAuthorDTO() {
		return this.authorDTO;
	}

	public void setAuthorDTO(AuthorDTO authorDTO) {
		this.authorDTO = authorDTO;
	}

}
