package com.shiro.test.mvc.base.Bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shiro.test.mvc.pojo.CommodityInformationKey;

import java.math.BigDecimal;
import java.util.Date;

public class PositionCommodityCountInformationBean extends CommodityInformationKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.commodity_id
     *
     * @mbggenerated
     */
    private String commodityId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_commodity_information.id
     *
     * @return the value of tbl_commodity_information.id
     *
     * @mbggenerated
     */
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.create_date
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.update_by
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.update_date
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.subordinate_departments
     *
     * @mbggenerated
     */
    private String subordinateDepartments;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.affiliated_company
     *
     * @mbggenerated
     */
    private String affiliatedCompany;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.commodity_name
     *
     * @mbggenerated
     */
    private String commodityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.commodity_abbreviation
     *
     * @mbggenerated
     */
    private String commodityAbbreviation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.commodity_code
     *
     * @mbggenerated
     */
    private String commodityCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.commodity_mn
     *
     * @mbggenerated
     */
    private String commodityMn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.commodity_specs
     *
     * @mbggenerated
     */
    private String commoditySpecs;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.commodity_color
     *
     * @mbggenerated
     */
    private String commodityColor;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.commodity_attribute
     *
     * @mbggenerated
     */
    private String commodityAttribute;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.commodity_bar_code
     *
     * @mbggenerated
     */
    private String commodityBarCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.supplier_name
     *
     * @mbggenerated
     */
    private String supplierName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.supplier_id
     *
     * @mbggenerated
     */
    private String supplierId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.supplier_phone
     *
     * @mbggenerated
     */
    private String supplierPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.supplier_code
     *
     * @mbggenerated
     */
    private String supplierCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.commodity_brand
     *
     * @mbggenerated
     */
    private String commodityBrand;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.commodity_category
     *
     * @mbggenerated
     */
    private String commodityCategory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.commodity_subgroup
     *
     * @mbggenerated
     */
    private String commoditySubgroup;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.storage_temperature
     *
     * @mbggenerated
     */
    private String storageTemperature;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.removable
     *
     * @mbggenerated
     */
    private Integer removable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.encoder_number
     *
     * @mbggenerated
     */
    private Integer encoderNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.encoder_height
     *
     * @mbggenerated
     */
    private Integer encoderHeight;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.production_date
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date productionDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.qgp
     *
     * @mbggenerated
     */
    private Integer qgp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.commdity_images
     *
     * @mbggenerated
     */
    private String commdityImages;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.company
     *
     * @mbggenerated
     */
    private String company;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.disassemble_number
     *
     * @mbggenerated
     */
    private Long disassembleNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.number_volume_ratio
     *
     * @mbggenerated
     */
    private Double numberVolumeRatio;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.number_gw_ratio
     *
     * @mbggenerated
     */
    private Double numberGwRatio;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.number_nw_ratio
     *
     * @mbggenerated
     */
    private Double numberNwRatio;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.dimension_unit
     *
     * @mbggenerated
     */
    private String dimensionUnit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.item_length
     *
     * @mbggenerated
     */
    private Double itemLength;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.item_wide
     *
     * @mbggenerated
     */
    private Double itemWide;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.item_high
     *
     * @mbggenerated
     */
    private Double itemHigh;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.fcl_length
     *
     * @mbggenerated
     */
    private Double fclLength;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.fcl_wide
     *
     * @mbggenerated
     */
    private Double fclWide;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.fcl_high
     *
     * @mbggenerated
     */
    private Double fclHigh;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.single_volume
     *
     * @mbggenerated
     */
    private Double singleVolume;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.total_volume
     *
     * @mbggenerated
     */
    private Double totalVolume;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.commodity_remark
     *
     * @mbggenerated
     */
    private String commodityRemark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.gross_weight
     *
     * @mbggenerated
     */
    private Long grossWeight;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.net_weight
     *
     * @mbggenerated
     */
    private Long netWeight;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.commodity_unit_price
     *
     * @mbggenerated
     */
    private BigDecimal commodityUnitPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.storage_unit
     *
     * @mbggenerated
     */
    private Integer storageUnit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.commodity_total_price
     *
     * @mbggenerated
     */
    private BigDecimal commodityTotalPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.commodity_storage_type
     *
     * @mbggenerated
     */
    private Integer commodityStorageType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_commodity_information.mass_unit
     *
     * @mbggenerated
     */
    private String massUnit;

    private  Integer Count;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getCommodityId() {
        return commodityId;
    }

    @Override
    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getSubordinateDepartments() {
        return subordinateDepartments;
    }

    public void setSubordinateDepartments(String subordinateDepartments) {
        this.subordinateDepartments = subordinateDepartments;
    }

    public String getAffiliatedCompany() {
        return affiliatedCompany;
    }

    public void setAffiliatedCompany(String affiliatedCompany) {
        this.affiliatedCompany = affiliatedCompany;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityAbbreviation() {
        return commodityAbbreviation;
    }

    public void setCommodityAbbreviation(String commodityAbbreviation) {
        this.commodityAbbreviation = commodityAbbreviation;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getCommodityMn() {
        return commodityMn;
    }

    public void setCommodityMn(String commodityMn) {
        this.commodityMn = commodityMn;
    }

    public String getCommoditySpecs() {
        return commoditySpecs;
    }

    public void setCommoditySpecs(String commoditySpecs) {
        this.commoditySpecs = commoditySpecs;
    }

    public String getCommodityColor() {
        return commodityColor;
    }

    public void setCommodityColor(String commodityColor) {
        this.commodityColor = commodityColor;
    }

    public String getCommodityAttribute() {
        return commodityAttribute;
    }

    public void setCommodityAttribute(String commodityAttribute) {
        this.commodityAttribute = commodityAttribute;
    }

    public String getCommodityBarCode() {
        return commodityBarCode;
    }

    public void setCommodityBarCode(String commodityBarCode) {
        this.commodityBarCode = commodityBarCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getCommodityBrand() {
        return commodityBrand;
    }

    public void setCommodityBrand(String commodityBrand) {
        this.commodityBrand = commodityBrand;
    }

    public String getCommodityCategory() {
        return commodityCategory;
    }

    public void setCommodityCategory(String commodityCategory) {
        this.commodityCategory = commodityCategory;
    }

    public String getCommoditySubgroup() {
        return commoditySubgroup;
    }

    public void setCommoditySubgroup(String commoditySubgroup) {
        this.commoditySubgroup = commoditySubgroup;
    }

    public String getStorageTemperature() {
        return storageTemperature;
    }

    public void setStorageTemperature(String storageTemperature) {
        this.storageTemperature = storageTemperature;
    }

    public Integer getRemovable() {
        return removable;
    }

    public void setRemovable(Integer removable) {
        this.removable = removable;
    }

    public Integer getEncoderNumber() {
        return encoderNumber;
    }

    public void setEncoderNumber(Integer encoderNumber) {
        this.encoderNumber = encoderNumber;
    }

    public Integer getEncoderHeight() {
        return encoderHeight;
    }

    public void setEncoderHeight(Integer encoderHeight) {
        this.encoderHeight = encoderHeight;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Integer getQgp() {
        return qgp;
    }

    public void setQgp(Integer qgp) {
        this.qgp = qgp;
    }

    public String getCommdityImages() {
        return commdityImages;
    }

    public void setCommdityImages(String commdityImages) {
        this.commdityImages = commdityImages;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getDisassembleNumber() {
        return disassembleNumber;
    }

    public void setDisassembleNumber(Long disassembleNumber) {
        this.disassembleNumber = disassembleNumber;
    }

    public Double getNumberVolumeRatio() {
        return numberVolumeRatio;
    }

    public void setNumberVolumeRatio(Double numberVolumeRatio) {
        this.numberVolumeRatio = numberVolumeRatio;
    }

    public Double getNumberGwRatio() {
        return numberGwRatio;
    }

    public void setNumberGwRatio(Double numberGwRatio) {
        this.numberGwRatio = numberGwRatio;
    }

    public Double getNumberNwRatio() {
        return numberNwRatio;
    }

    public void setNumberNwRatio(Double numberNwRatio) {
        this.numberNwRatio = numberNwRatio;
    }

    public String getDimensionUnit() {
        return dimensionUnit;
    }

    public void setDimensionUnit(String dimensionUnit) {
        this.dimensionUnit = dimensionUnit;
    }

    public Double getItemLength() {
        return itemLength;
    }

    public void setItemLength(Double itemLength) {
        this.itemLength = itemLength;
    }

    public Double getItemWide() {
        return itemWide;
    }

    public void setItemWide(Double itemWide) {
        this.itemWide = itemWide;
    }

    public Double getItemHigh() {
        return itemHigh;
    }

    public void setItemHigh(Double itemHigh) {
        this.itemHigh = itemHigh;
    }

    public Double getFclLength() {
        return fclLength;
    }

    public void setFclLength(Double fclLength) {
        this.fclLength = fclLength;
    }

    public Double getFclWide() {
        return fclWide;
    }

    public void setFclWide(Double fclWide) {
        this.fclWide = fclWide;
    }

    public Double getFclHigh() {
        return fclHigh;
    }

    public void setFclHigh(Double fclHigh) {
        this.fclHigh = fclHigh;
    }

    public Double getSingleVolume() {
        return singleVolume;
    }

    public void setSingleVolume(Double singleVolume) {
        this.singleVolume = singleVolume;
    }

    public Double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }

    public String getCommodityRemark() {
        return commodityRemark;
    }

    public void setCommodityRemark(String commodityRemark) {
        this.commodityRemark = commodityRemark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Long grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Long getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Long netWeight) {
        this.netWeight = netWeight;
    }

    public BigDecimal getCommodityUnitPrice() {
        return commodityUnitPrice;
    }

    public void setCommodityUnitPrice(BigDecimal commodityUnitPrice) {
        this.commodityUnitPrice = commodityUnitPrice;
    }

    public Integer getStorageUnit() {
        return storageUnit;
    }

    public void setStorageUnit(Integer storageUnit) {
        this.storageUnit = storageUnit;
    }

    public BigDecimal getCommodityTotalPrice() {
        return commodityTotalPrice;
    }

    public void setCommodityTotalPrice(BigDecimal commodityTotalPrice) {
        this.commodityTotalPrice = commodityTotalPrice;
    }

    public Integer getCommodityStorageType() {
        return commodityStorageType;
    }

    public void setCommodityStorageType(Integer commodityStorageType) {
        this.commodityStorageType = commodityStorageType;
    }

    public String getMassUnit() {
        return massUnit;
    }

    public void setMassUnit(String massUnit) {
        this.massUnit = massUnit;
    }

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }
}