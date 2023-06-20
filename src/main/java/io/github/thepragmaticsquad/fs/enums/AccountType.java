package io.github.thepragmaticsquad.fs.entity;

public enum AccountType {
    VIP("vip Account"),
    STANDARD("Standard Account");

    private final String displayName;

    AccountType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
