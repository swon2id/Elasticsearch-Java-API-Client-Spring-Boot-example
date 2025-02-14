package com.example.demo.service;

import com.example.demo.document.EcommerceDocument;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EcommerceService {

    private final ElasticsearchClient client;

    // 단일 필드 검색: category 필드에서 keyword 검색 후 source만 반환
    public List<EcommerceDocument> searchKeyword(String keyword) throws IOException {
        SearchResponse<EcommerceDocument> response = client.search(s -> s
                        .index("kibana_sample_data_ecommerce")
                        .query(q -> q.match(m -> m.field("category").query(keyword))),
                EcommerceDocument.class);
        return response.hits().hits().stream()
                .map(hit -> hit.source())
                .collect(Collectors.toList());
    }

    // 다중 필드 검색: 지정된 필드들에서 keyword 검색 후 source만 반환
    public List<EcommerceDocument> searchKeyword(String keyword, List<String> fields) throws IOException {
        SearchResponse<EcommerceDocument> response = client.search(s -> s
                        .index("kibana_sample_data_ecommerce")
                        .query(q -> q.multiMatch(m -> m.query(keyword).fields(fields))),
                EcommerceDocument.class);
        return response.hits().hits().stream()
                .map(hit -> hit.source())
                .collect(Collectors.toList());
    }

    // 집계 예시: taxful_total_price의 평균값 계산 (Aggregation 결과는 그대로 반환)
    public SearchResponse<EcommerceDocument> aggregation() throws IOException {
        return client.search(s -> s
                        .index("kibana_sample_data_ecommerce")
                        .aggregations("avg_taxful_total_price", a -> a
                                .avg(avg -> avg.field("taxful_total_price"))
                        ),
                EcommerceDocument.class);
    }

    // 전체 조회: 모든 문서를 검색 후 source만 반환
    public List<EcommerceDocument> findAll() throws IOException {
        SearchResponse<EcommerceDocument> response = client.search(s -> s
                        .index("kibana_sample_data_ecommerce")
                        .query(q -> q.matchAll(m -> m)),
                EcommerceDocument.class);
        return response.hits().hits().stream()
                .map(hit -> hit.source())
                .collect(Collectors.toList());
    }
}
