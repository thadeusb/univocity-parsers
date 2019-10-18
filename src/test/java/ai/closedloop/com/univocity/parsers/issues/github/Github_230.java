/*
 * Copyright (c) 2018. Univocity Software Pty Ltd
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ai.closedloop.com.univocity.parsers.issues.github;

import ai.closedloop.com.univocity.parsers.csv.CsvParser;
import ai.closedloop.com.univocity.parsers.csv.CsvParserSettings;
import ai.closedloop.com.univocity.parsers.csv.*;
import org.testng.annotations.*;

import static org.testng.Assert.*;

/**
 * From: https://github.com/univocity/univocity-parsers/issues/230
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public class Github_230 {

	@Test
	public void testQuotedTrim() {
		CsvParserSettings s = new CsvParserSettings();
		s.setEmptyValue("X");
		s.trimQuotedValues(true);
		s.getFormat().setQuote('\'');
		s.getFormat().setQuoteEscape('\'');
		final CsvParser parser = new CsvParser(s);

		String[] row = parser.parseLine("' a', ' b ', ' c'");
		assertEquals(row[0], "a");
		assertEquals(row[1], "b");
		assertEquals(row[2], "c");

		row = parser.parseLine("'', ' '");
		assertEquals(row[0], "X");
		assertEquals(row[1], "X");

		row = parser.parseLine("' ', ''");
		assertEquals(row[0], "X");
		assertEquals(row[1], "X");

		row = parser.parseLine("' a', ' b ', ' c '");
		assertEquals(row[0], "a");
		assertEquals(row[1], "b");
		assertEquals(row[2], "c");

		row = parser.parseLine("' a   ', '  b ', 'c '");
		assertEquals(row[0], "a");
		assertEquals(row[1], "b");
		assertEquals(row[2], "c");

		row = parser.parseLine("' \'\'a \'\'  ', ' \'\' b\'\' ', 'c\'\' '");
		assertEquals(row[0], "'a '");
		assertEquals(row[1], "' b'");
		assertEquals(row[2], "c'");

	}

	@Test
	public void testQuotedTrimKeepQuotes() {
		CsvParserSettings s = new CsvParserSettings();
		s.setKeepQuotes(true);
		s.trimQuotedValues(true);
		s.getFormat().setQuote('\'');
		s.getFormat().setQuoteEscape('\'');
		final CsvParser parser = new CsvParser(s);

		String[] row = parser.parseLine("' \'\'a \'\'  ', ' \'\' b\'\' ', 'c\'\' '");
		assertEquals(row[0], "' 'a '  '");
		assertEquals(row[1], "' ' b' '");
		assertEquals(row[2], "'c' '");

	}

	@Test
	public void testQuotedTrimKeepEscape() {
		CsvParserSettings s = new CsvParserSettings();
		s.trimQuotedValues(true);
		s.setKeepEscapeSequences(true);
		s.getFormat().setQuote('\'');
		s.getFormat().setQuoteEscape('\'');
		final CsvParser parser = new CsvParser(s);

		String[] row = parser.parseLine("' \'\'a \'\'  ', ' \'\' b\'\' ', 'c\'\' '");
		row = parser.parseLine("' \'\'a \'\'  ', ' \'\' b\'\' ', 'c\'\' '");
		assertEquals(row[0], "''a ''");
		assertEquals(row[1], "'' b''");
		assertEquals(row[2], "c''");

	}

	@Test
	public void testQuotedTrimKeepEscapeKeepQuote() {
		CsvParserSettings s = new CsvParserSettings();
		s.trimQuotedValues(true);
		s.setKeepQuotes(true);
		s.setKeepEscapeSequences(true);
		s.getFormat().setQuote('\'');
		s.getFormat().setQuoteEscape('\'');
		final CsvParser parser = new CsvParser(s);

		String[] row = parser.parseLine("' \'\'a \'\'  ', ' \'\' b\'\' ', 'c\'\' '");
		row = parser.parseLine("' \'\'a \'\'  ', ' \'\' b\'\' ', 'c\'\' '");
		assertEquals(row[0], "' ''a ''  '");
		assertEquals(row[1], "' '' b'' '");
		assertEquals(row[2], "'c'' '");

	}
}
