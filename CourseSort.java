import java.util.ArrayList;
import java.util.Arrays;

public class CourseSort {

	public ArrayList<Course> sort(ArrayList<Course> crs) {

		for (int i = 0; i < crs.size(); i++) {
			for (int k = i + 1; k < crs.size(); k++) {
				Course temp = crs.get(k);
				// extract the numerical value from the CSCI- courses
				// by taking the last digits from the course ID string
				if (Integer.parseInt(crs.get(i).getCourse_ID().substring(8)) > Integer
						.parseInt(crs.get(k).getCourse_ID().substring(8))) {
					crs.set(k, crs.get(i));
					crs.set(i, temp);
				}
			}
		}
		return crs;
	}
}
