package com.myowndev.main;

import java.util.ArrayList;

import com.myowndev.fileIO.Load_CIMEL;
import com.myowndev.fileIO.Load_SP9;

public class Calculator {

	public static ArrayList<Float> cc1 = new ArrayList<Float>();
	public static ArrayList<Float> cc2 = new ArrayList<Float>();
	public static ArrayList<Float> cc3 = new ArrayList<Float>();
	public static ArrayList<Float> cc4 = new ArrayList<Float>();
	public static ArrayList<Float> cc5 = new ArrayList<Float>();
	public static ArrayList<Float> cc6 = new ArrayList<Float>();
	public static ArrayList<Float> cc9 = new ArrayList<Float>();
	public static ArrayList<Float> cc11 = new ArrayList<Float>();
	public static ArrayList<Float> cc15 = new ArrayList<Float>();
	
	public Calculator()  {
		String calDate = null;
		float tempcc1 = 0f;
		float tempcc2 = 0f;
		float tempcc3 = 0f;
		float tempcc4 = 0f;
		float tempcc5 = 0f;
		float tempcc6 = 0f;
		float tempcc9 = 0f;
		float tempcc11 = 0f;
		float tempcc15 = 0f;
		
		for(int i = 0; i < Load_SP9.UBAT.size(); i++) {
			if(calDate == null) {
				calDate = Load_SP9.DATE.get(i);
				tempcc1 = cc(calDate, Load_SP9.K1, Load_CIMEL.AOT_412);
				tempcc2 = cc(calDate, Load_SP9.K2, Load_CIMEL.AOT_440);
				tempcc3 = cc(calDate, Load_SP9.K3, Load_CIMEL.AOT_500);
				tempcc4 = cc(calDate, Load_SP9.K4, Load_CIMEL.AOT_551);
				tempcc5 = cc(calDate, Load_SP9.K5, Load_CIMEL.AOT_675);
				tempcc6 = cc(calDate, Load_SP9.K6, Load_CIMEL.AOT_1640);
				tempcc9 = cc(calDate, Load_SP9.K9, Load_CIMEL.AOT_870);
				tempcc11 = cc(calDate, Load_SP9.K11, Load_CIMEL.AOT_1020);
				tempcc15 = cc(calDate, Load_SP9.K15, Load_CIMEL.AOT_340);
			}
			if(Load_SP9.DATE.get(i).equals(calDate)) {
				Load_SP9.K1.set(i, Load_SP9.K1.get(i) * tempcc1);
				cc1.add(tempcc1);
				Load_SP9.K2.set(i, Load_SP9.K2.get(i) * tempcc2);
				cc2.add(tempcc2);
				Load_SP9.K3.set(i, Load_SP9.K3.get(i) * tempcc3);
				cc3.add(tempcc3);
				Load_SP9.K4.set(i, Load_SP9.K4.get(i) * tempcc4);
				cc4.add(tempcc4);
				Load_SP9.K5.set(i, Load_SP9.K5.get(i) * tempcc5);
				cc5.add(tempcc5);
				Load_SP9.K6.set(i, Load_SP9.K6.get(i) * tempcc6);
				cc6.add(tempcc6);
				Load_SP9.K9.set(i, Load_SP9.K9.get(i) * tempcc9);
				cc9.add(tempcc9);
				Load_SP9.K11.set(i, Load_SP9.K11.get(i) * tempcc11);
				cc11.add(tempcc11);
				Load_SP9.K15.set(i, Load_SP9.K15.get(i) * tempcc15);
				cc15.add(tempcc15);
			}
			if(!(Load_SP9.DATE.get(i + 1).equals(calDate))) calDate = null;
		}
	}
	
	public float cc(String date, ArrayList<Float> f1, ArrayList<Float> f2) {
		int n = 0;
		float sred_1 = 0;
		float sred_2 = 0;
		for(int i = 0; i < f1.size(); i++) {
			if(Load_SP9.DATE.get(i).equals(date)) {
				n++;
				sred_1 = sred_1 + f1.get(i);
			}
		}
		sred_1 = sred_1 / n;
		n = 0;
		for(int i = 0; i < f2.size(); i++) {
			if(Load_CIMEL.DATE.get(i).equals(date)) {
				n++;
				sred_2 = sred_2 + f2.get(i);
			}
		}
		sred_2 = sred_2 / n;
		return sred_2 / sred_1;
	}
}


























































































































