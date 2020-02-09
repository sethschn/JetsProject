package com.skilldistillery.jets;

import java.text.NumberFormat;
import java.util.Locale;

public abstract class Jet {
	private String model;
	private double speed;
	private int range;
	private long price;
	
	
	public Jet(String model, double speed, int range, long price) {
		super();
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
	}
	
	public void fly() {
		String className = this.getClass().getSimpleName();
		double amtOfTime = (this.range / this.speed);
		System.out.println(this);	
		System.out.println(className+"> flying for "+String.format("%.2f", amtOfTime)+" hours");
		System.out.println();
		//prints out the jet details and the amount of time the jet can fly until it runs out of fuel (based on speed and range
	}
	
	public double getSpeedInMach() {
		return this.speed;
	}

	@Override
	public String toString() {
		String className = this.getClass().getSimpleName();
		double rangeDec = this.range;
		return 	className+"> model: " + model + ", speed: " + speed + " km/h, range: " + rangeDec + " km, price: " + NumberFormat.getCurrencyInstance(new Locale("en", "US"))
        .format(this.price) + "";	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + (int) (price ^ (price >>> 32));
		result = prime * result + range;
		long temp;
		temp = Double.doubleToLongBits(speed);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jet other = (Jet) obj;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (price != other.price)
			return false;
		if (range != other.range)
			return false;
		if (Double.doubleToLongBits(speed) != Double.doubleToLongBits(other.speed))
			return false;
		return true;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

}
