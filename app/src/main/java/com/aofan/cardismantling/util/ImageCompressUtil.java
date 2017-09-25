package com.aofan.cardismantling.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;


import com.aofan.cardismantling.common.Config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片压缩框架
 * Created by Administrator on 2015/12/31.
 */
public class ImageCompressUtil {

    public static Bitmap compressImgFitScreen(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        // return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
        return bitmap;
    }


    public static File compressLocalPic(String originalFilePath) {

        Bitmap fitScreenBitmap = compressImgFitScreen(originalFilePath);
        //先将所选图片转化为流的形式，path所得到的图片路径
        FileInputStream is = null;
        File resultFile = null;
        if (!FileUtils.checkFileExists(Config.APP_BASE_FOLDER)) {
            FileUtils.createDirectory(Config.APP_BASE_FOLDER);
        }

        if (!FileUtils.checkFileExists(Config.APP_BASE_FOLDER + Config.IMAGE_FOLDER)) {
            FileUtils.createDirectory(Config.APP_BASE_FOLDER + Config.IMAGE_FOLDER);
        }

        //如果压缩的包里面不存在被压缩的图片，则创建压缩图片，如果存在，则直接取
        if (!FileUtils.checkFileExists(Config.APP_BASE_FOLDER + Config.IMAGE_FOLDER + File.separator + originalFilePath.substring(originalFilePath.lastIndexOf("/") + 1))) {
            resultFile = FileUtils.createFile(Environment.getExternalStorageDirectory() + Config.APP_BASE_FOLDER + Config.IMAGE_FOLDER, originalFilePath.substring(originalFilePath.lastIndexOf("/") + 1));

            try {

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                fitScreenBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);//这里100表示不压缩，将不压缩的数据存放到baos中
                LogUtil.e("beforeBitMapCompressSize:"+ baos.toByteArray().length / 1024 +"KB");

                int per = 100;
                //如果>500kb且质量>50的话，则压缩，否则不压缩
                while (baos.toByteArray().length / 1024 > 500 & per > 50) { // 循环判断如果压缩后图片是否大于500kb,大于继续压缩
                    baos.reset();// 重置baos即清空baos
                    fitScreenBitmap.compress(Bitmap.CompressFormat.PNG, per, baos);// 将图片压缩为原来的(100-per)%，把压缩后的数据存放到baos中
                    per -= 10;// 每次都减少10
                }
                //回收图片，清理内存
                if (fitScreenBitmap != null && !fitScreenBitmap.isRecycled()) {
                    fitScreenBitmap.recycle();
                    fitScreenBitmap = null;
                    System.gc();
                }
                ByteArrayInputStream btinput = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
                FileOutputStream os = new FileOutputStream(resultFile);
                //自定义工具类，将输入流复制到输出流中
                byte[] buf = new byte[1024 * 8];
                while (true) {
                    int read = 0;
                    if (btinput != null) {
                        read = btinput.read(buf);
                    }
                    System.out.println(read);
                    if (read == -1) {
                        break;
                    }
                    os.write(buf, 0, read);
                }
                btinput.close();
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            resultFile = new File(Environment.getExternalStorageDirectory() + Config.APP_BASE_FOLDER + Config.IMAGE_FOLDER, originalFilePath.substring(originalFilePath.lastIndexOf("/") + 1));
        }
        //如果报错，则把结果file变成originalFile

        if (resultFile.length() == 0) {
            resultFile = new File(originalFilePath);
        }

        LogUtil.e("resultFileLength:" + resultFile.length() / 1024 + "kb");
        LogUtil.e("resultFilePath:" + resultFile.getAbsolutePath());

        return resultFile;

    }
}
