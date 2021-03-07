package com.zor.advanced.ddd.meituan;

import com.zor.advanced.ddd.meituan.common.ErrorData;
import com.zor.advanced.ddd.meituan.common.Response;
import com.zor.advanced.ddd.meituan.common.ResponseCode;
import com.zor.advanced.ddd.meituan.common.ResponseMsg;
import com.zor.advanced.ddd.meituan.lottery.context.LotteryContext;
import com.zor.advanced.ddd.meituan.dto.IssueResponse;
import com.zor.advanced.ddd.meituan.dto.LotteryConditionResult;
import com.zor.advanced.ddd.meituan.condition.service.LotteryConditionService;
import com.zor.advanced.ddd.meituan.risk.service.LotteryRiskService;
import com.zor.advanced.ddd.meituan.lottery.service.LotteryService;
import com.zor.advanced.ddd.meituan.dto.PrizeInfo;
import com.zor.advanced.ddd.meituan.dto.RiskAccessToken;
import com.zor.advanced.ddd.meituan.dto.RiskReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kuqi0 on 2021/3/7
 */
@Service
public class LotteryApplicationService {
    @Autowired
    private LotteryRiskService riskService;
    @Autowired
    private LotteryConditionService conditionService;
    @Autowired
    private LotteryService lotteryService;

    // 用户参与抽奖活动
    public Response<PrizeInfo, ErrorData> participateLottery(LotteryContext lotteryContext) {
        // 校验用户登录信息
        validateLoginInfo(lotteryContext);
        // 校验风险
        RiskAccessToken riskAccessToken = riskService.acquire(buildRiskReq(lotteryContext));
        //....
        // 活动准入检查
        LotteryConditionResult conditionResult = conditionService.checkLotteryCondition(lotteryContext.getLotteryId(), lotteryContext.getUserId());
        //...
        // 抽奖返回结果
        IssueResponse issueResponse = lotteryService.issueLottery(lotteryContext);
        if (issueResponse != null && issueResponse.getCode() == IssueResponse.OK) {
            return buildSuccessResponse(issueResponse.getPrizeInfo());
        } else {
            return buildErrorResponse(ResponseCode.ISSUE_LOTTERY_FAIL, ResponseMsg.ISSUE_LOTTERY_FAIL);
        }
    }

    private void validateLoginInfo(LotteryContext lotteryContext) {

    }

    private Response<PrizeInfo, ErrorData> buildErrorResponse(int code, String msg) {
        return null;
    }

    private Response<PrizeInfo, ErrorData> buildSuccessResponse(PrizeInfo prizeInfo) {
        return null;
    }

    private RiskReq buildRiskReq(LotteryContext lotteryContext) {
        return null;
    }

}
