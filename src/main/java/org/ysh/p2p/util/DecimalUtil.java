package org.ysh.p2p.util;

import java.math.BigDecimal;


public class DecimalUtil {
	 public static BigDecimal toMillon(BigDecimal b)
	  {
	    return toMillon(b, true);
	  }
	 
	  public static BigDecimal toMillon(BigDecimal b, boolean nullable)
	  {
	    return movePoint(b, nullable, 4, true);
	  }
	 
	  public static BigDecimal toYuan(BigDecimal millon)
	  {
	    return toYuan(millon, true);
	  }
	 
	  public static BigDecimal toYuan(BigDecimal millon, boolean nullable)
	  {
	    return movePoint(millon, nullable, 4, false);
	  }
	 
	  public static BigDecimal toPercent(BigDecimal rate)
	  {
	    return toPercent(rate, true);
	  }
	 
	  public static BigDecimal toPercent(BigDecimal rate, boolean nullable)
	  {
	    return movePoint(rate, nullable, 2, false);
	  }
	 
	  public static BigDecimal fromPercent(BigDecimal percent)
	  {
	    return fromPercent(percent, true);
	  }
	 
	  public static BigDecimal fromPercent(BigDecimal percent, boolean nullable)
	  {
	    return movePoint(percent, nullable, 2, true);
	  }
	 
	  public static BigDecimal toPermillage(BigDecimal decimal)
	  {
	    return toPermillage(decimal, true);
	  }
	 
	  public static BigDecimal toPermillage(BigDecimal decimal, boolean nullable)
	  {
	    return movePoint(decimal, nullable, 3, false);
	  }
	  
	  public static BigDecimal fromPermillage(BigDecimal permillage)
	  {
	    return fromPermillage(permillage, true);
	  }
	 
	  public static BigDecimal fromPermillage(BigDecimal permillage, boolean nullable)
	  {
	    return movePoint(permillage, nullable, 3, true);
	  }
	 
	  public static BigDecimal toMillonage(BigDecimal decimal)
	  {
	    return toMillonage(decimal, true);
	  }
	 
	  public static BigDecimal toMillonage(BigDecimal decimal, boolean nullable)
	  {
	    return movePoint(decimal, nullable, 4, false);
	  }
	 
	  public static BigDecimal fromMillonage(BigDecimal millonage)
	  {
	    return fromMillonage(millonage, true);
	  }
	 
	  public static BigDecimal fromMillonage(BigDecimal millonage, boolean nullable)
	  {
	    return movePoint(millonage, nullable, 4, true);
	  }
	 
	  protected static BigDecimal movePoint(BigDecimal value, boolean nullable, int n, boolean moveLeft)
	  {
	    if (StringUtil.isEmpty(value)) {
	      return nullable ? null : new BigDecimal("0.00");
	    }
	    BigDecimal bigRate = new BigDecimal(value.toString());
	    return moveLeft ? bigRate.movePointLeft(n) : bigRate.movePointRight(n);
	  }
	 
	  public static BigDecimal ifnull(BigDecimal num, BigDecimal defaultnum)
	  {
	    return num == null ? defaultnum : num;
	  }
	 
	public static BigDecimal ifnull(BigDecimal num) {
		return ifnull(num, BigDecimal.ZERO);
	}
	 
	  public static Integer ifnull(Integer num, Integer defaultnum)
	  {
	    return num == null ? defaultnum : num;
	  }
	 
	 
	  public static Integer ifnull(Integer num)
	  {
	    return ifnull(num, Integer.valueOf(0));
	  }
}
