ALTER TABLE "user"
    ADD password VARCHAR(255);

ALTER TABLE "user"
    ALTER COLUMN password SET NOT NULL;