package library;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Pixel{

  int p,a,r,g,b;
  int x,y;
  double percentA,percentR,percentG,percentB;

  public void getPixelData(BufferedImage img,int x,int y){
    this.p = img.getRGB(x,y);
    this.x = x;
    this.y = y;
  }

  public void seperateChannels(){
    this.a = (this.p>>24) & 0xff;
    this.r = (this.p>>16) & 0xff;
    this.g = (this.p>>8) & 0xff;
    this.b = (this.p) & 0xff;

    percentA = (double)this.a/(this.r+this.g+this.b+this.a);
    percentR = (double)this.r/(this.r+this.g+this.b+this.a);
    percentG = (double)this.g/(this.r+this.g+this.b+this.a);
    percentB = (double)this.b/(this.r+this.g+this.b+this.a);
  }

  void printPixel(){
    System.out.println(this.a+"\n"+this.r+"\n"+this.g+"\n"+this.b+"\n\n"+this.percentA+"\n"+this.percentR+"\n"+this.percentG+"\n"+this.percentB+"\n");
  }

  Pixel(BufferedImage img, int x, int y){
    getPixelData(img,x,y);
    this.x=x;
    this.y=y;
    seperateChannels();
    percentR = (double)this.r/(this.r+this.g+this.b);
    percentG = (double)this.g/(this.r+this.g+this.b);
    percentB = (double)this.b/(this.r+this.g+this.b);
  }

  public void combineChannels(){
    p = (a<<24) | (r<<16) | (g<<8) | b;
  }

  Pixel(){
    p=0xffffffff;
    seperateChannels();
  }

}
