package com.bdsoft.bdceo.j2se.excep;

import java.io.IOException;

public class ExcTest {

}

interface inter {
	public void execute();

	public void forward() throws Exception;

	public void redret() throws IOException;
}

class Test implements inter {
	public void execute() {

	}

	// public void forward() {
	//
	// }

	public void forward() throws IOException {

	}

	// Exception Exception is not compatible with throws clause in
	// inter.redret()
	// public void redret() throws Exception{
	//		
	// }
	public void redret() throws IOException {

	}
}
