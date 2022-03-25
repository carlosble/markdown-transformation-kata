package mdtransformer;

import java.util.Objects;

public class Footnote {
    private final String textInPage;
    private final String anchor;

    public Footnote(String textInPage, String anchor) {
        this.textInPage = textInPage;
        this.anchor = anchor;
    }

    public String textInPage(int anchorCount) {
        return String.format("%s [^anchor%s]", textInPage, anchorCount);
    }

    public String anchor(int anchorCount){
        return String.format("[^anchor%s]: %s", anchorCount, anchor);
    }


    @Override
    public String toString() {
        return "Footnote{" +
                "textInPage='" + textInPage + '\'' +
                ", anchor='" + anchor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Footnote footnote = (Footnote) o;
        return textInPage.equals(footnote.textInPage) && anchor.equals(footnote.anchor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textInPage, anchor);
    }


}
