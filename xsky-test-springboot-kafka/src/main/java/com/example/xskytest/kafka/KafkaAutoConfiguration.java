/*
 * Created by baili on 2021/10/09.
 */
package com.example.xskytest.kafka;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * https://docs.confluent.io/platform/current/installation/configuration/producer-configs.html#
 *
 * @author baili
 * @date 2021/10/09.
 */
@Configuration
public class KafkaAutoConfiguration implements EnvironmentPostProcessor {

	private static final Logger log = LoggerFactory.getLogger(KafkaAutoConfiguration.class);

	private static final String KAFKA_DEFAULT_PROPERTIES_SOURCE_NAME = "KafkaDefaultPropertiesSource";
	private static final String KAFKA_ADDRESS_NAME                   = "kafka.address";
	// common
	private static final String KAFKA_BOOTSTRAP_SERVERS              = "spring.kafka.bootstrap-servers";
	private static final String KAFKA_CONSUMER_BOOTSTRAP_SERVERS     = "spring.kafka.consumer.bootstrap-servers";
	private static final String KAFKA_PRODUCER_BOOTSTRAP_SERVERS     = "spring.kafka.producer.bootstrap-servers";
	private static final String KAFKA_CLIENT_ID                      = "spring.kafka.client-id";
	private static final String KAFKA_CONSUMER_CLIENT_ID             = "spring.kafka.consumer.client-id";
	private static final String KAFKA_PRODUCER_CLIENT_ID             = "spring.kafka.producer.client-id";
	// producer
	private static final String KAFKA_PRODUCER_KEY_SERIALIZER        = "spring.kafka.producer.key-serializer";
	private static final String KAFKA_PRODUCER_VALUE_SERIALIZER      = "spring.kafka.producer.value-serializer";
	private static final String KAFKA_PRODUCER_ACKS                  = "spring.kafka.producer.acks";
	private static final String KAFKA_PRODUCER_BATCH_SIZE            = "spring.kafka.producer.batch-size";
	private static final String KAFKA_PRODUCER_BUFFER_MEMORY         = "spring.kafka.producer.buffer-memory";
	private static final String KAFKA_PRODUCER_RETRIES               = "spring.kafka.producer.retries";
	// consumer
	private static final String KAFKA_CONSUMER_KEY_DESERIALIZER      = "spring.kafka.consumer.key-deserializer";
	private static final String KAFKA_CONSUMER_VALUE_DESERIALIZER    = "spring.kafka.consumer.value-deserializer";
	private static final String KAFKA_CONSUMER_GROUP_ID              = "spring.kafka.consumer.group-id";
	private static final String KAFKA_CONSUMER_AUTO_COMMIT_INTERVAL  = "spring.kafka.consumer.auto-commit-interval";
	private static final String KAFKA_CONSUMER_AUTO_OFFSET_RESET     = "spring.kafka.consumer.auto-offset-reset";
	private static final String KAFKA_CONSUMER_ENABLE_AUTO_COMMIT    = "spring.kafka.consumer.enable-auto-commit";
	private static final String KAFKA_CONSUMER_FETCH_MAX_WAIT        = "spring.kafka.consumer.fetch-max-wait";
	private static final String KAFKA_CONSUMER_MAX_POLL_RECORDS      = "spring.kafka.consumer.max-poll-records";

	private static final String DEFAULT_KAFKA_STRING_SERIALIZER   = "org.apache.kafka.common.serialization.StringSerializer";
	private static final String DEFAULT_KAFKA_STRING_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		Map<String, Object> defaultProperties = new HashMap<>();

		// producer
		setProducerProperties(environment, defaultProperties);
		// consumer
		setConsumerProperties(environment, defaultProperties);

		environment.getPropertySources().addLast(new MapPropertySource(KAFKA_DEFAULT_PROPERTIES_SOURCE_NAME, defaultProperties));

		log.info("初始化Kafka客户端Producer配置, addres:{}", environment.getProperty(KAFKA_PRODUCER_BOOTSTRAP_SERVERS));
		log.info("初始化Kafka客户端Consumer配置, addres:{}", environment.getProperty(KAFKA_CONSUMER_BOOTSTRAP_SERVERS));
	}

	@Bean
	@ConditionalOnMissingBean
	public RecordMessageConverter stringJsonMessageConverter() {
		return new StringJsonMessageConverter();
	}

	@SuppressWarnings({"DuplicatedCode", "ConstantConditions"})
	private void setProducerProperties(ConfigurableEnvironment environment, Map<String, Object> defaultProperties) {
		/*String clientId = environment.getProperty(KAFKA_PRODUCER_CLIENT_ID, environment.getProperty(KAFKA_CLIENT_ID));
		if (StringUtils.isBlank(clientId)) {
			defaultProperties.put(KAFKA_PRODUCER_CLIENT_ID, buildClientId("producer"));
		}*/
		String bootstrapServers = environment.getProperty(KAFKA_PRODUCER_BOOTSTRAP_SERVERS, environment.getProperty(KAFKA_BOOTSTRAP_SERVERS));
		if (StringUtils.isBlank(bootstrapServers)) {
			defaultProperties.put(KAFKA_PRODUCER_BOOTSTRAP_SERVERS, getKafkaAddress(environment));
		}
		String keySerializer = environment.getProperty(KAFKA_PRODUCER_KEY_SERIALIZER);
		if (StringUtils.isBlank(keySerializer)) {
			defaultProperties.put(KAFKA_PRODUCER_KEY_SERIALIZER, DEFAULT_KAFKA_STRING_SERIALIZER);
		}
		String valueSerializer = environment.getProperty(KAFKA_PRODUCER_VALUE_SERIALIZER);
		if (StringUtils.isBlank(valueSerializer)) {
			defaultProperties.put(KAFKA_PRODUCER_VALUE_SERIALIZER, DEFAULT_KAFKA_STRING_SERIALIZER);
		}
		/*String acks = environment.getProperty(KAFKA_PRODUCER_ACKS);
		if (StringUtils.isBlank(acks)) {
			defaultProperties.put(KAFKA_PRODUCER_ACKS, "all");
		}*/
		/*String batchSize = environment.getProperty(KAFKA_PRODUCER_BATCH_SIZE);
		if (StringUtils.isBlank(batchSize)) {
			defaultProperties.put(KAFKA_PRODUCER_BATCH_SIZE, "");
		}*/
		/*String bufferMemory = environment.getProperty(KAFKA_PRODUCER_BUFFER_MEMORY);
		if (StringUtils.isBlank(bufferMemory)) {
			defaultProperties.put(KAFKA_PRODUCER_BUFFER_MEMORY, "");
		}*/
		/*String retries = environment.getProperty(KAFKA_PRODUCER_RETRIES);
		if (StringUtils.isBlank(retries)) {
			defaultProperties.put(KAFKA_PRODUCER_RETRIES, "0");
		}*/
	}

	@SuppressWarnings({"DuplicatedCode", "ConstantConditions"})
	private void setConsumerProperties(ConfigurableEnvironment environment, Map<String, Object> defaultProperties) {
		/*String clientId = environment.getProperty(KAFKA_CONSUMER_CLIENT_ID, environment.getProperty(KAFKA_CLIENT_ID));
		if (StringUtils.isBlank(clientId)) {
			defaultProperties.put(KAFKA_CONSUMER_CLIENT_ID, buildClientId("consumer"));
		}*/
		String bootstrapServers = environment.getProperty(KAFKA_CONSUMER_BOOTSTRAP_SERVERS, environment.getProperty(KAFKA_BOOTSTRAP_SERVERS));
		if (StringUtils.isBlank(bootstrapServers)) {
			defaultProperties.put(KAFKA_CONSUMER_BOOTSTRAP_SERVERS, getKafkaAddress(environment));
		}
		String keyDeserializer = environment.getProperty(KAFKA_CONSUMER_KEY_DESERIALIZER);
		if (StringUtils.isBlank(keyDeserializer)) {
			defaultProperties.put(KAFKA_CONSUMER_KEY_DESERIALIZER, DEFAULT_KAFKA_STRING_DESERIALIZER);
		}
		String valueDeserializer = environment.getProperty(KAFKA_CONSUMER_VALUE_DESERIALIZER);
		if (StringUtils.isBlank(valueDeserializer)) {
			defaultProperties.put(KAFKA_CONSUMER_VALUE_DESERIALIZER, DEFAULT_KAFKA_STRING_DESERIALIZER);
		}
		String groupId = environment.getProperty(KAFKA_CONSUMER_GROUP_ID);
		if (StringUtils.isBlank(groupId)) {
			//defaultProperties.put(KAFKA_CONSUMER_GROUP_ID, BailiAppContext.get().getAppName());
			defaultProperties.put(KAFKA_CONSUMER_GROUP_ID, "XskyTestAccessApplication");
		}
		/*String autoCommitInterval = environment.getProperty(KAFKA_CONSUMER_AUTO_COMMIT_INTERVAL);
		if (StringUtils.isBlank(autoCommitInterval)) {
			defaultProperties.put(KAFKA_CONSUMER_AUTO_COMMIT_INTERVAL, "");
		}
		String autoOffsetReset = environment.getProperty(KAFKA_CONSUMER_AUTO_OFFSET_RESET);
		if (StringUtils.isBlank(autoOffsetReset)) {
			defaultProperties.put(KAFKA_CONSUMER_AUTO_OFFSET_RESET, "");
		}
		String enableAutoCommit = environment.getProperty(KAFKA_CONSUMER_ENABLE_AUTO_COMMIT);
		if (StringUtils.isBlank(enableAutoCommit)) {
			defaultProperties.put(KAFKA_CONSUMER_ENABLE_AUTO_COMMIT, "");
		}
		String fetchMaxWait = environment.getProperty(KAFKA_CONSUMER_FETCH_MAX_WAIT);
		if (StringUtils.isBlank(fetchMaxWait)) {
			defaultProperties.put(KAFKA_CONSUMER_FETCH_MAX_WAIT, "");
		}
		String maxPollRecords = environment.getProperty(KAFKA_CONSUMER_MAX_POLL_RECORDS);
		if (StringUtils.isBlank(maxPollRecords)) {
			defaultProperties.put(KAFKA_CONSUMER_MAX_POLL_RECORDS, "");
		}*/
	}

	private String getKafkaAddress(ConfigurableEnvironment environment) {
		String address = environment.getProperty(KAFKA_ADDRESS_NAME);
		if (StringUtils.isBlank(address)) {
			throw new IllegalArgumentException(
					String.format("当前应用未设置Kafka连接地址，可通过修改Spring配置文件(application.yml)添加配置项['%s']设置Kafka连接地址", KAFKA_ADDRESS_NAME));
		}
		return address;
	}

	/*private String buildClientId(String prefix) {
		return prefix + "-" + BailiAppContext.get().getAppName() + "-" + BailiAppContext.get().getHostIp() + "@" + PidUtils.getPid();
	}*/

}
