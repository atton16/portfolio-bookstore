password: password

Setup:
tomcat’s server.xml
Find:
    <!--
    <Connector port="8443" protocol="HTTP/1.1" SSLEnabled="true"
               maxThreads="150" scheme="https" secure="true"
               clientAuth="false" sslProtocol="TLS" />
    -->

Replace:
    <Connector SSLEnabled="true" acceptCount="100" clientAuth="false"
    	disableUploadTimeout="true" enableLookups="false" maxThreads="25"
    	port="8443" keystoreFile="PATH_TO_KEYSTORE_FILE" keystorePass="password"
    	protocol="org.apache.coyote.http11.Http11NioProtocol" scheme="https"
    	secure="true" sslProtocol="TLS" />

Force SSL:
app’s web.xml
add:
	<security-constraint>
		<web-resource-collection>
			<web-resource-name>securedapp</web-resource-name>
			<url-pattern>/*</url-pattern>
		</web-resource-collection>
		<user-data-constraint>
			<transport-guarantee>CONFIDENTIAL</transport-guarantee>
		</user-data-constraint>
	</security-constraint>