package net.gcolin.httpquery.examples.util;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;


public final class SerializationUtil{
	
	private SerializationUtil(){}
	
	public static byte[] createData(){
		Data data = new Data();

		List<App> list = new ArrayList<App>();
		
		App app1 = new App();
		app1.setName("app1");
		app1.setStatus("running");
		list.add(app1);
		
		App app2 = new App();
		app2.setName("app2");
		app2.setStatus("stopped");
		list.add(app2);
		
		data.setApp(list);
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		try{
		ObjectOutputStream oo = new ObjectOutputStream(bout);
		oo.writeObject(data);
		}catch (Exception e) {
			LoggerFactory.getLogger(SerializationUtil.class).error(e.getMessage(),e);
		}finally{
			IOUtils.closeQuietly(bout);
		}
		
		return bout.toByteArray();
	}
	
}