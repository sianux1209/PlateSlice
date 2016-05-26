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
		// TODO : �簢���� �ԷµǴ� ���ǿ� �� �Է� �� Ŀ�� ���� ��ġ�� �̵�
		// �簢���� �ԷµǴ� ���ǿ� �� �Է�;

		plate_view.add(new Plate(w, h, x, y)); // view�� �߰�

		int width = w + y;
		int height = h + x;
		// System.out.printf("ö���� ũ�� : [%d * %d]\n", w, h);
		// System.out.printf("[%d,%d]���� [%d,%d]���� %d �Է�\n", y, x, width - 1,
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
		// TODO : ���� ��� ������ ���̸� ��ȯ
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
		// TODO : ���� ��� ������ ���� ��ȯ

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
		// TODO : ���� Ŀ���� ���� ��ȯ

		return backPlate[y][x];
	}

	public int getCoordinate(int x, int y) {
		// TODO : ���� Ŀ���� ���� ��ȯ

		return backPlate[y][x];
	}

}
