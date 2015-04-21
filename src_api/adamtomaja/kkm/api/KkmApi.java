package adamtomaja.kkm.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.CharBuffer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;
import java.util.jar.Pack200.Unpacker;

/**
 * Powłoka obsługi danych ze strony.
 * Zawiera
 * @author Adam Tomaja
 *
 */
public class KkmApi {

	/**
	 * Format samej daty
	 */
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * Format daty i godziny
	 */
	private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd h:m");
	
	/**
	 * Kodowanie używane do enkodowania parametrów
	 */
	private static final String CHARSET = "UTF-8";
	
	/**
	 * Bazowy adres, który jest zarówno formatem dla metody String.format.
	 * To z niego są pobierane dane.
	 */
	private static final String baseUrlFormat = "http://www.kkm.krakow.pl/"
			+ "pl/sprawdz-waznosc-biletow-zapisanych-na-karcie/"
			+ "index,1.html?"
			+ "%s"
			+ "&sprawdz_kkm=Sprawdź";
	
	/**
	 * Oczyszcza linię z parametrem biletu ze zbędnych elementów
	 * @param propertyLine
	 * @return
	 */
	private String clearProperty(String propertyLine) {
		String[] splitted = propertyLine.split(":");
		if(splitted.length > 2)
			splitted[1] += ":" + splitted[2];
		return splitted[1].replace("<b>", "").replace("</div>", "").replace("</b>", "").trim();
	}
	
	/**
	 * Przetwarza źródło strony
	 * @param pageSource - źródło strony z biletami
	 * @return lista biletów
	 * @throws ParseException - wystąpił błąd podczas przetwarzania źródła strony
	 * @throws InvalidDataException 
	 */
	public ArrayList<Ticket> parseTickets(String pageSource) throws ParseException, InvalidDataException {
		if(pageSource.contains("Wprowadzone dane są nieprawidłowe."))
			throw new InvalidDataException("Wprowadzone dane są nieprawidłowe");
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		
		Ticket ticket = null;
		String [] lines = pageSource.split(System.lineSeparator());
		for(String line : lines ) {
			if(line.contains("Rodzaj biletu:")) {
				ticket = new Ticket();
				ticket.type = clearProperty(line);
			} else if(line.contains("Linie strefowe:")) {
				if(ticket == null)
					throw new ParseException("Brak poczatku biletu", 0);				
				ticket.regionLines = clearProperty(line);
 				tickets.add(ticket);
			}  else if(line.contains("Data początku ważności:")) {
				ticket.startOfValidityDate = DATE_FORMAT.parse(clearProperty(line)); 
				ticket.startOfValidityDate.setHours(23);
				ticket.startOfValidityDate.setSeconds(59);
				ticket.startOfValidityDate.setSeconds(59);
			} else if(line.contains("Data końca ważności:")) {
				ticket.endOfValidityDate = DATE_FORMAT.parse(clearProperty(line)); 
				ticket.endOfValidityDate.setHours(23);
				ticket.endOfValidityDate.setSeconds(59);
				ticket.endOfValidityDate.setSeconds(59);
			} else if(line.contains("Linie miejskie:"))
				ticket.cityLines = clearProperty(line);
			else if(line.contains("Data zwrotu:"))
				ticket.returnDate = clearProperty(line);
			else if(line.contains("Numer legitymacji:"))
				ticket.identifyNumber = clearProperty(line);
			else if(line.contains("Numer karty KKM:"))
				ticket.cityCardNumber = clearProperty(line);
			else if(line.contains("Data i godzina zakupu:"))
				ticket.purshaseDate = DATE_TIME_FORMAT.parse(clearProperty(line));
			else if(line.contains("Cena:"))
				ticket.price = clearProperty(line).replace("<!--ToString(\"%dd.dd\") -->", "");
		}
		
		System.out.println(tickets.size());
		
//		Test
//		Ticket ticket1 = new Ticket();
//		ticket1.endOfValidityDate = DATE_FORMAT.parse("2015-3-02");
//		tickets.add(ticket1);
		
		return tickets;
	}
	
	/**
	 * Pobiera stronę z biletami
	 * @param arguments - hashmapa z argumentami. Możliwe parametry na dzien 2 lutego 2015: 
	 * <ul>
	 * 	<li>cityCardType - typ karty</li>
	 * 	<li>dateValidity - data ? </li>
	 * 	<li>identityNumber - numer legitymacji</li>
	 * 	<li>cityCardNumber - numer karty kkm ( tylko typ KKM )</li>
	 * </ul>
	 * @return kod źródłowy strony
	 * @throws IOException - błąd pobierania
	 */
	public String get(HashMap<String, String> arguments) throws IOException {
		StringBuilder result = new StringBuilder();
		
		//Prepare URL
		StringBuilder argumentsBuilder = new StringBuilder();
		
		int count = 0;
		for(String key : arguments.keySet()) { 
			argumentsBuilder.append(String.format("%s=%s", URLEncoder.encode(key, CHARSET) , URLEncoder.encode(arguments.get(key), CHARSET)));
			count++;
			if(count < arguments.size())
				argumentsBuilder.append('&');
		}
		
		URL url = new URL(String.format(baseUrlFormat, argumentsBuilder.toString()));
		
		System.out.println(url.toString());
		
		//Download page
		InputStream inputStream = null;
		BufferedReader bufferedReader;
		InputStreamReader isReader;
		String line;
		
		inputStream = url.openStream();		
		bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		while((line = bufferedReader.readLine()) != null) {
			result.append(line);
			result.append(System.lineSeparator());
		}
		
		return result.toString();
	}
}
