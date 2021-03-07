package com.zor.advanced.ddd.meituan.lottery.repo.repository;

import com.zor.advanced.ddd.meituan.lottery.domain.aggregate.DrawLottery;
import com.zor.advanced.ddd.meituan.lottery.repo.dao.AwardDao;
import com.zor.advanced.ddd.meituan.lottery.repo.dao.AwardPoolDao;
import com.zor.advanced.ddd.meituan.lottery.repo.repository.DrawLotteryCacheAccessObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kuqi0 on 2021/3/7
 */
@Repository
public class DrawLotteryRepository {
    @Autowired
    private AwardDao awardDao;
    @Autowired
    private AwardPoolDao awardPoolDao;
    @Autowired
    private DrawLotteryCacheAccessObj drawLotteryCacheAccessObj;

    public DrawLottery getDrawLotteryById(int lotteryId) {
        DrawLottery drawLottery = drawLotteryCacheAccessObj.get(lotteryId);
        if (drawLottery != null) {
            return drawLottery;
        }
        drawLottery = getDrawLotteryFromDB(lotteryId);
        drawLotteryCacheAccessObj.add(lotteryId, drawLottery);
        return drawLottery;
    }

    private DrawLottery getDrawLotteryFromDB(int lotteryId) {
        // ....
        return null;
    }
}
