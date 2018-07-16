package com.str.engg.shape;

public class CompositeShape extends Shape {

	private final Shape bottomShape, topShape;

	public CompositeShape(Shape bottomShape, Shape topShape) {
		this.bottomShape = bottomShape;
		this.topShape = topShape;
	}

	@Override
	public double getCompressionArea(double neutralaxisDepth_x) {
		if (neutralaxisDepth_x < topShape.getTotalHeight()) {
			return topShape.getCompressionArea(neutralaxisDepth_x);
		} else {
			return bottomShape.getCompressionArea(neutralaxisDepth_x - topShape.getTotalHeight()) + topShape.getArea();
		}
	}

	@Override
	public void calculateSectionProperties() {

		if (topShape.getE() == 0) {

			area = bottomShape.getArea() + topShape.getArea();
			totalHeight = bottomShape.getTotalHeight();
			yb = bottomShape.getYb();
			yt = totalHeight - yb;
			Ix = bottomShape.getIx();
			Zb = Ix / yb;
			Zt = Ix / yt;
			E = bottomShape.getE();
			weightPerMeter = bottomShape.getWeightPerMeter() + topShape.getWeightPerMeter();

		} else {

			area = bottomShape.getArea() + topShape.getArea();
			totalHeight = bottomShape.getTotalHeight() + topShape.getTotalHeight();
			yb = (bottomShape.getArea() * bottomShape.getYb() + topShape.getArea()
					* (topShape.getE() / bottomShape.getE()) * (topShape.getYb() + bottomShape.totalHeight)) / area;
			yt = totalHeight - yb;
			Ix = (bottomShape.getIx() + bottomShape.getArea() * Math.pow((yb - bottomShape.getYb()), 2))
					+ (topShape.getIx()*(topShape.getE() / bottomShape.getE()) + topShape.getArea() * (topShape.getE() / bottomShape.getE())
							* Math.pow((yt - topShape.getYt()), 2));
			Zb = Ix / yb;
			Zt = Ix / yt;

			E = bottomShape.getE();
			weightPerMeter = bottomShape.getWeightPerMeter() + topShape.getWeightPerMeter();

		}

	}

}
