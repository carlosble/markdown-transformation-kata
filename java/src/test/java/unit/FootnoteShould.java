package unit;

import mdtransformer.Footnote;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FootnoteShould {
    @Test
    void have_a_unique_anchor_identifier() {
        Footnote footnote = new Footnote("some link", "url");
        Footnote footnote2 = new Footnote("some link", "url");

        assertThat(footnote.anchor()).matches("\\[\\^anchor_.*]: url");
        assertThat(footnote.textInPage()).matches("some link \\[\\^anchor_.*]");
        assertThat(extractIdFromFootnote(footnote.anchor()))
                .isEqualTo(extractIdFromFootnote(footnote.textInPage()));
        assertThat(footnote.anchor()).isNotEqualTo(footnote2.anchor());
    }

    private String extractIdFromFootnote(String anchor) {
        return anchor.split("anchor_")[1].split("]")[0];
    }
}
