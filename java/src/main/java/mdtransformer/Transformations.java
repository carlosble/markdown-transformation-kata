package mdtransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Transformations {
    public List<Footnote> linkToFootNote(String line) {
        Pattern pattern = Pattern.compile("\\[(.+?)]\\((.+?)\\)");
        Matcher matcher = pattern.matcher(line);
        List<Footnote> footnotes = new ArrayList<>();
        int anchorCount = 1;
        while (matcher.find()) {
            footnotes.add(new Footnote(
                    String.format("%s [^anchor%s]", matcher.group(1), anchorCount),
                    String.format("[^anchor%s]: %s", anchorCount, matcher.group(2))));
            anchorCount++;
        }
        return footnotes;
    }
}
