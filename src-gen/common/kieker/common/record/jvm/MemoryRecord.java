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
public class MemoryRecord extends AbstractJVMRecord  {
	/** Descriptive definition of the serialization size of the record. */
	public static final int SIZE = TYPE_SIZE_LONG // AbstractJVMRecord.timestamp
			 + TYPE_SIZE_STRING // AbstractJVMRecord.hostname
			 + TYPE_SIZE_STRING // AbstractJVMRecord.vmName
			 + TYPE_SIZE_LONG // MemoryRecord.heapMaxBytes
			 + TYPE_SIZE_LONG // MemoryRecord.heapUsedBytes
			 + TYPE_SIZE_LONG // MemoryRecord.heapCommittedBytes
			 + TYPE_SIZE_LONG // MemoryRecord.heapInitBytes
			 + TYPE_SIZE_LONG // MemoryRecord.nonHeapMaxBytes
			 + TYPE_SIZE_LONG // MemoryRecord.nonHeapUsedBytes
			 + TYPE_SIZE_LONG // MemoryRecord.nonHeapCommittedBytes
			 + TYPE_SIZE_LONG // MemoryRecord.nonHeapInitBytes
			 + TYPE_SIZE_INT // MemoryRecord.objectPendingFinalizationCount
	;
	private static final long serialVersionUID = 3895659770245456299L;
	
	public static final Class<?>[] TYPES = {
		long.class, // AbstractJVMRecord.timestamp
		String.class, // AbstractJVMRecord.hostname
		String.class, // AbstractJVMRecord.vmName
		long.class, // MemoryRecord.heapMaxBytes
		long.class, // MemoryRecord.heapUsedBytes
		long.class, // MemoryRecord.heapCommittedBytes
		long.class, // MemoryRecord.heapInitBytes
		long.class, // MemoryRecord.nonHeapMaxBytes
		long.class, // MemoryRecord.nonHeapUsedBytes
		long.class, // MemoryRecord.nonHeapCommittedBytes
		long.class, // MemoryRecord.nonHeapInitBytes
		int.class, // MemoryRecord.objectPendingFinalizationCount
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
	 * @param heapMaxBytes
	 *            heapMaxBytes
	 * @param heapUsedBytes
	 *            heapUsedBytes
	 * @param heapCommittedBytes
	 *            heapCommittedBytes
	 * @param heapInitBytes
	 *            heapInitBytes
	 * @param nonHeapMaxBytes
	 *            nonHeapMaxBytes
	 * @param nonHeapUsedBytes
	 *            nonHeapUsedBytes
	 * @param nonHeapCommittedBytes
	 *            nonHeapCommittedBytes
	 * @param nonHeapInitBytes
	 *            nonHeapInitBytes
	 * @param objectPendingFinalizationCount
	 *            objectPendingFinalizationCount
	 */
	public MemoryRecord(final long timestamp, final String hostname, final String vmName, final long heapMaxBytes, final long heapUsedBytes, final long heapCommittedBytes, final long heapInitBytes, final long nonHeapMaxBytes, final long nonHeapUsedBytes, final long nonHeapCommittedBytes, final long nonHeapInitBytes, final int objectPendingFinalizationCount) {
		super(timestamp, hostname, vmName);
	}

	/**
	 * This constructor converts the given array into a record.
	 * It is recommended to use the array which is the result of a call to {@link #toArray()}.
	 * 
	 * @param values
	 *            The values for the record.
	 */
	public MemoryRecord(final Object[] values) { // NOPMD (direct store of values)
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
	protected MemoryRecord(final Object[] values, final Class<?>[] valueTypes) { // NOPMD (values stored directly)
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
	public MemoryRecord(final ByteBuffer buffer, final IRegistry<String> stringRegistry) throws BufferUnderflowException {
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
			this.getHeapMaxBytes(),
			this.getHeapUsedBytes(),
			this.getHeapCommittedBytes(),
			this.getHeapInitBytes(),
			this.getNonHeapMaxBytes(),
			this.getNonHeapUsedBytes(),
			this.getNonHeapCommittedBytes(),
			this.getNonHeapInitBytes(),
			this.getObjectPendingFinalizationCount()
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
		buffer.putLong(this.getHeapMaxBytes());
		buffer.putLong(this.getHeapUsedBytes());
		buffer.putLong(this.getHeapCommittedBytes());
		buffer.putLong(this.getHeapInitBytes());
		buffer.putLong(this.getNonHeapMaxBytes());
		buffer.putLong(this.getNonHeapUsedBytes());
		buffer.putLong(this.getNonHeapCommittedBytes());
		buffer.putLong(this.getNonHeapInitBytes());
		buffer.putInt(this.getObjectPendingFinalizationCount());
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

	public final long getHeapMaxBytes() {
		return this.heapMaxBytes;
	}
	
	public final long getHeapUsedBytes() {
		return this.heapUsedBytes;
	}
	
	public final long getHeapCommittedBytes() {
		return this.heapCommittedBytes;
	}
	
	public final long getHeapInitBytes() {
		return this.heapInitBytes;
	}
	
	public final long getNonHeapMaxBytes() {
		return this.nonHeapMaxBytes;
	}
	
	public final long getNonHeapUsedBytes() {
		return this.nonHeapUsedBytes;
	}
	
	public final long getNonHeapCommittedBytes() {
		return this.nonHeapCommittedBytes;
	}
	
	public final long getNonHeapInitBytes() {
		return this.nonHeapInitBytes;
	}
	
	public final int getObjectPendingFinalizationCount() {
		return this.objectPendingFinalizationCount;
	}
	
}
