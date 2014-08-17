package net.gcolin.httpquery.spi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.gcolin.httpquery.For;
import net.gcolin.httpquery.Serializer;

@For(InputStream.class)
public class InputStreamSerializer implements Serializer{

    @Override
    public void write(OutputStream outStream, Object obj) throws IOException {
        InputStream in = (InputStream) obj;
        byte[] b = new byte[512];
        int c = 0;
        while((c=in.read(b))>0)
        {
            outStream.write(b, 0, c);
        }
    }

}
