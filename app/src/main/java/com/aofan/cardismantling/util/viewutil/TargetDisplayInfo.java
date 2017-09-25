package com.aofan.cardismantling.util.viewutil;

/**
 * Created by Administrator on 2015/9/21.
 */
public class TargetDisplayInfo {

    private int targetWidth;
    private int targetHeight;

    public TargetDisplayInfo(int targetWidth, int targetHeight) {
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
    }

    public int getTargetHeight() {
        return targetHeight;
    }

    public void setTargetHeight(int targetHeight) {
        this.targetHeight = targetHeight;
    }

    public int getTargetWidth() {
        return targetWidth;
    }

    public void setTargetWidth(int targetWidth) {
        this.targetWidth = targetWidth;
    }
}
