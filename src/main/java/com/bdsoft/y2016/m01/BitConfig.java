/**
 * BitConfig.java
 * com.bdsoft.y2016.m01
 * Copyright (c) 2016, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.y2016.m01;

public class BitConfig {

	public static void main(String[] args) {

		System.out.println(0xff);

		FunConfig fc = new FunConfig(0);
		fc.setB1_baoming(true);
		System.out.println(">>" + fc.genConfig());

		fc.setB2_toupiao(true);
		System.out.println(">>" + fc.genConfig());

		fc.setB3_tougao(true);
		System.out.println(">>" + fc.genConfig());

		fc.setB4_zhibo(true);
		System.out.println(">>" + fc.genConfig());

		int config = fc.genConfig();
		System.out.println("2=" + Integer.toBinaryString(config));

		fc = new FunConfig(config);
		System.out.println(fc);
	}

}

// 开启功能
class FunConfig {

	boolean b1_baoming = false; // 报名
	boolean b2_toupiao = false; // 投票
	boolean b3_tougao = false;// 投稿
	boolean b4_zhibo = false; // 直播

	public FunConfig(int config) {
		if ((config & 0x01) == 1) {
			setB1_baoming(true);
		}
		if ((config & 0x02) == 2) {
			setB2_toupiao(true);
		}
		if ((config & 0x04) == 4) {
			setB3_tougao(true);
		}
		if ((config & 0x08) == 8) {
			setB4_zhibo(true);
		}
	}

	public int genConfig() {
		int i = 0;
		if (isB1_baoming()) {
			i = 1;
		}
		if (isB2_toupiao()) {
			i += 2;
		}
		if (isB3_tougao()) {
			i += 4;
		}
		if (isB4_zhibo()) {
			i += 8;
		}
		return i;
	}

	@Override
	public String toString() {
		return "FunConfig [b1_baoming=" + b1_baoming + ", b2_toupiao=" + b2_toupiao + ", b3_tougao=" + b3_tougao
				+ ", b4_zhibo=" + b4_zhibo + "]";
	}

	public boolean isB1_baoming() {
		return b1_baoming;
	}

	public void setB1_baoming(boolean b1_baoming) {
		this.b1_baoming = b1_baoming;
	}

	public boolean isB2_toupiao() {
		return b2_toupiao;
	}

	public void setB2_toupiao(boolean b2_toupiao) {
		this.b2_toupiao = b2_toupiao;
	}

	public boolean isB3_tougao() {
		return b3_tougao;
	}

	public void setB3_tougao(boolean b3_tougao) {
		this.b3_tougao = b3_tougao;
	}

	public boolean isB4_zhibo() {
		return b4_zhibo;
	}

	public void setB4_zhibo(boolean b4_zhibo) {
		this.b4_zhibo = b4_zhibo;
	}

}