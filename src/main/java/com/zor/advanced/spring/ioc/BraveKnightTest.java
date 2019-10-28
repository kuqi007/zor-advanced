package com.zor.advanced.spring.ioc;

import org.junit.Test;

import static org.mockito.Mockito.*;


public class BraveKnightTest {

    @Test
    public void knightShouldEmbarkOnQuest() {
        Quest mockQuest = mock(Quest.class);
        BraveKnight knight = new BraveKnight(mockQuest);
        knight.embarkOnQuest();
        verify(mockQuest, times(1)).embark();


    }
}
