package com.yunchengke.app.bean;

import java.util.List;

/**
 * 名称: GroupMemberList <br/>
 * 描述: 小组成员列表 <br/>
 * 创建时间：2016/1/14 14:10
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class GroupMemberList extends BaseEntity {

    /**
     * total : 3
     * rows : [{"Field_YHNC":15957404028,"X6_Product_Admin":16,"TonyX7_Pager_RowRank":1},{"Field_YHNC":"李雪薇","X6_Product_Admin":271,"TonyX7_Pager_RowRank":2},{"Field_YHNC":"用户昵称","X6_Product_Admin":1,"TonyX7_Pager_RowRank":3}]
     * PageCount : 1
     * page : 1
     * time :  耗时：0.031 秒
     */

    private int total;
    private int PageCount;
    private int page;
    private String time;
    /**
     * Field_YHNC : 15957404028
     * X6_Product_Admin : 16
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
        private String Field_YHNC;
        private long X6_Product_Admin;
        private long TonyX7_Pager_RowRank;

        public void setField_YHNC(String Field_YHNC) {
            this.Field_YHNC = Field_YHNC;
        }

        public void setX6_Product_Admin(int X6_Product_Admin) {
            this.X6_Product_Admin = X6_Product_Admin;
        }

        public void setTonyX7_Pager_RowRank(int TonyX7_Pager_RowRank) {
            this.TonyX7_Pager_RowRank = TonyX7_Pager_RowRank;
        }

        public String getField_YHNC() {
            return Field_YHNC;
        }

        public long getX6_Product_Admin() {
            return X6_Product_Admin;
        }

        public long getTonyX7_Pager_RowRank() {
            return TonyX7_Pager_RowRank;
        }
    }
}
