package rvt;

import java.util.HashMap;

public class IOU {
	HashMap<String, Double> IOUMap = new HashMap<>();

	public void setSum(String toWhom, double amount) {
		IOUMap.put(toWhom, amount);
	}

	public double howMuchDoIOweTo(String toWhom) {
		return IOUMap.getOrDefault(toWhom, 0.0);
	}

	public static void main(String[] args) {
		IOU OweIOU = new IOU();
		OweIOU.setSum("Arthur", 51.5);
		OweIOU.setSum("Michael", 10.5);

		System.out.println(OweIOU.howMuchDoIOweTo("Arthur"));
		System.out.println(OweIOU.howMuchDoIOweTo("Michael"));
	}
}
