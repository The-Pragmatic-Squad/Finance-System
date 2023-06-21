create TABLE IF NOT EXISTS accounts (
    id bigint NOT NULL PRIMARY KEY unique AUTO_INCREMENT,
    user_name varchar(50) not null unique,
    email varchar(60) not null unique ,
    phone varchar(10) not null,
    credit_number varchar(16) not null unique,
    balance double not null,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    account_type varchar(50),
    is_active boolean default true,
   );
   CREATE SEQUENCE IF NOT EXISTS  accounts_id_seq AS BIGINT INCREMENT 1 start 1 OWNED BY  accounts.id;