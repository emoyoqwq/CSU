package com.tco.misc;

public class IllegalPositionException extends Exception {

	public IllegalPositionException() {}

	public IllegalPositionException(String arg0) {
		super(arg0);
	}

	public IllegalPositionException(Throwable arg0) {
		super(arg0);
	}

	public IllegalPositionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public IllegalPositionException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
