package com.bdsoft.y2011.m02;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UUID {
	private static final int IP;

	private static short counter = (short) 0;

	private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

	private final static String sep = "";
	static {
		int ipadd = 0;

		try {
			ipadd = iptoInt(InetAddress.getLocalHost().getAddress());
		} catch (UnknownHostException e) {
			ipadd = 0;
		}

		IP = ipadd;
	}

	protected static int iptoInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}

	private static UUID uuid = null;

	public static UUID getInstance() {
		if (null == uuid) {
			uuid = new UUID();
		}
		return uuid;
	}

	private UUID() {
	}

	protected String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	protected String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	public Serializable generate() {
		return new StringBuffer(36).append(format(getIP())).append(sep).append(
				format(getJVM())).append(sep).append(format(getHiTime()))
				.append(sep).append(format(getLoTime())).append(sep).append(
						format(getCount())).toString();
	}

	public static void main(String[] args) {
		UUID id = UUID.getInstance();
		String ge=id.generate().toString();
		System.out.println(ge);
	}

	/**
	 * Unique in a millisecond for this JVM instance (unless there are >
	 * Short.MAX_VALUE instances created in a millisecond)
	 */
	protected short getCount() {
		synchronized (this.getClass()) {
			if (counter < 0)
				counter = 0;
			return counter++;
		}
	}

	/**
	 * Unique down to millisecond
	 */
	protected short getHiTime() {
		return (short) (System.currentTimeMillis() >>> 32);
	}

	/**
	 * Unique in a local network
	 */
	protected int getIP() {
		return IP;
	}

	/**
	 * Unique across JVMs on this machine (unless they load this class in the
	 * same quater second - very unlikely)
	 */
	protected int getJVM() {
		return JVM;
	}

	protected int getLoTime() {
		return (int) System.currentTimeMillis();
	}

}
