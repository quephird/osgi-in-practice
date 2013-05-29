package com.quephird.oip.chapter2.hello;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class HelloOsgiActivator implements BundleActivator {
    public void start(BundleContext bc) throws Exception {
        System.out.println("Hello, OSGi!");
    }

    public void stop(BundleContext bc) throws Exception {
        System.out.println("Goodbye, OSGi!");
    }
}