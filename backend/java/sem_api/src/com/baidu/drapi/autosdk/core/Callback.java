package com.baidu.drapi.autosdk.core;

public interface Callback<T> {
	
	void execResult(T result);

	void execError(Throwable error);
}
