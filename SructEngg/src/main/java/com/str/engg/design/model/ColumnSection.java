package com.str.engg.design.model;

import org.springframework.data.annotation.Id;

public class ColumnSection {
	@Id
	int colSectId;
	double width;
	double depth;
	int materialID;
	
	
	public int getColSectId() {
		return colSectId;
	}
	public void setColSectId(int colSectId) {
		this.colSectId = colSectId;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getDepth() {
		return depth;
	}
	public void setDepth(double depth) {
		this.depth = depth;
	}
	public int getMaterialID() {
		return materialID;
	}
	public void setMaterialID(int materialID) {
		this.materialID = materialID;
	}
	
	

}
