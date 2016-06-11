package net.gcolin.httpquery.spi;

import java.io.IOException;
import java.io.OutputStream;

import net.gcolin.httpquery.For;
import net.gcolin.httpquery.Serializer;

@For(String.class)
public class StringSerializer implements Serializer {

    @Override
    public void write(OutputStream outStream, Object obj) throws IOException {
        outStream.write(((String) obj).getBytes());
    }

}
