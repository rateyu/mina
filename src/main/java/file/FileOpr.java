package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by ym on 2014/8/20.
 */
public class FileOpr {

        public static void main(String[] args) throws IOException
        {
            System.out.println(File.separator);
            System.out.println(System.getProperty("user.dir"));
//            FileInputStream fileInputStream = new FileInputStream("f:"+ File.separator +"IBM e-Mentor Program Kickoff Night 1105.pdf");
//            FileOutputStream fileOutputStream = new FileOutputStream("f:" + File.separator + "test.pdf");
//            FileOutputStream fileOutputStream = new FileOutputStream("." + File.separator + "pom.xml");
            FileInputStream fileInputStream = new FileInputStream("."+ File.separator +"test.txt");
            FileChannel inChannel = fileInputStream.getChannel();
//            FileChannel outChannel= fileOutputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(2);
            //Direct Buffer的效率会更高。
            //ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
            long start = System.currentTimeMillis();

            CharsetDecoder decoder = null;
            Charset charset = null;
            charset = Charset.forName("UTF-8");
            decoder = charset.newDecoder();

            while(true)
            {
//                byteBuffer.clear();
                int eof = inChannel.read(byteBuffer);
                if(eof == -1 ) break;
//                CharBuffer charBuffer = decoder.decode(byteBuffer.asReadOnlyBuffer());
                //准备读出缓冲区
                byteBuffer.flip();
                System.out.println("----"+byteBuffer.asCharBuffer().toString());
//                outChannel.write(byteBuffer);
                //准备写入缓冲区
                byteBuffer.clear();
            }
            System.out.println("spending : " + (System.currentTimeMillis()-start));
            inChannel.close();


            CharBuffer charBuffer = decoder.decode(byteBuffer.asReadOnlyBuffer());
            String [] temp = charBuffer.toString().split("\r\n");

            for (String s : temp) {
                System.out.println("111111");
                System.out.println(s.trim());
                System.out.println("222222");
            }
//            outChannel.close();
        }
}
