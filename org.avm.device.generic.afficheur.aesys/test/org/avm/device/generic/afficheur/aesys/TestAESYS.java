package org.avm.device.generic.afficheur.aesys;

import junit.framework.TestCase;

import org.avm.device.afficheur.AfficheurProtocol;
import org.avm.device.generic.afficheur.aesys.protocol.AfficheurProtocolFactory;

public class TestAESYS extends TestCase {

	public void debug(String string) {
		System.out.println(string);
	}

	private String generate(String message) {
		AfficheurProtocol protocol2 = AfficheurProtocolFactory.create("AESYS");
		return AfficheurProtocol
				.toHexaAscii(protocol2.generateMessage(message));
	}

	private String generateStatus() {
		AfficheurProtocol protocol2 = AfficheurProtocolFactory.create("AESYS");
		return AfficheurProtocol.toHexaAscii(protocol2.generateStatus());
	}

	public void testCode3() {
		String code = "3";
		String trame = generate(code);

		// TODO
		String expected = "???";
		debug("code=" + code + "   =>" + trame);
		assertEquals(trame.length(), expected.length());
		assertEquals(expected, trame);
	}

}
