package ee.taltech.inbankbackend.service;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;

public interface AgeValidationService {
    boolean validationPassed(String personalCode) throws PersonalCodeException;
}
