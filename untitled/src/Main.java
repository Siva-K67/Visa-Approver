import model.*;
import repository.RuleLoader;
import repository.RuleRepository;
import service.VisaEvaluator;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        // -------- DESTINATION COUNTRY --------
        System.out.println("Select Destination Country:");
        Country destination = selectCountry(scanner);

        // -------- PASSPORT COUNTRY --------
        System.out.println("\nSelect Passport Country:");
        Country passport = selectCountry(scanner);

        // -------- TRAVEL PURPOSE --------
        System.out.println("\nSelect Travel Purpose:");
        TravelPurpose purpose = selectPurpose(scanner);

        // -------- STAY DURATION --------
        System.out.print("\nEnter stay duration (days): ");
        int days = scanner.nextInt();

        TravelRequest request =
                new TravelRequest(destination, passport, purpose, days);

        List<VisaRule> rules = RuleLoader.loadRules("untitled/visa-rules.json");
        RuleRepository repository = new RuleRepository(rules);
        VisaEvaluator evaluator = new VisaEvaluator(repository);

        VisaDecision decision = evaluator.evaluate(request);

        System.out.println("\n===== VISA DECISION =====");
        System.out.println("Visa required: " + decision.isVisaRequired());
        System.out.println("Visa type: " + decision.getVisaType());
        System.out.println("Warnings: " + decision.getWarnings());
    }

    // ---------------- HELPERS ----------------

    private static Country selectCountry(Scanner scanner) {
        Country[] countries = Country.values();

        for (int i = 0; i < countries.length; i++) {
            System.out.println((i + 1) + ". " + countries[i]);
        }

        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();

        return countries[choice - 1];
    }

    private static TravelPurpose selectPurpose(Scanner scanner) {
        TravelPurpose[] purposes = TravelPurpose.values();

        for (int i = 0; i < purposes.length; i++) {
            System.out.println((i + 1) + ". " + purposes[i]);
        }

        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();

        return purposes[choice - 1];
    }
}
