CREATE TABLE if not exists "USERS_INFO" (
                         "ID" long  PRIMARY KEY auto_increment,
                         "FIRST_NAME" varchar,
                         "LAST_NAME" varchar,
                         "MIDDLE_NAME" varchar,
                         "PASSPORT_SERIES" varchar,
                         "PASSPORT_NUMBER" varchar,
                         "CREATE_DATA_USER" timestamp
);

insert into USERS_INFO values ( default,'Denis', 'Dorofeev', 'Aleksandrovich', '4100', '123456', now());
insert into USERS_INFO values ( default,'Dima', 'Potemkin', 'Anatolievich', '4107', '123456', now());
insert into USERS_INFO values ( default,'Alex', 'Suhov', 'Petrovich', '4123', '123456', now());
insert into USERS_INFO values ( default,'Petr', 'Maksimov', 'Borisovich', '4134', '123456', now());
insert into USERS_INFO values ( default,'Maksim', 'Petrov', 'Aleksandrovich', '4124', '123456', now());


CREATE TABLE "INVOICE" (
                           "ID" long auto_increment,
                           "BILL_NUMBER" varchar(250) primary key,
                           "COR_BILL" varchar(250),
                           "BALANCE" double,
                           "BILL_CREATE_DATE" timestamp,
                           "TYPE" varchar,
                           "USER_ID" long
);

insert into INVOICE values ( default,'42132341427634563432', '41070800002314865748', '432010', now(), 'CREATE', 1);
insert into INVOICE values ( default,'42132341457623563432', '41070800002314865748', '32', now(), 'DELETE', 2);
insert into INVOICE values ( default,'42132456414634563432', '41070800002314865748', '34000', now(), 'BLOCK', 3);
insert into INVOICE values ( default,'42132341427634123432', '41070800002314865748', '123', now(), 'CREATE', 4);
insert into INVOICE values ( default,'42137896427634563432', '41070800002314865748', '9829', now(), 'CREATE', 5);


CREATE TABLE "CARD" (
                        "ID" long PRIMARY KEY auto_increment,
                        "CARD_NUMBER" varchar UNIQUE,
                        "EXP_DATE" date,
                        "CCV" int,
                        "CREATE_DATE" date,
                        "ACTIVE_STATUS" varchar,
                        "INVOICE_ID" long
);

insert into CARD values ( default, '4217234356783456', '2023-12-12', 354,now(), 'ACTIVE',1);
insert into CARD values ( default, '4217234312123135', '2023-12-12', 354,now(), 'ACTIVE',2);
insert into CARD values ( default, '4211235266783456', '2023-12-12', 354,now(), 'ACTIVE',3);
insert into CARD values ( default, '4217239872343456', '2023-12-12', 354,now(), 'ACTIVE',4);
insert into CARD values ( default, '4217234562113456', '2023-12-12', 354,now(), 'ACTIVE',5);

CREATE TABLE "partner" (
                           "id" long auto_increment,
                           "partner_bill_number" varchar UNIQUE,
                           "partner_cor_bill" varchar PRIMARY KEY,
                           "balance" double
);

CREATE TABLE "CB" (
                      "partner_cor_bill" varchar,
                      "invoice_cor_bill" varchar,
                      PRIMARY KEY ("partner_cor_bill", "invoice_cor_bill")
);

ALTER TABLE "INVOICE" ADD FOREIGN KEY ("USER_ID") REFERENCES "USERS_INFO" ("ID");

ALTER TABLE "CARD" ADD FOREIGN KEY ("INVOICE_ID") REFERENCES "INVOICE" ("ID");

ALTER TABLE "CB" ADD FOREIGN KEY ("partner_cor_bill") REFERENCES "partner" ("partner_cor_bill");

ALTER TABLE "CB" ADD FOREIGN KEY ("invoice_cor_bill") REFERENCES "INVOICE" ("BILL_NUMBER");

CREATE INDEX ON "USERS_INFO" ("CREATE_DATA_USER");

-- CREATE UNIQUE INDEX ON "USERS_INFO" ("PASSPORT_SERIES","PASSPORT_NUMBER");

CREATE INDEX ON "INVOICE" ("USER_ID");

CREATE INDEX ON "INVOICE" ("BILL_CREATE_DATE");

CREATE INDEX ON "CARD" ("INVOICE_ID");

CREATE INDEX ON "CARD" ("CREATE_DATE");

CREATE INDEX ON "CARD" ("CREATE_DATE");




