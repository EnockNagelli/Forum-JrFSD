package com.iiht.forumAdmin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iiht.forumAdmin.model.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String>{

}
