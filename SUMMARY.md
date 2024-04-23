## What intern did well:


- **Project and code structure** - the project structure is organized into different modules, which
makes it easier to maintain and scale the existing code base. Written classes and methods are in general well-defined,
short, logical and readable.
- **Good use of OOP principles** - intern has a good  basic understanding of OOP principles. Methods accessibility is correct:
helper methods of the DecisionEngine have private access. Variable fields are private and when
needed getter and setter methods are added using project lombok instead of writing them manually.
- **Documentation** - all methods and classes are well-documented. Provided method and class-level documentation 
ensures the intent, logic, and functions of the code, reducing the risk of misinterpretation and costly errors.
- **Error handling** - Error handling logic is well-designed. The code includes error handling with custom
exceptions for different types of input validation errors
- **Unit testing** - even though there is no specific information about who implemented the unit tests, they are 
well-implemented and seem to cover most of the possible cases.

  
## Places for improvement:
- **Minor issues related to the code logic** - in a few places code can be simplified (e.g. `DecisionEngine` class: if-statements in `verifyInput` method can
be simplified  and `loanPeriod` can be calculated in place, instead of using while-loop).
- **Project structure** - even though project structure is organized into different modules, there is a place for adjustments in
order to follow **SOLID** principles and separate **data transfer objects (dto)** into a separate module.
- **SOLID principles** - `DecisionEngine` class violates several **SOLID** design principles. About it and implemented solution in the next section.

## Violation of SOLID principles
The most important shortcoming of TICKET-101 given implementation is lack of following **SOLID** principles.
Even though current code structure seems logical and maintainable it may not be such in the future. In this section is decribed
the violation of **SOLID** principles, potential danger and implementation of the solution.
### 1. Single Responsibility Principle (SRP)
- **Violation** - the `DecisionEngine` class currently handles multiple functions, including validating inputs, 
calculating the credit modifier based on the personal code, and determining the loan decision. This mixes business logic with validation logic.
- **Consequence** - this mixing makes `DecisionEngine` class harder to maintain and modify in the perspective because changes in one responsibility (like validation logic) could affect other unrelated parts of the class.
- **Solution** - refactor by creating separate classes for input validation and decision calculation.

### 2. Open/Closed Principle (OCP)
- **Violation** -  further modifications related to business rules require altering the existing `DecisionEngine` code.
- **Consequence** - modifying the loan rules or adding new types of validations would require changes to the existing code base, potentially introducing bugs in previously tested code.
- **Solution** - create an interfaces that allows for extending and modifying functionality (like different types of validators or decision strategies) without modifying the existing code.

### 3. (LSP), 4. (ISP), 5. (DIP)
- Not applicable at the given stage of project development.
  

## Summary
- Fixed minor issues related to the code logic.
- Refactoring with the folowing project structure:

``` 
java/
├── config/
│   └── DecisionEngineConstants.java        
│
├── dao/
│   └──
│
├── endpoint/
│   └── DecisionEngineController.java # REST controller to handle decision-related incoming request
│
├── exception/
│   ├── InvalidLoanAmountException.java
│   ├── InvalidLoanPeriodException.java
│   └── InvalidPersonalCodeException.java
│
├── model/
│   ├── DecisionRequest.java          # Model for incoming decision request data
│   ├── DecisionResponse.java         # Model for outgoing decision response data
│   └── Customer.java                 # Model representing a customer or borrower
│
├── service/
│   ├── DecisionEngine.java           # Interface for decision-making logic
│   └── ValidationService.java        # Interface for validation logic
│
├── repository/
│   └──
│
├── serviceImplementation
│   ├── DecisionEngineImpl.java       # Implements DecisionEngine
│   ├── LoanValidationService.java    # Implements ValidationService, handles all validation tasks
│   └── CreditCalculationService.java # Service for calculating credit-related metrics
│
└── InbankBackendApplication.java     # Main class
```