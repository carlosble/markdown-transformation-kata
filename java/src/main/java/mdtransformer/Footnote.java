package mdtransformer;

import java.util.Objects;
import java.util.UUID;

public class Footnote {
    private final String textInPage;
    private final String anchor;
    private String uniqueId;

    public Footnote(String textInPage, String anchor) {
        this.textInPage = textInPage;
        this.anchor = anchor;
    }

    public String textInPage(){
        return String.format("%s [^anchor_%s]", textInPage, uniqueId());
    }

    public String anchor(){
        return String.format("[^anchor_%s]: %s", uniqueId(), anchor);
    }

    public String uniqueId(){
        if (uniqueId == null){
            uniqueId = UUID.randomUUID().toString();
        }
        return uniqueId;
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
