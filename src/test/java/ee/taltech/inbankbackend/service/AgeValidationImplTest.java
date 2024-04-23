package ee.taltech.inbankbackend.service;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import ee.taltech.inbankbackend.config.DecisionEngineConstants;
import ee.taltech.inbankbackend.exceptions.InvalidLoanAmountException;
import ee.taltech.inbankbackend.exceptions.InvalidLoanPeriodException;
import ee.taltech.inbankbackend.exceptions.InvalidPersonalCodeException;
import ee.taltech.inbankbackend.exceptions.NoValidLoanException;
import ee.taltech.inbankbackend.model.Decision;
import ee.taltech.inbankbackend.serviceImplementation.AgeValidationServiceImpl;
import ee.taltech.inbankbackend.serviceImplementation.DecisionEngineImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AgeValidationImplTest {

    @InjectMocks
    private final AgeValidationServiceImpl ageValidationService = new AgeValidationServiceImpl();

    private String debtorPersonalCode;
    private String segment1PersonalCode;
    private String segment2PersonalCode;
    private String segment3PersonalCode;

    @BeforeEach
    void setUp() {
        debtorPersonalCode = "37605030299";
        segment1PersonalCode = "50307172740";
        segment2PersonalCode = "38411266610";
        segment3PersonalCode = "10202108849";
    }

    @Test
    void testAgeValidationPassed() throws PersonalCodeException {
        assertTrue(ageValidationService.validationPassed("37605030299"));
        assertTrue(ageValidationService.validationPassed("50307172740"));
        assertTrue(ageValidationService.validationPassed("38411266610"));
        assertFalse(ageValidationService.validationPassed("10202108849"));
    }

}
