package es.usj.sac.service;

public class Cost231HataDService {
	/**
	 * Free-space path loss calculation.
	 * 
	 * This method implements the COST231-Hata model (distance).
	 * 
	 * @return the distance of radio energy between the feedpoints of 2 antennas.
	 */
	public static double predict(double f, double lb, double hb, double hm, AreaType areaType) {
		double d = 0.0;

		if ((f >= 1500 && f <= 2000) && (hb >= 30 && hb <= 200) && (hm >= 1 && hm <= 10)) {
			double F = 46.3 + 33.9 * Math.log10(f) - 13.82 * Math.log10(hb);

			double B = 44.9 - 6.55 * Math.log10(hb);

			double C = 0.0;
			switch (areaType) {
			case RURAL:
				C = 4.78 * Math.pow(Math.log10(f), 2) - 18.33 * Math.log10(f) + 40.94;
				break;
			case SUBURBAN:
				C = 2 * Math.pow(Math.log10(f / 28), 2) + 5.4;
				break;
			case MD_SM_CITY:
				C = (1.1 * Math.log10(f) - 0.7) * hm - 1.56 * Math.log10(f) + 0.8;
				break;
			case LG_CITY:
				C = 3.2 * Math.pow(Math.log10(11.75 * hm), 2) - 4.97;
				break;
			}

			double G = (areaType == AreaType.LG_CITY) ? 3 : 0;

			d = Math.pow(10, (lb - F + C - G) / B);
		}

		return d;
	}
}
