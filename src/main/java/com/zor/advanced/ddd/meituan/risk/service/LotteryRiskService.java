package com.zor.advanced.ddd.meituan.risk.service;

import com.zor.advanced.ddd.meituan.dto.RiskAccessToken;
import com.zor.advanced.ddd.meituan.dto.RiskReq;

/**
 * Created by kuqi0 on 2021/3/7
 */
public interface LotteryRiskService {
    RiskAccessToken acquire(RiskReq buildRiskReq);
}
