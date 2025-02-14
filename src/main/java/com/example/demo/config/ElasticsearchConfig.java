package com.example.demo.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

    @Value("${spring.elasticsearch.uris}")
    private String elasticsearchUris; // 예: "localhost:9200"

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        // 1) Low Level REST Client 생성
        RestClientBuilder builder = RestClient.builder(HttpHost.create(elasticsearchUris));
        RestClient restClient = builder.build();

        // 2) Transport 생성 (JSON 직렬화/역직렬화를 위해 JacksonJsonpMapper 사용)
        ElasticsearchTransport transport = new RestClientTransport(
                restClient,
                new JacksonJsonpMapper()
        );

        // 3) 최종 ElasticsearchClient 생성
        return new ElasticsearchClient(transport);
    }
}
