package com.str.engg.design.core;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.str.engg.design.common.CustomSubFrameRepository;
import com.str.engg.design.model.Project;
import com.str.engg.design.model.SubFrame;

@Repository
public class CustomSubFrameRepositoryImpl implements CustomSubFrameRepository{

	@Autowired
	MongoTemplate mongoTemplate;
	DBObject tst;
	@Override
	public DBObject findOne(int projectId, int subFrameId) {
		
		
		
		
		Aggregation aggregation = newAggregation(
		        match(Criteria.where("projectNumber").is(projectId)),
		        unwind("subframeList"),
		        match(Criteria.where("subframeList.subFrameId").is(subFrameId)),
		        project("projectNumber","subframeList"),
		        group("projectNumber").push("subframeList").as("subframe")
		    );
		
		System.out.println(aggregation);
		AggregationResults<DBObject> result = mongoTemplate.aggregate(aggregation, Project.class,DBObject.class);
		
		result.forEach(new Consumer<DBObject>() {
		    @Override
		    public void accept(DBObject t) {
		        System.out.println(tst=t);
		   		    }
		});
		
		return tst;

}

	@Override
	public List<SubFrame> findSubFramesByProjectNumber(int projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubFrame insert(int projectId, SubFrame subFrame) {
		Query query = new Query(Criteria.where("projectNumber").is(projectId));
		Update update = new Update().push("subframeList", subFrame);
		System.out.println(update);
		this.mongoTemplate.findAndModify(query, update, Project.class);
		return subFrame;
	}

	@Override
	public SubFrame update(int projectId, int subFrameNumber, SubFrame subFrame) {
		Query query = new Query(Criteria.where("_id").is(projectId)
				.and("subframeList")			
	            .elemMatch(Criteria.where("_id").is(subFrameNumber)));
		Update update = new Update().set("subframeList.$", subFrame);
		System.out.println(update);
		this.mongoTemplate.findAndModify(query, update, Project.class);
		return subFrame;
	}
}