package mdtransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkToFootnote {
    public List<Footnote> transformOfLinksIn(String line) {
        Pattern pattern = Pattern.compile("\\[(.+?)]\\((.+?)\\)");
        Matcher matcher = pattern.matcher(line);
        List<Footnote> footnotes = new ArrayList<>();
        while (matcher.find()) {
            String linkText = matcher.group(1);
            String linkUrl = matcher.group(2);
            footnotes.add(new Footnote(linkText, linkUrl));
        }
        return footnotes;
    }
}
