package ee.ttu.catering.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ee.ttu.catering.rest.model.Image;

public interface ImageRepository extends JpaRepository<Image, String>{
	
}