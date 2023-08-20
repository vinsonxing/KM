package interview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class DimentionalArrayTest {
	public static void main(String[] args) {
		Plane[][] planes1 
		  = new Plane[][] { new Plane[]{new Plane("Plane 1", "A320")}, new Plane[]{new Plane("Plane 2", "B738")}};
		Plane[][] planes2 
		  = new Plane[][] { new Plane[]{new Plane("Plane 2", "B738")}, new Plane[]{new Plane("Plane 1", "A320")}};
		System.out.println("Before sort:" + Arrays.deepEquals(planes1, planes2));
		Comparator<Plane> planeComparator = (o1, o2) -> {
			    if (o1.getName().equals(o2.getName())) {
			        return o2.getModel().compareTo(o1.getModel());
			    }
			    return o2.getName().compareTo(o1.getName());
		};
		Arrays.sort(planes1[0], planeComparator);
		Arrays.sort(planes2[0], planeComparator);
		System.out.println("After sort:" + Arrays.deepEquals(planes1, planes2)); //lier!
	}
}
class Plane{
	String name; 
	String model;
	public Plane(String name, String model) {
		super();
		this.name = name;
		this.model = model;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	@Override
	public boolean equals(Object o) {
	    if (this == o)
	        return true;
	    if (o == null || getClass() != o.getClass())
	        return false;
	    Plane plane = (Plane) o;
	    return Objects.equals(name, plane.name) && Objects.equals(model, plane.model);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(name, model);
	}
}
