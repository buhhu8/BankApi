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
insert into USERS_INFO values ( default,'Nikolay', 'Bedniyi', 'Alekseevich', '4567', '123456', now());
insert into USERS_INFO values ( default,'Denis', 'Parov', 'Stasovich', '4007', '123456', now());
insert into USERS_INFO values ( default,'Anton', 'Volodin', 'Nikitovich', '4008', '123456', now());
insert into USERS_INFO values ( default,'Andrei', 'Mishustin', 'Dmitrich', '4409', '123456', now());
insert into USERS_INFO values ( default,'Anatoliy', 'Efremov', 'Sergeevich', '4308', '123456', now());

CREATE TABLE "invoice" (
                           "id" long auto_increment,
                           "bill_number" varchar UNIQUE,
                           "cor_bill" long PRIMARY KEY,
                           "balance" double,
                           "create_date_bill" date,
                           "type" varchar,
                           "user_id" int
);

CREATE TABLE "card" (
                        "id" long PRIMARY KEY auto_increment,
                        "card_number" long UNIQUE,
                        "exp_date" date,
                        "ccv" int,
                        "create_date" date,
                        "active_status" varchar,
                        "bill_id" int
);

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

ALTER TABLE "invoice" ADD FOREIGN KEY ("user_id") REFERENCES "USERS_INFO" ("ID");

ALTER TABLE "card" ADD FOREIGN KEY ("bill_id") REFERENCES "invoice" ("id");

ALTER TABLE "CB" ADD FOREIGN KEY ("partner_cor_bill") REFERENCES "partner" ("partner_cor_bill");

ALTER TABLE "CB" ADD FOREIGN KEY ("invoice_cor_bill") REFERENCES "invoice" ("cor_bill");

CREATE INDEX ON "USERS_INFO" ("CREATE_DATA_USER");

CREATE UNIQUE INDEX ON "USERS_INFO" ("PASSPORT_SERIES","PASSPORT_NUMBER");

CREATE INDEX ON "invoice" ("user_id");

CREATE INDEX ON "invoice" ("create_date_bill");

CREATE INDEX ON "card" ("bill_id");

CREATE INDEX ON "card" ("create_date");

CREATE INDEX ON "card" ("active_status");




