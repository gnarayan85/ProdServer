package com.str.engg.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Document
public class Graph {
	private @Id long graphId;
	private String createdUser;
	private String graphCode;
	
	
	
	
	public Graph(long graphId){
		this.graphId = graphId;
	}

	public long getGraphId() {
		return graphId;
	}

	public void setGraphId(long graphId) {
		this.graphId = graphId;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getGraphCode() {
		return graphCode;
	}

	public void setGraphCode(String graphCode) {
		this.graphCode = graphCode;
	}
 
	
}