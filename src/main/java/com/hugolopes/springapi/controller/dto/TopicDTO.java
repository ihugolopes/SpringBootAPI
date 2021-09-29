package com.hugolopes.springapi.controller.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import com.hugolopes.springapi.model.Topic;

@Getter
@Setter
public class TopicDTO {

	private Long id;
	private String title;
	private String message;
	private LocalDateTime creationDate;
	
	public TopicDTO(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.creationDate = topic.getCreationDate();
	}

	public static Page<TopicDTO> convert(Page<Topic> topics) {
		return topics.map(TopicDTO::new);
	}

}
