package com.mitri;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * <p>Title: Springboot-Enhance--com.mitri.QRcodeUtil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:12 </p>
 * <p>Create DateTime: 2017/12/17 下午12:50 </p>
 *
 * @author Potter
 * @version v2
 */
public class QRcodeUtil {

  public static void generateQrCode(OutputStream out,String content, int width, int height, String format) throws WriterException, IOException {
    //format="png"
    Map<EncodeHintType, Object> hints = new HashMap<>();
    // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
    // 内容所使用编码
    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
    // 设置图片的边距
    hints.put(EncodeHintType.MARGIN,1);
    // 生成矩阵
    BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
        BarcodeFormat.QR_CODE, width, height, hints);

    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setRGB(x, y,  (bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF));
      }
    }

    /**
     * 读取二维码图片，并构建绘图对象
     */
    Graphics2D g2 = image.createGraphics();

    /**
     * 读取Logo图片
     */
    BufferedImage logo = ImageIO.read(new File("/Users/Potter/Downloads/108.png"));

    //开始绘制图片
    g2.drawImage(logo,width/5*2,height/5*2, width/5, height/5, null);//绘制
    BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
    g2.setStroke(stroke);// 设置笔画对象
    //指定弧度的圆角矩形
    RoundRectangle2D.Float round = new RoundRectangle2D.Float(width/5*2, height/5*2, width/5, height/5,20,20);
    g2.setColor(Color.white);
    g2.draw(round);// 绘制圆弧矩形

    //设置logo 有一道灰色边框
    BasicStroke stroke2 = new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
    g2.setStroke(stroke2);// 设置笔画对象
    RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(width/5*2+2, height/5*2+2, width/5-5, height/5-5,20,20);
    g2.setColor(new Color(128,128,128));
    g2.draw(round2);// 绘制圆弧矩形

    g2.dispose();
    image.flush() ;

    ImageIO.write(image, format, out);

    // 输出图像
//    MatrixToImageWriter.writeToStream(bitMatrix,format,out);
  }

  public static Result decodeQrCode(InputStream inputStream) throws IOException, NotFoundException {
    BufferedImage image = ImageIO.read(inputStream);
    LuminanceSource source = new BufferedImageLuminanceSource(image);
    Binarizer binarizer = new HybridBinarizer(source);
    BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
    Map<DecodeHintType, Object> hints = new HashMap<>();
    hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
    Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
    return result;
  }


  public static void main(String[] args) throws IOException, WriterException {
    File file = new File("/Users/potter/Desktop/qr.png");
    OutputStream fileOutputStream = new FileOutputStream(file);
    generateQrCode(fileOutputStream,"http://180.97.70.120:8001",250,250,"png");
  }


}
