package Vista;

public class Imagen {
	String path;
	int x, y, weight, heigh;
	boolean border = false;

	public Imagen() {

	}

	public Imagen(String s, int xs, int ys, int weights, int heighs) {
		path = s;
		x = xs;
		y = ys;
		weight = weights;
		heigh = heighs;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setX(String x) {
		this.x = Integer.parseInt(x);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setY(String y) {
		this.y = Integer.parseInt(y);
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setWeight(String weight) {
		this.weight = Integer.parseInt(weight);
	}

	public int getHeigh() {
		return heigh;
	}

	public void setHeigh(int heigh) {
		this.heigh = heigh;
	}

	public void setHeigh(String heigh) {
		this.heigh = Integer.parseInt(heigh);
	}

	public boolean isBorder() {
		return border;
	}

	public void setBorder(boolean border) {
		this.border = border;
	}
}
