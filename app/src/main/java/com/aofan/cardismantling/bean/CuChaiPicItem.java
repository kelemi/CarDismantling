package com.aofan.cardismantling.bean;

import java.io.Serializable;

/**粗拆图片item
 * Created by Administrator on 2017/2/7.
 */

public class CuChaiPicItem implements Serializable {

    private String localPath;

    private String remotePath;


    public CuChaiPicItem() {
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    @Override
    public String toString() {
        return "CuChaiPicItem{" +
                "localPath='" + localPath + '\'' +
                ", remotePath='" + remotePath + '\'' +
                '}';
    }
}
