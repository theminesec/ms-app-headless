package com.minesec.msa.client.sdk.management.model;

import java.util.List;

import lombok.Getter;

/**
 * @author eric.song
 * @since 2023/4/20 11:18
 */
@Getter
public class DeviceInfoData {
    public String sysVersion;
    public String transType;
    public String groupId;
    public String mchName;
    public String mcc;
    public String uuid;
    public String helpSupportUrl;
    public String currency;
    public String mchLogo;
    public String mchContactAddress;
    public boolean loadEmvConfig;
	public boolean enableExternalReader;
	public List<String> supportedMPQRMethods; //supported QR payment methods.
}
