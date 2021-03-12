package com.bdsoft;

import java.text.MessageFormat;

public class DtTest {

	public static void main(String[] args) {
		long utc = System.currentTimeMillis();
		System.out.println(utc);
		 
		boolean tt = true;
		boolean ff = false;
		
		if(ff || tt){
			System.out.println("ddd");
		}


		String tmp = "update t_receipt r, t_mst_organization o set r.sale_store_id=o.id where r.vin=''{0}'' and o.code=''{1}'';";
		System.out.println(MessageFormat.format(tmp, "aa","22"));
	}
	 

}
