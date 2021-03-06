/* @flavorc
 *
 * Message.java
 * 
 * This file was automatically generated by flavorc
 * from the source file:
 *     'avm.fl'
 *
 * For information on flavorc, visit the Flavor Web site at:
 *     http://www.ee.columbia.edu/flavor
 *
 * -- Do not edit by hand --
 *
 */

package org.avm.elementary.protocol.avm.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import flavor.IBitstream;
import flavor.MapResult;

public class Message {
	int _type;

	public static final int MESSAGE_TYPE = 0;

	public Message() {
		_type = MESSAGE_TYPE;
	}

	public Message(int type) {
		_type = type;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public String toString() {
		return " type = " + _type;
	}

	public static String toHexaString(byte[] data) {
		byte[] buffer = new byte[data.length * 2];

		for (int i = 0; i < data.length; i++) {
			int rValue = data[i] & 0x0000000F;
			int lValue = (data[i] >> 4) & 0x0000000F;
			buffer[i * 2] = (byte) ((lValue > 9) ? lValue + 0x37
					: lValue + 0x30);
			buffer[i * 2 + 1] = (byte) ((rValue > 9) ? rValue + 0x37
					: rValue + 0x30);
		}
		return new String(buffer);
	}

	public static byte[] fromHexaString(String hexaString) {
		byte[] buffer = hexaString.getBytes();
		byte[] data = new byte[buffer.length / 2];
		for (int i = 0; i < data.length; i++) {
			int index = i * 2;
			int rValue = (buffer[i * 2] > 0x39) ? buffer[index] - 0x37
					: buffer[index] - 0x30;
			int lValue = (buffer[i * 2 + 1] > 0x39) ? buffer[index + 1] - 0x37
					: buffer[index + 1] - 0x30;
			data[i] = (byte) (((rValue << 4) & 0xF0) | (lValue & 0x0F));

		}
		return data;
	}

	public void put(OutputStream out) throws Exception {
		OutputBitstream bs = new OutputBitstream(out);
		put(bs);
		bs.flushbits();
	}

	public static class DefaultMessageFactory extends MessageFactory {

		protected Message get(InputStream in) throws Exception {
			InputBitstream bs = new InputBitstream(in);
			Message message = new Message();
			message.get(bs);

			return message;
		}

	}

	static {
		MessageFactory.factories.put(new Integer(MESSAGE_TYPE),
				new DefaultMessageFactory());
	}

	public int get(IBitstream _F_bs) throws IOException {
		int _F_ret = 0;
		MapResult _F_mr;
		_type = _F_bs.getbits(8);
		return _F_ret;
	}

	public int put(IBitstream _F_bs) throws IOException {
		int _F_ret = 0;
		MapResult _F_mr;
		_F_bs.putbits(_type, 8);
		return _F_ret;
	}
}
