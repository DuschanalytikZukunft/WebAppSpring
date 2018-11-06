package com.webforms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "listusernew")
public class Item implements Serializable {

    @Id
    @GeneratedValue
    Long id;
    Long userId, itemId;
    String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isValid(){
        return text != null && itemId != null && userId != null;
    }

    @Override
    public String toString() {
        return "list{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", userId=" + userId +
                ", text=" + text +
                '}';
    }
}