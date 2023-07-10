package io.github.thepragmaticsquad.fs.entity.creditcard;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class VisaCard implements CardService{
    Random random = new Random();
    @Override
    public String issueCreditCard() {
        return "5201-" + makeRandomNumber() + "-" + makeRandomNumber() + "-" + makeRandomNumber();
    }
    private String makeRandomNumber() {
        return String.valueOf(random.nextInt(1000, 9999));
    }

}
