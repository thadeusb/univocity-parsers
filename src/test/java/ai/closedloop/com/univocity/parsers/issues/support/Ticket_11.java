package ai.closedloop.com.univocity.parsers.issues.support;

import ai.closedloop.com.univocity.parsers.csv.CsvParser;
import ai.closedloop.com.univocity.parsers.csv.CsvParserSettings;
import ai.closedloop.com.univocity.parsers.csv.*;
import org.testng.annotations.*;

import java.io.*;
import java.util.*;

import static org.testng.Assert.*;

/**
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public class Ticket_11 {

	@Test
	public void parseLastLineComment() throws Exception {
		CsvParserSettings settings = new CsvParserSettings();
		CsvParser parser = new CsvParser(settings);

		String s = "a,b\n#c";

		List<String[]> strings = parser.parseAll(new StringReader(s));


		assertEquals(strings.size(), 1);
		String[] values = strings.get(0);
		assertEquals(values.length, 2);

		assertEquals(values[0], "a");
		assertEquals(values[1], "b");
	}

}
