/*******************************************************************************
 * Copyright 2018 Univocity Software Pty Ltd
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
package ai.closedloop.com.univocity.parsers.examples.annotation;

import ai.closedloop.com.univocity.parsers.annotations.Parsed;
import ai.closedloop.com.univocity.parsers.annotations.*;

public class ProfileByFieldName {

	@Parsed(field = "profile_id")
	private Long profileId;

	@Parsed
	private String username;

	@Parsed
	private int followers;

	@Override
	public String toString() {
		return "ProfileByFieldName{" +
				"profileId=" + profileId +
				", username='" + username + '\'' +
				", followers=" + followers +
				'}';
	}
}
