import java.util.ArrayList;

class Cursor {
	int backPlate[][];
	int backPlateWidth;
	static int plateNumber;
	int x;
	int y;
	int maxHeight;
	static final int limitHeight = 100 * Main.plateQtt; 
	static int Ycount;


	Cursor(int backPlateWidth) {

		Ycount = 0;
		this.backPlateWidth = backPlateWidth;
		backPlate = new int[backPlateWidth + 1][limitHeight];
		x = 0;
		y = 0;
		maxHeight = Integer.MIN_VALUE;
		plateNumber = 1;

	}

	public int getBackPlateWidth() {
		return backPlateWidth;
	}



	public void moveY() {
		y++;

		if (y >= backPlateWidth)
			moveX();
		else if (getCoordinate() != 0)
			moveY();
	}

	public void moveY(int w) {
		y += w;

		if (y >= backPlateWidth)
			moveX();
		else if (getCoordinate() != 0)
			moveY();
	}

	public void moveX() {
		y = 0;
		x++;

		if (getCoordinate() != 0)
			moveY();

	}


	public void inputPlate(ArrayList<Plate> plate_view, int w, int h) {
		// TODO : 사각형이 입력되는 후판에 값 입력 후 커서 다음 위치로 이동
		// 사각형이 입력되는 후판에 값 입력;

		plate_view.add(new Plate(w, h, x, y)); // view에 추가

		int width = w + y;
		int height = h + x;
		// System.out.printf("철판의 크기 : [%d * %d]\n", w, h);
		// System.out.printf("[%d,%d]에서 [%d,%d]까지 %d 입력\n", y, x, width - 1,
		// height - 1, plateNumber);
		for (int i = x; i < height; i++)
			for (int j = y; j < width; j++) {
				backPlate[j][i] = plateNumber;
			}

		moveY(w);

		plateNumber++;

		setMaxHeight(height);
		

	}

	public void setMaxHeight(int height) {
		// TODO Auto-generated method stub
		maxHeight = Math.max(maxHeight, height);
	}
	
	public int getMaxHeight(){
		return maxHeight;
		
	}
	
	

	public int getAvailableHeight() {
		// TODO : 현재 사용 가능한 높이를 반환
		int xTemp = x;
		int availableHeight = 0;

		for (int i = x; i < limitHeight; i++) {
			if (getCoordinate(xTemp++, y) == 0)
				availableHeight++;
			else
				break;

		}

		return availableHeight;
	}

	public int getY() {
		return y;
	}

	public int getAvailableWidth() {
		// TODO : 현재 사용 가능한 폭을 반환

		int yTemp = y;
		int availableWidth = 0;

		for (int i = y; i < backPlateWidth; i++) {
			if (getCoordinate(x, yTemp++) == 0)
				availableWidth++;
			else
				break;

		}

		return availableWidth;

	}

	public int getCoordinate() {
		// TODO : 현재 커서의 값을 반환

		return backPlate[y][x];
	}

	public int getCoordinate(int x, int y) {
		// TODO : 현재 커서의 값을 반환

		return backPlate[y][x];
	}

}
