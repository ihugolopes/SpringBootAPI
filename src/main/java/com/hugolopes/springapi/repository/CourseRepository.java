package com.hugolopes.springapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugolopes.springapi.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Course findByName(String name);

}
