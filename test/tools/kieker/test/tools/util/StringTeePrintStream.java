/***************************************************************************
 * Copyright 2012 Kieker Project (http://kieker-monitoring.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package kieker.test.tools.util;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * @author Andre van Hoorn
 */
public class StringTeePrintStream extends PrintStream {
	private static final String ENCODING = "UTF-8";

	private final StringTeeOutputStream stringTeeOutputStream;

	public StringTeePrintStream(final PrintStream originalStream) throws UnsupportedEncodingException {
		super(new StringTeeOutputStream(originalStream), false, StringTeePrintStream.ENCODING);
		this.stringTeeOutputStream = (StringTeeOutputStream) this.out;
	}

	public String getString() {
		if (this.stringTeeOutputStream == null) {
			return null;
		}

		return this.stringTeeOutputStream.getString();
	}
}