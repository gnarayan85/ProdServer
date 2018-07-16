package com.str.engg.design.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Column {
	@Id
	 int columnId;
	 double columnHeight;
     int colSectId;
     List<EndForces> endForceList;
     
	public int getColumnId() {
		return columnId;
	}
	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}
	public double getColumnHeight() {
		return columnHeight;
	}
	public void setColumnHeight(double columnHeight) {
		this.columnHeight = columnHeight;
	}
	public int getColSectId() {
		return colSectId;
	}
	public void setColSectId(int colSectId) {
		this.colSectId = colSectId;
	}
	public List<EndForces> getEndForceList() {
		return endForceList;
	}
	public void setEndForceList(List<EndForces> endForceList) {
		this.endForceList = endForceList;
	}
 
}
