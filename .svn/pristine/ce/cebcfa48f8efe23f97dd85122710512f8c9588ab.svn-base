package com.yunchengke.app.bean;

import java.util.List;

/**
 * 类名：MyUserInfo <br/>
 * 描述：用户关注信息
 * 创建时间：2016/01/28 20:28
 *
 * @author hanter
 * @version 1.0
 */
public class MyUserInfo extends BaseEntity {


    /**
     * total : 1
     * rows : [{"follow_Me":0,"followed":0,"Field_SZCS":null,"X6_Admin_UserType":1,"isFollow":0}]
     * Myself : 1
     */

    private int total;
    private int Myself;
    /**
     * follow_Me : 0
     * followed : 0
     * Field_SZCS : null
     * X6_Admin_UserType : 1
     * isFollow : 0
     */

    private List<RowsEntity> rows;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setMyself(int Myself) {
        this.Myself = Myself;
    }

    public void setRows(List<RowsEntity> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public int getMyself() {
        return Myself;
    }

    public List<RowsEntity> getRows() {
        return rows;
    }

    public static class RowsEntity extends BaseEntity {
        private int follow_Me;
        private int followed;
        private String Field_SZCS;
        private int X6_Admin_UserType;
        private int isFollow;

        public void setFollow_Me(int follow_Me) {
            this.follow_Me = follow_Me;
        }

        public void setFollowed(int followed) {
            this.followed = followed;
        }

        public void setField_SZCS(String Field_SZCS) {
            this.Field_SZCS = Field_SZCS;
        }

        public void setX6_Admin_UserType(int X6_Admin_UserType) {
            this.X6_Admin_UserType = X6_Admin_UserType;
        }

        public void setIsFollow(int isFollow) {
            this.isFollow = isFollow;
        }

        public int getFollow_Me() {
            return follow_Me;
        }

        public int getFollowed() {
            return followed;
        }

        public String getField_SZCS() {
            return Field_SZCS;
        }

        public int getX6_Admin_UserType() {
            return X6_Admin_UserType;
        }

        public int getIsFollow() {
            return isFollow;
        }
    }
}
