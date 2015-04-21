package adamtomaja.kkm.api;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class Legitimation extends CityCard {

	public Legitimation(int cityCardType, String identityNumber) {
		super(cityCardType, identityNumber);
	}

	@Override
	public ArrayList<Ticket> getTickets() throws IOException, ParseException,
			InvalidDataException {
		HashMap<String, String> parameters = new HashMap<String, String>();
		return getTickets(parameters);
	}

}
