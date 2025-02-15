package com.banking.cards.service.impl;

import com.banking.cards.contants.CardsConstants;
import com.banking.cards.dto.CardsDto;
import com.banking.cards.entity.CardsEntity;
import com.banking.cards.exception.CardAlreadyExistsException;
import com.banking.cards.exception.ResourceNotFoundException;
import com.banking.cards.repository.CardsRepository;
import com.banking.cards.service.ICardsService;
import com.banking.cards.transformer.CardsTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class CardsService implements ICardsService {

    @Autowired
    private CardsRepository cardsRepository;

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<CardsEntity> optionalCards= cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    /**
     * @param mobileNumber - Input mobile Number
     * @return Card Details based on a given mobileNumber
     */
    @Override
    public CardsDto fetchCard(String mobileNumber) {
        CardsEntity cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardsTransformer.toDto(cards);
    }

    /**
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        CardsEntity cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        CardsTransformer.toEntity(cardsDto, cards);
        cardsRepository.save(cards);
        return  true;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of card details is successful or not
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        CardsEntity cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new card details
     */
    private CardsEntity createNewCard(String mobileNumber) {

        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        return CardsEntity.builder().cardNumber(Long.toString(randomCardNumber)).mobileNumber(mobileNumber).cardType(CardsConstants.CREDIT_CARD).totalLimit(CardsConstants.NEW_CARD_LIMIT).amountUsed(0).availableAmount(CardsConstants.NEW_CARD_LIMIT).build();
    }
}
