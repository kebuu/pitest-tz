package com.cta.pitest.tz;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@SuppressWarnings("ConstantConditions")
public class TestedClass {

    private static final Logger LOGGER = Logger.getLogger(TestedClass.class.getName());

    private int totalStringLength = 0;

    /**
     * This method print the sum of the given string list and the number of string with a length higher than 10.
     * If the string list contains
     * @return &quot;OK&quot; if one of the input string equals to &quot;Hello&quot;, &quot;KO&quot; otherwise
     */
    public String testedMethod(List<String> strings) {
        LOGGER.info("Calling testedMethod");
        setTotalStringLength(0);

        List<String> effectiveStrings = strings.stream()
            .filter(string -> !string.equals("Poison"))
            .filter(string -> !string.equals("Poison"))
            .collect(Collectors.toList());

        for (String string : effectiveStrings) {
            totalStringLength += string.length();
        }

        System.out.print("totalStringLength: " + totalStringLength);

        return calculateResult(effectiveStrings);

    }

    private String calculateResult(List<String> strings) {
        return containsHello(strings) ? "OK" : "KO";
    }

    private boolean containsHello(List<String> strings) {
        return strings.stream().anyMatch(string -> string.equals("Hello"));
    }

    public int getTotalStringLength() {
        return totalStringLength;
    }

    private void setTotalStringLength(int totalStringLength) {
        this.totalStringLength = totalStringLength;
    }
}
