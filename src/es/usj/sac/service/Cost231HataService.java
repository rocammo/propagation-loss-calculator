package es.usj.sac.service;

public class Cost231HataService {
	/**
	 * Free-space path loss calculation.
	 * 
	 * This method implements the COST231-Hata model.
	 * 
	 * @return the attenuation of radio energy between the feedpoints of 2 antennas.
	 */
	public static double predict(double f, double d, double hb, double hm, AreaType areaType) {
		double lb = 0.0;

		if ((f >= 1500 && f <= 2000) && (d >= 1 && d <= 20) && (hb >= 30 && hb <= 200) && (hm >= 1 && hm <= 10)) {
			double F = 46.3 + 33.9 * Math.log10(f) - 13.82 * Math.log10(hb);
			double B = 44.9 - 6.55 * Math.log10(hb);
			double C = 3.2 * Math.pow(Math.log10(11.75 * hm), 2) - 4.97;
			double G = (areaType == AreaType.LG_CITY) ? 3 : 0;
			lb = F + B * Math.log10(d) - C + G;
		}

		return lb;
	}
}
