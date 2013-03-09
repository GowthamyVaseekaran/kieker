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

package kieker.monitoring.writer.filesystem.async;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.BlockingQueue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import kieker.common.logging.Log;
import kieker.common.logging.LogFactory;
import kieker.common.record.IMonitoringRecord;
import kieker.common.util.filesystem.FSConstants;
import kieker.monitoring.core.controller.IMonitoringController;
import kieker.monitoring.core.registry.RegistryRecord;
import kieker.monitoring.writer.AbstractAsyncThread;
import kieker.monitoring.writer.filesystem.map.StringMappingFileWriter;

/**
 * @author Jan Waller
 * 
 * @since 1.7
 */
public abstract class AbstractZipWriterThread extends AbstractAsyncThread implements FSConstants {
	private static final Log LOG = LogFactory.getLog(AbstractZipWriterThread.class);

	protected String fileExtension = NORMAL_FILE_EXTENSION;
	protected final ZipOutputStream zipOutputStream;

	private final StringMappingFileWriter mappingFileWriter;
	private final String zipFileName;
	private final int maxEntriesInFile;

	private final DateFormat dateFormat;

	private int entriesInCurrentFileCounter;

	private long previousFileDate;
	private long sameFilenameCounter;

	public AbstractZipWriterThread(final IMonitoringController monitoringController, final BlockingQueue<IMonitoringRecord> writeQueue,
			final StringMappingFileWriter mappingFileWriter, final String path, final int maxEntriesInFile, final int level) throws IOException {
		super(monitoringController, writeQueue);
		this.mappingFileWriter = mappingFileWriter;
		this.maxEntriesInFile = maxEntriesInFile;
		// Force to initialize first file!
		this.entriesInCurrentFileCounter = maxEntriesInFile;
		// initialize Date
		this.dateFormat = new SimpleDateFormat("yyyyMMdd'-'HHmmssSSS", Locale.US);
		this.dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		// create zip file
		this.zipFileName = new File(path).getAbsolutePath() + File.separatorChar + FILE_PREFIX + '-'
				+ this.dateFormat.format(new java.util.Date(System.currentTimeMillis())) + "-UTC-" // NOPMD (Date)
				+ monitoringController.getHostname() + "-" + monitoringController.getName() + "-" + this.getName() + ZIP_FILE_EXTENSION;
		this.zipOutputStream = new ZipOutputStream(new FileOutputStream(this.zipFileName));
		this.zipOutputStream.setLevel(level);
		this.zipOutputStream.closeEntry();
	}

	@Override
	protected final void cleanup() {
		PrintWriter pw = null;
		try {
			this.cleanupForNextEntry();
			this.zipOutputStream.putNextEntry(new ZipEntry(MAP_FILENAME));
			pw = new PrintWriter(new OutputStreamWriter(this.zipOutputStream, ENCODING));
			// if there is more than one writer thread we might miss some entries here!
			pw.print(this.mappingFileWriter.toString());
			pw.flush();
			this.cleanupFinal();
			this.zipOutputStream.close();
		} catch (final IOException ex) {
			LOG.error("Error finalizing logging zip file: " + this.zipFileName, ex);
		} finally {
			if (null != pw) {
				pw.close();
			}
		}
	}

	protected final String getFilename() {
		final long date = System.currentTimeMillis();
		if (this.previousFileDate == date) {
			this.sameFilenameCounter++;
		} else {
			this.sameFilenameCounter = 0;
			this.previousFileDate = date;
		}
		final StringBuilder sb = new StringBuilder(FILE_PREFIX.length() + this.fileExtension.length() + 27);
		sb.append(FILE_PREFIX).append('-').append(this.dateFormat.format(new java.util.Date(date))).append("-UTC-") // NOPMD (Date)
				.append(String.format("%03d", this.sameFilenameCounter)).append(this.fileExtension);
		return sb.toString();
	}

	@Override
	protected final void consume(final IMonitoringRecord monitoringRecord) throws Exception {
		if (monitoringRecord instanceof RegistryRecord) {
			this.mappingFileWriter.write((RegistryRecord) monitoringRecord);
		} else {
			if (++this.entriesInCurrentFileCounter > this.maxEntriesInFile) { // NOPMD
				this.entriesInCurrentFileCounter = 1;
				this.cleanupForNextEntry();
				this.zipOutputStream.putNextEntry(new ZipEntry(this.getFilename()));
			}
			this.write(monitoringRecord);
		}
	}

	protected abstract void write(IMonitoringRecord monitoringRecord) throws IOException;

	protected abstract void cleanupForNextEntry() throws IOException;

	protected abstract void cleanupFinal() throws IOException;

	@Override
	public final String toString() {
		final StringBuilder sb = new StringBuilder(128);
		sb.append(super.toString());
		sb.append("; Writing to File: '");
		sb.append(this.zipFileName);
		sb.append('\'');
		return sb.toString();
	}
}
