package com.yunchengke.app.bean;

import java.util.List;

/**
 * 类名：TopicList <br/>
 * 描述：话题列表
 * 创建时间：2016/01/08 21:43
 *
 * @author hanter
 * @version 1.0
 */
public class TopicList extends BaseEntity {

    /**
     * total : 10
     * rows : [{"X6_Product_Id":47,"X6_Product_Pic":"/X_UserUpload/day_160105/201601051237057736_small.jpg","Field_YHID":0,"Field_HTBT":"我是测试","Field_HTCJR":"","Field_HTCJSJ":"2016-1-5 12:56:59","Field_PLSL":1,"TonyX7_Pager_RowRank":1},{"X6_Product_Id":46,"X6_Product_Pic":"/X_UserUpload/day_160105/201601051237057736_small.jpg","Field_YHID":169,"Field_HTBT":"我是测试","Field_HTCJR":"andy","Field_HTCJSJ":"2016-1-5 12:56:59","Field_PLSL":0,"TonyX7_Pager_RowRank":2},{"X6_Product_Id":45,"X6_Product_Pic":"/X_UserUpload/day_160105/201601051237057736_small.jpg","Field_YHID":176,"Field_HTBT":"我是测试","Field_HTCJR":"董蕾","Field_HTCJSJ":"2016-1-5 12:56:48","Field_PLSL":0,"TonyX7_Pager_RowRank":3},{"X6_Product_Id":44,"X6_Product_Pic":"/X_UserUpload/day_160105/201601051237057736_small.jpg","Field_YHID":176,"Field_HTBT":"我是测试","Field_HTCJR":"董蕾","Field_HTCJSJ":"2016-1-5 12:55:28","Field_PLSL":0,"TonyX7_Pager_RowRank":4},{"X6_Product_Id":43,"X6_Product_Pic":"/X_UserUpload/day_160105/201601051237057736_small.jpg","Field_YHID":176,"Field_HTBT":"我是测试","Field_HTCJR":"董蕾","Field_HTCJSJ":"2016-1-5 12:55:26","Field_PLSL":0,"TonyX7_Pager_RowRank":5},{"X6_Product_Id":42,"X6_Product_Pic":"/X_UserUpload/day_160105/201601051237057736_small.jpg","Field_YHID":0,"Field_HTBT":"我是测试","Field_HTCJR":"","Field_HTCJSJ":"2016-1-5 12:55:26","Field_PLSL":0,"TonyX7_Pager_RowRank":6},{"X6_Product_Id":41,"X6_Product_Pic":"aaa","Field_YHID":176,"Field_HTBT":"我是测试","Field_HTCJR":"董蕾","Field_HTCJSJ":"2016-1-5 12:55:12","Field_PLSL":0,"TonyX7_Pager_RowRank":7},{"X6_Product_Id":40,"X6_Product_Pic":"aaa","Field_YHID":176,"Field_HTBT":"我是测试","Field_HTCJR":"董蕾","Field_HTCJSJ":"2016-1-5 12:53:46","Field_PLSL":0,"TonyX7_Pager_RowRank":8},{"X6_Product_Id":39,"X6_Product_Pic":"","Field_YHID":272,"Field_HTBT":"啦啦啦","Field_HTCJR":18815291912,"Field_HTCJSJ":"2015-12-29 16:29:33","Field_PLSL":10,"TonyX7_Pager_RowRank":9},{"X6_Product_Id":38,"X6_Product_Pic":"","Field_YHID":272,"Field_HTBT":"肯","Field_HTCJR":18815291912,"Field_HTCJSJ":"2015-12-29 16:13:11","Field_PLSL":4,"TonyX7_Pager_RowRank":10}]
     * PageCount : 1
     * page : 1
     * time :  耗时：0.125 秒
     */

    private int total;
    private int PageCount;
    private int page;
    private String time;
    /**
     * X6_Product_Id : 47
     * X6_Product_Pic : /X_UserUpload/day_160105/201601051237057736_small.jpg
     * Field_Pic : /X_UserUpload/day_160105/201601051237057736_small.jpg
     * Field_YHID : 0
     * Field_HTBT : 我是测试
     * Field_HTCJR :
     * Field_HTCJSJ : 2016-1-5 12:56:59
     * Field_PLSL : 1
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
        private String X6_Product_Pic;
        private String Field_Pic;
        private int Field_YHID;
        private String Field_HTBT;
        private String Field_HTCJR;
        private String Field_HTCJSJ;
        private int Field_PLSL;
        private int TonyX7_Pager_RowRank;

        public String getField_Pic() {
            return Field_Pic;
        }

        public void setField_Pic(String field_Pic) {
            Field_Pic = field_Pic;
        }

        public void setX6_Product_Id(int X6_Product_Id) {
            this.X6_Product_Id = X6_Product_Id;
        }

        public void setX6_Product_Pic(String X6_Product_Pic) {
            this.X6_Product_Pic = X6_Product_Pic;
        }

        public void setField_YHID(int Field_YHID) {
            this.Field_YHID = Field_YHID;
        }

        public void setField_HTBT(String Field_HTBT) {
            this.Field_HTBT = Field_HTBT;
        }

        public void setField_HTCJR(String Field_HTCJR) {
            this.Field_HTCJR = Field_HTCJR;
        }

        public void setField_HTCJSJ(String Field_HTCJSJ) {
            this.Field_HTCJSJ = Field_HTCJSJ;
        }

        public void setField_PLSL(int Field_PLSL) {
            this.Field_PLSL = Field_PLSL;
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

        public int getField_YHID() {
            return Field_YHID;
        }

        public String getField_HTBT() {
            return Field_HTBT;
        }

        public String getField_HTCJR() {
            return Field_HTCJR;
        }

        public String getField_HTCJSJ() {
            return Field_HTCJSJ;
        }

        public int getField_PLSL() {
            return Field_PLSL;
        }

        public int getTonyX7_Pager_RowRank() {
            return TonyX7_Pager_RowRank;
        }
    }
}
