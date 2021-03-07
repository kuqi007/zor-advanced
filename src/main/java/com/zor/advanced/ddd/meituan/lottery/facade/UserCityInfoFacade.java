package com.zor.advanced.ddd.meituan.lottery.facade;

import com.zor.advanced.ddd.meituan.lottery.context.LotteryContext;
import com.zor.advanced.ddd.meituan.lottery.domain.valobj.valobj.MtCityInfo;
import com.zor.advanced.ddd.meituan.lottery.facade.acl.LbsReq;
import com.zor.advanced.ddd.meituan.lottery.facade.acl.LbsResponse;
import com.zor.advanced.ddd.meituan.lottery.facade.acl.LbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kuqi0 on 2021/3/7
 */
@Component
public class UserCityInfoFacade {
    @Autowired
    private LbsService lbsService;

    public MtCityInfo getMtCityInfo(LotteryContext context) {
        LbsReq lbsReq = new LbsReq();
        lbsReq.setLat(context.getLat());
        lbsReq.setLng(context.getLng());
        LbsResponse lbsResponse = lbsService.getLbsCityInfo(lbsReq);
        return buildMtCityInfo(lbsResponse);
    }

    private MtCityInfo buildMtCityInfo(LbsResponse lbsResponse) {
        // ...将response转换为MtCityInfo
        return null;
    }
}
