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

import ai.closedloop.com.univocity.parsers.common.CommonWriterSettings;
import ai.closedloop.com.univocity.parsers.common.Format;
import ai.closedloop.com.univocity.parsers.csv.CsvFormat;
import ai.closedloop.com.univocity.parsers.common.*;
import ai.closedloop.com.univocity.parsers.csv.*;
import org.testng.annotations.*;

import java.util.*;
import java.util.concurrent.atomic.*;

import static org.testng.Assert.*;


/**
 * From: https://github.com/univocity/univocity-parsers/issues/143
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public class Github_146 {

	public static class TestSettings extends CommonWriterSettings {
		@Override
		protected Format createDefaultFormat() {
			CsvFormat out = new CsvFormat();
			out.setLineSeparator("\n");
			return out;
		}

		@Override
		public void configureFromAnnotations(Class beanClass) {
			super.configureFromAnnotations(beanClass);
		}
	}

	@Test(timeOut = 2000)
	public void parallelAnnotationProcessing() throws Exception {
		final AtomicInteger successCount = new AtomicInteger();
		List<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < 100; i++) {
			Thread t = new Thread() {
				public void run() {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}

					new TestSettings().configureFromAnnotations(Github_139.Person1.class);
					try {
						Thread.sleep(1);

					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
					new TestSettings().configureFromAnnotations(Github_139.Person1.class);

					successCount.incrementAndGet();
				}
			};
			threads.add(t);
			t.start();
		}

		for (Thread t : threads) {
			t.join();
		}

		assertEquals(successCount.get(), 100);
	}
}
