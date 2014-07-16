/***************************************************************************
 * Copyright 2014 Kicker Project (http://kicker-monitoring.net)
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

package kicker.common.record.flow;

/**
 * Interface for all flow records that describe operations.
 * 
 * All operation records have a <code>classSignature</code> and an <code>operationSignature</code> field of type <code>String</code>.
 * 
 * @author Jan Waller
 * 
 * @since 1.6
 */
public interface IOperationRecord extends IFlowRecord {

	/**
	 * @return The actually executing class.
	 * 
	 * @since 1.6
	 */
	public abstract String getClassSignature();

	/**
	 * @return The actual OperationSignature.
	 * 
	 * @since 1.6
	 */
	public abstract String getOperationSignature();

	/**
	 * @param record
	 *            The record to check.
	 * 
	 * @return True iff the specifieid record refers to the same operation as this record.
	 * 
	 * @since 1.6
	 */
	public abstract boolean refersToSameOperationAs(final IOperationRecord record);
}