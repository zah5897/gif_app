package com.zah.app.model.node;

import com.zah.app.model.node.Node;

/**
 * Created by zah on 2017/6/7.
 */
public class TxtNode extends Node {
    public TxtNode(String content){
        type=0;
        title=content;
    }
}
