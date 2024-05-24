// IMyService.aidl
package com.example.anotherprocess;

// Declare any non-default types here with import statements

interface IMyService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void sendMessage(String msg);
}