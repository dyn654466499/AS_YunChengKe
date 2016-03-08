package com.yunchengke.app.bean;

import java.util.List;

/**
 * 类名：GroupDetail <br/>
 * 描述：小组详情
 * 创建时间：2016/01/10 10:42
 *
 * @author hanter
 * @version 1.0
 */
public class GroupDetail extends BaseEntity {


    /**
     * total : 1
     * rows : [{"X6_Product_Id":50,"Field_XZBT":"测试小组数据","Field_XZCJR":"andy","Field_YHSL":2,"Field_XZJJ":"测试小组数据","Field_YHID":1,"X6_Product_Pic":"/X_UserUpload/day_151229/201512291604315746.jpg","Field_Pic":"/X_UserUpload/day_151229/201512291604384056.jpg","X6_Product_Recommend":",1,","iStatus":1}]
     */

    private int total;
    /**
     * X6_Product_Id : 50
     * Field_XZBT : 测试小组数据
     * Field_XZCJR : andy
     * Field_YHSL : 2
     * Field_XZJJ : 测试小组数据
     * Field_YHID : 1
     * X6_Product_Pic : /X_UserUpload/day_151229/201512291604315746.jpg
     * Field_Pic : /X_UserUpload/day_151229/201512291604384056.jpg
     * X6_Product_Recommend : ,1,
     * iStatus : 1
     */

    private List<RowsEntity> rows;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRows(List<RowsEntity> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public List<RowsEntity> getRows() {
        return rows;
    }

    public static class RowsEntity {
        private int X6_Product_Id;
        private String Field_XZBT;
        private String Field_XZCJR;
        private int Field_YHSL;
        private String Field_XZJJ;
        private int Field_YHID;
        private String X6_Product_Pic;
        private String Field_Pic;
        private String X6_Product_Recommend;
        private int iStatus;

        public void setX6_Product_Id(int X6_Product_Id) {
            this.X6_Product_Id = X6_Product_Id;
        }

        public void setField_XZBT(String Field_XZBT) {
            this.Field_XZBT = Field_XZBT;
        }

        public void setField_XZCJR(String Field_XZCJR) {
            this.Field_XZCJR = Field_XZCJR;
        }

        public void setField_YHSL(int Field_YHSL) {
            this.Field_YHSL = Field_YHSL;
        }

        public void setField_XZJJ(String Field_XZJJ) {
            this.Field_XZJJ = Field_XZJJ;
        }

        public void setField_YHID(int Field_YHID) {
            this.Field_YHID = Field_YHID;
        }

        public void setX6_Product_Pic(String X6_Product_Pic) {
            this.X6_Product_Pic = X6_Product_Pic;
        }

        public void setField_Pic(String Field_Pic) {
            this.Field_Pic = Field_Pic;
        }

        public void setX6_Product_Recommend(String X6_Product_Recommend) {
            this.X6_Product_Recommend = X6_Product_Recommend;
        }

        public void setIStatus(int iStatus) {
            this.iStatus = iStatus;
        }

        public int getX6_Product_Id() {
            return X6_Product_Id;
        }

        public String getField_XZBT() {
            return Field_XZBT;
        }

        public String getField_XZCJR() {
            return Field_XZCJR;
        }

        public int getField_YHSL() {
            return Field_YHSL;
        }

        public String getField_XZJJ() {
            return Field_XZJJ;
        }

        public int getField_YHID() {
            return Field_YHID;
        }

        public String getX6_Product_Pic() {
            return X6_Product_Pic;
        }

        public String getField_Pic() {
            return Field_Pic;
        }

        public String getX6_Product_Recommend() {
            return X6_Product_Recommend;
        }

        public int getIStatus() {
            return iStatus;
        }
    }
}
