package com.example.demo.controller;

import com.example.demo.document.EcommerceDocument;
import com.example.demo.service.EcommerceService;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/elastic/ecommerce")
public class EcommerceController {

    private final EcommerceService ecommerceService;

    // 단일 필드 검색 엔드포인트
    @GetMapping("/search")
    public ResponseEntity<?> searchKeyword(@RequestParam String keyword, @RequestParam String field) throws IOException {
        List<EcommerceDocument> docs = ecommerceService.searchKeyword(keyword, List.of(field));
        return ResponseEntity.ok(docs.get(0));
    }

    // 다중 필드 검색 엔드포인트
    @GetMapping("/multi-search")
    public ResponseEntity<?> searchByKeyword(@RequestParam String keyword,
                                             @RequestParam(required = false) String fields) throws IOException {
        List<String> fieldList = fields != null ? Arrays.asList(fields.split(",")) : List.of("category");
        List<EcommerceDocument> docs = ecommerceService.searchKeyword(keyword, fieldList);
        return ResponseEntity.ok(docs);
    }

    // 집계 엔드포인트
    @GetMapping("/aggregation")
    public ResponseEntity<?> aggregation() throws IOException {
        SearchResponse<EcommerceDocument> response = ecommerceService.aggregation();
        return ResponseEntity.ok(response);
    }

    // 전체 조회 엔드포인트
    @GetMapping("/all")
    public ResponseEntity<?> getAll() throws IOException {
        List<EcommerceDocument> docs = ecommerceService.findAll();
        return ResponseEntity.ok(docs);
    }
}
