package com.yunchengke.app.bean;

import java.util.List;

/**
 * 名称: GroupList <br/>
 * 描述: 话题列表 <br/>
 * 创建时间：2016/1/6 10:43
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class GroupList extends BaseEntity {

    /**
     * total : 2
     * rows : [{"X6_Product_Id":50,"X6_Product_Pic":"/X_UserUpload/day_151229/201512291604315746.jpg","Field_XZBT":"测试小组数据","Field_XZJJ":"测试小组数据","Field_YHSL":2,"TonyX7_Pager_RowRank":1},{"X6_Product_Id":49,"X6_Product_Pic":"","Field_XZBT":"乐活","Field_XZJJ":"健康生活方式","Field_YHSL":2,"TonyX7_Pager_RowRank":2}]
     * PageCount : 1
     * page : 1
     * time :  耗时：0.047 秒
     */

    private int total;
    private int PageCount;
    private int page;
    private String time;
    /**
     * X6_Product_Id : 50
     * X6_Product_Pic : /X_UserUpload/day_151229/201512291604315746.jpg
     * Field_XZBT : 测试小组数据
     * Field_XZJJ : 测试小组数据
     * Field_YHSL : 2
     * TonyX7_Pager_RowRank : 1
     */

    private List<RowsEntity> rows;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPageCount(int PageCount) {
        this.PageCount = PageCount;
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

    public int getPageCount() {
        return PageCount;
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

    public static class RowsEntity extends BaseEntity {
        private int X6_Product_Id;
        private String X6_Product_Pic;
        private String Field_XZBT;
        private String Field_XZJJ;
        private int Field_YHSL;
        private int TonyX7_Pager_RowRank;

        public void setX6_Product_Id(int X6_Product_Id) {
            this.X6_Product_Id = X6_Product_Id;
        }

        public void setX6_Product_Pic(String X6_Product_Pic) {
            this.X6_Product_Pic = X6_Product_Pic;
        }

        public void setField_XZBT(String Field_XZBT) {
            this.Field_XZBT = Field_XZBT;
        }

        public void setField_XZJJ(String Field_XZJJ) {
            this.Field_XZJJ = Field_XZJJ;
        }

        public void setField_YHSL(int Field_YHSL) {
            this.Field_YHSL = Field_YHSL;
        }

        public void setTonyX7_Pager_RowRank(int TonyX7_Pager_RowRank) {
            this.TonyX7_Pager_RowRank = TonyX7_Pager_RowRank;
        }

        public int getX6_Product_Id() {
            return X6_Product_Id;
        }

        public String getX6_Product_Pic() {
            return X6_Product_Pic;
        }

        public String getField_XZBT() {
            return Field_XZBT;
        }

        public String getField_XZJJ() {
            return Field_XZJJ;
        }

        public int getField_YHSL() {
            return Field_YHSL;
        }

        public int getTonyX7_Pager_RowRank() {
            return TonyX7_Pager_RowRank;
        }
    }
}
