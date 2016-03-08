package com.yunchengke.app.bean.city;

import com.yunchengke.app.bean.BaseEntity;

import java.util.List;

/**
 * 作者：Tinchy
 * 创建时间：2016/2/3 14:33
 * 描述：
 * 版本：1.0
 */
public class YCKJoinedList extends BaseEntity {


    /**
     * total : 7
     * rows : [{"X6_Admin_Id":1,"X6_Product_Pic":null,"Field_YHNC":"用户昵称","TonyX7_Pager_RowRank":1}]
     * PageCount : 1
     * page :
     * time :  耗时：0.047 秒
     */

    private int total;
    private int PageCount;
    private String page;
    private String time;
    /**
     * X6_Admin_Id : 1
     * X6_Product_Pic : null
     * Field_YHNC : 用户昵称
     * TonyX7_Pager_RowRank : 1
     */

    private List<RowsEntity> rows;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPageCount(int PageCount) {
        this.PageCount = PageCount;
    }

    public void setPage(String page) {
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

    public String getPage() {
        return page;
    }

    public String getTime() {
        return time;
    }

    public List<RowsEntity> getRows() {
        return rows;
    }

    public static class RowsEntity {
        private int X6_Admin_Id;

        public String getJoinTime() {
            return JoinTime;
        }

        public void setJoinTime(String joinTime) {
            JoinTime = joinTime;
        }

        private String JoinTime;
        private String Field_YHNC;
        private int TonyX7_Pager_RowRank;

        public void setX6_Admin_Id(int X6_Admin_Id) {
            this.X6_Admin_Id = X6_Admin_Id;
        }



        public void setField_YHNC(String Field_YHNC) {
            this.Field_YHNC = Field_YHNC;
        }

        public void setTonyX7_Pager_RowRank(int TonyX7_Pager_RowRank) {
            this.TonyX7_Pager_RowRank = TonyX7_Pager_RowRank;
        }

        public int getX6_Admin_Id() {
            return X6_Admin_Id;
        }


        public String getField_YHNC() {
            return Field_YHNC;
        }

        public int getTonyX7_Pager_RowRank() {
            return TonyX7_Pager_RowRank;
        }
    }
}
