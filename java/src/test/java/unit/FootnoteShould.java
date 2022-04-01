package unit;

import mdtransformer.Footnote;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FootnoteShould {

    @Test
    void generate_valid_markdown_format_for_text_in_page_and_anchor() {
        Footnote footnote = new Footnote("some link", "url");

        assertThat(footnote.anchor()).matches("\\[\\^anchor_.*]: url");
        assertThat(footnote.textInPage()).matches("some link \\[\\^anchor_.*]");
    }

    @Test
    void use_the_same_unique_id_for_text_in_page_and_anchor() {
        Footnote footnote = new Footnote("some link", "url");

        assertThat(extractIdFromFootnote(footnote.anchor()))
                .isEqualTo(extractIdFromFootnote(footnote.textInPage()));
    }

    @Test
    void generate_unique_id_per_footnote() {
        Footnote footnote = new Footnote("some link", "url");
        Footnote footnote2 = new Footnote("some link", "url");

        assertThat(footnote.anchor()).isNotEqualTo(footnote2.anchor());
    }

    private String extractIdFromFootnote(String anchor) {
        return anchor.split("anchor_")[1].split("]")[0];
    }
}
