package com.zor.advanced.ddd.meituan.lottery.domain.valobj.valobj;

import lombok.Data;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kuqi0 on 2021/3/7
 */
@Data
public class AwardPool {
    // 奖池支持的城市
    private String cityIds;
    // 奖池支持的得分
    private String scores;
    // 奖池匹配的用户类型
    private int userGroupType;
    // 奖池中包含的奖品
    private List<Award> awards;

    // 当前奖池
    public boolean matchedCity(int cityId) {
        //......具体逻辑
        // 是否包含该城市id
        return true;
    }

    public boolean matchScore(int score) {
        // 是否包含该分数
        return true;
    }

    // 根据概率选择奖池
    public Award randomGetAward() {
        int sumOfProbability = 0;
        for (Award award : awards) {
            sumOfProbability += award.getAwardProbability();
        }
        int randomNumber = ThreadLocalRandom.current().nextInt(sumOfProbability);
        int range = 0;
        for (Award award : awards) {
            range += award.getAwardProbability();
            if (randomNumber < range) {
                return award;
            }
        }
        return null;
    }


}
