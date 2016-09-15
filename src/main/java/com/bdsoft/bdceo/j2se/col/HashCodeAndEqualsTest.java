package com.bdsoft.bdceo.j2se.col;

public class HashCodeAndEqualsTest {

	/**
	 * 关于hashCode和equals方法的重写问题及其相关问题
	 */
	public static void main(String[] args) {

	}

}

class Student {
	int id;
	String name;
	boolean boy;

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (this == o) {
			return true;
		}

		if (!(o instanceof Student)) {
			return false;
		}

		Student that = (Student) o;
		if (this.id == that.id && this.name.equals(that.name)
				&& this.boy == that.boy) {
			return true;
		} else {
			return false;
		}
	}

	public int hashCode() {
		return 0; 
	}
}