package com.igeeksky.xtool.core.concurrent;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FuturesTest {

    private Future<?> future1;
    private Future<?> future2;
    private Future<?> future3;

    @BeforeEach
    public void setUp() {
        future1 = mock(Future.class);
        future2 = mock(Future.class);
        future3 = mock(Future.class);
    }

    @Test
    public void awaitAll_FuturesCompleted_NoException() {
        when(future1.isDone()).thenReturn(true);
        when(future2.isDone()).thenReturn(true);
        when(future3.isDone()).thenReturn(true);

        Futures.awaitAll(new Future<?>[]{future1, future2, future3});
    }

    @Test
    public void awaitAll_FuturesNotCompleted_WaitForCompletion() throws Exception {
        when(future1.isDone()).thenReturn(false);
        when(future2.isDone()).thenReturn(false);
        when(future3.isDone()).thenReturn(false);

        Futures.awaitAll(new Future<?>[]{future1, future2, future3});

        verify(future1).get();
        verify(future2).get();
        verify(future3).get();
    }

    @Test
    public void awaitAll_FuturesMixedState_WaitForUncompleted() throws Exception {
        when(future1.isDone()).thenReturn(true);
        when(future2.isDone()).thenReturn(false);
        when(future3.isDone()).thenReturn(true);

        Futures.awaitAll(new Future<?>[]{future1, future2, future3});

        verify(future2).get();
    }

    @Test
    public void awaitAll_EmptyList_NoException() {
        Futures.awaitAll(new Future<?>[]{});
    }

    @Test
    public void awaitAll_ListWithNulls_NoException() {
        Futures.awaitAll(new Future<?>[]{future1, null, future2});
    }

    @Test
    public void awaitAll_Timeout_AllCompletedBeforeTimeout() {
        when(future1.isDone()).thenReturn(true);
        when(future2.isDone()).thenReturn(true);
        when(future3.isDone()).thenReturn(true);

        int result = Futures.awaitAll(new Future<?>[]{future1, future2, future3}, 1000, TimeUnit.MILLISECONDS, 0);
        assertEquals(3, result);
    }

    @Test
    public void checkAll_AllCompleted_ReturnsLength() {
        when(future1.isDone()).thenReturn(true);
        when(future2.isDone()).thenReturn(true);
        when(future3.isDone()).thenReturn(true);

        int result = Futures.checkAll(new Future<?>[]{future1, future2, future3});
        assertEquals(3, result);
    }

    @Test
    public void checkAll_NotAllCompleted_ReturnsFirstUncompletedIndex() {
        when(future1.isDone()).thenReturn(true);
        when(future2.isDone()).thenReturn(false);
        when(future3.isDone()).thenReturn(true);

        int result = Futures.checkAll(new Future<?>[]{future1, future2, future3});
        assertEquals(1, result);
    }

    @Test
    public void checkAll_EmptyList_ReturnsZero() {
        int result = Futures.checkAll(new Future<?>[]{});
        assertEquals(0, result);
    }

    @Test
    public void cancelAll_AllCompleted_NoCancellation() {
        when(future1.isDone()).thenReturn(true);
        when(future2.isDone()).thenReturn(true);
        when(future3.isDone()).thenReturn(true);

        Futures.cancelAll(new Future<?>[]{future1, future2, future3}, false);

        verify(future1, never()).cancel(anyBoolean());
        verify(future2, never()).cancel(anyBoolean());
        verify(future3, never()).cancel(anyBoolean());
    }

    @Test
    public void cancelAll_EmptyList_NoCancellation() {
        Futures.cancelAll(new Future<?>[]{}, false);
    }

    @Test
    public void cancelAll_ListWithNulls_NoCancellation() {
        Futures.cancelAll(new Future<?>[]{future1, null, future2}, false);
    }

}
