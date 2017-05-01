package com.fly.util;

import java.util.Comparator;

import com.fly.model.M2Power;

public class AscPowerComparator implements Comparator<M2Power>{

	@Override
	public int compare(M2Power o1, M2Power o2) {
		// TODO Auto-generated method stub
		return o1.getPower().getpCount() - o2.getPower().getpCount();
//		return 0;
	}
}
