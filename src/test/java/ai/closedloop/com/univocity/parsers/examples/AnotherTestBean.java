/*******************************************************************************
 * Copyright 2015 Univocity Software Pty Ltd
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
package ai.closedloop.com.univocity.parsers.examples;

import ai.closedloop.com.univocity.parsers.annotations.BooleanString;
import ai.closedloop.com.univocity.parsers.annotations.Headers;
import ai.closedloop.com.univocity.parsers.annotations.Parsed;
import ai.closedloop.com.univocity.parsers.annotations.*;
import ai.closedloop.com.univocity.parsers.annotations.Format;

import java.text.*;
import java.util.*;

//The headers annotation tells us which headers to read/write, and the sequence.
//By setting the 'extract' parameter to true, we tell the parser to extract the first row of the input and use it as the headers. All other columns will be ignored.
//The 'write' parameter indicates whether to write the given sequence of headers to the output when writing beans to the output.
@Headers(sequence = {"pending", "date"}, extract = true, write = true)
public class AnotherTestBean {

	@Format(formats = {"dd-MMM-yyyy", "yyyy-MM-dd"}, options = "locale=en")
	@Parsed
	private Date date;

	@BooleanString(falseStrings = {"n"}, trueStrings = {"y"})
	@Parsed
	private Boolean pending;

	//##CLASS_END

	public final Boolean getPending() {
		return pending;
	}

	public final void setPending(Boolean pending) {
		this.pending = pending;
	}

	public final String getFormattedDate() {
		if(date == null){
			return null;
		}
		return new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH).format(date);
	}

	public final Date getDate() {
		return date;
	}

	public final void setDate(int year, int month, int day) {
		setDate(new GregorianCalendar(year, month, day).getTime());
	}

	public final void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "AnotherTestBean [date=" + getFormattedDate() + ", pending=" + pending + "]";
	}

}
