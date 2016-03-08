package com.yunchengke.app.bean.city;

import java.util.List;

/**
 * Created by Administrator on 2016/1/30.
 */
public class CityType  {


    /**
     * total : 18
     * rows : [{"X6_Product_Id":1,"Field_HDFL":"工喜您有了"},{"X6_Product_Id":2,"Field_HDFL":"我要请客"},{"X6_Product_Id":3,"Field_HDFL":"我要送礼"},{"X6_Product_Id":4,"Field_HDFL":"电影"},{"X6_Product_Id":5,"Field_HDFL":"讲座"},{"X6_Product_Id":6,"Field_HDFL":"展览"},{"X6_Product_Id":7,"Field_HDFL":"运动"},{"X6_Product_Id":8,"Field_HDFL":"公益"},{"X6_Product_Id":9,"Field_HDFL":"旅行"},{"X6_Product_Id":10,"Field_HDFL":"论坛"},{"X6_Product_Id":11,"Field_HDFL":"商业"},{"X6_Product_Id":12,"Field_HDFL":"其他"},{"X6_Product_Id":41,"Field_HDFL":"房产"},{"X6_Product_Id":42,"Field_HDFL":"艺术品"},{"X6_Product_Id":43,"Field_HDFL":"其它"},{"X6_Product_Id":46,"Field_HDFL":"聚会"},{"X6_Product_Id":47,"Field_HDFL":"音乐"},{"X6_Product_Id":48,"Field_HDFL":"戏剧"}]
     */

    private int total;
    /**
     * X6_Product_Id : 1
     * Field_HDFL : 工喜您有了
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
        private String Field_HDFL;

        public void setX6_Product_Id(int X6_Product_Id) {
            this.X6_Product_Id = X6_Product_Id;
        }

        public void setField_HDFL(String Field_HDFL) {
            this.Field_HDFL = Field_HDFL;
        }

        public int getX6_Product_Id() {
            return X6_Product_Id;
        }

        public String getField_HDFL() {
            return Field_HDFL;
        }


    }


}
