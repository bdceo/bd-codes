package com.bdsoft.bjsxt.array;

public class Paly2 {

	public static void main(String[] args) {
		KidCircle kc = new KidCircle(500);
		int countNum = 0;
		Kid k = kc.first;
		while (kc.count > 1) {
			countNum++;
			if (countNum == 3) {
				countNum = 0;
				kc.delete(k);
			}
			k = k.right;
		}
		System.out.println(kc.first.id);
	}
}

class Kid {
	int id;
	Kid left;
	Kid right;
}

class KidCircle {
	int count = 0;
	Kid first;
	Kid last;

	KidCircle(int n) {
		for (int i = 0; i < n; i++) {
			add();
		}
	}

	public void add() {
		Kid k = new Kid();
		k.id = count;
		if (count <= 0) {
			first = k;
			last = k;
			k.right = k;
			k.left = k;
		} else {
			last.right = k;
			first.left = k;
			k.left = last;
			k.right = first;
			last = k;
		}
		count++;
	}

	public void delete(Kid k) {
		if (count <= 0) {
			return;
		} else if (count == 1) {
			first = last = null;
		} else {
			k.left.right = k.right;
			k.right.left = k.left;
			if (k == first) {
				first = k.right;
			} else if (k == last) {
				last = k.left;
			}
		}
		count--;
	}
}
