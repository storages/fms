package com.fms.base.interceptor;

import java.io.Serializable;

import com.opensymphony.xwork2.ActionInvocation;

/**
 * �������ӿ�
 * @author Administrator
 *
 */
public abstract interface Interceptor extends Serializable {

	/**
	 * �÷�����init()������Ӧ��������������֮ǰ��ϵͳ���ص�����������destroy�������÷��������ͷ�init�����д򿪵���Դ��
	 * @param paramActionInvocation
	 * @return
	 * @throws Exception
	 */
	public abstract void destroy();

	/**
	 * �ڸ�����������ʼ��֮���ڸ�������ִ������֮ǰ��ϵͳ���ص��÷�����init()������Ҫ�����ڴ�һЩ��Դ���������ݿ���Դ���÷���ִֻ��һ�Ρ�
	 */
	public abstract void init();

	
	/**
	 * �÷������û���Ҫ���ض���������Action��execute����һ����intercept�����᷵��һ���ַ�����Ϊ�߼���ͼ��
	 * ����÷���ֱ�ӷ�����һ���ַ�����ϵͳ������ת�����߼���ͼ��Ӧ��ʵ����ͼ��Դ��������ñ����ص�Action��
	 * �÷�����(ActionInvocation ���������˱����ص�action�����ã�����ͨ�����øò�����invoke������
	 * ������Ȩת����һ��������������ת��action��exctute������
	 * @param paramActionInvocation
	 * @return
	 * @throws Exception
	 */
	public abstract String intercept(ActionInvocation paramActionInvocation) throws Exception;

}
