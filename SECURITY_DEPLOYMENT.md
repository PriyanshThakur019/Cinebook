# Secure account API deployment

The account API uses BCrypt password hashes and server-side HTTP sessions. Do not send credentials to the current plain-HTTP host. Deploy behind HTTPS before enabling `/auth/login`, `/auth/register`, or `/auth/logout` for real users.

## Database migration

The backend uses `spring.jpa.hibernate.ddl-auto=validate`; it does not apply schema changes automatically. Back up the database, then apply `src/main/resources/db/manual/V1__add_user_email.sql` before deploying the new entity. The email column is nullable for existing accounts and has a case-insensitive unique index. New registrations require email.

## Existing passwords

Existing development credentials are plaintext. A one-time application migration is available behind `CINEBOOK_MIGRATE_PLAINTEXT_PASSWORDS=true`. Run it on one backend instance after the email migration and before serving traffic, then unset the variable and restart/roll out normally. The migration is idempotent for BCrypt hashes and logs only the number of credentials migrated, never password values. It stops startup if a legacy password exceeds BCrypt's 72-byte input limit; reset those accounts before running the migration. Keep a database backup and verify a test account before production rollout.

## HTTPS, cookies, and origins

Configure the TLS endpoint or trusted HTTPS reverse proxy and set:

- `CINEBOOK_SECURE_COOKIES=true`
- `CINEBOOK_SESSION_COOKIE_SAME_SITE=Lax` when UI and API share a site; use `None` only when cross-site cookies are genuinely required, and keep `Secure=true`.
- `CINEBOOK_ALLOWED_ORIGINS` to a comma-separated list of exact HTTPS frontend origins. Never use `*` with credentialed CORS.

The development defaults allow localhost Vite origins. For local HTTP-only testing, set `CINEBOOK_SECURE_COOKIES=false` in the local backend environment only; never use that setting on the hosted deployment. A same-origin reverse proxy is preferred and avoids cross-origin credentialed CORS.

## Browser auth flow

1. `GET /auth/csrf` to receive the CSRF token cookie and token value.
2. Send the token in `X-XSRF-TOKEN` and include credentials on `POST /auth/register`, `POST /auth/login`, and `POST /auth/logout`.
3. The server creates an HttpOnly session cookie on successful login. Use `GET /auth/me` to restore the safe profile after refresh.
4. Logout is a CSRF-protected POST and invalidates the session.

Passwords are accepted only in JSON POST bodies. `/authernticate`, `/login`, `/registerUser`, `/userIsAdmin`, credential listing, user listing, username-query booking history, and direct booking are disabled/removed. Booking and admin APIs remain unavailable until authorization, ownership, and transactional seat availability are implemented.
