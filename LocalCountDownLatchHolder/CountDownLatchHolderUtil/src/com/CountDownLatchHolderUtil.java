/**
 * 
 */
package com;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author rku140
 * Utility Class
 * This is Custom Holder for ContDownLatch objects of different types.
 * Scenario: Suppose we have multiple action to be executed and each actions further can be divided 
 * into sub actions that needs to executed parallel using countDownLatch
 *
 */
public class CountDownLatchHolderUtil {

	private static final Map<String, CountDownLatch> countDownLatchMap = new HashMap<String, CountDownLatch>();

	private Map<String, Integer> countDownNameNumberMap;

	public void setCountDownNameNumberMap(
			final Map<String, Integer> countDownNameNumberMap) {
		this.countDownNameNumberMap = countDownNameNumberMap;
		if (this.countDownNameNumberMap != null
				&& this.countDownNameNumberMap.size() > 0) {
			for (String countDownStr : this.countDownNameNumberMap.keySet()) {
				final int count = this.countDownNameNumberMap.get(countDownStr)
						.intValue();
				final CountDownLatch countDownLatch = new CountDownLatch(count);

				countDownLatchMap.put(countDownStr, countDownLatch);
			}
		}
	}

	public static CountDownLatch getCountDownLatch(
			final String countDownLatchName) {
		if (countDownLatchMap != null
				&& countDownLatchMap.containsKey(countDownLatchName)) {
			return countDownLatchMap.get(countDownLatchName);
		} else {
			return null;
		}
	}

	/**
	 * Here we are re-initializing the CountDownLatch for passed count down
	 * latch. It creates new CounDownLatch Object and puts into Main map holder.
	 * 
	 * @param countDownLatchName
	 * @return boolean
	 */
	public boolean resetCounDownLatch(final String countDownLatchName) {
		if (countDownLatchMap != null
				&& countDownLatchMap.containsKey(countDownLatchName)) {
			final int count = countDownNameNumberMap.get(countDownLatchName)
					.intValue();

			final CountDownLatch countDownLatch = new CountDownLatch(count);

			countDownLatchMap.put(countDownLatchName, countDownLatch);
			return Boolean.TRUE;

		} else {
			return Boolean.FALSE;

		}

	}

}
