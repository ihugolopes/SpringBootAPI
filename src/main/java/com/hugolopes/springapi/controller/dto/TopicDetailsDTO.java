package com.hugolopes.springapi.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.hugolopes.springapi.model.StatusTopic;
import com.hugolopes.springapi.model.Topic;

public class TopicDetailsDTO {

	private Long id;
	private String title;
	private String message;
	private LocalDateTime creationDate;
	private String authorName;
	private StatusTopic status;
	private List<ResponseDTO> responses;
	
	public TopicDetailsDTO(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.creationDate = topic.getCreationDate();
		this.authorName = topic.getAuthor().getName();
		this.status = topic.getStatus();
		this.responses = new ArrayList<>();
		this.responses.addAll(topic.getResponses().stream().map(ResponseDTO::new).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public String getAuthorName() {
		return authorName;
	}

	public StatusTopic getStatus() {
		return status;
	}

	public List<ResponseDTO> getResponses() {
		return responses;
	}

	
	
}
