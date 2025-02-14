package com.example.demo.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;


// Camel Case로 작성된 클래스 필드와 Snake Case로 작성된 JSON 데이터 매핑
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
// 알 수 없는 속성 무시
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EcommerceDocument {
    @JsonProperty("_id") // JSON의 _id 필드를 직접 매핑
    private String id;

    private List<String> category;
    private String currency;
    private String customerFirstName;
    private String customerFullName;
    private String customerGender;
    private Integer customerId;
    private String customerLastName;
    private String customerPhone;
    private String dayOfWeek;
    private Integer dayOfWeekI;
    private String email;
    private List<String> manufacturer;
    private String orderDate;
    private Long orderId;
    private List<Product> products;
    private Double taxfulTotalPrice;
    private Double taxlessTotalPrice;
    private Integer totalQuantity;
    private Integer totalUniqueProducts;
    private String type;
    private String user;
    private GeoIp geoip;
    private Event event;

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {
        private Double basePrice;
        private Integer discountPercentage;
        private Integer quantity;
        private String manufacturer;
        private Double taxAmount;
        private Integer productId;
        private String category;
        private String sku;
        private Double taxlessPrice;
        private Double unitDiscountAmount;
        private Double minPrice;

        @JsonProperty("_id") // JSON의 _id 필드를 직접 매핑
        private String id;
        private Double discountAmount;
        private String createdOn;
        private String productName;
        private Double price;
        private Double taxfulPrice;
        private Double baseUnitPrice;
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GeoIp {
        private String countryIsoCode;
        private Location location;
        private String regionName;
        private String continentName;
        private String cityName;
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Location {
        private Double lon;
        private Double lat;
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Event {
        private String dataset;
    }
}