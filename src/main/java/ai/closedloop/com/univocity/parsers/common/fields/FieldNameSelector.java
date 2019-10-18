/*******************************************************************************
 * Copyright 2014 Univocity Software Pty Ltd
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
package ai.closedloop.com.univocity.parsers.common.fields;

import ai.closedloop.com.univocity.parsers.common.ArgumentUtils;
import ai.closedloop.com.univocity.parsers.common.*;

import java.util.*;

/**
 * A FieldSelector capable of selecting fields by their name.
 *
 * @see FieldSelector
 * @see FieldSet
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 *
 */
public class FieldNameSelector extends FieldSet<String> implements FieldSelector {

	/**
	 * Returns the position of a given header
	 * @param header the header whose position will be returned
	 * @return the position of the given header.
	 */
	public int getFieldIndex(String header) {
		return getFieldIndexes(new String[]{header})[0];
	}

	@Override
	public int[] getFieldIndexes(String[] headers) {
		if (headers == null) {
			return null;
		}
		headers = ArgumentUtils.normalize(headers);
		List<String> selection = this.get();
		ArgumentUtils.normalize(selection);

		String[] chosenFields = selection.toArray(new String[selection.size()]);
		Object[] unknownFields = ArgumentUtils.findMissingElements(headers, chosenFields);

		//if we get a subset of the expected columns, we can parse normally, considering missing column values as null.
		if (unknownFields.length > 0 && !selection.containsAll(Arrays.asList(headers))) {
			//nothing matched, just return an empty array and proceed.
			if (unknownFields.length == chosenFields.length) {
				return new int[0];
			}
		}

		int[] out = new int[chosenFields.length];
		int i = 0;
		for (String chosenField : chosenFields) {
			int[] indexes = ArgumentUtils.indexesOf(headers, chosenField);

			if (indexes.length > 1) {
				out = Arrays.copyOf(out, out.length + indexes.length - 1);
			}

			if (indexes.length == 0) {
				out[i++] = -1;
			} else {
				for (int j = 0; j < indexes.length; j++) {
					out[i++] = indexes[j];
				}
			}
		}

		return out;
	}

}
