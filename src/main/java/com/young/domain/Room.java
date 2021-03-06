package com.young.domain;

public class Room {
    private Integer id;
    private String photo;
    private String roomnum;
    private Integer roomtypeid;
    private Integer floorid;
    //房间状态(1-已预订 2-已入住 3-可预订)
    private Integer status;
    private String roomrequirement;
    private String remark;
    private String roomdesc;

    //房型名称
    private String typename;
    //楼层名称
    private String floorname;
    //房间状态描述
    private String statusStr;
    //房间价格
    private double price;
    //床数量
    private Integer bednum;

    public Integer getBednum() {
        return bednum;
    }

    public void setBednum(Integer bednum) {
        this.bednum = bednum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatusStr() {
        if (status != null){
            switch (status){
                case 1 :
                    statusStr = "可预订";
                break;
                case 2 :
                    statusStr = "已预定";
                    break;
                case 3 :
                    statusStr = "已入住";
                    break;
            }
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        if (status != null){
            switch (status){
                case 1 :
                    statusStr = "可预订";
                    break;
                case 2 :
                    statusStr = "已预定";
                    break;
                case 3 :
                    statusStr = "已入住";
                    break;
            }
        }
        this.statusStr = statusStr;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getFloorname() {
        return floorname;
    }

    public void setFloorname(String floorname) {
        this.floorname = floorname;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }

    public Integer getRoomtypeid() {
        return roomtypeid;
    }

    public void setRoomtypeid(Integer roomtypeid) {
        this.roomtypeid = roomtypeid;
    }

    public Integer getFloorid() {
        return floorid;
    }

    public void setFloorid(Integer floorid) {
        this.floorid = floorid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRoomrequirement() {
        return roomrequirement;
    }

    public void setRoomrequirement(String roomrequirement) {
        this.roomrequirement = roomrequirement;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRoomdesc() {
        return roomdesc;
    }

    public void setRoomdesc(String roomdesc) {
        this.roomdesc = roomdesc;
    }
}
