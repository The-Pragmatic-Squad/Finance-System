create TABLE IF NOT EXISTS transactions (
    id bigint NOT NULL PRIMARY KEY unique AUTO_INCREMENT,
    t_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    t_type varchar(50) not null,
    status varchar(50) not null ,
    amount double not null,
    balance_before double not null;
    balance_after double not null;
    details varchar(300);
    account_id BigInt not null;

    FOREIGN KEY (account_id)
    REFERENCES account (id)
   );
   CREATE SEQUENCE IF NOT EXISTS  transaction_id_seq AS BIGINT INCREMENT 1 start 1 OWNED BY  accounts.id;


    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

