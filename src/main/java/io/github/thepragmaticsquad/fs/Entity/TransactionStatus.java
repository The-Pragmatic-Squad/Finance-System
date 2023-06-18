package io.github.thepragmaticsquad.fs.entity;

public enum TransactionStatus {
    PENDING("Pending"),
    SUCCESS("Completed"),
    FAILED("Failed");

    private final String displayName;

    TransactionStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
