import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class pdfsp {
    public static void main(String []args){
        try {
            byte b[] = new byte[1000000];
            int x = 1, j = 0;
            String s = "";
            InputStreamReader ins =new InputStreamReader(System.in);
            BufferedReader br=new BufferedReader(ins);
            System.out.println("Enter path of source or original file :");
            String path= br.readLine();
            FileInputStream fis=new FileInputStream(path);
            int read_bytes;
            while(fis.available()!=0){
                j=0;
                s="";
                if (x<=9){
                    s=path +".00"+x;

                }
                else {
                    s=path+".0 " +x;

                }
                FileOutputStream fileOutputStream=new FileOutputStream(s);
                while (j<=50000000 &&  fis.available()!=0){
                    read_bytes=fis.read(b,0,1000000);
                    j=j+read_bytes;
                    fileOutputStream.write(b,0,read_bytes);
                }
                System.out.println("part" + x + "crested");
                x++;
            }
            System.out.println("file splitted sucessfully ........ :");
            fis.close();



        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}