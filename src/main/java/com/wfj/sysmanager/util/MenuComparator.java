package com.wfj.sysmanager.util;

import com.wfj.sysmanager.model.Symenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;

/**
 * 菜单排序
 * 
 * @author 孙宇
 * 
 */
public class MenuComparator implements Comparator<Symenu> {

	private static final Logger logger = LoggerFactory.getLogger(MenuComparator.class);

	public int compare(Symenu o1, Symenu o2) {
		int i1 = o1.getSeq() != null ? o1.getSeq().intValue() : -1;
		int i2 = o2.getSeq() != null ? o2.getSeq().intValue() : -1;
		logger.debug("i1[" + i1 + "]-i2[" + i2 + "]=" + (i1 - i2));
		return i1 - i2;
	}

}
