package org.ysh.p2p;

import java.util.List;

import org.ysh.p2p.enums.ProductEnum;
import org.ysh.p2p.util.CacheUtil;
import org.ysh.p2p.vo.DropDownDto;

public class CacheUtilTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<DropDownDto> configList = CacheUtil.getConfigList(ProductEnum.REPAY_METHOD.getCode());
		for(DropDownDto dto : configList){
			System.out.println(dto);
		}
		
	}

}
