package unit;

import mdtransformer.Footnote;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FootnoteShould {
    @Test
    void have_a_unique_anchor_identifier() {
        Footnote footnote = new Footnote("some link", "url");
        Footnote footnote2 = new Footnote("some link", "url");

        assertThat(footnote.anchor()).matches("^\\[\\^anchor_\\s+]: url$");
        //assertThat(footnote.anchor()).matches("\\[^anchor\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}\\b]: url");
        assertThat(footnote.textInPage()).matches("some link \\[\\^anchor\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}\\b]");
        //todo check same id
        assertThat(footnote.anchor()).isNotEqualTo(footnote2.anchor());
        assertThat(footnote.textInPage()).isNotEqualTo(footnote2.textInPage());
    }
}
