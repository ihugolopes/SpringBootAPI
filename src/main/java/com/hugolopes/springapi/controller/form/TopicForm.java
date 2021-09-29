package com.hugolopes.springapi.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.hugolopes.springapi.model.Course;
import com.hugolopes.springapi.model.Topic;
import com.hugolopes.springapi.repository.CourseRepository;

public class TopicForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String title;
	
	@NotNull @NotEmpty @Length(min = 10)
	private String message;
	
	@NotNull @NotEmpty
	private String courseName;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMenssage(String message) {
		this.message = message;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Topic convert(CourseRepository cursoRepository) {
		Course curso = cursoRepository.findByName(courseName);
		return new Topic(title, message, curso);
	}

}
