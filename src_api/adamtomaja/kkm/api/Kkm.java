package adamtomaja.kkm.api;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Reprezentuje Krakowską Kartę Miejską
 * @author Adam Tomaja
 */
public class Kkm extends CityCard {

	private static final String CARD_NUMER_PARAMETER_NAME = "cityCardNumber";
	/**
	 * Nr Karty KKM
	 */
	private String cityCardNumber;
	
	/**
	 * 
	 * @param identityNumber - numer legitymacji/albumu
	 * @param cardNumber - numer karty kkm
	 */
	public Kkm(String identityNumber, String cardNumber) {
		super(CityCardType.KKM, identityNumber);
		this.cityCardNumber = cardNumber;
 	}
	
	/**
	 * <p>Zwraca tekstową reprezentację karty KKM w formacie: 
	 * Zwraca tekstową reprezentację karty w formacie: "Karta typu: [nazwa typu], id: [numer legitymacji], numer: [numer karty kkm]"</p>
	 * <p>Dopisuje "numer: [numer karty]" do wyniku działania funkcji toString klasy bazowej</p>
	 */
	@Override
	public String toString() {
		return String.format("%s, numer: %s", super.toString(), cityCardNumber);
	}
	
	/**
	 * Pobiera listę biletów
	 */
	@Override
	public ArrayList<Ticket> getTickets() throws IOException, ParseException, InvalidDataException {
		HashMap<String, String> addicionalParameters = new HashMap<String, String>();
		addicionalParameters.put(CARD_NUMER_PARAMETER_NAME, cityCardNumber);
		return getTickets(addicionalParameters);
	}
}
