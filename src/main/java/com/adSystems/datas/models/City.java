package com.adSystems.datas.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Cities")
public class City {
    @Id
    private String id;
    private String name;
    private String region;
}
