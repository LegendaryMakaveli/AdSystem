package com.adSystems.datas.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Categories")
public class Category {
    @Id
    private String id;
    private String name;
    @Indexed
    private String parentId;
}
