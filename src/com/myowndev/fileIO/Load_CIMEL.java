package com.myowndev.fileIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Load_CIMEL {
	
	public static ArrayList<String> DATE = new ArrayList<String>();
	public static ArrayList<String> TIME = new ArrayList<String>();
	public static ArrayList<Float> AOT_1640 = new ArrayList<Float>();
	public static ArrayList<Float> AOT_1020 = new ArrayList<Float>();
	public static ArrayList<Float> AOT_870 = new ArrayList<Float>();
	public static ArrayList<Float> AOT_675 = new ArrayList<Float>();
	public static ArrayList<Float> AOT_667 = new ArrayList<Float>();
	public static ArrayList<Float> AOT_555 = new ArrayList<Float>();
	public static ArrayList<Float> AOT_551 = new ArrayList<Float>();
	public static ArrayList<Float> AOT_532 = new ArrayList<Float>();
	public static ArrayList<Float> AOT_531 = new ArrayList<Float>();
	public static ArrayList<Float> AOT_500 = new ArrayList<Float>();
	public static ArrayList<Float> AOT_490 = new ArrayList<Float>();
	public static ArrayList<Float> AOT_443 = new ArrayList<Float>();
	public static ArrayList<Float> AOT_440 = new ArrayList<Float>();
	public static ArrayList<Float> AOT_412 = new ArrayList<Float>();
	public static ArrayList<Float> AOT_380 = new ArrayList<Float>();
	public static ArrayList<Float> AOT_340 = new ArrayList<Float>();
	public static ArrayList<Float> WATER_CM = new ArrayList<Float>();
	
	public static String filedestination = null;
	
	public Load_CIMEL() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filedestination));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			String line;
			line = br.readLine();
			line = br.readLine();
			line = br.readLine();
			line = br.readLine();
			line = br.readLine();
			char[] chars;
			chars = line.toCharArray();
			while((line = br.readLine()) != null) {
				chars = line.toCharArray();
				int dob = 0;
				String tempStr = "";
				for (int i = 0; i < chars.length; i++) {
					if(chars[i] != ',') {
						tempStr = tempStr + chars[i];
					}
					if(chars[i] == ',') {
						int pos = 0;
						for (int t = 0; t < tempStr.length(); t++) {
							if(tempStr.charAt(t) == ':') {
								pos = t;
								tempStr = tempStr.substring(0, pos) + tempStr.substring(pos + 1);
							}
							pos = 0;
						}
						if(tempStr.equals("N/A")) tempStr = "0";
						if(dob == 0) {
							tempStr = tempStr.substring(0, 4) + tempStr.substring(6);
							DATE.add(tempStr);
						}
						if(dob == 1) TIME.add(tempStr);
						if(dob == 3) AOT_1640.add(Float.parseFloat(tempStr));
						if(dob == 4) AOT_1020.add(Float.parseFloat(tempStr));
						if(dob == 5) AOT_870.add(Float.parseFloat(tempStr));
						if(dob == 6) AOT_675.add(Float.parseFloat(tempStr));
						if(dob == 7) AOT_667.add(Float.parseFloat(tempStr)); // N/A
						if(dob == 8) AOT_555.add(Float.parseFloat(tempStr)); // N/A
						if(dob == 9) AOT_551.add(Float.parseFloat(tempStr)); // N/A
						if(dob == 10) AOT_532.add(Float.parseFloat(tempStr)); // N/A
						if(dob == 11) AOT_531.add(Float.parseFloat(tempStr)); // N/A
						if(dob == 12) AOT_500.add(Float.parseFloat(tempStr));
						if(dob == 13) AOT_490.add(Float.parseFloat(tempStr)); // N/A
						if(dob == 14) AOT_443.add(Float.parseFloat(tempStr)); // N/A
						if(dob == 15) AOT_440.add(Float.parseFloat(tempStr));
						if(dob == 16) AOT_412.add(Float.parseFloat(tempStr)); // N/A
						if(dob == 17) AOT_380.add(Float.parseFloat(tempStr));
						if(dob == 18) AOT_340.add(Float.parseFloat(tempStr));
						if(dob == 19) WATER_CM.add(Float.parseFloat(tempStr));
						dob++;
						tempStr = "";
						if (dob == 44) dob = 0;
					}
				}
			}
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}


















































