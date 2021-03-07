package com.zor.advanced.ddd.meituan.lottery.facade.acl;

/**
 * 假设此为第三方服务
 * Created by kuqi0 on 2021/3/7
 */
public interface LbsService {

    LbsResponse getLbsCityInfo(LbsReq lbsReq);

}
