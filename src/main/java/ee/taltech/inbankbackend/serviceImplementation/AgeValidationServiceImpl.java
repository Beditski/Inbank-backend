package ee.taltech.inbankbackend.serviceImplementation;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import com.github.vladislavgoltjajev.personalcode.locale.estonia.EstonianPersonalCodeParser;
import ee.taltech.inbankbackend.config.DecisionEngineConstants;
import ee.taltech.inbankbackend.service.AgeValidationService;

public class AgeValidationServiceImpl implements AgeValidationService {
    private final EstonianPersonalCodeParser parser = new EstonianPersonalCodeParser();

    /**
     * Validates weather person is underage or older than the current expected
     * lifetime of each respectable country minus our maximum loan period. Return true
     * if validation is passed, returns false if not.
     * @param personalCode person's personal code
     * @return whether validation is passed or not.
     */
    public boolean  validationPassed(String personalCode) throws PersonalCodeException  {
        boolean notOverage = DecisionEngineConstants.AVG_LIFE_EXPECTANCY * 12 - DecisionEngineConstants.MAXIMUM_LOAN_PERIOD
                > parser.getAge(personalCode).getYears() * 12;
        boolean notUnderage = 18 <= parser.getAge(personalCode).getYears();
        return notOverage && notUnderage;
    }
}
