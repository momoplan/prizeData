package com.ruyicai.prizedata.jms;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.ruyicai.prizedata.domain.PrizeInfo;

@Service("myQueueProducer")
public class PrizeInfoQueueProducer {

	@Resource
	private JmsTemplate jmsTemplate;
	
	@Resource(name="myQueue")
	private Destination destination;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public void send(final PrizeInfo prizeInfo){
		this.jmsTemplate.send(destination,new MessageCreator() {   
            public Message createMessage(Session session) throws JMSException {   

            	TextMessage message = session.createTextMessage();
            	message.setText(prizeInfo.toJson());
                
                return (Message) message;   
            }   
        });
	}
	
}
