package com.shiro.test.mvc.pojo;

public class ShelfPositionInformationKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_shelf_position_information.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_shelf_position_information.shelf_position_id
     *
     * @mbggenerated
     */
    private String shelfPositionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_shelf_position_information.shelf_position_code
     *
     * @mbggenerated
     */
    private String shelfPositionCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_shelf_position_information.id
     *
     * @return the value of tbl_shelf_position_information.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_shelf_position_information.id
     *
     * @param id the value for tbl_shelf_position_information.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_shelf_position_information.shelf_position_id
     *
     * @return the value of tbl_shelf_position_information.shelf_position_id
     *
     * @mbggenerated
     */
    public String getShelfPositionId() {
        return shelfPositionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_shelf_position_information.shelf_position_id
     *
     * @param shelfPositionId the value for tbl_shelf_position_information.shelf_position_id
     *
     * @mbggenerated
     */
    public void setShelfPositionId(String shelfPositionId) {
        this.shelfPositionId = shelfPositionId == null ? null : shelfPositionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_shelf_position_information.shelf_position_code
     *
     * @return the value of tbl_shelf_position_information.shelf_position_code
     *
     * @mbggenerated
     */
    public String getShelfPositionCode() {
        return shelfPositionCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_shelf_position_information.shelf_position_code
     *
     * @param shelfPositionCode the value for tbl_shelf_position_information.shelf_position_code
     *
     * @mbggenerated
     */
    public void setShelfPositionCode(String shelfPositionCode) {
        this.shelfPositionCode = shelfPositionCode == null ? null : shelfPositionCode.trim();
    }
}