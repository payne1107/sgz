package android.sgz.com.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SDCardUtil {
    protected static final String TAG = SDCardUtil.class.getSimpleName();

    /**
     * 判断SD卡已经挂载
     */
    public static boolean cheekSDCardIsMounted() {
        return Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获得SD卡的根目录的绝对路径
     */
    public static String getSdCardRootAbsolutePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static boolean deleteFile(File file) {
        if (!file.exists()) {
            return false;
        }
        return file.delete();
    }

    /**
     * 删除文件
     *
     * @param fileName 必须是绝对路径, sdcard0/cache/abc.mp4
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        return deleteFile(file);
    }

    /**
     * 获得一个文件夹的路径下面的子路径
     *
     * @param path 要操作的一个文件夹的绝对路径
     * @return List<.File> 目标文件夹的子文件、子文件夹
     */
    public static List<File> getListForEachObjectPath(String path) {
        List<File> list;
        if (!cheekSDCardIsMounted()) {
            return null;
        }
        File fileDir = new File(path);
        File files[] = fileDir.listFiles();
        list = new ArrayList<File>();
        if (files == null) {
            return list;
        }
        for (File f : files) {
            list.add(f);
        }
        return list;
    }

    /**
     * 获得SD卡总空间
     */
    @SuppressWarnings("deprecation")
    public static int getSize() {
        if (cheekSDCardIsMounted()) {
            StatFs statFs = new StatFs(getSdCardRootAbsolutePath());
            int blockCount = statFs.getBlockCount();
            int blockSize = statFs.getBlockSize();
            return blockCount * blockSize / 1024 / 1024;
        }
        return 0;
    }

    /**
     * 获得SD卡可用空间
     */
    @SuppressWarnings("deprecation")
    public static int getAvailableSize() {
        StatFs statFs = new StatFs(getSdCardRootAbsolutePath());
        if (cheekSDCardIsMounted()) {
            int availableBlocks = statFs.getAvailableBlocks();
            int blockSize = statFs.getBlockSize();
            return availableBlocks * blockSize / 1024 / 1024;
        }
        return 0;
    }

    /**
     * 保存文件
     *
     * @param filePath 全路径: sdcard/cache/mainCache.bak
     * @param append   true-追加|false-清空
     */
    public static boolean saveFile(byte byteAry[], String filePath, boolean append) {
        FileOutputStream output = null;
        try {
            File file = new File(filePath);
            String parentStr = file.getParent();
            boolean createSDCardDir = createSDCardDir(parentStr);
            if (!createSDCardDir) {
                return false;
            }
            output = new FileOutputStream(new File(filePath), append);
            output.write(byteAry);
            output.flush();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "有异常：" + e);
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                Log.e(TAG, "有异常：" + e);
            }
        }
        return false;
    }

    /**
     * 保存文件
     *
     * @param filePath 全路径: sdcard/cache/mainCache.bak
     * @param append   true-追加|false-清空
     */
    public static boolean saveFile(byte byteAry[], String fileDir, String fileName, boolean append) {
        createSDCardDir(fileDir);
        OutputStream output = null;
        try {
            output = new FileOutputStream(new File(File.separator + fileDir + File.separator + fileName), append);
            output.write(byteAry);
            output.flush();
        } catch (Exception e) {
            Log.e(TAG, "有异常：" + e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    Log.e(TAG, "有异常：" + e);
                }
            }
        }
        return append;
    }

    /**
     * 从SD卡中取出文件
     * <br/>如： sdcard0/dir/file.mp4
     */
    public static byte[] getFileFromSDCard(String filePath) {
        File file = new File(filePath);
        if ((!cheekSDCardIsMounted()) || (!file.exists())) {
            return null;
        }
        BufferedInputStream bufIS = null;
        ByteArrayOutputStream baos = null;
        try {
            bufIS = new BufferedInputStream(new FileInputStream(file));
            baos = new ByteArrayOutputStream();
            byte buffer[] = new byte[1024 * 8];
            int len = 0;
            while ((len = bufIS.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
                baos.flush();
            }
            return baos.toByteArray();
        } catch (Exception e) {
            Log.e(TAG, "有异常: " + e);
        } finally {
            closeSrc(bufIS, baos);
        }
        return null;
    }

    /**
     * 关闭getFileFromSDCard用到的 流
     */
    private static void closeSrc(BufferedInputStream bufIS, ByteArrayOutputStream baos) {
        try {
            if (baos != null) {
                baos.close();
            }
        } catch (IOException e) {
            Log.e(TAG, "有异常：" + e);
        }
        try {
            if (bufIS != null) {
                bufIS.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "有异常: " + e);
        }
    }

    public static boolean createSDCardDir(String newPath) {
        if (getSdCardRootAbsolutePath() == null) {
            Log.e(TAG, "SD卡不可用：");
            return false;
        }
        //得到一个路径，内容是sdcard的文件夹路径和名字
        File path1 = new File(newPath);
        if (!path1.exists()) {
            //若不存在，创建目录，可以在应用启动的时候创建
            boolean mkdirs = path1.mkdir();
            //LogUtils.e("创建"+path1.getAbsolutePath()+" 成功 = "+mkdirs);
            return mkdirs;
        }
        return true;
    }

    public static boolean fileExist(String filePath) {
        File file = new File(filePath);
        if ((!cheekSDCardIsMounted()) || (!file.exists())) {
            return false;
        }
        return true;
    }


    /**从SD卡中取出文件   必须是SD卡全路径
     * <br/>如： sdcard0/dir/file.mp4*/
    public static boolean saveBitmap(Bitmap bitmap, String filePath) {
        FileOutputStream output = null;
        try {
            File file = new File(filePath);
            String parentStr = file.getParent();
            boolean createSDCardDir = createSDCardDir(parentStr);
            if(!createSDCardDir){
                return false;
            }
            output = new FileOutputStream(new File(filePath),false);
            output.write(ImageUtil.bitMapToByte(bitmap));
            output.flush();
            return true;
        } catch (Exception e){
            Log.e("","有异常："+e);
        } finally {
            try {
                if(output!=null){
                    output.close();
                }
            } catch (IOException e){
                Log.e("","有异常："+e);
            }
        }
        return false;
    }
}
