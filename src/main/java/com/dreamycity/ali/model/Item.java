package com.dreamycity.ali.model;

public class Item {
    private int item_id;
    private String name;
    private String short_name;
    private String category;
    private String image;
    private String suggest_price;
    private String cost;
    private String quantity;
    private String unit;
    private String supplier;
    private String link;
    private String reoder_level;
    private int is_serialized;
    private int deleted;
    private String comment;

    public Item() {

    }

    public Item(String line,String supplier) {
        String[] temp = line.split(",");
        this.name = temp[0];
        this.short_name = "";
        this.category = "";
        this.image = temp[4];
        this.suggest_price = Integer.toString(Math.round(Float.valueOf(temp[1])*240));
        this.cost = Integer.toString(Math.round(Float.valueOf(temp[1])*100));
        this.quantity = temp[2];
        this.unit = temp[3];
        this.supplier = supplier;
        this.link = temp[5];

        this.reoder_level = "";
        this.is_serialized = 0;
        this.deleted = 0;
        this.comment = "";
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getReorder_level() {
        return reoder_level;
    }

    public void setReorder_level(String reoder_level) {
        this.reoder_level = reoder_level;
    }

    public int getIs_serialized() {
        return is_serialized;
    }

    public void setIs_serialized(int is_serialized) {
        this.is_serialized = is_serialized;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getSuggest_price() {
        return suggest_price;
    }

    public void setSuggest_price(String suggest_price) {
        this.suggest_price = suggest_price;
    }
}
