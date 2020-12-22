package es.usj.sac.controller;

import java.text.DecimalFormat;

import es.usj.sac.service.AreaType;
import es.usj.sac.service.Cost231HataDService;

public class Cost231HataDController implements IDistanceEstimation {
	private static DecimalFormat df = new DecimalFormat("0.00");

	public static String predict(double f, double lb, double hb, double hm, String area) {
		switch (area) {
		case "Rural":
			return df.format(Cost231HataDService.predict(f, lb, hb, hm, AreaType.RURAL));
		case "Suburban":
			return df.format(Cost231HataDService.predict(f, lb, hb, hm, AreaType.SUBURBAN));
		case "Medium-small city":
			return df.format(Cost231HataDService.predict(f, lb, hb, hm, AreaType.MD_SM_CITY));
		case "Big city":
			return df.format(Cost231HataDService.predict(f, lb, hb, hm, AreaType.LG_CITY));
		default:
			return "";
		}
	}
}
