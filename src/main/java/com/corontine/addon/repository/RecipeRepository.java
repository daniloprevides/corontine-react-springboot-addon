package com.corontine.addon.repository;

import java.util.List;

import com.corontine.addon.model.RecipeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeRepository extends MongoRepository<RecipeEntity, String> {

    public RecipeEntity findByFirstName(String firstName);
    public List<RecipeEntity> findByLastName(String lastName);

}