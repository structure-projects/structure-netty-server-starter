package cn.structure.starter.netty.server.exception;

/**
 * @version V1.0.0
 * @Title: InvokerRescanException
 * @Package com.deramac.invokerstarted.exception
 * @Description: 执行器重复扫描
 * @author: liuChuanqiang
 * @date: 2019/5/27 15:56
 */
public class InvokerRescanException extends RuntimeException {
	public InvokerRescanException(String message) {
		super("重复扫描执行器 :" + message);
	}
}
