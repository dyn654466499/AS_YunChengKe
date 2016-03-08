package com.yunchengke.app.bean.city;

import com.yunchengke.app.bean.BaseEntity;

import java.util.List;

/**
 * 作者：Tinchy
 * 创建时间：2016/2/3 13:23
 * 描述：
 * 版本：1.0
 */
public class YCKCommentList extends BaseEntity{

    /**
     * total : 2
     * rows : [{"X6_Product_Id":233,"X6_Product_Admin":1,"Field_PLYH":"","Field_PLSJ":"2015-12-28 11:22:06","Field_PLNR":"真的不错哦","TonyX7_Pager_RowRank":1}]
     * page : 1
     * time :  耗时：0.031 秒
     */

    private int total;
    private int page;
    private String time;
    /**
     * X6_Product_Id : 233
     * X6_Product_Admin : 1
     * Field_PLYH :
     * Field_PLSJ : 2015-12-28 11:22:06
     * Field_PLNR : 真的不错哦
     * TonyX7_Pager_RowRank : 1
     */

    private List<RowsEntity> rows;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setRows(List<RowsEntity> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public String getTime() {
        return time;
    }

    public List<RowsEntity> getRows() {
        return rows;
    }

    public static class RowsEntity {
        private int X6_Product_Id;
        private int X6_Product_Admin;
        private String Field_PLYH;
        private String Field_PLSJ;
        private String Field_PLNR;
        private int TonyX7_Pager_RowRank;

        public void setX6_Product_Id(int X6_Product_Id) {
            this.X6_Product_Id = X6_Product_Id;
        }

        public void setX6_Product_Admin(int X6_Product_Admin) {
            this.X6_Product_Admin = X6_Product_Admin;
        }

        public void setField_PLYH(String Field_PLYH) {
            this.Field_PLYH = Field_PLYH;
        }

        public void setField_PLSJ(String Field_PLSJ) {
            this.Field_PLSJ = Field_PLSJ;
        }

        public void setField_PLNR(String Field_PLNR) {
            this.Field_PLNR = Field_PLNR;
        }

        public void setTonyX7_Pager_RowRank(int TonyX7_Pager_RowRank) {
            this.TonyX7_Pager_RowRank = TonyX7_Pager_RowRank;
        }

        public int getX6_Product_Id() {
            return X6_Product_Id;
        }

        public int getX6_Product_Admin() {
            return X6_Product_Admin;
        }

        public String getField_PLYH() {
            return Field_PLYH;
        }

        public String getField_PLSJ() {
            return Field_PLSJ;
        }

        public String getField_PLNR() {
            return Field_PLNR;
        }

        public int getTonyX7_Pager_RowRank() {
            return TonyX7_Pager_RowRank;
        }
    }
}
