package com.poly.datn.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponse implements Serializable{

    private Integer current_inventory;

    private boolean need_changed;

    private boolean outOfStock = false;

    private Integer max_quantity;


    public InventoryResponse(Integer current_inventory, boolean need_changed) {

        this.current_inventory = current_inventory;

        if(this.current_inventory == 0) 
        setOutOfStock(true);
        
        if(!need_changed && current_inventory >= 5) {
            setMax_quantity(5);
        }else {
            setMax_quantity(current_inventory);
        }

        setNeed_changed(need_changed);
    }
}
