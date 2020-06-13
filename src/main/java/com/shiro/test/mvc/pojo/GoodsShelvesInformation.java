package com.shiro.test.mvc.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class GoodsShelvesInformation {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_shelves_information.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_shelves_information.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_shelves_information.create_date
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_shelves_information.update_by
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_shelves_information.update_date
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_shelves_information.goods_shelves_id
     *
     * @mbggenerated
     */
    private String goodsShelvesId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_shelves_information.goods_shelves_name
     *
     * @mbggenerated
     */
    private String goodsShelvesName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_shelves_information.warehouse_id
     *
     * @mbggenerated
     */
    private String warehouseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_shelves_information.warehouse_code
     *
     * @mbggenerated
     */
    private String warehouseCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_shelves_information.warehouse_name
     *
     * @mbggenerated
     */
    private String warehouseName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_shelves_information.opening_date
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date openingDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_shelves_information.goods_shelves_no
     *
     * @mbggenerated
     */
    private Integer goodsShelvesNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_shelves_information.goods_shelves_layer_no
     *
     * @mbggenerated
     */
    private Integer goodsShelvesLayerNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_shelves_information.goods_shelves_row_no
     *
     * @mbggenerated
     */
    private Integer goodsShelvesRowNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_shelves_information.goods_shelves_code
     *
     * @mbggenerated
     */
    private String goodsShelvesCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_shelves_information.max_capacity
     *
     * @mbggenerated
     */
    private Double maxCapacity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_goods_shelves_information.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_shelves_information.id
     *
     * @return the value of tbl_goods_shelves_information.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_shelves_information.id
     *
     * @param id the value for tbl_goods_shelves_information.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_shelves_information.create_by
     *
     * @return the value of tbl_goods_shelves_information.create_by
     *
     * @mbggenerated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_shelves_information.create_by
     *
     * @param createBy the value for tbl_goods_shelves_information.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_shelves_information.create_date
     *
     * @return the value of tbl_goods_shelves_information.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_shelves_information.create_date
     *
     * @param createDate the value for tbl_goods_shelves_information.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_shelves_information.update_by
     *
     * @return the value of tbl_goods_shelves_information.update_by
     *
     * @mbggenerated
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_shelves_information.update_by
     *
     * @param updateBy the value for tbl_goods_shelves_information.update_by
     *
     * @mbggenerated
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_shelves_information.update_date
     *
     * @return the value of tbl_goods_shelves_information.update_date
     *
     * @mbggenerated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_shelves_information.update_date
     *
     * @param updateDate the value for tbl_goods_shelves_information.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_shelves_information.goods_shelves_id
     *
     * @return the value of tbl_goods_shelves_information.goods_shelves_id
     *
     * @mbggenerated
     */
    public String getGoodsShelvesId() {
        return goodsShelvesId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_shelves_information.goods_shelves_id
     *
     * @param goodsShelvesId the value for tbl_goods_shelves_information.goods_shelves_id
     *
     * @mbggenerated
     */
    public void setGoodsShelvesId(String goodsShelvesId) {
        this.goodsShelvesId = goodsShelvesId == null ? null : goodsShelvesId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_shelves_information.goods_shelves_name
     *
     * @return the value of tbl_goods_shelves_information.goods_shelves_name
     *
     * @mbggenerated
     */
    public String getGoodsShelvesName() {
        return goodsShelvesName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_shelves_information.goods_shelves_name
     *
     * @param goodsShelvesName the value for tbl_goods_shelves_information.goods_shelves_name
     *
     * @mbggenerated
     */
    public void setGoodsShelvesName(String goodsShelvesName) {
        this.goodsShelvesName = goodsShelvesName == null ? null : goodsShelvesName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_shelves_information.warehouse_id
     *
     * @return the value of tbl_goods_shelves_information.warehouse_id
     *
     * @mbggenerated
     */
    public String getWarehouseId() {
        return warehouseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_shelves_information.warehouse_id
     *
     * @param warehouseId the value for tbl_goods_shelves_information.warehouse_id
     *
     * @mbggenerated
     */
    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId == null ? null : warehouseId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_shelves_information.warehouse_code
     *
     * @return the value of tbl_goods_shelves_information.warehouse_code
     *
     * @mbggenerated
     */
    public String getWarehouseCode() {
        return warehouseCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_shelves_information.warehouse_code
     *
     * @param warehouseCode the value for tbl_goods_shelves_information.warehouse_code
     *
     * @mbggenerated
     */
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode == null ? null : warehouseCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_shelves_information.warehouse_name
     *
     * @return the value of tbl_goods_shelves_information.warehouse_name
     *
     * @mbggenerated
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_shelves_information.warehouse_name
     *
     * @param warehouseName the value for tbl_goods_shelves_information.warehouse_name
     *
     * @mbggenerated
     */
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName == null ? null : warehouseName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_shelves_information.opening_date
     *
     * @return the value of tbl_goods_shelves_information.opening_date
     *
     * @mbggenerated
     */
    public Date getOpeningDate() {
        return openingDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_shelves_information.opening_date
     *
     * @param openingDate the value for tbl_goods_shelves_information.opening_date
     *
     * @mbggenerated
     */
    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_shelves_information.goods_shelves_no
     *
     * @return the value of tbl_goods_shelves_information.goods_shelves_no
     *
     * @mbggenerated
     */
    public Integer getGoodsShelvesNo() {
        return goodsShelvesNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_shelves_information.goods_shelves_no
     *
     * @param goodsShelvesNo the value for tbl_goods_shelves_information.goods_shelves_no
     *
     * @mbggenerated
     */
    public void setGoodsShelvesNo(Integer goodsShelvesNo) {
        this.goodsShelvesNo = goodsShelvesNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_shelves_information.goods_shelves_layer_no
     *
     * @return the value of tbl_goods_shelves_information.goods_shelves_layer_no
     *
     * @mbggenerated
     */
    public Integer getGoodsShelvesLayerNo() {
        return goodsShelvesLayerNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_shelves_information.goods_shelves_layer_no
     *
     * @param goodsShelvesLayerNo the value for tbl_goods_shelves_information.goods_shelves_layer_no
     *
     * @mbggenerated
     */
    public void setGoodsShelvesLayerNo(Integer goodsShelvesLayerNo) {
        this.goodsShelvesLayerNo = goodsShelvesLayerNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_shelves_information.goods_shelves_row_no
     *
     * @return the value of tbl_goods_shelves_information.goods_shelves_row_no
     *
     * @mbggenerated
     */
    public Integer getGoodsShelvesRowNo() {
        return goodsShelvesRowNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_shelves_information.goods_shelves_row_no
     *
     * @param goodsShelvesRowNo the value for tbl_goods_shelves_information.goods_shelves_row_no
     *
     * @mbggenerated
     */
    public void setGoodsShelvesRowNo(Integer goodsShelvesRowNo) {
        this.goodsShelvesRowNo = goodsShelvesRowNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_shelves_information.goods_shelves_code
     *
     * @return the value of tbl_goods_shelves_information.goods_shelves_code
     *
     * @mbggenerated
     */
    public String getGoodsShelvesCode() {
        return goodsShelvesCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_shelves_information.goods_shelves_code
     *
     * @param goodsShelvesCode the value for tbl_goods_shelves_information.goods_shelves_code
     *
     * @mbggenerated
     */
    public void setGoodsShelvesCode(String goodsShelvesCode) {
        this.goodsShelvesCode = goodsShelvesCode == null ? null : goodsShelvesCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_shelves_information.max_capacity
     *
     * @return the value of tbl_goods_shelves_information.max_capacity
     *
     * @mbggenerated
     */
    public Double getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_shelves_information.max_capacity
     *
     * @param maxCapacity the value for tbl_goods_shelves_information.max_capacity
     *
     * @mbggenerated
     */
    public void setMaxCapacity(Double maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_goods_shelves_information.status
     *
     * @return the value of tbl_goods_shelves_information.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_goods_shelves_information.status
     *
     * @param status the value for tbl_goods_shelves_information.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GoodsShelvesInformation{" +
                "id='" + id + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", goodsShelvesId='" + goodsShelvesId + '\'' +
                ", goodsShelvesName='" + goodsShelvesName + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", warehouseCode='" + warehouseCode + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", openingDate=" + openingDate +
                ", goodsShelvesNo=" + goodsShelvesNo +
                ", goodsShelvesLayerNo=" + goodsShelvesLayerNo +
                ", goodsShelvesRowNo=" + goodsShelvesRowNo +
                ", goodsShelvesCode='" + goodsShelvesCode + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", status=" + status +
                '}';
    }
}