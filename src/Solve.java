import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

class Solve {

	private static final Comparator<Plate> PlateComparator = (plate1, plate2) -> plate2.getLongerLength()
			- plate1.getLongerLength(); // 오름차순 Comparator

	ArrayList<Plate> plate;
	Cursor cursor;
	View view;

	// 커서 생성
	Solve(ArrayList<Plate> plate) {

		//int maxSum = plate.stream().mapToInt(p->p.getLongerLength()).sum();

		cursor = new Cursor(Main.backPlateWitch);
		this.plate = plate;
		view = new View(cursor);

	}

	public int running() {
		// TODO 알고리즘 시작 메소드

		sortDescending(); // 오름차순 정렬
		checkOverWitch(); // 폭보다 큰 철판 있으면 회전하고 리스트 맨앞으로 이동하고 철판 입력

		// 철판이 전부 잘릴때까지 반복
		while (!plate.isEmpty()) {
			if (cursorCheck()) { // 커서의 현재 위치에 들어갈 수 있는 철판이 있는지 검사
				slicePlate(); // 입력 가능한 철판이 있는지 검사하고 있으면 철판 입력

			}
			// 입력할 철판이 없으면 커서 이동
			else
				cursor.moveY();

		} // 철판이 전부 잘림.
		
		return cursor.getMaxHeight();

	}

	boolean cursorCheck() {
		// TODO 커서의 위치에 철판이 입력될 수 있는지 검사
		// 커서 체크할 때 유효 폭과 제일 큰놈 비교해서 유효폭이 더 작으면 유효 높이 증가

		int availableWidth = cursor.getAvailableWidth();

		Optional<Plate> check = plate.stream()
				.filter(plate -> plate.getWidth() <= availableWidth || plate.getHeight() <= availableWidth)
				.findFirst();
		
		return check.isPresent();

	}

	private void slicePlate() {
		// TODO 입력 가능한 철판이 검사하고 있으면 철판 입력

		Optional<Plate> op_slicePlate = Optional.empty();
		Plate slicePlate = null;
		
		// 입력할 수 있는 철판 찾기
		while (true) {

			// 첫번째 검사 로직
			op_slicePlate = plate.stream()
					.filter(plate -> plate.getHeight() <= cursor.getAvailableHeight())
					.filter(plate -> plate.getWidth() <= cursor.getAvailableWidth())
					.findFirst();

			// 값을 찾으면 break
			if (op_slicePlate.isPresent()) {
				slicePlate = op_slicePlate.get();

				break;
			} // 첫번째 검사 종료
				// 값이 없으면 폭, 넓이를 바꿔서 찾아보고 있으면 해당 철판 회전 후 break
			else {
				op_slicePlate = plate.stream()
						.filter(plate -> plate.getHeight() <= cursor.getAvailableWidth())
						.filter(plate -> plate.getWidth() <= cursor.getAvailableHeight())
						.findFirst();

				if (op_slicePlate.isPresent()) {
					slicePlate = op_slicePlate.get();
					slicePlate.rotate();
					break;

				}
				// 입력할 수 있는 철판이 없으면 최대 높이 증가
			} // 회전 후 재검사 종료

		} // 입력할 수 있는 철판 찾기 종료

		// 후판에 철판 입력 후 리스트에서 철판 삭제
		
		//Scanner s = new Scanner(System.in);
		//s.next();
		/*try{
			Thread.sleep(1000);
			
		}catch(Exception ee){}*/
		
		//System.out.println("\n유효폭 : " + cursor.getAvailableWidth() + ", " + "유효높이 : " + cursor.getAvailableHeight());
		//System.out.println("입력되는 철판의 폭 = " + slicePlate.getWidth() + ", 입력되는 철판의 높이 : " + slicePlate.getHeight());

		cursor.inputPlate(view.plate_view, slicePlate.getWidth(), slicePlate.getHeight());

		if(Main.argsCheck.equals("-t"))
			view.drawPlate(Main.sleepTime);
		
		//view.drawPlate();
		plate.remove(slicePlate);

		// System.out.println(slicePlate);
		//System.out.println("현재 커서 위치 : " + cursor.getLocation());
		//System.out.println("유효 폭 : " + cursor.getAvailableWidth() + " 유효 높이 : " + cursor.getAvailableHeight());

		//view.printView();

	}
	

	// 철판 폭 또는 높이가 긴 순서대로 오름차순 정렬
	private void sortDescending() {

		plate = (ArrayList<Plate>) this.plate.stream()
											 .sorted(PlateComparator)
											 .collect(toList());

	}

	private void checkOverWitch() {
		// TODO Auto-generated method stub

		int moveIndex = 0;

		for (int i = 0; i < plate.size(); i++) {

			// 폭이 길면 회전하고 리스트 앞쪽으로 이동
			if (plate.get(i).getWidth() > Main.backPlateWitch) {
				plate.get(i).rotate();

				plate.add(moveIndex++, plate.get(i));
				plate.remove(i--); // 지워진 인덱스를 다시 검사하도록 하기 위하여 i--
				continue;
			}
			// 철판을 전부 회전시키고 난 후 큰 것들 먼저 입력
			else {
				if (plate.get(i).getLongerLength() <= Main.backPlateWitch) {

					Plate temp;
					for (int j = 0; j < moveIndex; j++) {
						temp = plate.get(j);
						int height = temp.getHeight();
						int width = temp.getWidth();
						cursor.inputPlate(view.plate_view, width, height);
						plate.remove(temp);
						
						if(Main.argsCheck.equals("-t"))
							view.drawPlate(Main.sleepTime);

					}
					return;
				}

			}

		}

	}

}
