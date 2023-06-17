package io.github.thepragmaticsquad.fs.Entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

public enum AccountType {
    VIP("vip Account"),
    CREDIT("Credit Account");

    private final String displayName;

    AccountType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
