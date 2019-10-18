/*******************************************************************************
 * Copyright 2016 Univocity Software Pty Ltd
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
package ai.closedloop.com.univocity.parsers.issues.support;

import ai.closedloop.com.univocity.parsers.annotations.Parsed;
import ai.closedloop.com.univocity.parsers.annotations.Trim;
import ai.closedloop.com.univocity.parsers.common.DataProcessingException;
import ai.closedloop.com.univocity.parsers.common.ParsingContext;
import ai.closedloop.com.univocity.parsers.common.RowProcessorErrorHandler;
import ai.closedloop.com.univocity.parsers.common.processor.BeanListProcessor;
import ai.closedloop.com.univocity.parsers.csv.CsvParser;
import ai.closedloop.com.univocity.parsers.csv.CsvParserSettings;
import ai.closedloop.com.univocity.parsers.annotations.*;
import ai.closedloop.com.univocity.parsers.annotations.Format;
import ai.closedloop.com.univocity.parsers.common.*;
import ai.closedloop.com.univocity.parsers.common.processor.*;
import ai.closedloop.com.univocity.parsers.csv.*;
import org.testng.annotations.*;

import java.io.*;
import java.util.*;

import static org.testng.Assert.*;

/**
 * Description: lenient flag was not being set in format options of @Format annotation.
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 */
public class Ticket_7 {

	public static class DateBean {
		@Trim
		@Format(formats = {"yyyy-MM-dd", "MM/dd/yyyy", "MM/dd/yy"}, options = "lenient=false")
		@Parsed(field = "Invoice Date")
		public Date invoiceDate;
	}

	@Test
	public void testLenientDateFormatOptionInAnnotation() {
		CsvParserSettings settings = new CsvParserSettings();
		BeanListProcessor<DateBean> processor = new BeanListProcessor<DateBean>(DateBean.class);
		settings.setRowProcessor(processor);

		settings.setRowProcessorErrorHandler(new RowProcessorErrorHandler() {
			@Override
			public void handleError(DataProcessingException error, Object[] inputRow, ParsingContext context) {
				int columnIndex = error.getColumnIndex();
				String columnName = error.getColumnName();
				Object invalidValue = inputRow[columnIndex];

				assertEquals(invalidValue, "2016-12-43");
				assertEquals(columnName, "Invoice Date");
			}
		});

		new CsvParser(settings).parse(new StringReader("" +
				"Invoice Number,Invoice Date\n" +
				"ABC123,2016-12-43\n"));

		assertTrue(processor.getBeans().isEmpty());

	}

}
