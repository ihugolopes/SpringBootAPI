package com.hugolopes.springapi.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import com.hugolopes.springapi.model.Topic;
import com.hugolopes.springapi.repository.TopicRepository;

@Setter
public class TopicUpdateForm {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String title;

	@NotNull @NotEmpty @Length(min = 10)
	private String message;

	public Topic topicUpdate(Long id, TopicRepository topicRepository) {
		Topic topic = topicRepository.getOne(id);
		topic.setTitle(this.title);
		topic.setMessage(this.message);
		return topic;
	}
	
}
