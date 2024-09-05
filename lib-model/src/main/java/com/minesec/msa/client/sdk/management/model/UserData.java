package com.minesec.msa.client.sdk.management.model;

import lombok.Getter;
import lombok.ToString;

/**
 * @author eric.song
 * @since 2022/12/14 12:00
 */
@Getter
@ToString
public class UserData {
    String sysUserId;
    String loginUsername;
    String realname;
    String email;
    String avatarUrl;
    String userNo;
    String isAdmin;
    int state;
    String sysType;
    String belongInfoId;
    String createdAt;
    String updatedAt;
    String[] entIdList;
    Object allMenuRouteTree;
}
