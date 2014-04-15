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

package kieker.test.tools.junit.writeRead.jms;

import java.io.Serializable;

import javax.jms.BytesMessage;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TemporaryQueue;
import javax.jms.TemporaryTopic;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

/**
 * This class is part of a very basic fake JMS message broker. It uses a very simple design to deliver messages synchronously from a singleton producer to a
 * singleton consumer. It has only been designed for test purposes ({@link BasicJMSWriterReaderTest}) and should <b>not</b> be used outside this test.
 * 
 * @author Nils Christian Ehmke
 * 
 * @since 1.8
 */
public class FakeSession implements Session {

	private static final FakeMessageConsumer CONSUMER = new FakeMessageConsumer();
	private static final FakeMessageProducer PRODUCER = new FakeMessageProducer(CONSUMER);

	/**
	 * Default constructor.
	 */
	public FakeSession() {
		// No code necessary
	}

	/**
	 * {@inheritDoc}
	 */
	public void close() throws JMSException {
		// No code necessary
	}

	/**
	 * {@inheritDoc}
	 */
	public void commit() throws JMSException {
		// No code necessary
	}

	/**
	 * {@inheritDoc}
	 */
	public QueueBrowser createBrowser(final Queue arg0) throws JMSException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public QueueBrowser createBrowser(final Queue arg0, final String arg1) throws JMSException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public BytesMessage createBytesMessage() throws JMSException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public MessageConsumer createConsumer(final Destination arg0) throws JMSException {
		return CONSUMER;
	}

	/**
	 * {@inheritDoc}
	 */
	public MessageConsumer createConsumer(final Destination arg0, final String arg1) throws JMSException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public MessageConsumer createConsumer(final Destination arg0, final String arg1, final boolean arg2) throws JMSException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public TopicSubscriber createDurableSubscriber(final Topic arg0, final String arg1) throws JMSException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public TopicSubscriber createDurableSubscriber(final Topic arg0, final String arg1, final String arg2, final boolean arg3) throws JMSException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public MapMessage createMapMessage() throws JMSException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Message createMessage() throws JMSException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public ObjectMessage createObjectMessage() throws JMSException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public ObjectMessage createObjectMessage(final Serializable object) throws JMSException {
		final ObjectMessage message = new FakeObjectMessage();
		message.setObject(object);
		return message;
	}

	/**
	 * {@inheritDoc}
	 */
	public MessageProducer createProducer(final Destination arg0) throws JMSException {
		return PRODUCER;
	}

	/**
	 * {@inheritDoc}
	 */
	public Queue createQueue(final String arg0) throws JMSException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public StreamMessage createStreamMessage() throws JMSException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public TemporaryQueue createTemporaryQueue() throws JMSException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public TemporaryTopic createTemporaryTopic() throws JMSException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public TextMessage createTextMessage() throws JMSException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public TextMessage createTextMessage(final String arg0) throws JMSException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Topic createTopic(final String arg0) throws JMSException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getAcknowledgeMode() throws JMSException {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public MessageListener getMessageListener() throws JMSException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getTransacted() throws JMSException { // NOPMD (get -> is)
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void recover() throws JMSException {
		// No code necessary
	}

	/**
	 * {@inheritDoc}
	 */
	public void rollback() throws JMSException {
		// No code necessary
	}

	/**
	 * {@inheritDoc}
	 */
	public void run() {
		// No code necessary
	}

	/**
	 * {@inheritDoc}
	 */
	public void setMessageListener(final MessageListener arg0) throws JMSException {
		// No code necessary
	}

	/**
	 * {@inheritDoc}
	 */
	public void unsubscribe(final String arg0) throws JMSException {
		// No code necessary
	}

}
