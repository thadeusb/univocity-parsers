/*******************************************************************************
 * Copyright 2017 Univocity Software Pty Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ai.closedloop.com.univocity.parsers.issues.github;

import ai.closedloop.com.univocity.parsers.common.ParsingContext;
import ai.closedloop.com.univocity.parsers.common.ResultIterator;
import ai.closedloop.com.univocity.parsers.csv.CsvParserSettings;
import ai.closedloop.com.univocity.parsers.csv.CsvParserTest;
import ai.closedloop.com.univocity.parsers.csv.CsvRoutines;
import ai.closedloop.com.univocity.parsers.examples.TestBean;
import ai.closedloop.com.univocity.parsers.common.*;
import ai.closedloop.com.univocity.parsers.csv.*;
import ai.closedloop.com.univocity.parsers.examples.*;
import org.testng.annotations.*;

import java.io.*;
import java.util.*;

import static org.testng.Assert.*;

/**
 * From: https://github.com/univocity/univocity-parsers/issues/132
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public class Github_136 {

	@Test
	public void testIterateJavaBeansWithParsingContext() throws Exception {
		List<TestBean> beans = new ArrayList<TestBean>();

		Reader input = CsvParserTest.newReader("/examples/bean_test.csv");

		CsvParserSettings settings = new CsvParserSettings();
		settings.getFormat().setLineSeparator("\n");

		CsvRoutines routine = new CsvRoutines(settings);

		ResultIterator<TestBean, ParsingContext> it = routine.iterate(TestBean.class, input).iterator();

		StringBuilder content = new StringBuilder();
		while(it.hasNext()) {
			content.append(it.getContext().currentParsedContent());
			beans.add(it.next());
		}
		assertEquals(beans.size(), 2);
		assertEquals(beans.get(0).getQuantity(), Integer.valueOf(1));
		assertEquals(beans.get(1).getComments(), "\" something \"");

		assertEquals(content.toString(), "" +
				"10-oct-2001,\t555.999,\t1,\t\t\tyEs\t\t,?\n" +
				"2001-10-10,\t\t,\t\t\t?,\t\t\tN\t\t,\"  \"\" something \"\"  \"");
	}


}
