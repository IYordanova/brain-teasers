package brainteasers.strings;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PrettyJson {
    private final static String newLine = "\n";
    private final static String indentation = "\t";

    private static ArrayList<String> prettyJSON(String A) {
        char[] chars = A.toCharArray();
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        int numIndent = 0;
        for (char ch : chars) {
            if (ch == '{' || ch == '[') {
                if(!lines.isEmpty()) {
                    line = startNewLine(lines, line);
                }
                if(line.length() == 0) {
                    addIndentation(line, numIndent);
                }
                line.append(ch);
                line = startNewLine(lines, line);
                numIndent++;
            } else if (ch == '}' || ch == ']') {
                line = startNewLine(lines, line);
                numIndent--;
                if(line.length() == 0) {
                    addIndentation(line, numIndent);
                }
                line.append(ch);
                line = startNewLine(lines, line);
            } else if (ch == ',') {
                if(line.length() == 0) {
                    addIndentation(line, numIndent);
                }
                line.append(ch);
                line = startNewLine(lines, line);
            } else {
                if(line.length() == 0) {
                    addIndentation(line, numIndent);
                }
                line.append(ch);
            }
        }
        return lines.stream()
                .filter(l -> l.trim().length() != 0)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static StringBuilder startNewLine(ArrayList<String> lines, StringBuilder line) {
        line.append(newLine);
        lines.add(line.toString());
        line = new StringBuilder();
        return line;
    }

    private static void addIndentation(StringBuilder str, int numIndent) {
        str.append(indentation.repeat(Math.max(0, numIndent)));
    }


    public static void main(String[] args) {
        System.out.println(prettyJSON("{A:\"B\",C:{D:\"E\",F:{G:\"H\",I:\"J\"}}}"));
        System.out.println(prettyJSON("[\"foo\", {\"bar\":[\"baz\",null,1.0,2]}]"));
        System.out.println(prettyJSON("{\"id\":100,\"firstName\":\"Jack\",\"lastName\":\"Jones\",\"age\":12}"));
    }


}
