/***************************************************************************
* Copyright 2018 Kieker Project (http://kieker-monitoring.net)
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
***************************************************************************/
package kieker.common.record.misc;

import java.nio.BufferOverflowException;

import kieker.common.exception.RecordInstantiationException;
import kieker.common.record.AbstractMonitoringRecord;
import kieker.common.record.IMonitoringRecord;
import kieker.common.record.io.IValueDeserializer;
import kieker.common.record.io.IValueSerializer;


/**
 * @author Jan Waller
 * API compatibility: Kieker 1.14.0
 * 
 * @since 1.7
 */
public class KiekerMetadataRecord extends AbstractMonitoringRecord implements IMonitoringRecord.Factory, IMonitoringRecord.BinaryFactory {			
	/** Descriptive definition of the serialization size of the record. */
	public static final int SIZE = TYPE_SIZE_STRING // KiekerMetadataRecord.version
			 + TYPE_SIZE_STRING // KiekerMetadataRecord.controllerName
			 + TYPE_SIZE_STRING // KiekerMetadataRecord.hostname
			 + TYPE_SIZE_INT // KiekerMetadataRecord.experimentId
			 + TYPE_SIZE_BOOLEAN // KiekerMetadataRecord.debugMode
			 + TYPE_SIZE_LONG // KiekerMetadataRecord.timeOffset
			 + TYPE_SIZE_STRING // KiekerMetadataRecord.timeUnit
			 + TYPE_SIZE_LONG; // KiekerMetadataRecord.numberOfRecords
	
	public static final Class<?>[] TYPES = {
		String.class, // KiekerMetadataRecord.version
		String.class, // KiekerMetadataRecord.controllerName
		String.class, // KiekerMetadataRecord.hostname
		int.class, // KiekerMetadataRecord.experimentId
		boolean.class, // KiekerMetadataRecord.debugMode
		long.class, // KiekerMetadataRecord.timeOffset
		String.class, // KiekerMetadataRecord.timeUnit
		long.class, // KiekerMetadataRecord.numberOfRecords
	};
	
	/** user-defined constants. */
	public static final String NO_CONTROLLERNAME = "<no-controller-name>";
	public static final String NO_HOSTNAME = "<no-hostname>";
	public static final String NO_TIMESOURCE = "<no-timesource>";
	public static final String NO_TIMEUNIT = "NANOSECONDS";
	/** default constants. */
	public static final String VERSION = kieker.common.util.Version.getVERSION();
	public static final String CONTROLLER_NAME = NO_CONTROLLERNAME;
	public static final String HOSTNAME = NO_HOSTNAME;
	public static final int EXPERIMENT_ID = 0;
	public static final boolean DEBUG_MODE = false;
	public static final long TIME_OFFSET = 0L;
	public static final String TIME_UNIT = NO_TIMEUNIT;
	public static final long NUMBER_OF_RECORDS = 0L;
	private static final long serialVersionUID = 8241152536143822747L;
	
	/** property name array. */
	private static final String[] PROPERTY_NAMES = {
		"version",
		"controllerName",
		"hostname",
		"experimentId",
		"debugMode",
		"timeOffset",
		"timeUnit",
		"numberOfRecords",
	};
	
	/** property declarations. */
	private final String version;
	private final String controllerName;
	private final String hostname;
	private final int experimentId;
	private final boolean debugMode;
	private final long timeOffset;
	private final String timeUnit;
	private final long numberOfRecords;
	
	/**
	 * Creates a new instance of this class using the given parameters.
	 * 
	 * @param version
	 *            version
	 * @param controllerName
	 *            controllerName
	 * @param hostname
	 *            hostname
	 * @param experimentId
	 *            experimentId
	 * @param debugMode
	 *            debugMode
	 * @param timeOffset
	 *            timeOffset
	 * @param timeUnit
	 *            timeUnit
	 * @param numberOfRecords
	 *            numberOfRecords
	 */
	public KiekerMetadataRecord(final String version, final String controllerName, final String hostname, final int experimentId, final boolean debugMode, final long timeOffset, final String timeUnit, final long numberOfRecords) {
		this.version = version == null?VERSION:version;
		this.controllerName = controllerName == null?NO_CONTROLLERNAME:controllerName;
		this.hostname = hostname == null?NO_HOSTNAME:hostname;
		this.experimentId = experimentId;
		this.debugMode = debugMode;
		this.timeOffset = timeOffset;
		this.timeUnit = timeUnit == null?NO_TIMEUNIT:timeUnit;
		this.numberOfRecords = numberOfRecords;
	}

	/**
	 * This constructor converts the given array into a record.
	 * It is recommended to use the array which is the result of a call to {@link #toArray()}.
	 * 
	 * @param values
	 *            The values for the record.
	 *
	 * @deprecated to be removed 1.15
	 */
	@Deprecated
	public KiekerMetadataRecord(final Object[] values) { // NOPMD (direct store of values)
		AbstractMonitoringRecord.checkArray(values, TYPES);
		this.version = (String) values[0];
		this.controllerName = (String) values[1];
		this.hostname = (String) values[2];
		this.experimentId = (Integer) values[3];
		this.debugMode = (Boolean) values[4];
		this.timeOffset = (Long) values[5];
		this.timeUnit = (String) values[6];
		this.numberOfRecords = (Long) values[7];
	}

	/**
	 * This constructor uses the given array to initialize the fields of this record.
	 * 
	 * @param values
	 *            The values for the record.
	 * @param valueTypes
	 *            The types of the elements in the first array.
	 *
	 * @deprecated to be removed 1.15
	 */
	@Deprecated
	protected KiekerMetadataRecord(final Object[] values, final Class<?>[] valueTypes) { // NOPMD (values stored directly)
		AbstractMonitoringRecord.checkArray(values, valueTypes);
		this.version = (String) values[0];
		this.controllerName = (String) values[1];
		this.hostname = (String) values[2];
		this.experimentId = (Integer) values[3];
		this.debugMode = (Boolean) values[4];
		this.timeOffset = (Long) values[5];
		this.timeUnit = (String) values[6];
		this.numberOfRecords = (Long) values[7];
	}

	
	/**
	 * @param deserializer
	 *            The deserializer to use
	 * @throws RecordInstantiationException 
	 *            when the record could not be deserialized
	 */
	public KiekerMetadataRecord(final IValueDeserializer deserializer) throws RecordInstantiationException {
		this.version = deserializer.getString();
		this.controllerName = deserializer.getString();
		this.hostname = deserializer.getString();
		this.experimentId = deserializer.getInt();
		this.debugMode = deserializer.getBoolean();
		this.timeOffset = deserializer.getLong();
		this.timeUnit = deserializer.getString();
		this.numberOfRecords = deserializer.getLong();
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * @deprecated to be removed in 1.15
	 */
	@Override
	@Deprecated
	public Object[] toArray() {
		return new Object[] {
			this.getVersion(),
			this.getControllerName(),
			this.getHostname(),
			this.getExperimentId(),
			this.isDebugMode(),
			this.getTimeOffset(),
			this.getTimeUnit(),
			this.getNumberOfRecords(),
		};
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void serialize(final IValueSerializer serializer) throws BufferOverflowException {
		//super.serialize(serializer);
		serializer.putString(this.getVersion());
		serializer.putString(this.getControllerName());
		serializer.putString(this.getHostname());
		serializer.putInt(this.getExperimentId());
		serializer.putBoolean(this.isDebugMode());
		serializer.putLong(this.getTimeOffset());
		serializer.putString(this.getTimeUnit());
		serializer.putLong(this.getNumberOfRecords());
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
	public String[] getValueNames() {
		return PROPERTY_NAMES; // NOPMD
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
	 * @deprecated to be rmeoved in 1.15
	 */
	@Override
	@Deprecated
	public void initFromArray(final Object[] values) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		
		final KiekerMetadataRecord castedRecord = (KiekerMetadataRecord) obj;
		if (this.getLoggingTimestamp() != castedRecord.getLoggingTimestamp()) {
			return false;
		}
		if (!this.getVersion().equals(castedRecord.getVersion())) {
			return false;
		}
		if (!this.getControllerName().equals(castedRecord.getControllerName())) {
			return false;
		}
		if (!this.getHostname().equals(castedRecord.getHostname())) {
			return false;
		}
		if (this.getExperimentId() != castedRecord.getExperimentId()) {
			return false;
		}
		if (this.isDebugMode() != castedRecord.isDebugMode()) {
			return false;
		}
		if (this.getTimeOffset() != castedRecord.getTimeOffset()) {
			return false;
		}
		if (!this.getTimeUnit().equals(castedRecord.getTimeUnit())) {
			return false;
		}
		if (this.getNumberOfRecords() != castedRecord.getNumberOfRecords()) {
			return false;
		}
		
		return true;
	}
	
	public final String getVersion() {
		return this.version;
	}
	
	
	public final String getControllerName() {
		return this.controllerName;
	}
	
	
	public final String getHostname() {
		return this.hostname;
	}
	
	
	public final int getExperimentId() {
		return this.experimentId;
	}
	
	
	public final boolean isDebugMode() {
		return this.debugMode;
	}
	
	
	public final long getTimeOffset() {
		return this.timeOffset;
	}
	
	
	public final String getTimeUnit() {
		return this.timeUnit;
	}
	
	
	public final long getNumberOfRecords() {
		return this.numberOfRecords;
	}
	
}
