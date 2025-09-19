package com.dohuy.jpapart2.dto;

import com.dohuy.jpapart2.entity.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {

    private Long id;
    private String name;
    private List<ProductDTO> products;

    public static CategoryDTO toDTO(Category category){
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static CategoryDTO toDTOWithProducts(Category category){
        CategoryDTO categoryDTO = CategoryDTO.toDTO(category);

        if(category.getProducts() != null){
            List<ProductDTO> productDTOList = category.getProducts().stream()
                    .map(ProductDTO::toDTO)
                    .toList();
            categoryDTO.setProducts(productDTOList);
        }

        return categoryDTO;
    }
}


