package com.bdsoft.bdceo.dp.decorator;

/**
 * 装饰器模式：动态为已有实现添加新功能
 * 
 */
public class Printer {

	public static void main(String[] args) {
		Factory fat = new Factory();
		Component com = fat.getComponent();
		com.prtTicket();
	}
}

// 工厂类，实现装饰器模式的组装，使得客户端与其分离
class Factory {

	public Component getComponent() {
		Component comp;
		comp = new SalesTicket();
		comp = new Footer1(comp);
		comp = new Header2(comp);
		comp = new Header1(comp);
		return comp;
	}
}

// 组件定义
abstract class Component {
	abstract void prtTicket();
}

// 通用实现
class SalesTicket extends Component {
	public void prtTicket() {
		System.out.println("Sales Ticket");
	}
}

// 装饰定义
abstract class TicketDecorator extends Component {

	private Component myTrailer;

	public TicketDecorator(Component myComponent) {
		this.myTrailer = myComponent;
	}

	public void callTrailer() {
		if (myTrailer != null) {
			myTrailer.prtTicket();// 通用实现
		}
	}

	// TODO 抽象，未实现prtTicket方法，留给子类实现,从而实现装饰器模式
	abstract void prtTicket();
}

// 打印页眉扩展实现
class Header1 extends TicketDecorator {

	public Header1(Component myComponent) {
		super(myComponent);
	}

	public void prtTicket() {
		System.out.println("Header-1=======");
		super.callTrailer();
	}
}

class Header2 extends TicketDecorator {

	public Header2(Component myComponent) {
		super(myComponent);
	}

	public void prtTicket() {
		System.out.println("Header-2+++++++");
		super.callTrailer();
	}
}

// 打印页脚扩展实现
class Footer1 extends TicketDecorator {

	public Footer1(Component myComponent) {
		super(myComponent);
	}

	public void prtTicket() {
		super.callTrailer();
		System.out.println("Footer-1________");
	}
}

class Footer2 extends TicketDecorator {

	public Footer2(Component myComponent) {
		super(myComponent);
	}

	public void prtTicket() {
		super.callTrailer();
		System.out.println("Footer-2........");
	}
}