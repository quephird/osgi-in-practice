package com.quephird.oip.chapter2.counter;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

public class BundleCounterActivator implements BundleActivator, BundleListener {
    private BundleContext bc;

    public void start(BundleContext bc) throws Exception {
        this.bc = bc;
        bc.addBundleListener(this);
        printBundleCount();
    }

    public void stop(BundleContext bc) throws Exception {
        bc.removeBundleListener(this);
    }

    public void bundleChanged(BundleEvent be) {
        switch (be.getType()) {
            case BundleEvent.INSTALLED:
                System.out.println("Bundle installed. :)");
                printBundleCount();
                break;
            case BundleEvent.UNINSTALLED:
                System.out.println("Bundle uninstalled. :(");
                printBundleCount();
                break;
        }
    }

    private void printBundleCount() {
        int count = bc.getBundles().length;
        System.out.println("There are currently " + count + " bundles.");
    }
}