CREATE TABLE OAUTH_CLIENT_DETAILS (
  CLIENT_ID               VARCHAR(256) PRIMARY KEY,
  RESOURCE_IDS            VARCHAR(256),
  CLIENT_SECRET           VARCHAR(256),
  SCOPE                   VARCHAR(256),
  AUTHORIZED_GRANT_TYPES  VARCHAR(256),
  WEB_SERVER_REDIRECT_URI VARCHAR(256),
  AUTHORITIES             VARCHAR(256),
  ACCESS_TOKEN_VALIDITY   INTEGER,
  REFRESH_TOKEN_VALIDITY  INTEGER,
  ADDITIONAL_INFORMATION  VARCHAR(4096),
  AUTOAPPROVE             VARCHAR(256)
);

CREATE TABLE OAUTH_ACCESS_TOKEN (
  TOKEN_ID          VARCHAR(256),
  TOKEN             BYTEA,
  AUTHENTICATION_ID VARCHAR(256),
  USER_NAME         VARCHAR(256),
  CLIENT_ID         VARCHAR(256),
  AUTHENTICATION    BYTEA,
  REFRESH_TOKEN     VARCHAR(256),

  CONSTRAINT OAUTH_ACCESS_TOKEN_USER_NAME_CLIENT_ID_KEY UNIQUE(USER_NAME, CLIENT_ID)
);

CREATE TABLE OAUTH_REFRESH_TOKEN (
  TOKEN_ID        VARCHAR(256),
  TOKEN           BYTEA,
  AUTHENTICATION  BYTEA
);