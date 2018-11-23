import library.Image;
import library.Pixel;
import java.util.*;

class main{
  public static void main(String args[]){
    int inp=0;
    int inp2=0;
    String filePathIn,filePathOut,tmp,format;
    Scanner inputScanner = new Scanner(System.in);

    Image img = new Image();
    System.out.println("\n\nW e l c o m e   t o   t h e   a p p l i c a t i o n\n\n");

    do{
      inp = 0;
      System.out.println("Select action:");
      System.out.println("1) Read an image");
      System.out.println("2) Choose filter to apply");
      System.out.println("3) Write the image to an output file");
      System.out.println("4) Exit the program");

      try{
        inp = inputScanner.nextInt();
      }catch(InputMismatchException e){
        System.out.println("Enter appropriate value");
        inp = 0;
      }


      int x1=0,y1=0,x2=0,y2=0;

      if(inp==0){
        System.exit(0);
      }
      if(inp==1){
        System.out.println("Enter filepath to image:");
        filePathIn = System.console().readLine();
        img.readImage(filePathIn);
      }

      if(inp==2){
        System.out.println("Filters available:");
        System.out.println("1) Invert colors");
        System.out.println("2) Invert alpha");
        System.out.println("3) Select region");
        System.out.println("4) Convert to greyscale");
        System.out.println("5) Convert to Red");
        System.out.println("6) Convert to Green");
        System.out.println("7) Convert to Blue");
        System.out.println("8) Mirror Image");
        try{
          inp2 = inputScanner.nextInt();
        }catch(InputMismatchException e){
          System.out.println("Enter appropriate value");
        }

        if(inp2==1){
          img.filterInvert();
        }
        if(inp2==2){
          img.filterInvertAlpha();
        }
        if(inp2==3){
          System.out.println("Enter range of coordinates to select");
          System.out.println("x1: ");
          try{
            x1 = inputScanner.nextInt();
          }catch(InputMismatchException e){
            System.out.println("Enter appropriate value");
          }
          System.out.println("y1: ");
          try{
            y1 = inputScanner.nextInt();
          }catch(InputMismatchException e){
            System.out.println("Enter appropriate value");
          }
          System.out.println("x2: ");
          try{
            x2 = inputScanner.nextInt();
          }catch(InputMismatchException e){
            System.out.println("Enter appropriate value");
          }
          System.out.println("y2: ");
          try{
            y2 = inputScanner.nextInt();
          }catch(InputMismatchException e){
            System.out.println("Enter appropriate value");
          }
          img.selectRegion(x1,y1,x2,y2);
        }
        if(inp2==4){
          img.greyscale();
        }
        if(inp2==5){
          img.filterRed();
        }
        if(inp2==6){
          img.filterGreen();
        }
        if(inp2==7){
          img.filterBlue();
        }
        if(inp2==8){
          img.flip();
        }
        else if(inp2>8){
          System.out.println("Invalid selecton!"+inp);
        }

      }

      if(inp==3){
        System.out.println("Enter name of output file: ");
        filePathOut = System.console().readLine();
        System.out.println("Enter format of output file: ");
        format = System.console().readLine();
        img.writeImage(filePathOut,format);
      }
      if(inp==4){
        System.out.println("Quitting...");
        System.exit(0);
      }
      else if(inp>4){
        System.out.println("Invalid selecton!"+inp);
      }

    }while(true);
  }
}
