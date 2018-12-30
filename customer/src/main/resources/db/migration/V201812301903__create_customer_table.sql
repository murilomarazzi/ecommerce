CREATE TABLE CUSTOMER (
  ID            BIGSERIAL                    NOT NULL PRIMARY KEY,
  NAME          VARCHAR(255)                 NOT NULL,
  EMAIL         VARCHAR(255)                 NOT NULL,
  PHONE         VARCHAR(50),
  birth_date    DATE                         NOT NULL,
  USER_ID       BIGINT                       NOT NULL,
  CREATED_AT    TIMESTAMP WITHOUT TIME ZONE  NOT NULL,
  UPDATED_AT    TIMESTAMP WITHOUT TIME ZONE  NOT NULL
)