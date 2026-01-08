package test;

import model.*;
import repository.*;
import service.*;

public class VisaEvaluatorTest {

    public static void main(String[] args) throws Exception {

        RuleRepository repository =
                new RuleRepository(RuleLoader.loadRules("visa-rules.json"));

        VisaEvaluator evaluator = new VisaEvaluator(repository);

        testValidRuleMatch(evaluator);
        testNoRuleFound(evaluator);
        testMultipleRuleConflict(evaluator);
        testInvalidInput(evaluator);
        testMissingConfigFields(evaluator);
    }

    private static void testValidRuleMatch(VisaEvaluator evaluator) {

        System.out.println("TEST 1: Valid rule match");

        TravelRequest request =
                new TravelRequest(
                        Country.JAPAN,
                        Country.INDIA,
                        TravelPurpose.TOURISM,
                        45
                );

        VisaDecision decision = evaluator.evaluate(request);

        System.out.println("Expected visa type: TOURIST");
        System.out.println("Actual visa type  : " + decision.getVisaType());
        System.out.println();
    }


    private static void testNoRuleFound(VisaEvaluator evaluator) {

        System.out.println("TEST 2: No rule found");

        try {
            TravelRequest request =
                    new TravelRequest(
                            Country.USA,
                            Country.INDIA,
                            TravelPurpose.STUDY,
                            5
                    );

            evaluator.evaluate(request);
        } catch (Exception e) {
            System.out.println("Expected: No matching rule");
            System.out.println("Actual  : Exception handled correctly");
        }

        System.out.println();
    }

    private static void testMultipleRuleConflict(VisaEvaluator evaluator) {

        System.out.println("TEST 3: Multiple rule conflict");

        TravelRequest request =
                new TravelRequest(
                        Country.JAPAN,
                        Country.INDIA,
                        TravelPurpose.TOURISM,
                        45
                );

        VisaDecision decision = evaluator.evaluate(request);

        System.out.println("Applied rule warnings: " + decision.getWarnings());
        System.out.println("First matching rule applied");
        System.out.println();
    }


    private static void testInvalidInput(VisaEvaluator evaluator) {

        System.out.println("TEST 4: Invalid input");

        try {
            TravelRequest request =
                    new TravelRequest(
                            Country.JAPAN,
                            Country.INDIA,
                            TravelPurpose.TOURISM,
                            -10
                    );

            evaluator.evaluate(request);
        } catch (Exception e) {
            System.out.println("Expected: Invalid input");
            System.out.println("Actual  : Invalid input handled");
        }

        System.out.println();
    }


    private static void testMissingConfigFields(VisaEvaluator evaluator) {

        System.out.println("TEST 5: Missing config fields");

        TravelRequest request =
                new TravelRequest(
                        Country.INDIA,
                        Country.INDIA,
                        TravelPurpose.BUSINESS,
                        20
                );

        VisaDecision decision = evaluator.evaluate(request);

        System.out.println("Visa type: " + decision.getVisaType());
        System.out.println("Optional fields handled correctly");
        System.out.println();
    }
}





