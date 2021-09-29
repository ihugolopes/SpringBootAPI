package com.hugolopes.springapi.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hugolopes.springapi.controller.dto.TopicDetailsDTO;
import com.hugolopes.springapi.controller.dto.TopicDTO;
import com.hugolopes.springapi.controller.form.TopicUpdateForm;
import com.hugolopes.springapi.controller.form.TopicForm;
import com.hugolopes.springapi.model.Topic;
import com.hugolopes.springapi.repository.CourseRepository;
import com.hugolopes.springapi.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicsController {
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping
	@Cacheable(value = "topicsList")
	public Page<TopicDTO> list(@RequestParam(required = false) String courseName, 
			@PageableDefault(sort = "creationDate", direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {
		
		if (courseName == null) {
			Page<Topic> topics = topicRepository.findAll(pageable);
			return TopicDTO.convert(topics);
		} else {
			Page<Topic> topics = topicRepository.findByCourse(courseName, pageable);
			return TopicDTO.convert(topics);
		}
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "topicsList", allEntries = true)
	public ResponseEntity<TopicDTO> create(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
		System.out.println(form);
		Topic topic = form.convert(courseRepository);
		topicRepository.save(topic);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDTO(topic));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TopicDetailsDTO> detail(@PathVariable Long id) {
		Optional<Topic> topic = topicRepository.findById(id);
		if (topic.isPresent()) {
			return ResponseEntity.ok(new TopicDetailsDTO(topic.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "topicsList", allEntries = true)
	public ResponseEntity<TopicDTO> update(@PathVariable Long id, @RequestBody @Valid TopicUpdateForm form) {
		Optional<Topic> optional = topicRepository.findById(id);
		if (optional.isPresent()) {
			Topic topic = form.topicUpdate(id, topicRepository);
			return ResponseEntity.ok(new TopicDTO(topic));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "topicsList", allEntries = true)
	public ResponseEntity<?> remove(@PathVariable Long id) {
		Optional<Topic> optional = topicRepository.findById(id);
		if (optional.isPresent()) {
			topicRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}