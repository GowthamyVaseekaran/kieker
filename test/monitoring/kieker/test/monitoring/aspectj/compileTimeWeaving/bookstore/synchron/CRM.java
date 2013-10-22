/***************************************************************************
 * Copyright 2013 Kieker Project (http://kieker-monitoring.net)
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

package kieker.test.monitoring.aspectj.compileTimeWeaving.bookstore.synchron;

import kieker.monitoring.annotation.OperationExecutionMonitoringProbe;

/**
 * A simple test and demonstration scenario for Kieker's
 * monitoring component.
 * 
 * THIS VARIANT IS IDENTICAL TO kieker.tests.compileTimeWeaving.bookstore.Catalog,
 * but it uses a different Catalog that has a synchronized method. This allows to
 * test the (negative) performance influence of synchronized method invocation.
 * 
 * @author Matthias Rohr
 * 
 * @since 0.91
 */
public final class CRM {

	private CRM() {}

	/**
	 * This method will be monitored, since it has an annotation.
	 */
	@OperationExecutionMonitoringProbe
	public static void getOffers() {
		Catalog.getBook(true);
	}
}