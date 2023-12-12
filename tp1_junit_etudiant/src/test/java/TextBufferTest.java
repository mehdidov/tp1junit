import fr.einfolearning.tp2.metiers.TextBuffer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TextBufferTest {
    private TextBuffer textBuffer;

    @BeforeEach
    public void init() {
        textBuffer = new TextBuffer("Ceci est un test de la méthode toString");
    }

    @Test
    public void should_return_string_when_create_textbuffer_whith_string_content() {
        //Arrange
        String expectedString = "Ceci est un test de la méthode toString";

        //Act
        String string = textBuffer.toString();

        //Assert
        Assertions.assertEquals(expectedString, string);
    }

    @Test
    public void should_return_correct_max_when_create_textbuffer_whith_string_content() {

        //Arrange
        int expectedMax = "Ceci est un test de la méthode toString".length();

        //Act
        int max = textBuffer.maxP();

        //Assert
        assertThat(max, is(expectedMax));
    }

    @Test
    public void should_return_same_insterted_string_when_insert_string() {

        //Arrange
        String expectedString = "test";
        int from = 4;
        int to = from + "test".length();

        //Act
        textBuffer.ins("test", 4);


        //Assert
        String stringInserted = textBuffer.substr(from, to);
        assertThat(stringInserted, is(expectedString));

    }

    @Test
    public void should_return_correct_string_when_correct_to_from_parameter() {
        //Arrange
        int from = 12;
        int to = 16;
        String expectedString = "test";

        //Act
        String subStringRes = textBuffer.substr(from, to);

        //Assert
        assertThat(subStringRes, is(expectedString));

    }

    @Test
    public void should_return_trunced_string_when_to_out_of_limit() {
        //Arrange
        int from = "Ceci est un test de la méthode toString".length() - 6;
        int to = 200;
        String expectedString = "String";

        //Act
        String subStringRes = textBuffer.substr(from, to);

        //Assert
        assertThat(subStringRes, is(expectedString));
    }

    @Test
    public void should_return_nothing_where_when_from_and_to_out_of_limit() {
        //Arrange
        int from = 200;
        int to = 200;
        String expectedString = "String";

        //Act
        String subStringRes = textBuffer.substr(from, to);

        //Assert
        assertThat(subStringRes, is(""));
    }

    @Test
    public void should_delete_trunced_substring_where_to_out_of_limit() {
        //Arrange
        int from = "Ceci est un test de la méthode toString".length() - 6;
        int to = 200;

        String expectedString = "Ceci est un test de la méthode to";

        //Act
        textBuffer.del(from, to);

        //Assert
        assertThat(textBuffer.toString(), is(expectedString));

    }

    @Test
    public void should_del_nothing_where_from_and_to_out_of_limit() {

        //Arrange
        int from = 200;
        int to = 200;

        String expectedString = "Ceci est un test de la méthode toString";

        //Act
        textBuffer.del(from, to);

        //Assert
        assertThat(textBuffer.toString(), is(expectedString));
    }
}
