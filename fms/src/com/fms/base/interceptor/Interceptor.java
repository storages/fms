package com.fms.base.interceptor;

import java.io.Serializable;

import com.opensymphony.xwork2.ActionInvocation;

/**
 * 拦截器接口
 * @author Administrator
 *
 */
public abstract interface Interceptor extends Serializable {

	/**
	 * 该方法与init()方法对应，在拦截器销毁之前，系统将回调该拦截器的destroy方法，该方法用于释放init方法中打开的资源。
	 * @param paramActionInvocation
	 * @return
	 * @throws Exception
	 */
	public abstract void destroy();

	/**
	 * 在该拦截器被初始化之后，在该拦截器执行拦截之前，系统将回调该方法，init()方法主要是用于打开一些资源，例如数据库资源。该方法只执行一次。
	 */
	public abstract void init();

	
	/**
	 * 该方法是用户需要拦截动作。就像Action的execute方法一样，intercept方法会返回一个字符串作为逻辑视图，
	 * 如果该方法直接返回了一个字符串，系统将会跳转到该逻辑视图对应地实际视图资源，不会调用被拦截的Action。
	 * 该方法的(ActionInvocation 参数包含了被拦截的action的引用，可以通过调用该参数的invoke方法，
	 * 将控制权转给下一个拦截器，或者转到action的exctute方法。
	 * @param paramActionInvocation
	 * @return
	 * @throws Exception
	 */
	public abstract String intercept(ActionInvocation paramActionInvocation) throws Exception;

}
