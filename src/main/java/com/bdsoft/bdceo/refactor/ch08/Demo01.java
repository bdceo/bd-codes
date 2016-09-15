package com.bdsoft.bdceo.refactor.ch08;

/**
 * 自封装字段，原始类
 * 
 * @author 丁辰叶
 * @date 2014-11-21
 */
public class Demo01 {
	/**
	 * IntRange 重构前
	 * 
	 * IntRangePlus 重构后
	 * 
	 * CappedRange 重构后的好处体现
	 */
}

/**
 * 重构前，直接访问类内变量
 */
class IntRange {
	public int high;
	public int low;

	public IntRange(int high, int low) {
		this.high = high;
		this.low = low;
	}

	public boolean includes(int arg) {
		return arg >= low && arg <= high;
	}

	public void grow(int factor) {
		this.high = this.high * factor;
	}
}

/**
 * 重构后，采用取设值函数访问类内变量
 */
class IntRangePlus {

	private int high;
	private int low;

	public IntRangePlus(int high, int low) {
		this.high = high;
		this.low = low;
	}

	public boolean includes(int arg) {
		return arg >= getLow() && arg <= getHigh();
	}

	public void grow(int factor) {
		this.setLow(this.getHigh() * factor);
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}
}

// 继承，覆写父类设置函数，成为子类特有
class CappedRange extends IntRangePlus {
	private int cap;

	public CappedRange(int low, int high, int cap) {
		super(high, low);
		this.cap = cap;
	}

	// 复写父类的方法，返回修改后的属性值
	public int getHigh() {
		return Math.min(super.getHigh(), this.getCap());
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

}
