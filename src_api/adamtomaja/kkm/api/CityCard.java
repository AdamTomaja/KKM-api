package adamtomaja.kkm.api;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstrakcja karty biletowej
 * @author Adam Tomaja
 *
 */
public abstract class CityCard {

	/**
	 * Typ karty
	 */
	int cityCardType;

	/**
	 * Numer legitymacji/albumu
	 */
	String identityNumber;
	
	/**
	 * @param cityCardType - typ karty
	 * @param identityNumber - numer legitymacji/albumu
	 */
	public CityCard(int cityCardType, String identityNumber){
		this.cityCardType = cityCardType;
		this.identityNumber = identityNumber;
	}
	
	/**
	 * Zwraca tekstową reprezentację karty w formacie: "Karta typu: [nazwa typu], id: [numer legitymacji]"
	 */
	@Override
	public String toString(){
		return String.format("Karta typu: %s, id: %s", CityCardType.getTypeName(cityCardType), identityNumber);
	}
	
	/**
	 * Pobiera listę biletów
	 * @return lista biletów
	 * @throws IOException - nie udało się pobrać danych z serwera
	 * @throws ParseException - przetwarzanie danych otrzymanych z serwera nie powiodło się
	 * @throws InvalidDataException - dane karty nie zostały znalezione
	 */
	protected ArrayList<Ticket> getTickets(HashMap<String, String> addicionalParameters) throws IOException, ParseException, InvalidDataException {
	 
		KkmApi api = new KkmApi();
		
		addicionalParameters.put("identityNumber", identityNumber);
		addicionalParameters.put("cityCardType", Integer.toString(cityCardType));
		String pageSource = api.get(addicionalParameters);
		
		return api.parseTickets(pageSource);
	}
	
	/**
	 * Pobiera listę biletów
	 * @return lista biletów
	 * @throws IOException - nie udało się pobrać danych z serwaera
	 * @throws ParseException - wystąpił błąd podczas przetwarzania źródła strony
	 * @throws InvalidDataException - dane karty nie zostały znalezione na serwerze
	 */
	public abstract ArrayList<Ticket> getTickets() throws IOException, ParseException, InvalidDataException;
	
}
