package com.shiro.test.mvc.base.application.web.json;

import java.util.List;

public class DatagridResult {

    private long total;

    private List<?> rows;

    private List<?> footer;

    /**
     * @return the total
     */
    public long getTotal() {
        return total;
    }

    /**
     * @param total
     *            the total to set
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * @return the rows
     */
    public List<?> getRows() {
        return rows;
    }

    /**
     * @param rows
     *            the rows to set
     */
    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    /**
     * @return the footer
     */
    public List<?> getFooter() {
        return footer;
    }

    /**
     * @param footer
     *            the footer to set
     */
    public void setFooter(List<?> footer) {
        this.footer = footer;
    }

}
