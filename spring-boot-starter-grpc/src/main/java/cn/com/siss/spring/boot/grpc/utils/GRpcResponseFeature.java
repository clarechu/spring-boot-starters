package cn.com.siss.spring.boot.grpc.utils;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class GRpcResponseFeature<T> implements ListenableFuture<T> {
    private T response;

    public GRpcResponseFeature(T response) {
        this.response = response;
    }

    @Override
    public void addListener(Runnable listener, Executor executor) {

    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        return response;
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return response;
    }
}
