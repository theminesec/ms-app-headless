package com.minesec.msa.client.sdk.payment.model;

/**
 * @author eric.song
 * @since 2023/6/26 13:16
 */
public enum EntryModeAbbreviation {
    CONTACTLESS((short) 1, "T"),

    CHIP((short) 2, "C"),

    MAGSTRIPE((short) 3, "S"),

    MANUAL((short) 4, "M"),

    FALLBACK((short) 5, "F"),

    QR_SCAN((short) 6, "CP"),

    QR_PRESENT((short) 6, "MP");

    private final short entryMode;

    private final String abbreviation;

    private EntryModeAbbreviation(short entryMode, String abbreviation) {
        this.entryMode = entryMode;
        this.abbreviation = abbreviation;
    }

    public static EntryModeAbbreviation valueOfEnum(String entryMode) {
        if (entryMode == null) {
            return null;
        }
        for (EntryModeAbbreviation each : EntryModeAbbreviation.values()) {
            if ((each.getEntryMode() + "").equals(entryMode)) {
                return each;
            }
        }
        return null;
    }

    public short getEntryMode() {
        return entryMode;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
