package com.hurence.opc;

import com.hurence.opc.auth.NtlmCredentials;
import com.hurence.opc.ua.OpcUaConnectionProfile;
import com.hurence.opc.ua.OpcUaOperations;
import com.hurence.opc.ua.OpcUaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.time.Duration;

public class ClientOPCUA {
    public static void main (String [] args){
        Logger logger = LoggerFactory.getLogger(ClientOPCUA.class);
        //create a connection profile
        OpcUaConnectionProfile connectionProfile =  new OpcUaConnectionProfile()
                .withConnectionUri(URI.create("opc.tcp://Marvels-MacBook-Air.local:8666/UAExample"))
                //.withClientIdUri("hurence:opc-simple:client:test")
                .withClientName("Simple OPC test client")
                .withSocketTimeout(Duration.ofSeconds(5));

        //Create an instance of a ua operations
        OpcUaOperations opcUaOperations = new OpcUaTemplate();
        //connect using our profile
        opcUaOperations.connect(connectionProfile)
                .doOnError(throwable -> logger.error("Unable to connect", throwable))
                .ignoreElement().blockingAwait();
    }
}
