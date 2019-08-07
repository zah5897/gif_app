package com.zah.app.model.type;

/**
 * Created by zah on 2017/6/7.
 */
// 最新，最热，搞笑，屌丝，可爱，萌，斗图，表情，跳舞妹子


public enum ChannelType {
    Newest(0, "最新"),
    Hottest(1, "最热"),
    Funny(2, "搞笑"),
    Loser(3, "屌丝"),
    Lovely(4, "可爱"),
    Adorable(5, "萌"),
    Bucket(6, "斗图"),
    Expression(7, "表情"),
    Dancing_Gril(8, "跳舞妹子");

    private String _title;
    private int _value;

    private ChannelType(int value, String title) {
        _value = value;
        _title = title;
    }

    public String getTitle() {
        return _title;
    }

    public int getValue() {
        return _value;
    }
}
