package com.str.engg.design.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.str.engg.design.model.SubFrame;

@Repository
public interface CustomSubFrameRepository {

	List<SubFrame> findSubFramesByProjectNumber(int projectId);

	DBObject findOne(int projectId, int subFrameId);

	SubFrame insert(int projectId, SubFrame subFrame);

	SubFrame update(int projectId, int subFrameNumber, SubFrame subFrame);

}
