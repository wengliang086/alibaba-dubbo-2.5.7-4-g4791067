package com.alibaba.dubbo.extension;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.ProxyFactory;
import com.alibaba.dubbo.rpc.RpcException;

@Adaptive
public class MyProxyFactoryAdaptive implements ProxyFactory {

	public MyProxyFactoryAdaptive() {
		super();
	}

	public <T> T getProxy(Invoker<T> invoker) throws RpcException {
		if (invoker == null)
			throw new IllegalArgumentException("com.alibaba.dubbo.rpc.Invoker argument == null");
		if (invoker.getUrl() == null)
			throw new IllegalArgumentException("com.alibaba.dubbo.rpc.Invoker argument getUrl() == null");
		URL url = invoker.getUrl();
		String extName = url.getParameter("proxy", "javassist");
		if (extName == null)
			throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.rpc.ProxyFactory) name from url(" + url.toString() + ") use keys([proxy])");
		ProxyFactory extension = (ProxyFactory) ExtensionLoader.getExtensionLoader(ProxyFactory.class).getExtension(extName);
		return extension.getProxy(invoker);
	}

	public <T> Invoker<T> getInvoker(T proxy, Class<T> type, URL url) throws RpcException {
		if (url == null)
			throw new IllegalArgumentException("url == null");
		String extName = url.getParameter("proxy", "javassist");
		if (extName == null)
			throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.rpc.ProxyFactory) name from url(" + url.toString() + ") use keys([proxy])");
		ProxyFactory extension = (ProxyFactory) ExtensionLoader.getExtensionLoader(ProxyFactory.class).getExtension(extName);
		return extension.getInvoker(proxy, type, url);
	}
}
