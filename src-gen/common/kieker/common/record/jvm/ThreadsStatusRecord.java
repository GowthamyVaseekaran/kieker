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

package kieker.common.record.jvm;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

import kieker.common.util.registry.IRegistry;

import kieker.common.record.jvm.AbstractJVMRecord;

/**
 * @author Generic Kieker
 * 
 * @since 1.10
 */
public class ThreadsStatusRecord extends AbstractJVMRecord  {
	/** Descriptive definition of the serialization size of the record. */
	public static final int SIZE = TYPE_SIZE_LONG // AbstractJVMRecord.timestamp
			 + TYPE_SIZE_STRING // AbstractJVMRecord.hostname
			 + TYPE_SIZE_STRING // AbstractJVMRecord.vmName
			 + TYPE_SIZE_LONG // ThreadsStatusRecord.threadCount
			 + TYPE_SIZE_LONG // ThreadsStatusRecord.daemonThreadCount
			 + TYPE_SIZE_LONG // ThreadsStatusRecord.peakThreadCount
			 + TYPE_SIZE_LONG // ThreadsStatusRecord.totalStartedThreadCount
	;
	private static final long serialVersionUID = 2792503169060556644L;
	
	public static final Class<?>[] TYPES = {
		long.class, // AbstractJVMRecord.timestamp
		String.class, // AbstractJVMRecord.hostname
		String.class, // AbstractJVMRecord.vmName
		long.class, // ThreadsStatusRecord.threadCount
		long.class, // ThreadsStatusRecord.daemonThreadCount
		long.class, // ThreadsStatusRecord.peakThreadCount
		long.class, // ThreadsStatusRecord.totalStartedThreadCount
	};
	
	

	/**
	 * Creates a new instance of this class using the given parameters.
	 * 
	 * @param timestamp
	 *            timestamp
	 * @param hostname
	 *            hostname
	 * @param vmName
	 *            vmName
	 * @param threadCount
	 *            threadCount
	 * @param daemonThreadCount
	 *            daemonThreadCount
	 * @param peakThreadCount
	 *            peakThreadCount
	 * @param totalStartedThreadCount
	 *            totalStartedThreadCount
	 */
	public ThreadsStatusRecord(final long timestamp, final String hostname, final String vmName, final long threadCount, final long daemonThreadCount, final long peakThreadCount, final long totalStartedThreadCount) {
		super(timestamp, hostname, vmName);
	}

	/**
	 * This constructor converts the given array into a record.
	 * It is recommended to use the array which is the result of a call to {@link #toArray()}.
	 * 
	 * @param values
	 *            The values for the record.
	 */
	public ThreadsStatusRecord(final Object[] values) { // NOPMD (direct store of values)
		super(values, TYPES);
	}
	
	/**
	 * This constructor uses the given array to initialize the fields of this record.
	 * 
	 * @param values
	 *            The values for the record.
	 * @param valueTypes
	 *            The types of the elements in the first array.
	 */
	protected ThreadsStatusRecord(final Object[] values, final Class<?>[] valueTypes) { // NOPMD (values stored directly)
		super(values, valueTypes);
	}

	/**
	 * This constructor converts the given array into a record.
	 * 
	 * @param buffer
	 *            The bytes for the record.
	 * 
	 * @throws BufferUnderflowException
	 *             if buffer not sufficient
	 */
	public ThreadsStatusRecord(final ByteBuffer buffer, final IRegistry<String> stringRegistry) throws BufferUnderflowException {
		super(buffer, stringRegistry);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] toArray() {
		return new Object[] {
			this.getTimestamp(),
			this.getHostname(),
			this.getVmName(),
			this.getThreadCount(),
			this.getDaemonThreadCount(),
			this.getPeakThreadCount(),
			this.getTotalStartedThreadCount()
		};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeBytes(final ByteBuffer buffer, final IRegistry<String> stringRegistry) throws BufferOverflowException {
		buffer.putLong(this.getTimestamp());
		buffer.putInt(stringRegistry.get(this.getHostname()));
		buffer.putInt(stringRegistry.get(this.getVmName()));
		buffer.putLong(this.getThreadCount());
		buffer.putLong(this.getDaemonThreadCount());
		buffer.putLong(this.getPeakThreadCount());
		buffer.putLong(this.getTotalStartedThreadCount());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?>[] getValueTypes() {
		return TYPES; // NOPMD
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSize() {
		return SIZE;
	}
	/**
	 * {@inheritDoc}
	 * 
	 * @deprecated This record uses the {@link kieker.common.record.IMonitoringRecord.Factory} mechanism. Hence, this method is not implemented.
	 */
	@Override
	@Deprecated
	public void initFromArray(final Object[] values) {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @deprecated This record uses the {@link kieker.common.record.IMonitoringRecord.BinaryFactory} mechanism. Hence, this method is not implemented.
	 */
	@Override
	@Deprecated
	public void initFromBytes(final ByteBuffer buffer, final IRegistry<String> stringRegistry) throws BufferUnderflowException {
		throw new UnsupportedOperationException();
	}

	public final long getThreadCount() {
		return this.threadCount;
	}
	
	public final long getDaemonThreadCount() {
		return this.daemonThreadCount;
	}
	
	public final long getPeakThreadCount() {
		return this.peakThreadCount;
	}
	
	public final long getTotalStartedThreadCount() {
		return this.totalStartedThreadCount;
	}
	
}
