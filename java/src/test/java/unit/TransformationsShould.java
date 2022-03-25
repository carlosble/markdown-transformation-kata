package unit;

import mdtransformer.Footnote;
import mdtransformer.Transformations;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TransformationsShould {
    @Test
    public void transform_text_line_into_footnote(){
        Transformations transformations = new Transformations();
        List<Footnote> footNote = transformations.linkToFootNote("[some link](url)");

        assertThat(footNote.get(0)).isEqualTo(new Footnote("some link", "url"));
    }

    @Test
    public void transform_text_line_with_several_links_into_footnotes(){
        Transformations transformations = new Transformations();
        List<Footnote> footnotes = transformations.linkToFootNote("[some link](url)  \"[other link](url2)\"");

        assertThat(footnotes.get(0)).isEqualTo(
                new Footnote("some link", "url"));
        assertThat(footnotes.get(1)).isEqualTo(
                new Footnote("other link", "url2"));
    }
}
