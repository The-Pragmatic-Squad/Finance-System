package io.github.thepragmaticsquad.fs.entity.creditcard;

import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class MasterCard implements CardService {
    Random random = new Random();

    @Override
    public String issueCreditCard() {

        return "1234-" + makeRandomNumber() + "-" + makeRandomNumber() + "-" + makeRandomNumber();

    }

    private String makeRandomNumber() {
        return String.valueOf(random.nextInt(1000, 9999));
    }
}
