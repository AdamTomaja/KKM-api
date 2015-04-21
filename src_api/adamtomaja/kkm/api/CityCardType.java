package adamtomaja.kkm.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Opisuje rodzaj karty
 * @author Adam Tomaja
 */
public class CityCardType {
	/**
	 * Krakowska Karta Miejska
	 */
	public static final int KKM = 0;
	
	
	public static final int WSZiB = 20;
	
	public static final int AGH = 21;
	
	public static final int UJ = 22;
	
	public static final int PK = 23;
	
	public static final int UE = 24; 
	
	public static final int UR = 25;
	
	public static final int PWST = 26;
	
	public static final int AM = 27;
	
	public static final int WSE = 28;
	
	public static final int AIK_WSFP = 29;
	
	public static final int UP = 30;
	
	public static final int WSH = 31;
	
	public static final int KA = 32;
	
	public static final int WSEI = 33;
	
	public static final int IFJ_PAN = 34;

	public static final int IF_PAN = 35;
	
	public static final int IKIFP_PAN = 36;
	
	/**
	 * Zawiera nazwy typów kart
	 */
	private static HashMap<Integer, String> idNameMap = new HashMap<Integer, String>();
	
	/**
	 * Inicjalizacja typów kart
	 */
	static {
		idNameMap.put(KKM, "Karta KKM");
		idNameMap.put(WSZiB, "Legitymacja WSZiB");
		idNameMap.put(AGH, "Legitymacja AGH");
		idNameMap.put(UJ, "Legitymacja UJ");
		idNameMap.put(PK, "Legitymacja PK");
		idNameMap.put(UE, "Legitymacja UE");
		idNameMap.put(UR, "Legitymacja UR");
		idNameMap.put(PWST, "Legitymacja PWST");
		idNameMap.put(AM, "Legitymacja AM");
		idNameMap.put(WSE, "Legitymacja WSE");
		idNameMap.put(AIK_WSFP, "Legitymacja AIK (WSFP)");
		idNameMap.put(UP, "Legitymacja UP");
		idNameMap.put(WSH, "Legitymacja WSH");
		
		idNameMap.put(KA, "Legitymacja KA");
		idNameMap.put(WSEI, "Legitymacja WSEI");
		idNameMap.put(IFJ_PAN, "Legitymacja IFJ PAN");
		idNameMap.put(IF_PAN, "Legitymacja IF PAN");
		idNameMap.put(IKIFP_PAN, "Legitymacja IKiFP PAN");
	}
	
	/**
	 * Tłumaczy numer typu karty na nazwę
	 * @param cityCardType - identyfikator typu karty
	 * @return nazwa typu karty
	 */
	public static String getTypeName(int cityCardType){
		return idNameMap.get(cityCardType);
	}
	

	/**
	 * Numer typu w obiekcie
	 */
	int cityCardtype;
	 
	public CityCardType(int cityCardType) {
		this.cityCardtype = cityCardType;
	}
	
	@Override 
	public String toString() {
		return getTypeName(this.cityCardtype);
	}
	
	/**
	 * Pobiera listę wszystkich typów kart
	 * @return
	 */
	public static ArrayList<CityCardType> getAllTypes() {
		ArrayList<CityCardType> result = new ArrayList<CityCardType>();
		result.add(new CityCardType(KKM)); //Chcemy my KKM był pierwszy na liście
		for(Integer type : idNameMap.keySet()) {
			if(type != KKM)
				result.add(new CityCardType(type));
		}
		
		return result;
	}
	
	/**
	 * Pobiera identyfikator typu
	 * @return identyfikator typu
	 */
	public int getTypeId() {
		return cityCardtype;
	}
	
}
