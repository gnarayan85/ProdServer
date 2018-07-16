package com.str.engg.design.service;

public final class HelperUtil {

	public static double getEc(double fcu_t, double fcu_28) {
		if (fcu_t == 0) {
			return 0;
		}
		if (fcu_t == fcu_28) {
			return 20 + 0.2 * fcu_28;
		}

		return (20 + 0.2 * fcu_28) * (.4 + (.6 * fcu_t / fcu_28));

	}

}
