package com.chy.appleirbuyer;

import java.util.HashMap;
import java.util.Map;

public class CommonMapping {

	public static final Map<String, String> MODELMAP = new HashMap<String, String>() {
		{
			// "/" -> %2F
			put("256_Plus_JBlack", "MN4L2ZP%2FA");
			put("256_Plus_Black", "MN4E2ZP%2FA");
			put("256_Plus_Sliver", "MN4F2ZP%2FA");
			put("256_Plus_Gold", "MN4J2ZP%2FA");
			put("256_Plus_Pink", "MN4K2ZP%2FA");
			put("128_Plus_JBlack", "MN4D2ZP%2FA");
			put("128_Plus_Black", "MN482ZP%2FA");
			put("128_Plus_Sliver", "MN492ZP%2FA");
			put("128_Plus_Gold", "MN4A2ZP%2FA");
			put("128_Plus_Pink", "MN4C2ZP%2FA");
			put("32_Plus_Black", "MNQH2ZP%2FA");
			put("32_Plus_Sliver", "MNQJ2ZP%2FA");
			put("32_Plus_Gold", "MNQK2ZP%2FA");
			put("32_Plus_Pink", "MNQL2ZP%2FA");
			put("256_JBlack", "MN8Q2ZP%2FA");
			put("128_Black", "MN8W2ZP%2FA");
		}
	};

	public static final Map<String, String> STOREMAP = new HashMap<String, String>() {
		{
			put("FW", "R485");
			put("CWB", "R409");
			put("IFC", "R428");
			put("CR", "R499");
			put("NTP", "R610");
		}
	};

	public static final Map<String, String> GOVIDTYPEMAP = new HashMap<String, String>() {
		{
			put("idHongkongCard", "Hong Kong ID Card");
			put("macauIDCard", "Macau ID Card");
			put("travelPermitHKMacau", "Mainland Travel Permit for Hong Kong and Macao residents");
			put("entryExitPass", "Entry Exit Pass for Mainland and Macao Residents");
			put("passport", "Passport (International Visitors)");
			put("Hong Kong ID Card", "idHongkongCard");
			put("Macau ID Card", "macauIDCard");
			put("Mainland Travel Permit for Hong Kong and Macao residents", "travelPermitHKMacau");
			put("Entry Exit Pass for Mainland and Macao Residents", "entryExitPass");
			put("Passport (International Visitors)", "passport");
			put(null, "");
		}
	};
	
	public static final Map<String, String> ENGTIMENUMBERMAPPING = new HashMap<String, String>() {
		{
			put("9:00 AM - 9:30 AM", "09000930");
			put("9:30 AM - 10:00 AM", "09301000");
			put("10:00 AM - 10:30 AM", "10001030");
			put("10:30 AM - 11:00 AM", "10301100");
			put("11:00 AM - 11:30 AM", "11001130");
			put("11:30 AM - 12:00 PM", "11301200");
			put("12:00 PM - 12:30 PM", "12001230");
			put("12:30 PM - 1:00 PM", "12301300");
			put("1:00 PM - 1:30 PM", "13001330");
			put("1:30 PM - 2:00 PM", "13301400");
			put("2:00 PM - 2:30 PM", "14001430");
			put("2:30 PM - 3:00 PM", "14301500");
			put("3:00 PM - 3:30 PM", "15001530");
			put("3:30 PM - 4:00 PM", "15301600");
			put("4:00 PM - 4:30 PM", "16001630");
			put("4:30 PM - 5:00 PM", "16301700");
			put("5:00 PM - 5:30 PM", "17001730");
			put("5:30 PM - 6:00 PM", "17301800");
			put("6:00 PM - 6:30 PM", "18001830");
			put("6:30 PM - 7:00 PM", "18301900");
			put("7:00 PM - 7:30 PM", "19001930");
			put("7:30 PM - 8:00 PM", "19302000");
			put("8:00 PM - 8:30 PM", "20002030");
			put("8:30 PM - 9:00 PM", "20302100");
			put(null, "");
			put("", "");
		}
	};
	
	public static final Map<String, String> NUMBERENGTIMEMAPPING = new HashMap<String, String>() {
		{
			put("09000930", "9:00 AM - 9:30 AM");
			put("09301000", "9:30 AM - 10:00 AM");
			put("10001030", "10:00 AM - 10:30 AM");
			put("10301100", "10:30 AM - 11:00 AM");
			put("11001130", "11:00 AM - 11:30 AM");
			put("11301200", "11:30 AM - 12:00 PM");
			put("12001230", "12:00 PM - 12:30 PM");
			put("12301300", "12:30 PM - 1:00 PM");
			put("13001330", "1:00 PM - 1:30 PM");
			put("13301400", "1:30 PM - 2:00 PM");
			put("14001430", "2:00 PM - 2:30 PM");
			put("14301500", "2:30 PM - 3:00 PM");
			put("15001530", "3:00 PM - 3:30 PM");
			put("15301600", "3:30 PM - 4:00 PM");
			put("16001630", "4:00 PM - 4:30 PM");
			put("16301700", "4:30 PM - 5:00 PM");
			put("17001730", "5:00 PM - 5:30 PM");
			put("17301800", "5:30 PM - 6:00 PM");
			put("18001830", "6:00 PM - 6:30 PM");
			put("18301900", "6:30 PM - 7:00 PM");
			put("19001930", "7:00 PM - 7:30 PM");
			put("19302000", "7:30 PM - 8:00 PM");
			put("20002030", "8:00 PM - 8:30 PM");
			put("20302100", "8:30 PM - 9:00 PM");
			put(null, "");
			put("", "");
		}
	};
	
	public static final Map<String, String> NUMBERCHINESETIMEMAPPING = new HashMap<String, String>() {
		{
			put("09000930", "上午 9:00 - 上午 9:30");
			put("09301000", "上午 9:30 - 上午 10:00");
			put("10001030", "上午 10:00 - 上午 10:30");
			put("10301100", "上午 10:30 - 上午 11:00");
			put("11001130", "上午 11:00 - 上午 11:30");
			put("11301200", "上午 11:30 - 下午 12:00");
			put("12001230", "下午 12:00 - 下午 12:30");
			put("12301300", "下午 12:30 - 下午 1:00");
			put("13001330", "下午 1:00 - 下午 1:30");
			put("13301400", "下午 1:30 - 下午 2:00");
			put("14001430", "下午 2:00 - 下午 2:30");
			put("14301500", "下午 2:30 - 下午 3:00");
			put("15001530", "下午 3:00 - 下午 3:30");
			put("15301600", "下午 3:30 - 下午 4:00");
			put("16001630", "下午 4:00 - 下午 4:30");
			put("16301700", "下午 4:30 - 下午 5:00");
			put("17001730", "下午 5:00 - 下午 5:30");
			put("17301800", "下午 5:30 - 下午 6:00");
			put("18001830", "下午 6:00 - 下午 6:30");
			put("18301900", "下午 6:30 - 下午 7:00");
			put("19001930", "下午 7:00 - 下午 7:30");
			put("19302000", "下午 7:30 - 下午 8:00");
			put("20002030", "下午 8:00 - 下午 8:30");
			put("20302100", "下午 8:30 - 下午 9:00");
			put(null, "");
			put("", "");
		}
	};
}
