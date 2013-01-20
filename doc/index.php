<div class="span9">

<h1>Documentation</h1>

<h2>Motivation</h2>

<p>Writing the same lines of code for getting data through Http is fatidious and delicate. 
You need to take care of releasing your connection. After that, you need the desirialize
the response in order to use it efficiently. There are <a target="_blanc" href="http://hc.apache.org/httpcomponents-client-ga/">HttpClient</a> 
and <a href="http://www.jboss.org/resteasy" target="_blanc">RestEasy client</a> 
which makes the task easier but not in one simple line.
</p>

<p>A simple example</p>
<pre>

String pageContent = Http.get("http://mysite.com").asString();

</pre>

<p>A complex example</p>
<pre>

Result result = Http.post("http://mysite.com/search", searchParameterObject)
    .serializeWith(JAXBSerializer.class)
    .deserializeWith(JAXBDeserializer.class)
    .setAuthBasic("admin","admin")
    .as(Result.class);

</pre>

<h2>Extensibility</h2>

<p>HttpQuery is composed of a facade such as slf4j. So it's possible to use a HttpClient alternative.
The serializers and deserializers are also easy to plug with the java6 serviceLoader.</p>

<h2>Tests</h2>

<p>It's possible to create mock HttpQuery request.</p>

<pre>

//active mocking
MockHttpHandler.bind(true);
		
When.get("http://localhost:8880/index.html").asStringReturn("hello world");
		
log.info(Http.get("http://localhost:8880/index.html").asString());
		
//remove mocking
MockHttpHandler.bind(false);

</pre>

</div>