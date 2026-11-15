ALTER TABLE "UserDetails"
    ADD COLUMN IF NOT EXISTS "Email" VARCHAR(254);

CREATE UNIQUE INDEX IF NOT EXISTS "UserDetails_Email_UQ"
    ON "UserDetails" (LOWER("Email"))
    WHERE "Email" IS NOT NULL;