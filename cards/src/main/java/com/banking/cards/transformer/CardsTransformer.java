package com.banking.cards.transformer;

import com.banking.cards.dto.CardsDto;
import com.banking.cards.entity.CardsEntity;

public class CardsTransformer {

    public static CardsDto toDto(CardsEntity pCardsEntity) {
        if(pCardsEntity == null) {
            return null;
        }
        return CardsDto.builder().cardNumber(pCardsEntity.getCardNumber()).cardType(pCardsEntity.getCardType()).mobileNumber(pCardsEntity.getMobileNumber()).totalLimit(pCardsEntity.getTotalLimit()).availableAmount(pCardsEntity.getAvailableAmount()).amountUsed(pCardsEntity.getAmountUsed()).build();
    }

    public static CardsEntity toEntity(CardsDto pCardsDto, CardsEntity pCards) {
        if(pCardsDto == null) {
            return null;
        }
        CardsEntity cards = pCards;
        if(pCards == null) {
            cards = new CardsEntity();
        }
        cards.setCardNumber(pCardsDto.getCardNumber());
        cards.setCardType(pCardsDto.getCardType());
        cards.setMobileNumber(pCardsDto.getMobileNumber());
        cards.setTotalLimit(pCardsDto.getTotalLimit());
        cards.setAvailableAmount(pCardsDto.getAvailableAmount());
        cards.setAmountUsed(pCardsDto.getAmountUsed());
        return cards;
    }
}
