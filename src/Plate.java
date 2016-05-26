public class Plate {

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	private int x;
	private int y;
	private int width;
	private int height;

	Plate(int w, int h) {
		width = w;
		height = h;
	}

	Plate(int w, int h, int x, int y) {
		width = w;
		height = h;
		this.x = x;
		this.y = y;
	}
	

	// 90도 회전
	public void rotate() {
		int temp;

		temp = width;
		width = height;
		height = temp;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getLongerLength() {
		return Math.max(width, height);
	}

	public int getShorterLength(){
		return Math.min(width, height);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
	
		return String.format("왼쪽하단>>[%3d,%3d], 오른쪽상단>>[%3d,%3d]", y, x, y+width, x+height);
		
	}

}