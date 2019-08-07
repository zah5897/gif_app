package com.zah.app.model;

import com.zah.app.comm.annotation.Id;
import com.zah.app.comm.annotation.Strategy;
import com.zah.app.comm.annotation.Table;
import com.zah.app.model.type.ChannelType;

import java.util.List;

/**
 * Created by zah on 2017/7/19.
 */
@Table(name = "t_gif")
public class GifModel {
    @Id(strategy = Strategy.UUID)
    private String id;
    private String description;
    private String img_url;
    private int channel;
    private int flag; //0为正常状态，1为ios固定接口数据

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
