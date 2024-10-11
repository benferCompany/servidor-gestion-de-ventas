package com.servidor.gestiondeventas.entities.mercadopago.payments.dto;

import com.servidor.gestiondeventas.entities.mercadopago.payments.Items;

import lombok.Data;
@Data
public class ItemDTO {
    private Long idItem;
    private String categoryId;
    private String description;
    private String pictureUrl;
    private Long id;
    private int quantity;
    private String title;
    private double unitPrice;
    public static ItemDTO fromEntity(Items item){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setCategoryId(item.getCategoryId());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setId(item.getId());
        itemDTO.setIdItem(item.getIdItem());
        itemDTO.setPictureUrl(item.getPictureUrl());
        itemDTO.setQuantity(item.getQuantity());
        itemDTO.setTitle(item.getTitle());
        itemDTO.setUnitPrice(item.getUnitPrice());
        return itemDTO;
    }
}
