package repository;

import model.Country;
import model.TravelPurpose;
import model.VisaRule;
import model.VisaType;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RuleLoader {

    public static List<VisaRule> loadRules(String filePath) throws Exception {

        String content = Files.readString(Paths.get(filePath));

        // remove [ and ]
        content = content.trim();
        content = content.substring(1, content.length() - 1);

        // split individual rule blocks
        String[] ruleBlocks = content.split("\\},\\s*\\{");

        List<VisaRule> rules = new ArrayList<>();

        for (String block : ruleBlocks) {

            String ruleText = block.replace("{", "").replace("}", "");

            Optional<Country> destination = extractEnum(ruleText, "destinationCountry", Country.class);
            Optional<TravelPurpose> purpose = extractEnum(ruleText, "travelPurpose", TravelPurpose.class);
            Optional<Integer> minStay = extractInt(ruleText, "minStayDays");

            boolean visaRequired = Boolean.parseBoolean(extractValue(ruleText, "visaRequired"));
            VisaType visaType = VisaType.valueOf(extractValue(ruleText, "visaType"));

            List<String> warnings = extractWarnings(ruleText);

            rules.add(
                    new VisaRule(
                            destination,
                            purpose,
                            minStay,
                            visaRequired,
                            visaType,
                            warnings
                    )
            );
        }

        return rules;
    }

    // -------- helpers --------

    private static String extractValue(String text, String key) {
        int idx = text.indexOf("\"" + key + "\"");
        if (idx == -1) return null;

        int colon = text.indexOf(":", idx);
        int comma = text.indexOf(",", colon);
        if (comma == -1) comma = text.length();

        return text.substring(colon + 1, comma)
                .replace("\"", "")
                .trim();
    }

    private static Optional<Integer> extractInt(String text, String key) {
        String value = extractValue(text, key);
        return value == null ? Optional.empty() : Optional.of(Integer.parseInt(value));
    }

    private static <E extends Enum<E>> Optional<E> extractEnum(
            String text,
            String key,
            Class<E> enumType
    ) {
        String value = extractValue(text, key);
        return value == null ? Optional.empty() : Optional.of(Enum.valueOf(enumType, value));
    }

    private static List<String> extractWarnings(String text) {
        List<String> warnings = new ArrayList<>();

        int idx = text.indexOf("\"warnings\"");
        if (idx == -1) return warnings;

        int start = text.indexOf("[", idx);
        int end = text.indexOf("]", start);

        String inside = text.substring(start + 1, end);
        String[] parts = inside.split(",");

        for (String p : parts) {
            warnings.add(p.replace("\"", "").trim());
        }

        return warnings;
    }
}
