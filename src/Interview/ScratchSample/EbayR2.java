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
            if (currentSection == -1) {
                throw new IllegalArgumentException("Invalid hierarchy followed in input");
            }
            int eqPos = line.indexOf('=');
            String key = line.substring(0, eqPos).trim();
            String value = line.substring(eqPos + 1).trim();
            List<String[]> pairsTillNow = sectionKeyValue.get(currentSection);
            Set<String[]> keySet = new HashSet<>(pairsTillNow);
            if (keySet.contains(key)) {
                throw new IllegalArgumentException("Duplicate key in section: " + sectionName.getLast());
            }
            pairsTillNow.add(new String[]{key, value});
        }

        Map<String, Map<String, String>> res = new LinkedHashMap<>();
        for (int i = 0; i < sectionName.size(); i++) {
            Map<String, String> tempkv = new LinkedHashMap<>();
            for (String[] kv : sectionKeyValue.get(i)) {
                tempkv.put(kv[0], kv[1]);
}
}
}
}
