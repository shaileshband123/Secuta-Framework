package com.unity.customelements;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * This class creates handles the calls of methods of custom webelements.
 **/

public class CustomElementLocator implements MethodInterceptor {

	/**
	 * The locator to get the webelement from the webpage.
	 **/
	private final ElementLocator locator;

	/**
	 * The constructor.
	 *
	 * @param locator
	 *            The locator to get the webelement from the webpage.
	 **/
	public CustomElementLocator(ElementLocator locator) {
		this.locator = locator;
	}

	/**
	 * Handles the method calls to a custom webelement.
	 *
	 * @param o
	 *            The object from which the method was called.
	 * @param method
	 *            The called method.
	 * @param objects
	 *            The parameter object of the value.
	 * @param methodProxy
	 *            Used to call the method of the superclass.
	 **/
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		if (o instanceof CustomWebElement) {
			try {
				return methodProxy.invokeSuper(o, objects);
			} catch (InvocationTargetException e) {
				throw e.getCause();
			}
		}
		else if (o instanceof WebElement) {
			WebElement displayedElement = locateElement();

			if (displayedElement != null) {
				return method.invoke(displayedElement, objects);
			} else {
				return methodProxy.invokeSuper(o, objects);
			}
		}

		return null;
	}

	/**
	 * Get an instance of the webelement.
	 *
	 * @return Returns a proxy element which implements the webelement. This is
	 *         needed to call the isVisible and other methods on itself (the custom
	 *         web element) without getting a nasty exception.
	 **/
	private WebElement locateElement() {
		return proxyForLocator(ElementLocator.class.getClassLoader(), locator);
	}

	/**
	 * @param loader
	 *            The class loader used to create the proxy.
	 * @param locator
	 *            The element locator used to locate the webelement.
	 * @return The proxy webelement.
	 **/
	private WebElement proxyForLocator(ClassLoader loader, ElementLocator locator) {
		InvocationHandler handler = new LocatingElementHandler(locator);
		WebElement proxy;

		proxy = (WebElement) Proxy.newProxyInstance(loader,
				new Class[] { WebElement.class, WrapsElement.class, Locatable.class }, handler);

		return proxy;
	}
}
