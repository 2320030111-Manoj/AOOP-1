package eight;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
 
public class Lambda {

	public static void main(String[] args) {
		
		List<String> w = new ArrayList<>();
		w.add("Labmorgini");
		w.add("Audi");
		w.add("Bmw");
		w.add("Maruti");
		
		w.sort((str1, str2) -> str2.compareTo(str1));
		
		System.out.println(w);
	}

}
