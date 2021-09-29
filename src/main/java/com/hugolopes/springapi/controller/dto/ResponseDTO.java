package com.hugolopes.springapi.controller.dto;

import java.time.LocalDateTime;

import com.hugolopes.springapi.model.Response;

import lombok.Getter;

@Getter
public class ResponseDTO {

	private Long id;
	private String message;
	private LocalDateTime creationDate;
	private String authorName;
	
	public ResponseDTO(Response response) {
		this.id = response.getId();
		this.message = response.getMessage();
		this.creationDate = response.getCreationDate();
		this.authorName = response.getAuthor().getName();
	}


	
	
}
