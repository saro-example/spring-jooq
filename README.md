# spring jooq

# STEP 1
## create account data
```
CREATE TABLE account (
	no bigserial NOT NULL,
	account varchar(64) NOT NULL,
	password varchar(512) NOT NULL,
	CONSTRAINT account_pk PRIMARY KEY (no)
);

CREATE UNIQUE INDEX account_account ON account USING btree (account);

CREATE TABLE account_role (
	no bigint NOT NULL,
	role varchar(64) NOT NULL,
	CONSTRAINT account_role_pk PRIMARY KEY (no, role)
);

-- test data
-- d760688da522b4dc3350e6fb68961b0934f911c7d0ff337438cabf4608789ba94ce70b6601d7e08a279ef088716c4b1913b984513fea4c557d404d0598d4f2f1 -> sha3_512('1234')
insert into account values (nextval('account_no_seq'), 'saro', 'd760688da522b4dc3350e6fb68961b0934f911c7d0ff337438cabf4608789ba94ce70b6601d7e08a279ef088716c4b1913b984513fea4c557d404d0598d4f2f1');

insert into account_role values (currval('account_no_seq'), 'master');
insert into account_role values (currval('account_no_seq'), 'admin');
```
# STEP 2
## set application.yml
```
# example
db.oauth:
  driverClassName: org.postgresql.Driver
  jdbcUrl: jdbc:postgresql://192.168.0.40:5432/dbname?charSet=UTF-8&prepareThreshold=1
  username: username
  password: password
  cachePrepStmts: true
  maximumPoolSize: 50
  minimumIdle: 1
```
# STEP 3
## gradle generate schema
```
gradle generateSampleJooqSchemaSource
```
# STEP 4
```
http://localhost:8080
```
