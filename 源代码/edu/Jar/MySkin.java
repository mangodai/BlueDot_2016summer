package edu.Jar;

import org.jvnet.substance.skin.OfficeBlue2007Skin;
import org.jvnet.substance.watermark.SubstanceImageWatermark;

import BlueDot.Main;

public class MySkin extends OfficeBlue2007Skin {
	public MySkin() {
		super();
		try {
			// 新建一个图片水印，路径可以自己该，使用自己喜欢的图片来做应用程序的水印图片、
			SubstanceImageWatermark watermark = new SubstanceImageWatermark(
					Main.class
							.getResourceAsStream("img//Moon.jpg"));
			// new Func.File().log("创建水印背景完毕");
			this.watermark = watermark;
		} catch (Exception e) {
			// new Func.File().log("初始化界面捕获异常:"+e.getMessage());
		}
	}
}
