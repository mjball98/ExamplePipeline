package com.revature.repository;

import java.util.List;

import com.revature.model.Card;

public interface CardRepository {

	void insert(Card card);

	List<Card> findAll();

	Card findByName(String name);

	void update(Card card);

	void delete(Card card);
}
