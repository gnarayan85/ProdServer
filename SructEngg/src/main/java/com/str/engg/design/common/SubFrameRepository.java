package com.str.engg.design.common;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.str.engg.design.model.SubFrame;

@Repository
public interface SubFrameRepository extends MongoRepository<SubFrame, Integer>{
	
}
