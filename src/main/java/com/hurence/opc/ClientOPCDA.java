package com.hurence.opc;

import com.hurence.opc.da.OpcDaConnectionProfile;
import com.hurence.opc.da.OpcDaOperations;
import com.hurence.opc.da.OpcDaTemplate;
import com.hurence.opc.auth.NtlmCredentials;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class ClientOPCDA {
    public static void main(String [] args) throws  URISyntaxException {
        //create a connection profile
        OpcDaConnectionProfile connectionProfile = new OpcDaConnectionProfile()
                //change with the appropriate clsid
                .withComClsId("F8582CF2-88FB-11D0-B850-00C0F0104305")
                .withCredentials(new NtlmCredentials()
                        .withDomain("OPC-DOMAIN")
                        .withUser("OPC")
                        .withPassword("opc"))
                .withConnectionUri(new URI("opc.da://192.168.99.100"))
                .withSocketTimeout(Duration.of(5, ChronoUnit.SECONDS));

        //Create an instance of a da operations
        OpcDaOperations opcDaOperations = new OpcDaTemplate();
        //connect using our profile
        opcDaOperations.connect(connectionProfile).ignoreElement().blockingAwait();
    }
}
