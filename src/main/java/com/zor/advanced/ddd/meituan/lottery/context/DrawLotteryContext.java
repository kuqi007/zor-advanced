package com.zor.advanced.ddd.meituan.lottery.context;

import com.zor.advanced.ddd.meituan.lottery.domain.valobj.valobj.MtCityInfo;
import lombok.Data;

/**
 * Created by kuqi0 on 2021/3/7
 */
@Data
public class DrawLotteryContext {

    private MtCityInfo mtCityInfo;

    public int gameScore;
}
