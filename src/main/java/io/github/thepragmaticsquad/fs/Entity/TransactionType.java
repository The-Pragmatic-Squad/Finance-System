package io.github.thepragmaticsquad.fs.Entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;


public enum TransactionType {
    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal"),
    TRANSFER("Transfer");

    private final String displayName;

    TransactionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
