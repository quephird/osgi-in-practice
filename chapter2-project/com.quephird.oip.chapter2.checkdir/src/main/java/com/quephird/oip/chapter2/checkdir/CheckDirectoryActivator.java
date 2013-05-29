package com.quephird.oip.chapter2.checkdir;

import java.io.File;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

public class CheckDirectoryActivator implements BundleActivator {
    private static final long INTERVAL = 5000;
    private static final String BUNDLE_STAGING_DIRECTORY = "e:\\temp";

    private final Thread thread = new Thread(new BundleUpdater());
    private volatile BundleContext context;

    public void start(BundleContext context) throws Exception {
        this.context = context;
        thread.start();
    }

    public void stop(BundleContext context) throws Exception {
        thread.interrupt();
    }

    protected Bundle findBundleByLocation(String location){
        Bundle[] bundles = context.getBundles();
        for(Bundle bundle : bundles) {
            // Looks like the bundle updater uppercases the drive letter;
            // this is a hack
            if (bundle.getLocation().equalsIgnoreCase(location)) {
                return bundle;
            }
        }
        return null;
    }

    private class BundleUpdater implements Runnable {
        public void run() {
            try {
                File directory = new File(BUNDLE_STAGING_DIRECTORY);
                String fileLocation = "file:";

                while(!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(INTERVAL);
                    File[] files = directory.listFiles();
                    String bundleLocation;

                    for(File file : files) {
                        if (file.isFile() && file.getPath().endsWith(".jar")) {
                            bundleLocation = "file:" + file.getPath();
                            Bundle bundle = findBundleByLocation(bundleLocation);
                            if (bundle != null) {
                                System.out.println("Found a bundle that's already been installed: " + bundleLocation);
                            } else {
                                System.out.println("Installing new bundle: " + bundleLocation);
                                context.installBundle(bundleLocation);
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("I'm going now.");
            } catch (BundleException e) {
                System.err.println("Error updating bundle");
                e.printStackTrace();
            }
        }
    }
}
