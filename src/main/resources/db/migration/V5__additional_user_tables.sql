CREATE TABLE ut_role
(
    id   UUID        NOT NULL,
    name VARCHAR(20) NOT NULL,
    CONSTRAINT pk_ut_role PRIMARY KEY (id)
);

CREATE TABLE ut_user_roles
(
    role_id UUID NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT pk_ut_user_roles PRIMARY KEY (role_id, user_id)
);

ALTER TABLE ut_user
    ADD username VARCHAR(20);

ALTER TABLE ut_user
    ALTER COLUMN username SET NOT NULL;

ALTER TABLE ut_role
    ADD CONSTRAINT uc_role_name UNIQUE (name);

ALTER TABLE ut_user
    ADD CONSTRAINT uc_user_email UNIQUE (email);

CREATE INDEX idx_user_email ON ut_user (email);

ALTER TABLE ut_user_roles
    ADD CONSTRAINT fk_utuserol_on_role FOREIGN KEY (role_id) REFERENCES ut_role (id);

ALTER TABLE ut_user_roles
    ADD CONSTRAINT fk_utuserol_on_user FOREIGN KEY (user_id) REFERENCES ut_user (id);

ALTER TABLE ut_user
    ALTER COLUMN email TYPE VARCHAR(50) USING (email::VARCHAR(50));

ALTER TABLE ut_user
    ALTER COLUMN password TYPE VARCHAR(120) USING (password::VARCHAR(120));