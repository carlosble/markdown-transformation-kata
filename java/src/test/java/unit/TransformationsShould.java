package unit;

import mdtransformer.Footnote;
import mdtransformer.LinkToFootnote;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TransformationsShould {
    @Test
    public void transform_text_line_into_footnote(){
        LinkToFootnote transformations = new LinkToFootnote();
        List<Footnote> footNote = transformations.transformOfLinksIn("[some link](url)");

        assertThat(footNote.get(0)).isEqualTo(new Footnote("some link", "url"));
    }

    @Test
    public void transform_text_line_with_several_links_into_footnotes(){
        LinkToFootnote transformations = new LinkToFootnote();
        List<Footnote> footnotes = transformations.transformOfLinksIn("[some link](url)  \"[other link](url2)\"");

        assertThat(footnotes.get(0)).isEqualTo(
                new Footnote("some link", "url"));
        assertThat(footnotes.get(1)).isEqualTo(
                new Footnote("other link", "url2"));
    }
}
