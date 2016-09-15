package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

// 信号量测试
public class BoundedHashSet<T> {

	private final Set<T> set;
	private final Semaphore sem;

	public BoundedHashSet(int bound) {
		this.set = Collections.synchronizedSet(new HashSet<T>());
		sem = new Semaphore(bound);
	}

	public boolean add(T o) throws Exception {
		sem.acquire();
		boolean added = false;
		try {
			added = set.add(o);
			return added;
		} finally {
			if (!added) {
				sem.release();
			}
		}
	}

	public boolean remove(Object o) {
		boolean removed = set.remove(o);
		if (!removed) {
			sem.release();
		}
		return removed;
	}
}
