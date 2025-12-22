package com.adSystems.datas.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Categorys")
public class Category {
    @Id
    private String id;
    private String name;
    private String parentId;
}
