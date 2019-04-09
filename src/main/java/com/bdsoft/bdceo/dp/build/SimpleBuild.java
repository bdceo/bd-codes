package com.bdsoft.bdceo.dp.build;

public class SimpleBuild {

	/**
	 * 构造模式
	 */
	public static void main(String[] args) {
		Direct dir = new Direct();
		Car car = dir.genCar();
		System.out.println("OK - " + car);

		Train train = dir.genTrain();
		System.out.println("OK - " + train);
	}

}

// 总的建造类
class Direct {
	private IBuilder carBuilder;
	private IBuilder trainBuilder;

	public Direct() {
		this.carBuilder = new CarBuilder();
		this.trainBuilder = new TrainBuilder();
	}

	// 持有建造产品的建造者实例，封装了所有的建造逻辑及步骤，最终返回成品
	public Car genCar() {
		carBuilder.genEngine();
		carBuilder.genSite();
		carBuilder.genWheel();
		return carBuilder.genCar();
	}

	public Train genTrain() {
		trainBuilder.genBox();
		trainBuilder.genLine();
		return trainBuilder.genTrain();
	}
}

// 产品的抽象
class Car {
	public String toString() {
		return "a new car";
	}
}

class Train {
	public String toString() {
		return "a new train";
	}
}

// 在一个对象的构造过程中，可能涉及过多的步骤或逻辑，使用建造模式
// 抽象的建造者，需要列出产品制造的所有步骤
interface IBuilder {

	Car genCar();

	void genEngine();

	void genSite();

	void genWheel();

	// 建造火车的逻辑
	Train genTrain();

	void genBox();

	void genLine();
}

// 建造的适配层，其方法都是空实现，需要子类实现某一对象的实例化过程中需要用到的步骤
class BuilderAdapter implements IBuilder {
	public Car genCar() {
		return null;
	}

	public void genEngine() {
	}

	public void genSite() {
	}

	public void genWheel() {
	}

	public Train genTrain() {
		return null;
	}

	public void genBox() {
	}

	public void genLine() {
	}
}

// 具体的建造者，只需要实现具体的某一类构造方法
class CarBuilder extends BuilderAdapter {

	@Override
	public Car genCar() {
		System.out.println("CarBuilder-genCar");
		return new Car();
	}

	@Override
	public void genEngine() {
		System.out.println("CarBuilder-genEngine");
	}

	@Override
	public void genSite() {
		System.out.println("CarBuilder-genSite");
	}

	@Override
	public void genWheel() {
		System.out.println("CarBuilder-genWheel");
	}
}

class TrainBuilder extends BuilderAdapter {

	public Train genTrain() {
		System.out.println("TrainBuilder-genTrain");
		return new Train();
	}

	public void genBox() {
		System.out.println("TrainBuilder-genBox");
	}

	public void genLine() {
		System.out.println("TrainBuilder-genLine");
	}
}
