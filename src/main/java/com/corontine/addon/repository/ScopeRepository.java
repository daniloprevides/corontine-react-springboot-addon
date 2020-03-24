package com.corontine.addon.repository;

import com.corontine.addon.model.RecipeEntity;
import com.corontine.addon.model.ScopeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ScopeRepository extends MongoRepository<ScopeEntity, String> {
    public ScopeEntity findOneByCode(String code);
}