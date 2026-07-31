package Interview.ScratchSample;

import java.util.*;

public class EbayR2 {
    /*
     */
    // Time: O(n)
    // Space: O(n)
    public static Map<String, Map<String, String>> convert(String[] input) {
        List<String> sectionName = new ArrayList<>();
        List<List<String[]>> sectionKeyValue = new ArrayList<>();
        int currentSection = -1;

        for (String s : input) {
            String line = s.trim();
            if (line.isEmpty() || line.charAt(0) == ';' || line.charAt(0) == '#') continue;

            if (line.charAt(0) == '[' && line.charAt(line.length() - 1) == ']') {
                String temp = line.substring(1, line.length() - 1).trim();
                sectionName.add(temp);
                sectionKeyValue.add(new ArrayList<>());
                currentSection++;
                continue;
}
}
}
}
