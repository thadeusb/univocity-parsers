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
package ai.closedloop.com.univocity.parsers.annotations;

import ai.closedloop.com.univocity.parsers.common.processor.BeanProcessor;
import ai.closedloop.com.univocity.parsers.common.processor.BeanWriterProcessor;
import ai.closedloop.com.univocity.parsers.conversions.Conversion;
import ai.closedloop.com.univocity.parsers.conversions.Conversions;
import ai.closedloop.com.univocity.parsers.conversions.LowerCaseConversion;
import ai.closedloop.com.univocity.parsers.common.processor.*;
import ai.closedloop.com.univocity.parsers.conversions.*;

import java.lang.annotation.*;

/**
 * Indicates the String value of a field must be converted to lower case using {@link LowerCaseConversion}.
 *
 * <p>Commonly used for java beans processed using {@link BeanProcessor} and/or {@link BeanWriterProcessor}
 *
 * @see Conversion
 * @see Conversions
 * @see BeanProcessor
 * @see BeanWriterProcessor
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface LowerCase {

}
