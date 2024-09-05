package com.minesec.msa.client.sdk;

/**
 * @author eric.song
 * @since 2022/12/14 11:49
 */
public abstract class ClientOpts {
    public static boolean DBG = false;

    public static int DEFAULT_CONNECT_TIMEOUT = 5 * 1000;

    public static int DEFAULT_READ_TIMEOUT = 60 * 1000;

    public static int DEFAULT_QR_EXPIRED_TIMEOUT = 120;
}
