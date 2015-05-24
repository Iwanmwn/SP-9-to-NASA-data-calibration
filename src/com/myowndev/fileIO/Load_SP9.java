package com.myowndev.fileIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Load_SP9 {
	
	public static ArrayList<String> DATE = new ArrayList<String>();
	public static ArrayList<String> TIME = new ArrayList<String>();
	public static ArrayList<Float> K1 = new ArrayList<Float>();
	public static ArrayList<Float> K2 = new ArrayList<Float>();
	public static ArrayList<Float> K3 = new ArrayList<Float>();
	public static ArrayList<Float> K4 = new ArrayList<Float>();
	public static ArrayList<Float> K5 = new ArrayList<Float>();
	public static ArrayList<Float> K6 = new ArrayList<Float>();
	public static ArrayList<Float> K7 = new ArrayList<Float>();
	public static ArrayList<Float> K8 = new ArrayList<Float>();
	public static ArrayList<Float> K9 = new ArrayList<Float>();
	public static ArrayList<Float> K10 = new ArrayList<Float>();
	public static ArrayList<Float> K11 = new ArrayList<Float>();
	public static ArrayList<Float> K12 = new ArrayList<Float>();
	public static ArrayList<Float> K13 = new ArrayList<Float>();
	public static ArrayList<Float> K14 = new ArrayList<Float>();
	public static ArrayList<Float> K15 = new ArrayList<Float>();
	public static ArrayList<Float> K16 = new ArrayList<Float>();
	public static ArrayList<Float> K17 = new ArrayList<Float>();
	public static ArrayList<Float> PRES = new ArrayList<Float>();
	public static ArrayList<Float> TPR = new ArrayList<Float>();
	public static ArrayList<Float> HUM = new ArrayList<Float>();
	public static ArrayList<Float> THM = new ArrayList<Float>();
	public static ArrayList<Float> TBRD = new ArrayList<Float>();
	public static ArrayList<Float> UBAT = new ArrayList<Float>();
	
	public static String filedestination = null;
	
	public Load_SP9() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filedestination));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			String line;
			line = br.readLine();
			char[] chars;
			chars = line.toCharArray();
			while ((line = br.readLine()) != null) {
				chars = line.toCharArray();
				if (line.equals("165 165 165  165 165 165 ¤4294967295 ¤4294967295  65535  65535  65535  65535  65535  65535  65535  65535  65535  65535  65535  65535  65535  65535  65535  65535  65535   6554      0  65535     -1  65535  334.9")) {
					//System.out.println("End of adequate data in file");
					line = null; // ÷икл не завершает работу. »справить
				}
				int firstchar = 0;
				int lastchar = 0;
				int dob = 0;
				String tempStr = "";
				String tempData = "";
				for (int i = 0; i < chars.length; i++) {
					lastchar++;
					if(lastchar == 198) lastchar = 0;
					if(dob == 31) dob = 0;
					if (firstchar == 0) {
						tempStr = tempStr + chars[0] + chars[1];
						firstchar = lastchar;
					}
					if (chars[i] != ' ' && chars[i] != 'E' && chars[i] != 'N' && chars[i] != 'W' && chars[i] != 'S') {
						if (i != 0) if (chars[i - 1] == ' ' || chars[i - 1] == 'E' || chars[i - 1] == 'N' || chars[i - 1] == 'W' || chars[i - 1] == 'S') {
							firstchar = i;
						}
						if (((i - 1) == 1 || (i - 1) > 1) && i < 197) {
							if ((chars[firstchar - 1] == ' ' || chars[firstchar - 1] == 'E' || chars[firstchar - 1] == 'N' || chars[firstchar - 1] == 'W' && chars[firstchar - 1] == 'S' )
									&& (chars[lastchar] == ' ')) {
								for (int j = firstchar; j < lastchar; j++) {
									tempStr = tempStr + chars[j];
								}
								firstchar = lastchar;
							}
						}
					}
					if (lastchar == 197) {
						tempStr = tempStr + chars[196] + chars[197] + chars[198];
						firstchar = lastchar;
					}
					tempStr = tempStr.trim();
					if (tempStr != "") {
						if (dob == 0 || dob == 1 || dob == 2) {
							if(tempStr.length() == 1) {
								tempStr = "0" + tempStr;
							}
							tempData = tempData + tempStr;
							if(dob == 2) {
								DATE.add(tempData);
								tempData = "";
							}
						}
						if (dob == 3 || dob == 4 || dob == 5) {
							if (tempStr.length() == 1) {
								tempStr = "0" + tempStr;
							}
							tempData = tempData + tempStr;
							if (dob == 5) {
								TIME.add(tempData);
								tempData = "";
							}
						}
						if(dob == 8) { K1.add(Float.parseFloat(tempStr)); }
						if(dob == 9) { K2.add(Float.parseFloat(tempStr)); }
						if(dob == 10) { K3.add(Float.parseFloat(tempStr)); }
						if(dob == 11) { K4.add(Float.parseFloat(tempStr)); }
						if(dob == 12) { K5.add(Float.parseFloat(tempStr)); }
						if(dob == 13) { K6.add(Float.parseFloat(tempStr)); }
						if(dob == 14) { K7.add(Float.parseFloat(tempStr)); }
						if(dob == 15) { K8.add(Float.parseFloat(tempStr)); }
						if(dob == 16) { K9.add(Float.parseFloat(tempStr)); }
						if(dob == 17) { K10.add(Float.parseFloat(tempStr)); }
						if(dob == 18) { K11.add(Float.parseFloat(tempStr)); }
						if(dob == 19) { K12.add(Float.parseFloat(tempStr)); }
						if(dob == 20) { K13.add(Float.parseFloat(tempStr)); }
						if(dob == 21) { K14.add(Float.parseFloat(tempStr)); }
						if(dob == 22) { K15.add(Float.parseFloat(tempStr)); }
						if(dob == 23) { K16.add(Float.parseFloat(tempStr)); }
						if(dob == 24) { K17.add(Float.parseFloat(tempStr)); }
						if(dob == 25) { PRES.add(Float.parseFloat(tempStr)); }
						if(dob == 26) { TPR.add(Float.parseFloat(tempStr)); }
						if(dob == 27) { HUM.add(Float.parseFloat(tempStr)); }
						if(dob == 28) { THM.add(Float.parseFloat(tempStr)); }
						if(dob == 29) { TBRD.add(Float.parseFloat(tempStr)); }
						if(dob == 30) { UBAT.add(Float.parseFloat(tempStr)); }
						dob++;
					}
					tempStr = "";
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}











































































































