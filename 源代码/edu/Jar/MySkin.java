package edu.Jar;

import org.jvnet.substance.skin.OfficeBlue2007Skin;
import org.jvnet.substance.watermark.SubstanceImageWatermark;

import BlueDot.Main;

public class MySkin extends OfficeBlue2007Skin {
	public MySkin() {
		super();
		try {
			// �½�һ��ͼƬˮӡ��·�������Լ��ã�ʹ���Լ�ϲ����ͼƬ����Ӧ�ó����ˮӡͼƬ��
			SubstanceImageWatermark watermark = new SubstanceImageWatermark(
					Main.class
							.getResourceAsStream("img//Moon.jpg"));
			// new Func.File().log("����ˮӡ�������");
			this.watermark = watermark;
		} catch (Exception e) {
			// new Func.File().log("��ʼ�����沶���쳣:"+e.getMessage());
		}
	}
}
