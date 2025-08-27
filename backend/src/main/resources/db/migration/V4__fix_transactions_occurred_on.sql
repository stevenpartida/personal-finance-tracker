ALTER TABLE transactions
  ALTER COLUMN occurred_on TYPE timestamptz
  USING occurred_on::timestamptz;