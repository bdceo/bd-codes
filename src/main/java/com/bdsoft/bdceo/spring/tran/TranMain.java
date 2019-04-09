package com.bdsoft.bdceo.spring.tran;

public class TranMain {

	public static final ThreadLocal<DataSources> tl = new ThreadLocal<DataSources>() {

		@Override
		protected DataSources initialValue() {
			return DataSources.MAIN;
		}
	};

	public static DataSources get() {
		return tl.get();
	}

	public static void set(DataSources ds) {
		tl.set(ds);
	}

	public static void reset() {
		tl.set(DataSources.MAIN);
	}

	public static void unbind() {
		tl.set(null);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated atm stub

	}

}

enum DataSources {
	MAIN, INFO, BAK
}
