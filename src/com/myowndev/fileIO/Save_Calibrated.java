package com.myowndev.fileIO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.myowndev.main.Calculator;

public class Save_Calibrated {
	
	private FileWriter writer;
	
	public Save_Calibrated() {
		File file = new File("res/calibrated.txt");
		try {
			writer = new FileWriter(file);
			writer.append("Data(ddmmyy),Time(hhmmss),K1(407nm;412nm),cc1,K2(439nm;440),cc2,K3(500nm;500nm),cc3,K4(551nm;551nm),cc4,K5(672nm;665nm),cc5,K6(1559nm;1640nm),cc6,K7(2139nm)"
					+ ",K8(777nm),K9(866nm),cc9,K10(932nm),K11(1045nm;1020nm),cc11,K12(?),K13(?),K14(?),K15(342nm;350nm),cc15,K16(367nm),K17(1240nm),Pres,Tpr,Hum,Thm,Tbrd,Ubat");
			for(int i = 0; i < Load_SP9.UBAT.size(); i++) {
				writer.append("\n" + Load_SP9.DATE.get(i) + "," + Load_SP9.TIME.get(i) + "," + Load_SP9.K1.get(i) + "," + Calculator.cc1.get(i) + "," + Load_SP9.K2.get(i) + "," + Calculator.cc2.get(i) + "," + Load_SP9.K3.get(i) + "," + Calculator.cc3.get(i) + "," + Load_SP9.K4.get(i) + "," + Calculator.cc4.get(i) + "," + Load_SP9.K5.get(i) + "," + Calculator.cc5.get(i) + "," + Load_SP9.K6.get(i) + "," + Calculator.cc6.get(i) + "," + Load_SP9.K7.get(i) + "," + Load_SP9.K8.get(i) + "," + Load_SP9.K9.get(i) + "," + Calculator.cc9.get(i) + "," + Load_SP9.K10.get(i) + "," + Load_SP9.K11.get(i) + "," + Calculator.cc11.get(i) + "," + Load_SP9.K12.get(i) + "," + Load_SP9.K13.get(i) + "," + Load_SP9.K14.get(i) + "," + Load_SP9.K15.get(i) + "," + Calculator.cc15.get(i) + "," + Load_SP9.K16.get(i) + "," + Load_SP9.K17.get(i) + "," + Load_SP9.PRES.get(i) + "," + Load_SP9.TPR.get(i) + "," + Load_SP9.HUM.get(i) + "," + Load_SP9.THM.get(i) + "," + Load_SP9.TBRD.get(i) + "," + Load_SP9.UBAT.get(i));
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}







































































































































