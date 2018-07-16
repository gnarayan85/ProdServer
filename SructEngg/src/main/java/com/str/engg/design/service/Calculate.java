package com.str.engg.design.service;

import org.springframework.stereotype.Component;

@Component
public class Calculate {

	public double getUdlMoment(double moment, double value, double x, double span) {
		return moment+(((value*x*span)/2)*(span-(x*span)));
	}

	public double getUdlShear(double shear, double value, double x, double span) {
		return shear+(value*((span/2)*x));
	}

}
