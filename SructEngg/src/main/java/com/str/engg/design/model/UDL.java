package com.str.engg.design.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class UDL {
	@Id
	int udlId;
	double value;

	List<Integer> beamIdList;

	public List<Integer> getBeamIdList() {
		return beamIdList;
	}

	public void setBeamIdList(List<Integer> beamIdList) {
		this.beamIdList = beamIdList;
	}

	public int getUdlId() {
		return udlId;
	}

	public void setUdlId(int udlId) {
		this.udlId = udlId;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
