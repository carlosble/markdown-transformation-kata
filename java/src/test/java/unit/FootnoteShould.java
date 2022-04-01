package unit;

import mdtransformer.Footnote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FootnoteShould {

    private Footnote footnote;

    @BeforeEach
    public void setUp() {
        footnote = new Footnote("some link", "url");
    }

    @Test
    void generate_valid_markdown_format_for_text_in_page_and_anchor() {
        assertThat(footnote.anchor()).matches("\\[\\^anchor_.*]: url");
        assertThat(footnote.textInPage()).matches("some link \\[\\^anchor_.*]");
    }

    @Test
    void use_the_same_unique_id_for_text_in_page_and_anchor() {
        assertThat(extractIdFromFootnote(footnote.anchor()))
                .isEqualTo(extractIdFromFootnote(footnote.textInPage()));
    }

    @Test
    void generate_unique_id_per_footnote() {
        Footnote duplicatedFootnote = new Footnote("some link", "url");
        assertThat(this.footnote.anchor()).isNotEqualTo(duplicatedFootnote.anchor());
    }

    private String extractIdFromFootnote(String anchor) {
        return anchor.split("anchor_")[1].split("]")[0];
    }
}
