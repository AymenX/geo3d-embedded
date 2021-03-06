package org.avm.business.parser.hmi;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.apache.log4j.Logger;
import org.avm.business.protocol.hmi.MessageFactory;
import org.avm.business.protocol.hmi.State;
import org.avm.elementary.parser.AbstractParser;

public class ParserImpl extends AbstractParser {


	protected ClassLoader _loader;

	public ParserImpl(URL url) {
		_log = Logger.getInstance(this.getClass().getName());
		URLConnection conn;
		try {
			conn = url.openConnection();
			initialize(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize(URLConnection conn) throws Exception {
		JarInputStream zip = new JarInputStream(conn.getInputStream());
		JarEntry entry;
		while ((entry = (JarEntry) zip.getNextEntry()) != null) {
			String name = entry.getName();
			if (name.endsWith(".class")) {
				String classname = name.replace('/', '.');
				classname = classname.substring(0, classname.length() - 6);
				Class clazz;
				try {
					clazz = Class.forName(classname);
					if (_loader == null) {
						_loader = clazz.getClassLoader();
					}
					System.out.println(clazz.getName() + " loaded loader : "
							+ clazz.getClassLoader());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public String getProtocolName() {
		return MessageFactory.PROTOCOL_NAME;
	}

	public int getProtocolId() {
		return MessageFactory.PROTOCOL_ID;
	}

	public void marshal(Object n, OutputStream out) throws Exception {
		if (n instanceof State) {
			State message = (State) n;
			message.marshal(out);

		} else {
			throw new RuntimeException("Protocole non supporte");
		}
	}

	public Object unmarshal(InputStream in) throws Exception {
		State message = null;
		try {
			message = MessageFactory.unmarshal(in);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return message;
	}

	public Object get(InputStream in) throws Exception {
		in.reset();
		State message = MessageFactory.get(in);
		return message;
	}

	public void put(Object n, OutputStream out) throws Exception {
		if (n instanceof State) {
			State message = (State) n;
			message.put(out);
		} else {
			throw new RuntimeException("Protocole non supporte");
		}
	}


}
