CREATE SEQUENCE hibernate_sequence INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE TABLE account
(
    id         int4         NOT NULL,
    created_at timestamp(6) NULL,
    created_by varchar(255) NULL,
    status     int2         NULL,
    updated_at timestamp(6) NULL,
    updated_by varchar(255) NULL,
    valid_from timestamp(6) NULL,
    valid_to   timestamp(6) NULL,
    CONSTRAINT account_pkey PRIMARY KEY (id)
);

CREATE TABLE customer
(
    id            int4         NOT NULL,
    created_at    timestamp(6) NULL,
    created_by    varchar(255) NULL,
    email         varchar(255) NULL,
    first_name    varchar(255) NULL,
    last_name     varchar(255) NULL,
    personal_code varchar(255) NULL,
    phone_number  varchar(255) NULL,
    status        int2         NULL,
    updated_at    timestamp(6) NULL,
    updated_by    varchar(255) NULL,
    valid_from    timestamp(6) NULL,
    valid_to      timestamp(6) NULL,
    CONSTRAINT customer_pkey PRIMARY KEY (id)
);

CREATE TABLE "role"
(
    id         int4         NOT NULL,
    created_at timestamp(6) NULL,
    created_by varchar(255) NULL,
    "name"     varchar(255) NULL,
    updated_at timestamp(6) NULL,
    updated_by varchar(255) NULL,
    valid_from timestamp(6) NULL,
    valid_to   timestamp(6) NULL,
    CONSTRAINT role_pkey PRIMARY KEY (id)
);

CREATE TABLE shop
(
    id         int4         NOT NULL,
    address    varchar(255) NULL,
    created_at timestamp(6) NULL,
    created_by varchar(255) NULL,
    latitude   float8       NULL,
    longitude  float8       NULL,
    "name"     varchar(255) NULL,
    updated_at timestamp(6) NULL,
    updated_by varchar(255) NULL,
    url        varchar(255) NULL,
    valid_from timestamp(6) NULL,
    valid_to   timestamp(6) NULL,
    CONSTRAINT shop_pkey PRIMARY KEY (id)
);

CREATE TABLE "_user"
(
    id          int4         NOT NULL,
    created_at  timestamp(6) NULL,
    created_by  varchar(255) NULL,
    email       varchar(255) NULL,
    enabled     bool         NOT NULL,
    firstname   varchar(255) NULL,
    lastname    varchar(255) NULL,
    "password"  varchar(255) NULL,
    updated_at  timestamp(6) NULL,
    updated_by  varchar(255) NULL,
    valid_from  timestamp(6) NULL,
    valid_to    timestamp(6) NULL,
    customer_id int4         NULL,
    CONSTRAINT "_user_pkey" PRIMARY KEY (id),
    CONSTRAINT fknf3xgxkbcarxe8oxvt0utqsrj FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE category
(
    id                 int4         NOT NULL,
    category_type_code text         NULL,
    created_at         timestamp(6) NULL,
    created_by         varchar(255) NULL,
    "name"             varchar(255) NULL,
    updated_at         timestamp(6) NULL,
    updated_by         varchar(255) NULL,
    valid_from         timestamp(6) NULL,
    valid_to           timestamp(6) NULL,
    parent_category_id int4         NULL,
    CONSTRAINT category_pkey PRIMARY KEY (id),
    CONSTRAINT fks2ride9gvilxy2tcuv7witnxc FOREIGN KEY (parent_category_id) REFERENCES category (id)
);

CREATE TABLE customer_account
(
    id          int4         NOT NULL,
    created_at  timestamp(6) NULL,
    created_by  varchar(255) NULL,
    updated_at  timestamp(6) NULL,
    updated_by  varchar(255) NULL,
    valid_from  timestamp(6) NULL,
    valid_to    timestamp(6) NULL,
    account_id  int4         NOT NULL,
    customer_id int4         NOT NULL,
    CONSTRAINT customer_account_pkey PRIMARY KEY (id),
    CONSTRAINT fk6c5oqutth35p5vmw0svg56msa FOREIGN KEY (customer_id) REFERENCES customer (id),
    CONSTRAINT fkqpl8i89qkek8mq65tnbiiibm2 FOREIGN KEY (account_id) REFERENCES account (id)
);

CREATE TABLE product
(
    id                int4         NOT NULL,
    barcode           varchar(255) NULL,
    barcode_type_code int2         NULL,
    created_at        timestamp(6) NULL,
    created_by        varchar(255) NULL,
    description       varchar(255) NULL,
    "name"            varchar(255) NULL,
    product_type_code int2         NULL,
    updated_at        timestamp(6) NULL,
    updated_by        varchar(255) NULL,
    valid_from        timestamp(6) NULL,
    valid_to          timestamp(6) NULL,
    category_id       int4         NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (id),
    CONSTRAINT fk1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE review
(
    id               int4         NOT NULL,
    created_at       timestamp(6) NULL,
    created_by       varchar(255) NULL,
    description      varchar(255) NULL,
    review_type_code int2         NULL,
    score            int4         NULL,
    updated_at       timestamp(6) NULL,
    updated_by       varchar(255) NULL,
    valid_from       timestamp(6) NULL,
    valid_to         timestamp(6) NULL,
    customer_id      int4         NOT NULL,
    product_id       int4         NOT NULL,
    CONSTRAINT review_pkey PRIMARY KEY (id),
    CONSTRAINT fkgce54o0p6uugoc2tev4awewly FOREIGN KEY (customer_id) REFERENCES customer (id),
    CONSTRAINT fkiyof1sindb9qiqr9o8npj8klt FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE user_role
(
    id         int4         NOT NULL,
    created_at timestamp(6) NULL,
    created_by varchar(255) NULL,
    updated_at timestamp(6) NULL,
    updated_by varchar(255) NULL,
    valid_from timestamp(6) NULL,
    valid_to   timestamp(6) NULL,
    role_id    int4         NOT NULL,
    user_id    int4         NOT NULL,
    CONSTRAINT user_role_pkey PRIMARY KEY (id),
    CONSTRAINT fka68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES "role" (id),
    CONSTRAINT fkniaqoclrvx138sjw9hsollqav FOREIGN KEY (user_id) REFERENCES "_user" (id)
);

CREATE TABLE watchlist
(
    id                  int4         NOT NULL,
    created_at          timestamp(6) NULL,
    created_by          varchar(255) NULL,
    updated_at          timestamp(6) NULL,
    updated_by          varchar(255) NULL,
    valid_from          timestamp(6) NULL,
    valid_to            timestamp(6) NULL,
    watchlist_type_code int2         NULL,
    customer_id         int4         NOT NULL,
    product_id          int4         NOT NULL,
    CONSTRAINT watchlist_pkey PRIMARY KEY (id),
    CONSTRAINT fk1yply5vtbikeit1x6jjairb6g FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT fkbbsi3o8ohq57iio56r5y7hsig FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE alarm
(
    id              int4         NOT NULL,
    alarm_type_code int2         NULL,
    created_at      timestamp(6) NULL,
    created_by      varchar(255) NULL,
    max_value       float8       NULL,
    min_value       float8       NULL,
    "name"          varchar(255) NULL,
    updated_at      timestamp(6) NULL,
    updated_by      varchar(255) NULL,
    valid_from      timestamp(6) NULL,
    valid_to        timestamp(6) NULL,
    customer_id     int4         NOT NULL,
    product_id      int4         NOT NULL,
    CONSTRAINT alarm_pkey PRIMARY KEY (id),
    CONSTRAINT fkjdg3ipfj27axqi0chm6cun1dr FOREIGN KEY (customer_id) REFERENCES customer (id),
    CONSTRAINT fklol0vepjjku94u35fa0i9aysx FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE offer
(
    id                int4         NOT NULL,
    barcode           varchar(255) NULL,
    barcode_type_code int2         NULL,
    created_at        timestamp(6) NULL,
    created_by        varchar(255) NULL,
    description       varchar(255) NULL,
    "name"            varchar(255) NULL,
    updated_at        timestamp(6) NULL,
    updated_by        varchar(255) NULL,
    url               varchar(255) NULL,
    valid_from        timestamp(6) NULL,
    valid_to          timestamp(6) NULL,
    account_id        int4         NOT NULL,
    product_id        int4         NULL,
    shop_id           int4         NULL,
    CONSTRAINT offer_pkey PRIMARY KEY (id),
    CONSTRAINT fk3cow2cmfxb0nrt43hxm7yu1q3 FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT fk48r7fkvis75xe45esnx1yf4mo FOREIGN KEY (account_id) REFERENCES account (id),
    CONSTRAINT fk4xp6n7pb562vb04vr9y801mvc FOREIGN KEY (shop_id) REFERENCES shop (id)
);

CREATE TABLE price
(
    id              int4         NOT NULL,
    amount          float8       NULL,
    created_at      timestamp(6) NULL,
    created_by      varchar(255) NULL,
    currency        int2         NULL,
    price_type_code int2         NULL,
    quantity        float8       NULL,
    updated_at      timestamp(6) NULL,
    updated_by      varchar(255) NULL,
    valid_from      timestamp(6) NULL,
    valid_to        timestamp(6) NULL,
    offer_id        int4         NOT NULL,
    CONSTRAINT price_pkey PRIMARY KEY (id),
    CONSTRAINT fko4rccic2e98jrio7xuq51nd79 FOREIGN KEY (offer_id) REFERENCES offer (id)
);

CREATE TABLE feature
(
    id                int4         NOT NULL,
    created_at        timestamp(6) NULL,
    created_by        varchar(255) NULL,
    description       varchar(255) NULL,
    feature_type_code int2         NULL,
    "name"            varchar(255) NULL,
    updated_at        timestamp(6) NULL,
    updated_by        varchar(255) NULL,
    valid_from        timestamp(6) NULL,
    valid_to          timestamp(6) NULL,
    offer_id          int4         NOT NULL,
    CONSTRAINT feature_pkey PRIMARY KEY (id),
    CONSTRAINT fk78lujt2ig1f5gekkjqgp1ik8m FOREIGN KEY (offer_id) REFERENCES offer (id)
);

CREATE TABLE metric
(
    id               int4         NOT NULL,
    created_at       timestamp(6) NULL,
    created_by       varchar(255) NULL,
    metric_type_code int2         NULL,
    quantity         float8       NULL,
    updated_at       timestamp(6) NULL,
    updated_by       varchar(255) NULL,
    valid_from       timestamp(6) NULL,
    valid_to         timestamp(6) NULL,
    category_id      int4         NULL,
    offer_id         int4         NULL,
    product_id       int4         NULL,
    CONSTRAINT metric_pkey PRIMARY KEY (id),
    CONSTRAINT fkaypegbph1tcicjrm33o7ot51t FOREIGN KEY (offer_id) REFERENCES offer (id),
    CONSTRAINT fkojd14ywagkhqqhc7m9136lv37 FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT fktq5g0ngx37yj23cwvmcdb1s30 FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE SEQUENCE "_user_seq"
    MAXVALUE 9223372036854775807
    START 101;

CREATE SEQUENCE account_seq
    MAXVALUE 9223372036854775807
    START 101;

CREATE SEQUENCE alarm_seq
    MAXVALUE 9223372036854775807
    START 101;

CREATE SEQUENCE category_seq
    MAXVALUE 9223372036854775807
    START 101;

CREATE SEQUENCE customer_account_seq
    MAXVALUE 9223372036854775807
    START 101;

CREATE SEQUENCE customer_seq
    MAXVALUE 9223372036854775807
    START 101;

CREATE SEQUENCE feature_seq
    MAXVALUE 9223372036854775807
    START 101;

CREATE SEQUENCE metric_seq
    MAXVALUE 9223372036854775807
    START 101;

CREATE SEQUENCE offer_seq
    MAXVALUE 9223372036854775807
    START 101;

CREATE SEQUENCE price_seq
    MAXVALUE 9223372036854775807
    START 101;

CREATE SEQUENCE product_seq
    MAXVALUE 9223372036854775807
    START 101;

CREATE SEQUENCE review_seq
    MAXVALUE 9223372036854775807
    START 101;

CREATE SEQUENCE role_seq
    MAXVALUE 9223372036854775807
    START 101;

CREATE SEQUENCE shop_seq
    MAXVALUE 9223372036854775807
    START 101;

CREATE SEQUENCE user_role_seq
    MAXVALUE 9223372036854775807
    START 101;

CREATE SEQUENCE watchlist_seq
    MAXVALUE 9223372036854775807
    START 101;
