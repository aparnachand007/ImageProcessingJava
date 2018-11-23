package library;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Image{

  File file;
  BufferedImage image;
  int height, width;
  String secret;

  public Image(){
    this.image = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
  }

  public Image(String fileName){
    this.readImage(fileName);
    width = image.getWidth();
    height = image.getHeight();
  }

public void readImage(String fileName){
     try{
      file = new File(fileName);
      image = ImageIO.read(file);
      width = image.getWidth();
      height = image.getHeight();
      System.out.println("read success");
    } catch(IOException e){
      System.out.println("read error");
    }
  }

  public void writeImage(String fileName, String format){
    try{
      file = new File(fileName+"."+format);
      ImageIO.write(this.image,format,file);
      System.out.println("write success");
    } catch(IOException e){
      System.out.println("write error");
    }
  }

  public void setPixel(Pixel pixel){
    int x=pixel.x;
    int y=pixel.y;
    this.image.setRGB(x,y,pixel.p);
  }

  public void newBlank(){
    this.image = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
    Pixel pix = new Pixel();
    pix.a=0xff;pix.g=0;pix.b=0;
    pix.r=0xff;
    pix.combineChannels();
    for(int i=0;i<100;i++){
      for(int j=0;j<100;j++){
        pix.x = i;
        pix.y = j;
        this.setPixel(pix);
      }
    }
  }

  public void filterInvert(){
    Pixel tpix = new Pixel();
    for(int i=0;i<width;i++){
      for(int j=0;j<height;j++){
        try{
        tpix.getPixelData(this.image,i,j);
        tpix.seperateChannels();
        tpix.r = 0xff - tpix.r;
        tpix.g = 0xff - tpix.g;
        tpix.b = 0xff - tpix.b;
        tpix.combineChannels();
        this.setPixel(tpix);
        }catch(ArrayIndexOutOfBoundsException e){
          //System.out.println("("+i+","+j+")"+" is out of bounds");
          continue;
        }
      }
    }
  }

  public void filterInvertAlpha(){
    Pixel pix = new Pixel();
    for(int i=0;i<width;i++){
      for(int j=0;j<height;j++){
        try{
          pix.getPixelData(image,i,j);
          pix.seperateChannels();
          pix.a= 0xff - pix.a;
          pix.combineChannels();
          this.setPixel(pix);
        }catch(ArrayIndexOutOfBoundsException e){
          //System.out.println("("+i+","+j+")"+"is out of bounds");
        }
      }
    }
  }

  public void greyscale(){
    Pixel pix = new Pixel();
    for(int i=0;i<width;i++){
      for(int j=0;j<height;j++){
        try{
          pix.getPixelData(image,i,j);
          pix.seperateChannels();
          pix.r = (pix.r+pix.g+pix.b)/3;
          pix.g = (pix.r+pix.g+pix.b)/3;
          pix.b = (pix.r+pix.g+pix.b)/3;
          pix.combineChannels();
          this.setPixel(pix);
        }catch(ArrayIndexOutOfBoundsException e){
          continue;
        }
      }
    }
  }

  public void filterRed(){
    Pixel pix = new Pixel();
    for(int i=0;i<width;i++){
      for(int j=0;j<height;j++){
        try{
          pix.getPixelData(image,i,j);
          pix.seperateChannels();
          pix.g=0;
          pix.b=0;
          pix.combineChannels();
          this.setPixel(pix);
        }catch(ArrayIndexOutOfBoundsException e){
          continue;
        }
      }
    }
  }

  public void filterGreen(){
    Pixel pix = new Pixel();
    for(int i=0;i<width;i++){
      for(int j=0;j<height;j++){
        try{
          pix.getPixelData(image,i,j);
          pix.seperateChannels();
          pix.r=0;
          pix.b=0;
          pix.combineChannels();
          this.setPixel(pix);
        }catch(ArrayIndexOutOfBoundsException e){
          continue;
        }
      }
    }
  }

  public void filterBlue(){
    Pixel pix = new Pixel();
    for(int i=0;i<width;i++){
      for(int j=0;j<height;j++){
        try{
          pix.getPixelData(image,i,j);
          pix.seperateChannels();
          pix.g=0;
          pix.r=0;
          pix.combineChannels();
          this.setPixel(pix);
        }catch(ArrayIndexOutOfBoundsException e){
          continue;
        }
      }
    }
  }

  public void flip(){
    Pixel pix = new Pixel();
    for(int i=0;i<width;i++){
      for(int j=0;j<height;j++){
        try{
          pix.getPixelData(image,i,j);
          pix.x = width-i-1;
          this.setPixel(pix);
        }catch(ArrayIndexOutOfBoundsException e){
          continue;
        }
      }
    }
  }

  public void selectRegion(int x1,int y1,int x2,int y2){
    int newX=0, newY=0;
    int w=(x2-x1),h=(y2-y1);
    Pixel tpix = new Pixel();
    BufferedImage reg = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
    for(int i=x1;i<x2;i++){
      for(int j=y1;j<y2;j++){
        try{
          tpix.getPixelData(this.image,i,j);
          reg.setRGB(newX,newY,tpix.p);
          //System.out.println("("+newX+","+newY+")"+" is in bounds");
        }catch(ArrayIndexOutOfBoundsException e){
          //System.out.println("("+newX+","+newY+")"+" is out of bounds");
          continue;
        }
        newY++;
      }
      newX++;
      newY=0;
    }
    this.image = reg;
  }

}
