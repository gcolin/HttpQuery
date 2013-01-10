package net.gcolin.httpquery.test;

import java.io.StringReader;

import junit.framework.Assert;
import net.gcolin.httpquery.Http;

import org.junit.Test;

public class HttpTest {

	@Test
	public void setHandler() {
		try{
			Http.setHandler(null);
		}catch (Exception e) {
			Assert.fail("should not fail");
		}
	}

	@Test
	public void get(){
		try{
			Http.get("hello");
			Assert.fail("should fail");
		}catch (Exception e) {}
	}
	
	@Test
	public void delete(){
		try{
			Http.delete("hello");
			Assert.fail("should fail");
		}catch (Exception e) {}
	}
	
	@Test
	public void put1(){
		try{
			Http.put("hello",new Object());
			Assert.fail("should fail");
		}catch (Exception e) {}
	}
	
	@Test
	public void put2(){
		try{
			Http.put("hello",new byte[]{});
			Assert.fail("should fail");
		}catch (Exception e) {}
	}
		
	@Test
	public void put3(){
		try{
			Http.put("hello","hello");
			Assert.fail("should fail");
		}catch (Exception e) {}
	}
	
	@Test
	public void put4(){
		try{
			Http.put("hello", new StringReader("hello"));
			Assert.fail("should fail");
		}catch (Exception e) {}
	}
	
	@Test
	public void post1(){
		try{
			Http.post("hello", new Object());
			Assert.fail("should fail");
		}catch (Exception e) {}
	}
	
	@Test
	public void post2(){
		try{
			Http.post("hello", new byte[]{});
			Assert.fail("should fail");
		}catch (Exception e) {}
	}
	
	@Test
	public void post3(){
		try{
			Http.post("hello", new StringReader("hello"));
			Assert.fail("should fail");
		}catch (Exception e) {}
	}
	
	@Test
	public void post4(){
		try{
			Http.post("hello", "hello");
			Assert.fail("should fail");
		}catch (Exception e) {}
	}
	
	@Test
	public void trace(){
		try{
			Http.trace("hello");
			Assert.fail("should fail");
		}catch (Exception e) {}
	}
	
	@Test
	public void head(){
		try{
			Http.head("hello");
			Assert.fail("should fail");
		}catch (Exception e) {}
	}
	
	@Test
	public void options(){
		try{
			Http.options("hello");
			Assert.fail("should fail");
		}catch (Exception e) {}
	}
}
