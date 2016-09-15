package com.bdsoft.bdceo.refactor.ch08;

/**
 * 将引用对象改为值对象
 */
public class Demo04 {

	/**
	 * 重构前，币种类中code属性值，应该是不可变的
	 */
	class Currency {

		private String code;

		private Currency(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
	}

	/**
	 * 重构后：为保持值对象的不可变形，属性加final限制，并且覆写object类的equals和hashcode方法
	 * 
	 * 这样即使存在多个币种类实例，也能保持其等值效果
	 */
	class CurrencyPlus {

		final private String code;

		public CurrencyPlus(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof CurrencyPlus)) {
				return false;
			}
			CurrencyPlus cp = (CurrencyPlus) obj;
			return (code.equals(cp.code));
		}

		@Override
		public int hashCode() {
			return code.hashCode();
		}

	}

}
