package io.github.thepragmaticsquad.fs.entity.creditcard;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CardIssuer {

    private final CardService cardService;

    public CardIssuer(@Qualifier("visaCard") CardService cardService) {
        this.cardService = cardService;
    }
    public String issueCreditCard() {
        return cardService.issueCreditCard();
    }
}
