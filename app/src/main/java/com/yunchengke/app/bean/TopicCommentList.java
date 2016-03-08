package com.yunchengke.app.bean;

import java.util.List;

/**
 * 类名：TopicCommentList <br/>
 * 描述：话题注释列表
 * 创建时间：2016/01/10 14:36
 *
 * @author hanter
 * @version 1.0
 */
public class TopicCommentList extends BaseEntity {

    /**
     * total : 10
     * rows : [{"X6_Product_Id":304,"X6_Product_Admin":169,"Field_PLYH":"andy","Field_PLSJ":"2016-1-4 14:59:44","Field_PLNR":"不错.","TonyX7_Pager_RowRank":1},{"X6_Product_Id":303,"X6_Product_Admin":169,"Field_PLYH":"andy","Field_PLSJ":"2016-1-4 14:59:31","Field_PLNR":"不错.","TonyX7_Pager_RowRank":2},{"X6_Product_Id":302,"X6_Product_Admin":0,"Field_PLYH":"","Field_PLSJ":"2016-1-4 14:59:29","Field_PLNR":"不错.","TonyX7_Pager_RowRank":3},{"X6_Product_Id":301,"X6_Product_Admin":1,"Field_PLYH":"","Field_PLSJ":"2016-1-4 14:42:04","Field_PLNR":"试试看","TonyX7_Pager_RowRank":4},{"X6_Product_Id":285,"X6_Product_Admin":1,"Field_PLYH":"","Field_PLSJ":"2015-12-31 16:53:07","Field_PLNR":"aaaa","TonyX7_Pager_RowRank":5},{"X6_Product_Id":284,"X6_Product_Admin":1,"Field_PLYH":"","Field_PLSJ":"2015-12-31 15:44:03","Field_PLNR":"我是评论","TonyX7_Pager_RowRank":6},{"X6_Product_Id":283,"X6_Product_Admin":1,"Field_PLYH":"","Field_PLSJ":"2015-12-31 11:29:30","Field_PLNR":2222,"TonyX7_Pager_RowRank":7},{"X6_Product_Id":277,"X6_Product_Admin":1,"Field_PLYH":"","Field_PLSJ":"2015-12-31 11:16:09","Field_PLNR":"哈哈哈","TonyX7_Pager_RowRank":8},{"X6_Product_Id":271,"X6_Product_Admin":1,"Field_PLYH":"","Field_PLSJ":"2015-12-30 16:40:43","Field_PLNR":"我来评","TonyX7_Pager_RowRank":9},{"X6_Product_Id":258,"X6_Product_Admin":281,"Field_PLYH":13251011471,"Field_PLSJ":"2015-12-29 20:32:30","Field_PLNR":"哈哈","TonyX7_Pager_RowRank":10}]
     * PageCount : 1
     * page : 1
     * time :  耗时：0.063 秒
     */

    private int total;
    private int PageCount;
    private int page;
    private String time;
    /**
     * X6_Product_Id : 304
     * X6_Product_Admin : 169
     * Field_PLYH : andy
     * Field_PLSJ : 2016-1-4 14:59:44
     * Field_PLNR : 不错.
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
