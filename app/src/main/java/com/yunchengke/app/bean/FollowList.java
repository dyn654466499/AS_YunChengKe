package com.yunchengke.app.bean;

import java.util.List;

/**
 * 名称: FollowList <br/>
 * 描述: 关注列表 <br/>
 * 创建时间：2016/1/28 16:33
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class FollowList extends BaseEntity {


    /**
     * total : 2
     * rows : [{"X6_Product_Admin":null,"X6_Admin_Id":null,"TonyX7_Pager_RowRank":1},{"X6_Product_Admin":"管理员001","X6_Admin_Id":2,"TonyX7_Pager_RowRank":2}]
     * PageCount : 1
     * page : 1
     * time :  耗时：0.031 秒
     */

    private int total;
    private int PageCount;
    private int page;
    private String time;
    /**
     * X6_Product_Admin : null
     * X6_Admin_Id : null
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
        private String X6_Product_Admin;
        private long X6_Admin_Id;
        private int TonyX7_Pager_RowRank;

        public void setX6_Product_Admin(String X6_Product_Admin) {
            this.X6_Product_Admin = X6_Product_Admin;
        }

        public void setX6_Admin_Id(long X6_Admin_Id) {
            this.X6_Admin_Id = X6_Admin_Id;
        }

        public void setTonyX7_Pager_RowRank(int TonyX7_Pager_RowRank) {
            this.TonyX7_Pager_RowRank = TonyX7_Pager_RowRank;
        }

        public String getX6_Product_Admin() {
            return X6_Product_Admin;
        }

        public long getX6_Admin_Id() {
            return X6_Admin_Id;
        }

        public int getTonyX7_Pager_RowRank() {
            return TonyX7_Pager_RowRank;
        }
    }
}
