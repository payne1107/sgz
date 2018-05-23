package android.sgz.com.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.io.ByteArrayOutputStream;

public class ImageUtil
{
	/**将Bitmap转成 byte[]
	 * <br/>失败, 返回new byte[0]*/
	public static byte[] bitMapToByte(Bitmap map) 
	{
		if (map == null) {
			return new byte[0];
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		map.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}
	/**将byte[] 转成 Bitmap*/
	public static Bitmap byteToBitmap(byte[] bytes) {
		return (bytes == null || bytes.length == 0) ? null : BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}
	/**将Bitmap转成Drawable类型
	 * <br/>失败,返回null*/
	@SuppressWarnings("deprecation")
	public static Drawable bitmapToDrawable(Bitmap map) {
		return map == null ? null : new BitmapDrawable(map);
	}
	/** 将Drawable转成byte[]
	 * @return 失败, 返回new byte[0]
	 */
	public static byte[] drawableToByte(Drawable draw) {
		if(draw==null){
			return new byte[0];
		}
		return bitMapToByte(drawableToBitmap(draw));
	}
	/**
	 * 将byte[]转成Drawable
	 * @param bytes 字节数组
	 * @return 失败, 返回null
	 */
	public static Drawable byteToDrawable(byte[] bytes) 
	{
		if(bytes==null || bytes.length==0){
			return null;
		}
		return bitmapToDrawable(byteToBitmap(bytes));
	}
	/**缩放图片, scale > 1 为放大；非法参数为原图
	 * @param bitmap 位图
	 * @param scaleW 宽度缩放比例
	 * @param scaleH 高度缩放比例*/
	public static Bitmap zoomBitmap(Bitmap bitmap, float scaleW, float scaleH)
	{
		Matrix matrix = new Matrix();
		if(scaleW>1 || scaleW <0){
			scaleW = 1;
		}
		if(scaleH>1 || scaleH <0){
			scaleH = 1;
		}
		matrix.postScale(scaleW, scaleH);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return newbmp;
	}

	/**将Drawable对象转换成 Bitmap对象
	 * @param drawable 可绘制图像*/
	public static Bitmap drawableToBitmap(Drawable drawable)
	{
		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, width, height);
		drawable.draw(canvas);
		return bitmap;
	}
	/** 将drawable转成Bitmap
	 * @return 失败,返回null
	 */
	public static Bitmap drawable2Bitmap(Drawable d) {
		return d == null ? null : ((BitmapDrawable)d).getBitmap();
	}

	/**图片圆角化, 半径自己控制
	 * @param bitmap 位图
	 * @param roundPx 圆角半径, 20以上有效果 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx)
	{
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/**给图片家倒影
	 * @param bitmap Bitmap*/   
	public static Bitmap addReflection(Bitmap bitmap)
	{
		final int reflectionGap = 4;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);
		Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2, width, height / 2, matrix, false);
		Bitmap bitmapWithReflection = Bitmap.createBitmap(width, (height + height / 2), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(bitmap, 0, 0, null);
		Paint deafalutPaint = new Paint();
		canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);
		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);
		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0, bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
		paint.setShader(shader);
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight() + reflectionGap, paint);
		return bitmapWithReflection;
	}
	/**将Bitmap圆形化*/
	public static Bitmap getRoundedBitmap(Bitmap bitmap) 
	{
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) 
		{
			roundPx = width / 2;
			top = 0;
			bottom = width;
			left = 0;
			right = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}
		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);
		return output;
	}
	/**旋转Bitmap
	 * @param bitmap 为null或空图, 返回null
	 * @param degrees >0 为顺时针, degrees <0为逆时针
	 * @return 失败, 返回null*/
	public static Bitmap getRotateBitmap(Bitmap bitmap, float degrees)
	{
		if(bitmap==null || bitmap.getHeight()<=0){
			return null;
		}
		Matrix matrix = new Matrix();
		matrix.postRotate(degrees);//旋转的角度
		return Bitmap.createBitmap(bitmap, 0, 0,bitmap.getWidth(), bitmap.getHeight(), matrix, true);
	}
	/**黑白照效果*/ 
	public static Bitmap getGrayBitmap(Bitmap bitmap) 
	{   
		int width, height;   
		height = bitmap.getHeight();   
		width = bitmap.getWidth();   
		Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Config.RGB_565);
		Canvas c = new Canvas(bmpGrayscale);   
		Paint paint = new Paint();   
		ColorMatrix cm = new ColorMatrix();   
		cm.setSaturation(0);   
		ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);   
		paint.setColorFilter(f);   
		c.drawBitmap(bitmap, 0, 0, paint);   
		return bmpGrayscale;   
	}   
	/**怀旧效果
	 * @param bitmap 要处理的图片
	 * @return 新图片
	 */  
	public static Bitmap getOldRemeber(Bitmap bitmap)  
	{  
		int width = bitmap.getWidth();  
		int height = bitmap.getHeight();  
		Bitmap newImg = Bitmap.createBitmap(width, height, Config.RGB_565);
		int pixColor = 0;  
		int pixR = 0;  
		int pixG = 0;  
		int pixB = 0;  
		int newR = 0;  
		int newG = 0;  
		int newB = 0;  
		int[] pixels = new int[width * height];  
		bitmap.getPixels(pixels, 0, width, 0, 0, width, height);  
		for (int i = 0; i < height; i++)  
		{  
			for (int k = 0; k < width; k++)  
			{  
				pixColor = pixels[width * i + k];  
				pixR = Color.red(pixColor);  
				pixG = Color.green(pixColor);  
				pixB = Color.blue(pixColor);  
				newR = (int) (0.393 * pixR + 0.769 * pixG + 0.189 * pixB);  
				newG = (int) (0.349 * pixR + 0.686 * pixG + 0.168 * pixB);  
				newB = (int) (0.272 * pixR + 0.534 * pixG + 0.131 * pixB);  
				int newColor = Color.argb(255, newR > 255 ? 255 : newR, newG > 255 ? 255 : newG, newB > 255 ? 255 : newB);  
				pixels[width * i + k] = newColor;  
			}  
		}  
		newImg.setPixels(pixels, 0, width, 0, 0, width, height);  
		return newImg;  
	}  
	/**底片效果 
	 * @param bitmap 要处理的图片
	 * @return 新图片
	 */  
	public static Bitmap getNegative(Bitmap bitmap)  
	{  
		final int MAX_VALUE = 255;  
		int width = bitmap.getWidth();  
		int height = bitmap.getHeight();  
		Bitmap newImg = Bitmap.createBitmap(width, height, Config.RGB_565);
		int pixR = 0;  
		int pixG = 0;  
		int pixB = 0;  
		int pixColor = 0;  
		int newR = 0;  
		int newG = 0;  
		int newB = 0;  
		int[] pixels = new int[width * height];  
		bitmap.getPixels(pixels, 0, width, 0, 0, width, height);  
		int pos = 0;  
		for (int i = 1, length = height - 1; i < length; i++)  
		{  
			for (int k = 1, len = width - 1; k < len; k++)  
			{  
				pos = i * width + k;  
				pixColor = pixels[pos];  

				pixR = Color.red(pixColor);  
				pixG = Color.green(pixColor);  
				pixB = Color.blue(pixColor);  

				newR = MAX_VALUE - pixR;  
				newG = MAX_VALUE - pixG;  
				newB = MAX_VALUE - pixB;  

				newR = Math.min(MAX_VALUE, Math.max(0, newR));  
				newG = Math.min(MAX_VALUE, Math.max(0, newG));  
				newB = Math.min(MAX_VALUE, Math.max(0, newB));  

				pixels[pos] = Color.argb(MAX_VALUE, newR, newG, newB);  
			}  
		}  
		newImg.setPixels(pixels, 0, width, 0, 0, width, height);  
		return newImg;  
	}  
	/**  
	 * 图片拼接
	 * @param direction img[1]相对于img[0] 1-左 2-右 3-上 4-下
	 * @param bitmaps 数组
	 * @return  
	 */ 
	public static Bitmap compositeBitmap(int direction, Bitmap... bitmaps) 
	{   
		if (bitmaps.length <= 0) {   
			return null;   
		}   
		if (bitmaps.length == 1) {   
			return bitmaps[0];   
		}   
		Bitmap newBitmap = bitmaps[0];   
		for (int i = 1; i < bitmaps.length; i++) {   
			newBitmap = createBitmapForFotoMix(newBitmap, bitmaps[i], direction);   
		}   
		return newBitmap;   
	}   
	/**关联 {@link #potoMix(int, Bitmap...)}*/
	private static Bitmap createBitmapForFotoMix(Bitmap first, Bitmap second, int direction) {   
		if (first == null) {   
			return null;   
		}   
		if (second == null) {   
			return first;   
		}    
		int fw = first.getWidth();   
		int fh = first.getHeight();   
		int sw = second.getWidth();   
		int sh = second.getHeight();   
		Bitmap newBitmap = null;   
		if (direction == 1) {   /**LEFT*/
			newBitmap = Bitmap.createBitmap(fw + sw, fh > sh ? fh : sh, Config.ARGB_8888);   
			Canvas canvas = new Canvas(newBitmap);   
			canvas.drawBitmap(first, sw, 0, null);   
			canvas.drawBitmap(second, 0, 0, null);   
		} else if (direction == 2) {   /**RIGHT*/
			newBitmap = Bitmap.createBitmap(fw + sw, fh > sh ? fh : sh, Config.ARGB_8888);   
			Canvas canvas = new Canvas(newBitmap);   
			canvas.drawBitmap(first, 0, 0, null);   
			canvas.drawBitmap(second, fw, 0, null);   
		} else if (direction == 3) {/**TOP*/    
			newBitmap = Bitmap.createBitmap(sw > fw ? sw : fw, fh + sh, Config.ARGB_8888);   
			Canvas canvas = new Canvas(newBitmap);   
			canvas.drawBitmap(first, 0, sh, null);   
			canvas.drawBitmap(second, 0, 0, null);   
		} else if (direction == 4) { /**BOTTOM*/  
			newBitmap = Bitmap.createBitmap(sw > fw ? sw : fw, fh + sh, Config.ARGB_8888);   
			Canvas canvas = new Canvas(newBitmap);   
			canvas.drawBitmap(first, 0, 0, null);   
			canvas.drawBitmap(second, 0, fh, null);   
		}   
		return newBitmap;   
	}  
	/**图片覆盖叠加 
	 * @param mainImage 主图
	 * @param auxiliaryImage 辅助的图片
	 */  
	public static Bitmap getCoveredBitmap(Bitmap mainImage, Bitmap auxiliaryImage)  
	{  
		int width = mainImage.getWidth();  
		int height = mainImage.getHeight();  
		Bitmap bitmap = Bitmap.createBitmap(width, height, Config.RGB_565);
		// 对边框图片进行缩放  
		int w = auxiliaryImage.getWidth();  
		int h = auxiliaryImage.getHeight();  
		float scaleX = width * 1F / w;  
		float scaleY = height * 1F / h;  
		Matrix matrix = new Matrix();  
		matrix.postScale(scaleX, scaleY);  
		Bitmap overlayCopy = Bitmap.createBitmap(auxiliaryImage, 0, 0, w, h, matrix, true);  
		int pixColor = 0;  
		int layColor = 0;  
		int pixR = 0;  
		int pixG = 0;  
		int pixB = 0;  
		int pixA = 0;  
		int newR = 0;  
		int newG = 0;  
		int newB = 0;  
		int newA = 0;  
		int layR = 0;  
		int layG = 0;  
		int layB = 0;  
		int layA = 0;  
		final float alpha = 0.5F;  
		int[] srcPixels = new int[width * height];  
		int[] layPixels = new int[width * height];  
		mainImage.getPixels(srcPixels, 0, width, 0, 0, width, height);  
		overlayCopy.getPixels(layPixels, 0, width, 0, 0, width, height);  
		int pos = 0;  
		for (int i = 0; i < height; i++)  
		{  
			for (int k = 0; k < width; k++)  
			{  
				pos = i * width + k;  
				pixColor = srcPixels[pos];  
				layColor = layPixels[pos];  

				pixR = Color.red(pixColor);  
				pixG = Color.green(pixColor);  
				pixB = Color.blue(pixColor);  
				pixA = Color.alpha(pixColor);  

				layR = Color.red(layColor);  
				layG = Color.green(layColor);  
				layB = Color.blue(layColor);  
				layA = Color.alpha(layColor);  

				newR = (int) (pixR * alpha + layR * (1 - alpha));  
				newG = (int) (pixG * alpha + layG * (1 - alpha));  
				newB = (int) (pixB * alpha + layB * (1 - alpha));  
				layA = (int) (pixA * alpha + layA * (1 - alpha));  

				newR = Math.min(255, Math.max(0, newR));  
				newG = Math.min(255, Math.max(0, newG));  
				newB = Math.min(255, Math.max(0, newB));  
				newA = Math.min(255, Math.max(0, layA));  

				srcPixels[pos] = Color.argb(newA, newR, newG, newB);  
			}  
		}  
		bitmap.setPixels(srcPixels, 0, width, 0, 0, width, height);  
		return bitmap;  
	} 
	/**比较两个图片是否一样
	 * @param one 参与者, 无顺序区别
	 * @param two 参与者, 无顺序区别
	 * @return 必须一样, 缩放都不行*/
	public static boolean equals(Bitmap one, Bitmap two) 
	{ 
		int t = 0; 
		int f = 0; 
		int[] pixels_one = new int[one.getWidth()*one.getHeight()]; 
		int[] pixels_two = new int[two.getWidth()*two.getHeight()]; 
		one.getPixels(pixels_one,0,one.getWidth(),0,0,one.getWidth(),one.getHeight()); 
		two.getPixels(pixels_two,0,two.getWidth(),0,0,two.getWidth(),two.getHeight()); 
		//如果图片一个像素大于图片2的像素，就用像素少的作为循环条件。避免报错 
		if (pixels_one.length >= pixels_two.length) 
		{ 
			//对每一个像素的RGB值进行比较 
			for(int i = 0; i < pixels_two.length; i++){ 
				int clr_one = pixels_one[i]; 
				int clr_two = pixels_two[i]; 
				//RGB值一样就加一（以便算百分比） 
				if (clr_one == clr_two) { 
					t++; 
				}else { 
					f++; 
				} 
			} 
		}else 
		{ 
			for(int i = 0; i < pixels_one.length; i++){ 
				int clr_one = pixels_one[i]; 
				int clr_two = pixels_two[i]; 
				if (clr_one == clr_two) { 
					t++; 
				}else { 
					f++; 
				} 
			} 

		} 
		return myPercent(t,t+f);   
	} 
	/** 百分比的计算 
	 * <br/>关联 {@link #equals(Bitmap, Bitmap)}
	 */ 
	private static boolean myPercent(int y,int z) 
	{ 
		double baiy=y*1.0; 
		double baiz=z*1.0; 
		double fen=baiy/baiz; 
		if (fen == 1.00){ 
			return true; 
		} 
		else{ 
			return false; 
		}     
	}
	/** 获取View的截图
	 * @param v 需要进行截图的控件
	 * @return 该控件截图的Bitmap对象。
	 */
	public static Bitmap captureView(View v) {
		v.setDrawingCacheEnabled(true);
		v.buildDrawingCache();
		return v.getDrawingCache();
	}
}