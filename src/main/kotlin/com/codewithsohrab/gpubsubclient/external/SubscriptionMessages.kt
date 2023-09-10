package com.codewithsohrab.gpubsubclient.external

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate
import org.springframework.cloud.gcp.pubsub.integration.AckMode
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter
import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage
import org.springframework.cloud.gcp.pubsub.support.GcpPubSubHeaders
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.channel.DirectChannel
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandler

@Configuration
class SubscriptionMessages {

    private val logger = LoggerFactory.getLogger(SubscriptionMessages::class.java)

    @Bean
    fun pubsubInputChannel() : MessageChannel {
        return DirectChannel()
    }

    @Bean
    @ServiceActivator(inputChannel = "pubsubInputChannel")
    fun messageReceiver() : MessageHandler {
        return MessageHandler { message ->
            val originalMessage: BasicAcknowledgeablePubsubMessage? = message.headers.get(
                GcpPubSubHeaders.ORIGINAL_MESSAGE,
                BasicAcknowledgeablePubsubMessage::class.java
            )
            logger.info(String(message.payload as ByteArray))
            originalMessage?.ack()
        }
    }

    @Bean
    fun messageChannelAdapter(
        @Qualifier("pubsubInputChannel")
        inputChannel: MessageChannel,
        pubSubTemplate: PubSubTemplate?
    ): PubSubInboundChannelAdapter? {
        val adapter = PubSubInboundChannelAdapter(pubSubTemplate, "test-01-sub")
        adapter.outputChannel = inputChannel
        adapter.ackMode = AckMode.MANUAL
        return adapter
    }
}