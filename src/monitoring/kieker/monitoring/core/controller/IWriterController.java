/***************************************************************************
 * Copyright 2014 Kieker Project (http://kieker-monitoring.net)
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

package kieker.monitoring.core.controller;

import kieker.common.record.IMonitoringRecord;
import kieker.common.util.registry.IMonitoringRecordReceiver;

/**
 * The methods must not throw any exceptions but indicate problems via its
 * respective return value.
 * 
 * @author Andre van Hoorn, Jan Waller, Robert von Massow
 * 
 * @since 1.3
 */
public interface IWriterController extends IMonitoringRecordReceiver {

	/**
	 * Called for each new record.
	 * 
	 * Notice, that this method should not throw an exception, but indicate an error by the return value false.
	 * 
	 * @param record
	 *            the record.
	 * @return true on success; false in case of an error.
	 * 
	 * @since 1.3
	 */
	public abstract boolean newMonitoringRecord(IMonitoringRecord record);

	/**
	 * Shows how many inserts have been performed since last restart of the
	 * execution environment.
	 * 
	 * @return long
	 * 
	 * @since 1.3
	 */
	public abstract long getNumberOfInserts();
}
