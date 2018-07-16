/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.str.engg.fem.structure;

import com.str.engg.fem.loads.LoadGroup;

public class ElementResult {

	private LoadGroup loadGroup;
	private double Fx_near;
	private double Fy_near;
	private double Mz_near;
	private double Fx_far;
	private double Fy_far;
	private double Mz_far;

	public LoadGroup getLoadGroup() {
		return loadGroup;
	}

	public void setLoadGroup(LoadGroup loadGroup) {
		this.loadGroup = loadGroup;
	}

	public double getFx_near() {
		return Fx_near;
	}

	public void setFx_near(double Fx_near) {
		this.Fx_near = Fx_near;
	}

	public double getFy_near() {
		return Fy_near;
	}

	public void setFy_near(double Fy_near) {
		this.Fy_near = Fy_near;
	}

	public double getMz_near() {
		return Mz_near;
	}

	public void setMz_near(double Mz_near) {
		this.Mz_near = Mz_near;
	}

	public double getFx_far() {
		return Fx_far;
	}

	public void setFx_far(double Fx_far) {
		this.Fx_far = Fx_far;
	}

	public double getFy_far() {
		return Fy_far;
	}

	public void setFy_far(double Fy_far) {
		this.Fy_far = Fy_far;
	}

	public double getMz_far() {
		return Mz_far;
	}

	public void setMz_far(double Mz_far) {
		this.Mz_far = Mz_far;
	}

	public ElementResult(LoadGroup loadGroup, double Fx_near, double Fy_near, double Mz_near, double Fx_far,
			double Fy_far, double Mz_far) {
		this.loadGroup = loadGroup;
		this.Fx_near = Fx_near;
		this.Fy_near = Fy_near;
		this.Mz_near = Mz_near;
		this.Fx_far = Fx_far;
		this.Fy_far = Fy_far;
		this.Mz_far = Mz_far;
	}

}
