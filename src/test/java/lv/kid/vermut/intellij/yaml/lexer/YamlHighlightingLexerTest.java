package lv.kid.vermut.intellij.yaml.lexer;

import com.intellij.lexer.Lexer;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class YamlHighlightingLexerTest {

	@Test
    @Ignore
    public void testKeys() {
        Lexer l = new YamlHighlightingLexer(LexerTest.createLexer());

		l.start("key: val");

        assertEquals(YamlTokenTypes.YAML_BlockMappingStart, l.getTokenType());
        l.advance();
        assertEquals(YamlTokenTypes.YAML_Key, l.getTokenType());
        l.advance();

        assertEquals(YamlTokenTypes.YAML_Scalar, l.getTokenType()); // this is important
        assertEquals(0, l.getTokenStart());
		assertEquals(3, l.getTokenEnd());
		assertEquals("key", l.getTokenText());
		l.advance();

		assertEquals(YamlTokenTypesOld.NEON_COLON, l.getTokenType());
		assertEquals(3, l.getTokenStart());
		assertEquals(4, l.getTokenEnd());
		assertEquals(":", l.getTokenText());
		l.advance();

		assertEquals(YamlTokenTypesOld.NEON_WHITESPACE, l.getTokenType());
		assertEquals(4, l.getTokenStart());
		assertEquals(5, l.getTokenEnd());
		assertEquals(" ", l.getTokenText());
		l.advance();

		assertEquals(YamlTokenTypesOld.NEON_LITERAL, l.getTokenType());
		assertEquals(5, l.getTokenStart());
		assertEquals(8, l.getTokenEnd());
		assertEquals("val", l.getTokenText());
		l.advance();

		assertEquals(null, l.getTokenType());
	}

	@Test
	public void testKeywords() {
        Lexer l = new YamlHighlightingLexer(LexerTest.createLexer());

        l.start("[true,off,TruE,\"true\"]");

        assertEquals(YamlTokenTypes.YAML_FlowSequenceStart, l.getTokenType());
        assertEquals(0, l.getTokenStart());
        assertEquals(1, l.getTokenEnd());
        assertEquals("[", l.getTokenText());
        l.advance();

        assertEquals(YamlTokenTypes.YAML_Highlight_Keyword, l.getTokenType());
        assertEquals(1, l.getTokenStart());
		assertEquals(5, l.getTokenEnd());
		assertEquals("true", l.getTokenText());
		l.advance();

        assertEquals(YamlTokenTypes.YAML_FlowEntry, l.getTokenType());
        assertEquals(5, l.getTokenStart());
		assertEquals(6, l.getTokenEnd());
		assertEquals(",", l.getTokenText());
		l.advance();

        assertEquals(YamlTokenTypes.YAML_Scalar, l.getTokenType());
        assertEquals(6, l.getTokenStart());
		assertEquals(9, l.getTokenEnd());
		assertEquals("off", l.getTokenText());
		l.advance();

        assertEquals(YamlTokenTypes.YAML_FlowEntry, l.getTokenType());
        assertEquals(9, l.getTokenStart());
		assertEquals(10, l.getTokenEnd());
		assertEquals(",", l.getTokenText());
		l.advance();

        assertEquals(YamlTokenTypes.YAML_Scalar, l.getTokenType());
        assertEquals(10, l.getTokenStart());
		assertEquals(14, l.getTokenEnd());
		assertEquals("TruE", l.getTokenText());
		l.advance();

        assertEquals(YamlTokenTypes.YAML_FlowEntry, l.getTokenType());
        assertEquals(14, l.getTokenStart());
		assertEquals(15, l.getTokenEnd());
		assertEquals(",", l.getTokenText());
		l.advance();

        assertEquals(YamlTokenTypes.YAML_Scalar, l.getTokenType());
        assertEquals(15, l.getTokenStart());
		assertEquals(21, l.getTokenEnd());
		assertEquals("\"true\"", l.getTokenText());
		l.advance();

        assertEquals(YamlTokenTypes.YAML_FlowSequenceEnd, l.getTokenType());
        assertEquals(21, l.getTokenStart());
		assertEquals(22, l.getTokenEnd());
		assertEquals("]", l.getTokenText());
		l.advance();

        //assertEquals(null, l.getTokenType());
    }
}
