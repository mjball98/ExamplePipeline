package com.revature.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.Card;
import com.revature.repository.CardRepository;
import com.revature.repository.CardRepositoryImpl;

/*
 * Our service layer is reserved for our business logic. This makes it easier
 * to debug issues that may arise in our application. Typically, all data is passed
 * through the service layer of the application.
 */
public class CardService {

	/*
	 * Prior to today, we used the System class to "debug" issues by simply printing
	 * to the console. This isn't considered good practice. Instead, we will use
	 * some logging technology (in our case, Log4J2).
	 */

	//private static final Logger LOG = LogManager.getFormatterLogger(CardService.class);

	private CardRepository cardRepository;

	public CardService() {
		this.cardRepository = new CardRepositoryImpl();
	}

	/*
	 * This method has no business logic, but it is still appropriate to pass all of
	 * our data through this service layer as it scales well and guarantees that we
	 * do have some place to handle business logic.
	 */
	public void insert(Card card) {
		this.cardRepository.insert(card);
	}

	public List<Card> findAll() {
		return this.cardRepository.findAll();
	}

	public List<Card> findAllOmittingType(int id) {
		List<Card> allCards = this.cardRepository.findAll();

//		for(Card c : allCards) {
//			if(c.getType_id() != id) {
//				filteredCards.add(c);
//			}
//		}

		allCards.removeIf((c) -> c.getType_id() == id);

		//LOG.info("The number " + id + " was passed to the findallOmittingType method and " + "the output is: "
				//+ allCards);

		return allCards;
	}

}
