package adamtomaja.kkm.api.tests;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import adamtomaja.kkm.api.CityCard;
import adamtomaja.kkm.api.InvalidDataException;
import adamtomaja.kkm.api.Kkm;
import adamtomaja.kkm.api.Ticket;
/**
 * Test klasy do obsługi typu Kkm
 * @author Adam Tomaja
 */
public class KkmTest {

	public static void main(String[] args) throws IOException, ParseException, InvalidDataException {
		CityCard kkm = new Kkm("12281404", "22308378042");

		ArrayList<Ticket> tickets = kkm.getTickets();
		
		System.out.println("Bilety: ");
		for(Ticket ticket : tickets) {
			System.out.println(ticket);
			System.out.println(ticket.endOfValidityDate);
		}
	}

}
