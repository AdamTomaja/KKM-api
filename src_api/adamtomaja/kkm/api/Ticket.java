package adamtomaja.kkm.api;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Reprezentuje pojedynczy bilet użytkownika
 * @author Adam Tomaja
 */
public class Ticket {
	/**
	 * Format daty
	 */
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * Format daty i godziny
	 */
	private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd h:m");
	
	/**
	 * Koniec ważności biletu
	 */
	public Date endOfValidityDate;
	/**
	 * Początek ważności biletu
	 */
	public Date startOfValidityDate;
	/**
	 * Linie miejskie
	 */
	public String cityLines;
	/**
	 * Linie strefowe
	 */
	public String regionLines;
	/**
	 * Typ (ulgowy/normalny)
	 */
	public String type;
	/**
	 * Data zakupu
	 */
	public Date purshaseDate;
	/**
	 * Numer legitymacji
	 */
	public String identifyNumber;
	/**
	 * Numer krakowskiej karty miejskiej
	 */
	public String cityCardNumber;
	/**
	 * Cena
	 */
	public String price;
	/**
	 * Data zwrotu
	 */
	public String returnDate;
	
	/**
	 * Sprawdza ważność biletu
	 * @return <b>true</b> - jeśli bilet jest ważny<br><b>false</b> - bilet nieważny
	 */
	public boolean isValid() {
		return endOfValidityDate.after(new Date());
	}
	
	/**
	 * Zwraca ilość dni pozostałych do końca ważności biletu
	 * @return -1 jeśli bilet jest nieważny
	 */
	public int getDaysToEnd() {
		if(isValid()) {
			long diff = endOfValidityDate.getTime() - (new Date().getTime());
			return (int) Math.floor(diff / (1000 * 60 * 60 * 24));
		} else 
			return -1;
	}
	/**
	 * Zwraca wszystkie informacje o bilecie w postaci tekstowej
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(String.format("========Bilet=======%s", System.lineSeparator()));
		result.append(String.format("Rodzaj biletu: %s%s", type, System.lineSeparator()));
		result.append(String.format("Data i godzina zakupu: %s%s", DATE_TIME_FORMAT.format(purshaseDate), System.lineSeparator()));
		result.append(String.format("Numer legitymacji: %s%s", identifyNumber, System.lineSeparator()));
		result.append(String.format("Numer karty kkm: %s%s", cityCardNumber, System.lineSeparator()));
		result.append(String.format("Data początku ważności: %s%s", DATE_FORMAT.format(startOfValidityDate), System.lineSeparator()));
		result.append(String.format("Data końca ważności: %s%s",  DATE_FORMAT.format(endOfValidityDate), System.lineSeparator()));
		result.append(String.format("Data zwrotu: %s%s", returnDate, System.lineSeparator()));
		result.append(String.format("Linie miejskie: %s%s", cityLines, System.lineSeparator()));
		result.append(String.format("Linie strefowe: %s%s", regionLines, System.lineSeparator()));

		return result.toString();
	}
}
