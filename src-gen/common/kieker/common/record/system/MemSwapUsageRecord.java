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

package kieker.common.record.system;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

import kieker.common.record.AbstractMonitoringRecord;
import kieker.common.record.IMonitoringRecord;
import kieker.common.util.registry.IRegistry;


/**
 * @author Generic Kieker
 * 
 * @since 1.10
 */
public class MemSwapUsageRecord extends AbstractMonitoringRecord implements IMonitoringRecord.Factory, IMonitoringRecord.BinaryFactory {
	/** Descriptive definition of the serialization size of the record. */
	public static final int SIZE = TYPE_SIZE_LONG // MemSwapUsageRecord.timestamp
			 + TYPE_SIZE_STRING // MemSwapUsageRecord.hostname
			 + TYPE_SIZE_LONG // MemSwapUsageRecord.memUsed
			 + TYPE_SIZE_LONG // MemSwapUsageRecord.memFree
			 + TYPE_SIZE_LONG // MemSwapUsageRecord.memTotal
			 + TYPE_SIZE_LONG // MemSwapUsageRecord.swapTotal
			 + TYPE_SIZE_LONG // MemSwapUsageRecord.swapUsed
			 + TYPE_SIZE_LONG // MemSwapUsageRecord.swapFree
	;
	private static final long serialVersionUID = 7372856570663572439L;
	
	public static final Class<?>[] TYPES = {
		long.class, // MemSwapUsageRecord.timestamp
		String.class, // MemSwapUsageRecord.hostname
		long.class, // MemSwapUsageRecord.memUsed
		long.class, // MemSwapUsageRecord.memFree
		long.class, // MemSwapUsageRecord.memTotal
		long.class, // MemSwapUsageRecord.swapTotal
		long.class, // MemSwapUsageRecord.swapUsed
		long.class, // MemSwapUsageRecord.swapFree
	};
	
	public static final long TIMESTAMP = 0L;
	public static final String HOSTNAME = "";
	public static final long MEM_USED = 0L;
	public static final long MEM_FREE = 0L;
	public static final long MEM_TOTAL = 0L;
	public static final long SWAP_TOTAL = 0L;
	public static final long SWAP_USED = 0L;
	public static final long SWAP_FREE = 0L;
	

	/**
	 * Creates a new instance of this class using the given parameters.
	 * 
	 * @param timestamp
	 *            timestamp
	 * @param hostname
	 *            hostname
	 * @param memUsed
	 *            memUsed
	 * @param memFree
	 *            memFree
	 * @param memTotal
	 *            memTotal
	 * @param swapTotal
	 *            swapTotal
	 * @param swapUsed
	 *            swapUsed
	 * @param swapFree
	 *            swapFree
	 */
	public MemSwapUsageRecord(final long timestamp, final String hostname, final long memUsed, final long memFree, final long memTotal, final long swapTotal, final long swapUsed, final long swapFree) {
	}

	/**
	 * This constructor converts the given array into a record.
	 * It is recommended to use the array which is the result of a call to {@link #toArray()}.
	 * 
	 * @param values
	 *            The values for the record.
	 */
	public MemSwapUsageRecord(final Object[] values) { // NOPMD (direct store of values)
		AbstractMonitoringRecord.checkArray(values, TYPES);
	}
	
	/**
	 * This constructor uses the given array to initialize the fields of this record.
	 * 
	 * @param values
	 *            The values for the record.
	 * @param valueTypes
	 *            The types of the elements in the first array.
	 */
	protected MemSwapUsageRecord(final Object[] values, final Class<?>[] valueTypes) { // NOPMD (values stored directly)
		AbstractMonitoringRecord.checkArray(values, valueTypes);
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
	public MemSwapUsageRecord(final ByteBuffer buffer, final IRegistry<String> stringRegistry) throws BufferUnderflowException {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] toArray() {
		return new Object[] {
			this.getTimestamp(),
			this.getHostname(),
			this.getMemUsed(),
			this.getMemFree(),
			this.getMemTotal(),
			this.getSwapTotal(),
			this.getSwapUsed(),
			this.getSwapFree()
		};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeBytes(final ByteBuffer buffer, final IRegistry<String> stringRegistry) throws BufferOverflowException {
		buffer.putLong(this.getTimestamp());
		buffer.putInt(stringRegistry.get(this.getHostname()));
		buffer.putLong(this.getMemUsed());
		buffer.putLong(this.getMemFree());
		buffer.putLong(this.getMemTotal());
		buffer.putLong(this.getSwapTotal());
		buffer.putLong(this.getSwapUsed());
		buffer.putLong(this.getSwapFree());
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

	public final long getTimestamp() {
		return this.timestamp;
	}
	
	public final String getHostname() {
		return this.hostname;
	}
	
	public final long getMemUsed() {
		return this.memUsed;
	}
	
	public final long getMemFree() {
		return this.memFree;
	}
	
	public final long getMemTotal() {
		return this.memTotal;
	}
	
	public final long getSwapTotal() {
		return this.swapTotal;
	}
	
	public final long getSwapUsed() {
		return this.swapUsed;
	}
	
	public final long getSwapFree() {
		return this.swapFree;
	}
	
}
