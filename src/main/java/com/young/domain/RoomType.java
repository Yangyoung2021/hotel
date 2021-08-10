package com.young.domain;

public class RoomType {

    private Integer id;
    private String typeName;
    private String photo;
    private Double price;
    private Integer livenum;
    private Integer bednum;
    private Integer roomnum;
    private Integer reservednum;
    private Integer avilablenum;
    private Integer livednum;
    private Integer status;
    private String remark;

    @Override
    public String toString() {
        return "RoomType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", photo='" + photo + '\'' +
                ", price=" + price +
                ", livenum=" + livenum +
                ", bednum=" + bednum +
                ", roomnum=" + roomnum +
                ", reservednum=" + reservednum +
                ", avilablenum=" + avilablenum +
                ", livednum=" + livednum +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getLivenum() {
        return livenum;
    }

    public void setLivenum(Integer livenum) {
        this.livenum = livenum;
    }

    public Integer getBednum() {
        return bednum;
    }

    public void setBednum(Integer bednum) {
        this.bednum = bednum;
    }

    public Integer getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(Integer roomnum) {
        this.roomnum = roomnum;
    }

    public Integer getReservednum() {
        return reservednum;
    }

    public void setReservednum(Integer reservednum) {
        this.reservednum = reservednum;
    }

    public Integer getAvilablenum() {
        return avilablenum;
    }

    public void setAvilablenum(Integer avilablenum) {
        this.avilablenum = avilablenum;
    }

    public Integer getLivednum() {
        return livednum;
    }

    public void setLivednum(Integer livednum) {
        this.livednum = livednum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
